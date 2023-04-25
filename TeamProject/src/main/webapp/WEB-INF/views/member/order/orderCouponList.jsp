<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<style>
tr{
border:1px solid black;
}

th, td{
padding:10px;
}

table{
width: 1000px;
}

.container{
padding-top:100px;

}

.btn{
padding-top:10px;
}

</style>

<script>

function getCouponNo(){
	var cpn_no_inputs = document.getElementsByName('cpn_no');
	var cpn_price_inputs = document.getElementsByName('cpn_price');
 	var cr_no_inputs = document.getElementsByName('cr_no'); 
	console.log(cpn_price_inputs);
	cpn_no_inputs.forEach(function(cpn_no_input, i) {
		if (cpn_no_input.checked) {
			var parent_cpn_price = opener.document.getElementById("cpn_price");
			var parent_cpn_no = opener.document.getElementById("cpn_no");
		
			opener.document.getElementById('cpn_price').value = cpn_price_inputs[i].value;
			opener.document.getElementById('cpn_no').value = cpn_no_input.value;
			opener.document.getElementById('cr_no').value = cr_no_inputs[i].value;
			 
		}
		
	
	});

}

</script>

<div align="center" class="container">

	<div class="row">
    	<div align="center" class="col-12">
        <h2>쿠폰</h2>
        <p>고객님의 사용가능한 쿠폰 내역입니다.</p>
   	 </div>



	<div class="col-12">
	<form>
		<table class="table">
		
		<thead>
	<tr>
		<th></th>
		<th>쿠폰번호</th>
		<th>쿠폰명</th>
		<th>쿠폰 혜택</th>
		<th>사용가능 기간</th>
	</tr>
	</thead>
	<tbody>
	

		<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="7" align="center">
							<span style="font-weight: bold;">사용 가능한 쿠폰이 없습니다.</span>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list }" varStatus="status">
						<tr>
						
								<c:if test="${vo.cr_isused eq 0 }">
								<td><input type="radio" name="cpn_no" id="cpn_no" value="${vo.cpn_no}"></td>
								<td>${vo.cr_serial }</td>
								<td>${vo.cpn_name }
								<input type="hidden" id="cpn_name" value="${vo.cpn_name}">
								</td>
								<td>${vo.cpn_price }
								<input type="hidden" name="cpn_price" id="cpn_price" value="${vo.cpn_price}">
								<input type="hidden" name="cr_no" id="cr_no" value="${vo.cr_no}">
								</td>
								<td>${vo.cr_date1 } ~ ${vo.cr_date2}</td> 
								</c:if>	

						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				</tbody>
		</table>
		<!-- 페이징 처리 한페이지에 5개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/order/orderCouponList?start=${start - 10 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/order/orderCouponList?start=${start - 5 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/order/orderCouponList?start=${start + 5 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/order/orderCouponList?start=${start + 10 }">[다음]</a>
		</c:if>	
		
		<br>
		<div class="btn">
		<input type="button" class="btn btn-primary" value="선택쿠폰적용" onclick="getCouponNo()" >
		</div>
		
	</form>
	
    </div>	
</div>

</div>

