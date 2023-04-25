<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

#email{
display:none;}
</style>


<script>

	function check(f){
		
		let front = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z]).{4,16}$/;
		
		if(f.id.value ==''){
			alert("아이디를 입력해주십시오.");
			f.id.focus();
			return false;
		} else if(f.name.value == ""){
			alert("이름을 입력해주십시오");
			f.name.focus();
			return false;
		}
		
		if($('input:radio[id=findEmail]').is(':checked')){
			
			if(!front.test(f.email1.value)) {
				alert("이메일형식이 올바르지 않습니다.");
				f.email1.focus();
				return false;
			}else{
				return true;
				}
		}
	
		
		if($('input:radio[id=findTel]').is(':checked')){
			
			if(f.name.value == ""){
				alert("이름을 입력해주십시오");
				f.name.focus();
				return false;
			} else if(f.tel1.value ==""){
				alert("전화번호를 입력해주세요");
				f.tel1.focus();
				return false;
			}else if(f.tel2.value ==""){
				alert("전화번호를 입력해주세요");
				f.tel2.focus();
				return false;
			}else if(f.tel3.value ==""){
				alert("전화번호를 입력해주세요");
				f.tel3.focus();
				return false;
			}else {
				return true;
				}
			}
		}
	
	
	function setDisplay1(){
		if($('input:radio[id=findTel]').is(':checked')){
		   $('#email').hide();
		   $('#tel').show()
		 	}else{
		    $('#email').show();
		    $('#tel').hide();
		    }
		}	

		
</script>

<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
	<div align="center">
		<form action="${ pageContext.request.contextPath }/member/login/find" onsubmit="return check(this);" method="post" name="find">
			<c:choose>
				<c:when test="${param.mode == 'id' }">
					<input type="hidden" name="mode" value="id">
					<h3>아이디찾기</h3>
					<br>
				</c:when>
				
				<c:otherwise>
					<input type="hidden" name="mode" value="pw">					
					<h3>비밀번호찾기</h3>
					<br>
				</c:otherwise>
			</c:choose>
			
			<table>
				<tr>
				  <th>회원유형</th>
				  <th>개인회원</th>
				</tr>
				
				<c:if test="${param.mode == 'pw' }">
					<tr>
						<th>아이디</th>
						<td><input type="text" name="id"></td>
					</tr>
				</c:if>
				
				
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr >
					<th><input type="radio" name = "radio" value = "tel"  id = "findTel" onchange = "setDisplay1()" checked>휴대폰 번호로 찾기 </th>
					<th><input type="radio" name = "radio" value = "email" id = "findEmail" onchange = "setDisplay1()">이메일로 찾기 </th>
				</tr>
				<tr style=text-align:center> 
				
					<th id = "tel" colspan = "2">
						<input type="text" size = "5" maxlength="4" name = "tel1">
						- <input type="text" size = "5" maxlength="4" name = "tel2">
						- <input type="text" size = "5" maxlength="4" name = "tel3">
					</th>
					<th id = "email" colspan = "2" style=diplay:none>
						<input type = "text" size = "8" maxlength ="16" name = "email1" >
						@<select name = "email2">
						 <option value = "naver.com">네이버</option>
						 <option value = "daum.net">다음</option>
						 <option value = "google.com">구글</option>
						</select>
					</th>
				</tr>
				<tr>
					<td colspan="2" align="right">
					<br>
						<input type="submit" value="find">
						<input type="button" value="Reset" onclick="document.find.reset()">
					</td>
				</tr>
			</table>
		</form>
	</div>
<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>
