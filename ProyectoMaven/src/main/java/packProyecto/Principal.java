package packProyecto;

public class Principal {

	public static void main(String[] args) {
		
		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		ListaPeliculas.getListaPeliculas().leerFichero("/src/main/resources/testMovies.csv");
		ListaRatings.getListaRatings().leerFichero("/src/main/resources/testRatings.csv");
		ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
		TagsPorPeli.getTagsPorPeli().leerFichero("/src/main/resources/testTags.csv");
		//Gestor.getGestor().setFiltrado(new FiltradoPersonas(new Cos()));
		Gestor.getGestor().normalizarValoraciones();
	
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2));
		
		
		/**
		 * ARREGLA LO QUE HAS HECHO AYER, DEL ZSCORE, QUE NO TIENES NI PUTA IDEA
		 */
	}
}
