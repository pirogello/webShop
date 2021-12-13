<!DOCTYPE html>
<html>
<head>
    <%@page contentType="text/html;charset=utf-8" %>
    <%@page import = "java.util.ArrayList"%>
    <%@page import = "java.util.Comparator"%>
    <%@page import = "model.*"%>
    <%@page import = "model.dao.*"%>
    <style>
       <%@include file='/css/home.css'%>
    </style>

    <%User user = (User)session.getAttribute("user");%>
    <div class="this_user"><%=user%></div>
    <%ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");%>

</head>
<body>

<div class="container">
        <a class="header login" href="/shop/login">Вход</a>
        <a class="header registration" href="register">Регистрация</a>
        <form class="header exit" action="/shop/HomePage" method="get">
            <input type="submit" name="exit" value="Exit"/>
        </form>
        <%if(user != null){
            if(user.getRole().getTitle().equals("admin")){%>
                <a class="header" href="/shop/postAdd">Выложить объявление</a>
                <a class="header" href="/shop/myAdds">Редактировать мои объявления</a>
            <%}
        }%>
        <%if(user != null){%>
            <%if(user.getRole().getTitle().equals("user")){%>
                <a class="header" href="basket">Корзина</a>
            <%}%>
        <%}%>
    </div>

    <div class="my_info">
        <a href="">О НАС</a>
        <a href="">ДОСТАВКА</a>
        <a href="">ОТЗЫВЫ</a>
        <a href="">КОНТАКТЫ</a>
    </div>
    <div class="main_block">
       <%-- <div class="left_column">
            <div class="catalog">
                <p>Catalog</p>
            </div>
            <div class="news">
                <p>News</p>
            </div>
            <div class="info">
                <p>Info</p>
            </div>
        </div> --%>
        <div class="adds">
            <%
            if(products != null) {
                for(int i = 0; i < products.size(); i++){
                    if(products.get(i).getQuantity() != 0){
            %>
                        <div class=<%=(char)34%> ad <%if(products.get(i).isHighlighting()){%> isHighlighting<%}%><%=(char)34%> >
                            <div class="heading"><%=products.get(i).getTitle()%></div>
                            <div class="text"><p><%=products.get(i).getDescription()%></p></div>
                            <div class="image"><img src =<%=products.get(i).getImageHref()%>></div>
                            <div class="lower_block">
                                <div class="seller">Seller: <%=products.get(i).getUser().getFirstName()%></div>
                                <div class="price">Price: <%=products.get(i).getPrice()%></div>
                                <div class="quantity">Count: <%=products.get(i).getQuantity()%></div>
                            </div>
                            <div>
                                <%if(user != null){
                                    if(user.getRole().getTitle().equals("user")){%>
                                        <div class="add_to_basket">
                                            <form action="/shop/basket" method="post">
                                                 <input type="hidden" name="user_id" value=<%=user.getId()%>>
                                                 <input type="hidden" name="product_id" value=<%=products.get(i).getId()%>>
                                                <input type="number" class="quantityProd" name="quantity_products" value="1" max=<%=products.get(i).getQuantity()%> min="1"/>
                                                <input type="submit" class="button" name="buy" value="buy"/>
                                            </form>
                                        </div>
                                   <%}%>
                                <%}%>
                                 <div class="comments">
                                   <form action="/shop/comments" method="get">
                                        <input type="hidden" name="product_id" value=<%=products.get(i).getId()%>>
                                        <input type="submit" name="button"class="button comm" value="Комментарии"/>
                                   </form>
                                      <%if(user != null){
                                            if(user.getRole().getTitle().equals("user")){%>
                                                <form action="/shop/makeComment" method="get">
                                                    <input type="hidden" name="product_id"value=<%=products.get(i).getId()%>>
                                                    <input type="submit" name="button"  class="button comm" value="Комментировать"/>
                                                </form>
                                      <%}}%>
                                 </div>
                            </div>
                        </div>
                    <%}%>
                <%}%>
            <%}%>
        </div>
    </div>
    <script type="text/javascript">
        <%@include file="/js/main.js"%>
    </script>
</body>
</html>
