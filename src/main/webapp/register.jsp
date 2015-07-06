<%--
  Created by IntelliJ IDEA.
  User: Huxley
  Date: 7/6/15
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<head>
  <s:set name="hideMainMenu" value="true"/>
</head>

<div class="main">
  <div class="content" style="width:100%">
    <h2>Register</h2>
    <form id="register-form" class="new_user" action="register.do" method="post" novalidate>
      <div class="box tabular">
        <p>
          <label for="user_login">
            Username
            <span class="required">*</span>
          </label>
          <input id="user_login" size="25" type="text" name="username">
        </p>
        <p>
          <label for="user_password">
            Password
            <span class="required">*</span>
          </label>
          <input id="user_password" size="25" type="password" name="password">
          <em class="info">Must be at least 8 characters long.</em>
        </p>
        <p>
          <label for="user_password_confirmation">
            Confirmation
            <span class="required">*</span>
          </label>
          <input id="user_password_confirmation" size="25" type="password" name="password_confirmation">
        </p>
        <p>
          <label for="user_email">
            Email
            <span class="required">*</span>
          </label>
          <input id="user_email" size="25" type="email" name="email">
        </p>
        <p>
          <label for="user_language">Language</label>
          <select id="user_language">
            <option value="english">English</option>
          </select>
        </p>
      </div>
      <input class="submit" type="submit" value="Submit">
    </form>
    <script>
      $('#register-form').validate({
        debug: true,
        ignore: '*:not([name])',
        rules: {
          username: "required",
          password: {
            required: true,
            minlength: 8
          },
          password_confirmation: {
            required: true,
            equalTo: "#user_password"
          },
          email: {
            required: true
          }
        },
        messages: {
          username: "please enter username",
          password: {
            required: "please enter password",
            minlength: "password must be at least 8 characters long"
          },
          password_confirmation: {
            required: "please enter password",
            equalTo: "please enter the same password as above"
          },
          email: {
            required: "please provide a email address"
          }
        }
      });
    </script>
  </div>
</div>
