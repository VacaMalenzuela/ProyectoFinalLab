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
<title>Nuevo usuario</title>
</head>
<body>
	<div class="form-t" >
	<h2>NUEVO USUARIO ADMINISTRADOR</h2>
	
 	<form method="post" action="ServletHTML">
 		 <div class="input-t">
          	<label for="usuario2">Nuevo Usuario</label>
			<input id=usuario2 type="text" maxlenght="15" placeholder="Ingrese Nombre de Usuario" required name="txtUsuario2">
        </div>
        <div class="input-t">
          	<label for="contrasena2">Nueva Contraseña</label>
			<input id=contrasena2 type="password" maxlenght="15" placeholder="Ingrese Contraseña" required name="txtContrasena2">
        </div>
        <div class="input-t">
          		<label for="contrasena3">Repita Contraseña</label>
				<input id=contrasena3 type="password" maxlenght="15" placeholder="Repita la Contraseña" required name="txtContrasena3">
          	</div>

 		<input class="boton" id="btnGuardarUsuario" type="submit" value="Guardar" required name="btnGuardarUsuario">
 		
 	</form>
 	
 </div>
</body>
</html>