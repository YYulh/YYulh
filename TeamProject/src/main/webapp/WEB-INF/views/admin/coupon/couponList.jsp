<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
tr,th{
border-bottom: 1px solid #dfdfdf;
}
#btn_box{
margin-left:400px;
}
#section{
margin-top:150px;
}
</style>

<script>
function deleteStock(){
	
	if ($("input:checkbox[name='delList']:checked").length<1){
		alert("선택된 항목이 없습니다.");
		return false;
	}else if(!confirm("선택하신 항목을 삭제하시겠습니까?")){
		return false;
	}else{
		$('#list').submit();
	return true;
	}
}
</script> 

	<div align = "center" id = "section"> 
	<h3>쿠폰목록 조회</h3>
	<br><br>
	<form action = "${pageContext.request.contextPath}/admin/coupon/deleteCpn"  method = "get" name = "list" id="list">
		<table>
		<tr align = "center">
			<th>쿠폰번호</th>
			<th>쿠폰이름</th>
			<th>조건등급</th>
			<th>할인율</th>
			<th>할인금액</th>
		</tr>
		
		<c:choose> 
		<c:when test ="${empty list}">
			<tr >
				<th>등록된 쿠폰정보가 없습니다.</th>	
			</tr>
		</c:when>
		<c:otherwise>
		<c:forEach var = "list" items="${list}">
			<tr align = "center">
				<th>${list.cpn_no }</th>
				<th><a href="${pageContext.request.contextPath}/admin/coupon/insertUserForm?no=${list.cpn_no}">${list.cpn_name}</a></th>
				<th>${list.grade_name } 이상</th>
				<th>${list.cpn_per}</th>
				<th>${list.cpn_price}</th>
				<th><input type = "checkbox" name = "delList" value = "${list.cpn_no}"></th>
			</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		
		</table>
		<br>
		<div align = "center" id = "btn_box">
<!-- 			<span><input type = "submit" value = "저장"></span>	 -->
			<c:if test="${!empty list}"> 
			<span><input type="button" value="삭제" name = "delete" onclick ="deleteStock();" class="del"></span>
			</c:if>
		</div>
	</form>


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











