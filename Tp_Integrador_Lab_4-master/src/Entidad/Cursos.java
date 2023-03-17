package Entidad;
import Entidad.Materias;

import java.util.ArrayList;

import Entidad.Docentes;
public class Cursos {
	//atributos
	int ano;
	int cuatrimestre;
	int codcurso;
	Docentes Doc = new Docentes();
	Materias Mat = new Materias();
	ArrayList<Alumnos> ListaAlumnos;
	boolean estado;
	//metodos
	
	public Cursos() {
		super();
	}
	public Cursos(int ano, int cuatrimestre, int codcurso, Docentes doc, Materias mat, ArrayList<Alumnos> listaAlumnos,
			boolean estado) {
		super();
		this.ano = ano;
		this.cuatrimestre = cuatrimestre;
		this.codcurso = codcurso;
		Doc = doc;
		Mat = mat;
		ListaAlumnos = listaAlumnos;
		this.estado = estado;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getCuatrimestre() {
		return cuatrimestre;
	}
	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}
	public int getCodcurso() {
		return codcurso;
	}
	public void setCodcurso(int codcurso) {
		this.codcurso = codcurso;
	}
	public Docentes getDoc() {
		return Doc;
	}
	public void setDoc(Docentes doc) {
		Doc = doc;
	}
	public Materias getMat() {
		return Mat;
	}
	public void setMat(Materias mat) {
		Mat = mat;
	}
	public ArrayList<Alumnos> getListaAlumnos() {
		return ListaAlumnos;
	}
	public void setListaAlumnos(ArrayList<Alumnos> listaAlumnos) {
		ListaAlumnos = listaAlumnos;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	

}
