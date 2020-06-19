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

	<h1>Carrinho</h1>

	<form action="payment" method="POST">
		<div>

			<h3>Produtos</h3>

			<table>

				<thead>
					<tr>
						<th>Produto</th>
						<th>Preço</th>
						<th>Quantidade</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="productQuantity" items="${products.entrySet()}">
						<tr>
							<td>${productQuantity.key.name}</td>
							<td>${productQuantity.key.price}</td>
							<td>${productQuantity.value}</td>
						</tr>
					</c:forEach>
				</tbody>

			</table>

		</div>

		<div>
			<label>Total <strong>${total}</strong>
			</label>
		</div>
		
		<button> CONFIRMAR </button>

	</form>

</body>
</html>