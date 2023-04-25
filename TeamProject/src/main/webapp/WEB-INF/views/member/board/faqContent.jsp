<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
#top_nav>ul{
list-style:none;
display:flex;
justify-content:space-around;
width : 40%;
}
table{
border:1px solid black;
}
tr{
border:1px solid black;
}
#content{
min-width:750px;
min-height:450px;
}
.selectBoard{
border-bottom: 3px solid black;
}


</style>

<%@include file="/WEB-INF/views/layout/userHeader.jsp"%>

	<div id = top_nav align = "center">
		<ul>
			<li><a>NOTICE</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" >Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" class="selectBoard">FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>
	
<div align="center">
	<h3>자주 묻는 질문</h3>
	<br><br><br>
	<form action="${ pageContext.request.contextPath }/member/board/write" method="POST" id = "w" name = "write" enctype="multipart/form-data">

		<table class = "boardTable">
			<tr>
				<th width="60">제목</th>
				<th>${vo.getBo_name()} &nbsp; ${vo.getFaq_title()}</th>
				<th>조회수 </th>
				<th>${vo.getFaq_view()}</th>
			</tr>
			<tr>
				<th>작성자</th>
				<th>${vo.getMb_name()}</th>
				<th>작성날짜 </th>
				<th>${vo.getFaq_date()}</th>
			</tr>
			<tr>
				<td colspan="4" id="content">
					${vo.getFaq_content()}
				</td>
			</tr>		
		</table>
	<div align = "center">
		<input type = "button" value = "목록" onclick="location.href='${pageContext.request.contextPath }/member/board/faqForm'">
	<c:if test="${login.getMb_id()=='admin01'}"> 
		<input type = "button" value = "수정" onclick="location.href='${pageContext.request.contextPath }/member/board/faqUpdateForm?no=${vo.getFaq_no()}&board_no=3'">
		<input type = "button" value = "삭제" onclick="location.href='${pageContext.request.contextPath }/member/board/faqDelete?faq_no=${vo.getFaq_no()}'">
	</c:if>
	</div>
	</form>
	
	
	<div> 
	
	<span>첨부파일</span>
	<c:choose>
	<c:when test = "${!empty pic}">
		<c:forEach var = "pic" items = "${pic}">
	<div>
		<a href="${pageContext.request.contextPath }/resources/board/${pic.getFp_filename()}" download >${pic.getFp_filename()}</a>
	</div>
		</c:forEach>
	</c:when>
	<c:otherwise>
	<div>없음</div>
	</c:otherwise>

	</c:choose>
	</div>
			
	
	
	
	
<script type="text/javascript">
	$(document).ready(function() {
 		
		$('.summernote').summernote({
			lang: 'ko-KR',
			height: 400
		});
		
	
	});	
</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</div>
<%@include file="/WEB-INF/views/layout/userFooter.jsp"%>
















