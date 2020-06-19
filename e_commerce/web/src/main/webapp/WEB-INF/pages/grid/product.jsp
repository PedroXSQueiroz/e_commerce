<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h1>Produtos</h1>
	
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
						<c:if test="${!productsInCart.containsKey(product)}">
							<form method="POST" action="shopping-cart?productId=${product.id}&action=add"> 
								<button>Adicionar ao carrinho</button>
							</form>
						</c:if>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>
	
	<table>
		
		<thead>
			<tr>
				<th>Nome</th>
				<th>Preço</th>
				<th>Ações</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="productQuantity" items="${productsInCart.entrySet()}">

				<tr>
						<td>${productQuantity.key.name}</td>
						<td>${productQuantity.key.price}</td>
						
						<td>						
							<form method="POST" action="shopping-cart?productId=${productQuantity.key.id}&action=remove"> 
								<button>Remover</button>
							</form>
						</td>
						
						<td>
							${productQuantity.value}
						</td>
						
						<td>						
							<form method="POST" action="shopping-cart?productId=${productQuantity.key.id}&action=add">
							<button>Adicionar</button>
							</form>
						</td>
						
				</tr>

			</c:forEach>

		</tbody>
	</table>
	
	<a href="<c:url value="/shopping-cart"/>">Finalizar compra</a>

</body>
</html>