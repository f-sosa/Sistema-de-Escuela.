package Entidad;

public class Docentes {
	//atributos
	int legajoDoc;
	String Nacimiento;
	String dni;
	String nombre;
	String apellido;
	String direccion;
	Provincias Prov= new Provincias();
	Localidad Loc= new Localidad();
	String Email;
	String telefono;
	String nombreusuario;
	String contrasena;
	String administrador;
	Boolean estado;
	
	//metodos
	
	public Docentes() {
		super();
	}

	
	public Docentes(int legajoDoc, String Nacimiento, String dni, String nombre, String apellido,
			String direccion, Localidad loc, Provincias prov, String email, String telefono, String nombreusuario,
			String contrasena, String administrador, Boolean estado) {
		super();
		this.legajoDoc = legajoDoc;
		this.Nacimiento = Nacimiento;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.Loc=loc;
		this.Prov=prov;
		this.Email = email;
		this.telefono = telefono;
		this.nombreusuario = nombreusuario;
		this.contrasena = contrasena;
		this.administrador = administrador;
		this.estado = estado;
	}


	


	public Provincias getProv() {
		return Prov;
	}


	public void setProv(Provincias prov) {
		Prov = prov;
	}


	public Localidad getLoc() {
		return Loc;
	}


	public void setLoc(Localidad loc) {
		Loc = loc;
	}


	public int getLegajoDoc() {
		return legajoDoc;
	}

	public void setLegajoDoc(int legajoDoc) {
		this.legajoDoc = legajoDoc;
	}

	public String getNacimiento() {
		return Nacimiento;
	}

	public void setNacimiento(String Nacimiento) {
		this.Nacimiento = Nacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	

	
	
}
