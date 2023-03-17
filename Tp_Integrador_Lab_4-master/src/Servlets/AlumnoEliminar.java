package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Alumnos;
import Entidad.Provincias;
import daoimpl.AlumnoDaoimpl;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.ProvinciaNegimpl;

/**
 * Servlet implementation class AlumnoEliminar
 */
@WebServlet("/AlumnoEliminar")
public class AlumnoEliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AlumnoEliminar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				if(request.getParameter("btnEliminar")!=null)
				{
				int filas=0;
				String legajo =request.getParameter("LegajoAl");
				String Nombre=request.getParameter("Nombre");
				String Apellido=request.getParameter("Apellido");
				AlumnoNegimpl AlumNeg = new AlumnoNegimpl();
				filas= AlumNeg.EliminarAlumno(legajo);
				ArrayList<Alumnos> lista= AlumNeg.listarAlumnos();
				request.setAttribute("nombre", Nombre);
				request.setAttribute("legajoal", legajo);
				request.setAttribute("apellido", Apellido);
				request.setAttribute("listaAlum", lista);
				request.setAttribute("Eliminado", filas);	
				request.setAttribute("Tablaop", 3);
				RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");   
		        rd.forward(request, response);
			}
			
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	}
