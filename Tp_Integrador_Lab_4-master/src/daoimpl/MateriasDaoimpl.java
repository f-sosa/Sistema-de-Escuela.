package daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Materias;
import dao.MateriasDao;

public class MateriasDaoimpl implements MateriasDao{
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	
	public ArrayList<Materias> listarmaterias()
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Materias> lista = new ArrayList<Materias>();
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select * from Materias");
			while(rs.next()){
				
				Materias Mat = new Materias();
				Mat.setCodMateria(rs.getString("CodMateria"));
				Mat.setNombre(rs.getString("Nombre"));

				lista.add(Mat);
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
