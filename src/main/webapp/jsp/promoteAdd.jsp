<!DOCTYPE html>

<html>
  <head>
    <%@page contentType="text/html;charset=utf-8" %>
    <%@page import = "java.util.ArrayList"%>
    <%@page import = "java.util.Comparator"%>
    <%@page import = "model.*"%>
    <%@page import = "model.dao.*"%>

    <style>
        <%@include file="/css/login.css"%>
    </style>

    <%int productId = (int) request.getAttribute("product_id");%>
  </head>
  <body>

        <form action="/shop/promote" method="post" class="login_form" style="text-align:left">
           <input type="hidden" name="product_id" value=<%=productId%>>
           <p><input type="checkbox" name="highlighting" value="true"> Выделить цветом</p>
           <p><input type="checkbox" name="priorityInList" value="true"> Повысить позицию в списке</p>
           <input type="submit" class="button" name="ок" style="text-align:center" value="ок"/>
        </form>
  </body>
</html>