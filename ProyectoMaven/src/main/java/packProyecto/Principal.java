package packProyecto;

public class Principal {

	public static void main(String[] args) {

		ListaPeliculas.getListaPeliculas().leerFichero("/src/main/resources/movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("/src/main/resources/movie-ratings.csv");
		TagsPorPeli.getTagsPorPeli().leerFichero("/src/main/resources/movie-tags.csv");
		Gestor.getGestor().setFiltrado(new FiltradoContenido(new Cos()));
		//Gestor.getGestor().normalizarValoraciones();

	
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(4045));
	}

}
