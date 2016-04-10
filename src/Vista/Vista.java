package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Logica.ArbolAVL;

public class Vista extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ArbolAVL arbol;

	private JPanel contentPane;
	private JTextField txtLlave;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnPintar;
	private CanvasArbol canvas;
	private JTextField txtNombre;
	private JTextField txtNotauno;
	private JTextField txtNotados;
	private JTextField txtNotatres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Vista();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1343, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLlave = new JLabel("Llave");
		lblLlave.setBounds(12, 12, 70, 15);
		contentPane.add(lblLlave);

		txtLlave = new JTextField();
		txtLlave.setBounds(76, 12, 114, 19);
		contentPane.add(txtLlave);
		txtLlave.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(63, 167, 117, 25);
		btnAgregar.setActionCommand("ag");
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(63, 204, 117, 25);
		btnEliminar.setActionCommand("el");
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);
		
		btnPintar = new JButton("Pintar");
		btnPintar.setBounds(63,250,117,25);
		btnPintar.setActionCommand("pt");
		btnPintar.addActionListener(this);
		contentPane.add(btnPintar);

		canvas = new CanvasArbol();
		canvas.setBounds(227, 10, 1106, 620);
		contentPane.add(canvas);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 59, 70, 15);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(91, 57, 114, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNotauno = new JLabel("NotaUno");
		lblNotauno.setBounds(12, 86, 70, 15);
		contentPane.add(lblNotauno);

		txtNotauno = new JTextField();
		txtNotauno.setBounds(91, 84, 114, 19);
		contentPane.add(txtNotauno);
		txtNotauno.setColumns(10);

		JLabel lblNotatres = new JLabel("NotaTres");
		lblNotatres.setBounds(12, 140, 70, 15);
		contentPane.add(lblNotatres);

		JLabel lblNotados = new JLabel("NotaDos");
		lblNotados.setBounds(12, 113, 70, 15);
		contentPane.add(lblNotados);

		txtNotados = new JTextField();
		txtNotados.setBounds(91, 115, 114, 19);
		contentPane.add(txtNotados);
		txtNotados.setColumns(10);

		txtNotatres = new JTextField();
		txtNotatres.setBounds(91, 140, 114, 19);
		contentPane.add(txtNotatres);
		txtNotatres.setColumns(10);

		arbol = new ArbolAVL();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ag")) {
			arbol.agregarEstudiante(Integer.parseInt(txtLlave.getText()),
					txtNombre.getText(),
					Integer.parseInt(txtNotauno.getText()),
					Integer.parseInt(txtNotados.getText()),
					Integer.parseInt(txtNotatres.getText()));
			canvas.repaint();
		}
		if(e.getActionCommand().equals("pt")){
			canvas.repaint();
		}
		if (e.getActionCommand().equals("el")) {
			arbol.retirarLlave(Integer.parseInt(txtLlave.getText()));
			canvas.repaint();
		}
	}
}
