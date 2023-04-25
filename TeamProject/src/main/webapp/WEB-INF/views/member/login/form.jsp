<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<style>
 #section{
 margin-top:5%;
 }
 #kakaoLogin>ul>li{
 list-style:none;
 }

.loginInput{
	font-size: 16px;
    padding: 0;
    height: 54px;
    line-height: 54px;
    background: #ffffff;
}
.loginInput>th>input{
width:390px;
height:55px;
border: 1px solid #d4d4d4;
}

#login_Btn{
 background: #000000;
 border: none;
 text-align: center;
 width: 390px;
 color: #fff;
 height: 61px;
 line-height: 61px;
 font-size: 18px;
 letter-spacing: -1px;
 border-radius:5px;
 margin-top:40px;
 }
#find>a{
 padding: 0 8px 0 0;
 color: #000000;
 font-size: 14px;
 text-decoration:none;
 font-weight:bolder;
 }
 #find{
 margin-left:120px;
 }

</style>
<script type="text/javascript">

 	Kakao.init('c887a7e288f5ebfa94cf8f3f7fc78752'); 
 	console.log(Kakao.isInitialized()); 

 	//카카오로그인 
 	function kakaoLogin() { 
 	    Kakao.Auth.login({ 
 	    scope:'profile_nickname',
 	      success: function (response) { 
 	        Kakao.API.request({ 
 	          url: '/v2/user/me', 	       
 	          success: function (response) { 
 	        	  console.log(response)
 	        	  var mb_id = response.id;
 	        	  var mb_name = response.properties.nickname;
 	        	  return location.href="${pageContext.request.contextPath}/member/login/kakaoLogin?mb_id="+ mb_id +"&mb_name="+ mb_name + "&mode=kakao";
 	        	  	          }, 
 	          fail: function (error) { 
 	            console.log(error) 
 	          }, 
 	        }) 
 	      }, 
 	      fail: function (error) { 
 	        console.log(error) 
 	      }, 
 	    }) 
 	  } 

	function check(f) {
		let reg = /^(?=.*?[a-z])(?=.*?[0-9]).{4,16}$/
		
		if(f.id.value == ""){
			alert("아이디 항목은 필수 입력값입니다.");
			f.id.focus();
			return false;
		}else if(!reg.test(f.id.value)) {
			alert("아이디는 4~16글자,숫자,소문자를 모두 포함해야 합니다.");
			f.id.focus();
			return false;
		}else if(f.pw.value == ""){
			alert("패스워드 항목은 필수 입력값입니다.");
			f.pw.focus();
			return false;
		}else if(!reg.test(f.pw.value)) {
			alert("비밀번호는 4~16글자,숫자,소문자를 모두 포함해야 합니다.");
			f.pw.focus();
			return false;
		}
		
		return true;
	}
</script>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<br>
	<div align="center" id = "section">
		<h2 id="head">LOGIN</h2>
		<br><br><br>
		<form action="${ pageContext.request.contextPath }/member/login/check" onsubmit="return check(this)">
			<table>
				<tr class="loginInput">
					<th><input type="text" name="id" value="${id }" placeholder="아이디"></th>
				</tr>
				<tr class="loginInput">
					<th><input type="password" name="pw" placeholder="비밀번호"></th>
				</tr>
				<tr>
					<th colspan="2" align="right">
						<span class="ckid">
							<c:choose>
								<c:when test="${check }">
									<input type="checkbox" name="ckid" value="true" checked="checked">
								</c:when>
								<c:otherwise>
									<input type="checkbox" name="ckid" value="true">
								</c:otherwise>
							</c:choose>
							<font class="ckid_text">아이디 기억하기</font>
						</span>
						<span id="find">
							<a href="${ pageContext.request.contextPath }/member/login/findForm?mode=id">ID찾기</a> | 
							<a href="${ pageContext.request.contextPath }/member/login/findForm?mode=pw">PW찾기</a> 
						</span>
						</th>

				</tr>
				<tr>
					<th>
						<input type="submit" value="Login" id ="login_Btn">
					</th>
				</tr>
			 <tr> 
			 	<th onclick="kakaoLogin();"> 
			       <a href="javascript:void(0)"> 
			           <span><img src="${pageContext.request.contextPath}/resources/img/kakao_login_large_wide.png" width="390px;"></span> 
			       </a> 
			 	</th> 		 	
			 </tr> 
			</table>
		</form>
	</div>

	
<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>

