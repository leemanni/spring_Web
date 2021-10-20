<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="css/style.css">
<title>check</title>
</head>
<body>
	<table border="2">
		<tr>
			<th colspan="2">소개</th>
		</tr>
		<tr>
			<td>
				취미
			</td>
			<td>
				${vo.hobby }
			</td>
		</tr>
		<tr>
			<td>
				몸무게
			</td>
			<td>
				${vo.weight } kg
			</td>
		</tr>
		<tr>
			<td>
				신장
			</td>
			<td>
				${vo.height } cm
			</td>
		</tr>
		<tr>
			<td>
				나이
			</td>
			<td>
				${vo.age } 살
			</td>
		</tr>
	</table>
</body>
</html> 