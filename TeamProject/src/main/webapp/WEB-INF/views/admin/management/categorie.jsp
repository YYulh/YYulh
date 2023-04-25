<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/layout/adminHeader.jsp" %>
<style>

tr,th{
border-bottom: 1px solid #dfdfdf;
}
.form{
width:396px;
}
.input_name{
width:220px;
}
#all{
display:flex;
justify-content:center;
}
h3{
amrgin-top:100px;
}
</style>
<script>

	function addInput1(){
		var cnt = $('.no').length+1;
		console.log(cnt);
		$('.inputList1').append('<tr class="catrgorieList1"><th><input type = "hidden" name = "no" value = "'+ cnt +'" class="no"></th><th><input type = "text" value = "" name = "psc_name1" class = "input_name1"></th><th align="center"><input type = "checkbox" name = "delList1" value = "'+cnt+'" ></th></tr>');
				};
	
	
		
	function deleteCate1(){
			
		if ($("input:checkbox[name='delList1']:checked").length<1){
			alert("선택된 항목이 없습니다.");
			return false;
		}else if (!confirm("정말로 삭제하시겠습니까?")){
			  return false;		
		}else{
		$('#list1').attr("action","${pageContext.request.contextPath}/admin/management/deleteCate1");
			document.list1.submit();	
			return true;
		}
	};
	
	function deleteInput1(){

 		$('.catrgorieList1').last().remove();
	};
	
	function updateCate1(){
		var cnt = $('.no').length;
		
			for(var i = 0; i < cnt; i++){
				
		if($('.input_name1').eq(i).val()==''){
			alert("등급 명을 입력해주십시오");	
			return false;
		} 
			document.list1.submit();
			return true;
		}
	}
	//--------------------------------------------
		function addInput2(){
		var cnt = $('.no').length+1;
		console.log(cnt);
		$('.inputList2').append('<tr class="catrgorieList2"><th><input type = "hidden" name = "no" value = "'+ cnt +'" class="no"></th><th><input type = "text" value = "" name = "psc_name2" class = "input_name2"></th><th align="center"><input type = "checkbox" name = "delList2" value = "'+cnt+'" ></th></tr>');
				};

		function deleteCate2(){
				
			if ($("input:checkbox[name='delList2']:checked").length<1){
				alert("선택된 항목이 없습니다.");
				return false;
			}else if (!confirm("정말로 삭제하시겠습니까?")){
				  return false;		
			}else{
			$('#list2').attr("action","${pageContext.request.contextPath}/admin/management/deleteCate2");
				document.list2.submit();	
				return true;
			}
		};
		
		function deleteInput2(){
	
	 		$('.catrgorieList2').last().remove();
		};
		
		function updateCate2(){
			var cnt = $('.no').length;
			
				for(var i = 0; i < cnt; i++){
					
			if($('.input_name2').eq(i).val()==''){
				alert("등급 명을 입력해주십시오");	
				return false;
			} 
				document.list1.submit();
				return true;
			}
		}
	
	
	
</script>
<div align = "center" >
	<h3>카테고리 관리</h3>
</div>


<div id = "all" > 

<div align="center" >
<br>
<form action = "${pageContext.request.contextPath}/admin/management/insertPsc1" name = "list1" class="form" id ="list1">
	<table class="inputList1" style = "word-break: break-all">
	
		<tr>
			<th colspan = "4">Product</th>			
		</tr>
		
			<c:forEach var="psc1" items = "${psc1}">
			<tr class="catrgorieList">
				<th><input type = "hidden" name = "no" value = "${psc1.psc_no}" class="no"></th>
				<th><input type = "text" value = "${psc1.getPsc_name()}" name = "psc_name1" class = "input_name1"></th>
				<th align="center"><input type = "checkbox" name = "delList1" value = "${psc1.psc_no}" ></th>
			</tr>
			</c:forEach>	
		<tr>				
</table>
</form>
		<div align = "center">
			<span><input type="button" onclick ="addInput1();" value = "+"></span>
			<span ><input type="button" onclick ="updateCate1();" value = "저장"></span>
			<span><input type="button" onclick ="deleteCate1();" value = "삭제"></span>
			<span ><input type="button" onclick ="deleteInput1();" value = "-"></span>
		</div>
</div>
<br>

<div align="center" >
<br>
<form action = "${pageContext.request.contextPath}/admin/management/insertPsc2" name = "list2" class="form" id ="list2">
	<table class="inputList2" style = "word-break: break-all">
	
		<tr>
			<th colspan = "4">FRAGRANCE</th>			
		</tr>
		
			<c:forEach var="psc2" items = "${psc2}">
			<tr class="catrgorieList">
				<th><input type = "hidden" name = "no" value = "${psc2.psc_no}" class="no"></th>
				<th><input type = "text" value = "${psc2.getPsc_name()}" name = "psc_name2" class = "input_name2"></th>
				<th align="center"><input type = "checkbox" name = "delList2" value = "${psc2.psc_no}" ></th>
			</tr>
			</c:forEach>	
		<tr>				
</table>
</form>
		<div align = "center">
			<span><input type="button" onclick ="addInput2();" value = "+"></span>
			<span ><input type="button" onclick ="updateCate2();" value = "저장"></span>
			<span><input type="button" onclick ="deleteCate2();" value = "삭제"></span>
			<span ><input type="button" onclick ="deleteInput2();" value = "-"></span>
		</div>

</div>

</div>