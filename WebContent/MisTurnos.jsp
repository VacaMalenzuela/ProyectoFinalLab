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
<title>Administrar turnos</title>
</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>


<%if (usuLogueado.getTipo().getId()== 1){ %>
		<div class=adm>
	
	 	<h2>Administrar Turnos</h2>
		
		

			<div class="fila">
	          	<div class="input-fila">
		          	<label for="fechaTurno">Buscar Turno</label>
					<input id=BuscarTurno type="search" placeholder="Buscar"  name="Busqueda"></input>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="estado">Estado</label>
					<select id=estado>
					<option disabled selected>Filtrar por estado</option> 
					</select>
	          	</div>
	          	
	          	<div class="input-fila">
					<label for="fechaTurno">Fecha del turno</label>
					<input id=fechaTurno type="date">
	          	</div>
 	
          	</div>
			
			<table class="tabla">
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
			     		ArrayList<Turno> lstTurno = turNeg.obtenerTurnos(); 
							
     if (lstTurno!=null) 
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
	<div class=adm>
	
	 	<h2>Administrar Turnos</h2>
		
		<form method="get" action="servletObservacionTurno">

			<div class="fila">
	          	<div class="input-fila">
		          	<label for="fechaTurno">Buscar Turno</label>
					<input id=BuscarTurno type="search" placeholder="Buscar"  name="Busqueda"></input>
	          	</div>
	          	
	          	<div class="input-fila">
	          		<label for="estado">Estado</label>
					<select id=estado>
					<option disabled selected>Filtrar por estado</option> 
					</select>
	          	</div>
	          	
	          	<div class="input-fila">
					<label for="fechaTurno">Fecha del turno</label>
					<input id=fechaTurno type="date">
	          	</div>
 	
          	</div>
			
			<table class="tabla">
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
			     		ArrayList<Turno> lstTurno = turNeg.ObtenerTurnosPorMedico(med); 
							
     if (lstTurno!=null) 
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
	
	
	
	<div>
	
	<h4 Style= "color: #B2BABB; text-align:center; font-family: Abadi Extra Light; ">BIENVENIDO : <%= med.getDatoGenerales() %> </h3>
	</div>
<%} %>






</body>
</html>