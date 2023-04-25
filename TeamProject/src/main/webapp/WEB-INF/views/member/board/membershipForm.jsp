<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/layout/userHeader.jsp" %> 
<style>
#top_nav>ul{
list-style:none;
display:flex;
justify-content:center;
width : 60%;
}
#top_nav>ul>li{
margin-top:100px;
width:150px;
}
#top_nav>ul>li>a{
width:150px;
display:block;
text-decoration:none;
color:black;
font-weight:bolder;
}
.title{
width:600px;
align:left;

}
.title>a{
text-decoration:none;
}
tr,th{
border-bottom: 1px solid #dfdfdf;
}
.selectBoard{
border-bottom: 3px solid black;
}
table{
width:1200px;
border-bottom: 1px solid #dfdfdf;
}

margin-left:1000px;
}
</style>

<script>
	
</script>

	
	<div id = top_nav align = "center">
		<ul>
			<li><a>NOTICE</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" >Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" >FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm" class="selectBoard">MEMBERSHIP</a></li>
		</ul>
	</div>
<br><br><br>

		<div align="center">
		
			<c:forEach var="list" items="${list}">
				<img src="${pageContext.request.contextPath }/resources/adminPic/${list.ap_filename}" width="1200">
			</c:forEach>
		
		</div>






















	<%@ include file = "/WEB-INF/views/layout/userFooter.jsp" %> 