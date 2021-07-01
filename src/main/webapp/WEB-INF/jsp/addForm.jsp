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
		function add(){
			if(!confirm('입력된 정보가 맞습니까?')) return false;
			
			var param = {};
			param.name = $('#name').val();
			param.phone = $('#phone').val();
			param.email = $('#email').val();
			//alert(param);
			$.ajax({url: $('#add_form').attr('action'),
			method:'post',
			data:param,
			dataType:'text',
			success:function(res){
				alert(﻿eval(res) ? "추가 성공" : "추가 실패");
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
		<div id="add">
		<form id="add_form" action="/user2/add" method="post" onsubmit="return add();">
			<table>
				<tr>
					<th>
						<label for="name">Name</label>
					</th>
					<td>
						<input type="text" name="name" id="name"">
					</td>
				</tr>
				<tr>
					<th>
						<label for="phone">Phone</label>
					</th>
					<td>
						<input type="text" name="phone" id="phone"">
					</td>
				</tr>
				<tr>
					<th>
						<label for="email">Email</label>
					</th>
					<td>
						<input type="text" name="email" id="email"">
					</td>
				</tr>
			</table>
			<div id="buttons">
				<button type="button" onclick="location.href='/user2/list';">목록</button>
				<button type="submit" >저장</button>
				<button type="reset">취소</button>	
			</div>
		</form>
		</div>

	</div>
</body>
</html>