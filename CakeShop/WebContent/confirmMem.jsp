<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html lang="zh-hant">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <title>巨匠烘培坊</title>

  <!--css///////////-->
  <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
  <link rel="stylesheet" href="css/style.css" type="text/css">
  <link rel="stylesheet" href="fontawesome-5.15.1/css/all.min.css" type="text/css">
  <link rel="stylesheet" href="css/bootstrap-datepicker3.min.css" />

</head>
<body>

<!-- Navigation -->
<div id="top"></div>
<!-- Navigation -->

  	<!-- breadcrumb-section -->
	<section class="breadcrumb-section breadcrumb-bg">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 offset-lg-2 text-center">
            <div class="breadcrumb-text">
              <p>會員中心</p>
              <h1>資料確認</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- end breadcrumb section -->
    
 <section class="page-section">
      <div class="container">
           <!-- row -->
           <div class="row mt-5 mb-5">
                  <div class="col-xl-9 mx-auto">
                    <h3 class="text-center ">會員資料核對</h3>   
					
					<div class="">	
                          <form class="mt-5" >
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">帳號</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="adacc" value="${param.account}" >
                                  </div>
                              </div>
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">姓名</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="adname" value="${param.username}" >
                                  </div>
                              </div>
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">密碼</label>
                                  <div class="col-sm-10">
                                  <input type="password" class="form-control" id="adpwd" value="${param.password}" >
                                  </div>
                              </div>

                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">性別</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="sex" value="${param.gender}">	
                                  </div>
                              </div>
                              
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">生日</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="bday" value="${param.birthday}" >
                                  </div>
                              </div>
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">電話號碼</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="phone" value="${param.phoneNumber}" >
                                  </div>
                              </div>
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">電子信箱</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="email" value="${param.email}">
                                  </div>
                              </div>
                              <div class="form-group row">
                                  <label for="" class="col-sm-2 col-form-label">地址</label>
                                  <div class="col-sm-10">
                                  <input type="text" class="form-control" id="spot" value="${param.address}">
                                  </div>
                              </div>

                              <div class="form-group row mt-3">
                                  <div class="col-sm-10 offset-sm-2">
                                      <button type="button"  class="btn btn-small btn-primary w-100" onclick="memberAdd()" >確認</button>                                                                             
                                  </div>
                              </div>
                          </form>

                      </div></div></div></div>
        </section>
<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
  <script src="js/bootstrap.min.js" type="text/javascript"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/bootstrap-datepicker.zh-TW.min.js"></script>
	<script src="js/main.js">	</script>
	<script src="js/header.js"></script>    
        <script>
    	
    	 function memberAdd(){
         	 
    	    	$.post("addMember.do",	    	        
    	        		{"account":$("#adacc").val(),
	        				"memberName":$("#adname").val(),
            					"password":$("#adpwd").val(),
            						"gender":$("#sex").val(),
    	        						"birthday":$("#bday").val(),
    	        							"email":$("#email").val(),
    	        							"address":$("#spot").val(),
    	        								"phoneNumber":$("#phone").val()}, 
    	        								   document.location.href="/CakeShop/shop.html"); 
    	    	}
 
			
		
	    
        </script>

</body>
</html>
