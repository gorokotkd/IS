package packProyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BaseDatos {
	
private static BaseDatos mBd = new BaseDatos();
private Statement st;
private Connection miConexion;
private TablaPeliculas tablaPeliculas;
private TablaResena tablaResena;
private TablaTag tablaTag;
private TablaTiene tablaTiene;

private BaseDatos() {
	try {
		miConexion = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		st = miConexion.createStatement();
		String comando = "create database euskoflix";
		st.executeUpdate(comando);
		miConexion = DriverManager.getConnection("jdbc:mysql://localhost/euskoflix?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		st = miConexion.createStatement();
	//	cargarBd();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	//cargarBd();
	}

	public static BaseDatos getBd()
	{
		return mBd;
	}

	public void cargarBd()
	{
		try
		{
			
			System.out.println("Generando tabla peliculas...\n");
			tablaPeliculas = new TablaPeliculas();
			System.out.println("Generando tabla resena...\n");
			tablaResena = new TablaResena();
			System.out.println("Generando tabla tag...\n");
			tablaTag = new TablaTag();
			System.out.println("Generando tabla tiene...\n");
			tablaTiene = new TablaTiene();
			System.out.println("Base De Datos Generada.");
			
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void eliminarBD()
	{
		String comando = "drop database euskoflix;";
		try {
			st.executeUpdate(comando);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizar(String sql)
	{
		try {
			st.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet hacerConsulta(String sql)
	{
		try {
			ResultSet resul = st.executeQuery(sql);
			return resul;
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<String> tagArray()
	{
		return tablaTag.tablaArray();
	}

}
