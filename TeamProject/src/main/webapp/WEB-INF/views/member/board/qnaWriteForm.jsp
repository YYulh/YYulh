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
	
	<input type= "hidden" value = "0" name = "seq">
		<table class = "boardTable">
			<tr>
				<th width="60">제목</th>
				<td><input type="text" name = "qna_title" size = "53"></td>
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
					★ 주문취소는 마이페이지 내 [취소접수] 를 이용해주세요<br>
					(▶취소하는 방법 : 홈페이지 로그인 → 마이페이지 → 주문내역 확인 → 주문취소 접수)<br><br>
					
					★ 주문하신 상품을 변경하시거나 주소지를 변경하시는 경우, [주문취소] 후 [재주문] 을 부탁드립니다 :)<br><br>
					
					★ Q&A 게시판을 통해 취소/변경을 신청주실 경우, 반영되지 않은채로 출고가 진행될 수 있습니다.<br><br>
					
					-----------------------------------------------------------------------<br><br>
					
					성함:<br><br>
					
					문의 내용:
					</textarea>
				</td>
			</tr>		
		</table>
		
		<div>
			<span>비밀번호</span>
			<span><input type = "text" name = "qna_pw"></span>
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





















