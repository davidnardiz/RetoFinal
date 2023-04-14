package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import clases.Usuario;
import modelo.DAO;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Toolkit;

public class Buscar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private Usuario etiquetado;
	private DAO dao;
	private Usuario usu;
	private boolean conver;
	private ParaTi paraTi;

	private JTextField buscador;
	private JButton btnVolver;
	private JTable tablaUsuarios;

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;

	private List<Usuario> usuariosList;

	public Buscar(ParaTi paraTi, boolean b, DAO dao, Usuario usu, boolean conver, Usuario etiquetado) {
		super(paraTi);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/pantalla/logo.png")));
		setTitle("Buscar");
		setResizable(false);
		this.setModal(b);
		this.paraTi = paraTi;
		this.dao = dao;
		this.usu = usu;
		this.conver = conver;
		this.etiquetado = etiquetado;

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
		btnParaTi.setBounds(63, 750, 50, 50);
		btnParaTi.setBorder(null);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);

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

		buscador = new JTextField();
		buscador.setBounds(220, 127, 338, 35);
		contentPanel.add(buscador);
		buscador.setColumns(10);

		buscador.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				buscar();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				buscar();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				buscar();

			}
		});

		if (etiquetado == null) {
			usuariosList = dao.listarUsuario();

		} else {
			usuariosList = new ArrayList<>();
			usuariosList.add(etiquetado);
			buscador.setText(etiquetado.getUsuario());
		}

		presentarTabla(usuariosList);

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 186, 595, 17);
		contentPanel.add(separator);

		btnVolver = new JButton("Vovler");
		btnVolver.setBackground(SystemColor.controlHighlight);
		btnVolver.setForeground(new Color(0, 0, 0));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(255, 771, 121, 29);
		contentPanel.add(btnVolver);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(72, 133, 76, 22);
		contentPanel.add(lblNewLabel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnParaTi)) {
			abrirParaTi();
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
		Subir subir = new Subir(paraTi, true, dao, usu);
		this.dispose();
		subir.setVisible(true);
	}

	private void abrirParaTi() {
		this.dispose();
		paraTi.setVisible(true);
	}

	private void abrirPerfil(String usuario) {
		Perfil perfil = new Perfil(paraTi, true, dao, usuario);
		this.setVisible(false);
		perfil.setVisible(true);
	}

	private void buscar() {
		usuariosList = dao.listarUsuarioXUsuario(buscador.getText());
		presentarTabla(usuariosList);
	}

	public void presentarTabla(List<Usuario> usuariosList) {
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tablaUsuarios = this.cargarTabla(usuariosList);
		tablaUsuarios.setShowVerticalLines(false);
		tablaUsuarios.setShowHorizontalLines(false);
		tablaUsuarios.setShowGrid(false);
		tablaUsuarios.setRowSelectionAllowed(false);
		tablaUsuarios.setFillsViewportHeight(true);
		tablaUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tablaUsuarios.setForeground(new Color(255, 255, 255));
		tablaUsuarios.setBackground(new Color(49, 51, 53));
		tablaUsuarios.setRowHeight(85);
		tablaUsuarios.setTableHeader(null);

		TableColumnModel columnModel = tablaUsuarios.getColumnModel();
		columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
		columnModel.getColumn(1).setCellRenderer(new ImageRenderer());
		columnModel.getColumn(1).setResizable(true);
		columnModel.getColumn(1).setPreferredWidth(-1);

		scroll.setViewportView(tablaUsuarios);
		contentPanel.add(scroll);
		scroll.setBackground(new Color(49, 51, 53));
		scroll.setBounds(27, 214, 581, 495);

		tablaUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int fila = tablaUsuarios.rowAtPoint(e.getPoint());

				if (conver) {

				} else {
					abrirPerfil(tablaUsuarios.getValueAt(fila, 2).toString());
				}
			}
		});
	}

	public JTable cargarTabla(List<Usuario> usuariosList) {
		String[] cabezeras = { "icono", "verificado", "usuario", "seguidores" };
		Object[] fila = new Object[4];

		DefaultTableModel model = new DefaultTableModel(null, cabezeras);
		String rutaProyecto = System.getProperty("user.dir");

		for (Usuario j : usuariosList) {
			fila[0] = new ImageIcon(rutaProyecto + "\\src\\imagenes\\iconos\\" + j.getIcono());

			if (j.isVerificado()) {
				System.out.println("Entre");
				fila[1] = new ImageIcon(rutaProyecto + "\\src\\imagenes\\pantalla\\verificado.png");
			}

			fila[2] = j.getUsuario();
			fila[3] = j.getNumSeguidores() + " ";

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

				if (value.toString().contains("verificado")) {
					label.setHorizontalAlignment(JLabel.RIGHT);
				} else {
					label.setHorizontalAlignment(JLabel.CENTER);
				}

				label.setIcon((ImageIcon) value);
			}
			return label;
		}
	}
}
