package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import Entidad.Notas;
import negocioimpl.NotasNegimpl;

/**
 * Servlet implementation class AñadirAlumnosACursos
 */
@WebServlet("/AgregarAlumnosACursos")
public class AgregarAlumnosACursos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarAlumnosACursos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("btnAgregarAlumnos")!=null) {
			NotasNegimpl NotNeg= new NotasNegimpl();
			Notas not = new Notas();	
			int CodCurso = Integer.parseInt(request.getParameter("CodCurso"));		
			not.getCur().setCodcurso(CodCurso);
			ArrayList<Notas> ListaAlumnosNoExistenteDeEseCurso =NotNeg.ListaAlumnosNoExistenteDeEseCurso(not);
			int c=2;
			for (Notas Not : ListaAlumnosNoExistenteDeEseCurso) {
				c++;			
			}
			request.getSession().setAttribute("CodCurso", CodCurso);
			request.setAttribute("VerificarAlumnos", c);
			request.setAttribute("ListaAlumnos", ListaAlumnosNoExistenteDeEseCurso);
			RequestDispatcher rd = request.getRequestDispatcher("Añadir_Alumno_A_Curso.jsp");   
	        rd.forward(request, response);
		}
		if(request.getParameter("btnAgregar")!=null) {
			NotasNegimpl NotNeg= new NotasNegimpl();
			Notas not = new Notas();	
			int filas=0;
			int LegAlum = Integer.parseInt(request.getParameter("legAlum"));
			int CodCurso = Integer.parseInt(request.getParameter("CodCurso"));
			int verificar=0;		
			not.getAlum().setLegajoAl(LegAlum);
			not.getCur().setCodcurso(CodCurso);
			verificar=NotNeg.VerificarAlumnoAlCurso(not);
			if(verificar==1) {
				filas=NotNeg.CambiarEstadoParaAñadir(not);
				
				ArrayList<Notas> ListaAlumnosNoExistenteDeEseCurso =NotNeg.ListaAlumnosNoExistenteDeEseCurso(not);
				request.setAttribute("ListaAlumnos", ListaAlumnosNoExistenteDeEseCurso);
				request.setAttribute("Agregado", filas);
				RequestDispatcher rd = request.getRequestDispatcher("Añadir_Alumno_A_Curso.jsp");   
		        rd.forward(request, response);
			}	
			else {
			not.setNota1(0);
			not.setNota2(0);
			not.setRec1(0);
			not.setRec2(0);
			filas=NotNeg.AñadirNotas(not);
			request.setAttribute("Agregado", filas);
			ArrayList<Notas> ListaAlumnosNoExistenteDeEseCurso =NotNeg.ListaAlumnosNoExistenteDeEseCurso(not);
			request.setAttribute("ListaAlumnos", ListaAlumnosNoExistenteDeEseCurso);
			RequestDispatcher rd = request.getRequestDispatcher("Añadir_Alumno_A_Curso.jsp");   
	        rd.forward(request, response);
			}
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
