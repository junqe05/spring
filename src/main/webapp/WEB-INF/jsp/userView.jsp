<%@ page  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>User View</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" 
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" 
		crossorigin="anonymous">
	</script>
	<script>
		$(function(){
		   	$('div#edit').css('display','none');
		});
		function edit(){
			$('div#edit').css('display','block'); //보이게 하기
			$('div#view').css('display','none'); //안보이게 하기
		}

		function back(){
			$('div#edit').css('display','none'); //보이게 하기
			$('div#view').css('display','block'); //안보이게 하기
		}
		
		function update(){
			if(!confirm('입력된 정보로 변경하시겠습니까?')) return false;
			
			var param = {};
			param.num = ${user.num};
			param.name = $('#name').val();
			param.phone = $('#phone').val();
			param.email = $('#email').val();
			//alert(param);
			$.ajax({url: $('#update_form').attr('action'),
			method:'post',
			data:param,
			dataType:'text',
			success:function(res){
				alert(﻿eval(res) ? "업데이트 성공" : "업데이트 실패");
			},
			error:function(xhr, status, error){
				alert(status+':'+error);
			}
			});
			
			return false;
		}
		function del(num){
			if(!confirm('유저를 삭제하시겠습니까?')) return false;
			
			$.ajax({url: '/user2/delete/'+num,
			method:'post',
			dataType:'text',
			success:function(res){
				alert(﻿eval(res) ? "삭제 성공" : "삭제 실패");
				response.sendRedirect('/user2/list');
				},
				error:function(xhr, status, error){
					alert(status+':'+error);
				}
			});
			return false;
		}		
	</script>

<style type="text/css">
	#container{width:fit-content; padding:10px;	border:1px solid black; margin:0px auto;}
	#container>h3{text-align:center; border-bottom: 3px double black;}
	label { display:inline-block; width:50px; margin-right:10px; text-align:right;}
</style>


</head>
<body>
	<div id="container">
		<h3> 유저 정보 </h3>
		<div id="view">
			<table>
				<tr><th><label for="num">Num</label></th><td>${user.num}</td></tr>
				<tr><th><label for="name">Name</label></th><td>${user.name}</td></tr>
				<tr><th><label for="phone">Phone</label></th><td>${user.phone}</td></tr>
				<tr><th><label for="email">Email</label></th><td>${user.email}</td></tr>
			</table>
			<div id="buttons">
				<button type="button" onclick="del(${user.num});">삭제</button>
				<button type="button" onclick="edit();">수정</button>
				<button type="button" onclick="location.href='/user2/list';">목록</button>	
			</div>
		</div>
		
		<div id="edit">
		<form id="update_form" action="/user2/update" method="post" onsubmit="return update();">
			<table>
				<tr>
					<th>
						<label for="num">Num</label>
					</th>
					<td>
						<span name="num" id="num">${user.num}</span>
					</td>
				</tr>
				<tr>
					<th>
						<label for="name">Name</label>
					</th>
					<td>
						<input type="text" name="name" id="name" value="${user.name}">
					</td>
				</tr>
				<tr>
					<th>
						<label for="phone">Phone</label>
					</th>
					<td>
						<input type="text" name="phone" id="phone" value="${user.phone}">
					</td>
				</tr>
				<tr>
					<th>
						<label for="email">Email</label>
					</th>
					<td>
						<input type="text" name="email" id="email" value="${user.email}">
					</td>
				</tr>
			</table>
			<div id="buttons">
				<button type="button" onclick="back();">뒤로</button>
				<button type="submit" >저장</button>
				<button type="reset">취소</button>	
			</div>
		</form>
		</div>

	</div>
</body>
</html>