<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KORMENT</title>
</head>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="${pageContext.request.contextPath }/resources/js/slide.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<!-- summernote를 위해서 추가해야 할 부분... -->
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
.banner>li>a {
    display:block;
}
    
.slidewrap { 
     width: 100%; 
     margin: auto; 
     overflow: hidden; 
 	
} 
.banner>li>img{
  	width:100%;
  	height:100%;
  object-fit: cover;
  height: auto; 
  display: block;
 }
 .wrapper{
 text-align:center;
    width: 100%; 
   margin-bottom:50px;
 }
</style>
 <script>
 $(document).ready(function () {

	    $(function(){
	        $("#menu1").click(function(){
	           $("#drop1").slideToggle();
	     }) 
	     $("#menu2").click(function(){
	           $("#drop2").slideToggle();
	     }) 
	     $("#menu3").click(function(){
	           $("#drop3").slideToggle();
	     }) 
	     $("#menu4").click(function(){
	           $("#drop4").slideToggle();
	     }) 
	     $("#menu5").click(function(){
	           $("#drop5").slideToggle();
	     })   
	 })
	});
 </script>
<body>
    
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/team/">KORMENT</a>
  
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu1" role="button" data-bs-toggle="dropdown" aria-expanded="true">
            제품관리
 		</a>
          <ul class="dropdown-menu" id="drop1">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/product/insertForm">제품등록</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/product/productList">제품목록</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/stock/stockList">재고관리</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu2" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            회원관리
          </a>
          <ul class="dropdown-menu" id="drop2">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/member/list">회원목록</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/member/grade">회원등급관리</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/member/leaveList">탈퇴회원목록</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu3" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            게시판관리
          </a>
          <ul class="dropdown-menu" id="drop3">
            <li><a class="dropdown-item" href="#"></a></li>
            <li><a class="dropdown-item" href="#"></a></li>
            <li><a class="dropdown-item" href="#"></a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu4" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            쿠폰관리
          </a>
          <ul class="dropdown-menu" id="drop4">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/coupon/insertCouponForm">쿠폰등록</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/coupon/couponList">쿠폰목록</a></li>
            <li><a class="dropdown-item" href="#"></a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="menu5" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            홈페이지 관리
          </a>
          <ul class="dropdown-menu" id="drop5">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/management/bannerForm">배너관리</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/management/membershipForm">멤버쉽 관리</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/management/categorieForm">카테고리 관리</a></li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav">
      	<li class="nav-item">
          <a class="nav-link" aria-current="page" href="/team/">Home</a>
        </li>
        </ul>
    </div>
      <div align = "right">
      </div>
  </div>
</nav>


