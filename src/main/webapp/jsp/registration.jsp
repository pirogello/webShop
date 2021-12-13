<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="java.lang.*" %>

<html>
  <head>
    <style>
      <%@include file="/css/registration.css"%>
    </style> 
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Добро пожаловать, JSP!</title>
  </head>
  <body>
     <form action="/shop/register" method="post" class="register_form" id="registration">

            <input type="text" class="login" id="regFirstName" name="firstName" maxlength="30" placeholder="Логин"/>

            <input type="text" class="login" id="regSecondName" name="secondName" maxlength="30"  placeholder="Имя"/>

            <select class="login name="role">
                <option>user</option>
                <option>admin</option>
            </select>

            <div class="pass"><input type="password" class="password" id="regPass" name="password" maxlength="30" placeholder="Пароль"/></div>

           <div class="pass"><input type="password" class="password" id="regCheckPass" name="checkPassword" placeholder="Повторите пароль"/>
                <% String userExist = (String)request.getAttribute("userExist"); %>
                <div class="userExist"><%= userExist %></div>
                <% String passMatch = (String)request.getAttribute("passMatch"); %>
                <div class="passMatch"><%= passMatch %></div>
                <% String wrongLength = (String)request.getAttribute("wrongLength"); %>
                <div class="wrongLength"><%= wrongLength %></div>
            </div>
  			<input type="submit" value="Зарегистрироваться" class="button" />
     </form>
     <script type="text/javascript">
        <%@include file="/js/registration.js"%>
    </script>
  </body>
</html>