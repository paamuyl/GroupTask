<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<% int i=1;%>
	<table class="cart-table">
                <thead class="cart-table-head">
                  <tr class="table-head-row">
                    <th class="product-remove"></th>                    
                    <th class="product-name">名稱</th>
                    <th class="product-price">單價</th>
                    <th class="product-quantity">數量</th>
                    <th class="product-total">金額</th>
                  </tr>
                </thead>
	<tbody>
	<c:forEach items="${message}" var="mycart" varStatus="st">
		<tr class="table-body-row" id="AA">
                    <td class="product-remove"><i  id="<%out.print(i); %>" class="far fa-window-close" onclick="del(this)"></i></td>
                    
                    <td class="product-name" >${mycart.productName}</td>
                    <td class="product-price">$${mycart.price}</td>
                    <td class="product-quantity"><input id="<%out.print(i); %>" name="${mycart.price}" type="number" onchange="changP(this)" value="${msg2[st.count-1]}"></td>
                    <td class="product-total" id="smallTotal<%out.print(i); %>">${mycart.price*msg2[st.count-1]}</td>
                  </tr><%i++; %>
	</c:forEach>
	 </tbody>
	 </table>
<div>${NoMapping}</div>