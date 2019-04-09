package packProyecto;

public class FiltradoContenido extends Filtrado {

	@Override
	public Double filtrar(int pUsu, int pPeli) {
		
		return BaseDatos.getBd().filtradoContenido(pUsu, pPeli);
	}

}
