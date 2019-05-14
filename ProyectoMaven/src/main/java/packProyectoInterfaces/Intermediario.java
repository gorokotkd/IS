package packProyectoInterfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import packProyecto.Cos;
import packProyecto.FiltradoContenido;
import packProyecto.Gestor;
import packProyecto.ListaPeliculas;
import packProyecto.ListaRatings;
import packProyecto.ListaTags;
import packProyecto.ListaUsuarios;
import packProyecto.ListaValoracionesPorPeli;
import packProyecto.TagsPorPeli;
import packProyecto.Tupla;

public class Intermediario {
	
	private static Intermediario mInter;
	
	private Intermediario() {
	
	}
	
	public static Intermediario getIntermediario()
	{
		if(mInter==null)
			mInter=new Intermediario();
		return mInter;
	}

	
	public void leerFicheroPeliculasTest()
	{
		ListaPeliculas.getListaPeliculas().leerFichero("testMovies.csv");
	}
	
	public void leerFicheroRatingsTest()
	{
		ListaRatings.getListaRatings().leerFichero("testRatings.csv");
	}
	
	public void leerFicheroTagsTest()
	{
		TagsPorPeli.getTagsPorPeli().leerFichero("testTags.csv");
	}
	
	public void leerFicheroPeliculas()
	{
		ListaPeliculas.getListaPeliculas().leerFichero("movie-titles.csv");
	}
	
	public void leerFicheroRatings()
	{
		 ListaRatings.getListaRatings().leerFichero("movie-ratings.csv");
	}
	
	public void leerFicheroTags()
	{
		TagsPorPeli.getTagsPorPeli().leerFichero("movie-tags.csv");
	}
	
	public void crearFiltradoContenido()
	{
		Gestor.getGestor().setFiltrado(new FiltradoContenido(new Cos()));
	}
	
	public HashMap<String,Double> recomendarPeliculasAlUsu(int pUsu)
	{
		return Gestor.getGestor().recomendarPeliculasAlUsuario(pUsu);
	}
	
	public boolean existeUsu(int pUsu)
	{
		return ListaUsuarios.getListaUsuarios().contains(pUsu);
	}
	
	public boolean haValoradoLaPelicula(int pUsu, int pPeli)
	{
		return ListaRatings.getListaRatings().haValoradoLaPelicula(pUsu, pPeli);
	}
	
	public void calificar(int pUsu, int pPeli, double pNota)
	{
		ListaRatings.getListaRatings().anadir(pUsu, pPeli, pNota);
	}
	
	public Set<Entry<Integer, String>> peliculasEntrySet()
	{
		return ListaPeliculas.getListaPeliculas().entrySet();
	}
	
	public ArrayList<Integer> ratingsDevolKeys()
	{
		return ListaRatings.getListaRatings().ratingsDevolKeys();
	}
	
	public ArrayList<Integer> tagsDevolKeys()
	{
		return TagsPorPeli.getTagsPorPeli().tagsDevolKeys();
	}
	
	public ArrayList<Tupla<String, Integer>> getTagsPorId(int pId)
	{
		return TagsPorPeli.getTagsPorPeli().getTagsPorId(pId);
	}
	
	public ArrayList<Tupla<Integer,Double>> getRatingsPorId(int pId)
	{
		return ListaRatings.getListaRatings().getRatingsPorId(pId);
	}
	
	public void clear() {
		ListaPeliculas.getListaPeliculas().clear();
		ListaRatings.getListaRatings().clear();
		TagsPorPeli.getTagsPorPeli().clear();
		ListaTags.getListaTags().clear();
		ListaUsuarios.getListaUsuarios().clear();
		TagsPorPeli.getTagsPorPeli().clear();
	}
}
