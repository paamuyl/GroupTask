<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-hant">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <title>Dashboard Admin Template</title>

  <!--css///////////-->
  <link rel="stylesheet" href="../css/bootstrap.css" type="text/css">
  <link rel="stylesheet" href="../css/admin.css">
  <!-- <link rel="stylesheet" href="../css/fontawesome.min.css" type="text/css"> -->
  <link rel="stylesheet" href="../fontawesome-5.15.1/css/all.min.css" type="text/css">

</head>
<body>

<!-- 分類目錄 -->
<jsp:directive.include file="header.jsp" />
<!-- 分類目錄 -->

<!-- add-form pop-up window -->
<div class="add-form position-fixed m-5 row" style=width:550px;top:200px;left:150px;><!--rm position-relative m-5 row Form Body Top -->
	<form class="position-absolute col-12 mx-auto" action="#" method="POST">
		<div class="form-group row mt-3">
			<label for="" class="col-4 p-3">Product Code</label>
			<div class="col-6">
				<input type="text" class="form-control" id="prodCode" readonly>
			</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Product Name</label>
			<div class="col-6">
				<input type="text" class="form-control" id="prodName" placeholder="請輸入產品名稱">
			</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Units Price</label>
			<div class="col-6">
				<input type="text" class="form-control" id="prodPrice" placeholder="請輸入價錢">
			</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">In Stock</label>
			<div class="col-6">
				<input type="text" class="form-control" id="prodInStock" placeholder="請輸入庫存數量">
			</div>
		</div>
		<div class="">
			<button type="button" id="update" class="btn btn-primary save-btn mb-3" onclick="closebtn()">修改</button>
			<button type="button" class="btn btn-secondary close-btn mb-3" onclick="closebtn()">取消</button>        
		</div>  
	</form>  
</div>
<!-- add-form pop-up window -->             

<!-- body -->
<div class="row mt-5">
	<div class="col-xl-12 col-lg-12 tm-md-12 tm-sm-12 tm-col">
		<div class="bg-white tm-block h-100">
			<div class="row">
				<div class="col-md-8 col-sm-12">
					<h2 class="tm-block-title d-inline-block">Products</h2>
				</div>
				<div class="col-md-4 col-sm-12 text-right">
					<a href="add-product.jsp" class="btn btn-small btn-primary">Add New Product</a>
				</div>
			</div>
			<div id="msg"></div><!-- viewprod.jsp -->
		</div>
	</div>
</div>
<!-- body -->

<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
function show(data){
	$("#msg").html(data);
} 
function selectPage(obj){
	$.post("SelectPdPageP.do", {"page":obj.id}, show)
} 

function addbtn(obj){
    $(".add-form").toggleClass("active");
    $("#prodCode").val(obj.id);
}
function closebtn(){
    $(".add-form").removeClass("active");
}

function Update(){
	var prodCode=$("#prodCode").val();
	var prodName=$("#prodName").val();
	var prodPrice=$("#prodPrice").val();
	var prodInStock=$("#prodInStock").val();
	$.post("updateProduct.do", {"prodCode":prodCode, "prodName":prodName, "prodPrice":prodPrice, "prodInStock":prodInStock}, show);
	$("#prodName").val("");$("#prodPrice").val("");$("#prodInStock").val("");
}

$(document).ready(function(){
	$.get("product.do" , show)
	$("#update").click(Update)
})

function DelCheck() {
	obj = document.getElementsByName("checkboxs");
	check_value = [];
	for (i = 0; i < obj.length ; i++) {
		if (obj[i].checked)
		check_value.push(obj[i].value);
	}
	$.post("deleteProduct.do", {"productIdArr[]":check_value} ,show)
}
</script>
</body>
</html>