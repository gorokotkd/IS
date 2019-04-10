package packProyecto;

public class FiltradoProductos extends Filtrado {

	public FiltradoProductos() {
		
	}

	
	public Double filtrar(int pUsuario, int pPelicula) {
		Double idoneidad = BaseDatos.getBd().getPeliculas().calcularIdoneidad(pUsuario, pPelicula);
		return idoneidad;
	}
	
}
