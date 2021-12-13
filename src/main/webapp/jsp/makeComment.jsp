<!DOCTYPE html>
<html>
<header>
    <%@page contentType="text/html;charset=utf-8" %>
    <%@page import = "java.util.ArrayList"%>
    <%@page import = "java.util.Comparator"%>
    <%@page import = "model.*"%>
    <%@page import = "model.dao.*"%>
    <%@page import = "java.util.List"%>

    <style>
        <%@include file="/css/login.css"%>
        <%@include file="/css/makeComment.css"%>
    </style>
</header>
<body>

      <form action="/shop/makeComment" class="login_form"method="post">
           <textarea class="login" style="resize: none;" rows="10" placeholder="ваш текст" name="text"></textarea>
           <input type="hidden" name="product_id" value=<%=request.getParameter("product_id")%>>
  		   <input type="submit" class="button" name="button" value="Опубликовать"/>
     </form>
</body>
</html>
