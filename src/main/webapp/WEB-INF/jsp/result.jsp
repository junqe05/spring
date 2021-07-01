<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/user/register" method="post">
		<div><label for="num">Num</label><input type="text" name="num" id="num" value="${usr.num }"></div>
		<div><label for="name">Name</label><input type="text" name="name" id="name" value="${usr.name }"></div>
		<div><label for="phone">Phone</label><input type="text" name="phone" id="phone" value="${usr.phone }"></div>
		<div><label for="email">Email</label><input type="text" name="email" id="email" value="${usr.email }"></div>
		<div><button type="submit">전송</button></div>
	</form>


</body>
</html>