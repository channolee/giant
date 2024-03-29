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

	var sessionCheck = '${memberInfo}';
	
	if(sessionCheck == null || sessionCheck == ''){
		alert("로그인을 해주세요");
		location.href = "login";
	}
	
	$(function(){
		
		var mode = '${mode}';
		
		var addSeq = '${appr_no}';
		var addWriter = '${memberInfo.member_name}';
		var addWriterId = '${memberInfo.member_id}';
		var memRank = '${memberInfo.member_rank}';
		
		var mofySeq = '${detailVO.appr_no}';
		var mofyWriter = '${detailVO.writeName}';
		var mofyWriterId = '${detailVO.appr_id}'
		var mofyStatus = '${detailVO.appr_sign_state}';
		
		if(mofyStatus == 'wait'){
			
			$("#signWait").prop("checked",true);
			
			if(addWriterId != mofyWriterId){
				$("#tmpBtn").hide();
			}else{
				$("#tmpBtn, #retBtn, #appBtn").hide();
			}
			
		}else if( mofyStatus == 'ing'){
			$("#signWait").prop("checked",true);
			$("#signIng").prop("checked",true);
			
			if(memRank == '1'){
				$("#tmpBtn").hide();
			}else{
				$("#tmpBtn, #retBtn, #appBtn").hide();
			}
			
		}else if( mofyStatus == 'end'){
			$("input[name=sign]").prop("checked",true);
			$("#tmpBtn, #retBtn, #appBtn").hide();
			
		}else if( mofyStatus == 'save'){
			if(addWriterId == mofyWriterId){
				$("#retBtn").hide();
			}
		}else if( mofyStatus == 'ret'){
			if(addWriterId == mofyWriterId){
				$("#retBtn").hide();
			}else{
				$("#tmpBtn, #retBtn, #appBtn").hide();
				
			}
		}
		
//			tmpBtn retBtn appBtn
		
		if(mode == 'add'){
			$("#seq").val(addSeq); 
			$("#writer").val(addWriter);
			$("#writerId").val(addWriterId)
			$("#retBtn").hide();
			$("#subj").removeAttr("readonly");
			$("#content").removeAttr("readonly");
		}else {
			$("#seq").val(mofySeq);
			$("#writer").val(mofyWriter);
			$("#writerId").val(mofyWriterId);	
		}
		
		if((addWriterId == mofyWriterId) &&  ( mofyStatus == 'save' || mofyStatus == 'ret')){
			$("#subj").removeAttr("readonly");
			$("#content").removeAttr("readonly");
		}
		
	})
	
	function fncSave(stat){
		
		var ivChk = initVal();
		if(ivChk != 1 ){
			$("#status").val(stat);
			var grade = '${memberInfo.member_rank}';
			if(stat == 'app'){
				if(grade == '4'){
					$("#status").val('wait');
				}else if(grade == '3'){
					$("#status").val('wait');
				}else if(grade == '2'){
					$("#status").val('ing');
				}else if(grade == '1'){
					$("#status").val('end');
				}
			}
			
			
			$("#writeFrm").attr("action","writeProc").attr("method","post").submit();	
		}
		
	}
	
	function initVal(){
		var temp;
		if($("#subj").val() == ''){
			alert("제목을 입력하세요");
			temp = 1;
		}else if($("#content").val() == ''){
			alert("내용을 입력하세요");
			temp = 1;
		}
		return temp;
	}
	
</script>
</head>
<body>
<div>
	<table border = "1">
		<tr>
			<th>결재요청</th>
			<th>과장</th>
			<th>부장</th>
		</tr>
		<tr>
			<td><input type = "checkbox" name = "sign" id = "signWait" disabled="disabled"></td>
			<td><input type = "checkbox" name = "sign" id = "signIng" disabled="disabled"></td>
			<td><input type = "checkbox" name = "sign" id = "signEnd" disabled="disabled"></td>
		</tr>
	</table>
</div>
<form name = "writeFrm" id = "writeFrm">
	<input type = "hidden" name = "status" id = "status">
	<input type = "hidden" name = "logId" id = "logId" value = "${memInfo.memId }"> 
	<div>
		<table>
			<tr>
				<th>글번호</th>
				<td><input type = "text" name = "seq" id = "seq" readonly="readonly"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type = "text" name = "writer" id = "writer" readonly="readonly">
					<input type = "hidden" name = "writerId" id = "writerId">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type = "text" name = "subj" id = "subj" value = "${detailVO.appr_subject }" placeholder="제목을 입력하세요" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="5" cols="25" name = "content" id = "content" placeholder="내용을 입력하세요" readonly="readonly">${detailVO.appr_content }</textarea></td>
			</tr>
		</table>
	</div>
</form>
<div>

	<input type = "button" name = "tmpBtn" id = "tmpBtn" value = "임시저장" onclick = "fncSave('save')">
	<input type = "button" name = "retBtn" id = "retBtn" value = "반려" onclick = "fncSave('ret')">
	<input type = "button" name = "appBtn" id = "appBtn" value = "결재" onclick = "fncSave('app')">
</div>
<div>
	<table border = "1">
		<tr>
			<th>번호</th>
			<th>결재일</th>
			<th>결재자</th>
			<th>결재상태</th>
		</tr>
		<c:forEach items="${appHistory }" var = "his">
			<tr> 
				<td>${his.hisSeq }</td>
				<td>${his.hisAppDate}</td>
				<td>${his.hisApperName }</td>
				<td>${his.hisStatusKor }</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>