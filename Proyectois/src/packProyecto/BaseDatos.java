package packProyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
	
private static BaseDatos mBd;
private Statement st;
private Connection miConexion;

private BaseDatos() {
	try {
		//Class.forName("com.mysql.jdbc.Driver");
		miConexion = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
		st = miConexion.createStatement();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	cargarBd();
	}

public static BaseDatos getBd()
{
	if(mBd==null)
		mBd = new BaseDatos();
	return mBd;
}

	private void cargarBd()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			
			//Inicio Conexion
			
		//	Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			//Creacion BD
			
			//Statement st = miConexion.createStatement();
			String comando = "create database euskoflix";
			st.executeUpdate(comando);
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost/euskoflix?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			st = miConexion.createStatement();
			System.out.println("Generando tabla peliculas...\n");
			TablaPeliculas.getTablaPeliculas().generarTabla(st);
			System.out.println("Generando tabla resena...\n");
			TablaResena.getTablaResena().generarTabla(st);
			System.out.println("Generando tabla tag...\n");
			TablaTag.getTablaTag().generarTabla(st);
			System.out.println("Generando tabla tiene...\n");
			TablaTiene.getTablaTiene().generarTabla(st);
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

}
