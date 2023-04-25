<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<br>
<br>
<div align = "center">
	<c:choose>
		<c:when test="${!check}">
			<h3>입력하신 정보로 가입 된 회원 아이디는 존재하지 않습니다.</h3>
			<a href="${ pageContext.request.contextPath }/member/login/findForm?mode=id">ID찾기</a> | 
			<a href="${ pageContext.request.contextPath }/member/login/findForm?mode=pw">PW찾기</a> |
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${param.mode == 'id' }">
						<h2>고객님의 아이디는</h2>
						<h3>${id}</h3>
						<h2>입니다.</h2>
						<a href="${ pageContext.request.contextPath }/member/login/findForm?mode=pw">PW찾기</a> | 
				</c:when>
				<c:otherwise>
						<h2>고객님의 비밀번호는</h2>
						<h3>${pw}</h3>
						<h2>입니다.</h2>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
	<a href="${ pageContext.request.contextPath }/member/login/loginForm">로그인하러 가기 -></a>
	</div>
<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>


		