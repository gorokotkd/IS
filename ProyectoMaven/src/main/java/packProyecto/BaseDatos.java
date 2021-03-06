package packProyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;

public class BaseDatos {
	
private static BaseDatos mBd = new BaseDatos();
private Statement st;
private Connection miConexion;
private Peliculas peliculas;
private Ratings ratings;
private TagsPorPeli tagsPorPeli;

	private BaseDatos() {
		cargarBd();
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
			peliculas = new Peliculas();
			System.out.println("Generando tabla resena...\n");
			ratings = new Ratings();
			System.out.println("Generando tabla tagsPorPeli...\n");
			tagsPorPeli = new TagsPorPeli();
			System.out.println("Base De Datos Generada.");	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> ratingsDevolKeys() {
		return ratings.devolKeys();
	}
	public ArrayList<Tupla<Integer,Double>> getRatingsPorId(Integer pId) {
		return ratings.getRatingsPorId(pId);
	}
	
	public Set<Entry<Integer,String>> entrySet() {
		return peliculas.entrySet();
	}	
	
	public ArrayList<Integer> tagsDevolKeys() {
		return tagsPorPeli.tagsDevolKeys(); 
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return tagsPorPeli.getTagsPorId(pId);
	}
}
