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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>

<!-- 分類目錄 -->
<jsp:directive.include file="header.jsp" />
<!-- 分類目錄 -->

<!-- add-form pop-up window -->
<div class="add-form position-fixed m-5 row" style=width:550px;top:200px;left:150px;>
	<form class="position-absolute col-12 mx-auto" action="#" method="POST">
		<div class="form-group row mt-3">
			<label for="" class="col-4 p-3">Employee Id</label>
			<div class="col-6">
				<input type="text" class="form-control" id="empId" readonly>
			</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Employee Name</label>
				<div class="col-6">
					<input type="text" class="form-control" id="empName" placeholder="請輸入員工名稱">
				</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Account</label>
				<div class="col-6">
					<input type="text" class="form-control" id="empAccount" placeholder="請輸入帳號">
				</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Password</label>
			<div class="col-6">
				<input type="text" class="form-control" id="empPassword" placeholder="請輸入密碼">
			</div>
		</div>
		<div class="form-group row">
			<label for="" class="col-4 p-3">Email</label>
				<div class="col-6">
					<input type="text" class="form-control" id="empEmail" placeholder="請輸入信箱">
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
<section class="page-section">
	<div class="container">
		<div class="row mt-5">
			<div class="col-xl-12 col-lg-12 tm-md-12 tm-sm-12 tm-col">
				<div class="bg-white tm-block h-100">
					<div class="form-group row">
						<label for="" class="col-sm-2 col-form-label">Name</label>
						<div class="col-sm-10">
							<input type="" class="form-control" id="employeeName" placeholder="請輸入Name">                                          
						</div>
					</div>
                    <div class="form-group row">
						<label for="" class="col-sm-2 col-form-label">Account</label>
                        <div class="col-sm-10">
							<input type="" class="form-control" id="account" placeholder="請輸入account">                                          
						</div>
					</div>
                    <div class="form-group row">
						<label for="" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                        	<input type="password" class="form-control" id="password" placeholder="請輸入Password">
                        </div>
                    </div>
					<div class="form-group row">
						<label for="" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="email" placeholder="請輸入Email">
                        </div>
					</div>                                   
					<div class="form-group row">
						<div class="col-sm-10 offset-sm-2">
							<button id="add" class="btn btn-small btn-primary" >新增</button>                                            
							<button id="query" class="btn btn-small btn-primary">查詢</button>
						</div>
					</div>                             
						<div id="msg"></div><!-- viewemp.jsp -->
					</div>
			</div>       
		</div>
	</div>
</section>
<!-- body -->

<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
function show(data){
	$("#msg").html(data);
}

function Add(){
	var employeeName=$("#employeeName").val();
	var account=$("#account").val();
	var password=$("#password").val();
	var email=$("#email").val();
	$.post("addEmployee.do", {"employeeName":employeeName, "account":account, "password":password, "email":email}, show)
}

function Query(){
	$.get("employee.do", show);
}

function addbtn(obj){
    $(".add-form").toggleClass("active");
    $("#empId").val(obj.id);
}
function closebtn(){
    $(".add-form").removeClass("active");
}

function Update(){
	var empId=$("#empId").val();
	var empName=$("#empName").val();
	var empAccount=$("#empAccount").val();
	var empPassword=$("#empPassword").val();
	var empEmail=$("#empEmail").val();
	$.post("updateEmployee.do", {"empId":empId, "empName":empName, "empAccount":empAccount, 
		"empPassword":empPassword, "empEmail":empEmail}, show);
	$("#empName").val("");
	$("#empAccount").val("");
	$("#empPassword").val("");
	$("#empEmail").val("");
}

$(document).ready(function(){
	$("#add").click(Add);
	$("#query").click(Query);
	$("#update").click(Update);
});
</script>
</body>
</html>