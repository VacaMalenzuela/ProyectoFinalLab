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
<title>Login</title>
</head>
<body>
<div class="form-login" >
	<h2>INGRESAR</h2>
	
 	<form method="post" action="ServletHTML">

 		<div class="login">
				<input id=usuarioLogin type="text" maxlenght="15" placeholder="Usuario">
          	</div>
          	
          	<div class="input-t">
				<input id=contrasenaLogin type="password" maxlenght="15" placeholder="Contraseña">
          	</div>

 		
 		<input class="boton" id="btnIngresar" type="submit" value="Ingresar">
 		<a href="MenuPrincipal.jsp">Ingresar</a>
 		
 	</form>
</body>
</html>