<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<script type="text/javascript" src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(function () {
	$(".del").click(function () {
		if(confirm("删除？")){
			var id=$(this).parent().parent().children().eq(0).html()
			window.location.href='delCartServlet?id='+id+'';
		}
	})
})
</script>
</head>
<body>
<div align="center">
<h1>购物车</h1>
	<table cellpadding="0" cellspacing="0" border="1px gray">
		<tr bgcolor="blue">
			<td>商品编号</td>
			<td>商品名称</td>
			<td>商品单价</td>
			<td>商品数量</td>
			<td>添加时间</td>
			<td>商品描述</td>
			<td>操作</td>
		</tr>
		<c:forEach var="cart" items="${cartList }">
			<tr>
				<td>${cart.id}</td>
				<td>${cart.name}</td>
				<td>${cart.price}</td>
				<td>${cart.count}</td>
				<td>${cart.createTime}</td>
				<td>${cart.desc}</td>
				<td><a href="#" class="del">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>