<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header-footer/header.jsp" />

<html>
	 <head>
		 <title>Statistics</title>
	 </head>
	 <body class="bg-secondary">
	 	<br>
	 	<div class="text-center text-light">
			<h1>Programming Languages</h1><p>-------------------------</p>
			<h3>
				The favored programming language is <div class="text-warning">${language}</div> with
				<div class="text-warning">${langPercentage}%</div> of interns knowing it.
			</h3>
			<br>
			<p>*****************************************************************************************</p>
		</div>
	 	<div class="text-center text-light">
			<h1>Frameworks</h1><p>-------------------------</p>
			<h3>
				The favored framework is <div class="text-warning">${framework}</div> with
				<div class="text-warning">${framePercentage}%</div> of interns knowing it.
			</h3>
		</div>
	 </body>
</html>
