package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import packProyectoInterfaces.ListaContraseas;
import packProyecto.ListaPeliculas;
import packProyecto.ListaRatings;
import packProyecto.ListaUsuarios;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazNota extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox jcb;
	private JTextField txtIdDeLa;
	private JTextField txtIdDelUsuario;
	private JTextField txtNota;
	private JTextPane txtpnIntroduzcaLosDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazNota dialog = new InterfazNota();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazNota() {
		setBounds(500, 500, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		jcb = new JComboBox();
		jcb.setFont(new Font("Unispace", Font.PLAIN, 11));
		jcb.setEditable(true);
		getBox();
		AutoCompleteDecorator.decorate(jcb);
		
		txtIdDeLa = new JTextField();
		txtIdDeLa.setText("Id De La Pelicula");
		txtIdDeLa.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtIdDeLa.setColumns(10);
		txtIdDelUsuario = new JTextField();
		txtIdDelUsuario.setText("Id Del Usuario");
		txtIdDelUsuario.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtIdDelUsuario.setColumns(10);
		
		txtNota = new JTextField();
		txtNota.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtNota.setText("Nota");
		txtNota.setColumns(10);
		
		txtpnIntroduzcaLosDatos = new JTextPane();
		txtpnIntroduzcaLosDatos.setEditable(false);
		txtpnIntroduzcaLosDatos.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtpnIntroduzcaLosDatos.setText("Introduzca los datos necesarios para que podamos recomendarle mas peliculas proximamente");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtIdDelUsuario, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtIdDeLa, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
							.addComponent(txtpnIntroduzcaLosDatos, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(jcb, 0, 454, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtIdDeLa, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtIdDelUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtNota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtpnIntroduzcaLosDatos, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(jcb, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!txtIdDeLa.getText().equals(null) && !txtIdDelUsuario.getText().equals(null) && !txtNota.getText().equals(null) ) {
							String idUsu = txtIdDelUsuario.getText();
							String idPeli = txtIdDeLa.getText();
							String nota = txtNota.getText();
							
							if (ListaContraseas.getListaContraseas().contains(idUsu)) {
								txtpnIntroduzcaLosDatos.setText("Por favor, escriba su id de usuario. Puede saber cual es su id en el apartado de configuracion");
							}
							
							else if (isNumeric(idUsu)) {
								Integer idU = Integer.parseInt(idUsu);
								if (Intermediario.getIntermediario().existeUsu(idU)) {									
									if (isNumeric(idPeli)) {
										Integer iPe = Integer.parseInt(idPeli);
										if (!Intermediario.getIntermediario().haValoradoLaPelicula(idU, iPe)) {
											if (isNumeric(nota)) {
												Double not = Double.parseDouble(nota);			
												if ((not>=0.0)&&(not<=5.0)) {
													Intermediario.getIntermediario().calificar(idU, iPe, not);
													txtpnIntroduzcaLosDatos.setText("INTRODUCIDO CORRECTAMENTE, GRACIAS");
												}
												else {
													txtpnIntroduzcaLosDatos.setText("La nota debe estar entre 0.0 y 5.0");
												}
											}
											else {
												txtpnIntroduzcaLosDatos.setText("Escriba bien la nota porfavor");
											}										
										}
										else {
											txtpnIntroduzcaLosDatos.setText("Ya has valorado esta pelicula");
										}										
									}
									else {
										txtpnIntroduzcaLosDatos.setText("Por favor introduzca la id de la pelicula correctamente");
									}
								}
								else {
									txtpnIntroduzcaLosDatos.setText("Este usuario no existe");
								}
							}
							else {
								txtpnIntroduzcaLosDatos.setText("Este usuario no existe");
							}
						}
						else {
							txtpnIntroduzcaLosDatos.setText("Introduzca todos los campos");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("VOLVER");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						InterfazUsuario iu = new InterfazUsuario();
						iu.setVisible(true);
						dispose();
					}
				});
				cancelButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void getBox() {

			Vector<ListItems> items = new Vector<ListItems>();
			//ListaPeliculas.getListaPeliculas().leerFichero("/src/main/resources/movie-titles.csv");
			for (Map.Entry<Integer,String> entry: Intermediario.getIntermediario().peliculasEntrySet()) {
				items.add(new ListItems(entry.getKey(), entry.getValue()));
			}
			for (int i=0; i<items.size(); i++) {
				jcb.addItem(items.get(i).toString());
				System.out.println(items.get(i).toString());
		}
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}

class ListItems{
	private int Id;
	private String nombrepeli;
	
	ListItems (int pid,String pNombrepeli){
		this.Id=pid;
		this.nombrepeli=pNombrepeli;
	}
	
	@Override
	public String toString() {
		return nombrepeli + "    " + Id;
	}
}

