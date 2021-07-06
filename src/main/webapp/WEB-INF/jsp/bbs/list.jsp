<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 목록</title>
<style>
#container{width:fit-content; border:1px solid black; padding:20px; margin:0px auto;}
table { border-spacing: 0px; border-collapse: collapse;}
th,td{border-right: 1px solid black;padding:5px 10px ;text-align: center;}
th:nth-child(4){border-right:none;}
td:nth-child(4){border-right:none;}
td:nth-child(1){text-align: right;}
td:nth-child(2){text-align: left;}
th{border-bottom:3px double black;}
td{border-bottom:1px dotted black;}
tr:nth-child(even) { background-color: #eee;}
#container>h3{text-align: center; width:fit-content;
	margin:0px auto; border-bottom:3px double black;margin-bottom:10px;}
</style>
</head>
<body>
<div id='container'>
<h3>게시판 목록</h3>
<div id='header'>
	<table>
		<tr><th>번 호</th><th>제 목</th><th>작성자</th><th>작성일</th></tr>
		<c:forEach var="b" items="${list}">
			<tr><td>${b.num}</td>
			<td>${b.title}</td>
			<td>${b.writer}</td>
			<td>${b.wdate}</td></tr>
		</c:forEach>
	</table>
</div>
</div>
</body>
</html>