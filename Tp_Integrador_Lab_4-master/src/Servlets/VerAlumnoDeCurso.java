package Servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import Entidad.Cursos;
import Entidad.Notas;
import negocioimpl.NotasNegimpl;
/**
 * Servlet implementation class VerAlumnoDeCurso
 */
@WebServlet("/VerAlumnoDeCurso")
public class VerAlumnoDeCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerAlumnoDeCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	if(request.getParameter("btnAlumnos")!=null) {
		NotasNegimpl NotNeg= new NotasNegimpl();
		Notas not = new Notas();		
		int CodCurso = Integer.parseInt(request.getParameter("CodCurso"));		
		not.getCur().setCodcurso(CodCurso);
		request.getSession().setAttribute("CodCurso", CodCurso);
		ArrayList<Notas> ListaAlumnosDeEseCurso =NotNeg.listartodasNotas(not);
		int c=2;
		for (Notas Not : ListaAlumnosDeEseCurso) {
			c++;			
		}
		request.setAttribute("VerificarAlumnos", c);
		request.setAttribute("ListaAlumnos", ListaAlumnosDeEseCurso);
		
		RequestDispatcher rd = request.getRequestDispatcher("VerAlumnoDeUnCursoAdm.jsp");   
	       rd.forward(request, response);		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}
