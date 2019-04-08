package packProyecto;

public class Principal {

	public static void main(String[] args) {
		/*InterfazGrafica interfaz = new InterfazGrafica();
		interfaz.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);*/
		BaseDatos.getBd().cargarBd();
		System.out.println("Usuario: 4045	Pelicula: 2164	--> "+BaseDatos.getBd().getIdoneidad(4045,2164));
		System.out.println("Usuario: 4045	Pelicula: 63	--> "+BaseDatos.getBd().getIdoneidad(4045,63));
		System.out.println("Usuario: 4045	Pelicula: 807	--> "+BaseDatos.getBd().getIdoneidad(4045,807));
		System.out.println("Usuario: 4045	Pelicula: 187	--> "+BaseDatos.getBd().getIdoneidad(4045,187));
		System.out.println("Usuario: 4045	Pelicula: 11	--> "+BaseDatos.getBd().getIdoneidad(4045,11));

	}

}
