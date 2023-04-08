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

import clases.Publicacion;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import utilidades.Utilidades;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Font;

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
	private JLabel lblNewLabel_1;
	private JButton btnDm;
	private JLabel lblImagen;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JButton btnNext;

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

		// int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
		int alto = 864;
		int ancho = (alto / 4) * 3;

		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 54));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnDm = new JButton("");
		btnDm.setForeground(new Color(43, 45, 47));
		btnDm.setBackground(new Color(43, 45, 47));
		btnDm.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logoDm.png")));
		btnDm.setBounds(522, 21, 78, 68);
		btnDm.setBorder(null);
		contentPanel.add(btnDm);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/6666-removebg-preview.png")));
		lblNewLabel_1.setBounds(87, 30, 282, 70);
		contentPanel.add(lblNewLabel_1);

		JLabel icono = new JLabel("New label");
		icono.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logo2.png")));
		icono.setBounds(28, 26, 58, 58);
		contentPanel.add(icono);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/cuenta.png")));
		btnCuenta.setBounds(515, 711, 70, 70);
		btnCuenta.setBorder(null);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/tienda.png")));
		btnTienda.setBounds(398, 711, 70, 70);
		btnTienda.setBorder(null);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/subir.png")));
		btnSubir.setBounds(281, 711, 70, 70);
		btnSubir.setBorder(null);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/buscar.png")));
		btnBuscar.setBounds(164, 711, 70, 70);
		btnBuscar.setBorder(null);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/para ti.png")));
		btnParaTi.setBounds(47, 711, 70, 70);
		btnParaTi.setBorder(null);
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

		lblNewLabel = new JLabel("Me gustas");
		lblNewLabel.setBounds(195, 711, 78, 14);
		contentPanel.add(lblNewLabel);

		lblImagen = new JLabel("");
		lblImagen.setBackground(new Color(128, 64, 0));
		lblImagen.setBounds(107, 190, 311, 367);
		contentPanel.add(lblImagen);

		abrirCuenta();

		lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logopersona3.png")));
		lblNewLabel_3.setBounds(107, 122, 58, 58);
		contentPanel.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/corazonBlanco3.png")));
		lblNewLabel_4.setBounds(107, 563, 46, 40);
		contentPanel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Aqu\u00ED va la descripci\u00F3n...");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(107, 613, 332, 27);
		contentPanel.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Usuario");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(169, 144, 332, 27);
		contentPanel.add(lblNewLabel_6);

		lblNewLabel_7 = new JLabel("384");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(160, 567, 209, 40);
		contentPanel.add(lblNewLabel_7);

		btnNext = new JButton("Siguiente");
		btnNext.setBounds(494, 359, 89, 23);
		contentPanel.add(btnNext);
		btnNext.addActionListener(this);

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

	private void abrirTienda() {
		// TODO Auto-generated method stub

	}

	private void abrirSubir() {
		Subir subir = new Subir(this, true, dao, null);
		this.setVisible(false);
		subir.setVisible(true);

	}

	private void abrirBuscar() {
		// TODO Auto-generated method stub

	}

	private void abrirParaTi() {

	}

	private void next() {

		String publiActual = "";
		publiActual = generarPublicacionAleatoria(publiActual);

		Publicacion publi = dao.buscarPublicacionXId(publiActual); // Buscamos la publicacion con esa id

		if (publi.getImagen().contains("\\")) {
			
			lblImagen.setIcon(new ImageIcon(publi.getImagen()));

		} else {
			lblImagen.setIcon(new ImageIcon(ParaTi.class.getResource("/img/" + publi.getImagen())));
		}

	}

	private String generarPublicacionAleatoria(String publiAnterior) {
		String publiActual = "";
		int tipoPubli;
		int numPubli;

		do {
			tipoPubli = Utilidades.numeros_aleatorios(0, 2);

			switch (tipoPubli) {
			case 0:
				publiActual = "F";
				break;
			case 1:
				publiActual = "R";
				break;
			case 2:
				publiActual = "H";
				break;
			default:
				break;

			}
			// Miramos el numero de publicaciones que hay de ese tipo
			numPubli = dao.numPublicacionesHerencia(publiActual);

			// Generamos bien el codigo
			publiActual += "-" + String.format("%03d", Utilidades.numeros_aleatorios(0, numPubli));

		} while (publiActual.substring(2, 5).equalsIgnoreCase("000") && !publiActual.equalsIgnoreCase(publiAnterior));

		return publiActual;
	}

	private void abrirCuenta() {

	}
}