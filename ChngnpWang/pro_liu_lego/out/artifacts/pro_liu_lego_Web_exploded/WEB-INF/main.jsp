<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
 * @author 刘老师
 * 
 * - 源码请所有同学合理使用，禁止非学习用途。
 * - 参照源码多想多练多Debug，杜绝无脑Copy！
 * - 有问题找学委统一汇总，课堂答疑，也可到办公室问我。
 * - 小红书|微信视频号 @老刘编程 三连到位，禁止下次一定！
 -->
 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>乐购资源管理系统</title>
<style type="text/css">
body {
    background-image: url(${pageContext.request.contextPath}/image/EnList-16.jpg);
    background-size: 90% 90%;
    background-repeat: repeat;
}

.warp {
	width: 50%;
	margin: 0 auto;
}

table {
	margin-top: 15px;
	width: -webkit-fill-available;
	text-align: center;
}

table th {
	background-color: #828282;
	color: white;
	height: 38px;
}

table tr {
	height: 30px;
}

table tr:hover {
	color: white;
	background-color: black;
}

h1 {
	margin-left: 40%;
}
</style>
</head>
<body>
	<h1>乐购资源管理系统</h1>
	<div class="warp">
		<fieldset>
			<legend>搜索</legend>
			<form action="list">
				名称：<input type="text" name="keyword"> <input type="submit" value="查询">
			</form>

		</fieldset>
		<table border="1" align="center" cellpadding="0" cellspacing="0">
			<tr align="center">
				<th>编号</th>
				<th>名称</th>
				<th>类型</th>
				<th>采购时间</th>
				<th>价格</th>
				<th>图片（选做）</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${sources}" var="o">
				<tr>
					<td>${o.id }</td>
					<td>${o.name }</td>
					<td>${o.type }</td>
					<td>${o.buyDate }</td>
					<td>${o.price }</td>
					<td width="150px">
						<img width="50%" height="50%" src="${pageContext.request.contextPath}/upload/${o.imageUrl }">
					</td>
					<td><a href="javascript:void(0);" onclick="del(${o.id })">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="right">
					<a href="add_page">新增</a>
					<span>共计 ${sources.size() }条数据</span>
				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		function del(id) {
			 if (confirm("确定删除该数据？")) {
				window.location.href="delete?id="+id;
	        }
		}
		function openwin() {  
			window.open ("add.jsp", "newwindow", "height=500, width=400, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") //写成一行  
		}
	</script>
</body>
</html>