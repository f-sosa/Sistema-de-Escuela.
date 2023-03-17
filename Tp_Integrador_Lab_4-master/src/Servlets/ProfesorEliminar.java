package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Docentes;
import negocioimpl.ProfesorNegimpl;

/**
 * Servlet implementation class ProfesorEliminar
 */
@WebServlet("/ProfesorEliminar")
public class ProfesorEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfesorEliminar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnEliminar")!=null)
		{
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			int filas=0;
			int legajo =  Integer.parseInt(request.getParameter("legajoprof"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			ProfesorNegimpl Profneg = new ProfesorNegimpl();
		filas=Profneg.EliminarProfesor(legajo);
		ArrayList<Docentes> lista= ProfNeg.ListarDocentes();	
		request.setAttribute("listaProf", lista);
		request.setAttribute("Eliminado", filas);
		request.setAttribute("LegEliminado", legajo);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellido", apellido);
		request.setAttribute("Tablaop", 2);
		RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");   
        rd.forward(request, response);
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		

}
	}
