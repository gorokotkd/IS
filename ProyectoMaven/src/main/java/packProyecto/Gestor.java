package packProyecto;

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
	
	public void elegirFiltrado()
	{
		/**Aqui se podria elegir el filtrado que se desee e inicializar todo el filtrado,
		 * es decir, todos los modelos de persona, producto, similitudes etc.
		 */
		
		/*filtrado = new FiltradoContenido();
		((FiltradoContenido) filtrado).setSimilitud(new Cos());*/
		filtrado = new FiltradoPersonas(new Cos());
	}
	
	public void recomendarPeliculasAlUsuario(int pUsu)
	{
		if(filtrado instanceof FiltradoPersonas)
		{
			/*System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 161));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 640));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 9331));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 1422));
			System.out.println(((FiltradoPersonas) filtrado).valoracionEstimada(pUsu, 462));*/
			((FiltradoPersonas) filtrado).recomendarNPeliculas(pUsu);
		}
		else if(filtrado instanceof FiltradoProductos)
		{
			
		}
		else if(filtrado instanceof FiltradoContenido)
		{
			((FiltradoContenido) filtrado).recomendarNPeliculas(pUsu);
		}
		else
		{
			System.out.println("Al parecer hay algo mal en el tipo de filtrado elegido.");
		}
	}
}
