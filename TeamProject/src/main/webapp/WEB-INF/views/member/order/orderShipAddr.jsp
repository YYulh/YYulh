<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<style>
tr{
border:1px solid black;
}

th, td{
padding:10px;
}

table{
	width: 1000px;
}

</style>
<script>

	function selectAddr(){
/* 		$('#list').attr("action","${ pageContext.request.contextPath }/member/order/selectAddr?no=${vo.shipaddr_no}");
		document.list.submit();
		 */

	}

</script>

<div>

<div align="right">
    <span>
    <input type="button" name="delete" onclick="deleteAddr()" class="del" value="선택 주소록 삭제">
      
    </span>
    <span>
        <a href="${ pageContext.request.contextPath }/member/mypage/addAddrForm">배송지 등록</a>
    </span>
</div>

<div align="center">
	<form action="${ pageContext.request.contextPath }/member/mypage/order/shippingaddr/delete" method="post" name="list" id="list">  
        <table>
            <thead>
                <tr>
                    <th>
                        <span>
                            <input type="checkbox"  value="selectAll" onclick="selectAll(this)">
                        </span>
                    </th>
                    <th>배송지명</th>
                    <th>수령인</th>
                    <th>전화</th>
                    <th>주소</th>
                    <th>배송지관리</th>
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
							<th><input type="checkbox" name="delList" value="${vo.shipaddr_no}" id="shipaddr_no"></th>							
							<th>${vo.ship_name}
								<input type="hidden" id="ship_name" value="${vo.ship_name}">
							</th>
							<th>${vo.ship_personname}
								<input type="hidden" id="ship_personname" value="${vo.ship_personname }">
							</th>
							<th>${vo.ship_tel1}-${vo.ship_tel2}-${vo.ship_tel3}
								<input type="hidden" id="ship_tel1">
								<input type="hidden" id="ship_tel2" value="${vo.ship_tel2 }">
								<input type="hidden" id="ship_tel3" value="${vo.ship_tel3 }">
							</th>
							<th>${vo.ship_addr}
								<input type="hidden" id="ship_addr" value="${vo.ship_addr}">
							</th>
							
							<th> 
							<a href="${ pageContext.request.contextPath }/member/mypage/updateAddrForm?no=${vo.shipaddr_no}">수정</a><br>
							<input type="button" name="selectAddr" onclick="selectAddr()" class="select" value="적용">
							</th>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
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
     
     	
       <!--  <div>
            <span>
            <input type="button" name="selectAddr" onclick="selectAddr()" class="select" value="선택 주소록 적용">
            </span>
        </div> -->
    </form>
	
	</div>
</div>