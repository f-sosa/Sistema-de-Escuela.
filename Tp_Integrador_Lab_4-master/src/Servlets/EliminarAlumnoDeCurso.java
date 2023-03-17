package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import Entidad.Notas;
import negocioimpl.NotasNegimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EliminarAlumnoDeCurso
 */
@WebServlet("/EliminarAlumnoDeCurso")
public class EliminarAlumnoDeCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarAlumnoDeCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnEliminar")!=null) {
			NotasNegimpl NotNeg = new NotasNegimpl();
			int LegAlum = Integer.parseInt(request.getParameter("legAlum"));
			String Nombre= request.getParameter("Nombre");
			String Apellido= request.getParameter("Apellido");
			request.setAttribute("Legajo", LegAlum);
			request.setAttribute("Nombre", Nombre);		
			request.setAttribute("Apellido", Apellido);
			Notas not = new Notas();
			int CodCurso = Integer.parseInt(request.getParameter("CodCurso"));	
			NotasNegimpl notneg= new NotasNegimpl();
			not.getAlum().setLegajoAl(LegAlum);
			not.getCur().setCodcurso(CodCurso);
			int filas=0;
			filas=notneg.EliminarAlumnoDeCurso(not);
			ArrayList<Notas> ListaAlumnosDeEseCurso =NotNeg.listartodasNotas(not);
			request.setAttribute("ListaAlumnos", ListaAlumnosDeEseCurso);
			request.setAttribute("Eliminado", filas);
			RequestDispatcher rd = request.getRequestDispatcher("VerAlumnoDeUnCursoAdm.jsp");   
		       rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
