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
	
 	<form method="post" action="ServletLogin">

 		<div class="login">
				<input id="usuarioLogin" name ="txtUsuarioLogin" type="text" maxlenght="15" placeholder="Usuario">
          	</div>
          	
          	<div class="input-t">
				<input id="contrasenaLogin" name ="txtClaveLogin"type="password" maxlenght="15" placeholder="Contraseņa">
          	</div>

 		
 		<input class="boton" id="btnIngresar" type="submit" value="Ingresar" required name="btnIngresar">
 		
 		
 	</form>
 	
<% int ingresoCorrectamente=1;
if(request.getAttribute("errorAlIngresar") != null){
	ingresoCorrectamente = Integer.parseInt(request.getAttribute("errorAlIngresar").toString());
}
%>


<% if(request.getAttribute("errorAlIngresar") != null && ingresoCorrectamente == 0){ %>
	<p style = "color: red;">Usuario/contraseņa incorrecta. </p>
	
<%}%>
 	
</body>
</html>