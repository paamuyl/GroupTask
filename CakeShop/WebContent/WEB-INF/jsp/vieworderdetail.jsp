<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 修改欄位 -->
				<div class="add-form position-fixed" style=top:30px>
					<div class="position-relative col-6 bg-light row">
						<div class="container">
							<div class="col">
									<div class="row">
											<div class="col-12 mt-5 text-center"><h3>訂單編號:${param.orderNumber}</h3></div>
											</div>
									<div class="form-group1 form-inline">
											<label for="" class="col-4-1 mt-4">明細編號</label>
											<div class="col-8">
												<input type="text" class="form-control1 mt-4" id="odid" value="${param.orderdetailId}" readonly>
											</div>																						
											<label for="" class="col-4-1 mt-4">產品編號</label>
											<div class="col-8 mt-4">
												<input type="text" class="form-control1" id="pCode" value="${param.productCode}">
											</div>
											<label for="" class="col-4-1 mt-4">數量</label>
											<div class="col-8 mt-4">
												<input type="text" class="form-control1" id="qty" placeholder="請輸入數量">
											</div>
											<label for="" class="col-4-1 mt-4">價格</label>
											<div class="col-8 mt-4">
												<input type="text" class="form-control1" id="pr" placeholder="請輸入價格">
											</div>
											<div class="col-8  mt-4">
												<input type="hidden" class="form-control1" id="oNumber" value="${param.orderNumber}">
											</div>		
									</div>
							</div>
						</div>

						<div class="mt-5 mb-0 float-right">
							<button type="button" id="update" class="btn btn-primary save-btn mb-3"  onclick="Update(this)">修改</button>
                                    <button type="button" class="btn btn-secondary close-btn mb-3" onclick="closebtn()">取消</button>
						</div>
					</div>
					</div>

				
				<!-- 修改 -->
<div class="table-responsive1 float-none">
		<table class="table table-hover table-striped tm-table-striped-even mt-3">
			<thead>
				<tr class="tm-bg-gray">
								<th scope="col" class="text-center"><input type="hidden" value="訂單明細編號"></th>
								<th scope="col" class="text-center">產品編號</th>
								<th scope="col" class="text-center">數量</th>
								<th scope="col" class="text-center">價格</th>
								<th scope="col" class="text-center">&nbsp;</th>
								<th scope="col" class="text-center">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${message}" var="current" begin = "${pageBegin}" end = "${pageEnd}">
							<tr>
								<td class="text-center"><input type="hidden" value="${current.orderdetailId}"></td>		
								<td class="text-center">${current.productCode}</td>
								<td class="text-center">${current.quantity}</td>
								<td class="text-center">${current.price}</td>
								<td><a class="fas fa-pencil-alt" style="CURSOR: pointer" id="${current.orderdetailId}" name="${current.productCode}" onclick="addbtn(this)">修改訂單</a></td>
								<td><a class="fas fa-trash-alt" style="CURSOR: pointer" id="${current.orderdetailId}" onclick="Del(this)">移除</a></td>			
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
				
				<div class="tm-table-mt tm-table-actions-row">
				<div class="tm-table-actions-col-left">
                                        
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
