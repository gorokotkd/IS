package packProyecto;
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"))
		
		//Gestor.getGestor().cargarGestor();
		Gestor.getGestor().setFiltrado(new FiltradoProductos(new Cos()));
		Gestor.getGestor().cargarGestor();

		HashMap<String,Double> a = Gestor.getGestor().recomendarPeliculasAlUsuario(2048);
		System.out.println(a);
	}

}
