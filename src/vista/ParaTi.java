package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import clases.Publicacion;
import modelo.DAO;
import modelo.DAOImplementacionBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

public class ParaTi extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;
	private JLabel lblNewLabel;
	private JLabel imagen;
	private JButton btnNext;

	public static void main(String[] args) {
		try {
			ParaTi dialog = new ParaTi(null, true, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param iniciarSesion
	 **/

	public ParaTi(IniciarSesion iniciarSesion, boolean b, DAO dao) {
		super(iniciarSesion);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParaTi.class.getResource("/utilidades/logo.png")));
		setTitle("Para Ti");
		setResizable(false);
		this.setModal(b);
		// this.dao = dao;
		this.dao = new DAOImplementacionBD();
		
		int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
		int ancho = (alto / 4) * 3;

		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 54));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/cuenta.png")));
		btnCuenta.setBounds(645, 896, 100, 100);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/tienda.png")));
		btnTienda.setBounds(496, 896, 100, 100);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/subir.png")));
		btnSubir.setBounds(347, 896, 100, 100);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/buscar.png")));
		btnBuscar.setBounds(198, 896, 100, 100);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/para ti.png")));
		btnParaTi.setBounds(49, 896, 100, 100);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);

		btnNext = new JButton("Siguiente");
		btnNext.setBackground(SystemColor.controlHighlight);
		btnNext.setBounds(656, 424, 89, 23);
		contentPanel.add(btnNext);
		setLocationRelativeTo(null);
		btnNext.addActionListener(this);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 794, 112);
		contentPanel.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(new Color(43, 45, 47));
		textPane_1.setBounds(0, 839, 794, 202);
		contentPanel.add(textPane_1);

		JLabel perfil = new JLabel("");
		perfil.setBounds(177, 140, 40, 40);
		contentPanel.add(perfil);

		JLabel nombreUsuario = new JLabel("Usuario");
		nombreUsuario.setFont(new Font("Serif", Font.PLAIN, 20));
		nombreUsuario.setForeground(Color.WHITE);
		nombreUsuario.setBounds(247, 147, 370, 27);
		contentPanel.add(nombreUsuario);

		lblNewLabel = new JLabel("Me gustas");
		lblNewLabel.setBounds(195, 711, 78, 14);
		contentPanel.add(lblNewLabel);

		imagen = new JLabel("");
		imagen.setBounds(177, 191, 450, 500);
		contentPanel.add(imagen);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnParaTi)) {
			abrirParaTi();
		} else if (e.getSource().equals(btnBuscar)) {
			abrirBuscar();
		} else if (e.getSource().equals(btnSubir)) {
			abrirSubir();
		} else if (e.getSource().equals(btnTienda)) {
			abrirTienda();
		} else if (e.getSource().equals(btnCuenta)) {
			abrirCuenta();
		} else if (e.getSource().equals(btnNext)) {
			next();
		}

	}

	private void next() {
		//Generar el id de una publicacion aleatoria
		
		//Pasarle el id
		Publicacion publi = dao.buscarPublicacionXId("id");

		System.out.print(publi.toString());

		//Vincular los datos de la pagina a la de la publicacion
	}

	private void abrirCuenta() {
		// TODO Auto-generated method stub

	}

	private void abrirTienda() {
		// TODO Auto-generated method stub

	}

	private void abrirSubir() {
		// TODO Auto-generated method stub

	}

	private void abrirBuscar() {
		// TODO Auto-generated method stub

	}

	private void abrirParaTi() {
		
	}
}
