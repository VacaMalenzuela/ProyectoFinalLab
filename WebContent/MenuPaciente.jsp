
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="negocio.NacionalidadDao" %>
<%@ page import="negocio.ProvinciaDao" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="negocio.LocalidadDao" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="negocio.pacienteDao" %>
<%@ page import="dominio.Paciente" %>
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

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#datatable').DataTable();
	});
</script>

<title>Administrar Pacientes</title>
</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>

<% if (usuLogueado != null){%>
<header>
	<div class="logo">CLINICA MEDICA</div>
	<div class="hamburger">
		<div class="line"></div>
		<div class="line"></div>
		<div class="line"></div>
	</div>
	</div>
	<h4 Style= "color: #B2BABB;">BIENVENIDO : <%=usuLogueado.getNombre() %> </h4>
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
			<li>
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesi�n</a>
			</li>
		</ul>
	</nav>	
</header>
<div class=adm>

	 	<h2>Administrar Pacientes</h2>
		
		<form method="get" action="ServletPaciente">
<% 
          pacienteDao traeTodos = new pacienteDao();
     ArrayList<Paciente> lstPaciente= traeTodos.obtenerPacientes();
  
    %>
			<table id="datatable" class="tabla">
				<thead>
					<tr>
			     		<th>APELLIDO</th>
			     		<th>NOMBRE</th>
			     		 <th>DNI</th>
			     		 <th>CORREO ELECTRONICO</th>
			     		 <th>TELEFONO</th>
			     		 <th>NACIONALIDAD</th>
			     		 <th>FECHA DE NACIMIENTO</th>
			     		 <th>SEXO</th>
			     		 <th>MODIFICAR</th> 
			     		 <th>ELIMINAR</th>
		     		</tr>
				</thead>
					
				<tbody>
					
						     <%
     if (lstPaciente!=null) 
	 for(Paciente item : lstPaciente) {%>
     <tr>
     	<td> <%=item.getApellido()%></td>   
     	<td><%=item.getNombre()%> </td>  
     	<td><%=item.getDni()%> </td> 
     	<td> <%=item.getCorreoElectronico()%></td>   
     	<td> <%=item.getTelefono()%></td>  
     	<td> <%=item.getNacionalidad().getNacionalidad()%></td>
     	<td> <%=item.getFechaNacimiento()%></td>
     	<td> <%=item.getSexo()%></td>
     	<th><button class="botonTabla" type="submit" name="btnModificarPaciente" value="<%= item.getDni() %>">Modificar</button></th> 
		<th><button class="botonTabla" type="submit" name="btnEliminarPaciente" value="<%= item.getDni() %>">Eliminar</button></th>
     </tr>
     <%} %>
			     		 
			     	
				</tbody>

		     	</table>
		     	
		     	<br><br>
		     		<a href="NuevoPaciente.jsp" style="	width: 150px;
					background: black;
					border: none;
					padding: 12px;
					color: white;
					margin: 15px 0;
					font-size: 16px;
					border: 1px solid black;
					border-radius: 4px; text-decoration:none;">Nuevo Paciente</a>
		 
		     	
		     	
		     	
	     	</form>	
	     	
	     	
	     	<%int filas = 0; 
	     	if (request.getParameter("filasAfectadas")!= null){ 
	     		filas = Integer.parseInt(request.getAttribute("filasAfectadas").toString());
	     	}
	     	if (request.getParameter("filasAfectadas")!= null && filas !=0){ %>
	     		Paciente eliminado Correctamente.
	     	<%} if(request.getParameter("filasAfectadas")!= null && filas ==0){ %>
	     	Hubo un ERROR al eliminar.
	     	<%} %>
	     	
	</div>
	
	<%} else { 
	session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta p�gina.");
	response.sendRedirect("Error.jsp");
	
}%>
</body>
</html>