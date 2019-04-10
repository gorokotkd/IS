package packProyecto;

public class FiltradoContenido extends Filtrado {

	
	public void recomendar(int pUsu) {
		
		BaseDatos.getBd().recomendarContenido(pUsu);
	}

}