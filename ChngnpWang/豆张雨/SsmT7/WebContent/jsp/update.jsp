<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息修改页面</title>
</head>
<body>
<h1 align="center">学生信息管理系统</h1>
<c:forEach var="students" items="${students}">
<form action="update.do" method="post">
<input type="hidden" name="id" value="${students.id}">
<br>
<p>
学生姓名:<input type="text" name="name" value="${students.name}">
</p>
<br>
<p>
学生电话:<input type="text" name="phone" value="${students.phone}">
</p>
<br>
<p>
学生地址；<input type=""text name="adress" value="${students.adress}">
</p>
<br>
<input type="submit" value="修改">
<input type="button" value="取消">
</form>
</c:forEach>
</body>
</html>