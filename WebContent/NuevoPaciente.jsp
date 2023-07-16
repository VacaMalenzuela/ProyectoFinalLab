<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="negocio.NacionalidadDao" %>
<%@ page import="negocio.ProvinciaDao" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="negocio.LocalidadDao" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="dominio.Paciente" %>
<%@ page import="dominio.Usuario" %>
<%@ page import="negocio.pacienteDao" %>
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
<title>Nuevo paciente</title>
</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>

<%if (usuLogueado != null && usuLogueado.getTipo().getId() == 1){%>
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
<%	
Paciente objpaciente = new Paciente();
if(request.getParameter("btnModificarPaciente")!= null) {
	pacienteDao pacDao = new pacienteDao();
	
	objpaciente = pacDao.obtenerPacientePorDni(request.getParameter("btnModificarPaciente"));	
} %>
<div class="form-r" >
<%if (objpaciente.getDni()!= null) { %>
<h2>MODIFICAR PACIENTE</h2>
<%} else {%>
<h2>AGREGAR NUEVO PACIENTE</h2>
<%}%>
	
	
 <form method="post" action="ServletPaciente">



<% if (objpaciente.getDni()!= null){ %>
	      <div class="fila">
          	<div class="input-fila">
          	<label style="color:white;" for="apellido">Apellidos</label>
          	<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellido" value = "<%= objpaciente.getApellido() %>">
   
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="nombre">Nombres</label>
				<input id=nombre type="text" placeholder="Ingrese Nombres" required name="txtNombre" value="<%= objpaciente.getNombre() %>" >
          	
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="dni">DNI</label>
				<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni" value="<%= objpaciente.getDni() %>">

          		
          	</div>
          	
          <div class="input-fila">
          		<label style="color:white;" for="mail">Sexo</label>
          			<select id=sexo required name="sexo"> 
					<option disabled selected>Selecciona un Sexo</option> 
					<option value="hombre" <%= "hombre".equals(objpaciente.getSexo()) ? "selected" : "" %>>Hombre</option>
					<option value="mujer" <%= "mujer".equals(objpaciente.getSexo()) ? "selected" : "" %>>Mujer</option>
				</select>
          		
				
          	</div>



          	<div class="input-fila">
          		<label style="color:white;" for="nacionalidad">Nacionalidad</label>
          	<select id=nacionalidad required name="nacionalidad"> 
				<option disabled selected>Selecciona una nacionalidad</option> 
				<% ArrayList<Nacionalidad> listaNac = new ArrayList<Nacionalidad> ();
          				NacionalidadDao nacNegocio = new NacionalidadDao ();
          				listaNac = nacNegocio.obtenerNacionalidades();
          				if(listaNac != null){ 	
				for (Nacionalidad objeto : listaNac) { %>
				<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objpaciente.getNacionalidad().getId()) ? "selected" : "" %>>
      <%= objeto.getNacionalidad() %>
    </option>
  <% } }%> 
				</select>

          	</div>
          	 
          	<div class="input-fila">
          		<label style="color:white;" for="fechaNac">Fecha de Nacimiento</label>
				<input id=fechaNac type="date" required name="fechaNac" value="<%= objpaciente.getFechaNacimiento() %>" >
          	
          	
          		
          	</div>

          	<div class="input-fila">
          		<label style="color:white;" for="direccion">Direccion</label>
				<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccion" value="<%= objpaciente.getDireccion() %>" >
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Provincia</label>
				<select id=provincia required name="Sprovincia"> 
				<option disabled selected>Selecciona una Provincia</option> 
						<% ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
          				ProvinciaDao provNegocio = new ProvinciaDao ();
          				listaProv = provNegocio.obtenerProvincias();
          				if(listaProv != null){ 	
				for (Provincia objeto : listaProv) { %>
    			<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objpaciente.getProvincia().getId()) ? "selected" : "" %>>
      <%= objeto.getProvincia() %>
    </option>
  <% } }%> 
				</select>
          	</div>
       	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Localidad</label>
				<select id=localidad required name="Slocalidad">
				<option disabled selected>Selecciona una Localidad</option>  
										<% ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
          				LocalidadDao localidadNegocio = new LocalidadDao ();
          				listaLoc = localidadNegocio.obtenerLocalidades();
          				if(listaLoc != null){ 	
				for (Localidad objeto : listaLoc) { %>
    			<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objpaciente.getLocalidad().getId()) ? "selected" : "" %>>
      <%= objeto.getLocalidad() %>
    </option>
  <% } }%> 
				
            	</select>
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Correo Electronico</label>
				<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmail" value="<%= objpaciente.getCorreoElectronico() %>">
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="tel">Telefono</label>
				<input id=tel type="number" maxlenght="20" placeholder="Ingrese Telefono" required name="txtTel" value="<%= objpaciente.getTelefono() %>" >
          	</div>
          	
          	<div class="input-fila">
          	</div>
          	
          </div>
          
       		<input class="boton" id="btnModificarPaciente" type="submit" value="Guardar" required name="btnModificarPaciente">
 <% } else {%>
 
 				
 				
 				
 				
 				
 				
	      <div class="fila">
          	<div class="input-fila">
          	<label style="color:white;" for="apellido">Apellidos</label>
          	<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellido">
   
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="nombre">Nombres</label>
				<input id=nombre type="text" placeholder="Ingrese Nombres" required name="txtNombre">
          	
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="dni">DNI</label>
				<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni" >

          		
          	</div>
          	
          <div class="input-fila">
          		<label style="color:white;" for="mail">Sexo</label>
          			<select id=sexo required name="sexo"> 
					<option disabled selected>Selecciona un Sexo</option> 
					<option value="hombre">Hombre</option>
					<option value="mujer">Mujer</option>
				</select>
          		
				
          	</div>



          	<div class="input-fila">
          		<label style="color:white;" for="nacionalidad">Nacionalidad</label>
          	<select id=nacionalidad required name="nacionalidad"> 
				<option disabled selected>Selecciona una nacionalidad</option>
				<% ArrayList<Nacionalidad> listaNac = new ArrayList<Nacionalidad> ();
          				NacionalidadDao nacNegocio = new NacionalidadDao ();
          				listaNac = nacNegocio.obtenerNacionalidades();
          				if(listaNac != null){ 	
				for (Nacionalidad objeto : listaNac) { %>
				<option value="<%= objeto.getId() %>"> <%= objeto.getNacionalidad() %></option>
				 
  <% } }%> 
				</select>

          	</div>
          	 
          	<div class="input-fila">
          		<label style="color:white;" for="fechaNac">Fecha de Nacimiento</label>
				<input id=fechaNac type="date" required name="fechaNac">
          	
          	
          		
          	</div>

          	<div class="input-fila">
          		<label style="color:white;" for="direccion">Direccion</label>
				<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccion">
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Provincia</label>
				<select id=provincia required name="Sprovincia"> 
				<option disabled selected>Selecciona una Provincia</option> 
						<% ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
          				ProvinciaDao provNegocio = new ProvinciaDao ();
          				listaProv = provNegocio.obtenerProvincias();
          				if(listaProv != null){ 	
				for (Provincia objeto : listaProv) { %>
    			<option value="<%= objeto.getId() %>"><%= objeto.getProvincia() %></option>
  <% } }%> 
				</select>
          	</div>
       	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Localidad</label>
				<select id=localidad required name="Slocalidad">
				<option disabled selected>Selecciona una Localidad</option>  
										<% ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
          				LocalidadDao localidadNegocio = new LocalidadDao ();
          				listaLoc = localidadNegocio.obtenerLocalidades();
          				if(listaLoc != null){ 	
				for (Localidad objeto : listaLoc) { %>
    			<option value="<%= objeto.getId() %>"><%= objeto.getLocalidad() %> </option>
  <% } }%> 
				
            	</select>
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Correo Electronico</label>
				<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmail" >
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="tel">Telefono</label>
				<input id=tel type="number" maxlenght="20" placeholder="Ingrese Telefono" required name="txtTel" >
          	</div>
          	
          	<div class="input-fila">
          	</div>
          	
          </div>
          
       		<input class="boton" id="btnGuardarPaciente" type="submit" value="Guardar" required name="btnGuardarPaciente">
<% } %>
    
    </form>
    
    
    
    
<% int filas=0;
if(request.getAttribute("seGuardo") != null){
	  filas = Integer.parseInt(request.getAttribute("seGuardo").toString());
}
%>


<% if(request.getAttribute("seGuardo") != null && filas == 0){ %>
	Los campos ingresados no son correctos.
	
<%}%>
<%if(filas!=0){ %>
	Usuario agregado correctamente.
<%}%>
    
</div>             
<%} else { 
	session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta página");
	response.sendRedirect("Error.jsp");
	
}%> 
</body>
</html>