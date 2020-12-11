<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="form-group">
	<div class="form-group row">
		<div class="col">
			<select id="lesson" class="form-control">
				<option>選取課程</option>
				<c:forEach var="current" items="${message }">
				<option value="${current.lessonId }">【${current.lessonName }】${current.lessonDate }</option>
				<div name="A" id="A${current.lessonId }" ></div>
				</c:forEach>
			</select>
		</div>
	</div>
</div>
<script>

</script>