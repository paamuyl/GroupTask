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

<!-- body -->
<form action="addProduct.do" method="post" enctype="multipart/form-data">
	<div class="row tm-mt-big">
		<div class="col-xl-8 col-lg-10 col-md-12 col-sm-12">
			<div class="bg-white tm-block">
				<div class="row">
					<div class="col-12">
						<h2 class="tm-block-title d-inline-block">Add Product</h2>
					</div>
				</div>
				<div class="row mt-4 tm-edit-product-row">
					<div class="col-xl-7 col-lg-7 col-md-12">
						<div class="input-group mb-3">
							<label for="productName" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Product Name</label>
							<input name="productName" type="text" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7">
						</div>
						<div class="input-group mb-3">
							<label for="description" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 mb-2">Description</label>
							<textarea name="description" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" rows="3" required></textarea>
						</div>
						<div class="input-group mb-3">
							<label for="price" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 mb-2">Price</label>
							<input name="price" type="text" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7">
						</div>
						<div id=mmsg></div>
						<div class="input-group mb-3">
							<label for="expireDate" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Expire Date</label>
							<input name="expireDate" type="text" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7">
						</div>
						<div class="input-group mb-3">
							<label for="unitInStock" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Units In Stock</label>
							<input name="unitInStock" type="text" class="form-control validate col-xl-9 col-lg-8 col-md-7 col-sm-7">
						</div>
						<div class="input-group mb-3">
							<div class="ml-auto col-xl-8 col-lg-8 col-md-8 col-sm-7 pl-0">
								<input type="submit" Value="Add" class="btn btn-small btn-primary">
								<input type="reset" Value="Reset" class="btn btn-small btn-primary">
								<a class="text-right col-md-6" href="products.jsp" style="font-size: 20px">返回</a>
							</div>
						</div>
					</div>
					<div class="col-xl-4 col-lg-4 col-md-12 mx-auto mb-4">
						<div id="result" class="tm-product-img-dummy mx-auto">
							<i class="fas fa-5x fa-cloud-upload-alt" ></i>
						</div>
						<div>
							<input id="fileInput" type="file" name="photo"  /><br/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
<!-- body -->

<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
function show(data){
	$("#msg").text(data);
}

$(document).ready(function(){
	$.get("cate.do", function(data){
		$("#mmsg").html(data)
	})
})

var result = document.getElementById("result");
var input = document.getElementById("fileInput");

input.addEventListener('change',readFile,false);

function readFile(){
	var file = this.files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e){
	result.innerHTML = '<img src="'+this.result+'" alt="" width="240" height="240" />'
	}
}

</script>
</body>
</html>