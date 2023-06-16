<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<title>Administrar turnos</title>
</head>
<body>
	<div class=adm>
	
	 	<h2>Administrar Turnos</h2>
		
		<form method="post" action="ServletHTML">

			<div class="fila">
	          	<div class="input-fila">
		          	<label for="fechaTurno">Buscar Turno</label>
					<input id=BuscarTurno type="search" placeholder="Buscar"  name="Busqueda"></input>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="estado">Estado</label>
					<select id=estado>
					<option disabled selected>Filtrar por estado</option> 
					</select>
	          	</div>
	          	
	          	<div class="input-fila">
					<label for="fechaTurno">Fecha del turno</label>
					<input id=fechaTurno type="date">
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
			     		 <th>MODIFICAR</th> 
			     		 <th>ELIMINAR</th>
		     		</tr>
				</thead>
					
				<tbody>
					<tr>
			     		<th>Perez</th>
			     		<th>Juan</th>
			     		 <th>11.111.111</th>
			     		<th>Suarez</th>
			     		 <th>Maria</th>
			     		 <th>Odontologo</th>
			     		 <th>15/10/2023</th>
			     		 <th>OCUPADO</th>
			     		 <th><input class="botonTabla" id="btnModificarMedico" type="submit" value="Modificar"></th> 
			     		 <th><input class="botonTabla" id="btnEliminarMedico" type="submit" value="Eliminar"></th>
			     	</tr>
				</tbody>

		     	</table>
		     	
		     	<input class="botonAdm" id="btnNuevoTurno" type="submit" value="Nuevo Turno">
	     	</form>	
	</div>
</body>
</html>