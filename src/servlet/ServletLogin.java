package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Usuario;
import negocio.UsuarioDao;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ingresoCorrectamente = 1;
		try {
			if(request.getParameter("btnIngresar") != null) {
				Usuario usuario = new Usuario (); 
				UsuarioDao usNeg = new UsuarioDao(); 
				
				usuario = usNeg.validoUsuarioLogueado(request.getParameter("txtUsuarioLogin"), request.getParameter("txtClaveLogin"));
				if (usuario.getNombre() != null) { 
					RequestDispatcher rd = request.getRequestDispatcher("/MenuPrincipal.jsp");
					rd.forward(request, response);
				} else { 
					ingresoCorrectamente = 0; 
					request.setAttribute("errorAlIngresar", ingresoCorrectamente);
					RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}
			}	
		}
		catch(Exception ex) { 
			
		}

		//doGet(request, response);
	}

}
