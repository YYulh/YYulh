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
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" class="selectBoard">Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" >FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>
	
<div align="center">
	<h3>자주 묻는 질문</h3>
	<br><br><br>
	<form action="#" method="GET" id = "w" name = "write" >

		<table class = "boardTable">
			<tr>
				<th width="60">제목</th>
				<th>${vo.getBo_name()} &nbsp; ${vo.getQna_title()}</th>
				<th>조회수 </th>
				<th>${vo.getQna_view()}</th>
			</tr>
			<tr>
				<th>작성자</th>
				<th>${vo.getMb_name()}</th>
				<th>작성날짜 </th>
				<th>${vo.getQna_date()}</th>
			</tr>
			<tr>
				<td colspan="4" id="content">
					${vo.getQna_content()}
				</td>
			</tr>		
		</table>
	<div align = "center">
		<input type = "button" value = "목록" onclick="location.href='${pageContext.request.contextPath }/member/board/qnaForm'">
	<c:if test="${login.getMb_id()==vo.getMb_id()}"> 
		<input type = "button" value = "수정" onclick="location.href='${pageContext.request.contextPath }/member/board/qnaUpdateForm?no=${vo.getQna_no()}&board_no=2'">
		<input type = "button" value = "삭제" onclick="location.href='${pageContext.request.contextPath }/member/board/qnaDelete?no=${vo.getQna_no()}'">
	</c:if>
	<c:if test="${login.getMb_id()!=vo.getMb_id()}">
		<input type = "button" value = "답글쓰기" onclick="location.href='${pageContext.request.contextPath}/member/board/qnaAnswerForm?seq=${vo.getQna_no()}&board_no=2'">
	</c:if>
	</div>
	</form>

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
















