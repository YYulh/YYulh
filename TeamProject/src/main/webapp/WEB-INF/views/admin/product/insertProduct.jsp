<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<script>

	function checkInsert(){
		if(document.insert.name.value == ""){
			alert("제품 명을 선택해주십시오");
			return false;
		}else if(document.insert.price.value == ""){
			alert("제품 가격을 선택해주십시오");
			return false;
		}else if(document.insert.disc.value == ""){
			alert("제품 할인율을 선택해주십시오");
			return false;
		}else if(document.insert.sellprice.value ==""){
			alert("실제 판매가격 확인 버튼을 눌러주십시오.")
			return false;
		}else if(document.insert.content.value == ""){
			alert("상품설명을 입력해십시오");
			return false;
		}else if(document.insert.photo.value == "" ){
			alert("등록할 사진을 선택해주십시오.");
			return false;
		}else if($("input:checkbox[name='more']:checked").length == 0){
			alert("추가로 보여질 상품 선택해주십시오.");
			return false;
		} else{			
			document.insert.submit();	
			return true;			
			}
		}
</script>

<div align = "center">
	<br>
	<h3>제품 등록</h3>
	<br>
	<form action = "${pageContext.request.contextPath}/admin/product/insert" id="insert" name = "insert" enctype="multipart/form-data" method="POST" >
		<table>
		
			<tr>
				<th>제품명</th>
				<th colspan = "5"><input type = "text" name = "name" placeholder = "제품명 "></th>								
			</tr>
			
			<tr>
				<th colspan = "3">등록하실 상품이 사은품인가요?</th>
			</tr>
			<tr>
				<th><input type = "checkbox" name = "psc" id ="freebies" value ="freebies">사은품</th>	
			</tr>
			
			<tr class = "prod">
				<th>제품가격</th>
				<th colspan = "2"><input type = "number" name = "price" id = "price" placeholder = "제품가격 "></th>
			</tr>
			
			 <tr class = "prod">
			 	<th>상품 할인율</th>	 	
			 	<th colspan = "2"><input type = "number" name="disc" id = "disc" placeholder = "숫자만 작성,% 제외"></th>
			 </tr>	
			 
			 <tr class = "prod">
			 	<th>실제 판매가격 </th>
			 	<th colspan = "2"><input type = "number" name = "sellprice" id = "sellprice" value="" readonly placeholder="확인버튼을 눌러주세요.">
			 	<input type="button" value = "확인" id = "priceCheck"></th>
			 </tr>		 

			<tr class = "prod">
				<th>Product</th>
			</tr>
			<tr class = "prod">
				<c:forEach var = "psc1" items ="${psc1}">
					<th style = width:120px><input type = "checkbox" name = "psc"  value = "${psc1.psc_name}">${psc1.psc_name}</th>
				</c:forEach>	
			</tr>
			
			<tr class = "prod">
				<th>Fragrance</th>
			</tr>
			<tr class = "prod">
				<c:forEach var = "psc2" items ="${psc2}">
					<th><input type = "checkbox" name = "psc"  value ="${psc2.psc_name}">${psc2.psc_name}</th>
				</c:forEach>		
			</tr>
			
			<tr>		
			 	<th>상품 설명</th>
			</tr>
			<tr>
				<th colspan="9"><textarea rows="10" cols="100" name="content" class="summernote" ></textarea></th>
			 </tr>
			
			 <tr>
			 	<th>추천 상품</th>
			 </tr>
			 <tr >
			 <th><input type = "checkbox" name = "more" value = "">없음</th>
				<c:forEach var = "prod1" items ="${prod1}">
					<th class="prod"><input type = "checkbox" name = "more" value = "${prod1.prod_name}">${prod1.prod_name}</th>
				</c:forEach>				
			</tr>
			<tr>
				<c:forEach var = "prod2" items ="${prod2}">
					<th class = "prod"><input type = "checkbox" name = "more" value = "${prod2.prod_name}">${prod2.prod_name}</th>
				</c:forEach>				
			</tr>
			<tr>
				<c:forEach var = "prod3" items ="${prod3}">
					<th class = "prod"><input type = "checkbox" name = "more" value = "${prod3.prod_name}">${prod3.prod_name}</th>
				</c:forEach>				
			</tr>
			 <tr>
				<td colspan="4" align="right">
					<input type="button" value="등록" onclick="javascript:checkInsert()">
					<input type="button" value="초기화" onclick="document.insert.reset()">
				</td>
			</tr>
			<tr>
				<td colspan="2" id = "photo">
					<input type="file" name = "photo" size = "400" multiple>
				</td>		
			</tr>
			
		</table>			 
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {

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
	})
		$('#freebies').click(function(){		
			if($("input:checkbox[id = 'freebies']:checked").length ==0){				
				$('.prod').show();	
			}		
	})
		$('#priceCheck').click(function(){
			$('#sellprice').attr("value",$('#price').val()-($('#price').val()*$('#disc').val()/100));
		})
	});	
</script>
