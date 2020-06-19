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

	<h1>Cadastro</h1>
	
	<form action="<c:url value="/register"/>" method="POST" >
		
		<div>
			<label>Nome</label>
			<input name="name" />
		</div>
		
		<div>
			<label>email</label>
			<input name="email" />
		</div>
		
		<div>
			<label>password</label>
			<input name="password" />
		</div>
		
		<div>
			<label>Gênero</label>
			<input name="gender" />
		</div>
		
		<div>
			<label>Data nascimento</label>
			<input name="birthdate" type="date" />
		</div>
		
		<div>
			<label>Endereço</label>
			<input name="address" />
		</div>
		
		<div>
			<label>CEP</label>
			<input name="postcode" />
		</div>
		
		<button>Enviar</button>
		
	</form>

</body>
</html>