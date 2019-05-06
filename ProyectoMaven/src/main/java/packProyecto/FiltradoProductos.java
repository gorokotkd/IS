package packProyecto;

import java.util.*;

public class FiltradoProductos extends FiltradoStrategy {

	
	public FiltradoProductos(SimilitudStrategy pSimil) {
		// TODO Auto-generated constructor stub
		this.similitud = pSimil;
	}
	
	
	public Double recomendarPelicula(int pUsu, int pProducto) {
		
		Double rdo = ListaPeliculas.getListaPeliculas().calcularIdoneidad(pUsu, pProducto);
	//	System.out.println("El resultado obtenido: "+rdo);
		
		return rdo;
	}


	@Override
	public HashMap<Integer, Double> recomendarNPeliculas(int idUsu) {
		
		/**
		 * HACER UN BUCLE QUE CALCULE LA IDONEIDAD CON TODAS LAS PELICULAS Y LUEGO DE TODAS ESAS OBTENER TODAS.
		 * 
		 */
	}
	
	/**
	 * MOVER EL FILTRADO DE LISTA A FILTRADOPRODUCTOS
	 */

}
