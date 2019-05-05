package packProyecto;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"))
		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		Gestor.getGestor().cargarGestor();
		System.out.println("adasdas");

		Gestor.getGestor().recomendarPeliculasAlUsuario(2048);
	}

}
