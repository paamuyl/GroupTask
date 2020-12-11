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

<div class="container">
	<div class="row tm-mt-big">
		<div class="col-12 mx-auto tm-login-col">
			<div class="bg-white tm-block">
				<div class="row">
					<div class="col-12 text-center">
						<i class="fas fa-3x fa-tachometer-alt tm-site-icon text-center"></i>
						<h2 class="tm-block-title mt-3">Login</h2>
					</div>
				</div>
				<div class="row mt-2">
					<div class="col-12">
						<div class="input-group position-relative">
						<label for="username" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Username</label>
							<input type="text" class="form-control validate col-xl-9 col-lg-8 col-md-8 col-sm-7" id="username" value="admin" required>
						</div>
						<div class="input-group">
						<label for="password" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Password</label>
							<input type="password" class="form-control validate" id="password" value="1234" required>
						</div>
						<div class="input-group mt-3 ">
							<button type="button" class="btn btn-primary d-inline-block mx-auto" onclick="login()">Login</button>
						</div>
						<div id ="msg" class="input-group mt-3"></div><!-- viewsuccess.jsp -->
					</div>
				</div>
			</div>
		</div>
	</div>
 </div>
 

<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
 function login() {    		 
    	var account=$("#username").val();
    	var password=$("#password").val();
    	
    	$.post("employeelogin.do", {"account":account, "password":password}, show)
}
 function show(data){
	if(data==("trash")){$("#msg").html("帳號密碼錯誤")}
	if(data==("ok")){location.href="/CakeShop/admin/index.jsp"}
}
 </script>
</body>
</html>

