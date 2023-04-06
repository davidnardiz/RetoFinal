package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DAO;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class Subir extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;

	private JRadioButton rdbtnImagen;
	private JRadioButton rdbtnReel;
	private JRadioButton rdbtnHistoria;
	private JTextField etiquetado;
	private JComboBox<String> cancion;
	private JComboBox<String> resolucion;
	private JComboBox<String> tipoHistoria;
	private JSlider duracion;
	private JButton btnSubir;
	private JToggleButton mejoresAmigos;
	
	private ButtonGroup tipoPublicacion = new ButtonGroup();
	private JLabel lblTipoHistoria;
	private JLabel lblCancion;
	private JLabel lblEtiquetado;
	private JSeparator separator_1;
	private JLabel lblDuracion;
	private JLabel segundos;
	private JLabel lblResolucion;
	private JLabel lblMejoresAmigos;

	public Subir(ParaTi paraTi, boolean b, DAO dao) {
		super(paraTi);
		this.setModal(b);
		this.dao = dao;

		//int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
		int alto = 864;
		int ancho = (alto / 4) * 3;

		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Que vas a subir?");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 31));
		lblNewLabel.setBounds(215, 11, 202, 41);
		contentPanel.add(lblNewLabel);

		cancion = new JComboBox<String>();
		cancion.setBounds(293, 358, 228, 34);
		contentPanel.add(cancion);

		rdbtnImagen = new JRadioButton("Imagen");
		rdbtnImagen.setFont(new Font("Serif", Font.PLAIN, 20));
		rdbtnImagen.setForeground(Color.WHITE);
		rdbtnImagen.setBackground(new Color(49, 51, 53));
		rdbtnImagen.setBounds(76, 110, 109, 23);
		contentPanel.add(rdbtnImagen);
		rdbtnImagen.addActionListener(this);
		rdbtnImagen.setSelected(true);
		
		rdbtnReel = new JRadioButton("Reel");
		rdbtnReel.setFont(new Font("Serif", Font.PLAIN, 20));
		rdbtnReel.setForeground(Color.WHITE);
		rdbtnReel.setBackground(new Color(49, 51, 53));
		rdbtnReel.setBounds(261, 110, 109, 23);
		contentPanel.add(rdbtnReel);
		rdbtnReel.addActionListener(this);
		
		rdbtnHistoria = new JRadioButton("Historia");
		rdbtnHistoria.setFont(new Font("Serif", Font.PLAIN, 20));
		rdbtnHistoria.setForeground(Color.WHITE);
		rdbtnHistoria.setBackground(new Color(49, 51, 53));
		rdbtnHistoria.setBounds(446, 110, 109, 23);
		contentPanel.add(rdbtnHistoria);
		rdbtnHistoria.addActionListener(this);
		
		tipoPublicacion.add(rdbtnImagen);
		tipoPublicacion.add(rdbtnReel);
		tipoPublicacion.add(rdbtnHistoria);
		
		JTextPane txtpnFoto = new JTextPane();
		txtpnFoto.setText("FOTO");
		txtpnFoto.setBounds(122, 218, 388, 100);
		contentPanel.add(txtpnFoto);

		resolucion = new JComboBox<String>();
		resolucion.setBounds(301, 497, 228, 34);
		contentPanel.add(resolucion);

		etiquetado = new JTextField();
		etiquetado.setBounds(301, 595, 228, 34);
		contentPanel.add(etiquetado);
		etiquetado.setColumns(10);

		duracion = new JSlider();
		duracion.setBounds(76, 564, 494, 41);
		contentPanel.add(duracion);
		duracion.setVisible(false); 
		
		mejoresAmigos = new JToggleButton("New toggle button");
		mejoresAmigos.setBounds(333, 497, 228, 34);
		contentPanel.add(mejoresAmigos);
		mejoresAmigos.setVisible(false); 
		
		tipoHistoria = new JComboBox<String>();
		tipoHistoria.setBounds(333, 595, 228, 34);
		contentPanel.add(tipoHistoria);
		tipoHistoria.setVisible(false); 
		
		btnSubir = new JButton("Subir");
		btnSubir.setBackground(SystemColor.controlHighlight);
		btnSubir.setForeground(Color.WHITE);
		btnSubir.setFont(new Font("Serif", Font.PLAIN, 20));
		btnSubir.setBounds(211, 719, 210, 53);
		contentPanel.add(btnSubir);
				
		lblTipoHistoria = new JLabel("Tipo de Historia:");
		lblTipoHistoria.setForeground(Color.WHITE);
		lblTipoHistoria.setFont(new Font("Serif", Font.PLAIN, 20));
		lblTipoHistoria.setBounds(88, 599, 138, 27);
		contentPanel.add(lblTipoHistoria);
		lblTipoHistoria.setVisible(false); 
		
		lblCancion = new JLabel("Cancion:");
		lblCancion.setForeground(Color.WHITE);
		lblCancion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblCancion.setBounds(111, 358, 71, 27);
		contentPanel.add(lblCancion);
		
		lblEtiquetado = new JLabel("Etiquetado:");
		lblEtiquetado.setForeground(Color.WHITE);
		lblEtiquetado.setFont(new Font("Serif", Font.PLAIN, 20));
		lblEtiquetado.setBounds(103, 599, 95, 27);
		contentPanel.add(lblEtiquetado);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 158, 612, 13);
		contentPanel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(10, 428, 612, 13);
		contentPanel.add(separator_1);
		
		lblDuracion = new JLabel("Duracion:");
		lblDuracion.setForeground(Color.WHITE);
		lblDuracion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblDuracion.setBounds(275, 501, 95, 27);
		contentPanel.add(lblDuracion);
		lblDuracion.setVisible(false); 
		
		segundos = new JLabel("New label");
		segundos.setBounds(509, 560, 46, 14);
		contentPanel.add(segundos);
		segundos.setVisible(false); 
		
		lblResolucion = new JLabel("Resolucion:");
		lblResolucion.setForeground(Color.WHITE);
		lblResolucion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblResolucion.setBounds(103, 501, 95, 27);
		contentPanel.add(lblResolucion);
		
		lblMejoresAmigos = new JLabel("Subir a mejores amigos?");
		lblMejoresAmigos.setForeground(Color.WHITE);
		lblMejoresAmigos.setFont(new Font("Serif", Font.PLAIN, 20));
		lblMejoresAmigos.setBounds(69, 504, 195, 27);
		contentPanel.add(lblMejoresAmigos);
		lblMejoresAmigos.setVisible(false); 

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(rdbtnImagen)) {
			imagen();
		}if(e.getSource().equals(rdbtnReel)) {
			reel();
		}if(e.getSource().equals(rdbtnHistoria)) {
			historia();
		}
		
	}

	private void historia() {
		JComponent[] mostrar = {mejoresAmigos, lblMejoresAmigos, tipoHistoria, lblTipoHistoria};
		JComponent[] ocultar = {resolucion, lblResolucion, etiquetado, lblEtiquetado, duracion, lblDuracion};
	
		
		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}
		
		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}

	}

	private void reel() {
		JComponent[] mostrar = {duracion, lblDuracion};
		JComponent[] ocultar = {resolucion, lblResolucion, etiquetado, lblEtiquetado, mejoresAmigos, lblMejoresAmigos, tipoHistoria, lblTipoHistoria};
	
		
		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}
		
		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}

	}

	private void imagen() {
		JComponent[] mostrar = {resolucion, lblResolucion, etiquetado, lblEtiquetado};
		JComponent[] ocultar = {duracion, lblDuracion, mejoresAmigos, lblMejoresAmigos, tipoHistoria, lblTipoHistoria};
	
		
		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}
		
		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}
		
		
		
	}
}
