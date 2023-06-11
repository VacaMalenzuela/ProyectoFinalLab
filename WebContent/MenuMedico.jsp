<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
		.etiquetaA {
	color:white;
	text-decoration: none;
  background-color: #909497;
  padding:5px;
  border-radius: 5px; 
  font-size: 16px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div style="width:100%; height:400px; text-align:center;"> 
<h1>Administrar Medicos</h1>
</div>

<div style="text-align:rigth;height:210px; width:210px;"> 
<br><br>
	<a href="NuevoMedico.jsp" class="etiquetaA">Nuevo Medico</a>
</div>
<div> 
<table border="1">
     <tr>
     	<th>NOMBRE</th> <th>APELLIDO</th> <th>Modif.</th> <th>Eliminar</th>
     </tr>
     <tr>
     	<td> pepito </td>   <td>pepito </td>  <td> Modificar</td>   <td> Eliminar</td> 
     </tr>
     </table>
</div>
</body>
</html>