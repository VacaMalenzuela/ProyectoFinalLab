package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EstadoTurno;
import dominio.Medico;
import dominio.Paciente;
import dominio.Turno;
import negocio.MedicoDao;
import negocio.TurnoDao;
import negocio.pacienteDao;

/**
 * Servlet implementation class servletTurno
 */
@WebServlet("/servletTurno")
public class servletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletTurno() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("btnGuardarTurno") != null) {
			if (validarEspecialidadXMedico(request.getParameter("especialidad").toString(), request.getParameter("medico")) == true){ 
				//Carga Objeto turno 
				Turno tur = new Turno(); 
					MedicoDao medDao= new MedicoDao(); 
					Medico med = medDao.obtenerMedicoPorDni(request.getParameter("medico")); 
				tur.setMedico(med);
					pacienteDao pacDao = new pacienteDao(); 
					Paciente pac = pacDao.obtenerPacientePorDni(request.getParameter("SPaciente"));
				tur.setPaciente(pac);
				
				
				
				tur.setFecha(request.getParameter("fechaTurno"));
				tur.setHora(Integer.parseInt(request.getParameter("horaTurno")));
					TurnoDao turDao = new TurnoDao(); 
				EstadoTurno estado = turDao.obtenerEstadoDefault();
				tur.setEstado(estado);
				
				
				
				// Agrega Turno a la base
				
				turDao.agregarTurno(tur);
				request.setAttribute("SeAgregoCorrectamente", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
				rd.forward(request, response);
				
			} else { 
				request.setAttribute("noCoincideMedicoEspecialidad", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
				rd.forward(request, response);
			}
		}
		//doGet(request, response);
	}

	
	
	public Boolean validarEspecialidadXMedico(String idEspecialidad, String dni) {
		
		Boolean seEncontroMedico = false; 
		MedicoDao medNeg = new MedicoDao(); 
		try 
		{
			Medico med = medNeg.validaMedicoPorDniYespecialidad(dni, idEspecialidad );
			
			if (med != null) {
				seEncontroMedico= true;
			}
			
			System.out.println (med.getApellido() +" - "+ med.getEspecialidad().getEspecialidad());
			return seEncontroMedico;	
			
		}
		catch (Exception ex) {
			seEncontroMedico = false; 
			return seEncontroMedico;
			
		}

	
	}
}
