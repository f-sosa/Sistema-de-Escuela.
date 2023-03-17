package Servlets;

import java.io.IOException;

import java.util.ArrayList;
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

import Entidad.Alumnos;

/**
 * Servlet implementation class Alta_Profesor
 */
@WebServlet("/Alta_Profesor")
public class Alta_Profesor extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Alta_Profesor() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Alta_Profesor")!=null) {
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("listaprov", listaprov);
			ArrayList<Docentes> lista= ProfNeg.ListarDocentes();
			request.setAttribute("ListaDocentes", lista);
			RequestDispatcher rd = request.getRequestDispatcher("AltaProfesor.jsp");   
	        rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar")!=null) {
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			String Dni = request.getParameter("txtdni").toString() ;
			String Usuario = request.getParameter("txtusuario");
			int existedni=0;
			int existeusuario=0;		
			existedni=ProfNeg.VerificarProfesor(Dni);
			existeusuario=ProfNeg.VerificarAdmin(Usuario);
			if(existedni==1) {
			request.setAttribute("Existente", existedni);
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("listaprov", listaprov);
			RequestDispatcher rd = request.getRequestDispatcher("AltaProfesor.jsp");   
	        rd.forward(request, response);
			}			
			else {
				if(existeusuario==0) {
					int agregar=0;				
					ProfesorNegimpl Profneg = new ProfesorNegimpl();
					Docentes doc = new Docentes();
					doc.setDni(request.getParameter("txtdni"));;
					doc.setNombre(request.getParameter("txtNombre"));
					doc.setApellido(request.getParameter("txtApellido"));
					doc.setNacimiento(request.getParameter("txtnacimiento"));						
					doc.setDireccion(request.getParameter("txtdireccion"));
					doc.getProv().setIdProvincia((request.getParameter("Provincia")));
					doc.getLoc().setIdLocalidad((request.getParameter("sLctLoc")));
					doc.setEmail(request.getParameter("txtemail"));
					doc.setTelefono(request.getParameter("txtelefono"));
					doc.setNombreusuario(request.getParameter("txtusuario"));
					doc.setContrasena(request.getParameter("txtContraseña"));
					doc.setAdministrador(request.getParameter("Administrador"));
					agregar= ProfNeg.agregarprofesor(doc);	
					request.getSession().setAttribute("Dni", doc.getDni());
					request.getSession().setAttribute("Nombre", doc.getNombre());
					request.getSession().setAttribute("Apellido", doc.getApellido());
					ArrayList<Docentes> lista= Profneg.ListarDocentes();	
					ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
					request.setAttribute("Agregado", agregar);
					request.setAttribute("ListaDocentes", lista);
					request.setAttribute("listaprov", listaprov);
					RequestDispatcher rd = request.getRequestDispatcher("/AltaProfesor.jsp");
					rd.forward(request, response);	
				}else {
					ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
					
					ArrayList<Docentes> lista= ProfNeg.ListarDocentes();
					request.setAttribute("Existente", existedni);
					request.setAttribute("ExistenteUsuario", existeusuario);
					request.getSession().setAttribute("DniExistente", Dni);
					request.getSession().setAttribute("Usuario", Usuario);
					request.setAttribute("listaprov", listaprov);	
					request.setAttribute("ListaDocentes", lista);
					RequestDispatcher rd = request.getRequestDispatcher("AltaProfesor.jsp");   
			        rd.forward(request, response);
				}
					
				}
			
		}
		
		
		}
		}
	


