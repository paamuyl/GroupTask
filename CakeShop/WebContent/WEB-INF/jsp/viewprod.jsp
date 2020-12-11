<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="table-responsive">
                    <table class="table table-hover table-striped tm-table-striped-even mt-3">
                                        <thead>
                                            <tr class="tm-bg-gray">
                                            	<th scope="col" class="text-center">&nbsp;</th>
                                                <th scope="col" class="text-center">Product Code</th>
                                                <th scope="col" class="text-center">Product Name</th>
                                                <th scope="col" class="text-center">Units Price</th>
                                                <th scope="col" class="text-center">In Stock</th>
                                                <th scope="col" class="text-center">Expire Date</th>
                                                <th scope="col" class="text-center">&nbsp;</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach  var="current"  items="${message}" begin = "${pageBegin}" end = "${pageEnd}" >
                                            <tr>
                                            	<th scope="row">
                                                    <input id="checkbox" type="checkbox" name="checkboxs" aria-label="Checkbox" value="${current.productCode}">
                                                </th>             
                                                 <td class="text-center"><c:out value="${current.productCode}" /></td>
                                                <td class="text-center"><c:out value="${current.productName}" /></td>
                                                <td class="text-center"><c:out value="${current.price}" /></td>
                                                <td class="text-center"><c:out value="${current.unitInStock}" /></td>
                                                <td class="text-center"><c:out value="${current.expireDate}" /></td>
                                                <td><i class="fas fa-pencil-alt" style="CURSOR: pointer" id="${current.productCode}" onclick="addbtn(this)"></i></td>
                                            </tr>
                                           </c:forEach>                              
                                        </tbody>
                                    </table>
                                </div>
                      
                                <div class="tm-table-mt tm-table-actions-row">
                                    <div class="tm-table-actions-col-left">
                                        <button  id="del" class="btn btn-danger" onclick="DelCheck()">Delete Selected Items</button>
                                    </div>
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
                                    </div>
