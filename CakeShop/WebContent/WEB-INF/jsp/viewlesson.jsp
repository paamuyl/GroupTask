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
				<th scope="col" class="text-center">Description</th>
				<th scope="col" class="text-center">Price</th>
				<th scope="col" class="text-center">Date</th>
				<th scope="col">&nbsp;</th>
				<th scope="col">&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="current" items="${message}">
				<tr>
					<td class="text-center"><c:out value="${current.lessonId}" /></td>
					<td class="text-center"><c:out value="${current.lessonName}" /></td>
					<td class="text-center"><c:out value="${current.lessonDescription}" /></td>
					<td class="text-center"><c:out value="${current.lessonPrice}" /></td>
					<td class="text-center"><c:out value="${current.lessonDate}" /></td>
					
					<td><i class="fas fa-pencil-alt" style="CURSOR: pointer"
						id="${current.lessonId}" onclick="addbtn(this)"></i></td>
						
					<td><i class="fas fa-trash-alt" style="CURSOR: pointer"
						id="del${current.lessonId}" onclick="Del(this)"></i></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



<script type="text/javascript">
	function Del(obj) {
		$.post("deleteLesson.do", {
			"lessonName" : obj.id,
			"lessonDescription" : "",
			"lessonPrice" : "",
			"lessonDate" : "",
			"lessonPicUrl" : ""
		}, show)
	}

</script>
