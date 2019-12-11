<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

</head>
<body>
	<a href="acessoLiberado.jsp">Início</a>
	<a href="idex.jsp">Sair</a>
	<center>
		<h1>Cadastro de telefones</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>
	<form action="salvarTelefones" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>User:</td>
						<td><input type="text" readonly id="id" name="id"
							value="${userEscolhido.id}" class="field-long"></td>
							
						<td><input type="text" readonly id="nome" name="nome"
							value="${userEscolhido.nome}" class="field-long"></td>
						
					</tr>
					<tr>
						<td>Número:</td>
						<td><input type="text" id="numero" name="numero"></td>
						<td>
							<select id="tipo" name="tipo">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Salvar">
						</td>
					</tr>
				</table>
			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<tr>
				<th>Id</th>
				<th>Número</th>
				<th>Tipo</th>
				<th>Apagar</th>
			</tr>
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>
					<td><c:out value="${user.fone}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${fone.id}"><img
							src="resources/img/excluir.png" alt="excluir" title="Excluir"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			}
			
			return true;

		}
	</script>
</body>
</html>