package com.unicorn;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import com.unicorn.domain.User;
import javafx.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Huxley on 7/2/15.
 */
public class Utils {
    public static long ONE_MIN = 60000;
    public static long ONE_HOUR = 3600000;
    public static long ONE_DAY = ONE_HOUR * 24;

    public static String time2ago(long time) {
        long passedTime = (new Date()).getTime() - time;
        if (passedTime < 2 * ONE_MIN) return " about 1 minute";
        else if (passedTime < 2 * ONE_HOUR) return String.format("%d minutes", passedTime / ONE_MIN);
        else if (passedTime <  2 * ONE_DAY) return String.format("%d hours", passedTime / ONE_HOUR);
        else return String.format("%d days", passedTime / ONE_DAY);
    }

    public static Object getSessionAttrAndRemoved(HttpSession session, String attrName) {
        Object val = session.getAttribute(attrName);
        session.removeAttribute(attrName);
        return val;
    }

    public static String upperFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * Check the differences between two objects.
     * These two objects must have the same type and offer get-methods for every field.
     * @param left
     * @param right
     * @return A map showing the differences, as Map<FieldName, Pair<leftValue, rightValue>>.
     */
    @Nullable
    public static List<Pair<String, Pair<Object, Object>>> diff(@NotNull Object left, @NotNull Object right) {
        if (left.getClass() != right.getClass()) return null;

        List<Pair<String, Pair<Object, Object>>> differences = new ArrayList<Pair<String, Pair<Object, Object>>>();
        try {
            Class<?> clazz = left.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                Method method = clazz.getDeclaredMethod("get" + upperFirst(field.getName()));
                Object leftValue = method.invoke(left);
                Object rightValue = method.invoke(right);
                if (null == leftValue && null == rightValue) continue;
                if (null == leftValue || !leftValue.equals(rightValue))
                    differences.add(new Pair<String, Pair<Object, Object>>(
                            field.getName(), new Pair<Object, Object>(leftValue, rightValue)));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.err.println("Check if offering all get-methods.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return differences;
    }

    /**
     * Only used for `normalizeSQLProperty` function.
     * Every item in this array should at most occur once in given string.
     */
    private static String [] filterWords = { "Id" };

    /**
     * For those filtered names, normalize them to a sql style.
     * e.g. `projectUserId` => `project.user.id`
     * @param name
     * @return A normalized name.
     */
    public static String normalizeSQLProperty(String name) {
        StringBuffer buffer = new StringBuffer(name);

        for (int i = 0; i < filterWords.length; ++i) {
            int cursor = buffer.indexOf(filterWords[i]);
            if (-1 < cursor) buffer.replace(cursor, cursor + filterWords[i].length(), "." + filterWords[i].toLowerCase());
        }

        return buffer.toString();
    }

    /**
     * Make the given name human readable.
     * e.g. `dueDate_A` => `Due date A`
     * @param name
     * @return A human readable name.
     */
    public static String humanize(String name) {
        if (0 == name.length()) return name;
        StringBuffer buffer = new StringBuffer(name);
        for (int i = 1; i < buffer.length() - 1; ++i) {
            if (Character.isUpperCase(buffer.charAt(i + 1))) {
                if (Character.isLowerCase(buffer.charAt(i))) {
                    buffer.replace(i + 1, i + 2, " " + Character.toLowerCase(buffer.charAt(i++ + 1)));
                } else if ('_' == buffer.charAt(i)) {
                    buffer.replace(i, i + 2, " " + Character.toLowerCase(buffer.charAt(i++ + 1)));
                }
            }
        }
        buffer.replace(0, 1, String.valueOf(Character.toUpperCase(buffer.charAt(0))));
        return buffer.toString();
    }

    public static User getCurrentUser() {
        try {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        } catch (Exception e) { // no login yet
            return null;
        }
    }
}
