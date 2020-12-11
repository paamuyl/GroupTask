<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="table-responsive1 float-none">
		<table class="table table-hover table-striped tm-table-striped-even mt-3">
			<thead>
				<tr class="tm-bg-gray">
								<th scope="col" class="text-center">Name</th>
								<th scope="col" class="text-center">PhoneNumber</th>
								<th scope="col" class="text-center">Lesson</th>
								<th scope="col" class="text-center">Email</th>
								<th scope="col" class="text-center">Member Id(If Exists)</th>
								<th scope="col" class="text-center">&nbsp;</th>
								<th scope="col" class="text-center">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${message}" var="current" begin = "${pageBegin}" end = "${pageEnd}">
							<tr>
								<td class="tm-member-id text-center">${current.name}</td>
								<td class="tm-member-name text-center">${current.phoneNumber}</td>
								<td class="text-center text-center">${current.lessonId}</td>
								<td class="text-center text-center">${current.email}</td>
								<td class="text-center text-center">${current.memberId}</td>
								<td><i class="fas fa-pencil-alt" style="CURSOR: pointer" id="${current.lessonSignUpId}" onclick="addbtn(this)"></i></td>
								<td><i class="fas fa-trash-alt" style="CURSOR: pointer" id="del${current.lessonSignUpId}" onclick="Del(this)"></i></td>
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
<script type="text/javascript">
	function Del(obj) {
		$.post("deleteLessonsignup.do", {
			"name" : obj.id,
		}, show)
	}

</script>