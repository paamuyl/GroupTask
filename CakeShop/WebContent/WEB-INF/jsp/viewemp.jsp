<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="table-responsive">
	<table
		class="table table-hover table-striped tm-table-striped-even mt-3">
		<thead>
			<tr class="tm-bg-gray">
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col" class="text-center">Account</th>
				<th scope="col" class="text-center">Password</th>
				<th scope="col" class="text-center">Email</th>
				<th scope="col" class="text-center">CreatedDate</th>
				<th scope="col">&nbsp;</th>
				<th scope="col">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="current" items="${message}">
				<tr>
					<td class="text-center"><c:out value="${current.employeeId}" /></td>
					<td class="text-center"><c:out value="${current.employeeName}" /></td>
					<td class="tm-product-name"><c:out value="${current.account}" /></td>
					<td class="text-center"><c:out value="${current.password}" /></td>
					<td class="text-center"><c:out value="${current.email}" /></td>
					<td class="text-center"><c:out value="${current.createdDate}" /></td>
					
					<td><i class="fas fa-pencil-alt" style="CURSOR: pointer"
						id="${current.employeeId}" onclick="addbtn(this)"></i></td>
						
					<td><i class="fas fa-trash-alt" style="CURSOR: pointer"
						id="del${current.employeeId}" onclick="Del(this)"></i></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript">
	function Del(obj) {
		$.post("deleteEmployee.do", {
			"employeeName" : obj.id,
			"account" : "",
			"password" : "",
			"email" : ""
		}, show)
	}

</script>
