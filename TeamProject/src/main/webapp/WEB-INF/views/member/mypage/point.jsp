<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<c:set var="cpath" value="${ pageContext.request.contextPath }"/>
<%-- <c:set var="availablePoint" value="${vo.mb_point - vo2.ord_usepoint}" /> --%>
<%-- <c:set var="price" value="${vo2.ord_price * 0.1}"/> --%>

<style>
tr{
border:1px solid black;
}

/* table{
width: 1200px;
}
 */
.row{
padding:50px;
margin-top:100px;
/* border:1px solid black; */
}

.tableClass{
margin-top:100px;
}

</style>

<body>
    
<%--     <div>
        <ol>
            <li>
                <a href="${pageContext.request.contextPath }">홈</a>
            </li>
            <li>
                <a href="${ pageContext.request.contextPath }/mypage/main">마이 쇼핑</a>
            </li>
            <li>
                적립금
            </li>
        </ol>
    </div> --%>
    <!-- 적립금 -->
 <div class="container">
 
 
    <div class="row border border-1">
    	<div align="center" class="col-6">
        <h2>적립금</h2>
        <p>고객님의 사용가능 적립금 금액 입니다.</p>
    </div>
    <div align="center" class="col-6">
       
                <strong>총 적립금</strong>
                <span>${vo.mb_point + usedPointSum}</span>
        	<br>
                <strong>사용가능 적립금</strong>
                <span> ${vo.mb_point} </span>
       	<br>
                <strong>사용된 적립금</strong>
               
           		<span >${usedPointSum}</span>
               
               
        
    </div>
   
   

      
    </div>

    <!-- 테이블-->
    <div align="center" class="col-12 tableClass">
       <h2>적립내역보기</h2>
        <table class="table">
            <tr>
                <td>주문날짜</td>
                <td>적립금</td>
                <td>관련주문</td>
                <td>내용</td>
            </tr>

            <!-- list 걸어주기 -->
            
            <c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="7" align="center">
							<span style="font-weight: bold;">적립금 내역이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo3" items="${list }">
						<tr>
							<td>${vo3.ord_date}</td>
							<td>${vo3.ord_price * 0.01}</td>
							<td>${vo3.ord_serial}</td>
							<td>${vo3.prod_name}</td> <!-- refer에서 가지고오 -->
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
        </table>
        	<!-- 페이징 처리 한페이지에 5개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/mypage/point?start=${start - 10 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/mypage/point?start=${start - 5 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/point?start=${start + 5 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/point?start=${start + 10 }">[다음]</a>
		</c:if>	
 
   
   
   
    </div>
    
    
    <!-- 적립내역보기 -->
    <div align="center">
        
    
    </div>
	
</div>

</body>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>
    
    
    
 