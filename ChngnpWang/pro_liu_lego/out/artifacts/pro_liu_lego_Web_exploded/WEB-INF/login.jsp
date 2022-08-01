<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

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
<title>MyDaily登录界面</title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	background: url(img/loginbg.jpeg) no-repeat;
	background-image: url(img/loginbg.jpeg);
	background-size: 100% 100%;
	background-attachment: fixed;
}

#login-kuang {
	width: 700px;
	height: 500px;
	margin: auto;
	margin-top: 150px;
}

#login {
	width: 700px;
	height: 450px;
}

.user, .shuru {
	margin-top: 50px;
	font-family: "黑体";
	font-size: 35px;
}

.user {
	margin-left: 150px;
}

#jizhu, #forget {
	margin-left: 150px;
	margin-top: 50px;
	font-size: 35px;
}

#username, #userpassword {
	width: 300px;
	height: 40px;
}

#jizhu {
	width: 20px;
	height: 20px;
}

a {
	text-decoration: none;
	color: #000000;
}

a:hover {
	color: #FF5722;
}

#denglu {
	width: 400px;
	height: 60px;
	margin-left: 150px;
	margin-top: 35px;
	font-size: 30px;
	line-height: 60px;
}

#denglu:hover {
	background-color: #1E9FFF;
}
</style>
</head>
<body>
	<%
		String name = null;
		String pwd = null;
		Cookie[] cookies = request.getCookies();//得到cookie数组
		if(cookies != null){
			for (Cookie ck : cookies) {
				if ("name".equals(ck.getName())) {
					name = ck.getValue();
				}else if("password".equals(ck.getName())){
					pwd = ck.getValue();
				}
			}
		}
		%>
	<div id="warp">
		<div id="login-kuang">
			<fieldset id="login">
				<legend style="font-size: 30px; color: white;">登录</legend>
				<form action="LogionServlet" method="post">
					<span class="user">
						<strong>用户:</strong>
					</span>
					
					<input type="text" id="username" value="" name="username" required="required" class="shuru" /> 
					<br> 
					
					<span class="user">
						<strong>密码:</strong>
					</span>
					<input type="password" id="userpassword" value="" name="userpassword" required="required" class="shuru" /> 
					<br> 
					
					<input type="checkbox" name="strCookie" id="jizhu" value="" />
					<span style="font-size: 25px;">记住密码</span> 
					<a href="" id="forget style="font-size: 25px;">忘记密码</a> 
					<br> 
					
					<input type="submit"id="denglu" name="" value="登陆" />
				</form>
			</fieldset>
		</div>
	</div>
</body>
</html>