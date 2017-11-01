<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shop</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<li class="nav-item" id="listallproducts">
					<a class="nav-link" href="${contextRoot}/show/all/products">View Products</a>
				</li>
				<li class="nav-item" id="about">
					<a class="nav-link" href="${contextRoot}/about">About</a>
				</li>
				<li class="nav-item" id="contact">
					<a class="nav-link" href="${contextRoot}/contact">Contact</a>
				</li>

				<security:authorize access="hasAuthority('ADMIN')">
					<li class="nav-item" id="manageProducts">
						<a class="nav-link" href="${contextRoot}/manage/products">Manage Products</a>
					</li>
				</security:authorize>
			</ul>

			<ul class="navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li class="nav-item" id="register">
						<a class="nav-link" href="${contextRoot}/register">Sign Up</a>
					</li>
					<li class="nav-item" id="login">
						<a class="nav-link" href="${contextRoot}/login">Login</a>
					</li>
				</security:authorize>

				<security:authorize access="isAuthenticated()">
					<li class="dropdown" id="userModel">
						<a class="btn btn-default" href="javascript:void(0)" id="dropdownMenu1"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
							${userModel.fullName}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<security:authorize access="hasAuthority('USER')">
								<li id="cart">
									<a href="${contextRoot}/cart/show">
										<span class="glyphicon glyphicon-shopping-cart"></span>
										&#160;
										<span class="badge">${userModel.cart.cartLines}</span>
										- &#36; ${userModel.cart.grandTotal}
									</a>
								</li>
							</security:authorize>
							<li role="separator" class="divider"></li>
							<li id="logout">
								<a href="${contextRoot}/logout">Logout</a>
							</li>
						</ul>
					</li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
<script>
	window.userRole = '${userModel.role}';
</script>
