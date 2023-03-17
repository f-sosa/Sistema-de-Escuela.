package Servlets;

import java.io.IOException;


import java.util.ArrayList;

import negocioimpl.AlumnoNegimpl;
import negocioimpl.MateriasNegimpl;
import negocioimpl.NotasNegimpl;
import negocioimpl.CursoNegimpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entidad.Alumnos;
import Entidad.Materias;
import Entidad.Notas;
import Entidad.Cursos;

/**
 * Servlet implementation class Reportes
 */
@WebServlet("/Reportes")
public class Reportes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Reportes() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("param")!=null) {
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
		if(request.getParameter("filtro1")!=null) {
			request.setAttribute("Filtro", 1);
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
		if(request.getParameter("filtro2")!=null) {
			request.setAttribute("Filtro", 2);
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
		if(request.getParameter("filtro3")!=null) {
			request.setAttribute("Filtro", 3);
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Btnfiltro1")!=null)
        {
            AlumnoNegimpl aneg = new AlumnoNegimpl();
            String Materia= request.getParameter("Materias");
            String Seleccion= request.getParameter("Aprobaron/Desaprobaron");
            String a�o= request.getParameter("A�o");
            if(Seleccion.contains("Aprobaron")) {
                ArrayList<Alumnos> lista= aneg.listarAprobados(Materia,a�o);
                request.setAttribute("listaU", lista);
            }else{
                ArrayList<Alumnos> lista= aneg.listarDesaprobados(Materia,a�o);
                request.setAttribute("listaU", lista);
            }

            CursoNegimpl CurNeg = new CursoNegimpl();
            ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
            request.setAttribute("listaAn", ListarA�os);

            MateriasNegimpl MatNeg = new MateriasNegimpl();
            ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
            request.setAttribute("Listarmaterias", ListarMaterias);
            RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");
            rd.forward(request, response);
        }
		
		if(request.getParameter("Btnfiltro2")!=null)
		{
			AlumnoNegimpl aneg = new AlumnoNegimpl();
			String Materia= request.getParameter("Materias");
			String Parcial=request.getParameter("Parcial");
			String Condicion=request.getParameter("Condicion");
			String Nota= request.getParameter("Nota");
			int Cuatrimestre =Integer.parseInt(request.getParameter("Cuatrim"));
			int A�o= Integer.parseInt(request.getParameter("Ano"));
			
			
			
			ArrayList<Alumnos> lista2= aneg.listarNotas(Materia,Parcial,Condicion,Nota,Cuatrimestre,A�o);
			request.setAttribute("listaN", lista2);
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
		
		if(request.getParameter("Btnfiltro3")!=null)
		{
			NotasNegimpl notNeg= new NotasNegimpl();
			String Materia= request.getParameter("Materias2");
			int A�oInicio= Integer.parseInt(request.getParameter("A�oIni"));
			int A�oFinal= Integer.parseInt(request.getParameter("A�oFin"));
			
			int A�oIni=0;
			int A�oFin=0;
			
			
			if(A�oInicio<A�oFinal) {
				A�oIni=A�oInicio;
				A�oFin=A�oFinal;
				} else {
					A�oIni=A�oFinal;
					A�oFin=A�oInicio;
				}
			
			
			ArrayList<Notas> lista2= notNeg.listarPromedio(Materia,A�oIni,A�oFin);
			request.setAttribute("listaPro", lista2);
			
			CursoNegimpl CurNeg = new CursoNegimpl();
			ArrayList<Cursos> ListarA�os = CurNeg.ListarA�os();
			request.setAttribute("listaAn", ListarA�os);	
			
			MateriasNegimpl MatNeg = new MateriasNegimpl();
			ArrayList<Materias> ListarMaterias = MatNeg.listarmaterias();
			request.setAttribute("Listarmaterias", ListarMaterias);	
			RequestDispatcher rd = request.getRequestDispatcher("Reportes.jsp");   
	        rd.forward(request, response);
		}
		
		}
		
		
		
		
		
		
	}


