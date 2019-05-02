package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packProyecto.ListaContraseas;
import packProyecto.ListaUsuarios;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazEntrada() {
		setBounds(100, 100, 450, 300);
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
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(153)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(pwdContrasea)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(lblBienvenidoAFocaflix, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(lblBienvenidoAFocaflix, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pwdContrasea, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
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
							 System.out.println("Entro 1er if");
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
								 lblBienvenidoAFocaflix.setText("Estas Dentro");
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
