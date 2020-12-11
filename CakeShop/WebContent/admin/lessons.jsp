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
  <link rel="stylesheet" href="../fontawesome-5.15.1/css/all.min.css" type="text/css">

</head>
<body>

<!-- 分類目錄 -->	
<jsp:directive.include file="header.jsp" />
<!-- 分類目錄 -->

                 <!-- row -->
                 <div class="row mt-5">
                        <div class="col-xl-12 col-lg-12 tm-md-12 tm-sm-12 tm-col">
                            <div class="bg-white tm-block h-100">
                                <div class="row">
                                    <div class="col-md-8 col-sm-12">
                                        <h2 class="tm-block-title d-inline-block">Lessons</h2>
        
                                    </div>
                                    <div class="col-md-4 col-sm-12 text-right">
                                        <a href="add-lesson.jsp" class="btn btn-small btn-primary">Add Lessons</a>
                                    </div>
                                </div>
                                
                                <div id="msg"></div>                              
                                
                               </div>
                            </div>
                        </div>
                   <div class="add-form position-fixed m-5 row" style=width:550px;top:200px;left:150px;><!--rm position-relative m-5 row Form Body Top -->
                        <form class="position-absolute col-12 mx-auto" action="#" method="POST">
                               
                            <div class="form-group row mt-3">
                                <label for="" class="col-4 p-3">ID</label>
                                <div class="col-6">
                                    <input type="text" class="form-control" id="lessonId" readonly>
                                </div>
                            </div>
                            <div class="form-group row">
                                    <label for="" class="col-4 p-3">lesson Name</label>
                                    <div class="col-6">
                                        <input type="text" class="form-control" id="lessonName" placeholder="請輸入名稱">
                                    </div>
                            </div>
                            <div class="form-group row">
                                <label for="" class="col-4 p-3">lesson Description</label>
                                <div class="col-6">
                                    <input type="text" class="form-control" id="lessonDescription" placeholder="請輸入內容">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="" class="col-4 p-3">Price</label>
                                <div class="col-6">
                                    <input type="text" class="form-control" id="lessonPrice" placeholder="請輸入價格">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="" class="col-4 p-3">Date</label>
                                <div class="col-6">
                                    <input type="text" class="form-control" id="lessonDate" placeholder="請輸入日期">
                                </div>
                            </div>

                            <div class="">
                                    <button type="button" id="update" class="btn btn-primary save-btn mb-3" onclick="closebtn()">修改</button>
                                    <button type="button" class="btn btn-secondary close-btn mb-3" onclick="closebtn()">取消</button>        
                            </div>  
                        </form>  
                      </div>

      
<!--js///////////-->
<script src="../js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script>
function show(data){
	$("#msg").html(data);
} 

function addbtn(obj){
    $(".add-form").toggleClass("active");
    $("#lessonId").val(obj.id);
}
function closebtn(){
    $(".add-form").removeClass("active");
}

function Update(){
	var lessonId=$("#lessonId").val();
	var lessonName=$("#lessonName").val();
	var lessonDescription=$("#lessonDescription").val();
	var lessonPrice=$("#lessonPrice").val();
	var lessonDate=$("#lessonDate").val();
	$.post("updateLesson.do", {"lessonId":lessonId, "lessonName":lessonName, "lessonDescription":lessonDescription, "lessonPrice":lessonPrice, "lessonDate":lessonDate}, show);
	$("#lessonName").val("");$("#lessonDescription").val("");$("#lessonPrice").val("");$("#lessonDate").val("");
}

$(document).ready(function(){
	$.get("lesson.do" , show)
	$("#update").click(Update)
})

</script>
</body>
</html>