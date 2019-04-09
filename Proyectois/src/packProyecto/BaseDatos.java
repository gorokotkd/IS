package packProyecto;

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
	

	public void cargarBd()
	{
		try
		{
			if (filtrado instanceof FiltradoProductos) {
				peliculas = new Peliculas();
				peliculas.leerFichero();
				System.out.println("Leido peliculas");
				
				ratings = new Ratings();
				ratings.leerFichero();
				ratings.normalizar();
				System.out.println("Leido ratings y normalizado");
				
				ratings.cargarValoraciones();
				peliculas.initMatrizSimilitudes();
			}else {
				tagsPorPeli.generarModeladoDeProductos();
				System.out.println("Modelo Del producto Generado");
				
				tagsPorPeli.modeloPersona();
				System.out.println("Modelo de la persona Generado");
			}
			
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
	
	
	public Peliculas getPeliculas() {
		return peliculas;
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

	public SimilitudStrategy getSimilitud() {
		return filtrado.getSimilitud();
	}
	
	public Filtrado getFiltrado() {
		return filtrado;
	}
	
	public void setFiltrado(Filtrado pFil) {
		this.filtrado = pFil;
	}

}
