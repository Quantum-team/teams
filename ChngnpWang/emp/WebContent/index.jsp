<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function () {
		$(".search").click(function () {
			var name = $(".name1").val()
			alert(name)
		    window.location.href="queryByNameServlet?name="+name
		})
		$(document).on("click",".del",function(){
			var id = $(this).parent().parent().children().eq(0).html();
			alert(id)
			window.location.href="delServlet?id="+id
		})
		
	})
</script>
<body>
<div align="center">
	<h1>员工信息管理系统</h1>
	员工姓名:<input type="text" class="name1"/><input type="button" value="搜索" class="search">
	<table border="1px">
		<tr style="background-color: green">
			<td>员工编号</td>
			<td>员工姓名</td>
			<td>员工性别</td>
			<td>员工工资</td>
			<td>操作</td>
		</tr>
		<c:forEach var="emp" items="${ empList }">
		<tr>
			<td>${emp.empId }</td>
			<td>${emp.empName }</td>
			<td>${emp.sex }</td>
			<td>${emp.salary }</td>
			<td><a href="#" class="del">删除</a></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>