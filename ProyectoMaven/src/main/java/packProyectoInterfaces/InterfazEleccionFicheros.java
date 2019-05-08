package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packProyecto.ListaPeliculas;
import packProyecto.ListaRatings;
import packProyecto.ListaTags;
import packProyecto.ListaUsuarios;
import packProyecto.TagsPorPeli;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class InterfazEleccionFicheros extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazEleccionFicheros dialog = new InterfazEleccionFicheros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazEleccionFicheros() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		final JLabel lblPorFavorSelecciones = new JLabel("Por favor selecciones derecha se desea los ficheros");
		
		final JLabel lblStandardOIzquierda = new JLabel("standard o izquierda si desea los de tipo test");
		
		final JRadioButton rdbtnStandard = new JRadioButton("Standard");
		
		final JRadioButton rdbtnTest = new JRadioButton("Test");
		final JProgressBar progressBar25 = new JProgressBar();
		final JProgressBar progressBar50 = new JProgressBar();
		final JProgressBar progressBar75 = new JProgressBar();
		final JProgressBar progressBar100 = new JProgressBar();
		
		ButtonGroup grupoDeBotones;
		grupoDeBotones = new ButtonGroup();
		grupoDeBotones.add(rdbtnTest);
		grupoDeBotones.add(rdbtnStandard);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(90)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(rdbtnStandard)
							.addPreferredGap(ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
							.addComponent(rdbtnTest)
							.addGap(95))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPorFavorSelecciones)
								.addComponent(lblStandardOIzquierda, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(58, Short.MAX_VALUE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPorFavorSelecciones, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStandardOIzquierda)
					.addGap(56)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnStandard)
						.addComponent(rdbtnTest))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("ACEPTAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (rdbtnTest.isSelected()) {
							ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/testMovies.csv");
							System.out.println("he terminado con el 1º)");
							System.out.println("25%");
							ListaTags.getListaTags().leerFicheroTest();
							System.out.println("50%");
							ListaRatings.getListaRatings().setFichero("/src/main/resources/testRatings.csv");;
							System.out.println("75%");
							ListaUsuarios.getListaUsuarios().leerFicheroTest();
							System.out.println("100%");
							TagsPorPeli.getTagsPorPeli().setFichero("/src/main/resources/testTags.csv");;
							InterfazGrafica ac = new InterfazGrafica();
							ac.setVisible(true);
							dispose();
						}
						else if (rdbtnStandard.isSelected()) {
							ListaPeliculas.getListaPeliculas().setFichero("/src/main/resources/movie-titles.csv");;
							ListaTags.getListaTags().leerFichero();
							ListaRatings.getListaRatings().setFichero("/src/main/resources/movie-ratings.csv");
							ListaUsuarios.getListaUsuarios().leerFichero();
							TagsPorPeli.getTagsPorPeli().setFichero("/src/main/resources/movie-tags.csv");
							InterfazGrafica ac = new InterfazGrafica();
							ac.setVisible(true);
							dispose();
						}
						else {
							lblPorFavorSelecciones.setText("ELIGE UNA DE LAS DOS O");
							lblStandardOIzquierda.setText("TE ARRANCO LA CABEZA PAYASO"); 
						}
						
					}
				});
				
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
