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
<link rel="stylesheet" href="../fontawesome-5.15.1/css/all.min.css"
	type="text/css">

</head>
<body>

<!-- 帶入標題頁 -->
<jsp:directive.include file="header.jsp" />

<section class="page-section">
    <div class="container">        
        <div class="row mt-5"><!-- row top-->

            <div class="col-12">
                <div class="bg-white tm-block">
                    <h2 class="tm-block-title d-inline-block">Product Categories</h2>  

                    <form class="mb-5">
                        <div class="form-group row">
                            <label for="" class="col-sm-4 col-form-label">產品類別名稱</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="addcname" placeholder="請輸入欲新增之產品類別">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-10 offset-sm-4">
                                <button class="btn btn-small btn-primary" onclick="cateAdd()">新增</button>      
                            </div>
                        </div>
                    </form>
                
                	<div id="msg"></div>                      
                
                </div>
            </div>

        </div><!-- row end-->
    </div>
</section> 


<!--js///////////-->
<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
	 <script>

                $(document).ready(function(){
        	  		$.get("category.do",show);
        		});      
                 	
            	function show(data){
            		$("#msg").html(data);
            	} 

            	function cateAdd(){             		 
                 $.post("addCategory.do",
                 		{"categoryName":$("#addcname").val()},
                         show);          
          	  	}            	
                </script>
                
</body>
</html>