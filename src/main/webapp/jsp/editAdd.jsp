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
        <%@include file="/css/editAdd.css"%>
    </style>

    <%Product product = (Product)session.getAttribute("productForEdit");%>
  </head>
  <body>

     <form action="/shop/editAdd" method="post" class="login_form" enctype="multipart/form-data">
        <input type="text" class="login" name="title" placeholder="title" maxlength="30" value=<%=product.getTitle().replace(" ", "&nbsp;")%>>
        <input type="number" class="login" name="price" placeholder="price" value=<%=product.getPrice()%>>
        <input type="number" class="login" name="quantity" placeholder="quantity" value=<%=product.getQuantity()%>>
        <textarea name="description" style="resize: none;" class="login" rows="10" placeholder="description" maxlength="200" ><%=product.getDescription()%></textarea>
        <input type="file" style="display:none;" name="image" id="img">
        <div class="button load_img"><label for="img">Загрузить файл</label></div>
        <div class="login">Если хотите изменить изображение - загрузите новое!</div>
        <input type="submit" class="button" name="postAdd" value="post"/>
     </form>
  </body>
</html>