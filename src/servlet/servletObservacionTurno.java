package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EstadoTurno;
import dominio.Medico;
import dominio.Turno;
import negocio.MedicoDao;
import negocio.TurnoDao;

/**
 * Servlet implementation class servletObservacionTurno
 */
@WebServlet("/servletObservacionTurno")
public class servletObservacionTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletObservacionTurno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnModificarTurno")!= null) {
			RequestDispatcher rd = request.getRequestDispatcher("/AsignacionObservacionTurno.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnFiltraPorEstado") != null) { 
			int estadoSelec = Integer.parseInt(request.getParameter("estados"));
			request.setAttribute("estadoSeleccionado", estadoSelec);
			RequestDispatcher rd = request.getRequestDispatcher("/MisTurnos.jsp");
			rd.forward(request, response);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//if (request.getParameter("btnGuardarTurno")!= null) { 
			String Id = request.getParameter("idTurno");
			TurnoDao turNeg = new TurnoDao();
			Turno tur = turNeg.obtenerTurnoPorId(Id);
			EstadoTurno estado = turNeg.obtenerEstadoPorId(request.getParameter("estados"));
			tur.setEstado(estado);
			tur.setObservacion(request.getParameter("observacion"));
			
			int filasModificadas = turNeg.ActualizarTurno(tur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/MisTurnos.jsp");
			rd.forward(request, response);
		//}

		//doGet(request, response);
	}

}
