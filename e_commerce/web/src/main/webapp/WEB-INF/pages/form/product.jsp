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
	
	<h1>Produto</h1>
	
	<c:if test="${resource.id != null}">
		<a href="<c:url value="/products?id=new"/>">novo</a>
	</c:if>
	
	<form action="products" method="POST">
		
		<div>
			<label>Nome:</label>
			<input type="text" name="name" value="${resource.name}"/>
		</div>
		
		<div>
			<label>Descrição:</label>
			<textarea name="description">${resource.description}</textarea>
		</div>
		
		<div>
			<label>Preço:</label>
			<input type="number" name="price" value="${resource.price}"/>
		</div>
		
		<input type="hidden" name="id" value="${resource.id}">
		
		<button>SALVAR</button>
	</form>	
		
		
	<table>
		
		<thead>
			<tr>
				<th>Nome</th>
				<th>Descrição</th>
				<th>Preço</th>
				<th>Ações</th>
			
			</tr>
		</thead>
		
		<tbody>
	
			<c:forEach var="product" items="${resources}">
	
				<tr>
					<td>${product.name}</td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td>
						<a href="<c:url value="/products?id=${product.id}"/>">Editar</a>
						<form action="<c:url value="/products"/>" method="POST" >
							<input name="id" type="hidden" value="${product.id}">
							<input name="action" type="hidden" value="delete">
							<button> Excluir </button>
						</form>
					</td>
				</tr>
	
			</c:forEach>
	
		</tbody>
	
	</table>
	
</body>
</html>