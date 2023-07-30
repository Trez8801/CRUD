<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="header-footer/header.jsp" />

<html>
	<head>
		<title>Home Page</title>
	</head>
	<body class="bg-secondary">
		<br>
		<div class="container">
			<c:choose>
				<c:when test="${not empty usersList}">
					<c:forEach var="user" items="${usersList}">
						<table>
							<tr>
								<th class="col-2 text-light">ID#</th>
								<th class="col-2 text-light">Name</th>
							</tr>
							<tr>
								<td class="col-2 text-light">${user.id}</td>
								<td class="col-8 text-light">${user.name}</td>
								<td><button class="btn btn-info text-light" onclick="location.href='/${user.id}/viewUser-page'">View</button></td>
								<td><button class="btn btn-primary" onclick="location.href='/${user.id}/editUser-page'">Update</button></td>
								<td><button class="btn btn-success" onclick="location.href='/${user.id}/promote'">Promote</button></td>
								<td><button class="btn btn-danger" onclick="location.href='/${user.id}/deleteUser'">Fire</button></td>
							</tr>
						</table>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h1 class="text-light">No Interns!</h1>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
</html>
