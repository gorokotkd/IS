package packProyecto;

public class Principal {

	public static void main(String[] args) {


		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		Gestor.getGestor().normalizarValoraciones();

	
		System.out.println(Gestor.getGestor().recomendarPeliculasAlUsuario(2048));
	}

}
