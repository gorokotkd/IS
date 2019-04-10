package packProyecto;

public class FiltradoContenido extends Filtrado {

	
	public void recomendar(int pUsu) {
		
		BaseDatos.getBd().recomendarContenido(pUsu);
	}

	@Override
	public Double filtrar(int parametro1, int parametro2) {
		// TODO Auto-generated method stub
		return null;
	}

}