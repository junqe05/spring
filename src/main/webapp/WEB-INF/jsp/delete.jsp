<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>삭제한 유저 보여주기</title>
</head>
<body>
	<h3>삭제한 유저</h3>
	<c:forEach var='user' items="${users}" varStatus="status">
		<div>Name : ${user.name}</div>
		<div>Phone : ${user.phone}</div>
		<div>Email : ${user.email}</div>
	</c:forEach>
	<div>
	<c:if test="${delete eq 1}">
		<h2>삭제 성공</h2>
	</c:if>
	<c:if test="${delete eq 0}">
		<h2>삭제 실패</h2>
	</c:if>
	</div>
</body>
</html>