<!DOCTYPE html>

<%@page contentType="text/html;charset=utf-8" %>
<%@page import = "java.util.ArrayList"%>
<%@page import = "java.util.Comparator"%>
<%@page import = "model.*"%>
<%@page import = "model.dao.*"%>

<html>
  <head>
    <style>
      <%@include file="/css/home.css"%>
    </style>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

      <%User user = (User)session.getAttribute("user");%>
      <%ArrayList<Product> products = (ArrayList<Product>) new ProductsDaoDbcImpl().findAll();%>
  </head>
  <body>
      <div class="container">
         <a class="header" href="/shop/HomePage">Главная</a>
      </div>
      <div class="main_block">
      <div class="adds">
         <%
         if(products != null) {
             for(int i = 0; i < products.size(); i++){
                 if(products.get(i).getQuantity() != 0){
                    if(products.get(i).getUser().getId() == user.getId()){
         %>
                         <div class="ad">
                             <div class="heading"><%=products.get(i).getTitle()%></div>
                             <div class="text"><p><%=products.get(i).getDescription()%></p></div>
                             <div class="image"><img src =<%=products.get(i).getImageHref()%>></div>
                             <div class="lower_block">
                                 <div class="seller">Seller: <%=products.get(i).getUser().getFirstName()%></div>
                                 <div class="price">Price: <%=products.get(i).getPrice()%></div>
                                 <div class="quantity">Count: <%=products.get(i).getQuantity()%></div>
                             </div>
                                 <div class="add_to_basket">
                                     <form action="/shop/myAdds" method="post">
                                          <input type="hidden" name="product_id" value=<%=products.get(i).getId()%>>
                                          <input type="submit" class="button" name="edit" value="edit"/>
                                     </form>
                                      <form action="/shop/promote" method="get">
                                           <input type="hidden" name="product_id" value=<%=products.get(i).getId()%>>
                                           <input type="submit" class="button" name="promote" value="promote"/>
                                      </form>
                                 </div>
                         </div>
                    <%}%>
                 <%}%>
             <%}%>
         <%}%>
     </div>
     </div>




  </body>
</html>