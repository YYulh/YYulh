<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %> 
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
#section{
margin-top:100px;
}
#title{
    font-weight: bolder;
    border-bottom: 1px solid #000;
    font-size: 26px;
    color: #000;
    padding: 23px 0;
    letter-spacing: -0.6px;
    width:30%;
    
}
input{
    border: 1px solid #cfcfcf;
    padding: 4px 6px;    
}
.selectBtn{
    background-color: #048be3;
    border: 1px solid #048be3;
    color: #fff;
    box-shadow: none;
}
#joinBtn{
width:30%;
height:55px;
}
#email{
width:196.42px;
height:34.18px;
}
#agree{
font-weight:bolder;
}
#recomendId{
margin:5px;
}
#kakao_head{
color:#F7E600;
}
</style>
<script>

 function checkJoin() {

	 if(${mode == 'kakao'}){
		 if (document.join.mb_email1.value == "") {
	         alert("이메일을 입력하십시오!");
	         return false;
	      } else if (document.join.mb_email2.value == "") {
	          alert("이메일을 입력하십시오!");
	          return false;
	      } else if (document.join.mb_detailaddr.value == "") {
	          alert("주소를  입력하십시오!");
	          return false;
	      } else if (document.join.mb_tel1.value == "") {
	         alert("전화번호를 입력하십시오!");
	         return false;

	      } else if (document.join.mb_tel2.value == "") {
	         alert("전화번호를 입력하십시오!");
	         return false;

	      } else if (document.join.mb_tel3.value == "") {
	         alert("전화번호를 입력하십시오!");
	         return false;
	      } else if (document.join.mb_bir.value == "") {
	          alert("생일을 입력하십시오!");
	          return false;
	      }else if (document.join.mb_14.value == "0") {
	          alert("필수 동의 항목이 체크되지 않았습니다."); 
	          return false;
	       } else if (document.join.mb_service.value == "0" ) {
	           alert("필수 동의 항목이 체크되지 않았습니다."); 
	           return false;
	        } else if (document.join.mb_info.value == "0") {
	            alert("필수 동의 항목이 체크되지 않았습니다."); 
	            return false;
	         }else {
	         document.join.submit();
	         return true;
	      }
		 
	 }
	 
	 if(${mode == 'nomal'}){
      if (document.join.mb_id.value == "") {
         alert("아이디를 입력하십시오!");
         return false;

	 } else if (document.getElementById("check").innerText == "") {
         alert("아이디 중복확인을 해주십시오.");
         return false;
      }else if (document.join.mb_pw.value == "") {
          alert("비밀번호를 입력하십시오!");
          return false;
       } else if (document.join.mb_repw.value == "") {
         alert("비밀번호확인을 입력하십시오!");
         return false;

      } else if (document.join.mb_name.value == "") {
         alert("이름을 입력하십시오!");
         return false;

      } else if (document.join.mb_resi1.value == "") {
         alert("주민등록번호를  입력하십시오!");
         return false;

      } else if (document.join.mb_resi2.value == "") {
         alert("주민등록번호를  입력하십시오!");
         return false;

      } else if (document.join.mb_email1.value == "") {
         alert("이메일을 입력하십시오!");
         return false;
      } else if (document.join.mb_email2.value == "") {
          alert("이메일을 입력하십시오!");
          return false;
      } else if (document.join.mb_detailaddr.value == "") {
          alert("주소를  입력하십시오!");
          return false;
      } else if (document.join.mb_tel1.value == "") {
         alert("전화번호를 입력하십시오!");
         return false;

      } else if (document.join.mb_tel2.value == "") {
         alert("전화번호를 입력하십시오!");
         return false;

      } else if (document.join.mb_tel3.value == "") {
         alert("전화번호를 입력하십시오!");
         return false;
      } else if (document.join.mb_bir.value == "") {
          alert("생일을 입력하십시오!");
          return false;
      } else if (document.join.mb_pw.value != document.join.mb_repw.value) {
         alert("비밀번호가 일치하지 않습니다."); 
         return false;
      }  else if (document.join.mb_14.value == "0") {
          alert("필수 동의 항목이 체크되지 않았습니다."); 
          return false;
       } else if (document.join.mb_service.value == "0" ) {
           alert("필수 동의 항목이 체크되지 않았습니다."); 
           return false;
        } else if (document.join.mb_info.value == "0") {
            alert("필수 동의 항목이 체크되지 않았습니다."); 
            return false;
         }else {
         document.join.submit();
         return true;
      }
   } 
 }  

 function checkId(){
    	
    	const id = document.getElementById("id");
    	
		 if(id.value == ""){
			 alert("아이디를 입력해주십시오");
			 return;
		 }
 
    	 

    	
    	httpRequest = new XMLHttpRequest();
    	
    	if(!httpRequest){
    		alert("인스턴스 생성 불가");
    		return;
    	}
 
    	
    	var httpMethod ="GET";
    	var httpParam = "id=" + encodeURIComponent(id.value);
    	var httpURL = "${pageContext.request.contextPath }/member/checkId?" + httpParam;
    	
    	httpRequest.open(httpMethod,httpURL,true);
    	
    	//콜백메소드 지정 
    	httpRequest.onreadystatechange = resultCheckId;
    	httpRequest.send();
   
 }
    
        function resultCheckId(){
    	if(httpRequest.readyState == 4 && httpRequest.status == 200){
    		var data = httpRequest.responseText;
    		const check = document.getElementById("check");

    		check.innerText = ""; 
    		
    		if(data == '사용가능한 아이디입니다'){
    			check.style.cssText="color: blue; font-size: 10px;";
    		}else{
    			document.join.mb_id.value = '';
    			check.style.cssText="color: red; font-size: 10px;";
    		}
    		check.innerText = data; 
    	}
    }
   
       
   </script>


