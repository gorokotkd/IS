package packProyecto;

public class Principal {

	public static void main(String[] args) {
		/*InterfazGrafica interfaz = new InterfazGrafica();
		interfaz.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);*/
		/*BaseDatos.getBd().cargarBd();
		 */
		
		/**FILTRADO BASADO EN CONTENIDO*/
		FiltradoContenido filtrado = new FiltradoContenido();
		filtrado.setSimilitud(new Pearson());
		Gestor.getBd().setFiltrado(filtrado);
		Gestor.getBd().cargarBd();		
		System.out.println("Recomendacion basada en un filtrado por contenido: \n");
		Gestor.getBd().recomendar(1, 0);
		System.out.println("=====================================================================");
		/**FILTRADO BASADO EN PRODUCTO*/
		FiltradoProductos filtradoProducto = new FiltradoProductos();
		filtradoProducto.setSimilitud(new Cos());
		Gestor.getBd().setFiltrado(filtradoProducto);
		Gestor.getBd().cargarBd();
		System.out.println("Nota esperada del usuario 1 para la peli 24 basandose en un filtrado por porducto: \n");
		Gestor.getBd().recomendar(1, 24);
		/**FILTRADO BASADO EN PERSONA*/
		//FiltradoProductos filtradoProducto = new FiltradoProductos();
		filtradoProducto.setSimilitud(new Cos());
		Gestor.getBd().setFiltrado(filtradoProducto);
		Gestor.getBd().cargarBd();
		System.out.println("ALAAAA");
		System.out.println(Gestor.getBd().recomendarPersonas(2048,550));
		
	}

}
