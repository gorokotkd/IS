package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class InterfazRegistro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazRegistro dialog = new InterfazRegistro();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazRegistro() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Unispace", Font.PLAIN, 11));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Unispace", Font.PLAIN, 11));
		
		JLabel lblRepetirPass = new JLabel("Repetir pass");
		lblRepetirPass.setFont(new Font("Unispace", Font.PLAIN, 11));
		
		JLabel lblNumInvitacion = new JLabel("Num Invitacion");
		lblNumInvitacion.setFont(new Font("Unispace", Font.PLAIN, 11));
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		final JLabel lblAreaDeRegistro = new JLabel("AREA DE REGISTRO");
		lblAreaDeRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreaDeRegistro.setFont(new Font("Unispace", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblUsuario)
								.addGap(26))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblPassword)
								.addGap(18)))
						.addComponent(lblRepetirPass)
						.addComponent(lblNumInvitacion))
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(235, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(32, Short.MAX_VALUE)
					.addComponent(lblAreaDeRegistro, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAreaDeRegistro, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsuario)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRepetirPass)
								.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNumInvitacion)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPassword)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
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
					public void actionPerformed(ActionEvent e) {
						String usuario = textField.getText();
						String pass = passwordField.getText();
						String pass2 = passwordField_1.getText();
						String inv = textField_3.getText();
						if (!usuario.equals("") && !pass.equals("") && !ListaContraseas.getListaContraseas().contains(usuario)) {
							if (pass.equals(pass2)) {
								if (inv.equals("777")) {
									lblAreaDeRegistro.setText("REGITRADO CORRECTAMENTE");
									ListaContraseas.getListaContraseas().insertarNuevo(usuario, pass);
								}
								else {
									lblAreaDeRegistro.setText("Invitacion Incorrecta");
								}
							}							
							else {
									lblAreaDeRegistro.setText("Repita La Contraseña Bien");
							}
						}
						
						else if (ListaContraseas.getListaContraseas().contains(usuario)) {
							lblAreaDeRegistro.setText("Este Usuario Ya Esta En Uso");
						}
						else {
							lblAreaDeRegistro.setText("Aprenda A Escribir");
						}
					}
				});
				
				JButton btnNewButton = new JButton("AREA DE INICIO DE SESION");
				btnNewButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InterfazEntrada ae = new InterfazEntrada();
						ae.setVisible(true);
						dispose();
					}
				});
				buttonPane.add(btnNewButton);
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
