<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file = "/WEB-INF/views/layout/userHeader.jsp"%>
<style>
.list{
display :inline-block;
margin-left:50px;
margin-right:50px;

}
li{
list-style:none;
}
#per{
color:red;
font-weight:bolder;
}
#price{
font-weight:bolder;
}

</style>


<!-- 배너 사진 -->
<div class="wrapper">
	<div class="slidewrap"> 
	<ul class="slide">
		<c:forEach var="banner" items="${bannerList}">
			<li>
				<a href="#">
				<img src="${pageContext.request.contextPath }/resources/adminPic/${banner.ap_filename}"></a> 
			</li>
		</c:forEach>
	</ul>
	</div>
</div>


<div class="prodList" align="center">

<!-- 제품 리스트  -->
  <c:forEach var = "vo" items = "${list}">	
	    <ul class = "list">
	   		<li><a href="${pageContext.request.contextPath}/member/product/contentForm?no=${vo.prod_no}">
	    	<img src="${pageContext.request.contextPath }/resources/upload/${vo.pp_filename}" width="400"></a></li>
	    	<li><a href="${pageContext.request.contextPath}/member/product/contentForm?no=${vo.prod_no}">${vo.prod_name}</a></li>
	     	<li><div id="per">할인율 : ${vo.prod_disc}%</div><div id="price">${vo.prod_price}원 -> ${vo.prod_sellprice}원</div></li>
	     	<li><div>★<fmt:formatNumber value="${vo.prod_rate}" pattern=".0"/>(리뷰수)</div></li>
	    </ul>
  </c:forEach>
</div>
<%@ include file = "/WEB-INF/views/layout/userFooter.jsp" %> 