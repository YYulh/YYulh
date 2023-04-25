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

.intro{
margin:100px;
}

.row{
padding:50px;
margin-top:100px;

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
                <a href="#">마이 쇼핑</a>
            </li>
            <li>
                주문조회
            </li>
        </ol>
    </div> --%>
    
   <!--주문조회-->
  
<div class="container ">

<div class="row border border-1">
	 <div align="center" class="col-12">
        <h2>주문조회</h2>
        <p>  주문내역조회 </p>
    </div>
 </div>
 
    <!-- 주문처리상태 필터 -->
 <!--     <form align="center">
       <div>
            <div>
                <select>
                    <option selected="selected">전체 주문처리상태</option>
                    <option>입금전</option>
                    <option>배송시작(취소/교환불가)</option>
                    <option>배송중</option>
                    <option>배송완료</option>
                    <option>환불</option>
                </select>
            </div>
        </div> -->
       <!-- 날짜 필터링 더 공부해서 수정하기 -->
     <!--    <span>
            <a>오늘</a>
            <a>1주일</a>
            <a>1개월</a>
            <a>3개월</a>
            <a>6개월</a>
        </span>
        <input value="2022-11-28"> ~ <input value="2022-11-28">
        <button>조회</button>
        <ul>
            <li>기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 지난 주문내역을 조회하실 수 있습니다.
            </li>
            <li>
                주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.
            </li>
        </ul>
 
    </form>  -->
    <!-- 주문 상품 정보-->
    <!-- 테이블-->
    <div align="center" class="col-12 tableClass">
      <!--   <p>주문 상품 정보</p> -->
        <table class="table align-middle">
            <tr>
                <th>주문일자<br>[주문번호]</th>
                <th>이미지</th>
                <th>상품정보</th>
                <th>수량</th>
                <th>상품구매금액</th>
                <th>주문처리상태</th>
                <th>취소/교환/반품</th>
            </tr>
  			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="7" align="center">
							<span style="font-weight: bold;">주문 내역이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list}">
						<tr>
							<td>${vo.ord_date}<br>
								[${vo.ord_serial}]
							</td>
							<td><img src="${pageContext.request.contextPath }/resources/upload/${vo.pp_filename}" width="100"></td>
							<td>${vo.prod_name}</td>
							<td>${vo.ord_cnt}</td>
							<td>${vo.ord_price}</td>
							<c:choose>
								<c:when test="${vo.pt_name eq 0 }">
									<td>상품준비중</td>
								</c:when>
								<c:when test="${vo.pt_name eq 1 }">
									<td>배송시작</td>
								</c:when>
								<c:when test="${vo.pt_name eq 2 }">
									<td>배송중</td>
								</c:when>
								<c:when test="${vo.pt_name eq 3 }">
									<td>배송완료</td>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${vo.pt_name eq 4 }">
									<td>환불중</td>
								</c:when>
								<c:otherwise>
									<td>-<td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
        </table>
        
           	<!-- 페이징 처리 한페이지에 5개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/mypage/orderStatus?start=${start - 10 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/mypage/orderStatus?start=${start - 5 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/orderStatus?start=${start + 5 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/mypage/orderStatus?start=${start + 10 }">[다음]</a>
		</c:if>	
    </div>
    	

    	 
</div>


</body>


<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>

  		