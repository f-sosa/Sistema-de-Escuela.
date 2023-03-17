package Entidad;

public class Provincias {
	String IdProvincia;
	String Nombre;

public Provincias() {
	super();
}
public Provincias(String IdProvincia,String Nombre) {
	this.IdProvincia=IdProvincia;
	this.Nombre=Nombre;
	
	
}



public String getIdProvincia() {
	return IdProvincia;
}

public void setIdProvincia(String idProvincia) {
	IdProvincia = idProvincia;
}

public String getNombre() {
	return Nombre;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}













}