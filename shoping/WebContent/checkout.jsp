<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${current_user == null}">
		<c:set var="message" scope="session"
			value="You are not logged in !! Login first" />
		<c:redirect url="login.jsp" />
	</c:if>
	<c:set var="user" scope="session"
			value="${current_user}" />
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<c:import url="component/link.jsp" />
<title>Insert title here</title>
</head>
<body>
	<c:import url="component/navbar.jsp" />
	<div class="container">
		<div class="row mt-4">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<div class="cart-body"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<form action="register?opt=5" method="post"
							>
							<div class="form-group">
								<input type="email" class="form-control"
									value="${user.userEmail}" placeholder="Enter the e-mail" name="email">
							</div>
							<div class="form-group">
								<input type="text" class="form-control"
								value="${user.userName}"	placeholder="Enter the name" name="name">
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"
								value="${user. userPhone}"	placeholder="Enter the name" name="contact">
							</div>
							<div class="form-group">

								<textarea style="height: 150px" class="form-control"
								value="${user. userAddress}" name="address" placeholder="Enter the address">${user. userAddress}</textarea>
							</div>


							<!-- Modal footer -->
							<div class="card-footer text-center">

								<button type="submit" class="btn btn-outline-success">order
									</button>
								<button type="button" class="btn btn-outline-primary"
									data-dismiss="modal">Close</button>
							</div>

						</form>

					</div>
				</div>

			</div>
		</div>
	</div>

	<div id="toast">This is our custom Toast text</div>
</body>
</html>