<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>
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
.selectBoard{
border-bottom: 3px solid black;
}
</style>
<script type="text/javascript">

function checkWrite() {
	
	if(document.write.qna_title.value == ""){
		alert("제목을 입력하십시오!");
		return;
	}else if(document.write.qna_content.value == ""){
		alert("내용을 입력하십시오!")
		return;
	}else{
		document.write.submit();
	}

}
</script>

 	<div id = top_nav align = "center">
		<ul>
			<li><a>NOTICE</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" class="selectBoard">Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" >FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>

<div align="center" id ="section">
	<h3>상품 질문</h3>
	<br><br><br>
	<form action="${ pageContext.request.contextPath }/member/board/qnaWrite" method="get" id = "w" name = "write" >
	
	<input type= "hidden" value = "${seq}" name = "seq">
		<table class = "boardTable">
			<tr>
				<th width="60">제목</th>
				<td><input type="text" name = "qna_title" size = "53" value="RE:${vo.getQna_title()}"></td>
			</tr>
			<tr>
				<th>분류</th>
				<th> 
					<select name = "bo_no">
						<c:forEach var="list" items="${list}">
							<option value = "${list.bo_no}">${list.bo_name}</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="summernote" rows="15" cols="65" name="qna_content">
					${vo.getQna_content()}
					</textarea>
				</td>
			</tr>		
		</table>
		
		<div>
			<span>비밀번호</span>
			<span><input type = "text" name = "qna_pw" value="${vo.getQna_pw()}"></span>
		</div>
		<div>
			<span>비밀글 설정</span>
			<span><input type = "radio" name = "qna_isprivate" value ="0" onclick="return(false)">공개글</span>
			<span><input type = "radio" name = "qna_isprivate" value ="1" checked>비밀글</span>
		</div>
		
		<div class = "btnBox">
			<input type="button" value="쓰기" onclick="javascript:checkWrite()">
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function() {

		$('.summernote').summernote({
			lang: 'ko-KR',
			height: 400
		});
		
	
	});	
</script>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>





















