<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML>
<html>
<head>
<!-- Custom Theme files -->
<link
	href="<%=request.getContextPath()%>/templates/admin/assets/login/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<!-- for-mobile-apps -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- //for-mobile-apps -->
<!--Google Fonts-->
<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700'
	rel='stylesheet' type='text/css'>
</head>
<body>
	<!--header start here-->
	<div class="header">
		<div class="header-main">
			<h1>BSong Register</h1>
			<div class="header-bottom">
				<div class="header-right w3agile">

					<div class="header-left-bottom agileinfo">
						<
						<form action="#" method="post">
							<input type="text" value="User name" name="username"
								onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'User name';}" /> <input
								type="password" value="Password" name="password"
								onfocus="this.value = '';"
								onblur="if (this.value == '') {this.value = 'password';}" />
							<div class="remember">
								<span class="checkbox1"> <label class="checkbox"><input
										type="checkbox" name="" checked=""><i> </i>Remember me</label>
								</span>
								
								<div class="forgot">
									<h6>
										<a href="#">Forgot Password</a>
									</h6>
								</div>
								<div class="clear"></div>
							</div>

							<input type="submit" value="Login">
						</form>

					</div>
				</div>

			</div>
		</div>
	</div>
	<!--header end here-->
	<div class="copyright">
		<p>
			<a href="https://www.facebook.com/nv.nghia.98">Nghia Nguyen -
				VinaEnter</a>
		</p>
	</div>
	<!--footer end here-->
</body>
</html>