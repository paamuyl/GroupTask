<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="table-responsive1 float-none">
		<table class="table table-hover table-striped tm-table-striped-even mt-3">
			<thead>
				<tr class="tm-bg-gray">
								<th scope="col" class="text-center">種類編號</th>
								<th scope="col" class="text-center">種類名稱</th>								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tm-member-id text-center"><input id="categoryId"  value="${categoryId}" readonly style="border:none;"></td>
								<td class="tm-member-name text-center"><input id="cateName" value="${cateName}" ></td>
								<td><button class="btn btn-small btn-primary" onclick="Update()">修改</button></td>								
							</tr>
								
						</tbody>
					</table>
				</div>

<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
  <script src="js/bootstrap.min.js" type="text/javascript"></script>
<script>

function Update(){
	var categoryId=$("#categoryId").val();
	var cateName=$("#cateName").val();
	$.post("updateCategory.do", {"categoryId":categoryId, "categoryName":cateName}, show)
}

</script>