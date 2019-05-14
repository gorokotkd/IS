package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packProyecto.Cos;
import packProyecto.FiltradoContenido;
import packProyecto.Gestor;
import packProyecto.ListaPeliculas;
import packProyecto.ListaRatings;
import packProyecto.ListaTags;
import packProyecto.ListaUsuarios;
import packProyecto.TagsPorPeli;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class InterfazEntrada extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdContrasea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazEntrada dialog = new InterfazEntrada();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			ListaContraseas.getListaContraseas();
			Intermediario.getIntermediario().clear();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazEntrada() {
		setBounds(500, 500, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtUsuario.setText("Usuario");
		txtUsuario.setColumns(10);
		
		pwdContrasea = new JPasswordField();
		pwdContrasea.setText("Contraseña");
		
		final JLabel lblBienvenidoAFocaflix = new JLabel("FOCAFLIX ");
		lblBienvenidoAFocaflix.setFont(new Font("Unispace", Font.PLAIN, 16));
		lblBienvenidoAFocaflix.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(lblBienvenidoAFocaflix, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addGap(45))
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(66)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(87)
					.addComponent(pwdContrasea, 116, 116, 116)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblBienvenidoAFocaflix, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(pwdContrasea, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(42))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("SIGN IN");
				okButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String idUsuario = txtUsuario.getText();
						 String passUsuario = pwdContrasea.getText();
						 
						 if (idUsuario.equals("Admin")) {
							 if (ListaContraseas.getListaContraseas().getValue(idUsuario).equals(passUsuario)) {
								 InterfazEleccionFicheros interfazFicheros = new InterfazEleccionFicheros();
								 interfazFicheros.setVisible(true);
								 dispose();
							}
							 else {
								 lblBienvenidoAFocaflix.setText("Contraseña Incorrecta");
							 }
						 }
						 
						 else if(ListaContraseas.getListaContraseas().contains(idUsuario)) {
							 if (ListaContraseas.getListaContraseas().getValue(idUsuario).equals(passUsuario)) {
								 //abrir interfaz principal							 
								 InterfazUsuario iu = new InterfazUsuario();
								 /**
								  * HACEMOS QUE POR DEFECTO HAGA UN UNICO FILTRADO UTILIZANDO EL COSENO PARA COMPARAR
								  */
								 
								
								 Intermediario.getIntermediario().leerFicheroPeliculas();
								 Intermediario.getIntermediario().leerFicheroRatings();
								 Intermediario.getIntermediario().leerFicheroTags();
								 Intermediario.getIntermediario().crearFiltradoContenido();
								 iu.setVisible(true);
								 dispose();
								 
							 }
							 else {
								 lblBienvenidoAFocaflix.setText("Contraseña Incorrecta");
							 }
						 }
					}
				});
				
				JButton btnAreaDeRegistros = new JButton("AREA DE REGISTROS");
				btnAreaDeRegistros.setFont(new Font("Unispace", Font.PLAIN, 11));
				btnAreaDeRegistros.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InterfazRegistro ae = new InterfazRegistro();
						ae.setVisible(true);
						dispose();
					}
				});
				buttonPane.add(btnAreaDeRegistros);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("LOG OUT");
				cancelButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
