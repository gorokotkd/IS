package packProyecto;
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"))
		
		Gestor.getGestor().cargarGestor();
		Gestor.getGestor().setFiltrado(new FiltradoPersonas(new Cos()));
		//Gestor.getGestor().cargarGestor();

		HashMap<Integer,Double> a = Gestor.getGestor().recomendarPeliculasAlUsuario(2048);
		System.out.println(a);
	}

}
