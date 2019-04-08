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
private Similitud similitud;

	private BaseDatos() {
		
	}

	public static BaseDatos getBd()
	{
		if (mBd==null) {
			mBd = new BaseDatos();
		}
		return mBd;
	}

	public void cargarBd()
	{
		try
		{
			
			System.out.println("Generando tabla peliculas...\n");
			peliculas = new Peliculas();
			peliculas.leerFichero();
			System.out.println("Generando tabla resena...\n");
			ratings = new Ratings();
			ratings.leerFichero();
			System.out.println("Generando tabla tagsPorPeli...\n");
			tagsPorPeli = new TagsPorPeli();
			tagsPorPeli.leerFichero();
			System.out.println("Generando Modelado De productos\n");
			tagsPorPeli.generarModeladoDeProductos();
			System.out.println("Generando Modelado De las personas\n");
			tagsPorPeli.modeloPersona();
			System.out.println("Cargando valoraciones\n");
			ratings.cargarValoraciones();
			System.out.println("Normalizando...\n");
			similitud = new Similitud();
			this.filtradoProducto();
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
	
	public Ratings getRatings(){
		if (this.ratings==null) {
			ratings = new Ratings();
		}
		return ratings;
	}
	
	public Similitud getSimilitud() {
		return similitud;
	}
	
	public ArrayList<Integer> getIdPeliculas()
	{
		return peliculas.getKeys();
	}
	
	public double getIdoneidad(int pUsus, int pPelicula)
	{
		return tagsPorPeli.getIdoneidad(pUsus, pPelicula);
	}
	
	public int cuantasPelis()
	{
		return peliculas.size();
	}
	
	public int idMayorPelicula()
	{
		return peliculas.getIdMayor();
	}
	
	public void filtradoProducto() {
		this.peliculas.initMatrizSimilitudes();
		this.peliculas.calcularIdoneidad(2048, 77);
	}
	
	public void eliminarBd()
	{
		peliculas.eliminar();
		ratings.eliminar();
		tagsPorPeli.eliminar();
	}
	
	public void cargarSoloPelis()
	{
		peliculas = new Peliculas();
		peliculas.leerFichero();
	}
	
}
