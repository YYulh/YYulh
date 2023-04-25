
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "/WEB-INF/views/layout/userHeader.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
body{
font-size:14px;
font-style: italic;
font-family: "Noto Sans KR",sans-serif;
}
.slide {
	white-space: nowrap;
	font-size: 0;
	padding: 0;
	position: relative;
	margin:0;
	
}
.slidewrap_prod .slide>li {
    display: inline-block;
    transition: all 0.5s;
  	list-style: none;
    width:100%;
}
.slide>li>a {
    display:block;
    width:100%;
}
    

.slide>li>img{
  width:100%;
  object-fit: cover;
  height: auto; 
  display: block;
  min-width:560px;
 }
 .slidewrap_prod{
 text-align:center;
 max-width: 560px; 
 overflow-x: hidden;
 }
 #free>li{
 display:flex;
 border-bottom: 1px solid #e5e5e5;
 align-items:center;
 }
 #detailBox{
 width:48%;
 margin-left:70px;
 margin-right:10px;
 padding-top:100px;
 }
 #infoBox{
 width:48%;
 margin-right:70px;
 margin-left:10px;
 margin-top:0;
 padding-top:100px;
 align-items:left;
 }
 #p{
 margin-top:30px;
 display:flex;
 justify-content:space-around;
 font-weight:bolder;
 }
 
 .toggle1{
 display:none;
 border:1px solid black;
 }
 #contents{
 max-width:1400px;
 display:flex;
 margin:auto;
 }
 #all{
 align:center;
 }
 .prod_content{
  border-bottom: 1px solid #e5e5e5;
 }
 .select_option{
 font-size:14px;
 }
 .product_more{
 border:1px solid black;
 }
 .product_more{
 border: 1px solid #e5e5e5;
 }
 .prod_content{
 font-weight:bolder;
 }
 .title{
 font-weight:bolder;
 }
 .box{
 padding:15px;
 border:1px solid #e5e5e5;
 }
 .sell_amount>input{
 border:0 solid black;
 font-size: 22px;
 font-weight:bolder;

 }
 .sell_amount{
 padding-top:10px;
 font-size:16px;
 border-top: 1px solid #424242;
 display:flex;
 align-items:center;
 justify-content:center;
 }
 .cart_btn{
 margin-left:10px;
 border:1px solid #4d4b48;
 min-width:80px;
 background:none;

 }
 .present_btn{
 
 margin-left:10px;
 border:1px solid #4d4b48;
  min-width:200px;
  background:none;
 }
 .buy_btn{
 margin-left:10px;
 border:1px solid #4d4b48;
 min-width:290px;
 color:white;
 vertical-align:center;
 background-color:black;
 }
 .action{
 display:flex;
 align:center;
 flex-wrap: wrap;
 height:60px;
 }
 .action>div>a,input {
 border:1 solid black;
 height:60px;
 display:block;
 text-decoration:none;
 }
 .under_btn_all{
 height: 45px;
 text-align: center;
 color: #424242;
 border-bottom: 1px solid #dcdcdc;
 font-size: 14px;
 width :100%;
 display:flex;
 margin-top:25px;
 }
 .under_btn_all>input{
 width:23%;
 border:0 solid black;
 height: 52px;
 background:none;
 font-weight:bolder;
 font-size:16px;
 }
 .detail_img_box{
 margin-top:100px;
 margin-left:60px;
 width: 755px;
 }
 .detail_img_box>ul>li{
  list-style:none;
 }
 .under{
 border-top: 1px solid #222222;
 height:30px;
 font-weight:bolder;
 padding:5px;
 }
 #addCnt, #minusCnt,#cnt{
 width:38px;
 height:33px;
 background:none;
 border: 1px solid black;
 margin-right:5px;
 }
 #selectedOption{
 margin-right:5px;
 }
 #detail_box{
 display:flex;
 width:100%;
 }
 #detail_buy_box{
 position:sticky;
 top:0;
 display:block;
 height:600px;
 padding-top:80px;
 margin-left:180px;
 }
 #totalPriceText{
 width:130px;
 }
  #totalPrice{
 width:100px;
 }
 #selectedOption{
 font-size:12px;
