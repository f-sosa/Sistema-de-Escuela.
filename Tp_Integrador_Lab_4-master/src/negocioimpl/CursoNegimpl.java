package negocioimpl;

import java.util.ArrayList;

import Entidad.Cursos;
import daoimpl.CursoDaoimpl;
import negocio.CursoNeg;

public class CursoNegimpl implements CursoNeg {
	public int agregarCurso(Cursos Cur) {
		int filas = 0;
		CursoDaoimpl CurDao = new CursoDaoimpl();
		filas = CurDao.agregarCurso(Cur);
		return filas;
	}

	public ArrayList<Cursos> listarCursos() {
		ArrayList<Cursos> lista = new ArrayList<Cursos>();
		CursoDaoimpl CurDao = new CursoDaoimpl();
		lista = CurDao.listarcursos();
		return lista;
	}

	public int EliminarCurso(String Nombre) {
		int Curso = 0;
		CursoDaoimpl CurDao = new CursoDaoimpl();
		Curso = CurDao.EliminarCurso(Nombre);
		return Curso;
	}

	public int modificarcurso(Cursos Cur) {
		int filas = 0;
		CursoDaoimpl CurDao = new CursoDaoimpl();
		filas = CurDao.modificarcurso(Cur);
		return filas;
	}

	public Cursos BuscarCurso(int CodCurso) {
		CursoDaoimpl CurDao = new CursoDaoimpl();
		Cursos lista = CurDao.BuscarCurso(CodCurso);
		return lista;
	}
	public ArrayList<Cursos> ListarAños() {
        ArrayList<Cursos> lista = new ArrayList<Cursos>();
         CursoDaoimpl CurDao = new CursoDaoimpl();
         lista = CurDao.ListarAños();
        return lista;
     }
	

	public ArrayList<Cursos> obtenercodCursosxprofesor(String Usuario) {
		CursoDaoimpl CurDao = new CursoDaoimpl();
		ArrayList<Cursos> listacodCurso = CurDao.obtenercodCursosxprofesor(Usuario);
		return listacodCurso;
	}

	
}
