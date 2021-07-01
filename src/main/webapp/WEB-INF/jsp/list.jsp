<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User List</title>
<title>User View</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>
<script type="text/javascript">
	


</script>



<style type="text/css">
	#container{width:fit-content; padding:10px;	border:1px solid black; margin:0px auto;}
	#title{text-align:center; border-bottom: 3px double black;}
	#add{text-align:rigth;}
	div.row{border-bottom:1px solid black;}
	a {text-decoration: none;}
	a:hover{background-color: #ddd;}
	span{display:inline-block; border-right: 1px solid black; text-align: center; padding:5px;}
	span.num{width:50px; text-align: right;}
	span.name{width:80px;}
	span.phone{width:140px;}
	span.email{width:180px;}
	span.del{width:50px;}
	span.nth-child(5){border-right:none;}
</style>
</head>
<body>
<div id="container">
	<div id="list">
		<div id="title">
			<h3>User List</h3>
			<button id="add" type="button" onclick="location.href='/user2/addform';" >회원추가</button>
		</div>
		<div class="row">
			<span class="num">num</span>
			<span class="name">name</span>
			<span class="phone">phone</span>
			<span class="email">email</span>
			<span class="del"></span>
		</div> 
		<c:forEach var="user" items="${list}">
			<div class="row">
				<a href="/user2/userview?num=${user.num}">
				<span class="num">${user.num}</span>
				<span class="name">${user.name}</span>
				<span class="phone">${user.phone}</span>
				<span class="email">${user.email}</span>
				</a>
				<span class="del"><a href="/user2/delete?num=${user.num}">삭제</a></span>
			
			</div> 	
		</c:forEach>
	</div>
	
</div>


</body>
</html>