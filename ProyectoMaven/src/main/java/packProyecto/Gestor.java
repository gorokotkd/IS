package packProyecto;
import java.util.*;

public class Gestor {

	private static Gestor mGestor;
	private FiltradoStrategy filtrado;
	
	private Gestor()
	{
		
	}
	
	public static Gestor getGestor()
	{
		if(mGestor==null)
			mGestor = new Gestor();
		return mGestor;
	}
	
	public void cargarGestor() {
		System.out.println("Lista Ratings");
		ListaRatings.getListaRatings().setFichero("/src/main/resources/movie-ratings.csv");
		ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/movie-titles.csv");
		TagsPorPeli.getTagsPorPeli().setFichero("/src/main/resources/movie-tags.csv");
		System.out.println("Normalizando");
		ListaRatings.getListaRatings().normalizar(new Media());
		System.out.println("Lista Peliculas");
		
		
		
		ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/movie-titles.csv");
		System.out.println("Inicializando");
		//ListaPeliculas.getListaPeliculas().inicializar();
	//	TagsPorPeli.getTagsPorPeli().setFichero("/src/main/resources/movie-tags.csv");
		
	}
	public FiltradoStrategy getFiltrado() {
		return filtrado;
	}
	
	public void setFiltrado(FiltradoStrategy pFiltrado)
	{
		/**Aqui se podria elegir el filtrado que se desee e inicializar todo el filtrado,
		 * es decir, todos los modelos de persona, producto, similitudes etc.
		 */
		
		/*filtrado = new FiltradoContenido();
		((FiltradoContenido) filtrado).setSimilitud(new Cos());*/
		//filtrado = new FiltradoPersonas(new Cos());
		filtrado = pFiltrado;
	}
	
	public HashMap<String,Double> recomendarPeliculasAlUsuario(int pUsu)
	{
		if(filtrado instanceof FiltradoPersonas)
		{
			return ((FiltradoPersonas) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoProductos)
		{
			return ((FiltradoProductos) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoContenido)
		{
			return ((FiltradoContenido) filtrado).recomendarNPeliculas(pUsu);
		}
		else
		{
			System.out.println("Al parecer hay algo mal en el tipo de filtrado elegido.");
			return null;
		}
	}
}
