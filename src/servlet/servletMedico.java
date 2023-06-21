package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Especialidad;
import dominio.Horarios;
import dominio.Localidad;
import dominio.Medico;
import dominio.Nacionalidad;
import dominio.Provincia;
import dominio.Usuario;
import negocio.EspecialidadDao;
import negocio.HorariosDao;
import negocio.LocalidadDao;
import negocio.NacionalidadDao;
import negocio.ProvinciaDao;
import negocio.UsuarioDao;
import negocio.MedicoDao;

@WebServlet("/servletMedico")
public class servletMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public servletMedico() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		if(request.getParameter("btnGuardarMedico") != null) {
			int filasAgregaEspecialidades = 0;
			int filasAgregaUsuario = AgregarUsuario(request.getParameter("txtUsuario"),request.getParameter("txtContrasena"));
			if (filasAgregaUsuario == 1) { 
				Usuario usu = new Usuario(); 
				UsuarioDao usuNeg = new UsuarioDao(); 
				usu = usuNeg.obtenerUltimoUsuario();
				
				Medico med = new Medico(); 
				med.setApellido(request.getParameter("txtApellido"));
				med.setNombre(request.getParameter("txtNombre"));
				med.setDni(request.getParameter("txtDni"));
				med.setSexo(request.getParameter("sexo"));
					NacionalidadDao nacNegocio = new NacionalidadDao(); 
					Nacionalidad nacionalidad = nacNegocio.obtenerNacionalidadPorId(Integer.parseInt(request.getParameter("nacionalidad")));
				med.setNacionalidad(nacionalidad);
				med.setFechaNacimiento(request.getParameter("fechaNac"));
				med.setDireccion(request.getParameter("txtDireccion"));
					ProvinciaDao provNegocio = new ProvinciaDao(); 
					Provincia provincia = provNegocio.obtenerProvinciaPorId(Integer.parseInt(request.getParameter("Sprovincia")));	
					med.setProvincia(provincia);
					LocalidadDao locNegocio = new LocalidadDao(); 
					Localidad loc = locNegocio.obtenerLocalidadPorId(Integer.parseInt(request.getParameter("Slocalidad")));	
					med.setLocalidad(loc);
				med.setCorreoElectronico(request.getParameter("txtEmail"));
				med.setTelefono(request.getParameter("txtTel"));
					EspecialidadDao espeNegocio = new EspecialidadDao(); 
					Especialidad espe = espeNegocio.obtenerEspecialidadPorId(Integer.parseInt(request.getParameter("Sespecialidad")));	
				med.setEspecialidad(espe);
				med.setUsuario(usu);
			       if (request.getParameter("especialidad") != null && request.getParameter("especialidad").length() > 0) {
			    	   String[] especialidadesSeleccionadas = request.getParameterValues("especialidad");
			    	   med.setHorarios(listaHorariosSeleccionado(especialidadesSeleccionadas, med));
			    	   
			        }
				
				
				MedicoDao medicoNeg = new MedicoDao(); 
				medicoNeg.agregarMedico(med);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoMedico.jsp");
				rd.forward(request, response);
			
			}
			
			
			
			
		}
	}
	
	public int AgregarUsuario (String Usuario, String pass) { 
		Usuario us = new Usuario(); 
		us.setNombre(Usuario);
		us.setClave(pass);
		UsuarioDao usNeg = new UsuarioDao();
		int filas =0;
		
		filas = usNeg.AgregarUsuario(us);
		return filas;
		
	}
	
	public ArrayList<Horarios> listaHorariosSeleccionado (String[] idSeleccionados,Medico med) {
		HorariosDao horas = new HorariosDao();
		ArrayList<Horarios> lstHoras = new ArrayList<Horarios>();
		for (int i = 0; i < idSeleccionados.length; i++) {
			lstHoras.add(horas.obtenerHorarioPorId(idSeleccionados[i].toString()));
		}
		
		return lstHoras;
	}

}
