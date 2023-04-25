<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
tr,th{
border-bottom: 1px solid #dfdfdf;
}
</style>
<script>
	function checkInsert(){
		
		if(document.insert.stock_in.value == ""){
			alert("입고량을 입력해주십시오");
		}else{
			document.insert.submit();
		}
	}
	
		
	
</script>

<div align = "center">
	<br>
	<h3>재고 등록</h3>
	<br>
	<form action = "${pageContext.request.contextPath}/admin/stock/insert" name = "insert">
		<input type ="hidden" name = "prod_no" value = "${vo.prod_no}">
		<table>
			
			<tr>
				<th>제품 명</th>
				<th colspan = "5"><input type = "text" name = "name" value ="${vo.prod_name}" readonly></th>
			</tr>
			
			<tr>
				<th>누적판매수량</th>
				<th><input type = "number" name = "stock_sellcnt" value = "${vo.getStock_sellcnt()}" readonly></th>
			</tr>
			<tr>
				<th>총 매출금액</th>
				<th><input type = "number" name = "stock_sell" value ="${vo.getStock_sell()}" readonly></th>
			</tr>
			<tr>
				<th>불량재고량</th>
				<th><input type = "number" name = "stock_bad" value = "${vo.getStock_bad() }" ></th>
			</tr>
			<tr>
				<th>입고량</th>
				<th><input type = "number" name = "stock_in" placeholder = "입고량 입력"></th>
			</tr>
			<tr>
				<th>재고량</th>
				<th><input type = "number" name = "stock_out" value = "${vo.getStock_out() }"></th>
			</tr>
			 <tr>
				<td colspan="4" align="right">
					<input type="button" value="등록" onclick="javascript:checkInsert()">
					<input type="button" value="초기화" onclick="document.insert.reset()">
				</td>
			</tr>		
		</table>			 
	</form>
</div>

