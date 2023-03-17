package daoimpl;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entidad.Alumnos;
import dao.AlumnoDao;


public class AlumnoDaoimpl implements AlumnoDao{

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	public int agregarAlumno(Alumnos Alum)
	{		
		Alumnos alu = new Alumnos();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "INSERT INTO alumnos (Dni,Nombre,Apellido,Nacimiento,Direccion,Localidad,Provincia,Email,Telefono,Estado) VALUES('"+Alum.getDni()+"','"+Alum.getNombre()+"','"+Alum.getApellido()+"','"+Alum.getNacimiento()+"','"+Alum.getDireccion()+"','"+Alum.getLoc().getIdLocalidad()+"','"+Alum.getProv().getIdProvincia()+"','"+Alum.getEmail()+"','"+Alum.getTelefono()+"','1');";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public int modificaralumno(Alumnos Alum)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Update Alumnos SET Nombre='"+Alum.getNombre()+"',Dni='"+Alum.getDni()+"',Apellido='"+Alum.getApellido()+"',Nacimiento='"+Alum.getNacimiento()+"',Direccion='"+Alum.getDireccion()+"',Email='"+Alum.getEmail()+"',Telefono='"+Alum.getTelefono()+"',Provincia='"+Alum.getProv().getIdProvincia()+"',Localidad='"+Alum.getLoc().getIdLocalidad()+"'Where LegajoAl="+Alum.getLegajoAl();
			
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public int EliminarAlumno(String legajo)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int filas=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Update Alumnos SET Estado=0 where LegajoAl='"+legajo+"'";
			filas=st.executeUpdate(query);
			String query2 = "Update notas SET Estado=0 where LegajoAl='"+legajo+"'";
			filas=st.executeUpdate(query2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	public ArrayList<Alumnos> listarAlumnos()
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select Alumnos.LegajoAl,Alumnos.Dni,Alumnos.Nombre,Alumnos.Apellido,alumnos.Nacimiento,alumnos.Direccion,localidades.nombre_loc as Localidad, provincias.Nombre as Provincias, alumnos.Email,alumnos.Telefono,Alumnos.Provincia,Alumnos.Localidad,Alumnos.Estado from alumnos inner join provincias on alumnos.Provincia=provincias.idProvincias inner join localidades on alumnos.Localidad=localidades.idlocalidad_loc where alumnos.Estado=1");
			while(rs.next()){
				
				Alumnos Alum = new Alumnos();
				
				Alum.setLegajoAl(rs.getInt("Alumnos.LegajoAl"));
				Alum.setDni(rs.getString("Alumnos.Dni"));
				Alum.setNombre(rs.getString("Alumnos.Nombre"));
				Alum.setApellido(rs.getString("Alumnos.Apellido"));
				Alum.setTelefono(rs.getString("Alumnos.Telefono"));
				Alum.setEmail(rs.getString("Alumnos.Email"));
				Alum.setNacimiento(rs.getString("Alumnos.Nacimiento"));		
				Alum.getProv().setIdProvincia(rs.getString("Alumnos.Provincia"));			
				Alum.setDireccion(rs.getString("Alumnos.Direccion"));
				Alum.getLoc().setNombre((rs.getString("Localidad")));
				Alum.getLoc().setIdLocalidad(rs.getString("Alumnos.Localidad"));
				Alum.getProv().setIdProvincia(rs.getString("Alumnos.Provincia"));
				Alum.getProv().setNombre((rs.getString("Provincias")));
				Alum.setEstado(rs.getBoolean("Alumnos.Estado"));
			
			
				lista.add(Alum);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	

	
	public int VerificarAlumno(String Dni)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int filas=0;
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = con.createStatement();
			PreparedStatement miSentencia = con.prepareStatement("Select dni from Alumnos where Dni = ? ");
			miSentencia.setString(1, Dni);			
			ResultSet resultado = miSentencia.executeQuery();
			while(resultado.next()){
				filas=1;
				}
				con.close();																
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}	
	public ArrayList<Alumnos> listarDesaprobados(String Materia, String año)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("SELECT alumnos.LegajoAl, alumnos.Dni,alumnos.Nombre,alumnos.Apellido,alumnos.Email,alumnos.Telefono,materias.nombre FROM gesteducativa.alumnos INNER JOIN gesteducativa.notas on notas.LegajoAl=alumnos.LegajoAl INNER JOIN gesteducativa.cursos on cursos.CodCurso = notas.CodCurso INNER JOIN gesteducativa.materias on cursos.CodMateria = materias.CodMateria Where (alumnos.Estado=1) and ((notas.Rec_1>0 and notas.Rec_1<6) or (notas.Rec_2>0 and notas.Rec_2<6)) and (materias.CodMateria = '"+Materia+"')and cursos.Año='"+año+"' and notas.estado=1");
			
			while(rs.next()){
								
				Alumnos Alum = new Alumnos();
				Alum.setLegajoAl(rs.getInt("alumnos.LegajoAl"));
				Alum.setDni(rs.getString("alumnos.Dni"));
				Alum.setNombre(rs.getString("alumnos.Nombre"));
				Alum.setApellido(rs.getString("alumnos.Apellido"));
				Alum.setTelefono(rs.getString("alumnos.Telefono"));
				Alum.setEmail(rs.getString("alumnos.Email"));	
			
				lista.add(Alum);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Alumnos> listarAprobados(String Materia,String año)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("SELECT alumnos.LegajoAl, alumnos.Dni,alumnos.Nombre,alumnos.Apellido,alumnos.Email,alumnos.Telefono,materias.nombre FROM gesteducativa.alumnos INNER JOIN gesteducativa.notas on notas.LegajoAl=alumnos.LegajoAl INNER JOIN gesteducativa.cursos on cursos.CodCurso = notas.CodCurso INNER JOIN gesteducativa.materias on cursos.CodMateria = materias.CodMateria Where ((alumnos.Estado=1) and ((notas.Nota_1>=6 and notas.Nota_2>=6) or (notas.Rec_1>=6 AND Rec_2>=6) or (notas.Nota_1>=6 and notas.Rec_2>=6)or(notas.Nota_2>=6 and notas.Rec_1>=6))) and (materias.CodMateria = '"+Materia+"')and cursos.Año='"+año+"' and notas.estado=1");
			
			
			while(rs.next()){
				
				Alumnos Alum = new Alumnos();
				Alum.setLegajoAl(rs.getInt("alumnos.LegajoAl"));
				Alum.setDni(rs.getString("alumnos.Dni"));
				Alum.setNombre(rs.getString("alumnos.Nombre"));
				Alum.setApellido(rs.getString("alumnos.Apellido"));
				Alum.setTelefono(rs.getString("alumnos.Telefono"));
				Alum.setEmail(rs.getString("alumnos.Email"));			
			
				lista.add(Alum);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	public ArrayList<Alumnos> listarNotas(String Materia,String Parcial,String Condicion,String Nota)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("SELECT alumnos.LegajoAl, alumnos.Dni,alumnos.Nombre,alumnos.Apellido,alumnos.Email,alumnos.Telefono FROM gesteducativa.alumnos INNER JOIN gesteducativa.notas on notas.LegajoAl=alumnos.LegajoAl INNER JOIN gesteducativa.cursos on cursos.CodCurso = notas.CodCurso INNER JOIN gesteducativa.materias on cursos.CodMateria = materias.CodMateria Where (alumnos.Estado=1) AND (materias.CodMateria = '"+Materia+"')  AND (notas."+Parcial+" "+Condicion+" "+Nota+")");

			
			
			while(rs.next()){
				
				Alumnos Alum = new Alumnos();
				Alum.setLegajoAl(rs.getInt("alumnos.LegajoAl"));
				Alum.setDni(rs.getString("alumnos.Dni"));
				Alum.setNombre(rs.getString("alumnos.Nombre"));
				Alum.setApellido(rs.getString("alumnos.Apellido"));	
			
				lista.add(Alum);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	public Alumnos buscaralumno(String LegajoAl)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection cn = null;
		Alumnos Alum = new Alumnos();
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select Alumnos.LegajoAl,Alumnos.Dni,Alumnos.Nombre,Alumnos.Apellido,alumnos.Nacimiento,alumnos.Direccion,localidades.nombre_loc as Localidad, provincias.Nombre as Provincias, alumnos.Email,alumnos.Telefono,Alumnos.Provincia,Alumnos.Localidad,Alumnos.Estado from alumnos inner join provincias on alumnos.Provincia=provincias.idProvincias inner join localidades on alumnos.Localidad=localidades.idlocalidad_loc where alumnos.legajoal='"+LegajoAl+"' and alumnos.Estado=1");
			while(rs.next()){
				
			
				
				Alum.setLegajoAl(rs.getInt("Alumnos.LegajoAl"));
				Alum.setDni(rs.getString("Alumnos.Dni"));
				Alum.setNombre(rs.getString("Alumnos.Nombre"));
				Alum.setApellido(rs.getString("Alumnos.Apellido"));
				Alum.setTelefono(rs.getString("Alumnos.Telefono"));
				Alum.setEmail(rs.getString("Alumnos.Email"));
				Alum.setNacimiento(rs.getString("Alumnos.Nacimiento"));		
				Alum.getProv().setIdProvincia(rs.getString("Alumnos.Provincia"));			
				Alum.setDireccion(rs.getString("Alumnos.Direccion"));
				Alum.getLoc().setNombre((rs.getString("Localidad")));
				Alum.getLoc().setIdLocalidad(rs.getString("Alumnos.Localidad"));
				Alum.getProv().setIdProvincia(rs.getString("Alumnos.Provincia"));
				Alum.getProv().setNombre((rs.getString("Provincias")));
				Alum.setEstado(rs.getBoolean("Alumnos.Estado"));

			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Alum;
	}
	public ArrayList<Alumnos> listarNotas(String Materia,String Parcial,String Condicion,String Nota, int Cuatrimestre, int Año)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Alumnos> lista = new ArrayList<Alumnos>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("SELECT alumnos.LegajoAl, alumnos.Dni,alumnos.Nombre,alumnos.Apellido,alumnos.Email,alumnos.Telefono FROM gesteducativa.alumnos INNER JOIN gesteducativa.notas on notas.LegajoAl=alumnos.LegajoAl INNER JOIN gesteducativa.cursos on cursos.CodCurso = notas.CodCurso INNER JOIN gesteducativa.materias on cursos.CodMateria = materias.CodMateria Where (alumnos.Estado=1) AND (cursos.Año = '"+Año+"') AND (cursos.Cuatrimestre = '"+Cuatrimestre+"') AND (materias.CodMateria = '"+Materia+"')  AND (notas."+Parcial+" "+Condicion+" "+Nota+") and notas.estado=1");

			
			
			while(rs.next()){
				
				Alumnos Alum = new Alumnos();
				Alum.setLegajoAl(rs.getInt("alumnos.LegajoAl"));
				Alum.setDni(rs.getString("alumnos.Dni"));
				Alum.setNombre(rs.getString("alumnos.Nombre"));
				Alum.setApellido(rs.getString("alumnos.Apellido"));	
			
				lista.add(Alum);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	
	}
