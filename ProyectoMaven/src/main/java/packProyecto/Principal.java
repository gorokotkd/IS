package packProyecto;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		ListaPeliculas.getListaPeliculas().leerFichero();
		ListaTags.getListaTags().leerFichero();
		ListaRatings.getListaRatings().leerFichero();
		ListaUsuarios.getListaUsuarios().leerFichero();
		TagsPorPeli.getTagsPorPeli().leerFichero();
		Gestor.getGestor().elegirFiltrado();
		Gestor.getGestor().recomendarPeliculasAlUsuario(2048);
	}

}
