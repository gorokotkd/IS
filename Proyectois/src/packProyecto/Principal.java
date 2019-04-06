package packProyecto;

import javax.swing.JDialog;

public class Principal {

	public static void main(String[] args) {
		/*InterfazGrafica interfaz = new InterfazGrafica();
		interfaz.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		interfaz.setVisible(true);
		*/
		BaseDatos.getBd().cargarBd();
	}

}
