<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>
tr{
border:1px solid black;
}

table{
	width: 1000px;
}

.container{
padding-top:100px;

}

</style>
<c:set var="cpath" value="${ pageContext.request.contextPath }"/>
<div align="center" class="container">

	<div class="row">
    	<div align="center" class="col-12">
        <h2>쿠폰</h2>
        <p>고객님의 사용가능한 쿠폰 내역입니다.</p>
   	 </div>
	
	<div class="col-12">

	<table class="table">
	<tr>
		<!-- <th>번호</th> -->
		<th>쿠폰번호</th>
		<th>쿠폰명</th>
		<th>사용가능 회원등급</th>
		<th>쿠폰 혜택</th>
		<th>사용가능 기간</th>
	</tr>
		<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="7" align="center">
							<span style="font-weight: bold;">사용 가능한 쿠폰이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list }" varStatus="status">
						<tr>
						<c:if test="${vo.cr_isused eq 0}">
							<%-- <td>${status.count}</td> --%>
							<td>${vo.cr_serial }</td>
							<td>${vo.cpn_name }</td>
							<c:choose>
								<c:when test="${vo.grade_no eq 1 }">
									<td><strong>Welcome</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 2 }">
									<td><strong>Family</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 3 }">
									<td><strong>Bronze</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 4 }">
									<td><strong>Silver</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 5 }">
									<td><strong>Gold</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 6 }">
									<td><strong>Vip</strong></td>
								</c:when>
								<c:when test="${vo.grade_no eq 7 }">
									<td><strong>Vvip</strong></td>
								</c:when>
								
							</c:choose>
							<%-- <td>${vo.grade_no}</td> --%>  <!-- grade_no 1,2,3 으로 나온다...-->
							<td>${vo.cpn_price }</td>
							<td>${vo.cr_date1 } ~ ${vo.cr_date2}</td> <!-- refer에서 가지고오 -->
						
						</c:if>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
		<br>
		<!-- 페이징 처리 한페이지에 5개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/mypage/couponList?start=${start - 10 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/mypage/couponList?start=${start - 5 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/couponList?start=${start + 5 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/couponList?start=${start + 10 }">[다음]</a>
		</c:if>	
    
		</div>
		
</div>

</div>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>

    
    
    
 