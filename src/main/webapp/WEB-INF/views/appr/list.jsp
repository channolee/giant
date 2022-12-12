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
		
		$("#logout").click(function(){
			location.href = 'logout';
		})
		
		$("#search_type").val('${vo.search_type}');
		$("#searchStatus").val('${vo.searchStatus}');
		$("#startDate").val('${vo.startDate}');
		$("#endDate").val('${vo.endDate}');
		
		$("#search_btn").click(function(){
			$("#searchFrm").attr("action","list").attr("method","post").submit();
		})
		
		$("#searchStatus").change(function(){
			$.ajax({
				url : "ajax",
				data : $("#searchFrm").serialize(),
				type : "post",
				success : function(data){
					alert("검색성공");
					$("#appr_con").html(data);
				},
				error :function(data){
					alert("에러안돼! 그러지마~~");
				}
			})
		})
		
		
	})
	
	function detail(appr_no){
		location.href = 'detail?appr_no=' + appr_no;
	}
		
</script>

</head>
<body>
	
	<div>
		${sessionScope.memberInfo.member_name}(${sessionScope.memberInfo.member_rank_kor})님 환영합니다.
		<input type="button" name="logout" id="logout" value="로그아웃">
	</div>
	
	<div>
		<input type="button" name="write_btn" id="write_btn" value="글쓰기" onclick="location.href='write'">
		<c:if test="${ memberInfo.member_rank le 2 }">
			<button type="button" name="appr_btn" id="appr_btn">대리결재</button>
		</c:if>
	</div>
	
	<form name="searchFrm" id="searchFrm">
		<div>
			<select name="search_type" id="search_type">
				<option value="choice">선택</option>
				<option value="writeName">작성자</option>
				<option value="appr">결재자</option>
				<option value="content">제목+내용</option>
			</select>
			
			<input type="text" name="search_text" id="search_text" placeholder="검색어를 입력하세요" value="${vo.search_text}">
			
			<select name="searchStatus" id="searchStatus" value="${vo.searchStatus}">
				<option value="state">결재상태</option>
				<option value="save">임시저장</option>
				<option value="wait">결재대기</option>
				<option value="ing">결재중</option>
				<option value="end">결재완료</option>
				<option value="ret">반려</option>
			</select>
			<br>
			<input type = "date" name = "startDate" id = "startDate" value="${ vo.startDate }"> 
			~ 
			<input type = "date" name = "endDate" id = "endDate" value="${ vo.endDate }">
			<input type="button" name="search_btn" id="search_btn" value="검색">
		</div>
	</form>
	
	<form name="listFrm" id="listFrm">
		<table border="1">
			<thead>
				<tr>
					<td>번호</td>
					<td>작성자</td>
					<td>제목</td>
					<td>작성일</td>
					<td>결재일</td>
					<td>결재자</td>
					<td>결재상태</td>
				</tr>
			</thead>
			
			<tbody id="appr_con">	
			<c:choose>
				<c:when test="${ empty list }">
					<tr>
						<td colspan="7">검색결과가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ list }" var="list" >
						<tr onclick="detail(${list.appr_no})" style="cursor:pointer;">
							<td>${ list.appr_no }</td>
							<td>${ list.appr_id }</td>
							<td>${ list.appr_subject}</td>
							<td>${ list.appr_reg_date }</td>
							<td>${ list.appr_sign_date }</td>
							<td>${ list.appr_sign_person }</td>
							<td>${ list.appr_sign_state }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</form>
</body>
</html>