<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师信息管理系统</title>
</head>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".delete").click(function(){
		if(confirm("确定要删除该信息吗？")){
			/* var id = $(this).prop("name"); */
			var id = $(this).parent().parent().children().eq(0).html();
			$("#inputId").val(id);
			$("#deleteForm").submit();
		}
		
	})
})
</script>
<body>
<div align="center">
<h1>教师信息的管理系统</h1>
	<table border="1px black" cellpadding="0" cellspacing="0">
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>性别</td>
			<td>年龄</td>
			<td>职务</td>
			<td>操作</td>
		</tr>
		<c:forEach var="teacher" items="${teacherList }">
		<tr>
			<td>${teacher.id}</td>
			<td>${teacher.name}</td>
			<td>${teacher.sex}</td>
			<td>${teacher.age}</td>
			<td>${teacher.position}</td>
			<td><input type="button" value="删除" class="delete" name="${teacher.id }"/></td>
		</tr>
		</c:forEach>
		
	</table>
	<form action="delServlet" id="deleteForm">
			<input type="text" name="id" style="display:none" id="inputId"/>
		</form>
	
</div>
</body>
</html>