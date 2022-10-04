<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>员工表</h1>
	<form action="list" method="POST">
		<fieldset>
			<legend>员工姓名：</legend>
			<input type="text" value="123" name="empNamo">
			<input type="submit" value="搜索">
		</fieldset>
	</form>
	
	<table>
		<tr>
			<th>员工编号</th>
			<th>员工姓名</th>
			<th>员工性别</th>
			<th>员工工资</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${list}" var="o">
			<tr>
				<td>${o.empId }</td>
				<td>${o.empName }</td>
				<td>${o.sex }</td>
				<td>${o.salary }</td>
				<td><a href="javascript:void(0);" onclick="del(${o.empId} )">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
		function del(id) {
			 if (confirm("确定删除该数据？")) {
				window.location.href="delete?id="+id;
	       }
		}
	</script>
</body>
</html>