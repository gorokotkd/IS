package packProyecto;

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
		System.out.println("Normalizando");
		ListaRatings.getListaRatings().normalizar(new Media());
		System.out.println("Lista Peliculas");
		ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/movie-titles.csv");
		System.out.println("Inicializando");
		ListaPeliculas.getListaPeliculas().inicializar();
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
	
	public void recomendarPeliculasAlUsuario(int pUsu)
	{
		if(filtrado instanceof FiltradoPersonas)
		{
			/*System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 161));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 640));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 9331));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 1422));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 462));*/
			((FiltradoPersonas) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoProductos)
		{
			((FiltradoProductos) filtrado).recomendarNPeliculas(pUsu, 10);
		}
		else if(filtrado instanceof FiltradoContenido)
		{
			((FiltradoContenido) filtrado).recomendarNPeliculas(pUsu);
		}
		else
		{
			System.out.println("Al parecer hay algo mal en el tipo de filtrado elegido.");
		}
	}
}
