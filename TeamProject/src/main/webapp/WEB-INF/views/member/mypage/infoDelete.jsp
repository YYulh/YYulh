<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style>
.tb{
padding: 100px;
}

td{

padding:5px;

} 
</style>
<script type="text/javascript">
	function check(){
		if(document.del.mb_pw.value == ""){
			alert("비밀번호를 입력하십시오!!!");
		}else{
			document.del.submit();
		}
	}
</script>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<div align="center" class="tb">
	<form action="${ pageContext.request.contextPath }/member/mypage/delete" method="post" name = "del">
		
		<div class="container" class="innertb">
		<table>
			<tr align="center">
				<th><p>${vo.mb_name}님의 비밀번호 확인</p></th>
			</tr>
			
			<tr>
				<td>
					<input type="password" name = "mb_pw" placeholder="비밀번호 확인">
				</td>
			</tr>
			<tr>
				<td align="right">
				
					<input type="button" class="btn btn-dark" value="탈퇴" onclick="javascript:check()">
					<input type="button" class="btn btn-primary" onclick="location.href='${ pageContext.request.contextPath }/member/mypage/main'" value="돌아가기">				
				</td>
			</tr>
		</table>
		</div>
	</form>
</div>


<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>