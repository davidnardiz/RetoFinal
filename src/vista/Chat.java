package vista;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.Mensaje;
import clases.Usuario;
import modelo.DAO;

import javax.swing.JTextArea;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
	public Chat(Buscar buscar, boolean b, DAO dao, Usuario usu) {
		this.dao = dao;
		this.usu = usu;
		int alto = 864;
		int ancho = (alto / 4) * 3;
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		String usuario1 = usu.getUsuario();
		String usuario2 = "1alvaro";
		List<Mensaje> mensajes = dao.sacarIdMensajes(usuario1, usuario2); 
		
		introducirMensaje = new JTextField();
		introducirMensaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					enviarMensaje(mensajes);
				}
			}
		});
		introducirMensaje.setBounds(23, 665, 574, 133);

		contentPanel.add(introducirMensaje);
		introducirMensaje.setColumns(10);

	

		
		presentarTabla(mensajes);
		ponerDerecha(tabla);

	}

	private void ponerDerecha(JTable tabla2) {
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		tabla.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

		cellRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void presentarTabla(List<Mensaje> mensajes) {
		JScrollPane scroll = new JScrollPane();
		tabla = this.cargarTabla(mensajes);
		scroll.setViewportView(tabla);
		contentPanel.add(scroll);
		scroll.setBounds(10, 65, 599, 585);
	}

	private JTable cargarTabla(List<Mensaje> mensajes) {
		String[] cabezeras = { " ", " " };
		String[] fila = new String[2];

		DefaultTableModel model = new DefaultTableModel(null, cabezeras);

		for (Mensaje me : mensajes) {
			if (me.getUsuario1().equalsIgnoreCase("1alvaro")) {
				fila[0] = me.getMensaje() + " ";
			} else if(me.getUsuario1().equalsIgnoreCase("xDoble_Jx")){
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
	
		List<Mensaje> mensaje = dao.sacarIdMensajes(usuario1, usuario2); 
		presentarTabla(mensaje);
		cargarTabla(mensaje);
		ponerDerecha(tabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
