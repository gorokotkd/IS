package packProyecto;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class pruebaGrafica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList list;
	private JScrollBar scrollBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			pruebaGrafica dialog = new pruebaGrafica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public pruebaGrafica() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

			JLabel lblNewLabel = new JLabel("Etiqueta");
			GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
			gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)
					.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
			);
			gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup()
						.addComponent(lblNewLabel)
						.addGap(7)
						.addComponent(getScrollPane(), GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			contentPanel.setLayout(gl_contentPanel);
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getSource().equals(cancelButton)) {
							dispose();
						}
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getList());
			scrollPane.setRowHeaderView(getScrollBar());
		}
		return scrollPane;
	}
	private JList getList() {
		if (list == null) {
			//Map<Integer,String> hashpeliculas = new HashMap<>();
			//hashpeliculas.put(1,"Tetanic");
			//hashpeliculas.put(2,"La almeja Maya");
			//hashpeliculas.put(3,"50 tragos de leche");
			Peliculas.getPeliculas();
			Vector<ListItem> items = new Vector<>();
			for (Map.Entry<Integer,String> entry: Peliculas.getPeliculas().entrySet()) {
				items.add(new ListItem(entry.getKey(), entry.getValue()));
			}
			list = new JList(items);
			list.setListData(items);
		}
		return list;
	}
	private JScrollBar getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollBar();
		}
		return scrollBar;
	}
}

class ListItem{
	private int Id;
	private String nombrepeli;
	
	ListItem (int pid,String pNombrepeli){
		this.Id=pid;
		this.nombrepeli=pNombrepeli;
	}
	
	@Override
	public String toString() {
		return Id + "    " + nombrepeli;
	}
}
