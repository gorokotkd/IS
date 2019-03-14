package packProyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

public class FileChooser extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileChooser frame = new FileChooser();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileChooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getFileChooser(), BorderLayout.CENTER);
		
	}
	
	public File leerFichero()
	{
		int seleccion = fileChooser.showOpenDialog(contentPane);
		if(seleccion==JFileChooser.APPROVE_OPTION)
		{
			File fichero = fileChooser.getSelectedFile();
			//ListaPeliculas.getListaPeliculas().leerFichero(fichero);
			return fichero;
		}
		else
			return null;
	}

	public JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
		}
		return fileChooser;
	}

}
