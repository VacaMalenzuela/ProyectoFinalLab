<%@ page import="dominio.Especialidad" %>
<%@ page import="negocio.EspecialidadDao" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="dominio.Usuario" %>
<%@ page import="dominio.Nacionalidad" %>
<%@ page import="negocio.NacionalidadDao" %>
<%@ page import="negocio.ProvinciaDao" %>
<%@ page import="dominio.Provincia" %>
<%@ page import="negocio.LocalidadDao" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="dominio.Medico" %>
<%@ page import="negocio.MedicoDao" %>
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

<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>

<%if (usuLogueado != null && usuLogueado.getTipo().getId() == 1){%>
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
<%	
Medico objMedico = new Medico();
if(request.getParameter("btnModificarMedico")!= null) {
	MedicoDao medDao = new MedicoDao();
	objMedico = medDao.obtenerMedicoPorDni(request.getParameter("btnModificarMedico"));	
} %>


<div class="form-r" >
<%if(objMedico.getDni()== null){  %>
		<h2>AGREGAR NUEVO MEDICO</h2>
<%} else { %>
<h2>MODIFICAR MEDICO</h2>
<%}%>

	
 <form method="post" action="servletMedico">


	<%if(objMedico.getDni() == null){%>
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
					<option value= "M">Hombre</option>
					<option value= "F">Mujer</option>
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
                <input   type="checkbox" name="horarios" value="<%= registro.getId() %>" />
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
	<% } if (objMedico.getDni()!= null){ %>
	
	<div class="fila">
          	<div class="input-fila">
          	<label style="color:white;" for="apellido">Apellidos</label>
          	<input id=apellido type="text" placeholder="Ingrese Apellidos" required name="txtApellidoModif" value = "<%= objMedico.getApellido() %>">
   
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="nombre">Nombres</label>
				<input id=nombre type="text" placeholder="Ingrese Nombres" required name="txtNombreModif" value="<%= objMedico.getNombre() %>" >
          	
          		
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="dni">DNI</label>
				<input id=dni type="text" maxlenght="8" placeholder="Ingrese DNI" required name="txtDni" value="<%= objMedico.getDni() %>">

          		
          	</div>
          	
          <div class="input-fila">
          		<label style="color:white;" for="mail">Sexo</label>
          			<select id=sexo required name="sexoModif"> 
					<option disabled selected>Selecciona un Sexo</option> 
					<option value="M" <%= "M".equals(objMedico.getSexo()) ? "selected" : "" %>>Hombre</option>
					<option value="F" <%= "F".equals(objMedico.getSexo()) ? "selected" : "" %>>Mujer</option>
				</select>
          		
				
          	</div>



          	<div class="input-fila">
          		<label style="color:white;" for="nacionalidad">Nacionalidad</label>
          	<select id=nacionalidad required name="nacionalidadModif"> 
				<option disabled selected>Selecciona una nacionalidad</option> 
				<% ArrayList<Nacionalidad> listaNac = new ArrayList<Nacionalidad> ();
          				NacionalidadDao nacNegocio = new NacionalidadDao ();
          				listaNac = nacNegocio.obtenerNacionalidades();
          				if(listaNac != null){ 	
				for (Nacionalidad objeto : listaNac) { %>
				<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objMedico.getNacionalidad().getId()) ? "selected" : "" %>>
      <%= objeto.getNacionalidad() %>
    </option>
  <% } }%> 
				</select>

          	</div>
          	 
          	<div class="input-fila">
          		<label style="color:white;" for="fechaNac">Fecha de Nacimiento</label>
				<input id=fechaNac type="date" required name="fechaNacModif" value="<%= objMedico.getFechaNacimiento() %>" >
          	
          	
          		
          	</div>

          	<div class="input-fila">
          		<label style="color:white;" for="direccion">Direccion</label>
				<input id=direccion type="text" placeholder="Ingrese Direccion" required name="txtDireccionModif" value="<%= objMedico.getDireccion() %>" >
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Provincia</label>
				<select id=provincia required name="SprovinciaModif"> 
				<option disabled selected>Selecciona una Provincia</option> 
						<% ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
          				ProvinciaDao provNegocio = new ProvinciaDao ();
          				listaProv = provNegocio.obtenerProvincias();
          				if(listaProv != null){ 	
				for (Provincia objeto : listaProv) { %>
    			<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objMedico.getProvincia().getId()) ? "selected" : "" %>>
      <%= objeto.getProvincia() %>
    </option>
  <% } }%> 
				</select>
          	</div>
       	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Localidad</label>
				<select id=localidad required name="SlocalidadModif">
				<option disabled selected>Selecciona una Localidad</option>  
										<% ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
          				LocalidadDao localidadNegocio = new LocalidadDao ();
          				listaLoc = localidadNegocio.obtenerLocalidades();
          				if(listaLoc != null){ 	
				for (Localidad objeto : listaLoc) { %>
    			<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objMedico.getLocalidad().getId()) ? "selected" : "" %>>
      <%= objeto.getLocalidad() %>
    </option>
  <% } }%> 
				
            	</select>
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="mail">Correo Electronico</label>
				<input id=mail type="email" placeholder="Ingrese Correo electrronico" required name="txtEmailModif" value="<%= objMedico.getCorreoElectronico() %>">
          	</div>
          	
          	<div class="input-fila">
          		<label style="color:white;" for="tel">Telefono</label>
				<input id=tel type="number" maxlenght="20" placeholder="Ingrese Telefono" required name="txtTelModif" value="<%= objMedico.getTelefono() %>" >
          	</div>
          	
          	<div class="input-fila">
          		<label for="especialidad">Especialidad</label>
				<select id=especialidad required name="SespecialidadModif"> 
				<option disabled selected>Selecciona una Especialidad</option> 
												<% ArrayList<Especialidad> listaEspe = new ArrayList<Especialidad> ();
          				EspecialidadDao espeNegocio = new EspecialidadDao ();
          				listaEspe = espeNegocio.obtenerEspecialidades();
          				if(listaEspe != null){ 	
				for (Especialidad objeto : listaEspe) { %>
				<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objMedico.getEspecialidad().getId()) ? "selected" : "" %>> <%= objeto.getEspecialidad() %></option>
				 
  <% } }%> 
				</select>
          	</div>
          	
          <div class = "input-fila">
			<label for="horariosModif" > Horarios</label>
			
        <% ArrayList<Horarios> lstHorarios = new ArrayList<Horarios>();
		HorariosDao hsNeg = new HorariosDao(); 
		lstHorarios = hsNeg.obtenerHorarios();	
        %>
        
        <%-- Genera los checkboxes para cada registro --%>
        <% for (Horarios registro : lstHorarios) { %>
            <label style="color:white;">
            <input type="checkbox" name="horariosModif" value="<%= registro.getId() %>" <% if (objMedico.getHorarios().contains(registro)== true) { %> checked <% } %>> <%= registro.getTurno() %>
            </label><br>
        <% } %>
          	</div>
          	
           <div class = "input-fila"></div>
           <div class = "input-fila"></div>
          	
          	
          </div>
          	<input class="boton" id="btnModificarMedico" type="submit" value="Guardar" name="btnModificarMedico">
          
       	<% }%>
	 </form>
