<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/userHeader.jsp"%>
<style>
#top_nav>ul{
list-style:none;
display:flex;
justify-content:space-around;
width : 40%;
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
#box{
margin-top:100px;
}
.selectBoard{
border-bottom: 3px solid black;
}


</style>

<script>
	
	function checkPw(){
		if($('#login').val()!=1){
			
		if($('#pw').val()==''){
			alert("비밀번호를 입력해주십시오.");
			return false;
			}
		}
		httpRequest = new XMLHttpRequest();
	
		if(!httpRequest){
			alert("인스턴스 생성 불가!");
			return;
		}
		
		var httpMethod = "GET";
		var httpParam = $('#pw').val()
		var httpURL = "${pageContext.request.contextPath}/member/board/privateQna?no="+${no}+"&pw="+ $('#pw').val();
	
		httpRequest.open(httpMethod,httpURL,true);
		
		
		httpRequest.onreadystatechange = resultCheckPw;
		httpRequest.send();
	
	}
	
	function resultCheckPw(){
		if(httpRequest.readyState == 4 && httpRequest.status == 200){
			
			var data = httpRequest.responseText;
		
			if(data=='관리자'){
			 	location.href="${pageContext.request.contextPath}/member/board/qnaContent?no="+${no};
			 	return;
			}else{
				
			
			if(data =='비밀번호가 올바르지 않습니다'){
				alert(data);
				return;
			}else{				
				location.href="${pageContext.request.contextPath}/member/board/qnaContent?no="+${no};
				return;
			}
			}
		}
	}

</script>
	<input type = "hidden" value = "${login.getMb_no() }" id = "login">
	<div id = top_nav align = "center">
		<ul>
			<li><a>NOTICE</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/qnaForm" class="selectBoard">Q&A</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/faqForm" >FAQ</a></li>
			<li><a href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
		</ul>
	</div>
	
	<div align="center" id="box">
		<div>
			<h4>비밀 글 보기</h4>
		</div>
		<div>
			이 글은 비밀글입니다. 비밀번호를 입력하여 주세요.<br>
				관리자는 확인버튼만 누르시면 됩니다.
		</div> 
		<div>
			비밀번호 : &nbsp; <input type = "text" name = "pw" id="pw">
		</div>
		<div>
			<span><input type="button" value = "목록" ></span>
			<span><input type="button" value = "확인" onclick ="checkPw(${no});"></span>
		</div>
	
	</div>
			
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</div>
<%@include file="/WEB-INF/views/layout/userFooter.jsp"%>
















