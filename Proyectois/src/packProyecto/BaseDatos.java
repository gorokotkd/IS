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
private Peliculas peliculas;
private Ratings ratings;
private TagsPorPeli tagsPorPeli;
private Filtrado filtrado;

	private BaseDatos() {
		
	}

	public static BaseDatos getBd()
	{
		if (mBd==null) {
			mBd = new BaseDatos();
		}
		return mBd;
	}
	

	/**public void cargarBd()
	{
		try
		{
			
			peliculas = new Peliculas();
			peliculas.leerFichero();
			System.out.println("Leido peliculas");
			ratings = new Ratings();
			ratings.leerFichero();
			System.out.println("Leido ratings");
			tagsPorPeli = new TagsPorPeli();
			tagsPorPeli.leerFichero();
			System.out.println("Leido TagsPorPeli");
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/

	public void cargarBd()
	{
		try
		{
			peliculas = new Peliculas();
			peliculas.leerFichero();
			ratings = new Ratings();
			ratings.leerFichero();
			if (filtrado instanceof FiltradoProductos) {
				ratings.normalizar();
				ratings.cargarValoraciones();
				peliculas.initMatrizSimilitudes();
			}else {
				tagsPorPeli = new TagsPorPeli();
				tagsPorPeli.leerFichero();
				tagsPorPeli.inicializarFiltradoContenido();
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
}
	
	
	public void generarFiltradoContenido()
	{	
		filtrado = new FiltradoContenido();
		filtrado.setSimilitud(new Cos());
		tagsPorPeli.inicializarFiltradoContenido();
	}
	
	public void generarFiltradoProducto()
	{
		ratings.normalizar();
		filtrado = new FiltradoProductos();
		filtrado.setSimilitud(new Cos());
		System.out.println("Creado filtrado");
		ratings.cargarValoraciones();
		peliculas.initMatrizSimilitudes();
	}
	public boolean estaPeli(int id)
	{
		return peliculas.estaId(id);
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
	
	
	public Peliculas getPeliculas() {
		return peliculas;
	}
	
	public ArrayList<Integer> getIdPeliculas()
	{
		return peliculas.getKeys();
	}
	
		
	public void recomendar(int pUsus,int pPeli)
	{
		if(filtrado instanceof FiltradoContenido)
			((FiltradoContenido)filtrado).recomendar(pUsus);
		else
			((FiltradoProductos)filtrado).filtrar(pUsus, pPeli);
	}
	
	public void recomendarContenido(int pUsu)
	{
		tagsPorPeli.recomendarNPeliculas(pUsu);
	}
	public void eliminarParaRatings() 
	{
		peliculas.eliminar();
		ratings.eliminar();
	}
	
	public void cargarSoloPelis()
	{
		peliculas = new Peliculas();
		peliculas.leerFichero();
	}

	
	public int cuantasPelis()
	{
		return peliculas.size();
	}
	
	public int idMayorPelicula()
	{
		return peliculas.getIdMayor();
	}
	
	public void eliminarBd()
	{
		peliculas.eliminar();
		ratings.eliminar();
		tagsPorPeli.eliminar();
	}
	
	public double obtenerNotaPeliculas(int idUsu, int idPeli)
	{
		return ratings.obtenerNota(idUsu, idPeli);
	}

	public SimilitudStrategy getSimilitud() {
		return filtrado.getSimilitud();
	}
	
	public Filtrado getFiltrado() {
		return filtrado;
	}
	
	public void setFiltrado(Filtrado pFiltrado) {
		this.filtrado = pFiltrado;
	}


}
