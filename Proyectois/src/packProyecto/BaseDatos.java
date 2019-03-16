package packProyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BaseDatos {
	
	public static void main(String[] str)
	{
		cargarBd();
	}

	public static void cargarBd()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			
			//Inicio Conexion
			
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			//Creacion BD
			
			Statement st = miConexion.createStatement();
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

}
