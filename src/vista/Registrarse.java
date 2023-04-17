package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;
import utilidades.Utilidades;

import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;

public class Registrarse extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private JTextField usuario;
	private JPasswordField contrasenia;
	private JTextField dni;
	private JTextField nombre;
	private JTextField correo;
	private JTextField telefono;
	private JTextField fechaNacimiento;
	private ButtonGroup grupo;
	private JButton btnLimpiar;
	private JButton btnRegistrarse;
	private JButton btnVolver;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JToggleButton btnVerContrasenia;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param dao
	 * @param b
	 * @param principal
	 */
	public Registrarse(Principal principal, boolean b, DAO dao) {
		super(principal);
		this.dao = dao;
		setModal(b);
		setBounds(100, 100, 644, 714);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel icono = new JLabel("New label");
		icono.setBounds(28, 26, 58, 58);
		icono.setIcon(new ImageIcon(Principal.class.getResource("/utilidades/logo2.png")));
		icono.setVisible(true);
		contentPanel.add(icono);

		JLabel titulo = new JLabel("");
		titulo.setIcon(new ImageIcon(Principal.class.getResource("/utilidades/6666-removebg-preview.png")));
		titulo.setBounds(96, 31, 357, 73);
		titulo.setVisible(true);
		contentPanel.add(titulo);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 632, 112);
		contentPanel.add(textPane);

		usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setBounds(279, 154, 221, 35);
		contentPanel.add(usuario);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario.setBounds(81, 146, 95, 42);
		contentPanel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContrasea.setBounds(81, 199, 156, 42);
		contentPanel.add(lblContrasea);

		contrasenia = new JPasswordField();
		contrasenia.setBounds(279, 207, 221, 35);
		contentPanel.add(contrasenia);

		dni = new JTextField();
		dni.setColumns(10);
		dni.setBounds(279, 260, 221, 35);
		contentPanel.add(dni);

		JLabel lblUsuario_1 = new JLabel("Dni:");
		lblUsuario_1.setForeground(Color.WHITE);
		lblUsuario_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1.setBounds(81, 252, 95, 42);
		contentPanel.add(lblUsuario_1);

		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(279, 313, 221, 35);
		contentPanel.add(nombre);

		JLabel lblUsuario_1_1 = new JLabel("Nombre:");
		lblUsuario_1_1.setForeground(Color.WHITE);
		lblUsuario_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1_1.setBounds(81, 305, 95, 42);
		contentPanel.add(lblUsuario_1_1);

		correo = new JTextField();
		correo.setColumns(10);
		correo.setBounds(279, 366, 221, 35);
		contentPanel.add(correo);

		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(279, 419, 221, 35);
		contentPanel.add(telefono);

		JLabel lblUsuario_1_1_1 = new JLabel("Correo:");
		lblUsuario_1_1_1.setForeground(Color.WHITE);
		lblUsuario_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1_1_1.setBounds(81, 358, 95, 42);
		contentPanel.add(lblUsuario_1_1_1);

		JLabel lblUsuario_1_1_2 = new JLabel("Tel\u00E9fono:");
		lblUsuario_1_1_2.setForeground(Color.WHITE);
		lblUsuario_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1_1_2.setBounds(81, 411, 95, 42);
		contentPanel.add(lblUsuario_1_1_2);

		JLabel lblUsuario_1_1_3 = new JLabel("Sexo:");
		lblUsuario_1_1_3.setForeground(Color.WHITE);
		lblUsuario_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1_1_3.setBounds(81, 517, 95, 42);
		contentPanel.add(lblUsuario_1_1_3);

		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnHombre.setForeground(new Color(255, 255, 255));
		rdbtnHombre.setBackground(new Color(49, 51, 53));
		rdbtnHombre.setBounds(294, 525, 103, 21);
		contentPanel.add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtnMujer.setForeground(new Color(255, 255, 255));
		rdbtnMujer.setBackground(new Color(49, 51, 53));
		rdbtnMujer.setBounds(412, 525, 103, 21);
		contentPanel.add(rdbtnMujer);

		grupo = new ButtonGroup();
		grupo.add(rdbtnHombre);
		grupo.add(rdbtnMujer);

		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBackground(SystemColor.controlHighlight);
		btnVolver.setBounds(66, 593, 121, 49);
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimpiar.setBackground(SystemColor.controlHighlight);
		btnLimpiar.setBounds(253, 593, 121, 49);
		btnLimpiar.addActionListener(this);
		contentPanel.add(btnLimpiar);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegistrarse.setBackground(SystemColor.controlHighlight);
		btnRegistrarse.setBounds(440, 593, 121, 49);
		btnRegistrarse.addActionListener(this);
		contentPanel.add(btnRegistrarse);

		JLabel lblUsuario_1_1_2_1 = new JLabel("Fecha nacimiento:");
		lblUsuario_1_1_2_1.setForeground(Color.WHITE);
		lblUsuario_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario_1_1_2_1.setBounds(81, 464, 192, 42);
		contentPanel.add(lblUsuario_1_1_2_1);

		fechaNacimiento = new JTextField();
		fechaNacimiento.setColumns(10);
		fechaNacimiento.setBounds(279, 471, 221, 35);
		contentPanel.add(fechaNacimiento);
		
		btnVerContrasenia = new JToggleButton("");
		btnVerContrasenia.setSelectedIcon(new ImageIcon(Registrarse.class.getResource("/utilidades/ojoAbierto-removebg-preview222.png")));
		btnVerContrasenia.setIcon(new ImageIcon(Registrarse.class.getResource("/utilidades/ojoCerrado-removebg-preview2222.png")));
		btnVerContrasenia.setBackground(SystemColor.textHighlightText);
		btnVerContrasenia.setBounds(513, 206, 35, 35);
		btnVerContrasenia.addActionListener(this);
		contentPanel.add(btnVerContrasenia);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnRegistrarse)) {
			registrar();
		}
		if (e.getSource().equals(btnLimpiar)) {
			limpiar();
		}
		if (e.getSource().equals(btnVolver)) {
			volver();
		}
		if (e.getSource().equals(btnVerContrasenia)) {
			verContrasenia();
		}
	}

	private void verContrasenia() {
		// TODO Auto-generated method stub
		if(btnVerContrasenia.isSelected()) {
			contrasenia.setEchoChar((char) 0);
			
		}else {
			contrasenia.setEchoChar((char) '*');
		}
	}

	private void registrar() {
		// TODO Auto-generated method stub

		String error = comprobarDatosUsuario();
		if (error == "") {
			Usuario us = new Usuario();
			us.setUsuario(usuario.getText());
			us.setContrasenia(contrasenia.getText());
			us.setDni(dni.getText());
			us.setNombre_completo(nombre.getText());
			us.setCorreo(correo.getText());
			us.setTelefono(Integer.parseInt(telefono.getText()));
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate fecha = LocalDate.parse(fechaNacimiento.getText(), formateador);
			us.setFecha_nac(fecha);
			us.setNumSeguidores(Utilidades.numeros_aleatorios(0, 5000000));
			us.setNumSeguidos(Utilidades.numeros_aleatorios(0, Integer.parseInt(us.getNumSeguidores() * 1.05 + "")));

			if (us.getNumSeguidores() > 4500000) {
				us.setVerificado(true);
			} else {
				us.setVerificado(false);
			}

			if (rdbtnHombre.isSelected()) {
				us.setGenero("Hombre");
			} else if (rdbtnMujer.isSelected()) {
				us.setGenero("Mujer");
			}

			if (dao.registrar(us)) {
				JOptionPane.showMessageDialog(this, "Registro correctamente hecha!!!");
				limpiar();
			} else {
				JOptionPane.showMessageDialog(this, "Registro no completado!!!");
			}

		} else {
			JOptionPane.showMessageDialog(this, error);
		}

	}

	private void volver() {
		// TODO Auto-generated method stub
		this.dispose();
		Principal prin = new Principal(dao);
		prin.setVisible(true);
	}

	private void limpiar() {
		// TODO Auto-generated method stub
		usuario.setText("");
		usuario.setBackground(new Color(255, 255, 255));
		contrasenia.setText("");
		contrasenia.setBackground(new Color(255, 255, 255));
		dni.setText("");
		dni.setBackground(new Color(255, 255, 255));
		nombre.setText("");
		nombre.setBackground(new Color(255, 255, 255));
		correo.setText("");
		correo.setBackground(new Color(255, 255, 255));
		telefono.setText("");
		telefono.setBackground(new Color(255, 255, 255));
		fechaNacimiento.setText("");
		fechaNacimiento.setBackground(new Color(255, 255, 255));
		grupo.clearSelection();
	}

	public String comprobarDatosUsuario() {
		// Comprobar usuario
		String error = "";
		if (usuario.getText().length() > 20 || usuario.getText().length() == 0) {
			error += "El usuario no es válido.\n";
			usuario.setBackground(new Color(233, 0, 0));
		} else if (usuario.getText().length() > 1) {
			usuario.setBackground(new Color(142, 246, 86));
		}

		if (contrasenia.getText().length() > 20 || usuario.getText().length() == 0) {
			error += "La contraseña es demasiado larga.\n";
			contrasenia.setBackground(new Color(233, 0, 0));
		} else if (contrasenia.getText().length() > 1) {
			contrasenia.setBackground(new Color(142, 246, 86));
		}

		Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
		Matcher mat = patron.matcher(dni.getText());
		if (!mat.matches()) {
			error += "El dni no es válido.\n";
			dni.setBackground(new Color(233, 0, 0));
		} else {
			dni.setBackground(new Color(142, 246, 86));
		}

		if (nombre.getText().length() > 50 || nombre.getText().matches("[A-Z]*")) {
			error += "El nombre no es válido.\n";
			nombre.setBackground(new Color(233, 0, 0));
		} else {
			nombre.setBackground(new Color(142, 246, 86));
		}

		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(correo.getText());

		if (!matcher.matches()) {
			error += "El correo no es válido. \n";
			correo.setBackground(new Color(233, 0, 0));
		} else {
			correo.setBackground(new Color(142, 246, 86));
		}

		if (telefono.getText().matches("[0-9]{0,8}")) {
			error += "El teléfono no es válido.\n";
			telefono.setBackground(new Color(233, 0, 0));
		} else {
			telefono.setBackground(new Color(142, 246, 86));
		}

		if (!rdbtnHombre.isSelected() && !rdbtnMujer.isSelected()) {
			error += "El sexo no ha sido seleccionado.\n";
		}

		return error;
	}
}
