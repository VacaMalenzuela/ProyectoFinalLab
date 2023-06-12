<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>/Modificar paciente</title>
</head>
<body>
<div class="form-r" >
	<h2>MODIFICAR PACIENTE</h2>
	
 <form method="post" action="ServletHTML">

          <div class="fila">
          	<div class="input-fila">
          		<label for="apellido">Apellidos</label>
				<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellido">
          	</div>
          	
          	<div class="input-fila">
          		<label for="nombre">Nombres</label>
				<input id=nombre type="text" placeholder="Ingrese Nombres" required name="txtNombre">
          	</div>
          	
          	<div class="input-fila">
          		<label for="dni">DNI</label>
				<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni">
          	</div>
          	
          <div class="input-fila">
          		<label for="mail">Sexo</label>
				<select id=sexo required name="sexo"> 
					<option disabled selected>Selecciona un Sexo</option> 
					<option value= "hombre">Hombre</option>
					<option value= "mujer">Mujer</option>
				</select>
          	</div>
          	
          	<div class="input-fila">
          		<label for="nacionalidad">Nacionalidad</label>
				<select id=nacionalidad required name="nacionalidad"> 
				<option disabled selected>Selecciona una Nacionalidad</option>  
				</select>
          	</div>
          	
          	<div class="input-fila">
          		<label for="fechaNac">Fecha de Nacimiento</label>
				<input id=fechaNac type="date" required name="fechaNac">
          	</div>

          	<div class="input-fila">
          		<label for="direccion">Direccion</label>
				<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccion">
          	</div>
          	
          	<div class="input-fila">
          		<label for="mail">Provincia</label>
				<select id=provincia required name="Sprovincia"> 
				<option disabled selected>Selecciona una Provincia</option> 
				</select>
          	</div>
       	
          	<div class="input-fila">
          		<label for="mail">Localidad</label>
				<select id=localidad required name="Slocalidad">
				<option disabled selected>Selecciona una Localidad</option>  
            	</select>
          	</div>
          	
          	<div class="input-fila">
          		<label for="mail">Correo Electronico</label>
				<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmail">
          	</div>
          	
          	<div class="input-fila">
          		<label for="tel">Telefono</label>
				<input id=tel type="number" maxlenght="10" placeholder="Ingrese Telefono" required name="txtTel">
          	</div>
          	
          	<div class="input-fila">
          	</div>
          	
          </div>
          
       		<input class="boton" id="btnGuardarPaciente" type="submit" value="Guardar" required name="btnGuardarPaciente">
    </form>
</div>   
</body>
</html>