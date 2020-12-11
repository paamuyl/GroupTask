<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="order-details">
                <thead>
                  <tr>
                    <th>訂單品項</th>
                    <th>金額</th>
                  </tr>
                </thead>
                <tbody class="order-details-body">
                  <tr>
                    <td>產品</td>
                    <td>小計</td>
                  </tr>
                 <c:forEach items="${message}" var="mycart" varStatus="st">
                  <tr>
                    <td>${mycart.productName}</td>
                    <td>$${mycart.price*msg2[st.count-1]}</td>
                  </tr>
                </c:forEach>
                    
                </tbody>
                <tbody class="checkout-details">
                  <tr>
                    <td>商品總價</td>
                    <td>${Sum}</td>
                  </tr>
                  <tr>
                    <td>運費</td>
                    <td>$100</td>
                  </tr>
                  <tr>
                    <td>結帳金額</td>
                    <td>${Sum+100}</td>
                  </tr>
                </tbody>
              </table>
              
              
              <div class="mt-4 mb-5">
                <button id="${AllProduct}"onclick="checkout(this)" class="blue-btn text-white mb-4 confirm-btn">完成購買</button>
              </div>

              <!-- Confirm window pops-up -->
              <div class="confirm-window position-relative">
                  <div class="confirm-window-inner position-absolute">
                    <p class="pt-3 px-3">已完成購買，謝謝您的訂單，我們將開始安排訂單出貨。</p>
                    <div class="">
                       <a href="shop.html" class="pink-btn text-white mb-4 close-btn">OK，回到商店。</a>
                    </div>
                  </div>
              </div>
<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@beta/dist/js.cookie.min.js"></script>
<script>
function checkout(obj){
	//var AllProduct=${AllProduct}; 
	//var Sum=${Sum+100}; 
	$.post("greenJie.do", {"Sum":${Sum+100},"ProductName":obj.id,"TradeDesc":"甜點禮品"},showAll);
	Cookies.remove('ItemQty');Cookies.remove('cakeItem');
}



</script>