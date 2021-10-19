<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="css/style.css">
<title>Index</title>
</head>
<body>
	<form action="check">
		<label for="hobby">취미</label>
		<input id="hobby" name="hobby" type="text" required="required"><br/>
		<label for="weight">몸무게</label>
		<input id="weight" name="weight" type="text" required="required"><br/>
		<label for="height">신장</label>
		<input id="height" name="height" type="text" required="required"><br/>
		<label for="age">나이</label>
		<input id="age" name="age" type="text" required="required">
		<input type="submit" value="정보 넘기기">
	</form>
</body>
</html> 