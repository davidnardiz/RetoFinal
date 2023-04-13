package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conversacion extends JDialog implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private Usuario usu;
	private JButton btnIniciarConver;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private boolean conver = true;
	public Conversacion(ParaTi paraTi, boolean b, DAO dao, Usuario usu) {
		super(paraTi);
		this.setModal(b);
		this.dao = dao;
		this.usu = usu;
		
		int alto = 864;
		int ancho = (alto / 4) * 3;
		
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String nombre = usu.getUsuario();
		JLabel lblNewLabel = new JLabel(nombre);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(225, 23, 184, 29);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(" MENSAJES:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(44, 76, 155, 29);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("No tienes conversaciones");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(192, 122, 163, 45);
		contentPanel.add(lblNewLabel_2);
		
		btnIniciarConver = new JButton("Iniciar Conversacion");
		btnIniciarConver.setBounds(192, 178, 163, 23);
		contentPanel.add(btnIniciarConver);
		btnIniciarConver.addActionListener(this);
		
		if(!dao.buscarConver(nombre)) {
			
		}else {
			lblNewLabel_2.setVisible(false);
			btnIniciarConver.setVisible(false);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnIniciarConver)) {
			boolean conver = true;
			Buscar buscar = new Buscar(null,true, dao, usu, conver);
			this.setVisible(false);
			buscar.setVisible(true);
		}
		
	}
}
