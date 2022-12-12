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
		
		var tmp = 1;
		$("#addFile").click(function(){
			var html = '<div><input type = file name=file' + tmp +' id=file'+ tmp +' onchange=fileCheck(this)><input type = button value = 삭제 onclick=fncDelete(this)><div>'
			$("#fileDv").append(html);
			tmp += 1;
		})
	
		$("#reg_btn").click(function(){
			$("#frm").attr("action", "insert").attr("method", "post").submit();
		})
		
		$("#upt_btn").click(function(){
			$("#frm").attr("action", "update").attr("method", "post").submit();
		}) 	
		
	})
	
	function fncDelete(btn){
		$(btn).parent().remove();
	}
	
	function fileCheck(fileInput) {
	    var ext = $(fileInput).val().split(".").pop().toLowerCase();
	    if($.inArray(ext, ["gif","jpg","jpeg","png","bmp"]) == -1) {
	        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
	        $(fileInput).val("");
	        return;
	}
	    
	    var file = fileInput.files[0];
	    var _URL = window.URL || window.webkitURL;
	    var img = new Image();
	    img.src = _URL.createObjectURL(file);
	    img.onload = function() {
	        if (img.width > 500 || img.height > 500) {
	            alert("이미지 가로 500px, 세로 500px로 맞춰서 올려주세요.");
	            $(fileInput).val("");
	        }
	    }
	}


</script>
</head>
<body>
<!-- action이 requestMapping으로 @Autowired  -->
<form name="frm" id="frm" enctype="multipart/form-data">
			
			작성자 : <input type="text" name="name" id="name" value="${ map.memName }"><br>
			ID : <input type="text" name="id" id="id" value="${ map.memId }"><br>
			제목 : <input type="text" name="subject" id="subject" value="${ map.boardSubject }"><br>				
			내용 : <br>
			<textarea rows="5" cols="30" name="content" id="content">${ map.boardContent }</textarea><br>
			
			<div id="fileDv">
				<input type="button" name="addFile" id="addFile" value="파일추가"><br>
			</div>
			<div id="fileList">
				<c:forEach items="${ fileList }" var="fList">
					<a href="download?realName=${ fList.REAL_NAME }&saveName=${ fList.SAVE_NAME }">${ fList.REAL_NAME }</a>
				</c:forEach>
			</div>
			<c:choose>
				<c:when test="${ empty map }">
					<input type="button" name="reg_btn" id="reg_btn" value="등록">
				</c:when>
				<c:otherwise>
					<input type="button" name="upt_btn" id="upt_btn" value="수정">
					<input type="hidden" name="seq" id="seq" value="${map.seq}">
				</c:otherwise>
			</c:choose>
		
</form>
</body>
</html>