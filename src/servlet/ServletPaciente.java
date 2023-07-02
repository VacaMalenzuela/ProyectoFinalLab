package servlet;
import java.lang.reflect.Field;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Nacionalidad;
import negocio.NacionalidadDao;

import dominio.Paciente;
import negocio.pacienteDao;

import dominio.Provincia;
import negocio.ProvinciaDao;
import dominio.Especialidad;
import dominio.Localidad;
import negocio.EspecialidadDao;
import negocio.LocalidadDao;
@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPaciente() {
        super();   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Paciente objpaciente = new Paciente();
		if(request.getParameter("btnModificarPaciente")!= null) {
			pacienteDao pacDao = new pacienteDao();
			objpaciente = pacDao.obtenerPacientePorDni(request.getParameter("btnModificarPaciente"));
			RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnEliminarPaciente")!= null) {
			pacienteDao pacDao = new pacienteDao();
			int filasAfectadas = 0; 
			filasAfectadas = pacDao.EliminarPaciente(request.getParameter("btnEliminarPaciente"));
			request.setAttribute("filasAfectadas", filasAfectadas);
			RequestDispatcher rd = request.getRequestDispatcher("/MenuPaciente.jsp");
			rd.forward(request, response);
		}
		 		 
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seGuardo =0;
		try {
			
			
			if(request.getParameter("btnGuardarPaciente") != null){

				Paciente pac = new Paciente(); 
				pac.setApellido(request.getParameter("txtApellido"));
				pac.setNombre(request.getParameter("txtNombre"));
				pac.setDni(request.getParameter("txtDni"));
				pac.setSexo(request.getParameter("sexo"));
					NacionalidadDao nacNegocio = new NacionalidadDao(); 
					Nacionalidad nacionalidad = nacNegocio.obtenerNacionalidadPorId(Integer.parseInt(request.getParameter("nacionalidad")));
				pac.setNacionalidad(nacionalidad);
				pac.setFechaNacimiento(request.getParameter("fechaNac"));
				pac.setDireccion(request.getParameter("txtDireccion"));
					ProvinciaDao provNegocio = new ProvinciaDao(); 
					Provincia provincia = provNegocio.obtenerProvinciaPorId(Integer.parseInt(request.getParameter("Sprovincia")));	
					pac.setProvincia(provincia);
					LocalidadDao locNegocio = new LocalidadDao(); 
					Localidad loc = locNegocio.obtenerLocalidadPorId(Integer.parseInt(request.getParameter("Slocalidad")));	
					pac.setLocalidad(loc);
				pac.setCorreoElectronico(request.getParameter("txtEmail"));
				pac.setTelefono(request.getParameter("txtTel"));
								
				
				if (ValidoCamposPaciente(pac)) { 
					pacienteDao pacNeg = new pacienteDao(); 
					seGuardo = pacNeg.agregarPaciente(pac);
					request.setAttribute("seGuardo", seGuardo);
				} 
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
				rd.forward(request, response);

			}
			
			if(request.getParameter("btnModificarPaciente")!= null) {
				Paciente pac = new Paciente(); 
				pac.setApellido(request.getParameter("txtApellido"));
				pac.setNombre(request.getParameter("txtNombre"));
				pac.setDni(request.getParameter("txtDni"));
				pac.setSexo(request.getParameter("sexo"));
					NacionalidadDao nacNegocio = new NacionalidadDao(); 
					
					Nacionalidad nacionalidad = nacNegocio.obtenerNacionalidadPorId(Integer.parseInt(request.getParameter("nacionalidad")));
				pac.setNacionalidad(nacionalidad);
				pac.setFechaNacimiento(request.getParameter("fechaNac"));
				pac.setDireccion(request.getParameter("txtDireccion"));
					ProvinciaDao provNegocio = new ProvinciaDao(); 
					Provincia provincia = provNegocio.obtenerProvinciaPorId(Integer.parseInt(request.getParameter("Sprovincia")));	
					pac.setProvincia(provincia);
					
					LocalidadDao locNegocio = new LocalidadDao(); 
					Localidad loc = locNegocio.obtenerLocalidadPorId(Integer.parseInt(request.getParameter("Slocalidad")));	
					pac.setLocalidad(loc);
				pac.setCorreoElectronico(request.getParameter("txtEmail"));
				pac.setTelefono(request.getParameter("txtTel"));
				
				pacienteDao pacNeg = new pacienteDao(); 
				if (ValidoCamposPaciente (pac)) {
					seGuardo = pacNeg.ActualizarPaciente(pac);
					request.setAttribute("seGuardo", seGuardo);
					RequestDispatcher rd = request.getRequestDispatcher("/MenuPaciente.jsp");
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
					rd.forward(request, response);
				}
				
				
				
			}
			

		}
		catch(Exception ex) {
			
			request.setAttribute("seGuardo", seGuardo);
			RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
			rd.forward(request, response);
		}
		//doGet(request, response);
	}

	public Boolean ValidoCamposPaciente (Paciente pac) { 
		Boolean camposCargado = true;
		if (pac.getNombre() == null || pac.getApellido() == null || pac.getDni() == null || pac.getSexo() == null || pac.getNacionalidad() == null || pac.getFechaNacimiento() == null || pac.getDireccion() == null || pac.getLocalidad() == null || pac.getProvincia() == null || pac.getCorreoElectronico() == null || pac.getTelefono() == null) {
			camposCargado=false;
		} else { 
			
			if (pac.getNombre() == "" || pac.getApellido() == "" || pac.getDni() == "" || pac.getSexo() == "" || pac.getFechaNacimiento() == "" || pac.getDireccion() == "" ||  pac.getCorreoElectronico() == "" || pac.getTelefono() == "") {
				camposCargado=false;
			}
		}
		return camposCargado;
	}
}
