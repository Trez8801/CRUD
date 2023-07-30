<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header-footer/header.jsp" />

<html>
	<head>
		<title>Add User</title>
	</head>
	<body class="bg-secondary">
		<br>
		<div class="text-center text-light">
			<h1>Intern's Information</h1>
		</div>
		<div class="container">
			<form:form method="post" modelAttribute="user" action="/addUser">
				<spring:bind path="id">
					<div class="form-group">
						<label class="text-light">ID#</label>
						<form:input cssClass="input-group-text" path="id" types="number" id="id" required="true"/>
						<form:errors path="id" cssClass="text-danger"/>
						<small class="text-warning fw-bold">*This can be any number of your choosing*</small>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="name">
					<div class="form-group">
						<label class="text-light">Name:</label>
						<form:input cssClass="input-group-text" path="name" type="text" id="name" required="true"/>
						<form:errors path="name" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="email">
					<div class="form-group">
						<label class="text-light">Email:</label>
						<form:input cssClass="input-group-text" path="email" type="email" id="email" required="true"/>
						<form:errors path="email" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="bio">
					<div class="form-group">
						<label class="text-light">Bio:</label>
						<form:textarea cssClass="input-group-text" path="bio" type="textarea" rows="4" cols="80" id="bio"/>
						<form:errors path="bio" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="yearsOfExperience">
					<div class="form-group">
						<label class="text-light">Experience:</label>
						<c:forEach var="year" items="${yearsOfExperienceList}">
							<form:radiobutton cssClass="form-check-input" path="yearsOfExperience" value="${year}"
							                  id="yearsOfExperience" required="true"/>
						</c:forEach>
						<form:errors path="yearsOfExperience" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="programmingLanguages">
					<div>
						<label class="text-light">Programming Languages Known:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="programmingLanguages"
									 multiple="true" size="${languagesList.size()}" id="languages" required="true"/>
						<form:errors path="programmingLanguages" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="frameworks">
					<div>
						<label class="text-light">Frameworks Known:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="frameworks"
									 multiple="true" size="${frameworksList.size()}" id="frameworks" required="true"/>
						<form:errors path="frameworks" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="team">
					<div>
						<label class="text-light">Team they will be apart of:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="team"
									 multiple="false" size="1" id="team" required="true"/>
						<form:errors path="team" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<button class="btn btn-success btn-lg">Submit</button>
			</form:form>
		</div>
	</body>
</html>
