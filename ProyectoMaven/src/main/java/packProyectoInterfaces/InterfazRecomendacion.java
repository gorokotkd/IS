package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import packProyecto.*;
import packProyecto.FiltradoProductos;
import packProyecto.Gestor;
import packProyectoInterfaces.ListaContraseas;
import packProyecto.ListaPeliculas;
import packProyecto.ListaUsuarios;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazRecomendacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInsertaTuId;
	private JScrollPane scrollPane;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazRecomendacion dialog = new InterfazRecomendacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazRecomendacion() {
		setBounds(400, 400, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAreaDeRecomendacion = new JLabel("AREA DE RECOMENDACION");
		lblAreaDeRecomendacion.setFont(new Font("Unispace", Font.PLAIN, 16));
		lblAreaDeRecomendacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane txtpnEstasSonLas = new JTextPane();
		txtpnEstasSonLas.setEditable(false);
		txtpnEstasSonLas.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtpnEstasSonLas.setText("Inserta tu id para que te podamos recomendar peliculas");
		
		txtInsertaTuId = new JTextField();
		txtInsertaTuId.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtInsertaTuId.setText("INSERTA TU ID");
		txtInsertaTuId.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		scrollPane_1.setViewportView(getList());
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtInsertaTuId, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(31))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtpnEstasSonLas, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(113)
					.addComponent(lblAreaDeRecomendacion, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(224, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblAreaDeRecomendacion, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(txtInsertaTuId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(txtpnEstasSonLas, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(30))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
							.addContainerGap())))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("RECOMENDAR");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (ListaContraseas.getListaContraseas().contains(txtInsertaTuId.getText())){
							txtInsertaTuId.setText("Escribe tu id, la encontraras en el apartado de configuracion");
						}
						else if (isNumeric(txtInsertaTuId.getText())) {
							Integer id = Integer.parseInt(txtInsertaTuId.getText());
							if (Intermediario.getIntermediario().existeUsu(id)) {						
								
								HashMap<String,Double> aux =  Intermediario.getIntermediario().recomendarPeliculasAlUsu(id);
								Vector<String> lista = new Vector<String>(aux.keySet());								
								list.setListData(lista);
							}
							else {
								txtInsertaTuId.setText("No hemos hallado este usuario");
							}
						}
						else {
							txtInsertaTuId.setText("Por favor, escriba correctamente su id");
						}
					}
				});
				okButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("VOLVER");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
		//	scrollPane.setRowHeaderView(getScrollBar());
		}
		return scrollPane;
	}
	private JList getList() {
		if (list == null) {
			Vector<ListItem> items = new Vector<ListItem>();
			//ListaPeliculas.getListaPeliculas().leerFichero("/src/main/resources/movie-titles.csv");
			for (Map.Entry<Integer,String> entry: Intermediario.getIntermediario().peliculasEntrySet()) {
				items.add(new ListItem(entry.getKey(), entry.getValue()));
			}
			list = new JList(items);
			list.setVisibleRowCount(5);
			list.setListData(items);
		}
		return list;
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
