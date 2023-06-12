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
<title>Administrar Medicos</title>
</head>
<body>

	<div class=adm>
	
	 	<h2>Administrar Médicos</h2>
		
		<form method="post" action="ServletHTML">

			<div class="fila">
	          	<div class="input-fila">
		          	<label for="buscarTurno">Buscar turno</label>
					<input id=BuscarTurno type="search" placeholder="Buscar"  name="Busqueda" required name="buscarTurno"></input>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="especialidad">Filtrar por especialidad</label>
					<select id=especialidad required name="Sespecialidad">
					<option disabled selected>Elegir especialidad</option> 
					</select>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="provincia">Filtrar por provincia</label>
					<select id=provincia required name="Sprovincia">
					<option disabled selected>Elegir provincia</option> 
					</select>
	          	</div>
 	
          	</div>
			
			<table class="tabla">
				<thead>
					<tr>
			     		<th>APELLIDO</th>
			     		<th>NOMBRE</th>
			     		 <th>DNI</th>
			     		 <th>ESPECIALIDAD</th>
			     		 <th>TELEFONO</th>
			     		 <th>MODIFICAR</th> 
			     		 <th>ELIMINAR</th>
		     		</tr>
				</thead>
					
				<tbody>
					<tr>
			     		<th>Perez</th>
			     		<th>Juan</th>
			     		 <th>11.111.111</th>
			     		 <th>Odontologo</th>
			     		 <th>1234563789</th>
			     		 <th><input class="botonTabla" id="btnModificarMedico" type="submit" value="Modificar"></th> 
			     		 <th><input class="botonTabla" id="btnEliminarMedico" type="submit" value="Eliminar"></th>
			     	</tr>
				</tbody>

		     	</table>
		     	
		     	<input class="botonAdm" id="btnNuevoMedico" type="submit" value="Nuevo Medico">
	     	</form>	
	</div>
	
</body>
</html>