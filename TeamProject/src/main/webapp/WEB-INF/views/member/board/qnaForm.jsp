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
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" class="selectBoard">Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" >FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>
<br><br><br>
		<div align="center">
		<form action = "#" name = "qnaList">
			<div>총게시글 수 :  ${total}</div>
			<br><br>	
			<table>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			
		
			<c:choose>
				<c:when test="${empty list}"> <!-- 회원목록에 데이터가 존재하지 않으면 -->
					<tr align="center">
						<th colspan="5" >
							<span style="font-weight: bold;">게시글이 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>			
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
						<input type="hidden" value ="${vo.qna_no}"  name = "no">
						<tr>
							<th>${vo.rn}</th>
							<th class = "title">
							<a href="${pageContext.request.contextPath}/member/board/privateQnaForm?no=${vo.qna_no}">${vo.bo_name} &nbsp; ${vo.qna_title}</a></th>
							<th>${vo.mb_name}</th>							
							<td>${vo.qna_date}</td>
							<td>${vo.qna_view}</td>												
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<br>
		<div class="btn">
			<c:if test = "${!empty login.getMb_id()}">
				<input type = "button" value = "글쓰기" onclick ="location.href='${pageContext.request.contextPath}/member/board/qnaWriteForm?no=2'">
			</c:if>
		</div>
	</form>
		<!-- 페이징 처리 한페이지에 50개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 30 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 15 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 15 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 30 }">[다음]</a>
		</c:if>	
	</div>


	<%@ include file = "/WEB-INF/views/layout/userFooter.jsp" %> 