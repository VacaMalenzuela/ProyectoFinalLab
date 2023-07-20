<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="negocio.NacionalidadDao" %>
<%@ page import="negocio.ProvinciaDao" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="negocio.LocalidadDao" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="negocio.pacienteDao" %>
<%@ page import="dominio.Paciente" %>
<%@ page import="negocio.MedicoDao" %>
<%@ page import="dominio.Medico" %>
<%@ page import="negocio.TurnoDao" %>
<%@ page import="dominio.Turno" %>
<%@ page import="dominio.EstadoTurno" %>
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
<title>Administrar turnos</title>
</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>

<%if (usuLogueado == null){session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta página");
response.sendRedirect("Error.jsp");}%>
<%if (usuLogueado.getTipo().getId()== 1){ %>
<header>
	<div class="logo">CLINICA MEDICA</div>
	<div class="hamburger">
		<div class="line"></div>
		<div class="line"></div>
		<div class="line"></div>
	</div>
	<div>
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
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesión</a>
			</li>
		</ul>
	</nav>	
</header>
		<div class=adm>
	
	 	<h2>Administrar Turnos</h2>
		<form method="get" action="servletObservacionTurno">
		

			<div class="fila">
	          	<div class="input-fila">
	          		<label for="estado">Estado</label>
						<select id="estados" required name="estados"> 
				<option disabled selected>Selecciona el estado</option> 
			<% ArrayList<EstadoTurno> listaEstados = new ArrayList<EstadoTurno>();
          				TurnoDao turnoNegocio = new TurnoDao ();
 					listaEstados = turnoNegocio.obtenerEstadosTurnos();
          	if(listaEstados != null){ 	
				for (EstadoTurno objeto : listaEstados) { %>
				<option value="<%= objeto.getId() %>"> <%= objeto.getEstado() %></option>
  <% } }%> 
  </select>
	          	</div>
	          	<div class="input-fila">
					<input class="boton" id="btnFiltraPorEstado" type="submit" value="Filtrar" required name="btnFiltraPorEstado">
					
	          	</div>
 	
          	</div>
			
			<table id="datatable" class="tabla">
				<thead>
					<tr>
			     		<th>APELLIDO PACIENTE</th>
			     		<th>NOMBRE PACIENTE</th>
			     		 <th>DNI PACIENTE</th>
			     		 <th>APELLIDO MEDICO</th>
			     		 <th>NOMBRE MEDICO</th>
			     		 <th>ESPECIALIDAD</th>
			     		 <th>FECHA</th>
			     		 <th>ESTADO</th>
		     		</tr>
				</thead>
					
				<tbody>
					<tr>
			     		<% TurnoDao turNeg = new TurnoDao(); 
			     		ArrayList<Turno> lstTurno;
			     		int idEstadoFiltrar =0;
			     		if (request.getAttribute("estadoSeleccionado") != null){ 
			     			 idEstadoFiltrar = Integer.parseInt(request.getAttribute("estadoSeleccionado").toString()); 
			     			lstTurno = turNeg.obtenerTurnos(idEstadoFiltrar);
			     		} else {
			     			lstTurno = turNeg.obtenerTurnos(idEstadoFiltrar);
			     		}
			     				
    
	 for(Turno item : lstTurno) {%>
     <tr>
     	<td> <%=item.getPaciente().getApellido() %></td>   
     	<td><%=item.getPaciente().getNombre() %> </td>  
     	<td> <%=item.getPaciente().getDni() %></td>   
     	<td> <%=item.getMedico().getApellido() %></td>   
     	<td> <%=item.getMedico().getNombre() %></td>   
     	<td> <%=item.getMedico().getEspecialidad().getEspecialidad() %></td>  
     	<td> <%=item.getFechaTurno() %></td> 
     	<td> <%=item.getEstado().getEstado() %></td> 
     <%} %>
			     	</tr>
				</tbody>

		     	</table>
		     	</form>	
		     	<br><br>
		     		<a href="NuevoTurno.jsp" style="	width: 150px;
					background: black;
					border: none;
					padding: 12px;
					color: white;
					margin: 15px 0;
					font-size: 16px;
					border: 1px solid black;
					border-radius: 4px; text-decoration:none;">Nuevo Turno</a>
	</div>
<%} else {%>
<header>
	<div class="logo">CLINICA MEDICA</div>
	<div class="hamburger">
		<div class="line"></div>
		<div class="line"></div>
		<div class="line"></div>
	</div>
	<div>
	<h4 Style= "color: #B2BABB;">BIENVENIDO : <%=usuLogueado.getNombre() %> </h4>
	</div>
	<nav class="nav-bar">
		<ul>
			<li>
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesión</a>
			</li>
		</ul>
	</nav>	
</header>
	<div class=adm>
	
	 	<h2>Administrar Turnos</h2>
		
		<form method="get" action="servletObservacionTurno">

			<div class="fila">
	          	<div class="input-fila">
	          		<label for="estado">Estado</label>
						<select id="estados" required name="estados"> 
				<option disabled selected>Selecciona el estado</option> 
			<% ArrayList<EstadoTurno> listaEstados = new ArrayList<EstadoTurno>();
          				TurnoDao turnoNegocio = new TurnoDao ();
 					listaEstados = turnoNegocio.obtenerEstadosTurnos();
          	if(listaEstados != null){ 	
				for (EstadoTurno objeto : listaEstados) { %>
				<option value="<%= objeto.getId() %>"> <%= objeto.getEstado() %></option>
  <% } }%> 
  </select>
	          	</div>
	          	<div class="input-fila">
					<input class="boton" id="btnFiltraPorEstado" type="submit" value="Filtrar" required name="btnFiltraPorEstado">
					
	          	</div>
 	
          	</div>
			
			<table id="datatable" class="tabla">
				<thead>
					<tr>
			     		<th>APELLIDO PACIENTE</th>
			     		<th>NOMBRE PACIENTE</th>
			     		 <th>DNI PACIENTE</th>
			     		 <th>FECHA</th>
			     		 <th>ESTADO</th>
			     		 <th>OBSERVACION</th>
			     		 <th>MODIFICAR</th>
		     		</tr>
				</thead>
					
				<tbody>
					<tr>
			     		<% 
			     		MedicoDao medNeg = new MedicoDao(); 
							Medico med = medNeg.ObtenerMedicoPorUsuario(usuLogueado);
			     		TurnoDao turNeg = new TurnoDao(); 
			     		ArrayList<Turno> lstTurno; 
			     		
			     		
			     		int idEstadoFiltrar =0;
			     		if (request.getAttribute("estadoSeleccionado") != null){ 
			     			 idEstadoFiltrar = Integer.parseInt(request.getAttribute("estadoSeleccionado").toString()); 
			     			lstTurno = turNeg.ObtenerTurnosPorMedico(med, idEstadoFiltrar);
			     		} else {
			     			lstTurno= turNeg.ObtenerTurnosPorMedico(med, idEstadoFiltrar); 
			     		}
							
     
	 for(Turno item : lstTurno) {%>
     <tr>
     	<td> <%=item.getPaciente().getApellido() %></td>   
     	<td><%=item.getPaciente().getNombre() %> </td>  
     	<td> <%=item.getPaciente().getDni() %></td>   
     	<td> <%=item.getFechaTurno() %></td> 
     	<td> <%=item.getEstado().getEstado() %></td>
     	<td> <%=item.getObservacion() %></td> 
     	<th><button class="botonTabla" type="submit" name="btnModificarTurno" value="<%= item.getId() %>" >Modificar</button></th> 
     	</tr>
     <%} %>
				</tbody>

		     	</table>
	     	</form>	
	</div>
<%} %>






</body>
</html>