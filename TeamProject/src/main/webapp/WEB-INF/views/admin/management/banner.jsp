<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>
body{
align:center;
}
#section{
margin-top:100px;
}
</style>

<script>
	function deleteStock(){
		
	
		if ($("input:checkbox[name='delList']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if(!confirm("선택하신 항목을 삭제하시겠습니까?")){
			return false;
		}else{
			$('#list').attr("action","${pageContext.request.contextPath}/admin/management/deletePic");
			document.banner.submit();
		}
		return true;
	}
</script> 

	<div align = "center" id = "section"> 
	<form action = "${pageContext.request.contextPath}/admin/management/bannerInsert" enctype="multipart/form-data" method = "post" name = "banner" id="list">
		<table>
		<tr>
			<th>메인배너 추가</th>
		</tr>
		<tr>
			<th>*파일명은 'Banner' 가 포함되어야 합니다.</th>
		</tr>
		<tr>
			<th>*사진 출력 순서는 'Banner' 뒤의 숫자로 정할 수 있습니다. ex) Banner1, Banner2 / Banner01, Banner02</th>
		</tr>
		<tr>
			<th><input type="file" name = "photo" size = "400" multiple></th>
		</tr>
		<tr>
			<th>------------------------------------------------------------
			</th>
		</tr>
		<c:choose> 
		<c:when test ="${!empty list}">
		<tr>
			<th>현재 사용중인 배너 목록</th>		
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<th>현재 사용중인 배너가 없습니다.</th>
		</tr>
		</c:otherwise>
		</c:choose>
		<tr>
		<c:forEach var="vo" items = "${list}">
		<tr> 
			<th><img alt="" src="${pageContext.request.contextPath }/resources/adminPic/${vo.ap_filename}" width="800"></th>
			<th><input type = "checkbox" name = "delList" value = "${vo.ap_no}"></th>
		</tr>
		</c:forEach>
		</tr>
		<tr align = "right">
			<th colspan = "2" ><input type = "submit" value = "저장"></th>	
			<c:if test="${!empty list}"> 
			<th colspan = "2"><input type="button" value="삭제" name = "delete" onclick ="deleteStock();" class="del"></th>
			</c:if>
		</tr>
		</table>
	</form>

</div>











</div>