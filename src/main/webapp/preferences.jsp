<%@page import="clima.web.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Preferencias</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<!-- Navbar aca -->
			<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
				class="navbar-brand" href="#">Aplicacion de clima</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<span class="navbar-text"> 
					<% 	Usuario usuario = (Usuario) session.getAttribute("usuario");%>
					<%= usuario.getName() %>	
				</span>
				<form class="form-inline my-2 my-lg-0">
					<button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Logout</button>
				</form>
			</div>

			</nav>
		</div>

		<div class="row">
			<!-- Contenido de la pagina aqui! -->
			<form>
				<div class="form-group">
					<label for="exampleFormControlInput1">Ciudad seleccionada</label> 
					<input 	type="text" class="form-control" id="exampleFormControlInput1" readonly>
				</div>
				<div class="form-group">
					<label for="exampleFormControlSelect1">Seleccione Ciudad</label> 
					<select class="form-control" id="exampleFormControlSelect1">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="exampleFormControlInput1">Temperatura seleccionada</label> 
					<input 	type="text" class="form-control" id="exampleFormControlInput1" readonly>
				</div>
				<div class="form-group">
					<label for="exampleFormControlSelect1">Seleccione escala de grados</label> 
					<select class="form-control" id="exampleFormControlSelect1">
						<option>Celsius</option>
						<option>Farenheit</option>
					</select>
				</div>
				
			</form>

		</div>

	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>