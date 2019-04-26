package packProyecto;

import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;

public class BaseDatos {

	private static BaseDatos mBd = new BaseDatos();
	private Peliculas peliculas;
	private Ratings ratings;
	private TagsPorPeli tagsPorPeli;
	private Filtrado filtrado;

	private BaseDatos() {

	}

	public static BaseDatos getBd() {
		if (mBd == null) {
			mBd = new BaseDatos();
		}
		return mBd;
	}

	public SimilitudStrategy getSimilitud() {
		return filtrado.getSimilitud();
	}

	public Ratings getRatings() {
		if (this.ratings == null) {
			ratings = new Ratings();
		}
		return ratings;
	}

	public Peliculas getPeliculas() {
		return peliculas;
	}

	public Filtrado getFiltrado() {
		return filtrado;
	}

	public TagsPorPeli getTagsPorPeli() {
		return tagsPorPeli;
	}

	public void setFiltrado(Filtrado pFil) {
		this.filtrado = pFil;
	}

	public void cargarBd() {
		try {

			peliculas = new Peliculas();
			peliculas.leerFichero();
			ratings = new Ratings();
			ratings.leerFichero();
			if (filtrado instanceof FiltradoProductos) {
				ratings.normalizar();
				ratings.cargarValoraciones();
				peliculas.initMatrizSimilitudes();
			} else {
				tagsPorPeli = new TagsPorPeli();
				tagsPorPeli.leerFichero();
				tagsPorPeli.inicializarFiltradoContenido();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generarFiltradoContenido() {
		filtrado = new FiltradoContenido();
		filtrado.setSimilitud(new Cos());
		tagsPorPeli.inicializarFiltradoContenido();
	}

	public void generarFiltradoProducto() {
		ratings.normalizar();
		filtrado = new FiltradoProductos();
		filtrado.setSimilitud(new Cos());
		System.out.println("Creado filtrado");
		ratings.cargarValoraciones();
		peliculas.initMatrizSimilitudes();
	}

	public void cargarTodo() {
		peliculas = new Peliculas();
		peliculas.leerFichero();

		ratings = new Ratings();
		ratings.leerFichero();
		ratings.normalizar();

		ratings.cargarValoraciones();
		peliculas.initMatrizSimilitudes();

	}

	public void recomendar(int pUsus, int pPeli) {
		if (filtrado instanceof FiltradoContenido)
			((FiltradoContenido) filtrado).recomendar(pUsus);
		else
			((FiltradoProductos) filtrado).filtrar(pUsus, pPeli);
	}

	public ArrayList<Tupla<Integer, Double>> getRatingsPorId(Integer pId) {
		return ratings.getRatingsPorId(pId);
	}

	public ArrayList<Integer> ratingsDevolKeys() {
		return ratings.devolKeys();
	}

	public Set<Entry<Integer, String>> entrySet() {
		return peliculas.entrySet();
	}

	public ArrayList<Integer> tagsDevolKeys() {
		return tagsPorPeli.tagsDevolKeys();
	}

	public ArrayList<Tupla<String, Integer>> getTagsPorId(Integer pId) {
		return tagsPorPeli.getTagsPorId(pId);
	}

	public double obtenerNotaPeliculas(int idUsu, int idPeli) {
		return ratings.obtenerNota(idUsu, idPeli);
	}

	public ArrayList<Integer> getIdPeliculas() {
		return peliculas.getKeys();
	}

	// TODO A partir de aqui solo hay metodos de los jUnit
	public int idMayorPelicula() {
		return peliculas.getIdMayor();
	}

	public void eliminarBd() {
		peliculas.eliminar();
		ratings.eliminar();
		tagsPorPeli.eliminar();
	}

	public void eliminarRatingsPeliculas() {
		peliculas.eliminar();
		ratings.eliminar();
	}

	public void cargarSoloPelis() {
		peliculas = new Peliculas();
		peliculas.leerFichero();
	}

	public double recomendarPersonas(int usu, int peli) {
		return ratings.valoracionEstimada(usu, peli);
	}

}