width:200px;
 }
 #cart_success{
 position:fixed;
 z-index:3;
 text-align:center;
 border:1px solid black;
 left:43%;
 top:43%;
 background-color:white;
 }
 #cart_button{
 display:flex;
 border-top:1px solid black;
 }
 #cart_button >input{
 height:30px;
 margin:5,5,5,5;

 background:none;
 border:1px solid black;
 
 }
 #cart_header{
 background-color:black;
 color:white;
 }

 #reWrite{
 position:fixed;
 z-index:3;
 text-align:center;
 border:1px solid black;
 left:43%;
 top:43%;
 background-color:white;
 }
#starbox{
display:flex;
}
#starbox>input{
background:none;
border:none;
}
#re_box{
margin-right:100px;
}
#detail_review{
border-bottom: 1px solid #dcdcdc;
padding-bottom:30px;
padding-top:30px;
}
#info_review{
border-top: 1px solid #dcdcdc;
padding-top:30px;
}
#re_btn{
width:100px;
margin-right:300px;
float: right;
}
#re_btn>input{
height:30px;
font-weight:bolder;
background:none;
box-shadow: 0 1px 4px 0 rgb(0 0 0 / 18%);
border-radius: 20px;
border: 1px solid #dcdcdc;
}
#rate_box{
margin-left:300px;
}
</style>
<script>

	function carthide(){
		$('#cart_success').hide();
	}
	
	function checkCart(){
		if($('#totalPrice').val() == 0){
			alert("선택하신 상품이 없습니다.");
			return;
		}
		httpRequest = new XMLHttpRequest();
	
		if(!httpRequest){
			alert("인스턴스 생성 불가!");
			return;
		}
		
		var httpMethod = "GET";
		var httpParam = $('#insertCartForm').serialize();
		var httpURL = "${pageContext.request.contextPath}/member/order/cart/insert?"+ $('#insertCartForm').serialize();
	
		httpRequest.open(httpMethod,httpURL,true);
		
		//callback method 지정
		httpRequest.onreadystatechange = resultCheckNo;
		httpRequest.send();
	
	}
	
	function resultCheckNo(){
		if(httpRequest.readyState == 4 && httpRequest.status == 200){
			
			var data = httpRequest.responseText;
		
			if(data =='이미 동일한 상품(옵션)이 장바구니에 담겨있습니다'){
				if(confirm(data + "\n장바구니로 이동하시겠습니까?")){					
				location.href="${pageContext.request.contextPath }/member/order/cart"
				}
				return;
			}else if(data == '실패'){
				alert(data);
				return;
			}else{				
				$('#cart_success').show();
			}
		}
	}

	//-----------------------------------------------------
	function checkRe(){
		
		httpRequest = new XMLHttpRequest();
	
		if(!httpRequest){
			alert("인스턴스 생성 불가!");
			return;
		}	
		var httpMethod = "GET";
		var httpParam = ${vo.getProd_no()};
		var httpURL = "${pageContext.request.contextPath}/member/board/reWriteForm?prod_no="+  ${vo.getProd_no()};
	
		httpRequest.open(httpMethod,httpURL,true);
		
		//callback method 지정
		httpRequest.onreadystatechange = resultCheckOrder;
		httpRequest.send();
	}
	function resultCheckOrder(){
		if(httpRequest.readyState == 4 && httpRequest.status == 200){
			
			var data = httpRequest.responseText;
		
			if(data =='작성 가능한 글이 없습니다'){
				alert(data);					
				return;
			}else{				
				$('#reWrite').show();			
			}
		}
	}
	

</script>


<div id="all">


<div id="contents">

<div id="detailBox">

<div class="wrapper_prod" align="center">

<!-- 제품 컨텐츠 사진  -->
<div class ="slidewrap_prod">
	<ul class = "slide">
		<c:forEach var = "contentPic" items = "${contentPic}">
			<li><a><img src="${pageContext.request.contextPath }/resources/upload/${contentPic.pp_filename}" width="560px"></a></li>
		</c:forEach>
	</ul>
</div>
</div>
<div id="p">
	<p>구매 금액별 사은품</p><p><a id="toggle1">유의사항 보기</a></p>
