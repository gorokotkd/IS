package packProyecto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;


public class InterfazRatings extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList list;
	private JList list_1;
	private JLabel lblIdusuario;
	private JTextField txtIntroduceId;
	private JButton okButton;
	private JButton btnResetId;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			InterfazRatings dialog = new InterfazRatings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public InterfazRatings() {
		setBounds(100, 100, 450, 300);
		setSize(600, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		{
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String valor = txtIntroduceId.getText();
					if (isNumeric(valor)) {
							Integer numero = Integer.parseInt(valor);
						if (Ratings.getRatings().devolKeys().contains(numero)){
							PeliRating[] listaaux = Ratings.getRatings().getRatingsPorId(numero);
							Vector<ListPeliNota> items1 = new Vector<>();
							for (int i=0;i<listaaux.length;i++) {
								items1.add(new ListPeliNota(listaaux[i].getPeli(),listaaux[i].getNota()));
							}
							/*DefaultListModel listModel1 = new DefaultListModel();
							for(int i=0; i<listaaux.length;i++) {
								listModel1.add(i, listaaux[i]);
							}*/
							//list = new JList(items1);
							list.setListData(items1);
							//list_1 = new JList(items1);
							//list_1.setListData(items1);
							lblIdusuario.setText("IdPeli---Nota");
						}
					}
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(27)
							.addComponent(getTxtIntroduceId(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(okButton)
							.addGap(18)
							.addComponent(getBtnResetId())))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(getTxtIntroduceId(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(getBtnResetId()))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		{
			
		scrollPane.setViewportView(getList_1());
		scrollPane.setColumnHeaderView(getLblIdusuario());
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}}
	}
	private JList getList_1() {
		if (list == null) {
			DefaultListModel listModel = new DefaultListModel();
			ArrayList<Integer> lista = Ratings.getRatings().devolKeys();
			for (int i=0; i<lista.size();i++) {
				listModel.add(i, lista.get(i));
			}
			list = new JList(listModel);
			list.setModel(listModel);
		}
		return list;
	}
	private JLabel getLblIdusuario() {
		if (lblIdusuario == null) {
			lblIdusuario = new JLabel("IDUsuario");
		}
		return lblIdusuario;
	}
	private JTextField getTxtIntroduceId() {
		if (txtIntroduceId == null) {
			txtIntroduceId = new JTextField();
			txtIntroduceId.setText("Introduce Id");
			txtIntroduceId.setColumns(10);
		}
		return txtIntroduceId;
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	class ListPeliNota{
		private int Id;
		private double nota;
		
		ListPeliNota (int pid,double pnota){
			this.Id=pid;
			this.nota=pnota;
		}
		
		@Override
		public String toString() {
			return Id + ":        " + nota;
		}
	}
	private JButton getBtnResetId() {
		if (btnResetId == null) {
			btnResetId = new JButton("Reset ID");
			btnResetId.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DefaultListModel listModel = new DefaultListModel();
					ArrayList<Integer> lista = Ratings.getRatings().devolKeys();
					for (int i=0; i<lista.size();i++) {
						listModel.add(i, lista.get(i));}
					list.setModel(listModel);
					lblIdusuario.setText("IDUsuario");
					txtIntroduceId.setText("");
				}
			});
		}
		return btnResetId;
	}
}
