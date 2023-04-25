<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>

tr,th{
border-bottom: 1px solid #dfdfdf;
}
.form{
width:396px;
}
#section{
margin-top:100px;
}
</style>
<script>

	function addInput(){
		var cnt = $('.no').length+1;
		console.log(cnt);
		$('.inputList').append('<tr class="gradeList"><th><input type = "text"  name ="name" size ="9" class="name"></th><th><input type = "text" name ="price" size ="9"  class ="price"></th><th><input type = "text"  name = "per" size ="9" class="per"></th><th><input type = "checkbox" name = "delList" value = "${vo.grade_no}"></th><th><input type = "hidden" name = "no" value = "' + cnt + '"  class = "no"></th></tr>');
	};
	
	
		
	function deleteGrade(){
			
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if (!confirm("해당 등급에 속한 회원이 있을경우 삭제가 불가합니다. 해당 등급을 삭제하시겠습니까?")){
			  return false;		
		}else{
		$('#list').attr("action","${pageContext.request.contextPath}/admin/member/deleteGrade");
			document.list.submit();								
		}
	};
	
	function deleteInput(){

 		$('.gradeList').last().remove();
	};
	
	function updateGrade(){
		var cnt = $('.no').length;
		
			for(var i = 0; i < cnt; i++){
				
		if($('.name').eq(i).val()==''){
			alert("등급 명을 입력해주십시오");	
			return false;
		} else if($('.price').eq(i).val()==''){
			alert("조건 금액을 입력해주십시오");
			return false;
		} else if($('.per').eq(i).val()==''){
			alert("적립율을 입력해주십시오");
			return false;
			}
		} 
			document.list.submit();
			return true;
		}
	
</script>
<div align="center" id="section">
	<h3>회원 등급 관리</h3>
<br>
<form action = "${pageContext.request.contextPath}/admin/member/insertGrade" name = "list" class="form" id ="list">
	<table class="inputList" style = "word-break: break-all">
		<tr>
			<th colspan = "4">현재 관리 중인 등급</th>
		</tr>
		<tr>
			<th>등급명</th>
			<th>조건금액</th>
			<th>적립율</th>
			<th>체크V</th>
		</tr>
		
	<c:forEach var="vo" items = "${list}">
			<input type = "hidden" name = "no" value = "${vo.grade_no}" class="no">
		<tr class="gradeList">
			<th><input type = "text" value="${vo.grade_name }" name ="name" size ="9" class="name"></th>
			<th><input type = "text" value="${vo.grade_price }" name ="price" size ="9" class="price"></th>
			<th><input type = "text" value="${vo.grade_per }" name = "per" size ="9" class="per"></th>
			<th align="center"><input type = "checkbox" name = "delList" value = "${vo.grade_no}" ></th>
		</tr>
	</c:forEach>	
</table>
</form>
		<div align = "center">
			<span><input type="button" onclick ="addInput();" value = "+"></span>
			<span ><input type="button" onclick ="updateGrade();" value = "저장"></span>
			<span><input type="button" onclick ="deleteGrade();" value = "삭제"></span>
			<span ><input type="button" onclick ="deleteInput();" value = "-"></span>
		</div>
		
















</div>