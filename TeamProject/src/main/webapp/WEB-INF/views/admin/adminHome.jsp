
<%@ page  pageEncoding="UTF-8"%> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>   

<!-- 배너사진 -->
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


<%@ include file = "/WEB-INF/views/layout/adminFooter.jsp" %> 