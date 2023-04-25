<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
    <style>
tr,th{
border-bottom: 1px solid #dfdfdf;
}
    </style>
    <script>
	function delMember(){
		if ($("input:checkbox[name='check']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}
	
		if (!confirm("제품을 삭제하시겠습니까?")){
			  return false;
		}else{
			document.list.submit();
			return true;
		}
	}

	
</script> 

	<div align="center">
	<br>
	<h3>탈퇴 회원목록 조회</h3><br>
	
	<br>
		<form action = "${pageContext.request.contextPath}/admin/member/deleteMember" name = "list" >
		<table class="memberTable" >
			<tr align="center">
				<th>회원번호</th>
				<th>회원등급</th>
				<th>ID</th>
				<th>PW</th>
				<th>이름</th>
				<th>주민등록번호</th>
				<th>이메일</th>
				<th>우편번호</th>
				<th>상세주소</th>
				<th>전화번호</th>
				<th>가입날짜</th>
				<th>생년월일</th>
				<th>회원유형</th>
				<th>적립금</th>
				<th>총 구매금액</th>
				<th>추천인아이디</th>
				<th>SMS수신</th>
				<th>EMAIL수신</th>
				<th>체크V</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list}"> <!-- 회원목록에 데이터가 존재하지 않으면 -->
					<tr align = "center" >
						<th colspan="20" >
							<span style="font-weight: bold;" >회원정보가 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>			
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
							<tr align = "center" class="table">
							<th>${vo.mb_no}</th>							
							<th class ="${vo.grade_name}">${vo.grade_name}</th>
							<th>${vo.mb_id}</th>
							<th>${vo.mb_pw}</th>
							<th>${vo.mb_name}</th>
							<th>${vo.mb_resi1}-${vo.mb_resi2}</th>
							<th>${vo.mb_email1}@${vo.mb_email2}</th>
							<th>${vo.mb_post}</th>
							<th>${vo.mb_detailaddr}</th>
							<th>${vo.mb_tel1}-${vo.mb_tel2}-${vo.mb_tel3}</th>
							<th>${vo.mb_date}</th>
							<th>${vo.mb_bir}</th>
							<th>${vo.mb_type}</th>
							<th>${vo.mb_point}</th>
							<th>${vo.mb_tp}</th>
							<th>${vo.mb_rcid}</th>
							<th>${vo.mb_sms}</th>
							<th>${vo.mb_ckmail}</th>
							<th><input type = "checkbox" name = "check" value = "${vo.mb_no}"></th>				
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
		</table>
			<br>
			<c:if test="${!empty list}"> 
			<span><input type="button" value="삭제" name = "delete" onclick ="delMember();" class="del"></span>
			</c:if>
		<br>		
		</form>
		
		<!-- 페이징 처리 한페이지에 50개씩 -->
		<c:if test="${nowPage > 2 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 100 }">[이전]</a>
		</c:if>
		<c:if test="${nowPage > 1 }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start - 50 }">[${nowPage - 1 }]</a>
		</c:if>
		[${nowPage }]
		<c:if test="${nowPage < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 50 }">[${nowPage + 1 }]</a>
		</c:if>
		<c:if test="${nowPage + 1 < totalPage }">
			<a href="${pageContext.request.contextPath}/member/list?start=${start + 100 }">[다음]</a>
		</c:if>	
	</div>














