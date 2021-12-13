<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
      <%@ page contentType="text/html;charset=utf-8" %>
      <%@ page import = " java.util.* " %>
       <style>
         <%@include file='/css/login.css'%>
      </style>
  </head>
  <body>
    <form action="/shop/login" method="post" class="login_form">
        <input type="text" class="login" name="login" placeholder="Логин"/>
        <div class="pass">
            <input type="password" class="password" name="password" placeholder="Пароль"/>
            <div class="noUser"><%=request.getAttribute("noUser")%></div>
        </div>
        <input type="submit" value="Войти" class="button"/>
    </form>
    <script type="text/javascript">
      <%@include file="/js/login.js"%>
  </script>
  </body>
</html>