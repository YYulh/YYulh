<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/userHeader.jsp" %>
<style>
tr{
border:1px solid black;
}

table{
	width: 1000px;
}

.container{
padding-top: 100px;
}
 a{
color: white;
text-decoration: none;
display: block;
} 

</style>

<script>
function deleteAddr(){
	
	if ($("input:checkbox[name='delList']:checked").length<1){
		alert("선택된 항목이 없습니다.");
		return false;
	}else if (!confirm("해당 주소록을 삭제하시겠습니까?")){
		  return false;
	}else{
		document.list.submit();
	}
	return true;
	}


function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('delList');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
	}
	
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
    </div>
 --%>
 <div class="container">
 	
 	<div class="row">
 
    <div align="center">
        <h2>배송 주소록 관리</h2>
        <p>자주 쓰는 배송지를 등록 관리하실 수 있습니다.</p>
    </div>
    
    </div>
    
  
	<div align="center">
	<form action="${ pageContext.request.contextPath }/member/mypage/order/shippingaddr/delete" method="post" name="list" id="list">  
        <table class="table">
            <thead align="center">
                <tr>
                    <th>
                        <span>
                            <input type="checkbox"  value="selectAll" onclick="selectAll(this)">
                        </span>
                    </th>
               <!--      <th>주소록 고정</th> -->
                    <th>배송지명</th>
                    <th>수령인</th>
                    <th>전화</th>
                    <th>주소</th>
                  <!--   <th>수정</th> -->
                </tr>
            </thead>
            <tbody>
            
            <c:choose>
				<c:when test="${empty list}">
					<tr align="center">
						<th colspan="7" >
							<span style="font-weight: bold;">배송지 정보가 없습니다.</span>
						</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="vo" items="${list}"> <!-- model에 저장된 list에서 정보 출력 -->
						<tr align = "center">
							<th><input type="checkbox" name="delList" value="${vo.shipaddr_no}"></th>							
						<!-- 	<th>고정버튼</th> -->
							<th>${vo.ship_name}</th>
							<th>${vo.ship_personname}</th>
							<th>${vo.ship_tel1}-${vo.ship_tel2}-${vo.ship_tel3}</th>
							<th>${vo.ship_addr}</th>
							<th> 

							<%-- 	<a href="${ pageContext.request.contextPath }/member/mypage/updateAddrForm?no=${vo.shipaddr_no}">수정</a> --%>
			
							</th>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
            <!--     <tr>
                    <td>체크박스로 늘어나기</td>
                    <td>주소록 고정 버튼</td>
                    <td>배송지명</td>
                    <td>수령인</td>
                    <td>전화</td>
                    <td>주소</td>
                    <td>수정 버튼</td>
                </tr> -->
            </tbody>
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
     
     	
        <div align="right">
            <span>
            <input type="button" class="btn btn-secondary" name="delete" onclick="deleteAddr()" class="del" value="선택 주소록 삭제">
              
            </span>
            <span>
            <button type="button" class="btn btn-primary">
            	 <a href="${ pageContext.request.contextPath }/member/mypage/addAddrForm">배송지 등록</a>
            </button>
               
            </span>
        </div>
    </form>
	
	</div>
    
</div>
</body>

<%@ include file="/WEB-INF/views/layout/userFooter.jsp" %>
