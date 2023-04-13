package vista;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Mensaje;
import clases.Usuario;
import modelo.DAO;

import javax.swing.JTextArea;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;

public class Chat extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField introducirMensaje;
	private JTextArea chat;
	private DAO dao;
	private Usuario usu;
	private JTable tabla;
	private String usuario1;
	private String usuario2;
	private List<Mensaje> mensajes;
	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;

	public Chat(Buscar buscar, boolean b, DAO dao, Usuario usu) {
		
		this.dao = dao;
		this.usu = usu;
		int alto = 864;
		int ancho = (alto / 4) * 3;
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(49, 51, 53));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
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

		String usuario1 = usu.getUsuario();
		String usuario2 = "1alvaro";
		List<Mensaje> mensajes = dao.sacarIdMensajes(usuario1, usuario2);

		introducirMensaje = new JTextField();
		introducirMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					enviarMensaje(mensajes);
					List<Mensaje> mensaje = dao.sacarIdMensajes(usuario1, usuario2);
					presentarTabla(mensaje);
					cargarTabla(mensaje);
					ponerDerecha(tabla);
				}
			}
		});
		introducirMensaje.setBounds(27, 576, 574, 133);

		contentPanel.add(introducirMensaje);
		introducirMensaje.setColumns(10);

		presentarTabla(mensajes);
		ponerDerecha(tabla);

	}

	private void ponerDerecha(JTable tabla) {
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		tabla.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

		cellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void presentarTabla(List<Mensaje> mensajes) {
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportBorder(null);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tabla = this.cargarTabla(mensajes);
		tabla.setFillsViewportHeight(true);
		tabla.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabla.setForeground(new Color(255, 255, 255));
		tabla.setBackground(new Color(49, 51, 53));
		tabla.setRowHeight(85);
		tabla.setShowVerticalLines(false);
		tabla.setShowHorizontalLines(false);
		tabla.setShowGrid(false);
		tabla.setEnabled(false);
		tabla.setTableHeader(null);

		JScrollBar verticalBar = scroll.getVerticalScrollBar();
		AdjustmentListener downScroller = new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				Adjustable adjustable = e.getAdjustable();
				adjustable.setValue(adjustable.getMaximum());
				verticalBar.removeAdjustmentListener(this);
			}
		};
		verticalBar.addAdjustmentListener(downScroller);

		scroll.setViewportView(tabla);
		contentPanel.add(scroll);
		scroll.setBackground(new Color(49, 51, 53));
		scroll.setBounds(20, 70, 581, 495);

	}

	private JTable cargarTabla(List<Mensaje> mensajes) {
		String[] cabezeras = { " ", " " };
		String[] fila = new String[2];

		DefaultTableModel model = new DefaultTableModel(null, cabezeras);

		for (Mensaje me : mensajes) {
			if (me.getUsuario1().equalsIgnoreCase("1alvaro")) {
				fila[0] = me.getMensaje() + " ";
			} else if (me.getUsuario1().equalsIgnoreCase("xDoble_Jx")) {
				fila[1] = me.getMensaje() + " ";
			}

			model.addRow(fila);
			fila[1] = " ";
			fila[0] = " ";
		}

		return new JTable(model);

	}

	protected void enviarMensaje(List<Mensaje> mensajes) {
		Mensaje men = new Mensaje();
		String codigo;
		String ultimoCodigo;
		int numCod;
		String usuario1 = usu.getUsuario();
		String usuario2 = "1alvaro";

		ultimoCodigo = dao.calcularIdMensaje("M");
		numCod = Integer.parseInt(ultimoCodigo.substring(2));
		numCod++;
		codigo = "M-" + String.format("%03d", numCod);

		men.setIdMensaje(codigo);
		men.setUsuario1(usuario1);
		men.setUsuario2(usuario2);
		men.setMensaje(introducirMensaje.getText());

		dao.insertarMensaje(men, usuario1, usuario2);

		introducirMensaje.setText(" ");

	}
	 public void eliminar(JTable tabla){
	        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
	        int a = tabla.getRowCount()-1;
	        for (int i = a; i >= 0; i--) {          
	        tb.removeRow(tb.getRowCount()-1);
	        }
	        //cargaTicket();
	    }

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
