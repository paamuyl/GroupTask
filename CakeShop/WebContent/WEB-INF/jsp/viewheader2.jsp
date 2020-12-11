<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top clearfix" id="">
	<div class="container">
		<a href="index.html">
			<img class="logo" src="images/logo.png" alt="巨匠烘培坊"  title="巨匠烘培坊">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>  
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mx-auto">
				<li class="nav-item px-lg-4 active"><a class="nav-link" href="index.html" title="">首頁</a></li>
                <li class="nav-item px-lg-4 active"><a class="nav-link" href="shop.html" title="">商品櫥窗</a></li>
                <li class="nav-item px-lg-4 active"><a class="nav-link" href="lessons.html" title="">最新課程</a></li>
                <li class="nav-item px-lg-4 dropdown">
                  <a class="nav-link dropdown-toggle" href="＃" title="" data-toggle="dropdown">會員中心</a>
                  <ul class="dropdown-menu">
                  	  <li><a class="dropdown-item" >你好！ ${memberId.memberName}</a></li>
                      <li><a class="dropdown-item" href="logout.html">登出</a></li>
                      <li><a class="dropdown-item" href="account.html">個資維護</a></li>
                      <li><a class="dropdown-item" href="orders.html">訂單查詢</a></li>
                  </ul>
                </li>
                <li class="nav-item px-lg-4">
                  <div class="header-icon">
                    <a class="nav-link" href="cart.html"><i class="fas fa-shopping-cart"></i><span id="count" class="count"></span></a>                
                  </div>
                </li>
			</ul>
		</div>
	</div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
<script>
$(document).ready(function(){
	var currentItem = Cookies.get("cakeItem");
	var currentItems = currentItem.split(",");
	$("#count").html(currentItems.length);
});
</script>