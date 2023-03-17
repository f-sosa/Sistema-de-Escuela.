package Entidad;

public class Notas {
    Alumnos Alum = new Alumnos();
    Cursos Cur = new Cursos();
    int Nota1;
    int Nota2;
    int Rec1;
    int Rec2;
    int EstadoAcademico;
	String NombreAlum;
	



	public Notas() {
		super();
	}
	public Notas(Alumnos alum, Cursos cur, int nota1, int nota2, int rec1, int rec2, int estadoAcademico,
			String nombreAlum) {
		super();
		Alum = alum;
		Cur = cur;
		Nota1 = nota1;
		Nota2 = nota2;
		Rec1 = rec1;
		Rec2 = rec2;
		EstadoAcademico = estadoAcademico;
		NombreAlum = nombreAlum;
	}
	public Alumnos getAlum() {
		return Alum;
	}
	public void setAlum(Alumnos alum) {
		Alum = alum;
	}
	public Cursos getCur() {
		return Cur;
	}
	public void setCur(Cursos cur) {
		Cur = cur;
	}
	public int getEstadoAcademico() {
		return EstadoAcademico;
	}
	public void setEstadoAcademico(int estadoAcademico) {
		EstadoAcademico = estadoAcademico;
	}
	public String getNombreAlum() {
		return NombreAlum;
	}
	public void setNombreAlum(String nombreAlum) {
		NombreAlum = nombreAlum;
	}
	public int getNota1() {
		return Nota1;
	}
	public void setNota1(int nota1) {
		Nota1 = nota1;
	}
	public int getNota2() {
		return Nota2;
	}
	public void setNota2(int nota2) {
		Nota2 = nota2;
	}
	public int getRec1() {
		return Rec1;
	}
	public void setRec1(int rec1) {
		Rec1 = rec1;
	}
	public int getRec2() {
		return Rec2;
	}
	public void setRec2(int rec2) {
		Rec2 = rec2;
	}



    

}