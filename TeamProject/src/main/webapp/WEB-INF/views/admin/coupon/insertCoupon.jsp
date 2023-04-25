<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
.grade{
width:90px;
}
#submit_btn{
margin-left:300px;
}
#section{
margin-top:100px;
}
</style>



<div align = "center" id = "section">
	<br>
	<h3>쿠폰 등록</h3>
	<br>
	
	
	
	<form action = "${pageContext.request.contextPath}/admin/coupon/insertCoupon" id="list" name = "list"  method="GET" >
		<table>
		
			<tr>
				<th>쿠폰명 &nbsp;&nbsp;:&nbsp;&nbsp; <input type = "text" name = "cpn_name" id="name" placeholder = "쿠폰명 " size="25"></th>								
			</tr>
			<tr>
				<th>사용가능 최소등급 : 
					<select id="grade" name ="grade_no">
						<c:forEach var = "grade" items = "${grade}">
							<option value = "${grade.grade_no }">${grade.grade_name }</option>
						</c:forEach>
					</select>
					</th>
			</tr>
	 
			<tr align="center">
				<th >
					할인율<input type = "radio"   id = "per">
					할인금액<input type = "radio"  id = "price">	
				</th>
			</tr>
			<tr align="center">
				<th>
					<input type = "number" name = "cpn_per" placeholder = " % 생략" id="perInput">
					<input type = "number" name = "cpn_price" placeholder = " , 생략" id="priceInput">
				</th>
			</tr>
			
			
			
		</table>
		<br>	
		<input type = "submit" value="추가"  id = "submit_btn">		 
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#priceInput').hide();
		
		$('#price').click(function(){
			$('#perInput').hide();
			$('#priceInput').show();
			$('#perInput').attr("value",0);
		})
		
		$('#per').click(function(){
			$('#perInput').show();
			$('#priceInput').hide();
			$('#priceInput').attr("value",0);
		})
		
		$('#submit_btn').click(function(){
			if($('#name').val()==''){
				alert("쿠폰명을 입력해주십시오");
				return false;
			}else if($('#perInput').val() =='' || $('#priceInput').val()==''){
				alert("할인율/금액을 입력해주십시오");
				return false;
			}else{
				$('#list').submit();
				return true;
			}
			
			
			
			
			
			
			
		})
		
		
	})
		
	
</script>
