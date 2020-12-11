<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="input-group mb-3">
	<label for="category" class="col-xl-4 col-lg-4 col-md-4 col-sm-5 col-form-label">Category</label>
	<select class="custom-select col-xl-9 col-lg-8 col-md-8 col-sm-7" name="category">
		<option selected>Select one</option>
		<c:forEach var="current" items="${message}">
			<option value="${current.categoryId }">${current.categoryName }</option>
		</c:forEach>
	</select>
</div>