</div> 


  	<%
 	int valorParametro = 0;
 	
 	if (request.getAttribute("ContrasenasDistintas")!= null) {%>
 		<%--<p style= "color: red;">Las contraseñas no coinciden.</p>--%>
 		<script>alert("Las contraseñas no coinciden.");</script>
 		
 	<%}  %>        
 	
 	  	<%
 	int valorParametro1 = 0;
 	
 	if (request.getAttribute("MedicoAgregado")!= null) {
 		valorParametro1 = Integer.parseInt(request.getAttribute("MedicoAgregado").toString()); ; 
 		if (valorParametro1 != 0){ %>
 		<script>alert("Medico agregado correctamente.");</script>
 		<%} 
 		
 	}  %>     
 	
 	
 	 	  	<%
 	int valorParametro2 = 0;
 	
 	if (request.getAttribute("YaExisteDni")!= null) {
 		valorParametro2 = Integer.parseInt(request.getAttribute("YaExisteDni").toString()); ; 
 		if (valorParametro1 != 0){ %>
 		<%--<p style= "color: red;">El dni ingresado ya existe.</p> --%>
 		<script>alert("El dni ingresado ya existe.");</script>
 		<%} 
 		
 	}  %>  
 	

 	<%} else { 
	session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta página");
	response.sendRedirect("Error.jsp");
	
}%>  
</body>
</html>