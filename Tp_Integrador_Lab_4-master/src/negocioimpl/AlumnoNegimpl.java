package negocioimpl;
import java.util.ArrayList;
import Entidad.Alumnos;
import daoimpl.AlumnoDaoimpl;
import negocio.AlumnoNeg;
public class AlumnoNegimpl implements AlumnoNeg{
	public int agregarAlumno(Alumnos Alum) {
		 int filas=0;		 
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		filas=AlumDao.agregarAlumno(Alum);		 		 		
		return filas; 		
	 }		
	
	 public int modificaralumno(Alumnos Alum) {
		 int filas=0;
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 filas= AlumDao.modificaralumno(Alum);		 		 		
		return filas; 		
	 }
	
	 
	 public int EliminarAlumno(String legajo) {
		 int filas=0;
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 filas= AlumDao.EliminarAlumno(legajo);		 		 		
		return filas; 		
	 }
	 
		 
	 public ArrayList<Alumnos> listarAlumnos() {				
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 ArrayList<Alumnos> listaAlumnos= AlumDao.listarAlumnos();
		
		
		return listaAlumnos; 		
	 }
	 
	 public int VerificarAlumno(String Dni) {
		 int filas=0;
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 filas= AlumDao.VerificarAlumno(Dni);		 		 		
		return filas; 		
	 }
	 public ArrayList<Alumnos> listarAprobados(String Materia, String año) {
         AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();
         ArrayList<Alumnos> listaAprobados= AlumDao.listarAprobados(Materia,año);


        return listaAprobados;
	 }

     public ArrayList<Alumnos> listarDesaprobados(String Materia,String año) {
         AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();
         ArrayList<Alumnos> listaDesaprobados= AlumDao.listarDesaprobados(Materia,año);


        return listaDesaprobados;
     }
	 
	 public ArrayList<Alumnos> listarNotas(String Materia,String Parcial,String Condicion,String Nota) {				
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 ArrayList<Alumnos> listaNotas= AlumDao.listarNotas(Materia,Parcial,Condicion,Nota);
		
		
		return listaNotas; 		
	 }
	 public Alumnos buscaralumno(String LegajoAl) {				
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 Alumnos Alum= AlumDao.buscaralumno(LegajoAl);
		return Alum; 		
	 }
	 public ArrayList<Alumnos> listarNotas(String Materia,String Parcial,String Condicion,String Nota, int Cuatrimestre, int Año) {				
		 AlumnoDaoimpl AlumDao = new AlumnoDaoimpl();		 
		 ArrayList<Alumnos> listaNotas= AlumDao.listarNotas(Materia,Parcial,Condicion,Nota,Cuatrimestre,Año);
		
		
		return listaNotas; 		
	 }
}