</div>
<p class="toggle1">유의사항<br>
- 기재된 사은품 소진 시에는 다른 사은품으로 대체될 수 있습니다.<br>
- 이벤트 기간에는 구매금액별 사은품이 제공되지 않습니다.<br>
- 구매금액별 사은품은 실 상품결제금액으로 책정됩니다. (배송비 제외)<br>
 (실결제금액 기준(쿠폰할인, 회원할인 등 할인수단이 적용된 할인금액))<br>
- 회원 전용 이벤트이므로 비회원 구매 시에는 사은품이 제공되지 않습니다.<br>
  (네이버 페이 주문건은 비회원 주문건입니다.)</p>
  
		<ul id="free">
			<c:forEach var = "free" items = "${freebies}">			
				<li><img src="${pageContext.request.contextPath }/resources/upload/${free.pp_filename}" width="150">	
				${free.prod_name}<br>${free.prod_content}</li>	
			</c:forEach>
		</ul>
	
</div>

<div id="infoBox">
	<h2 class="title">${vo.getProd_name()}</h2>
	<div>★<fmt:formatNumber value="${vo.getProd_rate()}" pattern=".0"/></div>
	<div class="view"> (평점 수)</div>
<br>
	<div class="original_value">${vo.getProd_price()}원</div>
	<div class="disc_rate">${vo.getProd_disc()}%</div>   
	<div class="disc_value">${vo.getProd_sellprice()}원</div>
<br>
	<div class="prod_content">${vo.getProd_content()}</div>
	
	<div>세트선택</div>
	<select class="select_option" >
		<option value="" selected>-[필수] 옵션을 선택해주세요-</option>
		<c:forEach var = "option" items="${option}">					
			<option value ="${option.getPo_price()}">${option.getPo_name()}</option>		
		</c:forEach>
	</select>
	
	<div>추천 상품</div>
	<div class="product_more">
		<table>
		<c:forEach var="more" items="${moreList}">
		
 		<tr> 
 			<th><a href ="${pageContext.request.contextPath}/member/product/contentForm?no=${more.getProd_no()}">
 			<img src="${pageContext.request.contextPath}/resources/upload/${more.getPp_filename()}" width="100px"></a></th>
 			<th>상품명</th>
 				<td>${more.getProd_name()}</td> 
 			<th>판매가</th> 
 				<td>${more.getProd_sellprice()}</td>					
		</tr> 
		 </c:forEach>
		</table>		
	</div>
	<br>
	<div>
		<table>
		<tr> 
			<th>배송비</th>
			<td>3,000원 (50,000원 이상 구매 시 무료)</td>
		</tr>
		<tr> 
			<th>배송 예정일</th>
			<td>평일 16시 이전 결제완료 시 당일출고</td>
		</tr>
		<tr> 
			<th>쇼핑팁</th>
			<td class="모달띄우기">무이자 할부 혜택 보기</td>
		</tr>
		</table>
	</div>
	<br>
	<div class="box">
	지금 회원 가입시 10,000원 쿠폰팩
	</div>
	<br>
	<div class="box">
	카카오톡 채널 추가 시 1천원 쿠폰 증정		
	</div>
	
	
<div id="cart_success">
	<div id="cart_header">
	 <h3>장바구니 담기</h3>
	</div>
	<br><br>
	<div id="cart_content">
	  장바구니에 성공적으로 담겼습니다.
	</div>
	<br><br>
	<div id = "cart_button">
		<input type ="button" value = "쇼핑 계속 하기" onclick="carthide();">
		<input type ="button" value = "장바구니로 이동" onclick = "location.href='${pageContext.request.contextPath }/member/order/cart'">
	</div>
</div>
	
<br>
<form action="${pageContext.request.contextPath}/member/order/insert" id = "insertCartForm" name = "insertCartForm">	

	<div class="sell_amount">
		<input type = "hidden" value = "${vo.getProd_no()}" name = "prod_no" id = "prod_no">
		<input type="text" value ="" id="selectedOption" readonly name="optionName"> 
		<input type = "text" value ="1" id ="cnt" name = "cnt">
		<input type = "button" value ="+" id="addCnt">
		<input type = "button" value ="-" id="minusCnt">
		<input type="text" value ="총 상품금액" id = "totalPriceText">
		<input type="text" readonly name = "price" value="0" id = "totalPrice" name = "totalPrice">
	</div>
