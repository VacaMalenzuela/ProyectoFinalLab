package servlet;

import java.io.IOException;
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

import dominio.Localidad;
import negocio.LocalidadDao;
@WebServlet("/ServletPaciente")
public class ServletPaciente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPaciente() {
        super();   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				
				
				
				pacienteDao pacNeg = new pacienteDao(); 
				seGuardo = pacNeg.agregarPaciente(pac);
				request.setAttribute("seGuardo", seGuardo);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
				rd.forward(request, response);
			}		

		}
		catch(Exception ex) {
			
			request.setAttribute("seGuardo", seGuardo);
			RequestDispatcher rd = request.getRequestDispatcher("/NuevoPaciente.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
