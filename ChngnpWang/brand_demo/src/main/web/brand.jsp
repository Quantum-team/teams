<%--
  Created by IntelliJ IDEA.
  User: ChnpngWng
  Date: 10/22/2022
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <input type="button" value="新增" id="add"><br>
  <hr>
  <table border="1" onscroll="0" width="80%">
    <tr>
      <th>序号</th>
      <th>品牌名称</th>
      <th>企业名称</th>
      <th>排序</th>
      <th>品牌介绍</th>
      <th>状态</th>
      <th>操作</th>
    </tr>
  </table>

    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
          <td>${brand.brand_name}</td>
          <td>${brand.company_name}</td>
          <td>${brand.ordered}</td>
          <td>${brand.description}</td>
          <td>${status.count}</td>
          <c:if test="${brand.status == 1}">
            <td>启用</td>
          </c:if>
          <c:if test="${brand.status != 1}">
            <td>启用</td>
          </c:if>
          <td><a href="/brand_demo/selectByIdServlet?id=${brand.id}">修改</a> <a href="#">删除</a></td>
        </tr>
    </c:forEach>

  <script>
    document.getElementById("add").onclick = function (){
      location.href = "/brand_demo/addBrand.jsp";
    }
  </script>

  </body>
</html>