<br>
	<div class="action">
		<div><input type = "button" value="CART" class="cart_btn" onclick = "checkCart();"></div>
		<div><a href="#"><input type = "button" value="선물하기" class="present_btn"></a></div>
		<div><a href="#"><input type = "button" value="BUY NOW" class="buy_btn"></a></div>
	</div>
	
</form>


</div>
 </div>
 </div>
 
 
 
 
 <div class="under_btn_all">
 
 	<input type="button" value="제품상세" class="under-btn1">
 	<input type="button" value="제품안내" class="under-btn2">
 	<input type="button" value="구매후기 (${vo.getProd_view()})" class="under-btn3">
 	<input type="button" value="상품Q&A" class="under-btn4">
 </div>
 
 <div id= "detail_box">
 
 <div class="detail_img_box">
 	<ul>
 		<c:forEach var="detailPic" items="${detailPic}">
 			<li><img src="${pageContext.request.contextPath }/resources/upload/${detailPic.pp_filename}" width="700">
 		</c:forEach>
 	</ul>
 		
	 <div class="under">상품 정보 제공 고시</div>
	 <p class="under_info1"></p>
	 
	 <div class="under">배송/반품 안내</div>
	 <p class="under_info2"></p>
 </div>
</div>

<div id = "rate_box" align="center">
<span>리뷰(${viewCnt})</span>
<span>
	<c:choose>
		<c:when test = "${vo.getProd_rate()< 0.5}">
			☆☆☆☆☆
		</c:when>
		<c:when test = "${vo.getProd_rate()>0.4 && vo.getProd_rate()<1.5}">
			★☆☆☆☆
		</c:when>
		<c:when test = "${vo.getProd_rate()>1.4 && vo.getProd_rate()<2.5}">
			★★☆☆☆
		</c:when>
		<c:when test = "${vo.getProd_rate()>2.4 && vo.getProd_rate()<3.5}">
			★★★☆☆
		</c:when>
		<c:when test = "${vo.getProd_rate()>3.4 && vo.getProd_rate()<4.5}">
			★★★★☆
		</c:when>
		<c:when test = "${vo.getProd_rate()>4.4 && vo.getProd_rate()<5.1}">
			★★★★★
		</c:when>
	</c:choose>
</span>
<span><fmt:formatNumber value="${vo.getProd_rate()}" pattern=".0"/></span>
	<c:if test="${!empty login}">
	<span id = "re_btn"><input type="button" value = "리뷰 작성"  onclick = "checkRe();" ></span>
	</c:if>
</div>
<br><br>
<div id = "re_box">
		<div >		
			<c:choose>
				<c:when test="${empty review}"> <!-- 회원목록에 데이터가 존재하지 않으면 -->
					<div align="center">					
							<span style="font-weight: bold;">게시글이 없습니다.</span>					
					</div>
				</c:when>
			<c:otherwise>	
				<c:forEach var = "review" items="${review}">
					<div id="info_review">
						<span>★</span>
						<span>(${review.re_rate})</span>
						<span>${review.mb_name}</span>
						<span>${review.re_date}</span>
						<span>${review.grade_name}</span>	
						<span>${review.prod_name}</span>				
					</div>		
					<div id="detail_review">
						<c:forEach var="rePic" items="${rePic}">
							<c:if test="${rePic.re_no == review.re_no}">
								<c:if test="${rePic.rp_filename != ' '}">
									<span><img src="${pageContext.request.contextPath }/resources/review/${review.re_no}/${rePic.rp_filename}" width="200px"></span>
								</c:if>
							</c:if>
						</c:forEach>
						<span>제목 : ${review.re_title}</span>
						<span>내용 : ${review.re_content}</span>
					</div>	
				</c:forEach>		
			</c:otherwise>
			</c:choose>
		</div>
</div>

