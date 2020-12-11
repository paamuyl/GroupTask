<nav class="navbar navbar-expand-xl navbar-light bg-light mx-1 mt-5">
	<a class="navbar-brand" href="index.jsp">
		<i class="fas fa-2x fa-tachometer-alt tm-site-icon"></i>
			<h1 class="tm-site-title mb-0">DASHBOARD</h1>
		</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="users.jsp">Users</a>
			</li>
            <li class="nav-item">
                <a class="nav-link" href="member.jsp">Members</a>
			</li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Products</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="categories.jsp">Categories</a>
                    <a class="dropdown-item" href="products.jsp">Products</a>
                </div>
            </li>
			<li class="nav-item">
				<a class="nav-link" href="orders.jsp">Orders</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="lessons.jsp">Lessons</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="lessonsignup.jsp">Lesson SignUp</a>
			</li>
          </ul>
          <ul class="navbar-nav">
              <li class="nav-item">
                  <a id="logout" class="nav-link d-flex" onclick="Logout()" style="cursor: pointer">
                      <i class="far fa-user mr-2 tm-logout-icon"></i>
                      <span>Logout</span>
                  </a>
              </li>
          </ul>
	</div>
</nav>
<script type="text/javascript">
function Logout(){
	$.post("logout.do", document.location.href="login.jsp");
}

</script>