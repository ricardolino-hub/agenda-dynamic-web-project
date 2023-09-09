<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> contacts = (ArrayList<JavaBeans>) request.getAttribute("contacts");
//for (JavaBeans contact : contacts) {
//	out.println(contact.getName());
//}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Agenda de contatos</title>
<link rel="icon" href="images/telephone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="new" class="button1">Novo contato</a>
	<table id="table">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%for (JavaBeans contact : contacts) {%>
			<tr>
				<td><%=contact.getIdcon()%></td>
				<td><%=contact.getName()%></td>
				<td><%=contact.getFone()%></td>
				<td><%=contact.getEmail()%></td>
				<td><a href="find?idcon=<%=contact.getIdcon()%>" class="button1">Editar</a></td>
			</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>