<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Turno" %>
<%@ page import="dominio.Usuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reportes</title>
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

</head>
<body>
<%Usuario usuLogueado = (Usuario)session.getAttribute("usuarioLogueado");%>

<% if (usuLogueado != null && usuLogueado.getTipo().getId()== 1){%>


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
				<a href="Login.jsp" class="active" style="font-size: 10px; color: green;" >Cerrar sesi�n</a>
			</li>
		</ul>
	</nav>	
</header>
<div class=adm>

	<h2>Reportes</h2>

	<form method="get" action="ServletReportes">
	
			<% 
		String ocupados = "0"; 
		String ausentes = "0";
		String presentes = "0";
		if (request.getAttribute("cantidadOcupados") != null){ 
			ocupados = request.getAttribute("cantidadOcupados").toString();
		}
		if (request.getAttribute("cantidadPresentes") != null){ 
			presentes = request.getAttribute("cantidadPresentes").toString();
		}
		if (request.getAttribute("cantidadAusentes") != null){ 
			ausentes = request.getAttribute("cantidadAusentes").toString();
		}
		%>
		<div class="fila">
			<div class="input-fila">
				<label for="desde">Desde</label>
				<input id=desde type="date" required name="desde">
			</div>
			          	
			<div class="input-fila">
			    <label for="hasta">Hasta</label>
				<input id=hasta type="date" required name="hasta">
	 		</div>
	 		<div class="input-fila">
	 			<input class="botonAdm" id="btnReporte" type="submit" value="Listar turnos" name="btnReporte" >
	 		</div>
		</div>
		
		<% 
		ArrayList<Turno> lstTurno = null; 
		if (request.getAttribute("listaT") != null){
		lstTurno = (ArrayList<Turno>) request.getAttribute("listaT");
		} %>  		
		

		
		<div class=form-r>
			<h2>Cantidad de turnos</h2>
			<div class=fila>
				<label for="ocupados" style="color: blue; font-size:20px">OCUPADOS: </label> <p><%= ocupados %> </p>
				<label for="ausentes" style="color: red; font-size:20px">AUSENTES: </label> <p> <%= ausentes %></p>
				<label for="presentes" style="color: green; font-size:20px">PRESENTES: </label> <p><%= presentes %> </p>
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
			     		<%	
     					if (lstTurno != null)
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
				     </tr>
				     <%} %>
				</tbody>
			</table>
	</form>
</div>
<%} else {  
		session.setAttribute("ErrorSession", "Error debes loguearte/no puede acceder a esta p�gina.");
		response.sendRedirect("Error.jsp");
	}%>	
</body>
</html>