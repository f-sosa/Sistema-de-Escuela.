package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidad.Alumnos;
import Entidad.Docentes;
import Entidad.Provincias;
import negocioimpl.AlumnoNegimpl;
import negocioimpl.ProfesorNegimpl;
import negocioimpl.ProvinciaNegimpl;

/**
 * Servlet implementation class ProfesorModificar
 */
@WebServlet("/ProfesorModificar")
public class ProfesorModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ProfesorModificar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
if(request.getParameter("btnModificar")!=null) {
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			String LegajoDoc = request.getParameter("LegajoDoc");
			Docentes Doc = new Docentes();
			Doc = ProfNeg.BuscarDocente(LegajoDoc);
			request.setAttribute("Docentes", Doc);
		
			//////////////			
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			ArrayList<Docentes> listaDocentes= ProfNeg.ListarDocentes();		
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("ListaDocentes", listaDocentes);
			request.setAttribute("listaprov", listaprov);			
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Profesor.jsp");   
	        rd.forward(request, response);	
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificar2")!=null) {
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			Docentes Doc2 = new Docentes();
			String LegajoDoc = request.getParameter("LegajoDoc");
			Doc2.setDni(request.getParameter("txtdni"));
			Doc2.setLegajoDoc(Integer.parseInt(LegajoDoc));
			Doc2.setNombre(request.getParameter("txtNombre"));
			Doc2.setApellido(request.getParameter("txtApellido"));
			Doc2.setNacimiento(request.getParameter("txtnacimiento"));
			Doc2.setDireccion(request.getParameter("txtdireccion"));
			Doc2.getProv().setIdProvincia(request.getParameter("Provincia"));
			Doc2.getLoc().setIdLocalidad(request.getParameter("sLctLoc"));
			Doc2.setEmail(request.getParameter("txtemail"));
			Doc2.setTelefono(request.getParameter("txtelefono"));
			Doc2.setNombreusuario(request.getParameter("txtusuario"));
			Doc2.setContrasena(request.getParameter("txtContraseña"));
			Doc2.setAdministrador(request.getParameter("Administrador"));
			int filas = ProfNeg.modificarprofesor(Doc2);
			request.setAttribute("ProfEliminado", filas);
			///////////////////////////////////////
			
			Docentes Doc = new Docentes();
			Doc = ProfNeg.BuscarDocente(LegajoDoc);
			request.setAttribute("Docentes", Doc);
			//////////////			
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			ArrayList<Docentes> listaDocentes= ProfNeg.ListarDocentes();		
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("ListaDocentes", listaDocentes);
			request.setAttribute("listaprov", listaprov);			
			request.setAttribute("listaprov", listaprov);
			RequestDispatcher rd = request.getRequestDispatcher("Modificar_Profesor.jsp");   
	        rd.forward(request, response);
		}
		
		
		if(request.getParameter("btnModificar")!=null) {
			ProfesorNegimpl ProfNeg = new ProfesorNegimpl();
			ProvinciaNegimpl provNeg = new ProvinciaNegimpl();
			Docentes Doc = new Docentes();
			int filas=0;
			String dni = request.getParameter("DniProfesor");
			int legajo =Integer.parseInt(request.getParameter("LegajoProfesor").toString()) ;
			String Nombre= request.getParameter("Nombre");
			String Apellido= request.getParameter("Apellido");
			String Nacimiento= request.getParameter("Nacimiento");
			String Direccion= request.getParameter("Dirección");
			String Telefono=request.getParameter("Teléfono");
			String Email= request.getParameter("Email");
			String Localidad= request.getParameter("Localidad");
			String Provincia= request.getParameter("Provincia");	
			String Usuario =request.getParameter("Usuario");			
			Doc.setLegajoDoc(legajo);
			Doc.setDni(dni);
			Doc.setNombre(Nombre);
			Doc.setApellido(Apellido);
			Doc.setNacimiento(Nacimiento);
			Doc.setDireccion(Direccion);
			Doc.setTelefono(Telefono);
			Doc.setEmail(Email);
			Doc.getLoc().setIdLocalidad((Localidad));
			Doc.getProv().setIdProvincia((Provincia));
			Doc.setNombreusuario(Usuario);
			filas= ProfNeg.modificarprofesor(Doc);
			request.getSession().setAttribute("Legajo", legajo);		
			request.setAttribute("Modificado", filas);
			ArrayList<Docentes> listaDocentes= ProfNeg.ListarDocentes();
			ArrayList<Provincias> listaprov= provNeg.ListarProvincias();
			request.setAttribute("ListaDocentes", listaDocentes);
			request.setAttribute("listaprov", listaprov);
			RequestDispatcher rd = request.getRequestDispatcher("/Modificar_Profesor.jsp");   
	        rd.forward(request, response);	
		}

}
	}
