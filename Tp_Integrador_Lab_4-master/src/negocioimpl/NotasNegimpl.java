package negocioimpl;
import java.util.ArrayList;

import Entidad.Notas;
import daoimpl.AlumnoDaoimpl;
import daoimpl.NotasDaoimpl;
import negocio.NotasNeg;

public class NotasNegimpl implements NotasNeg{


public int BuscarAprobados() {

    NotasDaoimpl  NotasDao= new NotasDaoimpl();
    int Aprobaron=0;
    Aprobaron=NotasDao.BuscarAprobados();
    return Aprobaron;
}

public int BuscarDesaprobados() {

    NotasDaoimpl  NotasDao= new NotasDaoimpl();
    int Desaprobados=0;
    Desaprobados=NotasDao.BuscarDesaprobados();
    return Desaprobados;
}
public ArrayList<Notas> listarNotas(Notas not) {
    NotasDaoimpl NotDao = new NotasDaoimpl();
    ArrayList<Notas> listarNotas= NotDao.listarNotas(not);
   return listarNotas;
}
public ArrayList<Notas> listartodasNotas(Notas not) {
    NotasDaoimpl NotDao = new NotasDaoimpl();
    ArrayList<Notas> listarNotas= NotDao.listartodasNotas(not);
   return listarNotas;
}

public int AñadirNotas(Notas not) {
	NotasDaoimpl NotDao = new NotasDaoimpl();
	int filas = NotDao.AñadirNotas(not);
	return filas;
}
public int modificarnota(Notas not) {
	NotasDaoimpl NotDao = new NotasDaoimpl();
	int filas = NotDao.modificarnota(not);
	return filas;
}
public int Eliminarnota(Notas not) {
	NotasDaoimpl NotDao = new NotasDaoimpl();
	int filas = NotDao.Eliminarnota(not);
	return filas;
}
public int EliminarAlumnoDeCurso(Notas not) {
	NotasDaoimpl NotDao = new NotasDaoimpl();
	int filas = NotDao.EliminarAlumnoDeCurso(not);
	return filas;
}
public int VerificarAlumnoAlCurso(Notas not)
{
    NotasDaoimpl NotDao = new NotasDaoimpl();
    int filas = NotDao.VerificarAlumnoAlCurso(not);

    return filas;
}
public ArrayList<Notas> ListaAlumnosNoExistenteDeEseCurso(Notas not) {
    NotasDaoimpl NotDao = new NotasDaoimpl();
    ArrayList<Notas> listarNotas= NotDao.ListaAlumnosNoExistenteDeEseCurso(not);
   return listarNotas;
}
public int CambiarEstadoParaAñadir(Notas not) {
	NotasDaoimpl NotDao = new NotasDaoimpl();
	int filas = NotDao.CambiarEstadoParaAñadir(not);
	return filas;
}
public ArrayList<Notas> listarPromedio(String Materia,int AñoIni,  int AñoFin) {				
	 NotasDaoimpl NotDao = new NotasDaoimpl();		 
	 ArrayList<Notas> listaNotas= NotDao.listarPromedio(Materia,AñoIni,AñoFin);
	
	
	return listaNotas; 		
}

}