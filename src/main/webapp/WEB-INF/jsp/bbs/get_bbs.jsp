<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>page</title>
</head>
<body>

<div id="container">
	<div id="title">
		<h3>번호:${vo.num} 제목:${vo.title}</h3>
	</div>
	<div class="row">
		<span class="writer">작성자</span>
		<span class="hit">조회수</span>
		<span class="date">작성날짜</span>
		<span class="date">내용</span>
		<span class="del">삭제</span>
	</div> 
	<div class="row">
		<span class="writer">${vo.writer}</span>
		<span class="hit">${vo.hit}</span>
		<span class="date">${vo.wdate}</span>
		<span class="date">${vo.contents}</span>

		
	</div> 	
</div>

</body>
</html>