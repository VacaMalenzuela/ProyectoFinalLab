package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Turno;
import negocio.TurnoDao;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if(request.getParameter("btnReporte") != null) {
			TurnoDao turNeg = new TurnoDao();
			ArrayList<Turno> lista = turNeg.ObtenerTurnosEntreFechas(request.getParameter("desde").toString(), request.getParameter("hasta").toString()) ; 
			
			int cantOcupados= turNeg.CantidadOcupados(request.getParameter("desde").toString(), request.getParameter("hasta").toString()); 
			int cantPresentes= turNeg.CantidadPresentes(request.getParameter("desde").toString(), request.getParameter("hasta").toString()); 
			int cantAusentes= turNeg.CantidadAusentes(request.getParameter("desde").toString(), request.getParameter("hasta").toString()); 
			request.setAttribute("cantidadOcupados", cantOcupados);
			request.setAttribute("cantidadPresentes", cantPresentes);
			request.setAttribute("cantidadAusentes", cantAusentes);
			request.setAttribute("listaT", lista);
			RequestDispatcher rd = request.getRequestDispatcher("/Reportes.jsp");
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
