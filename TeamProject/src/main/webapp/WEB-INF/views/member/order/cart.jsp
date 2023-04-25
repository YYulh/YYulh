<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>
tr{
border:1px solid black;
}


.del{
margin-left:400px;
}

.container{
padding-top: 100px;
}


.intro{
padding-bottom: 50px;
font-size: 20px;
font-weight: bold;
}

.trBtn{
align: right;
}

.table{
text-align :center; 

}

a{
color: white;
text-decoration: none;
display: block;
}

}

</style>

<script>

	function deleteProduct(){
				
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if (!confirm("해당 상품을 삭제하시겠습니까?")){
			  return false;
		}else{
			document.list.submit();
		}
		return true;
		}
	
	
	function selectAll(selectAll)  {
		  const checkboxes 
		       = document.getElementsByName('delList');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked;
		  })
		}
	function calcSum(cartCnt, prod_no, prod_price) {
		var pp = prod_no + "-sum";
		var std = document.getElementById(pp);
		std.innerHTML = cartCnt * prod_price;
		
		const prods_sum = document.getElementsByClassName("prod_sum");
		var total = 0;
		for(sum of prods_sum){
			total += parseInt(sum.innerText);
		}
		
		document.getElementById("totalSum").innerHTML = total;
		console.log(total);
		
	}
	
	function updateCart(pn, mn, cc){
		var pn_param = "?prod_no=" + pn;
		var mn_param = "&mb_no=" + mn;
		var input_id = pn +"_cart_cnt_val";
		var cart_cnt_input = document.getElementById(input_id);
		var cc_param = "&cart_cnt=" + cart_cnt_input.value;
		console.log(pn_param);
		console.log(mn_param);
		console.log(cc_param);
	
		$('#list').attr("action","${pageContext.request.contextPath}/member/order/cart/update"+pn_param+mn_param+cc_param);
		document.list.submit();								
		
	}
	
	function deleteAll(){
		
		$('#list').attr("action","${pageContext.request.contextPath}/member/order/cart/deleteAll");
		document.list.submit();								
		
	}
	
	$(document).ready(function(){
		
		$("input:checkbox[name='delList']").click(function(){		
			var cnt = $("input:checkbox[name='delList']").length;
			
			if($("input:checkbox[name='delList']:checked").length == cnt){
				$('#allCheck').prop("checked",true);
			}else{
				$('#allCheck').prop("checked",false);
			}
		})
	});
	
</script> 



<body>
	<div class="container">
	
	<div align = "center" class="intro">
		<div>
			${vo.mb_name}회원 님은 ${vo.grade_name} 등급입니다
		</div>
		<div>
	    	<span>가용적립금 : ${vo.mb_point}</span>
			<span>쿠폰 : ${cr_cnt}</span>
		</div>
	</div>


	<div align="center" class="cart"> 
	<form action="${ pageContext.request.contextPath }/member/order/cart/delete" method="post" name="list" id="list">
	<table class="table table-hover align-middle">
			<thead>
				<tr>
					<th><input type="checkbox" value="selectAll" onclick="selectAll(this)" id = "allCheck"></th>
					<th>이미지</th>
					<th>상품정보</th>
					<th>판매가</th>
					<th>수량</th> <!-- scirpt주기! -->
					<th>배송비</th>
					<th>합계</th>
				</tr>
			</thead>
			<tbody>
			   <c:choose>
					<c:when test="${empty list}">
					<tr align="center">
						<th colspan="7" >
							<span style="font-weight: bold;">장바구니가 비었습니다.</span>
						</th>
					</tr>
					</c:when>
					<c:otherwise>
					<c:forEach var="vo" items="${map.list}" varStatus="status"> 
						<tr align = "center">
							<td align="left"><input type="checkbox" name="delList" value="${vo.cart_no}" class="delList"></td>							
							<td><img src="${pageContext.request.contextPath }/resources/upload/${vo.pp_filename}" width="100"></td>
							<td>${vo.prod_name}</td>
							<td>${vo.prod_price}</td>
							<td>
							<input type="number" id="${vo.prod_no}_cart_cnt_val" style="width:40px" name="cart_cnt" value="${vo.cart_cnt}" min="1" onchange="calcSum(this.value, '${vo.prod_no}', ${vo.prod_price})">
							<input type="button" value="변경" onclick="updateCart('${vo.prod_no}', '${vo.mb_no}','${vo.cart_cnt}')">
							<input type="hidden" name="prod_no" value="${vo.prod_no}">
							<input type="hidden" name="mb_no" value="${vo.mb_no}">
							<input type="hidden" name="prod_price" value="${vo.prod_price}">
							</td>
							<c:if test="${status.index eq 0}">
							<td rowspan="${map.list.size()}">${fee}
						
							</td>
							</c:if>
							
							<td id="${vo.prod_no}-sum" class="prod_sum">${vo.prod_price * vo.cart_cnt}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
					<td colspan="7">
						<c:if test="${!empty list }">
					
						<span>상품구매금액 : </span>
						
						<span id="prodSum">
						(${sumCart})
						</span>
						<span>
						 + 배송비 (${fee}) = 
						</span>
						<span>
						총 합계 :
						</span>
						<span id="totalSum">
						${total}
						</span>
					
					</c:if>
				</td>
			</tr>
			
			<!-- 	<tr>
					<td colspan="5" class="trBtn" align="right">
						<input type="button" class="btn btn-secondary" name="delete" onclick="deleteProduct()" class="del" value="선택상품 삭제하기">
						
					</td>
					<td colspan="2" align="left">
						<input type="button" class="btn btn-secondary" name="delAll" class="delAll" onclick="deleteAll()" value="장바구니 비우기">
					</td>
				</tr> -->
			</tbody>
			</table>
			<div align="right">
				
			
						<input type="button" class="btn btn-secondary" name="delete" onclick="deleteProduct()" class="del" value="선택상품 삭제하기">
			
						<input type="button" class="btn btn-secondary" name="delAll" class="delAll" onclick="deleteAll()" value="장바구니 비우기">
						
						
						
						<c:if test="${not empty list }">
							<button class="btn btn-primary">
						
							<a href="${ pageContext.request.contextPath }/member/order/orderAll">전체상품주문</a>
							</button>
							<!-- <a href="#">선택상품주문</a> -->
						</c:if>			
			
		
			</div>
		
			
			
	</form>
		
		
	</div>
</div>
</body>



<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>