package packProyecto;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"))
		Gestor.getGestor().cargarGestor();
		Gestor.getGestor().setFiltrado(new FiltradoContenido(new Cos()));
		//Gestor.getGestor().cargarGestor();

		Gestor.getGestor().recomendarPeliculasAlUsuario(4045);
	}

}
