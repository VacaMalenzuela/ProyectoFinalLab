<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Paciente" %>
<%@ page import="negocio.pacienteDao" %>
<%@ page import="dominio.Especialidad" %>
<%@ page import="negocio.EspecialidadDao" %>
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
<title>Nuevo turno</title>
</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");
if (usuLogueado == null){session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta página");
response.sendRedirect("Error.jsp");}
if (usuLogueado.getTipo().getId()==1 ){ %>

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
			<li>
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesión</a>
			</li>
		</ul>
	</nav>	
</header>
<%} else {%>
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
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesión</a>
			</li>
		</ul>
	</nav>	
</header>
<%}%>

<div class="form-t" >
	<h2>NUEVO TURNO</h2>
	
 	<form method="post" action="servletTurno">
 		<div class="input-t">
 			<label for="especialidad">Especialidad</label>
			<select id=especialidad required name="especialidad">
			<option disabled selected>Selecciona una Especialidad</option> 
				<% ArrayList<Especialidad> listaEspecialidad = new ArrayList<Especialidad> ();
 				EspecialidadDao especialidadDao = new EspecialidadDao ();
 			    listaEspecialidad = especialidadDao.obtenerEspecialidades();	
				for (Especialidad objeto : listaEspecialidad) { %>
				<option value="<%= objeto.getId() %>"> <%= objeto.getEspecialidad() %></option><%}%>
			</select>
			
 		</div>
 		<div class="input-t">
 			<label for="medico">Buscar Medico</label>
			<select id="medico" required name="medico">
				<option disabled selected>Selecciona un medico</option> 
				
				<%  MedicoDao medNeg = new MedicoDao(); 
				ArrayList<Medico> lstMedicos = medNeg.obtenerMedicos();  
	  			  for(Medico med : lstMedicos){  %>
	  				  
	  				  <option value="<%= med.getDni() %>"> <%= med.getApellido() + ", "+ med.getNombre() %></option>
	  			  <%} %>

			</select>
 		</div>
 		<div class="input-t">
 			<label for="fechaTurno">Fecha del turno</label>
			<input id="fechaTurno" type="date" required name="fechaTurno">
 		</div>
 		<div class="input-t">
 			<label for="horaTurno">Hora del turno</label>
			<input id="horaTurno" type="time" required name="horaTurno"></input>
 		</div>
 		<div class="input-t">
 			<label for="paciente">Busqueda Paciente</label>
			<select id=especialidad required name="SPaciente">
			<option disabled selected>Selecciona un paciente</option> 
					<%ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();
          			pacienteDao pacNegocio = new pacienteDao ();
 					listaPaciente = pacNegocio.obtenerPacientes();	
				for (Paciente objeto : listaPaciente) { %>
				<option value="<%= objeto.getDni() %>"> <%= objeto.getDatoGenerales() %></option><%}%> 
			</select>
 		</div>
 		<input class="boton" id="btnGuardarTurno" type="submit" value="Guardar"  name="btnGuardarTurno">
 	</form>
 </div>
 
  	<%
 	int valorParametro = 0;
 	
 	if (request.getAttribute("noCoincideMedicoEspecialidad")!= null) {%>
 		<script>alert("El medico Seleccionado no trabaja para la especialidad seleccionada.");</script>
 	<%}  %>
 	
 	
 	  	<%
 	int valorParametroDias = 0;
 	
 	if (request.getAttribute("noCoincideHorarioPorMedico")!= null) {%>
 		<script>alert("El medico No trabaja en ese dia/hora seleccionada.");</script>
 	<%}  %>
 	
 	 	  	<%
 	int valorParametroTurnoEnDiaSelec = 0;
 	
 	if (request.getAttribute("TieneTurnoEnHorarioSeleccionado")!= null) {%>
 		<script>alert("El medico ya tiene turnos asignados en el horario seleccionado.");</script>
 	<%}  %>
 	
 	<% if (request.getAttribute("SeAgregoCorrectamente")!= null) { %>
 		<script>alert("El turno se agrego correctamente.");</script>
 	<% } %>
 	
</body>
</html>