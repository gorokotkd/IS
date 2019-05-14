package packProyecto;

import javax.swing.JDialog;

import packProyectoInterfaces.*;


public class Principal {

/**	public static void main(String[] args) {
		
		//FILTRADO PERSONAS POR CONSOLA
		 
		ListaPeliculas.getListaPeliculas().leerFichero("movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("movie-ratings.csv");
		TagsPorPeli.getTagsPorPeli().leerFichero("movie-tags.csv");
		Gestor.getGestor().setFiltrado(new FiltradoPersonas(new Cos()));
		Gestor.getGestor().normalizarValoraciones();
		
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2048));
		
		
		//FILTRADO PRODUCTOS POR CONSOLA
		 
		
		
		ListaPeliculas.getListaPeliculas().leerFichero("movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("movie-ratings.csv");
		TagsPorPeli.getTagsPorPeli().leerFichero("movie-tags.csv");
		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		ListaPeliculas.getListaPeliculas().inicializarMatrizSimilitudes();
		Gestor.getGestor().normalizarValoraciones();
		 
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2048));
		
		//FILTRADO CONTENIDO POR CONSOLA
		 
		
		ListaPeliculas.getListaPeliculas().leerFichero("movie-titles.csv");
		ListaRatings.getListaRatings().leerFichero("movie-ratings.csv");
		TagsPorPeli.getTagsPorPeli().leerFichero("movie-tags.csv");
		Gestor.getGestor().setFiltrado(new FiltradoContenido(new Cos()));
		 
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2048));
		
	}
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
