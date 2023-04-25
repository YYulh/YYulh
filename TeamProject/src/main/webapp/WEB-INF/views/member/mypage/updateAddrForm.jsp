<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>
.row{
padding:50px;
margin-top:100px;
/* border:1px solid black; */
}

a{
color: white;
text-decoration: none;
display: block;
}

 .longText {
     width: 500px;

}
</style>
<script>
$(document).ready(function(){
	$('input[name="ship_default"]').change(function(){
	    this.value = (Number(this.checked));
	    
	    console.log(this.value);
	});

});
	
	
	
</script>
<body>
    
 <%--    <div>
        <ol>
            <li>
                <a href="${pageContext.request.contextPath }">홈</a>
            </li>
            <li>
                <a href="#">마이 쇼핑</a>
            </li>
            <li>
                배송 주소록 관리
            </li>
        </ol>
    </div> --%>
<div class="container">
   <div class="row  border border-1">
    	<div align="center">
    		<h2>배송 주소록 관리</h2>
        <p>자주 쓰는 배송지를 등록 관리하실 수 있습니다.</p>
    	</div>
      
    </div>

    <form action="${ pageContext.request.contextPath }/member/mypage/updateAddr" method="get" name="updateAddr">
        <table class="table">
            <tr>
                <th>배송지명</th>
                <td>
                    <input type="text" name="ship_name" value="${vo.ship_name }">
                </td>
            </tr>
            <tr>
                <th>성명</th>
                <td>
                	
                    <input type="text" name="ship_personname" value="${vo.ship_addr } ">
                </td>
            </tr>
            <tr>
                <th>주소</th>
                <td>
                	<%-- <input type="hidden" name="ship_default" value="${vo.ship_default}"> --%>
                    <input type="text" name="ship_addr" value="${vo.ship_personname}">
                </td>
            </tr>
            <tr>
                <th>휴대전화</th>
                <td>
                    <select name="ship_tel1">
                        <option value="010" selected>010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="018">018</option>
                        <option value="019">019</option>
                    </select>
                    -
                    <input type="text" maxlength="4" name="ship_tel2" value="${vo.ship_tel2 }">
                    -
                    <input type="text" maxlength="4" name="ship_tel3" value="${vo.ship_tel3 }">
                </td>
            </tr>
           <tr>
                <td colspan="2">
                	<c:choose>
                		<c:when test="${vo.ship_default eq 1}">
                		<input type="checkbox" name="ship_default" value="1" checked>
                		</c:when>
                		
                		<c:otherwise>
                		<input type="checkbox" name="ship_default" value="0" checked>
                		</c:otherwise>
                	</c:choose>
                    기본 배송지로 저장
                </td>
            </tr> 
        </table>
               <div align="right">
        	
            <input type="submit" class="btn btn-primary" value="수정">
            <button type="button" class="btn btn-secondary">
            	<a href="${ pageContext.request.contextPath }/member/mypage/shippingAddr">취소</a>
            </button>
            
        </div>
    </form>
</div>
</body>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>

    