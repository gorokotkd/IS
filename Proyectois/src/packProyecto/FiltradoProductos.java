package packProyecto;

public class FiltradoProductos extends Filtrado {

	public FiltradoProductos() {
		
	}

	
	public double filtrar(int pUsuario, int pPelicula) {
		double nota = BaseDatos.getBd().getRatings().obtenerNota(pUsuario, pPelicula);
		if (nota!=-1) {
			nota = nota +BaseDatos.getBd().getRatings().getMedia(pUsuario);
			System.out.println("Esta pelicula ya ha sido valorada, no es posible realizar el filtrado");
			System.out.println("Su nota es: "+nota);
			return nota;
		}else {
			Double idoneidad = BaseDatos.getBd().getPeliculas().calcularIdoneidad(pUsuario, pPelicula);
			return idoneidad;
		}
}
	
}