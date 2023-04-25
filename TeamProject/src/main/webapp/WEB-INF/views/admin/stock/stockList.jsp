<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
tr,th{
border-bottom: 1px solid #dfdfdf;
}
.del{
margin-left:600px;
}
#section{
margin-top:100px;
}

</style>
<script>
	function deleteStock(){
	
	
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else{
			document.list.submit();
		}
		return true;
	}

	
</script> 
 
	<div align="center" id="section">
	<br>
	<h3>제품 재고목록 조회</h3><br>
	<form action = "${pageContext.request.contextPath}/admin/stock/deleteStock" name="list" method = "post">
		<table class="memberTable">
			<tr align="center">
				<th>재고관리번호</th>
				<th>제품명</th>
				<th>누적판매수량</th>
				<th>총 매출액</th>
				<th>입고날짜</th>
				<th>불량재고량</th>
				<th>입고량</th>
				<th>재고량</th>
				<th>체크V</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list}"> <!-- 제품목록에 데이터가 존재하지 않으면 -->
					<tr align="center">
						<th colspan="9" >
							<span style="font-weight: bold;" >제품정보가 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
						<tr align = "center">
							<th>${vo.stock_no}</th>							
							<th><a href = "${pageContext.request.contextPath}/admin/stock/insertForm?no=${vo.prod_no}">${vo.prod_name }</a></th>
							<th>${vo.stock_sellcnt}</th>
							<th>${vo.stock_sell}</th>
							<th>${vo.stock_date}</th>
							<th>${vo.stock_bad}</th>
							<th>${vo.stock_in}</th>
							<th>${vo.stock_out}</th>	
							<th><input type = "checkbox" name = "delList" value = "${vo.stock_no}"></th>													
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
		<br>
			<c:if test="${!empty list}"> 
			<span><input type="button" value="삭제" name = "delete" onclick ="deleteStock();" class="del"></span>
			</c:if>
		<br>
		
		<!-- 페이징 처리 한페이지에 50개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 100 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 50 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 50 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 100 }">[다음]</a>
		</c:if>	
	</div>















