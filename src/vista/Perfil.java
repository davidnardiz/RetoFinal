package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import clases.Publicacion;
import clases.Usuario;
import modelo.DAO;

public class Perfil extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private ParaTi paraTi;
	private DAO dao;
	private Usuario usu;
	private boolean nosotros;
	private List<Publicacion> publicaciones;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;

	private JLabel lblUsuario;
	private JLabel lblIcono;
	private JLabel lblNumPubli;
	private JLabel lblSeguidores;
	private JLabel lblSiguiendo;

	private JButton btnEditarPerfil;
	private JButton btn;
	private JButton btnSeguir;
	private JButton btnEnviarMensaje;
	private JTable tablaPublicaciones;

	private JRadioButton rdbtnFoto;
	private JRadioButton rdbtnReels;
	private JRadioButton rdbtnHistorias;

	private ButtonGroup tipo = new ButtonGroup();

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param buscar
	 * @param usuario
	 * @param nosotros
	 */
	public Perfil(ParaTi paraTi, boolean b, DAO dao, Usuario usuario, boolean nosotros) {
		super(paraTi);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/pantalla/logo.png")));
		setTitle("Buscar");
		setResizable(false);
		this.setModal(b);
		this.paraTi = paraTi;
		this.dao = dao;
		this.usu = usuario;
		this.nosotros = nosotros;

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

		lblIcono = new JLabel();
		lblIcono.setIcon(new ImageIcon(Perfil.class.getResource("/imagenes/iconos/" + usu.getIcono())));
		lblIcono.setBounds(78, 120, 64, 64);
		contentPanel.add(lblIcono);

		lblUsuario = new JLabel(usu.getUsuario());
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(78, 196, 120, 20);
		contentPanel.add(lblUsuario);

		lblNumPubli = new JLabel();
		lblNumPubli.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumPubli.setForeground(new Color(255, 255, 255));
		lblNumPubli.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNumPubli.setBounds(212, 120, 94, 28);
		contentPanel.add(lblNumPubli);

		lblSeguidores = new JLabel(usu.getNumSeguidores() + "");
		lblSeguidores.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeguidores.setForeground(new Color(255, 255, 255));
		lblSeguidores.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSeguidores.setBounds(352, 120, 78, 28);
		contentPanel.add(lblSeguidores);

		lblSiguiendo = new JLabel(usu.getNumSeguidos() + "");
		lblSiguiendo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiendo.setForeground(new Color(255, 255, 255));
		lblSiguiendo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSiguiendo.setBounds(484, 120, 78, 28);
		contentPanel.add(lblSiguiendo);

		JLabel lblSeguidos = new JLabel("Siguiendo");
		lblSeguidos.setForeground(new Color(255, 255, 255));
		lblSeguidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeguidos.setBounds(488, 156, 70, 20);
		contentPanel.add(lblSeguidos);

		JLabel lblPublicaciones = new JLabel("Publicaciones");
		lblPublicaciones.setForeground(new Color(255, 255, 255));
		lblPublicaciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublicaciones.setBounds(212, 156, 94, 20);
		contentPanel.add(lblPublicaciones);

		JLabel lblSeguidores_2 = new JLabel("Seguidores");
		lblSeguidores_2.setForeground(new Color(255, 255, 255));
		lblSeguidores_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeguidores_2.setBounds(352, 156, 78, 20);
		contentPanel.add(lblSeguidores_2);

		btnEditarPerfil = new JButton("Editar Perfil");
		btnEditarPerfil.setBackground(SystemColor.controlHighlight);
		btnEditarPerfil.setBounds(130, 242, 120, 42);
		contentPanel.add(btnEditarPerfil);
		btnEditarPerfil.setBorder(null);

		btn = new JButton("???");
		btn.setBackground(SystemColor.controlHighlight);
		btn.setBounds(380, 242, 120, 42);
		contentPanel.add(btn);
		btn.setBorder(null);

		btnEnviarMensaje = new JButton("Enviar Mensaje");
		btnEnviarMensaje.setBackground(SystemColor.controlHighlight);
		btnEnviarMensaje.setBounds(380, 242, 120, 42);
		contentPanel.add(btnEnviarMensaje);
		btnEnviarMensaje.setBorder(null);

		btnSeguir = new JButton("Seguir");
		btnSeguir.setBackground(SystemColor.controlHighlight);
		btnSeguir.setBounds(130, 242, 120, 42);
		contentPanel.add(btnSeguir);
		btnSeguir.setBorder(null);

		rdbtnFoto = new JRadioButton("Fotos");
		rdbtnFoto.setForeground(new Color(255, 255, 255));
		rdbtnFoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnFoto.setBackground(new Color(49, 51, 53));
		rdbtnFoto.setBounds(63, 314, 109, 23);
		contentPanel.add(rdbtnFoto);
		rdbtnFoto.addActionListener(this);
		rdbtnFoto.setSelected(true);

		rdbtnReels = new JRadioButton("Reels");
		rdbtnReels.setForeground(new Color(255, 255, 255));
		rdbtnReels.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnReels.setBackground(new Color(49, 51, 53));
		rdbtnReels.setBounds(248, 314, 109, 23);
		contentPanel.add(rdbtnReels);
		rdbtnReels.addActionListener(this);

		rdbtnHistorias = new JRadioButton("Historias");
		rdbtnHistorias.setForeground(new Color(255, 255, 255));
		rdbtnHistorias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHistorias.setBackground(new Color(49, 51, 53));
		rdbtnHistorias.setBounds(433, 314, 109, 23);
		contentPanel.add(rdbtnHistorias);
		rdbtnHistorias.addActionListener(this);

		tipo.add(rdbtnFoto);
		tipo.add(rdbtnReels);
		tipo.add(rdbtnHistorias);

		tablaPublicaciones = new JTable();

		if (!nosotros) {
			btn.setVisible(false);
			btnEditarPerfil.setVisible(false);
		}

		publicaciones = dao.listarPublicacionesUsuario(usu.getUsuario(), "Foto");
		presentarTabla(publicaciones);

	}

	public void presentarTabla(List<Publicacion> publicacionesList) {
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablaPublicaciones = this.cargarTabla(publicacionesList);
		tablaPublicaciones.setShowVerticalLines(false);
		tablaPublicaciones.setShowHorizontalLines(false);
		tablaPublicaciones.setShowGrid(false);
		tablaPublicaciones.setRowSelectionAllowed(false);
		tablaPublicaciones.setFillsViewportHeight(true);
		tablaPublicaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tablaPublicaciones.setForeground(new Color(255, 255, 255));
		tablaPublicaciones.setBackground(new Color(49, 51, 53));
		tablaPublicaciones.setRowHeight(345);
		tablaPublicaciones.setTableHeader(null);

		TableColumnModel columnModel = tablaPublicaciones.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
		columnModel.getColumn(1).setCellRenderer(new ImageRenderer());
		columnModel.getColumn(2).setCellRenderer(new ImageRenderer());

		scroll.setViewportView(tablaPublicaciones);
		contentPanel.add(scroll);
		scroll.setBackground(new Color(49, 51, 53));
		scroll.setBounds(19, 355, 594, 351);

		tablaPublicaciones.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = tablaPublicaciones.rowAtPoint(e.getPoint());
				int columna = tablaPublicaciones.columnAtPoint(e.getPoint());

				abrirFoto(tablaPublicaciones.getValueAt(fila, columna).toString());

			}
		});
	}

	public JTable cargarTabla(List<Publicacion> publicacionesList) {
		String[] cabezeras = { "", "", "" };
		Object[] fila = new Object[3];

		DefaultTableModel model = new DefaultTableModel(null, cabezeras);
		String rutaProyecto = System.getProperty("user.dir");

		for (int i = 0; i < publicacionesList.size(); i = i + 3) {

			fila[0] = new ImageIcon(
					rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i).getImagen());

			if (publicacionesList.size() > i + 1) {
				fila[1] = new ImageIcon(
						rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 1).getImagen());
			} else {
				fila[1] = null;
			}

			if (publicacionesList.size() > i + 2) {
				fila[2] = new ImageIcon(
						rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 2).getImagen());
				System.out.println(i + 2);
			} else {
				fila[2] = null;
			}

			model.addRow(fila);
		}

		return new JTable(model);
	}

	class ImageRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			JLabel label = new JLabel();
			if (value != null) {
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setIcon((ImageIcon) value);
			}
			return label;
		}
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

		if (e.getSource().equals(rdbtnFoto)) {
			presentarTabla(dao.listarPublicacionesUsuario(usu.getUsuario(), "Foto"));
		} else if (e.getSource().equals(rdbtnReels)) {
			presentarTabla(dao.listarPublicacionesUsuario(usu.getUsuario(), "Reel"));
		} else if (e.getSource().equals(rdbtnHistorias)) {
			presentarTabla(dao.listarPublicacionesUsuario(usu.getUsuario(), "Historia"));
		}

	}

	private void abrirFoto(String foto) {
		String rutaProyecto = System.getProperty("user.dir");
		Publicacion publi = null;

		for (Publicacion i : publicaciones) {
			if (foto.equalsIgnoreCase(rutaProyecto + "\\src\\imagenes\\publicaciones\\" + i.getImagen())) {

				publi = i;
				break;
			}
		}
		System.out.println(publi.toString());
		PublicacionPopUp publiPop = new PublicacionPopUp(paraTi, true, publi, usu);
		publiPop.setVisible(true);

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
