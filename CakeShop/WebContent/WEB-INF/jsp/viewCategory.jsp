<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="table-responsive"><!-- table-responsive top-->
	
                    <table class="table table-hover table-striped tm-table-striped-even mt-3">
                        <thead>
                            <tr class="tm-bg-gray">
                                <th scope="col">編號</th>
                                <th scope="col">產品類別名稱</th>
                                <th scope="col">&nbsp;</th>
                                <th scope="col">&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
	                        <c:forEach items="${message}" var="current" begin = "${pageBegin}" end = "${pageEnd}">
                            <tr>
                                <td>${current.categoryId}</td>
                                <td>${current.categoryName}</td>
                                <td class="tm-trash-icon-cell"><i class="fas fa-pencil-alt" style="CURSOR: pointer" id="${current.categoryId}" name="${current.categoryName}"  onclick="QuerybyId(this)"></i></td>
                                <td class="tm-trash-icon-cell"><i class="fas fa-trash-alt" style="CURSOR: pointer" id="${current.categoryId}" onclick="Del(this)"></i></td>                            
                            </tr>
							</c:forEach>                                                       
                        </tbody>                        
                    </table>
                    
                    	<div class="tm-table-actions-col-right">
                                        <span class="tm-pagination-label">Page</span>
                                        <nav aria-label="Page navigation" class="d-inline-block">
                                            <ul class="pagination tm-pagination">   
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
                                        </nav>
                                    </div>
                    	
                   
                </div><!-- table-responsive end-->

<script>

	function Del(obj) {
		$.post("deleCategory.do", {
			"categoryId" : obj.id}, show)
	}

	function selectPage(obj){
		$.post("SelectPdPageC.do", {"page":obj.id}, show)
	}

	function QuerybyId(obj) {
		$.post("quyIdCat.do", {
			"categoryId" : obj.id}, show)

		}
	
</script>