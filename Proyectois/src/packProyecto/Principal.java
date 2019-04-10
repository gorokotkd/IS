package packProyecto;

public class Principal {

	public static void main(String[] args) {
		/*InterfazGrafica interfaz = new InterfazGrafica();
		interfaz.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);*/
	/**	FiltradoProductos filtrado = new FiltradoProductos();
		filtrado.setSimilitud(new Cos());
		BaseDatos.getBd().setFiltrado(filtrado);
		BaseDatos.getBd().cargarBd();*/
		FiltradoContenido filtrado = new FiltradoContenido();
		filtrado.setSimilitud(new Cos());
		BaseDatos.getBd().setFiltrado(filtrado);
		BaseDatos.getBd().cargarBd();
	//	BaseDatos.getBd().generarFiltradoContenido();
		//BaseDatos.getBd().generarFiltradoProducto();
		BaseDatos.getBd().recomendar(4045, 161);
		/*System.out.println("Usuario: 4045	Pelicula: 2164	--> "+BaseDatos.getBd().filtradoContenido(4045,2164));
		System.out.println("Usuario: 4045	Pelicula: 63	--> "+BaseDatos.getBd().filtradoContenido(4045,63));
		System.out.println("Usuario: 4045	Pelicula: 807	--> "+BaseDatos.getBd().filtradoContenido(4045,807));
		System.out.println("Usuario: 4045	Pelicula: 187	--> "+BaseDatos.getBd().filtradoContenido(4045,187));
		System.out.println("Usuario: 4045	Pelicula: 11	--> "+BaseDatos.getBd().filtradoContenido(4045,11));*/
		//BaseDatos.getBd().recomendar(1);

		
/*		//BaseDatos.getBd().cargarBd();
	//	BaseDatos.getBd().filtrar();
		BaseDatos.getBd().generarFiltradoProducto();
		BaseDatos.getBd().getFiltrado();
		System.out.println("Antes daba: 3.037939937776456");
		BaseDatos.getBd().getFiltrado().filtrar(1, 14);
		System.out.println();
		
		System.out.println("Antes daba: 4.188019662154371");
		BaseDatos.getBd().getFiltrado().filtrar(1, 161);
		System.out.println();
		
		System.out.println("Antes daba: 2.7687439656654873 -- 4,3711");
		BaseDatos.getBd().getFiltrado().filtrar(1, 187);
		System.out.println();
		
		System.out.println("Antes daba: 2.7924519607562974 -- 4,3503");
		BaseDatos.getBd().getFiltrado().filtrar(1, 641);
		System.out.println();	*/
	}

}