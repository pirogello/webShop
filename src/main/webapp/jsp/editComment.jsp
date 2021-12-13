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
        <%@include file="/css/editAdd.css"%>
    </style>

    <%Comment comment = (Comment)request.getAttribute("comment");%>
</header>
<body>
     <form action="/shop/editComment" method="post" class="login_form">
            <textarea name="text" class="login"><%=comment.getText()%></textarea>
            <input type="hidden" name="user_id" class="login" value=<%=request.getAttribute("user_id")%>>
            <input type="hidden" name="comment_id" class="login" value=<%=request.getParameter("comment_id")%>>
            <input type="submit" name="button" class="button" value="Изменить"/>
     </form>
</body>
</html>
