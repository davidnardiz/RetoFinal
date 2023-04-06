package vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DAO;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JButton btnRegistrarse;
	private DAO dao;
	private JTextPane textPane;
	private JLabel icono;
	private JLabel lblNewLabel;
	
	/**
	 * Create the frame.
	 * @param dao 
	 */
	
	public Principal(DAO dao) {
		this.dao = dao;
		
		int alto = 864;
		int ancho = (alto/4) * 3;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 520);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(49, 51, 53));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/utilidades/6666-removebg-preview.png")));
		lblNewLabel.setBounds(96, 31, 357, 73);
		lblNewLabel.setVisible(true);
		contentPane.add(lblNewLabel);
		
		icono = new JLabel("New label");
		icono.setIcon(new ImageIcon(Principal.class.getResource("/utilidades/logo2.png")));
		icono.setBounds(28, 26, 58, 58);
		icono.setVisible(true);
		contentPane.add(icono);
		
		btnIniciar = new JButton("Iniciar sesi\u00F3n");
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIniciar.setBounds(209, 193, 195, 58);
		contentPane.add(btnIniciar);
		btnIniciar.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setBounds(209, 315, 195, 58);
		btnRegistrarse.addActionListener(this);
		contentPane.add(btnRegistrarse);
		
		textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 632, 112);
		contentPane.add(textPane);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnIniciar)) {
			iniciarSesion();
		}else if(e.getSource().equals(btnRegistrarse)) {
			Registrarse();
		}
	}


	private void Registrarse() {
		IniciarSesion ini = new IniciarSesion(this, true, dao);
		this.setVisible(false);
		ini.setVisible(true);
		
	}


	private void iniciarSesion() {
		Registrarse regis = new Registrarse(this,true, dao);
		this.setVisible(false);
		regis.setVisible(true);
	}
}
