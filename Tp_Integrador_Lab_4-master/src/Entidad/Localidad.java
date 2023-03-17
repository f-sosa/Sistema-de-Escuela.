package Entidad;

public class Localidad {
String Nombre;
String IdLocalidad;
Provincias prov;
public Provincias getProv() {
	return prov;
}
public void setProv(Provincias prov) {
	this.prov = prov;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getIdLocalidad() {
	return IdLocalidad;
}
public void setIdLocalidad(String idLocalidad) {
	IdLocalidad = idLocalidad;
}
@Override
public String toString() {
	return "Localidad [Nombre=" + Nombre + ", IdLocalidad=" + IdLocalidad + ", prov=" + prov + "]";
}



}
