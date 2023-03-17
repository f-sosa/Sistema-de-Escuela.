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
 * Servlet implementation class CursoModificar
 */
@WebServlet("/CursoModificar")
public class CursoModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CursoModificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("btnModificar") != null) {
			CursoNegimpl CurNeg = new CursoNegimpl();
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();
			ArrayList<Docentes> listaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("listaProf", listaDocentes);
			ArrayList<Cursos> lista = CurNeg.listarCursos();
			request.setAttribute("listaCur", lista);
			ArrayList<Materias> listanombreMaterias = MatNeg.listarmaterias();
			ArrayList<Docentes> ListaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("ListaMaterias", listanombreMaterias);
			request.setAttribute("ListaDocentes", ListaDocentes);
			request.setAttribute("Tablaop", 1);
			Cursos Cur = new Cursos();
			int CodCurso = Integer.parseInt(request.getParameter("CodCurso")) ;
			Cur = CurNeg.BuscarCurso(CodCurso);
			request.setAttribute("CodCurso", CodCurso);
			request.setAttribute("Cuatrimestre", Cur.getCuatrimestre());
			request.setAttribute("CodMateria", Cur.getMat().getCodMateria());
			request.setAttribute("Año", Cur.getAno());
			request.setAttribute("Docente", Cur.getDoc().getLegajoDoc());
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Cursos.jsp");
			rd.forward(request, response);
		}
		

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnModificar2") != null) {
			Cursos Cur = new Cursos();
			CursoNegimpl CurNeg = new CursoNegimpl();
			Cur.setCuatrimestre(Integer.parseInt(request.getParameter("Cuatrimestre")));
			Cur.getMat().setCodMateria(request.getParameter("Materias"));
			Cur.setAno(Integer.parseInt(request.getParameter("Año")));
			Cur.getDoc().setLegajoDoc(Integer.parseInt(request.getParameter("Profesor")));
			Cur.setCodcurso(Integer.parseInt(request.getParameter("CodCurso")));
			int filas = CurNeg.modificarcurso(Cur);
			request.setAttribute("CursoEliminado", filas);
			///			
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();
			ArrayList<Docentes> listaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("listaProf", listaDocentes);
			ArrayList<Cursos> lista = CurNeg.listarCursos();
			request.setAttribute("listaCur", lista);
			ArrayList<Materias> listanombreMaterias = MatNeg.listarmaterias();
			ArrayList<Docentes> ListaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("ListaMaterias", listanombreMaterias);
			request.setAttribute("ListaDocentes", ListaDocentes);
			request.setAttribute("Tablaop", 1);
			Cursos Cur2 = new Cursos();
			int CodCurso = Integer.parseInt(request.getParameter("CodCurso")) ;
			Cur2 = CurNeg.BuscarCurso(CodCurso);
			request.setAttribute("CodCurso", CodCurso);
			request.setAttribute("Cuatrimestre", Cur2.getCuatrimestre());
			request.setAttribute("CodMateria", Cur2.getMat().getCodMateria());
			request.setAttribute("Año", Cur2.getAno());
			request.setAttribute("Docente", Cur2.getDoc().getLegajoDoc());
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Cursos.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnModificartodo")!=null) {
			int modificar=0;
			Cursos Cur = new Cursos();
			CursoNegimpl CurNeg = new CursoNegimpl();
			
			String codigostring[] = request.getParameterValues("CodCurso");
			String materiastring[] = request.getParameterValues("Materia");
			String cuatrimestrestring[] = request.getParameterValues("Cuatrimestre");
			String stringano[] = request.getParameterValues("Año");
			String stringlegajo[] = request.getParameterValues("Profesor");
			int i = 0;
			int filas =0;
			for(String Curso : codigostring) {
				Cur.setCodcurso(Integer.parseInt(Curso));
				Cur.getMat().setCodMateria(materiastring[i]);
				Cur.setCuatrimestre(Integer.parseInt(cuatrimestrestring[i]));
				Cur.setAno(Integer.parseInt(stringano[i]));
				Cur.getDoc().setLegajoDoc(Integer.parseInt(stringlegajo[i]));
				filas += CurNeg.modificarcurso(Cur);
				i++;
			}
			int elimino = 2;
			if(filas==i) {
				elimino=1;
			}
			
			
			
			
			///////////////////////////////////////////////////////
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ProfesorNegimpl DocNeg = new ProfesorNegimpl();		
			ArrayList<Cursos> listaCursos = CurNeg.listarCursos();
			ArrayList<Materias> ListaMaterias = MatNeg.listarmaterias();
			ArrayList<Docentes> ListaDocentes = DocNeg.ListarDocentes();
			request.setAttribute("cantFilas", modificar);
			request.setAttribute("ListaCursos", listaCursos);				
			request.setAttribute("ListaMaterias", ListaMaterias);
			request.setAttribute("ListaDocentes", ListaDocentes);
            request.setAttribute("elimino", elimino);
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Cursos.jsp");
			rd.forward(request,response);
		}
	}

}
