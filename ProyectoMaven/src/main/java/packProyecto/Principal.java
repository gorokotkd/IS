package packProyecto;

public class Principal {

	public static void main(String[] args) {
		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		ListaPeliculas.getListaPeliculas().leerFichero("/src/main/resources/movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("/src/main/resources/movie-ratings.csv");
		ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
		TagsPorPeli.getTagsPorPeli().leerFichero("/src/main/resources/movie-tags.csv");
		
		//Gestor.getGestor().normalizarValoraciones();

	
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(4045));
	}
}
