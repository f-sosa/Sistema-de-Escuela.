package daoimpl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Localidad;
import Entidad.Provincias;
import dao.LocalidadesDao;

public class LocalidadesDaoimpl implements LocalidadesDao{
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	
	
	public ArrayList<Localidad> CargarLocalidades(String idprov)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select * from Localidades where idprovincia_loc='"+idprov+"'");
			while(rs.next()){
				
				Localidad Loc = new Localidad();
				Provincias Prov= new Provincias();
				Loc.setIdLocalidad(rs.getString("idLocalidad_Loc"));
				Prov.setIdProvincia(rs.getString("idprovincia_loc"));
				Loc.setNombre(rs.getString("nombre_loc"));
				Loc.setProv(Prov);
				lista.add(Loc);
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
