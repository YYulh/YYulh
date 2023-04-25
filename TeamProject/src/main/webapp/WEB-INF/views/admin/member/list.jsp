<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
tr,th{
border-bottom: 1px solid #dfdfdf;
}
.checkbox{
margin-right:30px;
width:80%;
}
</style>

<script>
	function checksort(){
				var checkGrade = [];
		$("input:checkbox[name='sort']:checked").each(function(){	
			
			//체크된 등급 이름들을 배열에 넣고 
			checkGrade.push ($(this).val());
			
				//각 회원마다 반복 수행
				$('.grade').each(function(){ 
					//회원의 등급이름
				var memberGrade = $(this).val(); 

				//클래스가 memberGrade인(회원의 등급이름) th의 html 정보에 체크된 등급명이 있는지 체크
				// 0이상이면 있음, 미만이면 없음
				if(checkGrade.indexOf($('.'+memberGrade).html())>-1){
					$('.'+memberGrade+'this').show();
				}	else{
					$('.'+memberGrade+'this').hide();					
				}			
			})
		})
				if($("input:checkbox[name='sort']:checked").length == 0){
					$('.list').hide(); //체크된 값이 없다면 모두 hide
				}
	};	
	
	function allShow(){
		if($('#allShow').prop("checked")){
			$('.selectShow').prop('checked', true);
			$('.list').show();
		} else{
			$('.selectShow').prop('checked', false);
			$('.list').hide();
		}
	};
	
	$(document).ready(function(){
		
	$('.selectShow').click(function(){
		
		if($("input:checkbox[name='sort']:checked").length==$("input:checkbox[name='sort']").length){
			$('#allShow').prop('checked', true);
		}else{
			$('#allShow').prop('checked', false);
		}	
	});

	});
	
	
	
	
</script>

	<div align="center">
	<br>
	<h3>회원목록 조회</h3><br>
	<div align="right" class = "checkbox">
	<c:forEach var="grade" items ="${grade}">
	<input type = "checkbox" name = "sort" class="selectShow" value = "${grade.grade_name}" onchange ="checksort();" >${grade.grade_name }
	</c:forEach>
	<input type = "checkbox" name ="allShow" id="allShow" value = "all" onchange ="allShow();">모두
	</div>
	<br>
	
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
			</tr>
			
			<c:choose>
				<c:when test="${empty list}"> <!-- 회원목록에 데이터가 존재하지 않으면 -->
					<tr>
						<th colspan="5" align="center">
							<span style="font-weight: bold;">회원정보가 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>			
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
						<input type="hidden" value ="${vo.grade_name}"  class = "grade">
						<tr align = "center" class="${vo.grade_name}this list" >
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
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
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
















