package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import Entidad.Alumnos;
import Entidad.Cursos;
import Entidad.Docentes;
import Entidad.Materias;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.CursoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.ProfesorNegimpl;

/**
 * Servlet implementation class Iniciar_Secion
 */
@WebServlet("/Iniciar_Secion")
public class Iniciar_Secion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Iniciar_Secion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("BtnEntrar") != null) {
			String Nombre = request.getParameter("txtnombre");
			String Contraseña = request.getParameter("txtcontraseña");
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			CursoNegimpl CurNeg = new CursoNegimpl();
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			int filas = 0;
			int verificar = 0;
			int verificarExistenciaProf=0;
			verificar = ProfNeg.VerificarModoAdministrador(Nombre);
			filas = ProfNeg.VerificarInicioSecion(Nombre, Contraseña);
			verificarExistenciaProf=ProfNeg.VerificarExistenciaprof(Nombre);
			
			if (filas == 1) {
				if (verificar == 1) {
					String NombreProfesor = null;
					NombreProfesor = ProfNeg.BuscarNombreDocentes(Nombre);
					request.getSession().setAttribute("Profesor", NombreProfesor);
					request.getSession().setAttribute("Usuario", Nombre);
					ArrayList<Docentes> listaDocentes = ProfNeg.ListarDocentes();
					ArrayList<Cursos> lista = CurNeg.listarCursos();
					ArrayList<Materias> listanombreMaterias = MatNeg.listarmaterias();
					request.setAttribute("listaProf", listaDocentes);
					request.setAttribute("listaCur", lista);
					request.setAttribute("ListaMaterias", listanombreMaterias);
					request.setAttribute("Tablaop", 1);
					RequestDispatcher rd = request.getRequestDispatcher("Modo_Administrador.jsp");
					rd.forward(request, response);
				}
				else {
					if(verificarExistenciaProf==1) {
					String NombreProfesor = null;
					NombreProfesor = ProfNeg.BuscarNombreDocentes(Nombre);
					request.getSession().setAttribute("Profesor", NombreProfesor);
					request.getSession().setAttribute("Usuario", Nombre);
					String Usuario=request.getSession().getAttribute("Usuario").toString();
		            ArrayList<Cursos> Listanombrecursos= CurNeg.obtenercodCursosxprofesor(Usuario);
					request.setAttribute("ListaCursos", Listanombrecursos);
					RequestDispatcher rd = request.getRequestDispatcher("/Modificar_AlumnosXCurso.jsp");
					rd.forward(request,response);
				}
					else {
						request.setAttribute("DatosErroneos", filas);
						RequestDispatcher rd = request.getRequestDispatcher("Inicio_Secion.jsp");
						rd.forward(request, response);
						
					}
				}
			} else {
				request.setAttribute("DatosErroneos", filas);
				RequestDispatcher rd = request.getRequestDispatcher("Inicio_Secion.jsp");
				rd.forward(request, response);

			}

		}

	}
}
