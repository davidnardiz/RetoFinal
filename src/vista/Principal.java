package vista;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

import javax.swing.JButton;

public class Principal extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JButton btnRegistrarse;
	private DAO dao;
	private Usuario usu;
	
	/**
	 * Create the frame.
	 * @param dao 
	 */
	
	public Principal(DAO dao) {
		this.dao = dao;
		
		int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		int ancho = (alto/4) * 3;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, ancho, alto);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(443, 675, 107, 43);
		contentPane.add(btnIniciar);
		btnIniciar.addActionListener(this);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(26, 675, 107, 43);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(this);
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
		IniciarSesion ini = new IniciarSesion(this, true, dao, usu);
		this.setVisible(false);
		ini.setVisible(true);
		
	}


	private void iniciarSesion() {
		Registrarse regis = new Registrarse(this,true, dao);
		this.setVisible(false);
		regis.setVisible(true);
	}
}
