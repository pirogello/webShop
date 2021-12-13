<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>

<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>
          <%@include file="/css/login.css"%>
          <%@include file="/css/editAdd.css"%>
        </style>
  </head>
  <body>
     <form action="/shop/postAdd" method="post" class="login_form" enctype="multipart/form-data">
          <input type="text" class="login" name="title" placeholder="title" maxlength="30">
          <input type="number" class="login" name="price" placeholder="price">
          <input type="number" class="login" name="quantity" placeholder="quantity">
          <textarea name="description" style="resize: none;" class="login" rows="10" placeholder="description" maxlength="200" ></textarea>
          <input type="file" style="display:none;" name="image" id="img">
          <div class="button load_img"><label for="img">Загрузить файл</label></div>
          <input type="submit" class="button" name="postAdd" value="post"/>
       </form>
  </body>
</html>