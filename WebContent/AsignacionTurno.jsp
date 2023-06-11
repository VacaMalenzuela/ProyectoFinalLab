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
<title>Insert title here</title>
</head>
<body>
<div class="form-r" >
	<h2>Nuevo Turno</h2>
	
 <form method="post" action="ServletHTML">

          <div class="fila">
          	 <div class="input-fila">
          		<label for="especialidad">Especialidad</label>
				<select id=nacionalidad required name="nacionalidad"> 
				<option disabled selected>Selecciona una Especialidad</option>  
				</select>
          	</div>
          	
          	<div class="input-fila">
          		<label for="medico">Medico/a</label>
				<select id=nacionalidad required name="medico"> 
				<option disabled selected>Selecciona un/a Medico/a</option>  
				</select>
          	</div>
 
          	<div class="input-fila">
          		<label for="horario">Horario</label>
				<select id=horario required name="horario"> 
				<option disabled selected>Selecciona un horario</option>  
				</select>
          	</div>
          	     	<div class="input-fila">
          		<label for="paciente">Paciente</label>
				<select id=paciente required name="paciente"> 
				<option disabled selected>Selecciona un Paciente</option>  
				</select>
          	</div>
          </div>
          
       		<input class="boton" id="btnGuardarMedico" type="submit" value="Guardar" required name="btnGuardarMedico">
    </form>
</div> 
</body>
</html>