<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>
	<a href="acessoLiberado.jsp">Início</a>
	<a href="idex.jsp">Sair</a>
	<center>
		<h1>Cadastro de usuários</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>
	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos() ? true : false;" enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly id="id" name="id"
							value="${user.id}" class="field-long"></td>
						
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep" value="${user.cep}" class="field-long"
							onblur="consultaCep();" placeholder="Informe um cep válido"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" class="field-long"></td>
							
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" value="${user.cep}" class="field-long" placeholder="Informe o endereço"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" class="field-long" placeholder="informe a senha"></td>
							
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" value="${user.bairro}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" class="field-long" placeholder="Informe o nome do usuário"></td>
							
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" value="${user.cidade}" class="field-long" placeholder="Informe a cidade do usuário"></td>
					</tr>
					<tr>
						<td>Fone:</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}" class="field-long" placeholder="Informe o número do fone"></td>
							
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado" value="${user.estado}" class="field-long" placeholder="Informe o estado do usuário"></td>
					</tr>
					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge" value="${user.ibge}" class="field-long" placeholder="Informe o IBGE da cidade"></td>
					</tr>
					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto" value="Foto"></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<input type="submit" value="Salvar">
							<input type="submit" value="Cancelar" onclick="documento.getElementById('formUser').action = 'salvarUsuario?acao=reset'">
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
				<th>Foto</th>
				<th>Nome</th>
				<th>Delete</th>
				<th>Update</th>
				<th>Fones</th>
			</tr>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td><c:out value="${user.id}"></c:out></td>
					<td><a href="salvarUsuario?acao=download&user=${user.id}"><img src="<c:out value="${user.tempFotoUser}"></c:out>" alt="Imagem User" title="Imagem User" width="32px" height="32px"></a></td>
					<td><c:out value="${user.nome}"></c:out></td>
					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/excluir.png" alt="excluir" title="Excluir" width="20px" height="20px"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/editar.png" alt="editar" title="Editar" width="20px" height="20px"></a></td>
					<td><a href="salvarTelefones?acao=addFone&user=${user.id}"><img src="resources/img/phone.png" alt="Telefones" title="Telefones" width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {

			if (document.getElementById("login").value == '') {
				alert('Informe o Login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe o Senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("fone").value == '') {
				alert('Informe o Fone');
				return false;
			}
			
			return true;

		}
		
		function consultaCep() {
			var  cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
            $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#estado").val(dados.uf);
                    $("#ibge").val(dados.ibge);
                } //end if.
                else {
                    //CEP pesquisado não foi encontrado.
                    $("#cep").val('');
                    $("#rua").val('');
                    $("#bairro").val('');
                    $("#cidade").val('');
                    $("#estado").val('');
                    $("#ibge").val('');
                    
                    alert("CEP não encontrado.");
                }
            });

		}
	</script>
</body>
</html>