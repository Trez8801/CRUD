<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:include page="header-footer/header.jsp" />

<html>
	<head>
		<title>Read</title>
	</head>
	<body class="bg-secondary">
		<br>
		<div class="text-center text-light">
			<h1>Viewing ${user.name}</h1>
		</div>
		<br><br>
		<div class="container">
			<table class="table table-bordered table-dark">
				<thead>
					<tr>
						<th scope="col" class="text-center">Team</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center w-25">${user.name}</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<table class="table table-bordered table-dark">
				<thead>
					<tr>
						<th scope="col" class="text-center">ID#</th>
						<th scope="col" class="text-center">Name</th>
						<th scope="col" class="text-center">Email</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center w-25">${user.id}</td>
						<td class="text-center w-25">${user.name}</td>
						<td class="text-center w-25">${user.email}</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<table class="table table-bordered table-dark">
				<thead>
					<tr>
						<th scope="col" class="text-center w-25">Experience</th>
						<th scope="col" class="text-center w-25">Programming Languages</th>
						<th scope="col" class="text-center w-25">Frameworks</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center w-25">${user.yearsOfExperience}</td>
						<td class="text-center w-25">${user.programmingLanguages}</td>
						<td class="text-center w-25">${user.frameworks}</td>
					</tr>
				</tbody>
			</table>
			<br><br>
			<table class="table table-bordered table-dark">
				<thead>
					<tr>
						<th scope="col" class="text-center">Biography</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center">${user.bio}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
