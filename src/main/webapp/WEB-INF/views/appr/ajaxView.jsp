<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<c:choose>
				<c:when test="${ empty list }">
					<tr>
						<td colspan="7">검색결과가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ list }" var="list" >
						<tr onclick="detail(${list.appr_no})">
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
			
	
