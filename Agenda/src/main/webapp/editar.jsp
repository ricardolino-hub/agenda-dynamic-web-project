<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="images/telephone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar contato</h1>
	<form id="frmContact" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" class="box1" id="box3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="name" class="box1" value="<%out.print(request.getAttribute("name"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="box2" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>

		<input type="button" value="Salvar" class="button1"
			onclick="validator()">

		<script src="scripts/validator.js"></script>
	</form>
</body>
</html>