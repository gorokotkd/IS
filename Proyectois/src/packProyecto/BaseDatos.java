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
<<<<<<< HEAD
		cargarBd();
=======
		//cargarBd();
>>>>>>> parent of 5179f82... Todo Bien solo quedan JUnits
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
<<<<<<< HEAD
=======
			tagsPorPeli.leerFichero();
			System.out.println("Generando Modelado De productos\n");
			tagsPorPeli.generarModeladoDeProductos();
			System.out.println("Generando Modelado De las personas\n");
			tagsPorPeli.modeloPersona();
		/*	System.out.println("Cargando valoraciones\n");
			ratings.cargarValoraciones();
			System.out.println("Normalizando...\n");
			ratings.normalizar();
			similitud = new Similitud();
			this.filtradoProducto();*/
>>>>>>> parent of 5179f82... Todo Bien solo quedan JUnits
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
	
<<<<<<< HEAD
	public Ratings getRatings()
	{return ratings;}
=======
	public Ratings getRatings(){
		if (this.ratings==null) {
			ratings = new Ratings();
		}
		return ratings;
	}
	
	public Similitud getSimilitud() {
		/*if (similitud==null) {
			similitud = new Similitud();
		}*/
		return similitud;
	}
	
	public void filtradoProducto() {
		this.peliculas.initMatrizSimilitudes();
	}
	
	public double getIdoneidad(int pUsus, int pPelicula)
	{
		return tagsPorPeli.getIdoneidad(pUsus, pPelicula);
	}
	
	public int cuantasPelis()
	{
		return peliculas.size();
	}
	
	public void eliminarBd()
	{
		peliculas.eliminar();
		ratings.eliminar();
		tagsPorPeli.eliminar();
	}
>>>>>>> parent of 5179f82... Todo Bien solo quedan JUnits
}
