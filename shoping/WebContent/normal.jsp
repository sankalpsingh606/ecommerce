<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:choose>
<c:when test="${current_user == null}">
<c:set var="message" scope="session" value="You are not logged in !! Login first"/>
<c:redirect url="login.jsp" />
</c:when>
<c:otherwise>
<c:if test="${current_user.getUserType()=='admin'}">
<c:set var="message" scope="session" value="You are not normal user ! Do not access this Page!"/>
<c:redirect url="login.jsp" />
</c:if>
</c:otherwise>
</c:choose>


<!DOCTYPE html>
<html>
<head>
<c:import url="component/link.jsp"/>
<title>Insert title here</title>
</head>
<body>
<c:import url="component/navbar.jsp"/>
<h1>This is normal Panel</h1>
<div id="toast"> This is our custom Toast text </div>
</body>
</html>