<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>

.container{
padding-top: 100px;
}

.intro{
padding-bottom: 50px;
font-size: 20px;
font-weight: bold;
}

tr{
border:1px solid black;
}

th, td{
padding: 10px;
}



table{
	width: 1000px;
}

.del{
	margin-left:400px;
}

.hidden {
	display: none;
}

/* #btn {
     font-size: 16px;
     width: 80px;
     height: 35px;
     color: white;
     background-color: rgb(54, 104, 168);
     display: block;
     margin: auto;
} */
        
 .longText {
     width: 500px;

}


    
</style>

<script>


	function chk(){
		
		let cpn_price = document.getElementById("cpn_price");
		let disc = document.getElementById("disc");
		let total_disc = document.getElementById("total_disc");
		let final_price = document.getElementById("final_price");
		let prod_price = document.getElementById("prod_price");
		let pointSum = document.getElementById("pointSum");
		let total_disc_paid = document.getElementById("total_disc_paid");
		
		
		total_disc.value = Number(cpn_price.value) + Number(disc.value);
		
		total_disc_paid.value = Number(total_disc.value)  + Number(pointSum.value);

		
		final_price.value = Number(prod_price.value) - Number(total_disc_paid.value);


		console.log(final_price.value);
		
		
		let pmtOptions = $("input[name=pay_method]");

		let payBnk = $("input[name=pay_bnk]");
    	let payName = $("input[name=pay_name]");
    	let payAcc = $("input[name=pay_acc]");
    	
    	let cardName = $("input[name=card_name]");
    	let payPeriod = $("input[name=pay_period]");
    	
    	
		
		if($("input:checkbox[name='addrchk']:checked").length<1) {
			alert("주소를 확인해주세요!");
		} else {
			if(pmtOptions.eq(0).is(':checked')){
				console.log("f1");
  			  if(payBnk.val().trim() == ""){
  					alert("은행명을 확인해주세요!");
  					payBnk.focus();
  				}else if(payName.val().trim() == ""){
  					alert("입금자 명을 확인해주세요!");
  					payName.focus();
  				}else if(payAcc.val().trim() == ""){
  					alert("계좌번호를 확인해주세요!")
  					payAcc.focus();
  				}else{
  					
  					 document.list.submit(); 
  				}	
  		  } else if(pmtOptions.eq(1).is(':checked')){
  			  console.log("f2");
  			 if(cardName.val() == ""){
  				alert("카드이름을 확인해주세요");
  			 } else if(payPeriod.val().trim() == ""){
  				alert("할부개월을 확인해주세요!");
  			 } else{
  			
  				document.list.submit(); 
  			}
  	   }
	}
	}

	

	function deleteProduct(){
				
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if (!confirm("해당 상품을 삭제하시겠습니까?")){
			  return false;
		}else{
			
			$('#list').attr("action","${ pageContext.request.contextPath }/member/order/delete");
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
	
	function getCouponList(){
		window.open("orderCouponList","_blank","width=800,height=600");
	}
	
	function getShipAddr(){
		window.open("orderShipAddr","_blank","width=800, height=600");
	}
	

	
	$(document).ready(function(){
		let table = $(".inner_table");
		let options = $("input[name=addrOption]");
		
		$("#addr").find("input").click(function(){
			if((options.eq(0)).is(':checked')){
				table.eq(0).removeClass("hidden");
				table.eq(1).addClass("hidden");
			}else if((options.eq(1)).is(':checked')){
				table.eq(0).addClass("hidden");
				table.eq(1).removeClass("hidden");
			}
		});
	    
		
		let pointSum = $("#pointSum");
		let total_disc_paid = $("#total_disc_paid");
		let total_disc = $("#total_disc");
		let prod_price = $("#prod_price");
		let final_price = $("#final_price");
		$("#usepoint").change(function(){
			console.log(pointSum);
			pointSum.val($(this).val());
			total_disc_paid.val(parseInt($(this).val()) + parseInt($("#total_disc").val()));
			final_price.val(parseInt(prod_price.val()) - parseInt(total_disc_paid.val()));
			
		});
		

		
		
		let pmtTable = $(".inner_pmt");
    	let pmtOptions = $("input[name=pay_method]");

    	$("#payment").find("input").click(function(){
	       if((pmtOptions.eq(0)).is(':checked')){
	       	pmtTable.eq(0).removeClass("hidden");
	       	pmtTable.eq(1).addClass("hidden");
	       	
	       }else if((pmtOptions.eq(1)).is(':checked')){
	       	pmtTable.eq(0).addClass("hidden");
	       	pmtTable.eq(1).removeClass("hidden");
	       	
	       }
	
	   	})
	
		
		
	});
	

	function getCouponPrice(){
		
		let cpn_price = document.getElementById("cpn_price");
		let disc = document.getElementById("disc");
		let total_disc = document.getElementById("total_disc");
		let final_price = document.getElementById("final_price");
		let prod_price = document.getElementById("prod_price");
		let pointSum = document.getElementById("pointSum");
		let total_disc_paid = document.getElementById("total_disc_paid");
		
		
		total_disc.value = Number(cpn_price.value) + Number(disc.value);
		
		total_disc_paid.value = Number(total_disc.value)  + Number(pointSum.value);

		
		final_price.value = Number(prod_price.value) - Number(total_disc_paid.value);

		let cpn_no = document.getElementById("cpn_no");
		console.log(cpn_no.value + "cpn_no");
		let cr_no = document.getElementById("cr_no");
		console.log(cr_no.value + "cr_no");
	}
	
</script> 


<body >

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
    
<form action="${ pageContext.request.contextPath }/member/order/insertOrder" method="post" name="list" id="list">
        <div align="center"> 
        <input type="hidden" name="grade_no" value="${vo.grade_no}">
        <table class="align-middle">
                <thead>
                    <tr align="center">
                        <th><input type="checkbox" value="selectAll" onclick="selectAll(this)"></th>
                        <th>이미지</th>
                        <th>상품정보</th>
                        <th>판매가</th>
                        <th>수량</th>
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
                                <td align="left"><input type="checkbox" name="delList" value="${vo.cart_no}"></td>							
                              	<td><img src="${pageContext.request.contextPath }/resources/upload/${vo.pp_filename}" width="100"></td>
                                <td>${vo.prod_name}</td>
                                <td>${vo.prod_price}</td>
                                <td>${vo.cart_cnt}
                                <input type="hidden" name="ord_cnt" value="${vo.cart_cnt}">
                                <input type="hidden" name="prod_no" value="${vo.prod_no}">
                                <input type="hidden" name="mb_no" value="${vo.mb_no}">
                                <input type="hidden" name="prod_price" value="${vo.prod_price}">
                                <input type="hidden" name="prod_sellprice" id="prod_sellprice" value="${vo.prod_sellprice }">
                                <c:set var="disc" value="${vo.prod_price - vo.prod_sellprice}" />
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
                		<td colspan="6" align="right">
                			   <c:if test="${!empty list }">
                    
                        <span>상품구매금액 : </span>
                        
                        <span id="prodSum">
                        (${sumCart})
                        </span>
                        <span>
                         + 배송비 (${fee}) = 
                         <input type="hidden" value="${fee}" name="ord_fee">
                        </span>
                        <span>
                        총 합계 :
                        </span>
                        <span id="totalSum">
                        ${total}
                        </span>
                    
                    </c:if>
                		</td>
                		<td colspan="1" align="right">
                			<input type="button" class="btn btn-secondary" name="delete" onclick="deleteProduct()" class="del" value="선택상품 삭제하기">
                		</td>
                	</tr>
                </tbody>
                </table>
  <%-- 
                <div>
                    <div>
                        <input type="button" class="btn btn-secondary" name="delete" onclick="deleteProduct()" class="del" value="선택상품 삭제하기">
                    </div>
                    <div>
                        <a href="${ pageContext.request.contextPath }/member/order/cart/">이전페이지</a>
                    </div>
                </div>
        --%>
    </div>
    
    <hr>



    <!-- 주문 정보 -->
    <div align = "center">
        
        <div>   
            <h5>주문 정보</h5>
    <!--         <div>*필수입력사항</div> -->
        </div>

        <div><!--주문 정보 테이블-->
            <table>
                <tr>
                    <th>
                        주문하시는 분
                    </th>
                    <td>
                        <input type="text" value="${vo.mb_name}">
                    </td>
                </tr>

                <tr>
                    <th>
                        휴대전화
                    </th>
                    <td>
                        <select>
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                        </select>
                        -
                        <input type="text" maxlength="4" value=${vo.mb_tel2 }>
                        -
                        <input type="text" maxlength="4" value=${vo.mb_tel3 }>
                    </td>
                </tr>

                <tr>
                    <th>
                        이메일
                    </th>
                    <td>
                        <input type="text" value="${vo.mb_email1}">@
                        <input type="text" value="${vo.mb_email2}"><br>
                        - 이메일을 통해 주문처리과정을 보내드립니다.
                        - 이메일 주소란에는 반드시 수신가능한 이메일주소를 입력해 주세요
                    </td>
                </tr>
            </table>
        </div>
    </div>
	
	<br>
    <!-- 배송 정보 -->
    <div align = "center">
        
        <div>   
            <h5>배송 정보</h5>
       <!--      <div>*필수입력사항</div> -->
        </div>

        <div><!--배송 정보 테이블-->
        	<div id="addr">
        		<table>
        		<tr>
                    <th>
                        배송지 선택
                    </th>
                    <td>
                        <input type="radio" name="addrOption" value="주문자 정보와 동일" checked>주문자 정보와 동일 
                        <input type="radio" name="addrOption" value="새로운 배송지">새로운 배송지
                        <input type="button" value="주소록 보기" onclick="getShipAddr()" class="btn btn-light">
                        <input type="hidden" name="shipaddr_no"  id="shipaddr_no" value="">
                    </td>
                </tr>
        		</table>
        	</div>

        <!-- 주문자 정보와 동일 -->
			
				 <table class="inner_table">
             
                <tr>
                    <th>
                        받으시는 분
                    </th>
                    <td>
                        <input type="text" name="ord_receiver" value="${vo.mb_name}">
                    </td>
                </tr>
                <tr>
                    <th>
                        주소
                    </th>
                    <td>
                        <input type="text" value="${vo.mb_post }" name="ord_post"><!-- 우편번호 --><br>
                        <input type="text" value="${vo.mb_detailaddr}" name="ord_addr"><!-- 주소 -->기본주소<br>
                        <input type="text">나머지주소(선택입력가능)<br>
                    </td>
                </tr>
                <tr>
                    <th>
                        휴대전화
                    </th>
                    <td>
                        <select name="ord_tel1">
                            <option value="010" selected>010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                        </select>
                        <!--   <input type="hidden" value="010" name="ord_tel1"> -->
                        -
                        <input type="text" maxlength="4" value="${vo.mb_tel2}" name="ord_tel2">
                        -
                        <input type="text" maxlength="4" value="${vo.mb_tel3}" name="ord_tel3">
                    </td>
                </tr>
            </table>
			
            
            <!-- 새로운 배송 -->
            
            <table class="inner_table hidden">
                <tr>
                    <th>
                        받으시는 분
                    </th>
                    <td>
                        <input type="text" name="ord_receiver">
                    </td>
                </tr>
                <tr>
                    <th>
                        주소
                    </th>
                    <td>
                        <input type="text" name="ord_post"><!-- 우편번호 --><br>
                        <input type="text" name="ord_addr"><!-- 주소 -->기본주소<br>
                        <input type="text">나머지주소(선택입력가능)<br>
                    </td>
                </tr>
                <tr>
                    <th>
                        휴대전화
                    </th>
                    <td>
                        <select name="ord_tel2">
                            <option value="010" selected>010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                        </select>
                        -
                        <input type="text" maxlength="4"  name="ord_tel2">
                        -
                        <input type="text" maxlength="4"  name="ord_tel3">
                    </td>
                </tr>
            </table>
            
            <!-- 주소록 -->
            
             <table class="hidden">
                <tr>
                    <th>
                        받으시는 분
                    </th>
                    <td>
                        <input type="text" name="ord_receiver" id="ship_personname">
                    </td>
                </tr>
                <tr>
                    <th>
                        주소
                    </th>
                    <td>
                        <input type="text" name="ord_post"><!-- 우편번호 --><br>
                        <input type="text" name="ord_addr" id="ship_addr"><!-- 주소 -->기본주소<br>
                        <input type="text">나머지주소(선택입력가능)<br>
                    </td>
                </tr>
                <tr>
                    <th>
                        휴대전화
                    </th>
                    <td>
                        <select name="ord_tel1" id="ship_tel1">
                            <option value="010">010</option>
                            <option value="011">011</option>
                            <option value="016">016</option>
                            <option value="017">017</option>
                            <option value="018">018</option>
                            <option value="019">019</option>
                        </select>
                        -
                        <input type="text" maxlength="4"  name="ord_tel2" id="ship_tel2">
                        -
                        <input type="text" maxlength="4"  name="ord_tel3" id="ship_tel3">
                    </td>
                </tr>
            </table>
            
            <div>
            	<table>
            		<tr>
            		<th>배송메세지</th>
            		<td>
            		<input type="text" name="ord_msg" class="longText">
            		</td>
            		</tr>
            	</table>
            </div>
        </div>
    </div>
	
	<br>
    <div align = "center"><!-- 추가 정보 -->
        <div>   
            <h5>추가 정보</h5>
        </div>

        <div>
            <table>
                <tr>
                    <th> 주소확인 </th>
                    <td>
                        <input type="checkbox" name="addrchk">상세주소(동, 호수)를 제대로 기재하셨나요?
                        (주소가 올바르지 않을시에는 반송위험이 있습니다.
                        *대량 구매 시 출고가 불가능할 수 있습니다.)
                    </td>
                </tr>
            </table>
        </div>
    </div>

	<br>
    <div align = "center"><!-- 결제 예정 금액 -->
        <h5>
            결제 예정 금액
        </h5>
        <div>
            <table>
                <tr>
                    <th>
                        총 주문 금액 
                    </th>
                    <th>
                        총 할인 + 부가결제 금액
                    </th>
                    <th>
                        총 결제예정 금액
                    </th>
                </tr>
                <tr>
                    <td>
                    	<input type="text" id="prod_price" value="${total}" readonly style="border: none; background: transparent;">
             
                    </td>
                    <td>
                    	<input type="text" id="total_disc_paid" value="${disc}" readonly style="border: none; background: transparent;">
                    </td>
                    <td>
                 
     
                        <input type="text" id="final_price" value="${total - disc}" name="ord_price" readonly style="border: none; background: transparent;">
                    </td>
                </tr>
            </table>
        </div>
        <div>
            <table>
                <tr>
                    <th><strong>총 할인금액</strong></th>
                    <td>
                    <input type="text" id="total_disc" value="${disc}" readonly style="border: none; background: transparent;"></td>
                </tr>
                <tr>
                    <th>
                        쿠폰할인
                       
                    </th>
                    <td>
                    	 <input type="button" name="couponList" value="쿠폰선택" onclick="getCouponList()" class="btn btn-light">
                        <input type="text" name="cpn_price" id="cpn_price" value="" readonly>
                        <input type="hidden" name="cpn_no" id="cpn_no" value="">
                        <input type="hidden" name="cr_no" id="cr_no" value="">
                        <input type="button" value="적용하기" onclick="getCouponPrice()" class="btn btn-primary">
                    </td>
                </tr>
              <tr>
                    <th>
                        추가할인금액
                    </th>
                    <td>
                    <input type="text" id=disc value="${disc}" readonly style="border: none; background: transparent;"></td>
                </tr> 
                <tr> 
                    <th>
                        <strong>총 부가결제금액</strong>
                    </th>
                    <td>
                    	<input type="text" id="pointSum" value="0" readonly style="border: none; background: transparent;">
                    </td>
                </tr>
                <tr>
                    <th>
                        적립금
                    </th>
                    <td>
                        <input type="number" min="0" max="${vo.mb_point}" name="ord_usepoint" id="usepoint" value="0">원
                        	(총 사용가능 적립금 : ${vo.mb_point} 원)<br>
                       <!--  - 적립금은 최소 2,000 이상일 때 결제가 가능합니다. <br>
                        - 1회 구매시 적립금 최대 사용금액은 0원입니다. <br>
                        - 적립금으로만 결제할 경우, 결제금액이 0으로 보여지는 것은 정상이며 [결제하기] 버튼을 누르면 주문이 완료됩니다. -->
                    </td>
                </tr>
            </table>
        </div>
    </div>
    

    <div id="payment" align="center">
        <div id="pay_method">
        <table>
        <tr>
        	<th>
        		결제수단
        	</th>
        	<td>
        	<input type="radio" name="pay_method" value="계좌이체" checked>계좌이체
            <input type="radio" name="pay_method" value="카드결제">카드결제
        	</td>
        </tr>
         
        </table>
        </div>
      
            <table class="inner_pmt">
                <tr>
                    <th>은행명</th>
                    <td><input type="text" name="pay_bnk" value=""></td>
                </tr>
                <tr>
                    <th>예금주명</th>
                    <td><input type="text" name="pay_name" value=""></td>
                </tr>
                 <tr>
                    <th>계좌번호</th>
                    <td><input type="text" name="pay_acc" value=""></td>
                </tr>
            </table>
    
      
            <table class="inner_pmt hidden">
                <tr>
                    <th>카드선택</th>
                    <td>
                    	<select name="card_name">
                            <option value="신한카드">신한카드</option>
                            <option value="비씨카드">비씨카드</option>
                            <option value="우리카드">우리카드</option>
                            <option value="KB국민카드">KB국민카드</option>
                            <option value="롯데카드">롯데카드</option>
                            <option value="현대카드">현대카드</option>
                            <option value="삼성카드">삼성카드</option>
                            <option value="하나카드">NH하나카드</option>
                            <option value="카카오뱅크 ">카카오뱅크</option>
                            <option value="토스카드">토스카드</option>
                        </select>

        </td>
                </tr>
                <tr>
                    <th>할부기간</th>
                    <td><input type="text" name="pay_period"></td>
                </tr>
            </table>
        
        <br>
        <input type="button"class="btn btn-primary" onclick="chk()" id="btn" value="결제">
    </div>
    
    

</form>
</div>
</body>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>