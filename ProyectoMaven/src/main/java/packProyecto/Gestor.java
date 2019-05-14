package packProyecto;
import java.util.*;

public class Gestor {

	private static Gestor mGestor;
	private FiltradoStrategy filtrado;
	
	private Gestor()
	{

	}
	
	public static Gestor getGestor()
	{
		if(mGestor==null)
			mGestor = new Gestor();
		return mGestor;
	}
	
	public void normalizarValoraciones()
	{
		ListaRatings.getListaRatings().normalizar(new ZScore());
		
		/**
		 * SI NORMALIZO TENGO QUE PONER ESE BOOLEANO A TRUE SINO LO DEJO EN FALSE.
		 */
		if(filtrado instanceof FiltradoPersonas)
		{
			((FiltradoPersonas) filtrado).cambiarEstadoNormalizar(true);
		}
		else if(filtrado instanceof FiltradoProductos)
		{
			((FiltradoProductos) filtrado).cambiarEstadoNormalizar(true);
		}
		
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
