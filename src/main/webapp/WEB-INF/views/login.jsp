<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"  href="css/style.css">
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="loginOK">
		<div class="login-form">
			<div class="login-form__id">
				<div class="login-form__id-title"></div>
				<span>아이디</span>
				<input type="text" class="login-form__id-data" name="id" required="required">
			</div>
			<div class="login-form__password">
				<div class="login-form__id-title"></div>
				<span>비밀번호</span>
				<input type="password" class="login-form__password-data" name="password" required="required">
			</div>
			<div class="login-form__submit">
				<input type="submit" value="로그인">
			</div>
		</div>
	</form>
</body>
</html>