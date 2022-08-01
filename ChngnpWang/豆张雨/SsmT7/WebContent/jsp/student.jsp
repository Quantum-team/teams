<%@page import="com.po.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息展示</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style type="text/css">
#sj {
	color: orange;
}
th{
background-color:lightgreen;
}
</style>
</head>
<body>
	<script type="text/javascript">
		$(function() {
			$(".sel").click(function() {
				var id = $("#ss").val();
				window.location.href = "Stuid.do?id=" + id;
			})
			
			$(document).on("click", ".del", function() {
			if (confirm("确定要删除吗?")) {
				var id = $(this).parent().parent().children().eq(0).html();
				window.location.href = "Delid.do?id=" + id;
			}
		})
		
		$(document).on("click", ".xg", function() {
			var id = $(this).parent().parent().children().eq(0).html();
			window.location.href = "selid.do?id=" + id;
		})
		
		})
	</script>
	<h1 align="center">学生信息管理系统</h1>
	<table border="1" width="100%">
		<input type="text" id="ss">
		<input type="button" value="搜索" class="sel">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>电话</th>
			<th>地址</th>
			<th>操作</th>
		</tr>

		<%
		List<Student> students = (List<Student>) request.getAttribute("students");
		%>
		<c:forEach var="students" items="${students}">
			<tr>
				<td>${students.id}</td>
				<td>${students.name}</td>
				<td>${students.phone}</td>
				<td>${students.adress}</td>
				<td><input type="button" value="删除" class="del"> <input
					type="button" value="修改" class="xg"></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">共计为您查询出<span id="sj"><%=students.size()%></span>条数据
				<a href="add.jsp">新增</a>
			</td>
		</tr>
	</table>
</body>
</html>