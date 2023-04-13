package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Publicacion;
import clases.Usuario;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import utilidades.Utilidades;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextField;

public class ParaTi extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private List<String> hanSalido = new ArrayList<>();
	private Usuario usu;

	private Usuario usuPubli;
	private Publicacion publi;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;
	private JLabel lblNewLabel_1;
	private JButton btnDm;
	private JLabel lblIcono;
	private JToggleButton btnLike;
	private JLabel lblDescripcion;
	private JLabel lblUsuario;
	private JLabel lblMegusta;
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
		// this.dao = dao;
		this.dao = new DAOImplementacionBD();
		this.usu = usu;
		
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
		btnDm.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/conversaciones.png")));
		btnDm.setBounds(550, 12, 50, 50);
		btnDm.setBorder(null);
		contentPanel.add(btnDm);
		btnDm.addActionListener(this);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/6666-removebg-preview.png")));
		lblNewLabel_1.setBounds(98, 12, 282, 70);
		contentPanel.add(lblNewLabel_1);

		JLabel icono = new JLabel("New label");
		icono.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logo2.png")));
		icono.setBounds(27, 21, 50, 50);
		contentPanel.add(icono);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/cuenta.png")));
		btnCuenta.setBounds(500, 750, 70, 50);
		btnCuenta.setBorder(null);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/tienda.png")));
		btnTienda.setBounds(390, 750, 50, 50);
		btnTienda.setBorder(null);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/subir.png")));
		btnSubir.setBounds(280, 750, 50, 50);
		btnSubir.setBorder(null);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/buscar.png")));
		btnBuscar.setBounds(170, 750, 50, 50);
		btnBuscar.setBorder(null);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/para ti.png")));
		btnParaTi.setBounds(60, 750, 50, 50);
		btnParaTi.setBorder(null);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);
		setLocationRelativeTo(null);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 632, 83);
		contentPanel.add(textPane);

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBackground(new Color(43, 45, 47));
		textPane_1.setBounds(0, 725, 632, 100);
		contentPanel.add(textPane_1);

		lblIcono = new JLabel("");
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logopersona3.png")));
		lblIcono.setBounds(119, 93, 64, 64);
		contentPanel.add(lblIcono);

		btnLike = new JToggleButton("");
		btnLike.setSelectedIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/btnLike(True).png")));
		btnLike.setBackground(new Color(49, 51, 53));
		btnLike.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/btnLike(false).png")));
		btnLike.setBounds(137, 640, 46, 40);
		contentPanel.add(btnLike);
		btnLike.setBorder(null);
		btnLike.setContentAreaFilled(false);
		btnLike.addActionListener(this);

		lblDescripcion = new JLabel("Aqu\u00ED va la descripci\u00F3n...");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(137, 687, 332, 27);
		contentPanel.add(lblDescripcion);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(213, 112, 332, 27);
		contentPanel.add(lblUsuario);

		lblMegusta = new JLabel("");
		lblMegusta.setForeground(new Color(255, 255, 255));
		lblMegusta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMegusta.setBounds(199, 640, 209, 40);
		contentPanel.add(lblMegusta);

		imagen = new JLabel("");
		imagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next();
			}
		});
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setBounds(70, 161, 475, 475);
		contentPanel.add(imagen);

		next();
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
		} else if (e.getSource().equals(btnLike)) {
			darLike();
		} else if(e.getSource().equals(btnDm)){
			abrirConversacion();
		}

	}

	private void abrirConversacion() {
		Conversacion conver  = new Conversacion(this, true, dao, usu);
		this.setVisible(false);
		conver.setVisible(true);
		
	}

	private void darLike() {
		if (btnLike.isSelected()) {
			lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) + 1 + "");
			dao.insertarLike(usu.getUsuario(), publi.getId_publicacion());

		} else {
			lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) - 1 + "");
			dao.quirarLike(usu.getUsuario(), publi.getId_publicacion());
		}

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
		Buscar buscar = new Buscar(this, true, dao, usu, false);
		this.setVisible(false);
		buscar.setVisible(true);

	}

	private void abrirParaTi() {

	}

	private void abrirCuenta() {

	}

	private void next() {
		// Buscamos una publicacion con una id aleatoria
		publi = dao.buscarPublicacionXId(generarPublicacionAleatoria());
		usuPubli = dao.buscarUsuario(publi.getUsuario());

		if (dao.comprobarLike(usu.getUsuario(), publi.getId_publicacion())) {
			btnLike.setSelected(true);
		} else {
			btnLike.setSelected(false);
		}

		System.out.println(publi.toString());
		imagen.setIcon(new ImageIcon(ParaTi.class.getResource("/img/" + publi.getImagen())));
		lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/" + usuPubli.getIcono())));
		lblUsuario.setText(publi.getUsuario());
		lblMegusta.setText(publi.getNumLikes() + "");

	}

	private String generarPublicacionAleatoria() {
		String publiActual = "";
		int tipoPubli;
		int numPubli;
		boolean salir;

		do {
			salir = true;
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

			// Si el ya han salido todas las fotos borrar el arrayList
			if (hanSalido.size() == dao.numPublicaciones()) {
				hanSalido.clear();
			}

			// Mirar si la foto ya a salido
			for (String i : hanSalido) {
				if (i.equalsIgnoreCase(publiActual)) {
					salir = false;
				}
			}

			// Comprobar que el codigo no esta vacio
			if (publiActual.substring(2, 5).equalsIgnoreCase("000")) {
				salir = false;
			}

		} while (!salir);

		System.out.println(publiActual);

		// Añadimos el codigo a la lista de los que han salido
		hanSalido.add(publiActual);
		return publiActual;
	}
}