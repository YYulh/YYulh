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

</script> 

<div align = "center" id = "section">
	 
	<h3>쿠폰 전송</h3>
	<br><br>
	<h4>본 쿠폰에 해당되는 유저 목록</h4>
	<form action = "${pageContext.request.contextPath}/admin/coupon/insertUser"  method = "get" name = "list" id="list">
		<input type="hidden" value = "${cpn_no }" name ="cpn_no">
		<table>
		<tr align = "center">
			<th>회원번호</th>
			<th>ID</th>
			<th>이름</th>
			<th>회원등급</th>
			<th>총 사용금액</th>
			<th>적립금</th>
			<th><input type ="checkbox" name = "all" id="all_checkbox"></th>
		</tr>
		
		<c:choose> 
		<c:when test ="${empty list}">
			<tr >
				<th>사용조건에 해당되는 유저가 없습니다.</th>	
			</tr>
		</c:when>
		<c:otherwise>
		<c:forEach var = "list" items="${list}">
			<tr align = "center">
				<th>${list.mb_no }</th>
				<th>${list.mb_id}</th>
				<th>${list.mb_name }</th>
				<th>${list.grade_name }</th>
				<th>${list.mb_tp}</th>
				<th>${list.mb_point}</th>
				<th><input type = "checkbox" name = "sendList" class="sendList" value = "${list.mb_no}"></th>
			</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		
		</table>
		<br>
		<div align = "center" id = "btn_box">
			<c:if test="${!empty list}"> 
			<span><input type="button" value="전송" name = "send" id="send"></span>
			</c:if>
		</div>
	</form>
</div>

<script>
$(document).ready(function(){
	
	$('#all_checkbox').click(function(){
		if($('#all_checkbox').is(":checked")==true){			
			$('.sendList').prop("checked",true);
		}else{
			$('.sendList').prop("checked",false);
		}
	})
	
	$('#send').click(function(){
		if($("input[name='sendList']:checked").length ==0){
			alert("선택된 유저가 없습니다");
			return false;
		}else if(confirm("쿠폰을 전송하시겠습니까?")){
			$('#list').submit();
			return true;
		}
		
	})
	
	
	
	
	
	
	
	
	
})
</script>










