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
		width: 100%;
	}
	
	.warp {
		width: 40%;
		margin: 0 auto;
		text-align: center;
		/* border: 1px solid; */
	}
	</style>
	</head>
	<body>
		<div class="warp">
			<form action="add">
				<table border="1">
					<tr>
						<td>名称：</td>
						<td><input type="text" name="names" required="required"></td>
					</tr>
					<tr>
						<td>类型：</td>
						<td><input type="text" name="type" required="required"></td>
					</tr>
					<tr>
						<td>采购时间：</td>
						<td><input type="date" name="buyDate" required="required"></td>
					</tr>
					<tr>
						<td>价格:</td>
						<td><input type="text" name="price" required="required"></td>
					</tr>
					<tr align="center" colspan="2" align="center">
						<td colspan="2"><input type="submit" value="添加"> 
							<input type="reset" value="重置">
						</td>
					</tr>
				</table>
			</form>
			
			
			<div align="left" id="message" style="margin-top: 80px; font-size: 20px; color: Red;">
				上传图片功能（非考试部分，选做）
			
				<form method="post" action="upload" enctype="multipart/form-data">
					<input type="file" name="file" /> 
					<input type="submit" value="上传" />
				</form>
			</div>
		</div>
	</body>
</html>