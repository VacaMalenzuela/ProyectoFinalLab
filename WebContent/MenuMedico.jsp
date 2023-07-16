<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Medico" %>
<%@ page import="negocio.MedicoDao" %>
<%@ page import="dominio.Usuario" %>
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
<% Usuario us =  (Usuario)session.getAttribute("usuarioLogueado");
if (us != null && us.getTipo().getId()==1){ %>
<header>
	<div class="logo">CLINICA MEDICA</div>
	<div class="hamburger">
		<div class="line"></div>
		<div class="line"></div>
		<div class="line"></div>
	</div>
	<nav class="nav-bar">
		<ul>
			<li>
				<a href="MisTurnos.jsp" class="active">Turnos</a>
			</li>
			<li>
				<a href="MenuMedico.jsp" class="active">Medicos</a>
			</li>
			<li>
				<a href="MenuPaciente.jsp" class="active">Pacientes</a>
			</li>
			<li>
				<a href="Reportes.jsp" class="active">Reportes</a>
			</li>
		</ul>
	</nav>	
</header>	
<div class=adm>
	
	 	<h2>Administrar Médicos</h2>
		
		<form method="get" action="servletMedico">
		
		<div class="fila">
	          	<div class="input-fila">
		          	<label for="buscarTurno">Buscar turno</label>
					<input id=BuscarTurno type="search" placeholder="Buscar"  name="Busqueda"></input>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="especialidad">Filtrar por especialidad</label>
					<select id=especialidad>
					<option disabled selected>Elegir especialidad</option> 
					</select>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="provincia">Filtrar por provincia</label>
					<select id=provincia>
					<option disabled selected>Elegir provincia</option> 
					</select>
	          	</div>
 	
          	</div>

<% 
          MedicoDao traeTodos = new MedicoDao();
     ArrayList<Medico> lstMedico= traeTodos.obtenerMedicos();
    %>
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
						     <%
     if (lstMedico!=null) 
	 for(Medico item : lstMedico) {%>
     <tr>
     	<td> <%=item.getApellido()%></td>   
     	<td><%=item.getNombre()%> </td>  
     	<td><%=item.getDni()%> </td> 
     	<td> <%=item.getEspecialidad().getEspecialidad() %></td>   
     	<td> <%=item.getTelefono()%></td>  
     	<th><button class="botonTabla" type="submit" name="btnModificarMedico" value="<%= item.getDni() %>">Modificar</button></th> 
     	
     	 <th><button class="botonTabla" type="submit" name="btnEliminarMedico" value="<%= item.getDni() %>">Eliminar</button></th>
     </tr>
     <%} %>
				</tbody>

		     	</table>
		     	<br><br>
		     	  <a href="NuevoMedico.jsp" style="	width: 150px;
					background: black;
					border: none;
					padding: 12px;
					color: white;
					margin: 15px 0;
					font-size: 16px;
					border: 1px solid black;
					border-radius: 4px; text-decoration:none;">Nuevo Medico</a>
	     	</form>	
	</div>
	<%} else {  
		session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta página.");
		response.sendRedirect("Error.jsp");
	}%>
</body>
</html>