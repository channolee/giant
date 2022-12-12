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
		$("#search_type").val('${map.search_type}');
		$("#startDate").val('$(map.startDate)');
		$("#endDate").val('$(map.endDate)');
		
		$("#search_btn").click(function(){
			$.ajax({
				url : "ajax",
				type : "post",
				data : $("#searchFrm").serialize(),
				success : function(result){
					$("#listFrm").html(result);
					
				},
				error : function(){
					alert("ajax실패");
				}
			
			})
		})
	})
	
	 function fncDelete(){
		var frm = document.listFrm;
		frm.method = "post";
		frm.action = "delete";
		frm.submit();
	}
	
	function list(num){
		$("#curPage").val(num);
		$("#search_btn").click();
	}

</script>
</head>
<body>
	<form name="searchFrm" id="searchFrm" action="list" method="post">
			<input type="hidden" name="curPage" id="curPage" value="1">
			<input type="hidden" name="pageScale" id="pageScale" value="10">
			<div>
				<select name="search_type" id="search_type">
				    <option value="choice">선택</option>
				    <option value="name">작성자</option>
				    <option value="subject">제목</option>
				    <option value="content">제목+내용</option>
				</select>
				
				<input type="text" name="keyword" id="keyword" value="${ map.keyword }">
				<input type="button" name="search_btn" id="search_btn" value="검색"><br>
				<input type = "date" name = "startDate" id = "startDate" value="${ map.startDate }"> 
				~ 
				<input type = "date" name = "endDate" id = "endDate" value="${ map.endDate }">
			</div>
			</form>
			
			<div>
				<input type="button" name="write_btn" id="write_btn" value="글쓰기" onclick="location.href='write'">
				<input type="button" name="delete" id="delete" value="삭제" onclick="fncDelete()">
			</div>
			
		<form name="listFrm" id="listFrm">
		<table border="1">
			<tr>
				<td><input type="checkbox" name="check_all" id="check_all"></td>
				<td>글번호</td>
				<td>작성자(ID)</td>
				<td>제목</td>
				<td>작성일</td>
				<td>수정일</td>
				<td>조회수</td>
			</tr>
			
			<c:forEach items="${ list }" var="list">
			<tr>
				<td><input type="checkbox" name="delete" id="delete" value="${ list.seq }"></td>
				<td>${ list.seq }</td>
				<td>${ list.memName}( ${list.memId} )</td>
				<td><a id="page_view" href="/map?seq=${list.seq}">${ list.boardSubject }</a></td>
				<td>${ list.regDate }</td>
				<td>${ list.uptDate }</td>
				<td>${ list.viewCnt }</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td colspan="7">
					<!-- **처음페이지로 이동 : 현재 페이지가 1보다 크면 [처음]하이퍼링크를 화면에 출력-->
					<c:if test="${pageMap.curBlock > 1}">
					   <a href="javascript:list('1')">[처음]</a>
					</c:if>
					<!-- **이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 -->
					<c:if test="${pageMap.curBlock > 1}">
					    <a href="javascript:list('${pageMap.prevPage}')">[이전]</a>
					</c:if>
					<!-- **하나의 블럭에서 반복문 수행 시작페이지부터 끝페이지까지 -->
					<c:forEach var="num" begin="${pageMap.blockBegin}" end="${pageMap.blockEnd}">
					<!-- **현재페이지이면 하이퍼링크 제거 -->
					    <c:choose>
					        <c:when test="${num == pageMap.curPage}">
					            <span style="color: red">${num}</span>&nbsp;
					        </c:when>
					        <c:otherwise>
					            <a href="javascript:list('${num}')">${num}</a>&nbsp;
					        </c:otherwise>
					    </c:choose>
					</c:forEach>
					<!-- **다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
					<c:if test="${pageMap.curBlock <= pageMap.totBlock}">
					    <a href="javascript:list('${pageMap.nextPage}')">[다음]</a>
					</c:if>
					<!-- **끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 -->
					<c:if test="${pageMap.curPage <= pageMap.totPage}">
					    <a href="javascript:list('${pageMap.totPage}')">[끝]</a>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>