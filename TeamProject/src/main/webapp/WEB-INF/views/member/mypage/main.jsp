<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>


.head{

margin-top:50px;
margin-bottom:50px;
text-align: center;

}

a{
color: black;
text-decoration: none;
display: block;
}


</style>
<c:set var="cpath" value="${ pageContext.request.contextPath }"/>
<body>
<div class="container">
    <!-- 정보 -->
    <div class="row head border border-1">
        <div class="head col-4">
            <div>
                ${vo.mb_name}회원 님은
            </div>
            <div>
                ${vo2.grade_name}등급입니다 
            </div>
            
        </div>
        <div class="head col-4">
            <div>
                ${vo2.grade_name}등급 혜택
            </div>
            <div>
                아래 ‘멤버십 등급별 혜택 보기’로 혜택을 확인해 보세요!
                
            </div>
            <div>
            	 <a href="#">멤버십 등급별 혜택 보기</a> <!-- 게시판: membership 페이지로 이동 -->
            </div>  
      
            
        
        </div>
        
      
            <!-- 적립금, 쿠폰 -->
        <div class="head col-4">
                <div>
                    사용가능한 적립금
                    <button type="button" class="btn btn-light btn-sm">
                    <div>
                   
                        <a href="${ pageContext.request.contextPath }/member/mypage/point">${vo.mb_point}</a>
                    
                    </div>
                    </button>
                    <br>
                     사용가능한 쿠폰
                    <button type="button" class="btn btn-light btn-sm">
                    <div>
                      
                        <a href="${ pageContext.request.contextPath }/member/mypage/couponList">${vo3}</a>
                 		
                    </div>
                    </button>
                </div>
    		 </div>
        </div>
        
  <!--  head끝 -->
        
<div class="row"> <!-- 중간시작 -->
        
  <div class="sectionSide col-2"><!-- 왼쪽 사이드 -->   
	<!-- 나의 쇼핑 정보 -->
        
                <h5 align="center">나의 쇼핑 정보</h5>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="${ pageContext.request.contextPath }/member/mypage/orderStatus">주문/배송현황</a>
                        </li>
                        <li class="list-group-item">
                            <a href="#">취소/교환/반품 내역</a>
                        </li>
                        <li class="list-group-item">
                            <a href="${ pageContext.request.contextPath }/member/mypage/shippingAddr">배송지 수정</a>
                        </li>
          				<li class="list-group-item">
          					<a href="${ pageContext.request.contextPath }/member/mypage/infoUpdate">회원정보 수정</a>
          				</li>
                        <li class="list-group-item">
                        	<a href="#">게시물 관리</a>
                        </li>
                        <li class="list-group-item">
                        	<a href="#">문의 내역</a>
                        </li>
                        <li class="list-group-item">
                        	 <a href="#">정기배송 관리</a>
                        </li>
    	 			 </ul>
    </div>          
    
   <div class="sectionCenter col-10">   <!-- 가운데 -->
            
                    <h3 align="center">최근 주문</h3>
                    
                        <table class="table">
                            <tr>
                                <th>주문일자</th>
                                <th>이미지</th>
                                <th>상품정보</th>
                                <th>수량</th>
                                <th>주문번호</th>
                                <th>상품구매금액</th>
                            </tr>
                        <c:choose>
                            <c:when test="${empty list }">
                                <tr>
                                    <td colspan="6" align="center">
                                        <span>주문 내역이 없습니다.</span>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="vo" items="${list }">
                                    <tr>
                                        <!-- <td>${dto.seq }</td>
                                        <td><a href="${ pageContext.request.contextPath }/board/content?seq=${dto.seq }">${dto.title }</a></td>
                                        <td>${dto.id }</td>
                                        <td>${dto.logtime }</td>
                                        <td>${dto.hit }</td> -->
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </table>
  
        </div>
    
</div><!-- 중간 끝 -->
        
<%--  <div class="row">
        
          <!--나의 쇼핑 활동-->
                <div class="col-3">나의 쇼핑 활동<!--title-->
           
                        <div>
                            <a href="${ pageContext.request.contextPath }/member/mypage/infoUpdate">회원정보 수정</a>
                        </div>
                        <div>
                            <a href="#">게시물 관리</a>
                        </div>
                        <div>
                            <a href="#">문의 내역</a>
                        </div>
                        <div>
                            <a href="#">정기배송 관리</a>
                        </div>
                   
                </div>
    
 </div>  <!-- 왼쪽 사이드 종료--> --%>
            
      
</div>


</div>
</body>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>