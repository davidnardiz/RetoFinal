package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Perfil extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ParaTi paraTi;
	private DAO dao;
	private Usuario usu;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;
	private JLabel lblNewLabel;

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param buscar
	 * @param usuario
	 */
	public Perfil(ParaTi paraTi, boolean b, DAO dao, String usuario) {
		super(paraTi);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/pantalla/logo.png")));
		setTitle("Buscar");
		setResizable(false);
		this.setModal(b);
		this.paraTi = paraTi;
		this.dao = dao;
		this.usu = dao.buscarUsuario(usuario);

		int alto = 864;
		int ancho = (alto / 4) * 3;
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/para ti.png")));
		btnParaTi.setBounds(63, 750, 51, 51);
		btnParaTi.setBorder(null);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/subir.png")));
		btnSubir.setBounds(289, 750, 51, 51);
		btnSubir.setBorder(null);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/tienda.png")));
		btnTienda.setBounds(402, 750, 51, 51);
		btnTienda.setBorder(null);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/cuenta.png")));
		btnCuenta.setBounds(515, 750, 51, 51);
		btnCuenta.setBorder(null);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/buscar.png")));
		btnBuscar.setBounds(176, 750, 51, 51);
		btnBuscar.setBorder(null);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		JLabel lblLetrasInstagram = new JLabel("");
		lblLetrasInstagram.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/letrasInstagram.png")));
		lblLetrasInstagram.setBounds(98, 12, 282, 70);
		contentPanel.add(lblLetrasInstagram);

		JLabel lblLogoInstagram = new JLabel("");
		lblLogoInstagram.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/logoPequeño.png")));
		lblLogoInstagram.setBounds(27, 21, 50, 50);
		contentPanel.add(lblLogoInstagram);

		JTextPane franjaArriba = new JTextPane();
		franjaArriba.setEditable(false);
		franjaArriba.setBackground(new Color(43, 45, 47));
		franjaArriba.setBounds(0, 0, 632, 83);
		contentPanel.add(franjaArriba);

		JTextPane franjaAbajo = new JTextPane();
		franjaAbajo.setEditable(false);
		franjaAbajo.setBackground(new Color(43, 45, 47));
		franjaAbajo.setBounds(0, 725, 632, 100);
		contentPanel.add(franjaAbajo);

		lblNewLabel = new JLabel(usuario);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 55));
		lblNewLabel.setBounds(29, 100, 569, 598);
		contentPanel.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

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
		}

	}

	private void abrirParaTi() {
		this.dispose();
		paraTi.setVisible(true);

	}

	private void abrirTienda() {
		// TODO Auto-generated method stub

	}

	private void abrirSubir() {
		Subir subir = new Subir(paraTi, true, dao, usu);
		this.setVisible(false);
		subir.setVisible(true);

	}

	private void abrirBuscar() {
		Buscar buscar = new Buscar(paraTi, true, dao, usu, false, null);
		this.setVisible(false);
		buscar.setVisible(true);

	}

	private void abrirCuenta() {

	}
}
