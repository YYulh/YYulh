<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>

<script>
$(window).on('load',function(){
	
	var cateList = "${cateList}";
	var moreList = "${moreList}";
	
	$("input:checkbox[name='psc_name']").each(function(){
		if(cateList.indexOf(this.value)>-1){
			$(this).attr('checked', true);
			}
		})
		
	$("input:checkbox[name='prod_more']").each(function(){
		if(moreList.indexOf(this.value)>-1){
			$(this).attr('checked', true);
			}
		})	
});

function checkUpdate(){
	
	if(document.update.prod_name.value == ""){
		alert("제품 명을 선택해주십시오");
	}else if(document.update.prod_price.value == ""){
		alert("제품 가격을 선택해주십시오");
	}else if(document.update.prod_disc.value == ""){
			alert("제품 할인율을 선택해주십시오");
	}else if(document.update.prod_sellprice.value ==""){
		alert("실제 판매가격 확인 버튼을 눌러주십시오.")
		return false;
	}else if($("input:checkbox[name='psc_name']:checked").length == 0){
		alert("카테고리를 선택해주십시오.");
	}else if(document.update.prod_content.value == ""){
		alert("상품설명을 입력해십시오");
	}else if($("input:checkbox[name='prod_more']:checked").length == 0){
		alert("추가로 보여질 상품 선택해주십시오.");
	}else{
		document.update.submit();
	}
}

</script>
<div align = "center">
	<br>
	<h3>제품 수정</h3>
	<br>
	<form action = "${pageContext.request.contextPath}/admin/product/update" name = "update" enctype="multipart/form-data" method="post">
		<input type = "hidden" name = "prod_no" value ="${vo.prod_no}">
		<table>
			<tr>
				<th>제품명</th>
				<td><input type = "text" name = "prod_name" id="name" value = "${vo.prod_name}"></td>
			</tr>
			<tr>
				<th colspan = "3">수정하실 상품이 사은품인가요?</th>
			</tr>
			<tr>
				<th><input type = "checkbox" name = "psc_name" id ="freebies" value ="freebies">사은품</th>	
			</tr>
			<tr>
				<th class = "prod">제품가격</th>
				<td colspan = "2" class = "prod"><input type = "number" name = "prod_price" id="price" value = "${vo.prod_price}"></td>
			</tr>
			
			 <tr>
			 	<th class = "prod">제품 할인율</th>	 	
			 	<td colspan = "2" class = "prod"><input type = "number" name="prod_disc" id="disc" value ="${vo.prod_disc}"></td>
			 </tr>
			 
			<tr class = "prod">
			 	<th>실제 판매가격 </th>
			 	<th colspan = "2"><input type = "number" name = "prod_sellprice" id = "sellprice" value="" readonly>
			 	<input type="button" value = "확인" id = "priceCheck"></th>
			 </tr>	
			
			<tr>
				<th class = "prod">Product</th>
			</tr>
			<tr class = "prod">
				<c:forEach var = "psc1" items ="${psc1}">
					<th style = width:120px;><input type = "checkbox" name = "psc_name" value = "${psc1.psc_name}">${psc1.psc_name}</th>
				</c:forEach>		
			</tr>
			<tr>
				<th class = "prod">Fragrance</th>
			</tr>
			<tr>
				<c:forEach var = "psc2" items ="${psc2}">
					<th class = "prod"><input type = "checkbox" name = "psc_name" value = "${psc2.psc_name}">${psc2.psc_name}</th>
				</c:forEach>				
			</tr>
			
			<tr>
			 	<th>상품 설명</th>
			</tr>
			<tr>
			<th colspan="9">
				<textarea rows="10" cols="100" name="prod_content" class="summernote" >${vo.prod_content}</textarea></th>	
			 </tr>
			
			 <tr>
			 	<th>추천 상품</th>
			 </tr>
			 <tr>
			 <th><input type = "checkbox" name = "prod_more" value="">없음</th>
				<c:forEach var = "prod1" items ="${prod1}">
					<th class = "prod"><input type = "checkbox" name = "prod_more" value = "${prod1.prod_name}">${prod1.prod_name}</th>
				</c:forEach>				
			</tr>
			<tr>
				<c:forEach var = "prod2" items ="${prod2}">
					<th class = "prod"><input type = "checkbox" name = "prod_more" value = "${prod2.prod_name}">${prod2.prod_name}</th>
				</c:forEach>				
			</tr>
			<tr>
				<c:forEach var = "prod3" items ="${prod3}">
					<th class = "prod"><input type = "checkbox" name = "prod_more" value = "${prod3.prod_name}">${prod3.prod_name}</th>
				</c:forEach>				
			</tr>
			 <tr>
				<td colspan="10" align="right">
					<input type="button" value="등록" onclick="javascript:checkUpdate()">
					<input type="button" value="초기화" onclick="document.update.reset()">
				</td>
			</tr>	
			
			<tr>
				<td colspan="2" id = "photo">
					<input type="file" name = "photo" size = "400" multiple>
				</td>		
			</tr>
			
		<c:forEach var = "po" items = "${po}">
			<tr>
			<th>첨부파일</th>
			<td colspan="9">
 				<img alt="" src="${pageContext.request.contextPath }/resources/upload/${po.pp_filename}" width="200">
				<a href="${pageContext.request.contextPath }/resources/upload/${po.pp_filename}" download >${po.pp_filename }</a>
			</td>
			</tr>
		</c:forEach>
		</table>
			 
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$('#freebies').attr("checked",false);
		
		$('.summernote').summernote({
			lang: 'ko-KR',
			height: 400
	});
	
		$('#freebies').click(function(){		
			if($("input:checkbox[id = 'freebies']:checked").length >0){				
				$('#price').attr("value", "0");
				$('#disc').attr("value", "0");
				$('#sellprice').attr("value", "0");
				$('.prod').hide();	
			}		
	});
		$('#freebies').click(function(){		
			if($("input:checkbox[id = 'freebies']:checked").length ==0){				
				$('.prod').show();	
			}		
	});
		$('#priceCheck').click(function(){
			$('#sellprice').attr("value",$('#price').val()-($('#price').val()*$('#disc').val()/100));
			})
	});
</script>
