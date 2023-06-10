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
<title>Nuevo turno</title>
</head>
<body>
<div class="form-t" >
	<h2>NUEVO TURNO</h2>
	
 	<form method="post" action="ServletHTML">
 		<div class="input-t">
 			<label for="especialidad">Especialidad</label>
			<select id=especialidad placeholder="Elegir Especialidad" required name="Sespecialidad"> 
			</select>
 		</div>
 		<div class="input-t">
 			<label for="medico">Nombre Medico</label>
			<input id=medico type="search" name="BusquedaMedico" required name="medico"></input>
 		</div>
 		<div class="input-t">
 			<label for="fechaTurno">Fecha del turno</label>
			<input id=fechaTurno type="date" required name="fechaTurno">
 		</div>
 		<div class="input-t">
 			<label for="horaTurno">Hora del turno</label>
			<input id=horaTurno type="time" name="Hora" required name="horaTurno"></input>
 		</div>
 		<div class="input-t">
 			<label for="paciente">DNI Paciente</label>
			<input id=paciente type="search" name="BusquedaPaciente" required name="paciente"></input>
 		</div>
 		
 		<input class="boton" id="btnGuardarTurno" type="submit" value="Guardar" required name="btnGuardarTurno">
 		
 	</form>
 	
 </div>
</body>
</html>