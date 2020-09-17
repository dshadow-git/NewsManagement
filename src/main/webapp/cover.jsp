<%--
  Created by IntelliJ IDEA.
  User: lei-long
  Date: 2020/9/17
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>image/uphold</title>
</head>
<body>
<form action="user/update/cover" method="post" enctype="multipart/form-data">
    图片：<input type="file" name="cover">
    电话：<input type="text" name="userId" value="000001">
    <input type="submit" value="提交">
</form>
</body>
</html>
