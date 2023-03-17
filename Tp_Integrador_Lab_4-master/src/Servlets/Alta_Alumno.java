package Servlets;
import java.io.IOException;


import java.util.ArrayList;
import Entidad.Alumnos;
import Entidad.Provincias;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.ProvinciaNegimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Alta_Alumno
 */
@WebServlet("/Alta_Alumno")
public class Alta_Alumno extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Alta_Alumno() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
			if(request.getParameter("Alta_Alumno")!=null) {
				ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
				AlumnoNegimpl alumNeg = new AlumnoNegimpl();
				ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
				Provincias prov= new Provincias();						
				ArrayList<Alumnos> listaalumnos= alumNeg.listarAlumnos();	
				request.setAttribute("ListaAlumnos", listaalumnos);
				request.setAttribute("listaprov", listaprov);
				RequestDispatcher rd = request.getRequestDispatcher("AltaAlumno.jsp");   
		        rd.forward(request, response);
			}
		
		}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
			
		if(request.getParameter("btnAgregar")!=null) {
			AlumnoNegimpl AlumNeg = new AlumnoNegimpl();
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			String Dni=request.getParameter("txtdni");
			int filas=0;
			filas=AlumNeg.VerificarAlumno(Dni);
			if(filas==1) {
			request.setAttribute("Existente", filas);
			AlumnoNegimpl alumNeg = new AlumnoNegimpl();
			 ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
				ArrayList<Alumnos> listaalumnos= alumNeg.listarAlumnos();
				request.getSession().setAttribute("DniExistente", Dni);
			request.setAttribute("listaprov", listaprov);
			request.setAttribute("ListaAlumnos", listaalumnos);
			RequestDispatcher rd = request.getRequestDispatcher("AltaAlumno.jsp");   
	        rd.forward(request, response);
			}
			else {
					int agregar=0;
					AlumnoNegimpl alumNeg = new AlumnoNegimpl();
					Alumnos alum = new Alumnos();
					alum.setDni(request.getParameter("txtdni"));;
					alum.setNombre(request.getParameter("txtNombre"));
					alum.setApellido(request.getParameter("txtApellido"));
					alum.setNacimiento(request.getParameter("txtnacimiento"));
					alum.setDireccion(request.getParameter("txtdireccion"));
					alum.getProv().setIdProvincia((request.getParameter("Provincia")));
					alum.getLoc().setIdLocalidad((request.getParameter("sLctLoc")));
					alum.setEmail(request.getParameter("txtemail"));
					alum.setTelefono(request.getParameter("txtelefono"));				
					agregar= alumNeg.agregarAlumno(alum);	
					request.getSession().setAttribute("Dni", alum.getDni());
					request.getSession().setAttribute("Nombre", alum.getNombre());
					request.getSession().setAttribute("Apellido", alum.getApellido());
					ArrayList<Alumnos> listaalumnos= alumNeg.listarAlumnos();	
					 ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
					request.setAttribute("ListaAlumnos", listaalumnos);
					request.setAttribute("Agregado", agregar);
					request.setAttribute("listaprov", listaprov);
					RequestDispatcher rd = request.getRequestDispatcher("/AltaAlumno.jsp");
					rd.forward(request, response);	
				}
				
				
		}	
	}

	}
	}
