<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About to get authorization code.....</title>
</head>
<body>
	<h3 style="color: red;">About to get authorization code.....</h3>

	<div id="getEmployees">
		<form:form action="http://localhost:8080/oauth/authorize"
			method="post">
			<table border="1">
				<tr align="left">
					<th>Response Type :</th>
					<td><input type="text" name="response_type" value="code" /></td>
				</tr>
				<tr align="left">
					<th>Client ID :</th>
					<td><input type="text" name="client_id"
						value="frederick_testing" /></td>
				</tr>
				<tr align="left">
					<th>Redirect URI :</th>
					<td><input type="text" name="redirect_uri"
						value="http://localhost:8090/hello" /></td>
				</tr>
				<tr align="left">
					<th>Scope :</th>
					<td><input type="text" name="scope" value="read" /></td>
				</tr>
				<tr align="left">
					<th><input type="SUBMIT" value="Get Code" /></th>

				</tr>
			</table>





		</form:form>
	</div>
</body>
</html>