package packProyectoInterfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InterfazUsuario dialog = new InterfazUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InterfazUsuario() {
		setBounds(500, 500, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("FOCAFLIX");
		lblNewLabel.setFont(new Font("Unispace", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane txtpnCliqueEnPeliculas = new JTextPane();
		txtpnCliqueEnPeliculas.setFont(new Font("Unispace", Font.PLAIN, 11));
		txtpnCliqueEnPeliculas.setEditable(false);
		txtpnCliqueEnPeliculas.setText("Clique en Peliculas para ver todas las peliculas, clique en Nota para añadir una nota a una de las peliculas que haya visto o clique en Recomendar para que le recomendemos una.");
		
		JButton btnNewButton = new JButton("PELICULAS");
		btnNewButton.setFont(new Font("Unispace", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazPeliculas ip = new InterfazPeliculas();
				ip.setVisible(true);
			}
		});
		
		JButton btnNewButton_1 = new JButton("NOTA");
		btnNewButton_1.setFont(new Font("Unispace", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazNota in = new InterfazNota();
				in.setVisible(true);
				dispose();
			}
		});
		
		JButton btnNewButton_2 = new JButton("RECOMENDAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazRecomendacion ir = new InterfazRecomendacion();
				ir.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Unispace", Font.PLAIN, 11));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
							.addGap(22))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton_1)
									.addGap(57)
									.addComponent(btnNewButton_2))
								.addComponent(txtpnCliqueEnPeliculas, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnCliqueEnPeliculas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_1))
					.addGap(35))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnAreaDeOpciones = new JButton("AREA DE OPCIONES");
			btnAreaDeOpciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					InterfazConfig ic = new InterfazConfig();
					ic.setVisible(true);
					dispose();
				}
			});
			btnAreaDeOpciones.setFont(new Font("Unispace", Font.PLAIN, 11));
			buttonPane.add(btnAreaDeOpciones);
			{
				JButton cancelButton = new JButton("LOG OUT");
				cancelButton.setFont(new Font("Unispace", Font.PLAIN, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						InterfazEntrada ie = new InterfazEntrada();
						ie.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
