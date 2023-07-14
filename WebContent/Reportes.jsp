<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
</head>
<body>
<div class=adm>
	<h2>Reportes</h2>

	<form method="get" action="servletReportes">
		<div class="fila">
			<div class="input-fila">
				<label for="desde">Desde</label>
				<input id=desde type="date">
			</div>
			          	
			<div class="input-fila">
			    <label for="hasta">Hasta</label>
				<input id=hasta type="date">
	 		</div>
	 		<div class="input-fila">
	 			<input class="boton" id="btnReporte" type="submit" value="Listar turnos">
	 		</div>
		</div>
		
		<table class="tabla">
				<thead>
					<tr>
			     		<th>APELLIDO PACIENTE</th>
			     		<th>NOMBRE PACIENTE</th>
			     		 <th>DNI PACIENTE</th>
			     		 <th>APELLIDO MEDICO</th>
			     		 <th>NOMBRE MEDICO</th>
			     		 <th>ESPECIALIDAD</th>
			     		 <th>FECHA</th>
			     		 <th>ESTADO</th>
		     		</tr>
				</thead>
	</form>
</div>
	
</body>
</html>