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
			<th colspan="2">${name} 님의 소개</th>
		</tr>
		<tr>
			<td>
				취미
			</td>
			<td>
				${hobby }
			</td>
		</tr>
		<tr>
			<td>
				몸무게
			</td>
			<td>
				${weight }
			</td>
		</tr>
		<tr>
			<td>
				신장
			</td>
			<td>
				${height }
			</td>
		</tr>
		<tr>
			<td>
				나이
			</td>
			<td>
				${age }
			</td>
		</tr>
	</table>
</body>
</html> 