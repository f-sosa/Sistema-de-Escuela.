package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import Entidad.Alumnos;
import Entidad.Docentes;
import Entidad.Provincias;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.ProfesorNegimpl;
import negocioimpl.ProvinciaNegimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlumnoModificar
 */
@WebServlet("/AlumnoModificar")
public class AlumnoModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AlumnoModificar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnModificar")!=null) {

			AlumnoNegimpl AlNeg = new AlumnoNegimpl();
			String LegajoAl = request.getParameter("LegajoAl");
			Alumnos Alum = new Alumnos();
			Alum = AlNeg.buscaralumno(LegajoAl);			
			request.setAttribute("Alumnos", Alum);		
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			ArrayList<Alumnos> listaAlumnos= AlNeg.listarAlumnos();		
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("ListaAlumnos", listaAlumnos);
			request.setAttribute("listaprov", listaprov);			
            RequestDispatcher rd = request.getRequestDispatcher("Modificar_Alumno.jsp");
            rd.forward(request, response);
        }		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificar2")!=null) {

			AlumnoNegimpl AlNeg = new AlumnoNegimpl();
			Alumnos Alum2 = new Alumnos();
			String LegajoAl = request.getParameter("LegajoAl");
			Alum2.setDni(request.getParameter("txtdni"));
			Alum2.setLegajoAl(Integer.parseInt(LegajoAl));
			Alum2.setNombre(request.getParameter("txtNombre"));
			Alum2.setApellido(request.getParameter("txtApellido"));
			Alum2.setNacimiento(request.getParameter("txtnacimiento"));
			Alum2.setDireccion(request.getParameter("txtdireccion"));
			Alum2.getProv().setIdProvincia(request.getParameter("Provincia"));
			Alum2.getLoc().setIdLocalidad(request.getParameter("sLctLoc"));
			Alum2.setEmail(request.getParameter("txtemail"));
			Alum2.setTelefono(request.getParameter("txtelefono"));
			int filas = AlNeg.modificaralumno(Alum2);
			request.setAttribute("AlumEliminado", filas);
			
			
			
			//////////////////////////////////
			
			Alumnos Alum = new Alumnos();
			Alum = AlNeg.buscaralumno(LegajoAl);
			request.setAttribute("Alumnos", Alum);
			//////////////	
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			ArrayList<Alumnos> listaAlumnos= AlNeg.listarAlumnos();		
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("ListaAlumnos", listaAlumnos);
			request.setAttribute("listaprov", listaprov);			
            RequestDispatcher rd = request.getRequestDispatcher("Modificar_Alumno.jsp");
            rd.forward(request, response);
        }	
		
	}	
}
		
		
	


