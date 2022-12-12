<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	
	$(function(){
		var loginCheck = '${loginCheck}';
		
		if(loginCheck == 'idFail'){
			alert("등록되지 않은 사용자 입니다.");
			$("#member_id").focus();
		} else if(loginCheck == 'pwdFail'){
			alert("비밀번호가 틀렸습니다.");
		}
		
		$("#login_btn").click(function(){
			if($("#member_id").val() == ''){
				alert("아이디를 입력하세요");
			} else if($("#member_pwd").val() == ''){
				alert("비밀번호를 입력하세요");
			} else{
			$("#loginFrm").attr("action", "loginCheck").attr("method", "post").submit();
			}
		})
		
		
	})

</script>
</head>
<body>

	<form name="loginFrm" id="loginFrm">
		<table border="1" align="center">
		<caption>:::로그인:::</caption>
		
			<tr>
				<th>아이디</th>
				<td><input type="text" name="member_id" id="member_id"></td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="member_pwd" id="member_pwd"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" name="login_btn" id="login_btn" value="로그인">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>