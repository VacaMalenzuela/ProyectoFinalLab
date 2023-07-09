<%@ page import="dominio.Turno" %>
<%@ page import="negocio.TurnoDao" %>
<%@ page import="dominio.EstadoTurno" %>
<%@ page import="java.util.ArrayList" %>
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
<title>Turno</title>
</head>
<body>

<%	
Turno objTurno = new Turno();
if(request.getParameter("btnModificarTurno")!= null) {
	TurnoDao turnoNegocio = new TurnoDao();
	objTurno = turnoNegocio.obtenerTurnoPorId(request.getParameter("btnModificarTurno")); 
} %>
<div class="form-r" >
	<h2>Estado Turno</h2>
	
 <form method="post" action="servletObservacionTurno">
 		<div class="input-t">
 			<label for="fechaTurno">Fecha del turno</label>
			<input id=fechaTurno type="date" required name="fechaTurno" value="<%= objTurno.getFecha() %>" disabled>
 		</div>
 		<div class="input-t">
 			<label for="horaTurno">Hora del turno</label>
			<input id=horaTurno type="time" required name="horaTurno" value="<%= objTurno.getHora() %>" disabled>
 		</div>
 		<div class="input-t">
 			<label for="paciente"> Paciente</label>			
			<input id=paciente type="text" required name="paciente" value="<%= objTurno.getPaciente().getDatoGenerales() %>" disabled>
			</div>
 		<div class="input-t">
 			<label for="estado"> Estado</label>
			<select id="estados" required name="estados"> 
				<option disabled selected>Selecciona el estado</option> 
			<% ArrayList<EstadoTurno> listaEstados = new ArrayList<EstadoTurno>();
          				TurnoDao turnoNegocio = new TurnoDao ();
 					listaEstados = turnoNegocio.obtenerEstadosTurnos();
          				if(listaEstados != null){ 	
				for (EstadoTurno objeto : listaEstados) { %>
    			<option value="<%= objeto.getId() %>" <%= (objeto.getId() == objTurno.getEstado().getId()) ? "selected" : "" %>>
      <%= objeto.getEstado() %>
    </option>
  <% } }%> 
  </select>
 		</div>
 		 <div class="input-t">
 			<label for="observacion">Observacion</label>
			<input id="observacion" type="text"  name="observacion"></input>
 		</div>
 		<input class="boton" id="btnGuardarTurno" type="submit" value="Guardar" name="btnGuardarTurno">
    </form>
</div> 
</body>
</html>