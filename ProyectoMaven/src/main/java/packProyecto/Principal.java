package packProyecto;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		Gestor.getGestor().elegirFiltrado();
		Gestor.getGestor().recomendarPeliculasAlUsuario(4045);
	}

}
