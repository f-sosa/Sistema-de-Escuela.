package daoimpl;

import java.sql.Connection;
import Entidad.Notas;
import dao.NotasDao;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.Alumnos;
import Entidad.Cursos;
public class NotasDaoimpl implements NotasDao{	
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "gestEducativa";
	
	
	public int BuscarAprobados() {
		
		NotasDaoimpl  NotasDao= new NotasDaoimpl();
		int Aprobaron=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			PreparedStatement miSentencia = cn.prepareStatement("select COUNT(nota1)as cuentas  from Notas where nota1 >=6 and nota2>=6 OR rec1>=6 and nota1>=6 or nota2>=6 and rec2>=6 or nota2>=6 and rec1>=6 or nota1>=6 and rec2>=6");
			
			
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			Aprobaron=resultado.getInt(1);
			
		    cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Aprobaron;
		
		
		
	}	
	
public int BuscarDesaprobados() {
		
		NotasDaoimpl  NotasDao= new NotasDaoimpl();
		int Aprobaron=0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Connection cn = null;
		try
		{
			cn = DriverManager.getConnection(host+dbName, user,pass);
			PreparedStatement miSentencia = cn.prepareStatement("select COUNT(nota1)as cuentas  from Notas where nota1<6 and nota2<6 and rec1<6 and rec2<6 or nota1<6 and rec1<6 or nota2<6 and rec2<6 or nota1<6 and rec2<6 or nota2<6 and rec1<6");
			
			
			ResultSet resultado = miSentencia.executeQuery();
			resultado.next();
			Aprobaron=resultado.getInt(1);
			
		    cn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Aprobaron;

	}	
	

public ArrayList<Notas> listarNotas(Notas not)
{
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    ArrayList<Notas> lista = new ArrayList<Notas>();
    Connection cn = null;
    try
    {
        cn = DriverManager.getConnection(host+dbName, user,pass);
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery ("Select * From Notas inner join alumnos on Notas.LegajoAl=alumnos.LegajoAl where Notas.CodCurso='"+not.getCur().getCodcurso()+"' and Notas.Estado=1");
        while(rs.next()){

            Notas Not = new Notas();
            Not.setNombreAlum(rs.getString("Alumnos.Nombre"));
            Not.getAlum().setLegajoAl(rs.getInt("LegajoAl"));
            Not.getCur().setCodcurso(rs.getInt("Notas.CodCurso"));
            Not.setNota1(rs.getInt("Notas.Nota_1"));
            Not.setNota2(rs.getInt("Notas.Nota_2"));
            Not.setRec1(rs.getInt("Notas.Rec_1"));
            Not.setRec2(rs.getInt("Notas.Rec_2"));
            Not.setEstadoAcademico(rs.getInt("Notas.EstadoAcademico"));
            lista.add(Not);
        }
        cn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return lista;
}
public ArrayList<Notas> listartodasNotas(Notas not)
{
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    ArrayList<Notas> lista = new ArrayList<Notas>();
    Connection cn = null;
    try
    {
        cn = DriverManager.getConnection(host+dbName, user,pass);
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery ("Select Notas.CodCurso,materias.Nombre as Nombre_Materia, alumnos.LegajoAl,alumnos.Nombre,alumnos.apellido,Notas.nota_1,notas.Nota_2,notas.Rec_1,notas.Rec_2,notas.estadoacademico From Notas inner join cursos on notas.CodCurso=cursos.CodCurso inner join materias on cursos.CodMateria=materias.CodMateria inner join alumnos on notas.LegajoAl=alumnos.LegajoAl where notas.CodCurso='"+not.getCur().getCodcurso()+"'and notas.Estado=1");
        while(rs.next()){

            Notas Not = new Notas();
            Not.getCur().setCodcurso((rs.getInt("Notas.CodCurso")));
            Not.getCur().getMat().setNombre(rs.getString("Nombre_Materia"));
            Not.getAlum().setLegajoAl((rs.getInt("alumnos.LegajoAl")));
            Not.getAlum().setNombre(rs.getString("alumnos.Nombre"));
            Not.getAlum().setApellido(rs.getString("alumnos.apellido"));
            Not.setNota1(rs.getInt("notas.Nota_1"));
            Not.setNota2(rs.getInt("notas.Nota_2"));
            Not.setRec1(rs.getInt("notas.Rec_1"));
            Not.setRec2(rs.getInt("notas.Rec_2"));
            Not.setEstadoAcademico(rs.getInt("Notas.estadoacademico"));

            lista.add(Not);
        }
        cn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return lista;
}
public int AñadirNotas(Notas Not)
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
        String query = "insert INTO notas (LegajoAl, CodCurso, Nota_1, Nota_2, Rec_1, Rec_2, Estado, EstadoAcademico) VALUES ( '"+Not.getAlum().getLegajoAl()+"','"+Not.getCur().getCodcurso()+"','"+Not.getNota1()+"','"+Not.getNota2()+"','"+Not.getRec1()+"','"+Not.getRec1()+"', '1', '1')";
        filas=st.executeUpdate(query);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return filas;
}
public int modificarnota(Notas Not)
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
		String query = "UPDATE notas SET `LegajoAl` = '"+Not.getAlum().getLegajoAl()+"', `CodCurso` = '"+Not.getCur().getCodcurso()+"', `Nota_1` = '"+Not.getNota1()+"', `Nota_2` = '"+Not.getNota2()+"', `Rec_1` = '"+Not.getRec1()+"', `Rec_2` = '"+Not.getRec2()+"', `EstadoAcademico` = '"+Not.getEstadoAcademico()+"' WHERE (`CodCurso` = '"+Not.getCur().getCodcurso()+"') AND (`LegajoAl` = '"+Not.getAlum().getLegajoAl()+"')";
		filas=st.executeUpdate(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return filas;
}
public int Eliminarnota(Notas Not)
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
		String query = "UPDATE notas SET `Estado` = '0' WHERE (`CodCurso` = '"+Not.getCur().getCodcurso()+"') AND (`LegajoAl` = '"+Not.getAlum().getLegajoAl()+"')";
		filas=st.executeUpdate(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return filas;
}
public int VerificarAlumnoAlCurso(Notas not )
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
        PreparedStatement miSentencia = con.prepareStatement("Select * from Notas where LegajoAl = ? and CodCurso = ? ");
        miSentencia.setInt(1, not.getAlum().getLegajoAl());
        miSentencia.setInt(2, not.getCur().getCodcurso());
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
public int EliminarAlumnoDeCurso(Notas Not)
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
		String query = "UPDATE notas SET `Estado` = '0' WHERE  `LegajoAl`= '"+Not.getAlum().getLegajoAl()+"'";
		filas=st.executeUpdate(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return filas;
}
public ArrayList<Notas> ListaAlumnosNoExistenteDeEseCurso(Notas not)
{
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    ArrayList<Notas> lista = new ArrayList<Notas>();
    Connection cn = null;
    try
    {
        cn = DriverManager.getConnection(host+dbName, user,pass);
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery ("select LegajoAl,Nombre,Apellido FROM alumnos WHERE NOT EXISTS (SELECT NULL FROM Notas  WHERE notas.LegajoAl = alumnos.LegajoAl and notas.CodCurso='"+not.getCur().getCodcurso()+"' and notas.Estado=1 ) AND alumnos.Estado = 1;");
        while(rs.next()){

            Notas Not = new Notas();
          
            Not.getAlum().setLegajoAl((rs.getInt("LegajoAl")));
            Not.getAlum().setNombre(rs.getString("Nombre"));
            Not.getAlum().setApellido(rs.getString("apellido"));

            lista.add(Not);
        }
        cn.close();
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    return lista;
}
public int CambiarEstadoParaAñadir(Notas Not)
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
		String query = "UPDATE notas SET `Estado` = '1' WHERE (`CodCurso` = '"+Not.getCur().getCodcurso()+"') AND (`LegajoAl` = '"+Not.getAlum().getLegajoAl()+"')";
		filas=st.executeUpdate(query);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return filas;
}
public ArrayList<Notas> listarPromedio(String Materia, int AñoIni,int AñoFin)
{		
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ArrayList<Notas> lista = new ArrayList<Notas>();
	Connection cn = null;
	try
	{
		cn = DriverManager.getConnection(host+dbName, user,pass);
		
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery ("SELECT round(avg(Nota_1)) as Nota_1,round(avg(Nota_2))as Nota_2,round(avg(Rec_1))as Rec_1,round(avg(Rec_2))as Rec_2  FROM gesteducativa.notas INNER JOIN gesteducativa.cursos on notas.CodCurso = cursos.CodCurso where cursos.Estado=1 and cursos.CodMateria= '"+ Materia +"' and cursos.Año>= "+ AñoIni +" and cursos.Año<= "+ AñoFin +" group by cursos.CodMateria");
		
		while(rs.next()){
			
			Notas Not = new Notas();
			Not.setNota1(rs.getInt("Nota_1"));
			Not.setNota2(rs.getInt("Nota_2"));
			Not.setRec1(rs.getInt("Rec_1"));	
			Not.setRec2(rs.getInt("Rec_2"));	
			
			
			lista.add(Not);
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
