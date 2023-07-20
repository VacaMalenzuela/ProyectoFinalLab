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
			
			int cantOcupado= turNeg.CantidadOcupado(request.getParameter("desde").toString(), request.getParameter("hasta").toString()); 
			//float porcOcupado  = ObtenerPorcentaje( 1); 
			//float porcPresente = ObtenerPorcentaje( 1); 
			//float porcAusente = ObtenerPorcentaje( 1); 
			
			request.setAttribute("cantidadOcupado", cantOcupado);
			
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
