<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar turno</title>
</head>
<body>
<div class="form-t" >
	<h2>MODIFICAR TURNO</h2>
	
 	<form method="post" action="ServletHTML">
 		<div class="input-t">
 			<label for="especialidad">Especialidad</label>
			<select id=especialidad required name="Sespecialidad">
			<option disabled selected>Selecciona una Especialidad</option> 
			</select>
 		</div>
 		<div class="input-t">
 			<label for="medico">Buscar Medico</label>
			<input id=medico type="search" placeholder="Ingrese nombre del Medico"  name="BusquedaMedico" required name="medico"></input>
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
 			<label for="paciente">Busqueda Paciente</label>
			<input id=paciente type="search" placeholder="Ingrese DNI del paciente"  name="BusquedaPaciente" required name="paciente"></input>
 		</div>
 		
 		<input class="boton" id="btnGuardarTurno" type="submit" value="Guardar" required name="btnGuardarTurno">
 		
 	</form>
 	
 </div>
</body>
</html>