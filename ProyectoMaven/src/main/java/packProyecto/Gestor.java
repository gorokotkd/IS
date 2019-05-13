package packProyecto;
import java.util.*;

public class Gestor {

	private static Gestor mGestor;
	private FiltradoStrategy filtrado;
	
	private Gestor()
	{
		/**
		 * ESTAS LLAMADAS SE HARAN DESDE LA INTERFAZ YA QUE DEBO PASAR COMO PARAMETROS LOS 
		 * FICHEROS QUE QUIERO LEER, QUE PUEDEN SER LOS TEST O LOS NORMALES.
		 */
		//ListaRatings.getListaRatings().setFichero("/src/main/resources/movie-ratings.csv");
		//ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/movie-titles.csv");
		//TagsPorPeli.getTagsPorPeli().setFichero("/src/main/resources/movie-tags.csv");
	}
	
	public static Gestor getGestor()
	{
		if(mGestor==null)
			mGestor = new Gestor();
		return mGestor;
	}
	
	public void normalizarValoraciones()
	{
		/**
		 * ESTE METODO SE INICIALIZARA TMBN EN LA INTERFAZ, YA QUE PUEDO ELEGIR ENTRE NORMALIZAR O NO, Y
		 * SI NORMALIZAMOS PODEMOS ELEGIR ENTRE USAR Z-SCORE O MEDIA.
		 */
		ListaRatings.getListaRatings().normalizar(new Media());
		
		/**
		 * SI NORMALIZO TENGO QUE PONER ESE BOOLEANO A TRUE SINO LO DEJO EN FALSE.
		 */
		((FiltradoProductos) filtrado).cambiarEstadoNormalizar(true);
	}
	public FiltradoStrategy getFiltrado() {
		return filtrado;
	}
	
	public void setFiltrado(FiltradoStrategy pFiltrado)
	{
		filtrado = pFiltrado;
	}
	
	public HashMap<String,Double> recomendarPeliculasAlUsuario(int pUsu)
	{
		if(filtrado instanceof FiltradoPersonas)
		{
			return ((FiltradoPersonas) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoProductos)
		{
			return ((FiltradoProductos) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoContenido)
		{
			return ((FiltradoContenido) filtrado).recomendarNPeliculas(pUsu);
		}
		else
		{
			System.out.println("Al parecer hay algo mal en el tipo de filtrado elegido.");
			return null;
		}
	}
}
