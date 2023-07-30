<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header-footer/header.jsp" />

<html>
	<head>
		<title>Update</title>
	</head>
	<body class="bg-secondary">
		<br>
		<div class="text-center text-light">
			<h1>Update ${user.name}'s Information</h1>
		</div>
		<div class="container">
			<form:form method="post" modelAttribute="user" action="/editUser">
				<spring:bind path="id">
					<div class="form-group disabled">
						<label class="text-light">ID#</label>
						<form:input cssClass="input-group-text disabled" path="id" type="number" disabled="true"
						            placeholder="${user.id}"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="name">
					<div class="form-group disabled">
						<label class="text-light">Name:</label>
						<form:input cssClass="input-group-text" path="name" type="text" disabled="true"
						            placeholder="${user.name}"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="email">
					<div class="form-group disabled">
						<label class="text-light">Email:</label>
						<form:input cssClass="input-group-text" path="email" type="email" disabled="true"
						            placeholder="${user.email}"/>
					</div>
				</spring:bind>
				<br>
				<spring:bind path="bio">
					<div class="form-group">
						<label class="text-light">Bio:</label>
						<form:textarea cssClass="input-group-text" path="bio" type="textarea" id="bio" rows="4" cols="80"
						               placeholder="${user.bio}"/>
					</div>
				</spring:bind>
				<spring:bind path="yearsOfExperience">
					<div class="form-group">
						<label class="text-light">Experience:</label><br>
						<c:forEach var="year" items="${yearsOfExperienceList}">
							<form:radiobutton cssClass="form-check-input" path="yearsOfExperience" value="${year}"
							                  id="yearsOfExperience"/>
							<label class="text-light">${year}</label><br>
						</c:forEach>
						<form:errors path="yearsOfExperience" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<spring:bind path="programmingLanguages">
					<div>
						<label class="text-light">Programming Languages Known:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="programmingLanguages"
						             items="${languagesList}" multiple="true" size="${languagesList.size()}"
						             id="languages"/>
						<form:errors path="programmingLanguages" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<spring:bind path="frameworks">
					<div>
						<label class="text-light">Frameworks Known:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="frameworks"
						             items="${frameworksList}" multiple="true" size="${frameworksList.size()}"
						             id="frameworks"/>
						<form:errors path="frameworks" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<spring:bind path="team">
					<div>
						<label class="text-light">Team they will be apart of:</label>
						<form:select cssClass="form-select form-select-lg mb-3 w-25" path="team"
						             items="${teamList}" multiple="false" size="1" id="team"/>
						<form:errors path="team" cssClass="text-danger"/>
					</div>
				</spring:bind>
				<br>
				<button class="btn btn-success btn-lg">Submit</button>
			</form:form>
		</div>
	</body>
</html>
