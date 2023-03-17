package Entidad;

public class Materias {
	//atributos
	String CodMateria;
	String Nombre;
	
	//metodos
	

	public Materias() {
		super();
	}
	
	public Materias(String codMateria, String nombre) {
		super();
		CodMateria = codMateria;
		Nombre = nombre;
	}


	public String getCodMateria() {
		return CodMateria;
	}

	public void setCodMateria(String codMateria) {
		CodMateria = codMateria;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
}
