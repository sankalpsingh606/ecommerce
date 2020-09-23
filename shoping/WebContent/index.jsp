<%@page import="com.sankalp.dao.Dao"%>
<%@page import="com.sankalp.helper.Helper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:set var="list" scope="session"
	value="${Dao.geDao().getProductList()}" />
<!DOCTYPE html>
<html>

<head>
<c:import url="component/link.jsp" />
<title>Insert title here</title>

</head>

<body>
	<c:import url="component/navbar.jsp"/>
	<div class="container-fluid">
		<div class="row mt-3 mx-3">
			<div class="col-md-2">
				<ul class="list-group">
					<a href="register?opt=6&category=all"
						class="list-group-item list-group-item-action active">All
						Category</a>
					<c:forEach items="${Dao.geDao().getCategoryList()}" var="cat">
						<a href="register?opt=6&category=${cat.categoryId}"
							class="list-group-item list-group-item-action">${cat.categoryTitle}</a>
					</c:forEach>
				</ul>
			</div>

			<div class="col-md-10">
				<div class="row">
					<div class="col-md-12">
						<div class="card-columns">

							<c:forEach items="${list}" var="p">
								<div class="card p-2 ">
									<div class="container text-center">
										<img src="image/product/${p.pPhoto}" class="card-img-top"
											style="max-height: 200px; max-width: 100%; width: auto;"
											alt="no image">
									</div>
									<div class="card-body">
										<h5 class="card-title">${p.pTitle}</h5>
										<p class="card-text">${Helper.get10words(p.pDesc)}</p>

										<h4 style="font-size: 15px!important;">
											<span id="discount-lable">&#8377;${p.pPrice}</span>,${p.pDiscount }%
											off
										</h4>

									</div>
									<!-- card footer -->
									<div class="card-footer bt text-center">

										<button type="submit" class="btn custom-bg text-white"
											onclick="add_to_cart(${p.pId},'${p.pTitle}',${p.getProductDiscount()},${p.pQuanity})">Add
											to Cart</button>
										<button type="submit" class="btn btn-outline-success">&#8377;
											${p.getProductDiscount()}/-</button>


									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<c:if test="${list.size()==0}">

					<h2>No item in this Category</h2>

				</c:if>

			</div>
		</div>


	</div>

<div id="toast"> This is our custom Toast text </div>
</body>

</html>