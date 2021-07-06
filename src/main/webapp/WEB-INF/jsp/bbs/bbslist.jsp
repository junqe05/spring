<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>BBS List</title>
<title>BBS View</title>
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
	span.title{width:120px;}
	span.writer{width:80px;}
	span.hit{width:50px;}
	span.date{width:180px;}
	span.mgr{width:50px;}
	span.lvl{width:50px;}
	span.topdi{width:50px;}
	span.del{width:50px;}
	span.nth-child(5){border-right:none;}
</style>
</head>
<body>
<div id="container">
	<div id="list">
		<div id="title">
			<h3>BBS List</h3>
			<button id="add" type="button" onclick="location.href='/user2/addform';" >글쓰기</button>
		</div>
		<div class="row">
			<span class="num">글번호</span>
			<span class="title">제목</span>
			<span class="writer">작성자</span>
			<span class="hit">조회수</span>
			<span class="date">작성날짜</span>
			<span class="mgr">상위글</span>
			<span class="lvl">층</span>
			<span class="topdi">그룹</span>
			<span class="del">삭제</span>
		</div> 
		<c:forEach var="bbs" items="${list}">
			<div class="row">
				<a href="">
				<span class="num">${bbs.num}</span>
				<span class="title">${bbs.title}</span>
				<span class="writer">${bbs.writer}</span>
				<span class="hit">${bbs.hit}</span>
				<span class="date">${bbs.wdate}</span>
				<span class="mgr">${bbs.mgr}</span>
				<span class="lvl">${bbs.lvl}</span>
				<span class="topdi">${bbs.topid}</span>
				</a>
				<span class="del"><a href="">삭제</a></span>
			
			</div> 	
		</c:forEach>
	</div>
	
</div>


</body>
</html>