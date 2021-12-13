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
        <%@include file="/css/comments.css"%>
    </style>

    <%User user = (User)session.getAttribute("user");%>
    <%List<Comment> comments = (List<Comment>)request.getAttribute("comms");%>
    <%Product product = comments.get(0).getProduct();%>
</header>
<body>


   <% if(comments.size() != 0) { %>
        <div class="product_title"><%=product.getTitle()%></div>
        <div class="container">
            <div class="login_form">
                <% for(int i = 0; i < comments.size(); i++){ %>
                    <div>
                        <div class="comment login">
                            <div><%=comments.get(i).getUser().getFirstName()%>: </div>
                            <div><%=comments.get(i).getText()%></div>
                            <% if(user != null && comments.get(i).getUser().getId() == user.getId()){ %>
                                <form action="/shop/editComment" method="get">
                                    <input type="hidden" name="comment_id" value=<%=comments.get(i).getId()%>>
                                    <input type="submit" name="button" value="изменить"/>
                                </form>
                            <%}%>
                        </div>
                    </div>
                <%}%>
            </div>
        </div>
    <%}%>

</body>
</html>
