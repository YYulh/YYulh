<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>

tr,th{
border-bottom: 1px solid #dfdfdf;
}
.del{
margin-left:400px;
}
#section{
margin-top:100px;
}

</style>
<script>

	function deleteProduct(){
				
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if (!confirm("제품을 삭제하시겠습니까?")){
			  return false;
		}else{
			document.list.submit();
		}
		return true;
		}
	
	function checksort(){
				var tr = [];
		$("input:checkbox[name='sort']:checked").each(function(){	
			
			tr.push ($(this).val());	
				
				$('.grade').each(function(){
				var th = $(this).val();
				var name = $('.'+th).text();
				
				if(tr.indexOf($('.'+th).html())>-1){
					$('.'+th+'1').show();
				}	else{
					$('.'+th+'1').hide();					
				}			
			})
		})
				if($("input:checkbox[name='sort']:checked").length == 0){
					$('.list').hide(); //체크된 값이 없다면 모두 hide-
				}
		};	
		
		
</script> 
 
	<div align="center" id="section">
	<br>
	<h3>제품목록 조회</h3><br>
	
	
	<form action="${pageContext.request.contextPath}/admin/product/delete" method ="post" name = "list">
		<table class="memberTable">
			<tr align="center">
				<th>제품번호</th>
				<th>카테고리</th>
				<th>제품명</th>
				<th>가격</th>
				<th>할인율</th>
				<th>판매가격</th>
				<th>평점</th>
				<th>추가구성상품</th>
				<th>체크V</th>
				<th>옵션</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list}"> <!-- 제품목록에 데이터가 존재하지 않으면 -->
					<tr align="center">
						<th colspan="9" >
							<span style="font-weight: bold;">제품정보가 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
						<tr align = "center">
							<th>${vo.prod_no}</th>							
							<th>${vo.cd_list}</th>
							<th><a href="${pageContext.request.contextPath}/admin/product/updateForm?prod_no=${vo.prod_no}">${vo.prod_name}</a></th>
							<th>${vo.prod_price}</th>
							<th>${vo.prod_disc}</th>
							<th>${vo.prod_sellprice}</th>
							<th>${vo.prod_rate}</th>
							<th>${vo.prod_more}</th>					
							<th><input type = "checkbox" name = "delList" value = "${vo.prod_no}"></th>
							<th><a href="${pageContext.request.contextPath}/admin/product/optionForm?no=${vo.prod_no}">
							<input type = "button" name = "option" value = "옵션" ></a></th>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		</form>
		<br>
		
		<c:if test="${!empty list}"> 
			<div><input type="button" value="삭제" name = "delete" onclick ="deleteProduct();" class="del"></div>
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
















