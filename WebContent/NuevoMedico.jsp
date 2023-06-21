<%@ page import="dominio.Especialidad" %>
<%@ page import="negocio.EspecialidadDao" %>
<%@ page import="java.util.ArrayList" %>


<%@ page import="dominio.Nacionalidad" %>
<%@ page import="negocio.NacionalidadDao" %>
<%@ page import="negocio.ProvinciaDao" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="negocio.LocalidadDao" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="dominio.Paciente" %>
<%@ page import="negocio.pacienteDao" %>
<%@ page import="dominio.Horarios" %>
<%@ page import="negocio.HorariosDao" %>

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
<title>Nuevo medico</title>
</head>
<body>
<div class="form-r" >
	<h2>AGREGAR NUEVO MEDICO</h2>
	
 <form method="post" action="servletMedico">

          <div class="fila">
          	<div class="input-fila">
          		<label for="apellido">Apellidos</label>
				<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellido">
          	</div>
          	
          	<div class="input-fila">
          		<label for="nombre">Nombres</label>
				<input id=nombre type="text" placeholder="Ingrese Nombres" required name="txtNombre">
          	</div>
          	
          	<div class="input-fila">
          		<label for="dni">DNI</label>
				<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni">
          	</div>

          	<div class="input-fila">	
          		<label for="mail">Sexo</label>
				<select id=sexo required name="sexo"> 
					<option disabled selected>Selecciona un Sexo</option> 
					<option value= "hombre">Hombre</option>
					<option value= "mujer">Mujer</option>
				</select>
          	</div>
          	
          	<div class="input-fila">
          		<label for="nacionalidad">Nacionalidad</label>
				<select id=nacionalidad required name="nacionalidad"> 
				<option disabled selected>Selecciona una Nacionalidad</option>  
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
          		<label for="fechaNac">Fecha de Nacimiento</label>
				<input id=fechaNac type="date" required name="fechaNac">
          	</div>

          	<div class="input-fila">
          		<label for="direccion">Direccion</label>
				<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccion">
          	</div>
          	
          	<div class="input-fila">
          		<label for="mail">Provincia</label>
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
          		<label for="mail">Localidad</label>
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
          		<label for="mail">Correo Electronico</label>
				<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmail">
          	</div>
          	
          	<div class="input-fila">
          		<label for="tel">Telefono</label>
				<input id=tel type="text" maxlenght="10" placeholder="Ingrese Telefono" required name="txtTel">
          	</div>
          	
          	<div class="input-fila">
          		<label for="especialidad">Especialidad</label>
				<select id=especialidad required name="Sespecialidad"> 
				<option disabled selected>Selecciona una Especialidad</option> 
												<% ArrayList<Especialidad> listaEspe = new ArrayList<Especialidad> ();
          				EspecialidadDao espeNegocio = new EspecialidadDao ();
          				listaEspe = espeNegocio.obtenerEspecialidades();
          				if(listaEspe != null){ 	
				for (Especialidad objeto : listaEspe) { %>
				<option value="<%= objeto.getId() %>"> <%= objeto.getEspecialidad() %></option>
				 
  <% } }%> 
				</select>
          	</div>
          	
          <div class = "input-fila">
			<label for="horarios" > Horarios</label>
			
        <% ArrayList<Horarios> lstHorarios = new ArrayList<Horarios>();
		HorariosDao hsNeg = new HorariosDao(); 
		lstHorarios = hsNeg.obtenerHorarios();	
        %>
        
        <%-- Genera los checkboxes para cada registro --%>
        <% for (Horarios registro : lstHorarios) { %>
            <label style="color:white;">
                <input   type="checkbox" name="especialidad" value="<%= registro.getId() %>" />
                <%= registro.getTurno() %>
            </label><br>
        <% } %>
          	</div>
          	
           <div class = "input-fila"></div>
           <div class = "input-fila"></div>
          	
          	<div class="input-fila">
          		<label for="usuario">Nuevo Usuario</label>
				<input id=usuario type="text" maxlenght="15" placeholder="Ingrese Nombre de Usuario" required name="txtUsuario">
          	</div>
          	
          	<div class="input-fila">
          		<label for="contrasena">Nueva Contraseña</label>
				<input id=contrasena type="password" maxlenght="15" placeholder="Ingrese Contraseña" required name="txtContrasena">
          	</div>
          	
          	<div class="input-fila">
          		<label for="contrasena1">Repita Contraseña</label>
				<input id=contrasena1 type="password" maxlenght="15" placeholder="Repita la Contraseña" required name="txtContrasena1">
          	</div>
          	
          </div>
          
       		<input class="boton" id="btnGuardarMedico" type="submit" value="Guardar" required name="btnGuardarMedico">
    </form>
</div>                
</body>
</html>