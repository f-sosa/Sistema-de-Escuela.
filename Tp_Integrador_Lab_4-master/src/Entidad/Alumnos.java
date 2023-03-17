package Entidad;

public class Alumnos {
	
	//atributos
	int LegajoAl;
	String Nacimiento;
	String Dni;
	String Nombre;
	String Apellido;
	String Direccion;
	Localidad Loc = new Localidad();
	Provincias Prov = new Provincias();
	String Email;
	String Telefono;
	Boolean Estado;
	//metodos

	public Alumnos() {
		super();
	}
	
	public Alumnos(int LegajoAl,String Nacimiento,String Dni,String Nombre,String Apellido,String Direccion,Localidad Loc,Provincias Prov,String Email,String Telefono,int CodProvincia,Boolean Estado) {
	this.LegajoAl=LegajoAl;
	this.Nacimiento=Nacimiento;
	this.Dni=Dni;
	this.Nombre=Nombre;
	this.Apellido=Apellido;
	this.Direccion=Direccion;
	this.Loc=Loc;
	this.Prov=Prov;
	this.Email=Email;
	this.Telefono=Telefono;
	this.Estado=Estado;
	}



	public Localidad getLoc() {
		return Loc;
	}

	public void setLoc(Localidad loc) {
		Loc = loc;
	}

	public Provincias getProv() {
		return Prov;
	}

	public void setProv(Provincias prov) {
		Prov = prov;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	public int getLegajoAl() {
		return LegajoAl;
	}

	public void setLegajoAl(int legajoAl) {
		LegajoAl = legajoAl;
	}

	public String getNacimiento() {
		return Nacimiento;
	}

	public void setNacimiento(String Nacimiento) {
		this.Nacimiento = Nacimiento;
	}
	
	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	
	
}

