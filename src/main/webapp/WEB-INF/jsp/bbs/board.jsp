<%@ page contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>page</title>
<style>
	#container{width:fit-content; padding:20px; margin:0px auto;}
	#container>h3{text-align:center;}
	#board{borer:1px solid black;}
	#board>div{padding:opx 5px;}
	.row{borer:1px solid black;}
	.row>label{display: inline-block; width:50px; padding:5px 5px;
	border-right:1px dotted black; margin-right:10px;}
	.row>label:not(:first-child){border-left:3px double black;}
	#contents{ overflow: auto; height:100px; border:1px solid black;}
</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>

<script type="text/javascript">
	$(function(){
	   	$('div#replform').css('display','none');
	});
	
	
	function sendForm(){
		alert("sendForm");
		var param = {};
		param.pnum = $('#pnum').val();
		param.writer = $('#writer').val();
		param.title = $('#title').val();
		param.contents = $('textarea#contents').val();
		alert(param);

		$.ajax({url:'/bbs/write',
			method:'post',
			data:param,
			dataType:'text',
			success:function(res){
				alert(res);
			},
			error:function(xhr, status, error){
				alert(status+':'+error);
			}
			});

		
		return false;
	}
</script>
</head>
<body>

	<div id="container">
		<h3>게시판 글 보기</h3>
		<div id="board">
			<div class="row">
				<label>제목</label>${vo.title}
				<span class="writer">작성자</span>
			</div>
			<div class="row">
				<label>번호</label>${vo.num}
				<label>작성자</label>${vo.writer}
				<label>조회수</label>${vo.hit}
				<label>작성일</label>${vo.wdate}
			</div> 
			<div id="contents" class="row">
				${vo.contents}			
			</div> 	
		</div>
		<div class='btn'>
			<button type='button' onclick="$('#replform').css('display','block');"> 답글쓰기</button>
		</div>
	</div>
	
	<div id="replform">
		<form action='/bbs/write'>
			<input type='hidden' name='pnum' id='pnum' value='${vo.num}'>
			<div><label for='writer'>작성자</label><input type='text' name='writer' id='writer'></div>
			<div><label for='title'>제목</label><input type='text' name='title' id='title'></div>
			<textarea row="10" cols="50" name='contents' id='contents' ></textarea>
			<div>
				<button type='button' onclick="sendForm();">저장</button>
				<button type='reset'>취소</button>
			</div>
		</form>
	</div>

</body>
</html>