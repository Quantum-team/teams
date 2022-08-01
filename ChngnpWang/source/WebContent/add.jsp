<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<h1>添加信息</h1>
<form action="addServlet" method="get">
名称:<input type="text" name="name"/><br/>
类型:<input type="text" name="type"/><br/>
采购时间:<input type="date" name="buyDate"/><br/>
价格:<input type="number" name="price"/><br/>
<input type="submit" value="提交">
<button>重置</button>
</form>
</div>

</body>
</html>