package packProyecto;

import javax.swing.JDialog;

import packProyectoInterfaces.*;


public class Principal {

/*	public static void main(String[] args) {
		
		ListaPeliculas.getListaPeliculas().leerFichero("testMovies.csv");
		ListaRatings.getListaRatings().leerFichero("testRatings.csv");
		//ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
		TagsPorPeli.getTagsPorPeli().leerFichero("testTags.csv");
		Gestor.getGestor().setFiltrado(new FiltradoContenido(new Cos()));
		/*ListaPeliculas.getListaPeliculas().leerFichero("movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("movie-ratings.csv");
		//ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
		TagsPorPeli.getTagsPorPeli().leerFichero("movie-tags.csv");
		//Gestor.getGestor().setFiltrado(new FiltradoPersonas(new Cos()));
		Gestor.getGestor().normalizarValoraciones();
	
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2));
		
	}*/
		/**
		 * ARREGLA LO QUE HAS HECHO AYER, DEL ZSCORE, QUE NO TIENES NI PUTA IDEA
		 */

	public static void main(String[] args) {
		try {
			InterfazEntrada dialog = new InterfazEntrada();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			ListaContraseas.getListaContraseas();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
