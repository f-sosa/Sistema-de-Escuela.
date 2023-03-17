package daoimpl;
import java.sql.Connection;

import Entidad.Alumnos;
import Entidad.Cursos;
import dao.CursoDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.Statement;
import Entidad.Cursos;

public class CursoDaoimpl implements CursoDao{	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	public int agregarCurso(Cursos Cur)
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
            String query = "insert INTO cursos (CodMateria, Cuatrimestre, Año, LegajoDoc, Estado) VALUES ( '"+Cur.getMat().getCodMateria()+"','"+Cur.getCuatrimestre()+"','"+Cur.getAno()+"','"+Cur.getDoc().getLegajoDoc()+"','1');";
            filas=st.executeUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return filas;
    }
	public ArrayList<Cursos> listarcursos()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Cursos> lista = new ArrayList<Cursos>();
        Connection cn = null;
        try
        {
            cn = DriverManager.getConnection(host+dbName, user,pass);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("Select Cursos.CodCurso,materias.Nombre, materias.Codmateria,cursos.Cuatrimestre,cursos.Año,docentes.LegajoDoc,docentes.Nombre as NombreProf,docentes.Apellido from Cursos inner join materias on cursos.CodMateria=materias.CodMateria inner join docentes on cursos.LegajoDoc=docentes.LegajoDoc where cursos.Estado=1");
            while(rs.next()){
                Cursos Cur = new Cursos();
                Cur.setCodcurso(rs.getInt("Cursos.CodCurso"));
                Cur.getDoc().setLegajoDoc(rs.getInt("docentes.LegajoDoc"));
                Cur.getDoc().setNombre(rs.getString("NombreProf"));
                Cur.getDoc().setApellido(rs.getString("docentes.apellido"));
                Cur.getMat().setCodMateria(rs.getString("materias.Codmateria"));
                Cur.getMat().setNombre(rs.getString("materias.nombre"));
                Cur.setCuatrimestre(rs.getInt("cursos.Cuatrimestre"));
                Cur.setAno(rs.getInt("cursos.Año"));
                lista.add(Cur);
            }
            cn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }
	public int EliminarCurso(String codcurso)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int Curso=0;
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			String query = "Update Cursos SET estado=0 where CodCurso="+codcurso;
			Curso=st.executeUpdate(query);
			String query2 = "Update notas SET estado=0 where CodCurso="+codcurso;
			st.executeUpdate(query2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Curso;
	}
	public int modificarcurso(Cursos Cur)
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
			String query = "UPDATE cursos SET `CodMateria` = '"+Cur.getMat().getCodMateria()+"', `Cuatrimestre` = '"+Cur.getCuatrimestre()+"', `Año` = '"+Cur.getAno()+"', `LegajoDoc` = '"+Cur.getDoc().getLegajoDoc()+"' WHERE (`CodCurso` = '"+Cur.getCodcurso()+"');";
			filas=st.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filas;
	}
	
	public Cursos BuscarCurso(int CodCurso)
	{		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Cursos> lista = new ArrayList<Cursos>();
		Connection cn = null;
		Cursos Cur = new Cursos();
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery ("Select * from Cursos where cursos.CodCurso='"+CodCurso+"' and cursos.estado=1");
			while(rs.next()){
								
                Cur.setCodcurso(rs.getInt("CodCurso"));
                Cur.getMat().setCodMateria(rs.getString("CodMateria"));
                Cur.setCuatrimestre(rs.getInt("Cuatrimestre"));
                Cur.setAno(rs.getInt("Año"));
                Cur.getDoc().setLegajoDoc(rs.getInt("LegajoDoc"));
                Cur.setEstado(rs.getBoolean("Estado"));
																								
			}
			cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
		return Cur;
	}

	
	
	
	public ArrayList<Cursos> obtenercodCursosxprofesor(String Usuario)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Cursos> lista = new ArrayList<Cursos>();
        Connection cn = null;
        try
        {
            cn = DriverManager.getConnection(host+dbName, user,pass);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("Select Cursos.CodCurso,materias.Nombre,cursos.Cuatrimestre,cursos.Año,docentes.LegajoDoc,docentes.Nombre as NombreProf,docentes.Apellido from Cursos inner join materias on cursos.CodMateria=materias.CodMateria inner join docentes on cursos.LegajoDoc=docentes.LegajoDoc where Docentes.Nombre_Usuario= '"+Usuario+"' and cursos.Estado=1");
            while(rs.next()){
                Cursos Cur = new Cursos();
                Cur.setCodcurso(rs.getInt("Cursos.CodCurso"));
                Cur.getDoc().setLegajoDoc(rs.getInt("docentes.LegajoDoc"));
                Cur.getDoc().setNombre(rs.getString("NombreProf"));
                Cur.getDoc().setApellido(rs.getString("docentes.apellido"));
                Cur.getMat().setNombre(rs.getString("materias.nombre"));
                Cur.setCuatrimestre(rs.getInt("cursos.Cuatrimestre"));
                Cur.setAno(rs.getInt("cursos.Año"));
                lista.add(Cur);
            }
            cn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lista;
    }
	
	public ArrayList<Cursos> ListarAños()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayList<Cursos> lista = new ArrayList<Cursos>();
        Connection cn = null;
        try
        {
            cn = DriverManager.getConnection(host+dbName, user,pass);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT Año FROM cursos where estado=1" );
            while(rs.next()){
                Cursos Cur = new Cursos();

                Cur.setAno(rs.getInt("Año"));
                lista.add(Cur);
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
