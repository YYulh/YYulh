<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<body>
	<div align = "center">
		<div>
			${vo.mb_name}회원 님은 ${vo.grade_name} 등급입니다
		</div>
		<div>
	    	<span>가용적립금 : ${vo.mb_point}</span>
			<span>쿠폰 : ${cr_cnt}</span>
		</div>
	</div>
	
	<br>
	
	<div align="center">
		<div>
			<!-- 주문완료 체크 이미지 -->
		</div>
		<div>
			<h2>고객님의 주문이 완료 되었습니다.</h2>
			<p>주문내역 및 배송에 관한 안내는 <a href="${ pageContext.request.contextPath }/member/mypage/orderStatus">주문조회</a>를 통하여 확인 가능합니다.</p>
		</div>
	
    </div>
    
    <div>	<!-- 배송지 정보 -->
    
    
	
			
    
    
    </div>


</body>
<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>