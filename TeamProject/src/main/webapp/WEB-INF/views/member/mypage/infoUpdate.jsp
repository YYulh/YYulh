<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>

<style>

tr{
	/* border: 1px black solid; */
}

/* table{
	width: 1000px;
	border: 1px black solid;
}
 */
.row{
padding:50px;
margin-top:100px;
/* border:1px solid black; */
}


</style>
 <script type="text/javascript">
    function checkPw(f) {
    	
        if(f.mb_pw.value == 0){
       	 alert("비밀번호를 입력해주세요");
       	 return false;
        }else if(f.mb_pw_check.value == 0){
       	 alert("비밀번호 확인을 입력해주세요");
       	 return false;
        }
    	
      if( f.mb_pw.value != f.mb_pw_check.value ) {
        alert("비밀번호가 일치 하지 않습니다");
        return false;
      } else{

        return true;
      }
   

    }
  </script>

<body>

<div class="container">

<div class="row border border-1">
<div align="center">
	<h3>회원 정보 수정</h3>
    
    <p>
        저희 쇼핑몰을 이용해 주셔서 감사합니다. ${vo.mb_name} 님은 [${vo2.grade_name}] 회원이십니다.
    </p>
</div>

</div>

<div align="center">
<form action="${ pageContext.request.contextPath }/member/mypage/update" method="post"  onsubmit="return checkPw(this);" name="update">
	<br>
	<h4>기본정보</h4>
    <table class="table">
        <tr>
            <th>
                아이디
            </th>
            <td>
                <input type="text" value="${vo.mb_id}"/>
            </td>
        </tr>
    
        <tr>
            <th>
                비밀번호
            </th>
            <td>
                <input type="password" name="mb_pw"/>
            </td>
        </tr>
    
        <tr>
            <th>
                비밀번호 확인
            </th>
            <td>
                <input type="password" name="mb_pw_check"/>
            </td>
        </tr>
    
        <tr>
            <th>
                이름
            </th>
            <td>
                <input type="text" value="${vo.mb_name}"/>
            </td>
        </tr>
        
          <tr>
            <th>
                생년월일
            </th>
            <td>
                <!-- 생년월일 잘라주기 -->
                <input type="text" value = "${vo.mb_bir}" readonly size = "10">

            </td>
        </tr>

        <tr>
            <th>
                주소
            </th>
            <td>
                <input type="text" value="${vo.mb_post}">
                <input type="text" value="${vo.mb_detailaddr}">
            </td>
        </tr>

        <tr>
            <th>
                휴대전화
            </th>
            <td>
                <select name = "mb_tel1">
                    <option value="010" selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                   </select>
				-<input type="text" size = "5" maxlength="4" value = "${vo.mb_tel2}" name="mb_tel2">
				-<input type="text" size = "5" maxlength="4" value = "${vo.mb_tel3}" name="mb_tel3">
			</td>
        </tr>

        <tr>
            <th>SMS 수신여부</th>
            <td>
                <c:choose>
                     <c:when test="${vo.mb_sms eq 1 }">
                		<input type="radio" name="mb_sms" value="1" checked> 수신함
                		<input type="radio" name="mb_sms" value="0"> 수신안함
                	</c:when>
                	<c:otherwise>
                		<input type="radio" name="mb_sms" value="1"> 수신함
                		<input type="radio" name="mb_sms" value="0" checked> 수신안함
                	</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th>이메일</th>
            <td>
                <input type="text"  name="mb_email1" value ="${vo.mb_email1}">
                @<input type="text" name="mb_email2" value ="${vo.mb_email2}">
            </td>
        </tr>
        <tr>
            <th>이메일 수신여부</th>
            <td>
            	<c:choose>
            		<c:when test="${vo.mb_ckmail eq 1}">
            			<input type="radio" name="mb_ckmail" value="1" checked> 수신함
                		<input type="radio" name="mb_ckmail" value="0"> 수신안함
            		</c:when>
            		<c:otherwise>
            			 <input type="radio" name="mb_ckmail" value="1"> 수신함
               			 <input type="radio" name="mb_ckmail" value="0" checked> 수신안함
            		</c:otherwise>
       			</c:choose>
            </td>
        </tr>
    </table>

        
        	<tr>
			 		<td colspan="2" align="center" id="border">
			 			<input type="submit" value = "회원정보 수정" class="btn btn-primary">
			 			<input type="button" class="btn btn-secondary" value = "취소"onclick="location.href='${ pageContext.request.contextPath }/member/mypage/main'">
			 			<input type="button" class="btn btn-dark" value = "탈퇴"onclick="location.href='${ pageContext.request.contextPath }/member/mypage/infoDelete'">
			 		</td>
			 	</tr>
    </table>
</form>
</div>

</div>

</body>


<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>
