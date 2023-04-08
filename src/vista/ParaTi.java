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

public class ParaTi extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private Usuario usu;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;
	private JLabel lblNewLabel;
	private JLabel imagen;


	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param iniciarSesion
	 **/

	public ParaTi(IniciarSesion iniciarSesion, boolean b, DAO dao, Usuario usu) {
		super(iniciarSesion);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParaTi.class.getResource("/utilidades/logo.png")));
		setTitle("Para Ti");
		setResizable(false);
		this.setModal(b);
		this.dao = dao;
		this.usu = usu;
			
		int alto = 864;
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
		btnCuenta.setBounds(515, 711, 70, 70);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/tienda.png")));
		btnTienda.setBounds(398, 711, 70, 70);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/subir.png")));
		btnSubir.setBounds(281, 711, 70, 70);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/buscar.png")));
		btnBuscar.setBounds(164, 711, 70, 70);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/para ti.png")));
		btnParaTi.setBounds(47, 711, 70, 70);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);
		setLocationRelativeTo(null);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 632, 112);
		contentPanel.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(new Color(43, 45, 47));
		textPane_1.setBounds(-162, 665, 794, 160);
		contentPanel.add(textPane_1);

		JLabel perfil = new JLabel("");
		perfil.setBounds(177, 140, 40, 40);
		contentPanel.add(perfil);

		lblNewLabel = new JLabel("Me gustas");
		lblNewLabel.setBounds(195, 711, 78, 14);
		contentPanel.add(lblNewLabel);

		imagen = new JLabel("");
		imagen.setBounds(86, 174, 450, 500);
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
		}
	}

	private void abrirCuenta() {
		
	}

	private void abrirTienda() {
		// TODO Auto-generated method stub

	}

	private void abrirSubir() {
		Subir subir = new Subir(this, true, dao, usu);
		this.setVisible(false);
		subir.setVisible(true);

	}

	private void abrirBuscar() {
		// TODO Auto-generated method stub

	}

	private void abrirParaTi() {
		
	}
}
