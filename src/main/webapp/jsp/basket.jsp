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
       <%@include file='/css/home.css'%>
        <%@include file='/css/basket.css'%>
    </style>

    <%User user = (User)session.getAttribute("user");%>
    <div class="this_user"><%=user%></div>
    <%List<Product> products = (List<Product>) session.getAttribute("productsAtBasket");%>

</header>
<body>

    <div class="container">
        <a class="header" href="/shop/HomePage">Главная</a>
    </div>
    <div class="main_block">
        <div class="adds">
            <%
            if(products != null) {
                for(int i = 0; i < products.size(); i++){
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
                        <div class="delete_from_basket">
                             <form action="/shop/purchase" method="post">
                                <input type="hidden" name="product_id" value=<%=products.get(i).getId()%>>
                                <input type="submit" class="button" name="delete" value="remove"/>
                             </form>
                        </div>
                    </div>
                <%}%>
            <%}%>
        </div>
    </div>
    <%if(products.size() != 0){%>
        <form class="form_buy" action="/shop/purchase" method="get">
            <input type="submit" class="button" name="buy" value="buyFromBasket"/>
        </form>
    <%}%>

</body>
</html>