<body>
   <div align="center" id = "section">
      <h2 id = "title"><c:if test="${mode=='kakao'}"><span id="kakao_head">카카오&nbsp;</span></c:if>회원가입</h2>
      <br> <br>
  
      <form action="${pageContext.request.contextPath }/member/join/join" method="GET" name="join">
      
     	 <c:if test = "${mode.equals('kakao')}">
		      <input type = "hidden" value = "${mb_id}" name ="mb_id">
		      <input type = "hidden" value = "${mb_name}" name = "mb_name">		    
	      </c:if>
		      <input type = "hidden" value = "${mode}" name = "mode">
	      
         <table>
         <c:if test="${mode.equals('nomal')}">
            <tr>
               <th>아이디</th>
               <td><input type="text" id="id" name="mb_id" placeholder="id" autocomplete="off">
                  <input type="button" onclick="checkId()" value="중복확인" class="selectBtn">
                  <span id="check" ></span>
                 </td>
            </tr>
         

           <tr>
               <th>비밀번호</th>
               <td><input type="password" name="mb_pw" placeholder="pw"></td>
            </tr>
            <tr>
            <th>비밀번호 확인</th>
               <td><input type="password" name="mb_repw"></td>
            </tr>
           
            <tr>
				<th>이름 </th>
	 			<td><input type="text"name="mb_name" ></td>
 			</tr>
             <tr>
               <th>주민등록번호</th>
               
               <th><input type="text" name="mb_resi1" >&nbsp;&nbsp;-&nbsp;
               <input type="password" name="mb_resi2" ></th>
            </tr>
           </c:if>
           
            <tr>
               <th>이메일</th>
               <td><input type="text" name="mb_email1" > 
               @ 
                <select name="mb_email2" id="email">

                     <option value="gmail.com" >gmail.com</option>
                     <option value="naver.com">naver.com</option>
                     <option value="daum.net">daum.net</option>
               </select></td>
            </tr>
            <tr>
            	<th></th>
            </tr>
            <tr>
               <th>주소</th>
               <td><input type="text" id="postcode" name="mb_post" size="5" placeholder="우편번호" readonly>&nbsp;<input type="button" value="주소 검색" class="selectBtn" id="addrSearch"></td>                
            </tr>
			<tr>
				<th></th>
				<td><input type="text" id="base" name="mb_baseaddr" size="56" readonly></td>
			</tr>
			<tr>	
				<th></th>
				<td><input type="text" id="detail" name="mb_detailaddr" size="35" placeholder="상세주소">&nbsp;<input type="text" id="extra" size="15" readonly></td>
			</tr>
            <tr>  
               <th>핸드폰</th>
               <td><select name="mb_tel1" style="width: 60px;">
              
                     <option value="010" >010</option>
                     <option value="011">011</option>
                     <option value="017">017</option>
               </select>
                - 
                <input type="text" name="mb_tel2" size="5"> 
               -
                <input type="text" name="mb_tel3" size="5"></td>
            </tr>

            <tr>
               <th>생일</th>
       
               <td><input type="date" name="mb_bir"placeholder="ex)1999-01-01" ></td>
            </tr>

         </table>
         <br /><br />
            <div id="recomendId">
               <span>추천인 아이디<img src="${pageContext.request.contextPath}/resources/img/joinkakao.gif" width="50px;"></span>
            </div>
            <div>
                <span><input type="text" name="mb_rcid" > </span> 
            </div>
	<br /> <br /> 
	<div id="agree">
       	14세 이상 동의 (필수 )
        <input type="radio" name="mb_14" value="1" checked>동의
        <input type="radio" name="mb_14" value="0">비동의 <br />     

         서비스이용약관 동의 (필수 )
         <input type="radio" name="mb_service" value="1"checked>동의 
         <input type="radio" name="mb_service" value="0">비동의 <br />       
         
         개인정보수집이용 동의 (필수 )
         <input type="radio"  name="mb_info" value="1" checked>동의 
         <input type="radio" name="mb_info" value="0">비동의 <br />
         <br /> 
         SMS 수신 동의 
         <input type="radio" name="mb_sms" value="1" checked>동의 
         <input type="radio" name="mb_sms" value="0">비동의 <br /> 
         이메일 수신동의 
         <input type="radio" name="mb_ckmail" value="1" checked>동의
         <input type="radio" name="mb_ckmail" value="0">비동의 <br /><br />
	</div>



      
         <input  type="button"  onclick="checkJoin()" id="joinBtn" class="selectBtn"  value = "가입하기">

      </form>
   </div> 

</body>

<script>
$(document).ready(function(){
	
	 $('#addrSearch').click(function(){
     	
	       
	        new daum.Postcode({
	            oncomplete: function(data) {
	            	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("extra").value = extraAddr;
	                
	                } else {
	                    document.getElementById("extra").value = '';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById("base").value = addr+',';
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("detail").focus();
	            }
	        }).open();
	       });
})
</script>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %> 

