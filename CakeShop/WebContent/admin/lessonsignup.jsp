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
<link rel="stylesheet" href="../fontawesome-5.15.1/css/all.min.css"
	type="text/css">

</head>
<body>

<!-- 分類目錄 -->
<jsp:directive.include file="header.jsp" />
<!-- 分類目錄 -->

<!-- add-form pop-up window -->
<div class="add-form position-fixed m-5 row" style=width:550px;top:150px;left:150px;>
	<form class="position-absolute col-12 mx-auto" action="#" method="POST">
		<div class="container">
			<div class="row col">
				<div class="col mt-0">
					<div class="form-group1 form-inline float-left">
						<div class="row col">
							<label for="" class="col-4-1">ID</label>
							<div class="col-8">
								<input type="text" class="form-control1" id="lessonSignUpId" readonly>
							</div>
							<label for="" class="col-4-1 mt-4">Name</label>
							<div class="col-8 mt-4">
								<input type="text" class="form-control1" id="name" placeholder="請輸入名稱">
							</div>
							<label for="" class="col-4-1 mt-4">Phone Number</label>
							<div class="col-8 mt-4">
								<input type="text" class="form-control1" id="phoneNumber" placeholder="請輸入電話">
							</div>
							<label for="" class="col-4-1 mt-4">Lesson</label>
							<div class="col-8 mt-4">
								<input type="text" class="form-control1" id="lessonId" placeholder="請輸入課程">
							</div>
							<label for="" class="col-4-1 mt-4">Email</label>
							<div class="col-8 mt-4">
								<input type="text" class="form-control1" id="email" placeholder="請輸入信箱">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mt-5 mb-0 float-right">
			<button type="button" id="update" class="btn btn-primary save-btn mb-3" onclick="closebtn()">修改</button>
			<button type="button" class="btn btn-secondary close-btn mb-3" onclick="closebtn()">取消</button>
		</div>
	</form>
</div>
<!-- add-form pop-up window -->

<!-- body -->
<div class="row mt-5">
	<div class="col-xl-12 col-lg-12 tm-md-12 tm-sm-12 tm-col h-auto">
		<div class="bg-white tm-block">
			<div class="row mt-2 col-sm-12">
				<h1 class="tm-block-title d-inline-block float-right col-md-6 float-left">課程報名列表</h1>	
			</div>
			<div id="msg"></div><!-- viewlessonsignup.jsp -->
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
	$.post("SelectPdPagel.do", {"page":obj.id}, show)
} 

$(document).ready(function(){
	$.get("lessonsignup.do", show);
	$("#update").click(Update);
})
		
function addbtn(obj){
    $(".add-form").toggleClass("active");
    $("#lessonSignUpId").val(obj.id);
       
}
function closebtn(){
    $(".add-form").removeClass("active");
}

function Update(){
	var lessonSignUpId=$("#lessonSignUpId").val();
	var name=$("#name").val();
	var phoneNumber=$("#phoneNumber").val();
	var lessonId=$("#lessonId").val();
	var email=$("#email").val();
	$.post("updateLessonsignup.do", {"lessonSignUpId":lessonSignUpId, "name":name, "phoneNumber":phoneNumber, "lessonId":lessonId,
		"email":email}, show);
	$("#name").val("");$("#phoneNumber").val("");$("#lessonId").val("");$("#email").val("");
}

</script>

</body>
</html>