<div id ="reWrite">
	<form action = "${pageContext.request.contextPath }/member/board/reWrite" enctype="multipart/form-data" name = "insertReview" id ="insertReview"  method="POST">
			<input type = "hidden" value = ${vo.getProd_no() } name = "prod_no">
			<input type = "hidden" value = "0" name = "seq">;
		<table>
			<tr>
				<th>리뷰 제목</th>
				<th><input type = "text" name = "re_title" placeholder="리뷰 제목"></th>

			</tr>
			<tr>
				<th>평점</th>			
				<td id = "starbox">
				<input type ="button" value = "☆" class = "star1 1_Star">			
				<input type ="button" value = "☆" class = "star2 2_Star">			
				<input type ="button" value = "☆" class = "star3 3_Star">				
				<input type ="button" value = "☆" class = "star4 4_Star">			
				<input type ="button" value = "☆" class = "star5 5_Star"></td>
				
				<td><input type = "hidden" value ="1" id= "re_rate" name = "re_rate"></td>
			</tr>
			<tr>
				<th>작성 내용</th>
				<th><input type = "text" name = "re_content" placeholder="리뷰 제목"></th>
			</tr>
			<tr>
				<td colspan="2" id = "photo">
					<input type="file" name = "photo" size = "400" multiple>
				</td>		
			</tr>
			<tr>
				<th><input type = "submit" value ="작성"></th>
				<th><input type = "button" value ="취소" id = "hideWrite"></th>
			</tr> <!--  넘겨주는 정보 :  회원번호(세션) 제품번호, 제목, 내용, 평점, 사진 +(주문번호는 직접 셀렉트해와야할듯) -->
		</table>
	</form>
</div>
<%@ include file = "/WEB-INF/views/layout/userFooter.jsp" %> 
<script>
$(document).ready(function(){
	

	$('#hideWrite').click(function(){
		$('#reWrite').hide();
	})
	
    $('#reWrite').hide();
	$('#cart_success').hide();
	
    $(function(){
       $("#toggle1").click(function(){
          $(".toggle1").slideToggle();
    });  
}); 
    
    $('#addCnt').click(function(){
    	$('#cnt').attr("value",parseInt($('#cnt').val())+1);
    })
    
     $('#minusCnt').click(function(){
    	 $('#cnt').attr("value",parseInt($('#cnt').val())-1);
    	if($('#cnt').val()<2){
    		 $('#cnt').attr("value","1");
    	}
    })
    
    $('.select_option').change(function(){
    	$('#totalPrice').attr("value",$('.select_option option:selected').val()*$('#cnt').val());    	  	
    })
    
     $('#addCnt').click(function(){
    	$('#totalPrice').attr("value",$('.select_option option:selected').val()*$('#cnt').val());
     })
     $('#minusCnt').click(function(){
    	$('#totalPrice').attr("value",$('.select_option option:selected').val()*$('#cnt').val());
     })
     $('.select_option').change(function(){
    	$('#selectedOption').attr("value",$('.select_option option:selected').text());
    })
    
    
    
    $('.star1').click(function(){
    	 $('.star1').attr("value","★");
    	 $('.star2').attr("value","☆");
    	 $('.star3').attr("value","☆");
    	 $('.star4').attr("value","☆");
    	 $('.star5').attr("value","☆");
    	 $('#re_rate').attr("value",1);
    })
    $('.star2').click(function(){
    	 $('.star1').attr("value","★");
    	 $('.star2').attr("value","★");
    	 $('.star3').attr("value","☆");
    	 $('.star4').attr("value","☆");
    	 $('.star5').attr("value","☆");
    	 $('#re_rate').attr("value",2);
    })
    $('.star3').click(function(){
    	 $('.star1').attr("value","★");
    	 $('.star2').attr("value","★");
    	 $('.star3').attr("value","★");
    	 $('.star4').attr("value","☆");
    	 $('.star5').attr("value","☆");
    	 $('#re_rate').attr("value",3);
    })
    $('.star4').click(function(){
    	 $('.star1').attr("value","★");
    	 $('.star2').attr("value","★");
    	 $('.star3').attr("value","★");
    	 $('.star4').attr("value","★");
    	 $('.star5').attr("value","☆");
    	 $('#re_rate').attr("value",4);
    })
    $('.star5').click(function(){
    	 $('.star1').attr("value","★");
    	 $('.star2').attr("value","★");
    	 $('.star3').attr("value","★");
    	 $('.star4').attr("value","★");
    	 $('.star5').attr("value","★");
    	 $('#re_rate').attr("value",5);
    })
    
   
});
</script>
