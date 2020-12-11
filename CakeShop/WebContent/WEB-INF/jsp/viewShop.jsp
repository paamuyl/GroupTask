<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="product-section mt-5 mb-5">
	<div class="container">    
	  
		<div class="row">
			<div class="col-md-12">
				<div class="product-filters">
					<ul>
						<li class="active" data-filter="*">All</li>
						<c:forEach var="current2" items="${message2 }">
							<li id="${current2.categoryId }" onclick="CategorySearch(this)"><c:out value="${current2.categoryName }" /></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<form class="form-inline d-flex justify-content-center">
					<input id="search" class="form-control mr-sm-2 mb-3" type="text" placeholder="Search" aria-label="Search">
					<button id="search" class="blue-btn text-white mb-3" type="button" onclick="Search()">Search</button>
				</form>
			</div>
		</div>
		
		<div class="row product-lists mt-5">
			<c:forEach var="current1" items="${message1}" begin="${pageBegin}" end="${pageEnd }">
				<div class="col-lg-4 col-md-6 text-center l-cake">
					<div class="single-product-item">
						<div class="product-image">
							<a href="single-product.html"><img src="/CakeShop/WebContent/images/<c:out value="${current1.picUrl}"/>" style=width:200px;height:200px;></a>
						</div>
						<h3><c:out value="${current1.productName}"/></h3>
						<p class="product-price"><c:out value="${current1.price}"/></p>
						<p class="product-quantity"><input id="Qty${current1.productCode }" name="${current1.productName}" type="number" placeholder="0"></p>
						<a class="cart-btn" id="${current1.productCode}" onclick="addToCar(this)" style="cursor: pointer">
							<i class="fas fa-shopping-cart"></i> 加入購物車
						</a>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="pagination-wrap">
					<ul>   
						<c:if test="${pageN-2>0}">                                        
							<li class="page-item"><a  class="page-link" style="cursor: pointer" onclick="selectPage(this)" id="page${pageN-2}">${pageN-2}</a></li>
						</c:if>
						<c:if test="${pageN-1>0}">
							<li class="page-item"><a  class="page-link" style="cursor: pointer" onclick="selectPage(this)" id="page${pageN-1}">${pageN-1}</a></li>
						</c:if>
							<li class="page-item"><a  class="page-link" style="cursor: pointer" onclick="selectPage(this)" id="page${pageN}">${pageN}</a></li>
						<c:if test="${pageMax-pageN>0}">
							<li class="page-item"><a  class="page-link" style="cursor: pointer" onclick="selectPage(this)" id="page${pageN+1}">${pageN+1}</a></li>
						</c:if>
						<c:if test="${pageMax-pageN>1}">
							<li class="page-item"><a  class="page-link" style="cursor: pointer" onclick="selectPage(this)" id="page${pageN+2}">${pageN+2}</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
		
	</div>
</div>
<script>
function CategorySearch(obj){
	$.post("categorySearch.do", {"categoryId":obj.id},show);
}

function Search(){
	$.post("searchShop.do", {"search":$("#search").val()}, show);
}
</script>