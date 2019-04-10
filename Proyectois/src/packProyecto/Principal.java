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
		BaseDatos.getBd().setFiltrado(filtrado);
		BaseDatos.getBd().cargarBd();		
		System.out.println("Recomendacion basada en un filtrado por contenido: \n");
		BaseDatos.getBd().recomendar(1, 0);
		System.out.println("=====================================================================");
		/**FILTRADO BASADO EN PRODUCTO*/
		FiltradoProductos filtradoProducto = new FiltradoProductos();
		filtradoProducto.setSimilitud(new Cos());
		BaseDatos.getBd().setFiltrado(filtradoProducto);
		BaseDatos.getBd().cargarBd();
		System.out.println("Nota esperada del usuario 1 para la peli 24 basandose en un filtrado por porducto: \n");
		BaseDatos.getBd().recomendar(1, 24);
	}

}
