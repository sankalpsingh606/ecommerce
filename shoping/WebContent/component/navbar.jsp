<%@page import="com.sankalp.dao.Dao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<body>
	<header>
		<!--nav-->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark custom-bg">
			<div class="container">
				<a class="navbar-brand" href="index.jsp">Sankalp</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item active"><a class="nav-link"
							href="index.jsp">Home <span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> Categories </a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<c:forEach items="${Dao.geDao().getCategoryList()}" var="cat">
									<a class="dropdown-item"
										href="register?opt=6&category=${cat.categoryId}">${cat.categoryTitle}</a>
								</c:forEach>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="register?opt=6&category=all">All Product</a>
							</div></li>
					</ul>

					<ul class="navbar-nav ml-auto">
						<li class="nav-item active"><a style="font-size: 18px;"
							class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i
								class="fa fa-cart-plus"></i> <span class="ml-0 cart-items"></span>
						</a></li>
						<c:choose>
							<c:when test="${current_user == null}">
								<li class="nav-item active"><a class="nav-link"
									href="login.jsp">Login </a></li>
								<li class="nav-item active"><a class="nav-link"
									href="register.jsp">Register</a></li>

							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link" href="admin.jsp">${current_user.getUserName()}
								</a></li>
								<li class="nav-item"><a class="nav-link"
									href="register?opt=3">logout</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<c:import url="component/model.jsp" />
</body>
</html>
