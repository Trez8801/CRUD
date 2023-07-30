<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="header-footer/header.jsp" />

<html>
	<head>
		<title>Promote</title>
	</head>
	<body class="bg-secondary">
		<br><br>
		<div class="h-50 d-flex flex-column align-items-center justify-content-center text-light">
			<div>
				<h2>Send ${user.name} along the journey to MTU Pro!</h2>
			</div>
			 <br>
			<div class="d-flex flex-column">
				<form:form method="post" modelAttribute="user" action="/${user.id}/deleteUser">
					 <div class="d-flex flex-column">
						  <spring:bind path="pay">
								<div>
									 <h6>Pay:</h6>
									 <form:select cssClass="form-select form-select-lg mb-3" path="pay" items="${payList}"
													  multiple="false" size="1" id="pay" required="true"/>
								</div>
						 </spring:bind>
						  <spring:bind path="team">
								<div>
									 <h6>Position:</h6>
									 <form:select cssClass="form-select form-select-lg mb-3" path="team" items="${teamList}"
													  multiple="false" size="1" id="team" required="true"/>
								</div>
						  </spring:bind>
					 </div>
					<div>
						<button class="btn btn-success btn-lg">Begin Journey!</button>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>
