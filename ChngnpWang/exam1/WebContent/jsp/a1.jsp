<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新冠疫苗接种登记系统</title>
</head>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".btn2").clic(function(){
		alert("fyhgjfhgdc")
	})
})

</script>
<body>
<div align="center"><h1>新冠疫苗接种登记系统</h1></div>
	<div align="center">
		姓名:<input type="text" name="names">身份证号:<input type="text" name="idNo">
		<input type="button" value="查询" class="btn1"><input type="button" value="新增" class="btn2">
	</div>
	<div align="center">
		<table border="1px">
			<tr>
				<td>序号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>身份证号码</td>
				<td>地址</td>
				<td>疫苗企业</td>
				<td>接种时间</td>
				<td>操作</td>
			</tr>
			<c:forEach var="list" items="${vaccineList }">
				<tr>
				<td>${list.id }</td>
				<td>${list.name }</td>
				<td>${list.sex }</td>
				<td>${list.idNo }</td>
				<td>${list.address }</td>
				<td>${list.company }</td>
				<td>${list.inoculationTime }</td>
				<td><a href="#">删除</a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>