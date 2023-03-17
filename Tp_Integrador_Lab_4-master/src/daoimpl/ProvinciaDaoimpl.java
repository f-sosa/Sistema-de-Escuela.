package daoimpl;
import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Entidad.Provincias;
import dao.ProvinciaDao;
public class ProvinciaDaoimpl implements ProvinciaDao{
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	
	public ArrayList<Provincias> ListarProvincias() {				
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			ArrayList<Provincias> lista = new ArrayList<Provincias>();
			Connection cn = null;
			try
			{
				cn = DriverManager.getConnection(host+dbName, user,pass);
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery ("Select * FROM Provincias ");
				while(rs.next()){
				 Provincias Prov = new Provincias();				
					Prov.setIdProvincia(rs.getString("IdProvincias"));
					Prov.setNombre(rs.getString("Nombre"));		
					lista.add(Prov);
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
