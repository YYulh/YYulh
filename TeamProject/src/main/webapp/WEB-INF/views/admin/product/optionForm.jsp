<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
.section{
margin-top:100px;
}
h4{
font-weight:bolder;
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
		$('#optionForm').attr("action","${pageContext.request.contextPath}/admin/product/deleteOption");
		document.optionForm.submit();
	}
	return true;
	}
	
	
</script>


<div align ="center" class="section">
	<h3>옵션 관리</h3>
	<br>
	<br>
	<h4>선택 제품 명 : [${vo.getProd_name()}]</h4>
	<br><br>
		<form action="${pageContext.request.contextPath }/admin/product/insertOption" name ="optionForm" id = "optionForm" method="POST">
			<input type = "hidden" value = "${vo.getProd_no()}"  name ="prod_no">
			<table>				
				<tr>
					<th></th>
				</tr>	
				<tr>
					<th colspan = "3"><input type="checkbox" id="addOption">옵션 추가</th>				
				</tr>	
				<tr>
					<th colspan = "3"> -------------------------------------------------------------------------------------------------</th>
				</tr>
				
				<tr class="addForm">				 
					<th>옵션 명 &nbsp;&nbsp;&nbsp;<input type="text" name = "option_name" placeholder="옵션명" style=width:300px></th>
				</tr>
				<tr class="addForm"> 
					<th>가격 설정&nbsp;<input type="text" name = "option_price" placeholder="가격설정" style=width:300px></th>
				</tr>
				<tr class="addForm">
					<th colspan = "3"> -------------------------------------------------------------------------------------------------</th>
				</tr>
				<tr> 
					<th colspan = "2">보유 옵션<select>
					<option value="" selected>-[필수] 옵션을 선택해주세요-</option>
					
					<c:forEach var = "po" items="${po}">
						<option value = "">${po.getPo_name()}</option>
					</c:forEach>
					
					</select></th>
				</tr>
				<tr>
					<th colspan = "3"> -------------------------------------------------------------------------------------------------</th>
				</tr>
				<tr>
					<th>옵션명</th>
					<th>옵션 가격</th>
				</tr>
				<c:forEach var = "po" items="${po}">
				<tr>
					<th style=width:350px>${po.getPo_name()}</th>
					<th>${po.getPo_price()}</th>
					<th><input type="checkbox" name = "delList" value = "${po.getPo_no()}"></th>
				</tr>
				</c:forEach>
			</table>
			<br>
			<div>
			<input type = "submit" value = "저장" >
			<input type = "button"  value = "삭제" onclick="deleteProduct();" >
			</div>
		</form>
	
</div>

<script>
	$(document).ready(function(){
		
		$('.addForm').hide();
		
		
		$('#addOption').click(function(){
			if($("input:checkbox[id='addOption']:checked").length > 0){
				$('.addForm').show();
			}
		})
		
		$('#addOption').click(function(){
			if($("input:checkbox[id='addOption']:checked").length < 1){
				$('.addForm').hide();
			}
		})
		
 
	})
</script>

	
