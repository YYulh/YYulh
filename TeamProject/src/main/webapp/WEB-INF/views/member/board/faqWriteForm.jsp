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
	
	if(document.write.title.value == ""){
		alert("제목을 입력하십시오!");
		return;
	}else if(document.write.content.value == ""){
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
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" >Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" class="selectBoard">FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>
	
<div align="center" id ="section">
	<h3>자주 묻는 질문</h3>
	<br><br><br>
	<form action="${ pageContext.request.contextPath }/member/board/write" method="POST" id = "w" name = "write" enctype="multipart/form-data">

		<table class = "boardTable">
			<tr>
				<th width="60">제목</th>
				<td><input type="text" name = "title" size = "53"></td>
			</tr>
			<tr>
				<th>분류</th>
				<th> 
					<select name = "boardOption">
						<c:forEach var="list" items="${list}">
							<option value = "${list.bo_no}">${list.bo_name}</option>
						</c:forEach>
					</select>
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<textarea class="summernote" rows="15" cols="65" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="file" name = "photo" size = "400" multiple>
				</td>
			</tr>
		</table>
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





















