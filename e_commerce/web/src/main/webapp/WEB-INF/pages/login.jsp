<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1> Login </h1>
	
	<form action="<c:url value="auth"/>" method="POST" >
		<div>
			<label>Email</label>
			<input name="email" />
		</div>
		
		<div>
			<label>Senha</label>
			<input name="password" type="password" />
		</div>
		
		<button> LOGIN </button>
		
		<a href="<c:url value="register"/>"> Registrar-se </a>
		
	</form>

</body>
</html>