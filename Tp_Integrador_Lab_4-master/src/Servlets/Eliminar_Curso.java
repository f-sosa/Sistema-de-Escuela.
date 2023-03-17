package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Cursos;
import Entidad.Docentes;
import Entidad.Materias;
import negocioimpl.CursoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.ProfesorNegimpl;

/**
 * Servlet implementation class Eliminar_Curso
 */
@WebServlet("/Eliminar_Curso")
public class Eliminar_Curso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Eliminar_Curso() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnEliminar") != null) {
			CursoNegimpl CurNeg = new CursoNegimpl();
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();
			int filas = 0;
			String CodCurso = request.getParameter("CodCurso");
			filas=CurNeg.EliminarCurso(CodCurso);
			ArrayList<Cursos> listaCursos = CurNeg.listarCursos();
			ArrayList<Materias> ListaMaterias = MatNeg.listarmaterias();
			ArrayList<Docentes> ListaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("Eliminado", filas);
			request.setAttribute("cursoel", CodCurso);
			request.setAttribute("listaCur", listaCursos);
			request.setAttribute("ListaMaterias", ListaMaterias);
			request.setAttribute("listaProf", ListaDocentes);
			request.setAttribute("Tablaop", 1);
			RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");
			rd.forward(request, response);

		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

	}

}
