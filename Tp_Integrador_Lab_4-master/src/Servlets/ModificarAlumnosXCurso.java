package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.log.Log;

import Entidad.Alumnos;
import Entidad.Cursos;
import Entidad.Docentes;
import Entidad.Materias;
import Entidad.Notas;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.CursoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.NotasNegimpl;
import negocioimpl.ProfesorNegimpl;

/**
 * Servlet implementation class ModificarAlumnosXCurso
 */
@WebServlet("/ModificarAlumnosXCurso")
public class ModificarAlumnosXCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarAlumnosXCurso() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(String stringValue : stringArray) {
                //Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
        }       
        return result;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("param")!=null) {	
			CursoNegimpl CurNeg = new CursoNegimpl();
			String Usuario=request.getSession().getAttribute("Usuario").toString();
            ArrayList<Cursos> Listanombrecursos= CurNeg.obtenercodCursosxprofesor(Usuario);
			request.setAttribute("ListaCursos", Listanombrecursos);
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_AlumnosXCurso.jsp");
			rd.forward(request,response);
		}
		if(request.getParameter("ModificarNotas")!=null) {
            CursoNegimpl CurNeg = new CursoNegimpl();
            String Usuario=request.getSession().getAttribute("Usuario").toString();
            ArrayList<Cursos> Listanombrecursos= CurNeg.obtenercodCursosxprofesor(Usuario);
            request.setAttribute("ListaCursos", Listanombrecursos);
            NotasNegimpl NotNeg = new NotasNegimpl();
            Notas Not = new Notas();
            int cursocodselec = Integer.parseInt(request.getParameter("Curso"));
            Not.getCur().setCodcurso(cursocodselec);
            request.setAttribute("cursocodselec", cursocodselec);
            ArrayList<Notas> ListaNotas = NotNeg.listartodasNotas(Not);
            request.setAttribute("ListaNotas", ListaNotas);
            RequestDispatcher rd = request.getRequestDispatcher("/Modificar_AlumnosXCurso.jsp");
            rd.forward(request,response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnModificartodo")!=null) {
			String cursostring[] = request.getParameterValues("Curso");
			String legajostring[] = request.getParameterValues("Legajo");
			String Nota1string[] = request.getParameterValues("Nota1");
			String Nota2string[] = request.getParameterValues("Nota2");
			String Rec1string[] = request.getParameterValues("Rec1");
			String Rec2string[] = request.getParameterValues("Rec2");
			String Estadoacstring [] = request.getParameterValues("EstadoAcademico");
			Notas Not = new Notas();
			NotasNegimpl NotNeg = new NotasNegimpl();
			int i = 0;
			int filas = 0;
			int modifico = 0;
			for(String Curso : cursostring) {
				Not.getCur().setCodcurso(Integer.parseInt(Curso));
				Not.getAlum().setLegajoAl(Integer.parseInt(legajostring[i]));
				
				Not.setNota1(Integer.parseInt(Nota1string[i]));
				Not.setNota2(Integer.parseInt(Nota2string[i]));
				Not.setRec1(Integer.parseInt(Rec1string[i]));
				Not.setRec2(Integer.parseInt(Rec2string[i]));
				Not.setEstadoAcademico(Integer.parseInt(Estadoacstring[i]));
				filas = NotNeg.modificarnota(Not);
				i++;
			}
			if(filas == i) {
				modifico = 1;
			}
            CursoNegimpl CurNeg = new CursoNegimpl();
            String Usuario=request.getSession().getAttribute("Usuario").toString();
            ArrayList<Cursos> Listanombrecursos= CurNeg.obtenercodCursosxprofesor(Usuario);
            request.setAttribute("ListaCursos", Listanombrecursos);
            AlumnoNegimpl AlNeg = new AlumnoNegimpl();
            ArrayList<Alumnos> ListaAlumnos = AlNeg.listarAlumnos();
            request.setAttribute("ListaAlumnos", ListaAlumnos);
            int cursocodselec = Integer.parseInt(request.getParameter("Curso"));
            Not.getCur().setCodcurso(cursocodselec);
            request.setAttribute("cursocodselec", cursocodselec);
            ArrayList<Notas> ListaNotas = NotNeg.listartodasNotas(Not);
            request.setAttribute("ListaNotas", ListaNotas);
            request.setAttribute("Modifico", modifico);
            RequestDispatcher rd = request.getRequestDispatcher("/Modificar_AlumnosXCurso.jsp");
            rd.forward(request,response);
		}
	}

}
