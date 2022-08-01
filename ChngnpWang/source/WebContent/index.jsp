<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"src="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	$(document).on("click",".del",function(){
		var id = $(this).parent().parent().children().eq(0).html();
		window.location.href="delServlet?id="+id
	})
	$(".search").blur(function() {
		var name = $(".search").val()
		window.location.href="queryByNameServlet?name="+name
	})
	$(".btn").click(function () {
		window.location.href="add.jsp"
	})
})
</script>
<style type="text/css">
	table tbody tr:hover{
    background-color:black;
    color:white;
}
</style>
<body>
<div align="center">
	<fieldset style="width: 330px">
		<legend>搜索</legend>
		名称:<input type="text" class="search"/>
	</fieldset>
</div>
<br/>
<div align="center" class="tb">
	<table border="1px" cellspacing="0" cellpadding = "10">
		<tr style="background-color: grey;color: white">
			<td>编号</td>
			<td>名称</td>
			<td>类型</td>
			<td>采购时间</td>
			<td>价格</td>
			<td>操作</td>
		</tr>
		<c:forEach var="source" items="${sourceList }">
		<tr>
			<td>${source.id }</td>
			<td>${source.name }</td>
			<td>${source.type }</td>
			<td>${source.buyDate }</td>
			<td>${source.price }</td>
			<td><a href="#" class="del">删除</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="right">共有${sourceList.size()}条数据</td>
		</tr>
	</table>
	<input type="button" class="btn" value="添加"/>
</div>

</body>
</html>