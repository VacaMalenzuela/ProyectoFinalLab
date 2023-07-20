package servlet;

import java.io.IOException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EstadoTurno;
import dominio.Horarios;
import dominio.Medico;
import dominio.Paciente;
import dominio.Turno;
import negocio.MedicoDao;
import negocio.TurnoDao;
import negocio.pacienteDao;


import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//if(request.getParameter("Param") != null) {
			//TurnoDao turNeg = new TurnoDao();
			//ArrayList<Turno> lista = turNeg.ObtenerTurnos() ; 
			//request.setAttribute("listaT", lista);
			//RequestDispatcher rd = request.getRequestDispatcher("/Turnos.jsp");
			//rd.forward(request, response);
			
		//}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("btnGuardarTurno") != null) {
			if (validarEspecialidadXMedico(request.getParameter("especialidad").toString(), request.getParameter("medico")) == true){ 
				
				if ( validaHorarioPorMedico(request.getParameter("medico").toString(), request.getParameter("fechaTurno").toString(), request.getParameter("horaTurno"))) {
					
					if (validaTurnosEnHorarioSeleccionado(request.getParameter("medico").toString(), request.getParameter("fechaTurno").toString(),request.getParameter("horaTurno"))) {
						//Carga Objeto turno 
						Turno tur = new Turno(); 
							MedicoDao medDao= new MedicoDao(); 
							Medico med = medDao.obtenerMedicoPorDni(request.getParameter("medico")); 
						tur.setMedico(med);
							pacienteDao pacDao = new pacienteDao(); 
							Paciente pac = pacDao.obtenerPacientePorDni(request.getParameter("SPaciente"));
						tur.setPaciente(pac);
						
						
						
						tur.setFecha(request.getParameter("fechaTurno"));
						tur.setHora(request.getParameter("horaTurno"));
							TurnoDao turDao = new TurnoDao(); 
						EstadoTurno estado = turDao.obtenerEstadoDefault();
						tur.setEstado(estado);
						
						
						
						// Agrega Turno a la base
						
						turDao.agregarTurno(tur);
						request.setAttribute("SeAgregoCorrectamente", 1);
						RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("TieneTurnoEnHorarioSeleccionado", 1);
						RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
						rd.forward(request, response);
					}
					
				} else {
					request.setAttribute("noCoincideHorarioPorMedico", 1);
					RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
					rd.forward(request, response);
				}

				
			} else { 
				request.setAttribute("noCoincideMedicoEspecialidad", 1);
				RequestDispatcher rd = request.getRequestDispatcher("/NuevoTurno.jsp");
				rd.forward(request, response);
			}
		}
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
			
			
			return seEncontroMedico;	
			
		}
		catch (Exception ex) {
			seEncontroMedico = false; 
			return seEncontroMedico;
			
		}
	}
	
	
	public Boolean validaHorarioPorMedico (String dni, String fecha, String time) { 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        int hora = Integer.parseInt(time.substring(0, time.indexOf(":")));
        
        //int horaParseada = Integer.parseInt(hora);
        Boolean CoincideHorarioXturno = false;
        try { 
        	Date fechaDate = format.parse(fecha);
        	String dia = ObtenerDia(fechaDate);
        	if (dia != "") { 
        		if (validaMedicoPorDia(dni, dia, hora)) { 
        			CoincideHorarioXturno = true; 
        		} 
        	}
        }
        catch (Exception e) { 
        String excepsion = e.toString();
        }
		return CoincideHorarioXturno;
		
	}
	
	
	
	
	
	public Boolean validaMedicoPorDia(String dni, String dia, int hora) { 
		MedicoDao medDao = new MedicoDao(); 
		Medico med = medDao.obtenerMedicoPorDni(dni); 
		Boolean trabajaEnEseHorario = false;
		for(Horarios hs : med.getHorarios()) { 
			if (hs.getDia().equals(dia)) {
				if (hora>= hs.getHorarioInicio() && hora <= hs.getHorarioFin()) { 
					trabajaEnEseHorario= true; 
					break;
				}
			}

		}
		
		return trabajaEnEseHorario;
	}
	
	public String ObtenerDia(Date fecha) { 
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
        case Calendar.MONDAY:
            return "Lunes";
        case Calendar.TUESDAY:
            return "Martes";
        case Calendar.WEDNESDAY:
            return "Miercoles";
        case Calendar.THURSDAY:
            return "Jueves";
        case Calendar.FRIDAY:
            return "Viernes";
        case Calendar.SATURDAY:
            return "Sabado";
        case Calendar.SUNDAY:
            return "Domingo";
    }
		
		
		return "";
	}
	
	
	public Boolean validaTurnosEnHorarioSeleccionado (String Dni, String Fecha, String Hora) { 
		int idTurno=0;
		TurnoDao turNegocio = new TurnoDao(); 
		int horaSinMin = Integer.parseInt(Hora.substring(0, 2));
		Turno tur = turNegocio.ObtenerTurnosPorFechaYhora (Dni, Fecha, horaSinMin);
		idTurno = tur.getId();
		
		if (idTurno != 0) {
			return false;
		} else { 
			return true;
		}
		
		
	}
}
