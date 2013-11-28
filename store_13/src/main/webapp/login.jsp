<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓库管理平台</title>
<link href="themes/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<a href="http://demo.dwzjs.com"><img src="themes/default/images/login_logo.gif" /></a>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="http://bbs.dwzjs.com">加入收藏夹</a></li>
						<li><a href="">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="/login.action" method="post">
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input required" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="password" size="20" class="login_input required" />
					</p>
					<p>
						<label>验证码：</label>
						<input class="code required" name="validCode" type="text" size="5" />
						<span><img src="/validateCodeServlet" alt="" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p></p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2013 www.btttech.cn Inc. All Rights Reserved.
		</div>
	</div>
</body>
</html>