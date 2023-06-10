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
<title>nuevo paciente</title>
</head>
<body>
<div class="encabezado"></div> 
 
<div class="parteIzq"></div>

<div class="parteDer" >
	<h1>Agregar nuevo paciente</h1>
	
 <form method="post" action="ServletHTML">

          <p> 
          	<label for="apellido">Apellido</label>
			<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellido">	
          </p>
          <p> 
          	<label for="dni">DNI</label>
			<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni">
          </p> 
          <p> 
          	<label for="mail">Sexo</label>
			<select id=sexo placeholder="Elegir Sexo" required name="sexo"> 
					<option value= "hombre">Hombre</option>
					<option value= "mujer">Mujer</option>
			</select>
          </p> 
          <p> 
          	<label for="nacionalidad">Nacionalidad</label>
			<select id=nacionalidad placeholder="Elegir Nacionalidad" required name="nacionalidad"> 
			</select>
          </p> 
          <p> 
          	<label for="fechaNac">Fecha de Nacimiento</label>
			<input id=fechaNac type="date" required name="fechaNac"> 
          </p> 
          <p> 
          	<label for="direccion">Direccion</label>
			<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccion">
          </p> 
          <p> 
          	<label for="mail">Localidad</label>
			<select id=localidad placeholder="Elegir Localidad" required name="Slocalidad"> 
            </select>
          </p> 
          <p> 
          	<label for="mail">Provincia</label>
			<select id=provincia placeholder="Elegir Provincia" required name="Sprovincia"> 
			</select>
          </p> 
          <p> 
          	<label for="mail">Correo Electronico</label>
			<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmail">
          </p> 
          <p> 
          	<label for="tel">Telefono</label>
			<input id=tel type="text" maxlenght="10" placeholder="Ingrese Telefono" required name="txtTel">
          </p> 
          
          <p>
       		<input id="btnGuardar" type="submit" value="Guardar" required name="btnGuardarPaciente">
      	</p>
                
    </form>

</body>
</html>