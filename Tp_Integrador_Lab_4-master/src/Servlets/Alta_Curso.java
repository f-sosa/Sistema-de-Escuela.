package Servlets;

import java.io.IOException;
import Entidad.Materias;
import java.util.ArrayList;

import Entidad.Cursos;
import Entidad.Docentes;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Provincias;
import negocioimpl.CursoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.ProfesorNegimpl;

/**
 * Servlet implementation class Alta_Curso
 */
@WebServlet("/Alta_Curso")
public class Alta_Curso extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Alta_Curso() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Alta_Curso")!=null) {
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			CursoNegimpl curNeg= new CursoNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			ArrayList<Docentes> listanombreProfesor= ProfNeg.ListarDocentes();
			ArrayList<Cursos> lista= curNeg.listarCursos();	
			request.setAttribute("ListarmateriasDropDown", ListarMaterias);	
			request.setAttribute("listanombreProfDropdown", listanombreProfesor);
			request.setAttribute("listadoDeCurso", lista);
			RequestDispatcher rd = request.getRequestDispatcher("AltaCursos.jsp");   
	        rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar")!=null) {
			CursoNegimpl CurNeg= new CursoNegimpl();
			String NombreCur=request.getParameter("Materias");
			int Curso=0;
			//Curso=CurNeg.VerificarCurso(NombreCur);
			if(Curso==1) {
			request.setAttribute("Existente", Curso);
			RequestDispatcher rd = request.getRequestDispatcher("AltaCursos.jsp");   
	        rd.forward(request, response);
			}
			else {
				int agregar=0;
				CursoNegimpl curNeg= new CursoNegimpl();
				ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
				MateriasNegimpl MatNeg = new MateriasNegimpl();
				Cursos Cur = new Cursos();
				Cur.getMat().setCodMateria(request.getParameter("Materias"));
				Cur.setCuatrimestre(Integer.parseInt(request.getParameter("Cuatrimestre")));
				Cur.getDoc().setLegajoDoc(Integer.parseInt(request.getParameter("Profesor")));	
				int año=Integer.parseInt(request.getParameter("Año"));	
				Cur.setAno(año);
				agregar=curNeg.agregarCurso(Cur);	
				ArrayList<Cursos> lista= curNeg.listarCursos();	
				ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
				ArrayList<Docentes> listanombreProfesor= ProfNeg.ListarDocentes();
				request.setAttribute("ListarmateriasDropDown", ListarMaterias);	
				request.setAttribute("listanombreProfDropdown", listanombreProfesor);
				request.setAttribute("listadoDeCurso", lista);
				request.setAttribute("Funciono", agregar);
				RequestDispatcher rd = request.getRequestDispatcher("AltaCursos.jsp");
				rd.forward(request, response);	
				
				
				
				
			}
			
			
		}
	}

}
