package daoimpl;
import java.sql.Connection;
import Entidad.Docentes;
import dao.ProfesorDao;
import negocioimpl.CursoNegimpl;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ProfesorDaoimpl implements ProfesorDao{

	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	public int agregarprofesor(Docentes doc)
	{		
		Docentes Doc = new Docentes();
		
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
			String query = "INSERT INTO docentes (Dni,Nombre,Apellido,Nacimiento,Direccion,Localidad,Provincia,Email,Telefono,Nombre_Usuario,Contraseña,administrador,estado) VALUES('"+doc.getDni()+"','"+doc.getNombre()+"','"+doc.getApellido()+"','"+doc.getNacimiento()+"','"+doc.getDireccion()+"','"+doc.getLoc().getIdLocalidad()+"','"+doc.getProv().getIdProvincia()+"','"+doc.getEmail()+"','"+doc.getTelefono()+"','"+doc.getNombreusuario()+"','"+doc.getContrasena()+"','"+doc.getAdministrador()+"','1');";			
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	
	
	public int modificarprofesor(Docentes Doc)
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
			String query = "Update Docentes SET Nombre='"+Doc.getNombre()+"',Dni='"+Doc.getDni()+"',Apellido='"+Doc.getApellido()+"',Nacimiento='"+Doc.getNacimiento()+"',Direccion='"+Doc.getDireccion()+"',Provincia='"+Doc.getProv().getIdProvincia()+"',Localidad='"+Doc.getLoc().getIdLocalidad()+"',Email='"+Doc.getEmail()+"',Telefono='"+Doc.getTelefono()+"',Nombre_Usuario='"+Doc.getNombreusuario()+"' where LegajoDoc="+Doc.getLegajoDoc();
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}

	public int EliminarDocentes(int Legajo)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//where Dni="+dni;
		int filas=0;
		CursoNegimpl CurNeg = new CursoNegimpl();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Update Docentes SET Estado=0 where LegajoDoc="+Legajo;
			String query2 = "Select codcurso from cursos where LegajoDoc="+Legajo;
			filas=st.executeUpdate(query);
			ResultSet rs =st.executeQuery(query2);
			while(rs.next()) {
				CurNeg.EliminarCurso(rs.getString("codcurso"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	public ArrayList<Docentes> ListarDocentes()
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Docentes> lista = new ArrayList<Docentes>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select docentes.LegajoDoc,Docentes.Dni,Docentes.Nombre,docentes.Apellido,docentes.Nacimiento,docentes.Direccion,docentes.Localidad,localidades.nombre_loc, docentes.Provincia,provincias.Nombre as ProvinciaN,Docentes.Email,Docentes.Telefono,Docentes.Nombre_Usuario,Docentes.Estado from docentes inner join provincias on Docentes.Provincia=provincias.idProvincias inner join localidades on Docentes.Localidad=localidades.idlocalidad_loc where Docentes.Estado=1 and Docentes.administrador=0" + 
					"");
			while(rs.next()){
				
				Docentes Doc = new Docentes();			
				Doc.setLegajoDoc(rs.getInt("docentes.LegajoDoc"));
				Doc.setDni(rs.getString("docentes.Dni"));
				Doc.setNombre(rs.getString("docentes.Nombre"));
				Doc.setApellido(rs.getString("docentes.Apellido"));
				Doc.setTelefono(rs.getString("docentes.Telefono"));
				Doc.setEmail(rs.getString("docentes.Email"));
				Doc.setNacimiento(rs.getString("docentes.Nacimiento"));
				Doc.setDireccion(rs.getString("docentes.Direccion"));
				Doc.getLoc().setIdLocalidad((rs.getString("docentes.Localidad")));
				Doc.getLoc().setNombre((rs.getString("nombre_Loc")));
				Doc.getProv().setIdProvincia((rs.getString("docentes.Provincia")));
				Doc.getProv().setNombre(rs.getString("ProvinciaN"));				
				Doc.setNombreusuario(rs.getString("docentes.Nombre_Usuario"));
				Doc.setEstado(rs.getBoolean("Docentes.Estado"));
				lista.add(Doc);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return lista;
	}
	

	
	public ArrayList<Docentes> obtenernombreDocentes()
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Docentes> listanombreDocentes = new ArrayList<Docentes>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select * FROM Docentes where estado=1" );
			while(rs.next()){
				
				Docentes Prof = new Docentes();
				Prof.setLegajoDoc(rs.getInt("LegajoDoc"));
				Prof.setNombre(rs.getString("Nombre"));
				Prof.setApellido(rs.getString("Apellido"));		
				listanombreDocentes.add(Prof);
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
		return listanombreDocentes;
	}
	
	public int VerificarInicioSecion(String Nombre, String Contraseña)
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
			PreparedStatement miSentencia = con.prepareStatement("Select Nombre,Contraseña from Docentes where Nombre_Usuario = ? and Contraseña=? and estado=1");
			miSentencia.setString(1, Nombre);
			miSentencia.setString(2, Contraseña);
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
	
	public int VerificarUsuario(String Usuario)
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
			PreparedStatement miSentencia = con.prepareStatement("Select Administrador from Docentes where Nombre_Usuario = ? and estado=1");
			miSentencia.setString(1, Usuario);
			ResultSet resultado = miSentencia.executeQuery();
			
		while(resultado.next()){
			filas=resultado.getInt("Administrador");
			}
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	public int VerificarAdmin(String Usuario)
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
			PreparedStatement miSentencia = con.prepareStatement("Select Count(Nombre_Usuario) as contador from Docentes where Nombre_Usuario = ? and estado=1");
			miSentencia.setString(1, Usuario);
			ResultSet resultado = miSentencia.executeQuery();
				
			while(resultado.next()){
				filas = resultado.getInt("contador");
				}
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	public int VerificarModoAdministrador(String Usuario)
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
			PreparedStatement miSentencia = con.prepareStatement("Select Nombre_Usuario from Docentes where Nombre_Usuario = ? and Administrador=1");
			miSentencia.setString(1, Usuario);
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
	
	
	public int VerificarDocentes(String dni)
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
			PreparedStatement miSentencia = con.prepareStatement("Select dni from Docentes where dni = ? ");
			miSentencia.setString(1, dni);			
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
	
	
	public String BuscarNombreDocentes(String Nombre)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String NombreDocentes="1";
        Connection con = null;
        try
        {
            con = DriverManager.getConnection(host+dbName, user,pass);
            Statement st = con.createStatement();
            PreparedStatement miSentencia = con.prepareStatement("Select Nombre from Docentes where Nombre_Usuario = ? and estado=1");
            miSentencia.setString(1, Nombre);
            ResultSet resultado = miSentencia.executeQuery();
            while(resultado.next()){
                NombreDocentes=resultado.getString("Nombre");
                }

            con.close();

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
        return NombreDocentes;
    }	
	public Docentes BuscarDocente(String LegajoDoc)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection cn = null;
		Docentes Doc = new Docentes();
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select Docentes.Administrador, Docentes.contraseña, Docentes.LegajoDoc,Docentes.Dni,Docentes.Nombre,Docentes.Apellido,Docentes.Telefono,Docentes.Email,Docentes.Nacimiento,Docentes.Direccion,Docentes.Localidad,Provincias.Nombre as Provincia,Docentes.Provincia,Docentes.Nombre_Usuario,Docentes.estado,localidades.nombre_loc FROM Docentes inner join provincias on provincias.idProvincias=docentes.Provincia inner join localidades on docentes.Localidad=localidades.idlocalidad_loc where docentes.legajodoc='"+LegajoDoc+"' and estado=1");
			while(rs.next()){			
				Doc.setLegajoDoc(rs.getInt("LegajoDoc"));
				Doc.setDni(rs.getString("Dni"));
				Doc.setNombre(rs.getString("Nombre"));
				Doc.setApellido(rs.getString("Apellido"));
				Doc.setTelefono(rs.getString("Telefono"));
				Doc.setEmail(rs.getString("Email"));
				Doc.setNacimiento(rs.getString("Nacimiento"));
				Doc.getProv().setIdProvincia(rs.getString("Docentes.Provincia"));
				Doc.setDireccion(rs.getString("Direccion"));
				Doc.getLoc().setIdLocalidad(rs.getString("Localidad"));
				Doc.getLoc().setNombre(rs.getString("localidades.nombre_loc"));
				Doc.getProv().setNombre(rs.getString("Provincia"));
				Doc.setNombreusuario(rs.getString("Nombre_Usuario"));
				Doc.setContrasena(rs.getString("Docentes.contraseña"));
				Doc.setAdministrador(rs.getString("Docentes.Administrador"));
				Doc.setEstado(rs.getBoolean("Estado"));
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Doc;
	}
	public int VerificarExistenciaprof(String Usuario)
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
			PreparedStatement miSentencia = con.prepareStatement("Select *  from Docentes where Nombre_Usuario = ? and estado=1");
			miSentencia.setString(1, Usuario);
			ResultSet resultado = miSentencia.executeQuery();
				
			while(resultado.next()){
				filas =1;
				}
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
}
