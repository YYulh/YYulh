<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forment</title>
</head>
 <script src="http://code.jquery.com/jquery-latest.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="${pageContext.request.contextPath }/resources/js/slide.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<!-- summernot를 위해서 추가해야 할 부분... -->
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/summernote/summernote-lite.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/resources/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/summernote/summernote-lite.css">

<style>
body{
width:100%;
max-width: 100%;
}
.slide{    
	 display:flex;
	 padding:0;
}

.slidewrap .slide {
	white-space: nowrap;
	font-size: 0;
	padding: 0;
	position: relative;
	margin:0;
	
}
.slidewrap .slide>li {
    display: inline-block;
    transition: all 0.5s;
  	list-style: none;
    width:100%;
}
.slide>li>a {
    display:block;
}
    
.slidewrap { 
     width: 100%; 
     margin: auto; 
     overflow: hidden; 
 	
} 
.slide>li>img{
  	width:100%;
  object-fit: cover;
  height: auto; 
  display: block;
 }
 .wrapper{
 text-align:center;
    width: 100%; 
   margin-bottom:50px;
 }
 .bigCate{
 font-weight:bolder;
 }
 .nav-link:hover{
 border-bottom: 2px solid black;
 font-weight:bolder;
 }
 .nav-link{
 cursor:pointer;
 width:80px;
 
 }
 #section_header{
 height:65px;
 }
 .nav-item{ 
 text-align:center; 
 } 

 
</style>

<script>	 
    $(function(){
        $("#menu1").click(function(){
           $("#drop1").slideToggle();
     }) 
	     $("#menu2").click(function(){
	         $("#drop2").slideToggle();
	   }) 
 })
 
 
</script>
<body>
    


<nav class="navbar navbar-expand-lg bg-light" id= "section_header">
  <div class="container-fluid">
    <a class="navbar-brand" href="/team/">KORMENT</a>
    
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu1" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
            product
          </a>
          <ul class="dropdown-menu" id="drop1">
         		 <c:forEach var = "pbc" items="${pbc}">
          <li class="bigCate">       	       		
         		 ${pbc.pbc_name}
          </li>
          		</c:forEach>
          
          
          <c:forEach var = "psc1" items="${psc1}">
            <li><a class="dropdown-item  smallCate" href="${pageContext.request.contextPath}/member/product/productList?name=${psc1.psc_name}" >${psc1.psc_name}</a></li>
          </c:forEach>  
         
              
          <c:forEach var = "psc2" items="${psc2}">
            <li><a class="dropdown-item smallCate" href="${pageContext.request.contextPath}/member/product/productList?name=${psc2.psc_name}">${psc2.psc_name}</a></li>
          </c:forEach>
          </ul>
          
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            about
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#"></a></li>
            <li><a class="dropdown-item" href="#"></a></li>
            <li><a class="dropdown-item" href="#"></a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            review
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">ㅎㅇ</a></li>
            <li><a class="dropdown-item" href="#">ㅎㅇ</a></li>
            <li><a class="dropdown-item" href="#">ㅎㅇ</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu2"role="button" data-bs-toggle="dropdown" aria-expanded="false">
            c/s center
          </a>
          <ul class="dropdown-menu" id="drop2">
            <li><a class="dropdown-item" href="#">NOTICE</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/member/board/qnaForm">Q&A</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/member/board/faqForm">FAQ</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/member/board/membershipForm">MEMBERSHIP</a></li>
          </ul>
        
        <li class="nav-item dropdown">
      </ul>
    </div>
       <div align = "right">
        <div class="collapse navbar-collapse" id="navbarNav" >
	      <ul class="navbar-nav">
       <c:choose>
				<c:when test="${empty login}">
	      	<li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/member/login/loginForm">Login</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/member/join/joinForm?mode=nomal">Join</a>
	        </li>
	        </c:when>
	        <c:otherwise>
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" id = "logout" >Logout</a>
	        </li>
	         <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/member/mypage/main">Mypage</a>
	        </li>
	        </c:otherwise>
	       </c:choose>      
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/member/mypage/orderStatus">Order</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/member/order/cart">Cart</a>
	        </li>
	        <c:if test = "${login.getMb_id()=='admin01'}"> 
	         <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/adminHome">관리자페이지</a>
	        </li>
	        </c:if>
	       </ul>
	       </div>   
      </div>
      </div>
</nav>

<script>
Kakao.init('c887a7e288f5ebfa94cf8f3f7fc78752');

	$('#logout').click(function(){	
	 		
	 	    if (Kakao.Auth.getAccessToken()) { 
	 	      Kakao.API.request({ 
	 	        url: '/v1/user/unlink', 
	 	        success: function (response) { 
	 	        	console.log(response) 
	 	        }, 
	 	        fail: function (error) { 
	 	          console.log(error) 
	 	        }, 
	 	      }) 
	 	      Kakao.Auth.setAccessToken(undefined) 
	 	    } else{
	 	    	
	 	   location.href="${pageContext.request.contextPath}/member/login/logout";
	 	   return;
	 	   }	 	    
	})
</script>
