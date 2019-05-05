package packProyecto;

public class FiltradoProductos extends FiltradoStrategy {

	
	public FiltradoProductos(SimilitudStrategy pSimil) {
		// TODO Auto-generated constructor stub
		this.similitud = pSimil;
	}
	
	
	public void recomendarNPeliculas(int pUsu, int pProducto) {
		
		Double rdo = ListaPeliculas.getListaPeliculas().calcularIdoneidad(pUsu, pProducto);
		System.out.println("El resultado obtenido: "+rdo);
	}

}
