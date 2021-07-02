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
	#container{width:fit-content; padding:10px; margin:0px auto; 
	   border:1px solid black; text-align:center;}
	h3{width:fit-content; border-bottom:3px double black; margin:20px auto;}
	label{display:inline-block; border-right:1px dotted black; 
	   text-align:left; padding-right:10px;}
	div{width:fit-content; border-bottom:1px solid black;}
	div#header{border:1px solid black; background-color:#ddd;
	   border-bottom:3px double black; text-align:center; font-weight:bolder;}
	div#header>label{text-align:center;}
	.lbnum{width:50px; text-align:right;}
	.lbsub{width:200px;}
	.lbwriter{width:100px;}
	.lbdate{width:100px; text-align:center;}
	.lbfname{width:300px;}
	div:nth-child(even) { background-color:#eee;}

</style>
</head>
<body>
<div id="container">
	<div id="list">
		<div id="title">
			<h3>User List</h3>
			<button id="upload" type="button" onclick="location.href='/upload';" >파일추가</button>
			<h3>업로드 목록 보기</h3>
		</div>
		<div id="header"><label class="lbnum">번호</label>
		      <label class="lbsub">제 목</label>
		       <label class="lbwriter">작성자</label>
		       <label class="lbdate">작성일</label>
		       <label class="lbfname">첨부파일</label>
		</div>

		<c:forEach var="uvo" items="${list}">
		   <c:set var="files" value="${uvo.files}"/>
		   <div><label class="lbnum">${uvo.num}</label>
		      <label class="lbsub">${uvo.subject}</label>
		       <label class="lbwriter">${uvo.writer}</label>
		       <label class="lbdate">${uvo.date}</label>
		       <label class="lbfname">
		      		<c:forEach var="fvo" items="${files}">
		        		 ${fvo.filename} 
     				 </c:forEach>
				</label>
		  </div>
		</c:forEach>
	</div>


</body>
</html>