package negocioimpl;
import java.util.ArrayList;

import Entidad.Docentes;
import daoimpl.ProfesorDaoimpl;
import negocio.ProfesorNeg;

public class ProfesorNegimpl implements ProfesorNeg{

	public int agregarprofesor(Docentes doc) {
		 int filas=0;		 
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		filas=ProfDao.agregarprofesor(doc);		 		 		
		return filas; 		
	 }	
	 public int modificarprofesor(Docentes Prof) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.modificarprofesor(Prof);		 		 		
		return filas; 		
	 }
	 
	 public int EliminarProfesor(int dni) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.EliminarDocentes(dni);		 		 		
		return filas; 		
	 }
	 public ArrayList<Docentes> ListarDocentes() {				
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 ArrayList<Docentes> ListarDocentes= ProfDao.ListarDocentes();
		return ListarDocentes; 		
	 }
	 
	 
	 public int VerificarInicioSecion(String Nombre,String Contraseña) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.VerificarInicioSecion(Nombre,Contraseña);		 		 		
		return filas; 		
	 }
	 
	 public int VerificarProfesor(String dni) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.VerificarDocentes(dni);		 		 		
		return filas; 		
	 }
	 public int VerificarAdmin(String Usuario) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.VerificarAdmin(Usuario);		 		 		
		return filas; 		
	 }
	 public int VerificarModoAdministrador(String Usuario) {
		 int filas=0;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 filas= ProfDao.VerificarUsuario(Usuario);		 		 		
		return filas; 		
	 }
	 
	 public String BuscarNombreDocentes(String Nombre) {
		String NombreProfesor;
		 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
		 NombreProfesor= ProfDao.BuscarNombreDocentes(Nombre);		 		 		
		return NombreProfesor; 		
	 }
	 public Docentes BuscarDocente(String LegajoDoc) {
			Docentes Doc;
			 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
			 Doc= ProfDao.BuscarDocente(LegajoDoc);		 		 		
			return Doc; 		
		 }
	 
	 public int VerificarExistenciaprof(String Nombre) {
			int filas=0;
			 ProfesorDaoimpl ProfDao = new ProfesorDaoimpl();		 
			 filas= ProfDao.VerificarExistenciaprof(Nombre);		 		 		
			return filas; 		
		 }
}
