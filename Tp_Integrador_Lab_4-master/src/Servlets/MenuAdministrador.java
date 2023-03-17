package Servlets;

import java.io.IOException;

import Entidad.Alumnos;
import Entidad.Docentes;
import Entidad.Materias;
import Entidad.Notas;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.CursoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.NotasNegimpl;
import negocioimpl.ProfesorNegimpl;
import Entidad.Cursos;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
//import Entidades.Profesor;

/**
 * Servlet implementation class MenuAdministrador
 */
@WebServlet("/MenuAdministrador")
public class MenuAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public MenuAdministrador() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("btnCerrar") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("Inicio_Secion.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("param")!=null)
		if (Integer.parseInt(request.getParameter("param")) == 14) {
			CursoNegimpl CurNeg = new CursoNegimpl();
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();
			ArrayList<Docentes> listaDocentes = DocNeg.ListarDocentes();
			ArrayList<Cursos> lista = CurNeg.listarCursos();
			ArrayList<Materias> listanombreMaterias = MatNeg.listarmaterias();
			request.setAttribute("listaProf", listaDocentes);
			request.setAttribute("listaCur", lista);
			request.setAttribute("ListaMaterias", listanombreMaterias);
			request.setAttribute("Tablaop", 1);
			RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("param")!=null)
		if (Integer.parseInt(request.getParameter("param")) == 15) {
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();
			ArrayList<Docentes> listaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("listaProf", listaDocentes);
			request.setAttribute("Tablaop", 2);
			RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("param")!=null)
		if (Integer.parseInt(request.getParameter("param")) == 16) {
			AlumnoNegimpl AlumNeg = new AlumnoNegimpl();
			ArrayList<Alumnos> listaAlumno = AlumNeg.listarAlumnos();
			request.setAttribute("listaAlum", listaAlumno);
			request.setAttribute("Tablaop", 3);
			RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		

	}

}
