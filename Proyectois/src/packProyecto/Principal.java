package packProyecto;

public class Principal {

	public static void main(String[] args) {
		/*InterfazGrafica interfaz = new InterfazGrafica();
		interfaz.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);*/
		System.out.println(ListaTags.getListaTags().tamano());
		System.out.println(ListaUsuarios.getListaUsuarios().size());
		BaseDatos.getBd().cargarBd();
		BaseDatos.getBd().kk();

	}

}
