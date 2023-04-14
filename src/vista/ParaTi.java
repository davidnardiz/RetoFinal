package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.Usuario;
import modelo.DAO;
import modelo.DAOImplementacionBD;
import utilidades.Utilidades;

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
	private JButton btnDm;
	private JLabel lblIcono;
	private JToggleButton btnLike;
	private JLabel lblDescripcion;
	private JLabel lblUsuario;
	private JLabel lblMegusta;
	private JLabel imagen;
	private JLabel lblVerificado;
	private JLabel lblHistoria;
	private JButton btnEtiquetado;

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param iniciarSesion
	 **/

	public ParaTi(IniciarSesion iniciarSesion, boolean b, DAO dao, Usuario usu) {
		super(iniciarSesion);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ParaTi.class.getResource("/imagenes/pantalla/logo.png")));
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

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/tienda.png")));
		btnTienda.setBounds(402, 750, 50, 50);
		btnTienda.setBorder(null);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/cuenta.png")));
		btnCuenta.setBounds(515, 750, 50, 50);
		btnCuenta.setBorder(null);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/buscar.png")));
		btnBuscar.setBounds(176, 750, 50, 50);
		btnBuscar.setBorder(null);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/subir.png")));
		btnSubir.setBounds(289, 750, 50, 50);
		btnSubir.setBorder(null);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/para ti.png")));
		btnParaTi.setBounds(63, 750, 50, 50);
		btnParaTi.setBorder(null);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);

		btnDm = new JButton("");
		btnDm.setForeground(new Color(43, 45, 47));
		btnDm.setBackground(new Color(43, 45, 47));
		btnDm.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/conversaciones.png")));
		btnDm.setBounds(550, 12, 50, 50);
		btnDm.setBorder(null);
		contentPanel.add(btnDm);

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
		setLocationRelativeTo(null);

		lblIcono = new JLabel();
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(119, 89, 64, 64);
		contentPanel.add(lblIcono);
		lblIcono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPerfil();
			}
		});

		lblHistoria = new JLabel("");
		lblHistoria.setBounds(101, 71, 100, 100);
		contentPanel.add(lblHistoria);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(239, 110, 141, 22);
		contentPanel.add(lblUsuario);
		lblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPerfil();
			}
		});

		btnLike = new JToggleButton("");
		btnLike.setSelectedIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(True).png")));
		btnLike.setBackground(new Color(49, 51, 53));
		btnLike.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(false).png")));
		btnLike.setBounds(137, 640, 46, 40);
		contentPanel.add(btnLike);
		btnLike.setBorder(null);
		btnLike.setContentAreaFilled(false);
		btnLike.addActionListener(this);

		lblDescripcion = new JLabel("Aqu\u00ED va la descripci\u00F3n...");
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(137, 687, 463, 27);
		contentPanel.add(lblDescripcion);

		lblMegusta = new JLabel("");
		lblMegusta.setForeground(new Color(255, 255, 255));
		lblMegusta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMegusta.setBounds(199, 640, 209, 40);
		contentPanel.add(lblMegusta);

		btnEtiquetado = new JButton("");
		btnEtiquetado.setBackground(new Color(49, 51, 53));
		btnEtiquetado.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/etiquedado.png")));
		btnEtiquetado.setBounds(504, 593, 30, 30);
		contentPanel.add(btnEtiquetado);
		btnEtiquetado.addActionListener(this);
		btnEtiquetado.setBorder(null);

		imagen = new JLabel();
		imagen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				next();
			}
		});
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setBounds(70, 161, 475, 475);
		contentPanel.add(imagen);

		lblVerificado = new JLabel("");
		lblVerificado.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/verificado.png")));
		lblVerificado.setBounds(199, 106, 30, 30);
		lblVerificado.setVisible(false);
		contentPanel.add(lblVerificado);

		next();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			abrirBuscar(null);
		} else if (e.getSource().equals(btnSubir)) {
			abrirSubir();
		} else if (e.getSource().equals(btnTienda)) {
			abrirTienda();
		} else if (e.getSource().equals(btnCuenta)) {
			abrirCuenta();
		} else if (e.getSource().equals(btnLike)) {
			darLike();
		} else if (e.getSource().equals(btnEtiquetado)) {
			abrirBuscar((dao.buscarUsuario(((Foto) publi).getEtiquetado())));
		}

	}

	private void abrirPerfil() {
		Perfil perfil = new Perfil(this, true, dao, dao.buscarUsuario(lblUsuario.getText()), false);
		this.setVisible(false);
		perfil.setVisible(true);

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

	private void abrirBuscar(Usuario etiquetado) {
		Buscar buscar = new Buscar(this, true, dao, usu, false, etiquetado);
		this.setVisible(false);
		buscar.setVisible(true);

	}

	private void abrirCuenta() {
		Perfil perfil = new Perfil(null, true, dao, usu, true);
		this.setVisible(false);
		perfil.setVisible(true);
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

		lblDescripcion.setVisible(true);
		lblVerificado.setVisible(false);
		lblUsuario.setLocation(213, 110);
		lblHistoria.setVisible(false);
		btnEtiquetado.setVisible(false);
		if (usuPubli.isVerificado()) {
			lblVerificado.setVisible(true);
			lblUsuario.setLocation(239, 110);

		}

		if (publi instanceof Foto) {
			lblDescripcion.setText(((Foto) publi).getDescripcion());

			if (((Foto) publi).getEtiquetado() != null) {
				btnEtiquetado.setVisible(true);

			}

		} else if (publi instanceof Reel) {
			lblDescripcion.setText(((Reel) publi).getDescripcion());

		} else if (publi instanceof Historia) {
			lblHistoria.setVisible(true);
			lblDescripcion.setVisible(false);

			if (((Historia) publi).isMejores_amigos()) {
				lblHistoria.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/esMejos.png")));

			} else {
				lblHistoria.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/esHistoria.png")));
			}
		}

		System.out.println(publi.toString());
		System.out.println(usuPubli.toString());
		System.out.println();
		
		lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuPubli.getIcono())));
		imagen.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));
		lblUsuario.setText(publi.getUsuario());
		lblMegusta.setText(publi.getNumLikes() + "");

	}

	private String generarPublicacionAleatoria() {
		List<Publicacion> id = dao.listarId();
		String publiActual = "";
		int numRandom;
		boolean salir;

		if (hanSalido.size() == id.size()) {
			hanSalido.clear();
		}

		do {
			salir = true;
			numRandom = Utilidades.numeros_aleatorios(0, id.size() - 1);

			for (String i : hanSalido) {
				if (i.equalsIgnoreCase(id.get(numRandom).getId_publicacion())) {
					salir = false;
				}
			}

		} while (!salir);

		publiActual = id.get(numRandom).getId_publicacion();
		hanSalido.add(publiActual);

		return publiActual;
	}
}