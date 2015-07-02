package com.unicorn;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Huxley on 7/2/15.
 */
public class Utils {
    public static long ONE_MIN = 60000;
    public static long ONE_HOUR = 3600000;
    public static long ONE_DAY = ONE_HOUR * 24;

    public static String time2ago(long time) {
        long passedTime = (new Date()).getTime() - time;
        if (passedTime < 2 * ONE_MIN) return "1 minute";
        else if (passedTime < 2 * ONE_HOUR) return String.format("%d minutes", passedTime / ONE_MIN);
        else if (passedTime <  2 * ONE_DAY) return String.format("%d hours", passedTime / ONE_HOUR);
        else return String.format("%d days", passedTime / ONE_DAY);
    }

    public static Object getSessionAttrAndRemoved(HttpSession session, String attrName) {
        Object val = session.getAttribute(attrName);
        session.removeAttribute(attrName);
        return val;
    }
}
