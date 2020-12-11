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



<!-- 輸出欄位 -->

<div class="row mt-5">
		<div class="col-xl-12 col-lg-12 tm-md-12 tm-sm-12 tm-col h-auto">
			<div class="bg-white tm-block">
			<h1 class="col-12" align="center">訂單明細</h1>
				<div class="row mt-2 col-sm-12">
						<h1 class="tm-block-title d-inline-block float-right col-md-6 float-left">訂單編號:${param.orderNumber}</h1>	
						<div class="text-right col-md-6"><h5><a href="orders.jsp">返回訂單列表</a></h5></div>
				</div>
			
				<div id="msg"></div>
				</div>
			</div>
		</div>


			

	<!--js///////////-->
	<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript"></script>

	<script>

	function Del(obj) {
		$.post("deleteOrderdetail.do", {
			"orderdetailId" : obj.id,
			"orderNumber" : ${param.orderNumber},
			"productCode":"",
			"quantity":"",
			"price":""
		}, show)
	}
	
	function show(data){
		$("#msg").html(data);
	}
	
	function selectPage(obj){
		$.post("SelectPdPageOt.do", {"page":obj.id}, show)
	} 

	$(document).ready(function(){
		$.get("orderdetailquerybyid.do",{"orderNumber":${param.orderNumber}}, show);
		$("#update").click(Update);
	})
			
		
	function addbtn(obj){
		$("#odid").val(obj.id);
		$("#pCode").val(obj.name);
	    $(".add-form").toggleClass("active");    
	}
	function closebtn(){
	    $(".add-form").removeClass("active");
	}

	function Update(){
		var odid=$("#odid").val();
		var oNumber=$("#oNumber").val();
		var pCode=$("#pCode").val();
		var qty=$("#qty").val();
		var pr=$("#pr").val();		
		$.post("updateOrderdetail.do", {"odid":odid,"oNumber":oNumber, "pCode":pCode, "qty":qty, "pr":pr}, show)
	}
	</script>

</body>
</html>
