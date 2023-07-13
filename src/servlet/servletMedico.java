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
import dominio.Paciente;
import dominio.Persona;
import dominio.Provincia;
import dominio.Usuario;
import negocio.EspecialidadDao;
import negocio.HorariosDao;
import negocio.LocalidadDao;
import negocio.NacionalidadDao;
import negocio.ProvinciaDao;
import negocio.UsuarioDao;
import negocio.pacienteDao;
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
		if(request.getParameter("btnModificarMedico")!= null) {
			MedicoDao medDao = new MedicoDao();
			Medico objMedico = medDao.obtenerMedicoPorDni(request.getParameter("btnModificarMedico"));
			RequestDispatcher rd = request.getRequestDispatcher("/NuevoMedico.jsp");
			rd.forward(request, response);
		}
		
		if(request.getParameter("btnEliminarMedico") != null) {
			MedicoDao medDao = new MedicoDao();
			int filasAfectadas = 0; 
			filasAfectadas = medDao.EliminarMedico(request.getParameter("btnEliminarMedico"));
			//request.setAttribute("filasAfectadas", filasAfectadas);
			RequestDispatcher rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		if(request.getParameter("btnGuardarMedico") != null) {
			int filasAgregaMedico = 0;
			int filasAgregaUsuario = 0; 
			if (validaUsuario(request.getParameter("txtContrasena"), request.getParameter("txtContrasena1"))){
				filasAgregaUsuario=AgregarUsuario(request.getParameter("txtUsuario"),request.getParameter("txtContrasena"));
			}
			
			if (filasAgregaUsuario != 0) {
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
				       if (request.getParameter("horarios") != null && request.getParameter("horarios").length() > 0) {
				    	   String[] horariosSeleccionadas = request.getParameterValues("horarios");
				    	   med.setHorarios(listaHorariosSeleccionado(horariosSeleccionadas));
				    	   
				        }
					
					if (validaCampoMedico(med, true)) { 
						MedicoDao medicoNeg = new MedicoDao(); 
						filasAgregaMedico= medicoNeg.agregarMedico(med);
					}
					request.setAttribute("MedicoAgregado", filasAgregaMedico);
					RequestDispatcher rd = request.getRequestDispatcher("/NuevoMedico.jsp");
					rd.forward(request, response);
			} else {
				request.setAttribute("ContrasenasDistintas", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoMedico.jsp");
				rd.forward(request, response);
			}
		} 
		
		if(request.getParameter("btnModificarMedico")!= null) {
			Medico med = new Medico(); 
			med.setApellido(request.getParameter("txtApellidoModif"));
			med.setNombre(request.getParameter("txtNombreModif"));
			med.setDni(request.getParameter("txtDni"));
			med.setSexo(request.getParameter("sexoModif"));
				NacionalidadDao nacNegocio = new NacionalidadDao(); 
				Nacionalidad nacionalidad = nacNegocio.obtenerNacionalidadPorId(Integer.parseInt(request.getParameter("nacionalidadModif")));
			med.setNacionalidad(nacionalidad);
			med.setFechaNacimiento(request.getParameter("fechaNacModif"));
			med.setDireccion(request.getParameter("txtDireccionModif"));
				ProvinciaDao provNegocio = new ProvinciaDao(); 
				Provincia provincia = provNegocio.obtenerProvinciaPorId(Integer.parseInt(request.getParameter("SprovinciaModif")));	
				med.setProvincia(provincia);
				
				LocalidadDao locNegocio = new LocalidadDao(); 
				Localidad loc = locNegocio.obtenerLocalidadPorId(Integer.parseInt(request.getParameter("SlocalidadModif")));	
				med.setLocalidad(loc);
			med.setCorreoElectronico(request.getParameter("txtEmailModif"));
			med.setTelefono(request.getParameter("txtTelModif"));
			EspecialidadDao espeNegocio = new EspecialidadDao(); 
			Especialidad espe = espeNegocio.obtenerEspecialidadPorId(Integer.parseInt(request.getParameter("SespecialidadModif")));	
		med.setEspecialidad(espe);
		    if (request.getParameter("horariosModif") != null && request.getParameter("horariosModif").length() > 0) {
		    	   String[] horariosSeleccionadas = request.getParameterValues("horariosModif");
		    	   med.setHorarios(listaHorariosSeleccionado(horariosSeleccionadas));
		    	   
		        }
		    
		    if (validaCampoMedico(med,false)) { 
				MedicoDao medDao = new MedicoDao(); 
				//Primero Elimina los horarios q contiene 
				medDao.ElimnaHorariosXMedico(med.getDni().toString()); 
				//Inserta los nuevos horarios 
				int fila = medDao.AgregarHorarios(med);
				fila += medDao.ActualizaMedico(med);
		    }
			RequestDispatcher rd = request.getRequestDispatcher("/MenuMedico.jsp");
			rd.forward(request, response);
			
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
	
	public ArrayList<Horarios> listaHorariosSeleccionado (String[] idSeleccionados) {
		HorariosDao horas = new HorariosDao();
		ArrayList<Horarios> lstHoras = new ArrayList<Horarios>();
		for (int i = 0; i < idSeleccionados.length; i++) {
			lstHoras.add(horas.obtenerHorarioPorId(idSeleccionados[i].toString()));
		}
		
		return lstHoras;
	}
	
	public Boolean validaCampoMedico(Medico med, Boolean esNuevoMedico) { 
		Boolean camposCargado = true;
		
		if (esNuevoMedico) { 
			if (med.getNombre() == null || med.getApellido() == null || med.getDni() == null || med.getSexo() == null || med.getNacionalidad() == null || med.getFechaNacimiento() == null || med.getDireccion() == null || med.getLocalidad() == null || med.getProvincia() == null || med.getCorreoElectronico() == null || med.getTelefono() == null || med.getEspecialidad() == null || med.getUsuario() == null) {
				camposCargado=false;
			} else { 
				
				if (med.getNombre() == "" || med.getApellido() == "" || med.getDni() == "" || med.getSexo() == "" || med.getFechaNacimiento() == "" || med.getDireccion() == "" ||  med.getCorreoElectronico() == "" || med.getTelefono() == "" || med.getUsuario().getNombre() == "" || med.getUsuario().getClave() == "") {
					camposCargado=false;
				}
			}
		} else {
			if (med.getNombre() == null || med.getApellido() == null || med.getDni() == null || med.getSexo() == null || med.getNacionalidad() == null || med.getFechaNacimiento() == null || med.getDireccion() == null || med.getLocalidad() == null || med.getProvincia() == null || med.getCorreoElectronico() == null || med.getTelefono() == null || med.getEspecialidad() == null) {
				camposCargado=false;
			} else { 
				
				if (med.getNombre() == "" || med.getApellido() == "" || med.getDni() == "" || med.getSexo() == "" || med.getFechaNacimiento() == "" || med.getDireccion() == "" ||  med.getCorreoElectronico() == "" || med.getTelefono() == "") {
					camposCargado=false;
				}
			}
		}

		return camposCargado;
	}
	
	public Boolean validaUsuario (String contrasena, String contrasena2) {
		if (contrasena.toString().trim().equals(contrasena2.toString().trim())) {
			return true; 
		} else { 
			return false;
		}
			
	}


}
