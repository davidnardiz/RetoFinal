package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class Buscar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private Usuario usu;

	private JTextField buscador;
	private int bucle;

	private JLabel lblIcono;
	private JLabel lblIcono_1;
	private JLabel lblIcono_2;
	private JLabel lblIcono_3;
	private JLabel lblIcono_4;
	private JLabel lblUsuario;
	private JLabel lblUsuario_1;
	private JLabel lblUsuario_2;
	private JLabel lblUsuario_3;
	private JLabel lblUsuario_4;

	private JLabel[] iconos = { lblIcono, lblIcono_1, lblIcono_2, lblIcono_3, lblIcono_4 };
	private JLabel[] usuarios = { lblUsuario, lblUsuario_1, lblUsuario_2, lblUsuario_3, lblUsuario_4 };

	private List<Usuario> usuariosList;

	private JButton btnBuscar;

	public Buscar(ParaTi paraTi, boolean b, DAO dao, Usuario usu) {
		super(paraTi);
		this.setModal(b);
		this.dao = dao;

		int alto = 864;
		int ancho = (alto / 4) * 3;
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/6666-removebg-preview.png")));
		lblNewLabel_1.setBounds(95, 21, 282, 70);
		contentPanel.add(lblNewLabel_1);

		JLabel icono = new JLabel("New label");
		icono.setIcon(new ImageIcon(ParaTi.class.getResource("/utilidades/logo2.png")));
		icono.setBounds(27, 21, 58, 58);
		contentPanel.add(icono);

		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(43, 45, 47));
		textPane.setBounds(0, 0, 632, 100);
		contentPanel.add(textPane);

		buscador = new JTextField();
		buscador.addVetoableChangeListener(new VetoableChangeListener() {
			public void vetoableChange(PropertyChangeEvent evt) {
				buscar();
			}
		});
		buscador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		buscador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					buscar();
				}
			}
		});

		buscador.setBounds(166, 121, 300, 31);
		contentPanel.add(buscador);
		buscador.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(SystemColor.controlHighlight);
		btnBuscar.setBounds(493, 125, 89, 23);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 180, 595, 17);
		contentPanel.add(separator);

		usuariosList = dao.listarUsuario();

		int[] y_icono = { 214, 335, 456, 577, 698 };
		int[] y_usuario = { 224, 345, 466, 587, 708 };

		if (usuariosList.size() > iconos.length) {
			bucle = iconos.length;
		} else {
			bucle = usuariosList.size();
		}

		for (int i = 0; i < bucle; i++) {
			iconos[i] = new JLabel("");
			iconos[i].setHorizontalAlignment(SwingConstants.CENTER);
			iconos[i].setIcon(new ImageIcon(Buscar.class.getResource("/utilidades/" + usuariosList.get(i).getIcono())));
			iconos[i].setBounds(79, y_icono[i], 64, 64);
			contentPanel.add(iconos[i]);
			iconos[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					abrirPerfil();
				}
			});

			usuarios[i] = new JLabel(usuariosList.get(i).getUsuario());
			usuarios[i].setForeground(Color.WHITE);
			usuarios[i].setFont(new Font("Tahoma", Font.BOLD, 18));
			usuarios[i].setBounds(222, y_usuario[i], 330, 44);
			contentPanel.add(usuarios[i]);
			usuarios[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					abrirPerfil();
				}
			});
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {
			buscar();
		}

	}

	private void abrirPerfil() {
		Perfil perfil = new Perfil(this, true, dao);
		this.setVisible(false);
		perfil.setVisible(true);
	}

	private void buscar() {
		usuariosList = dao.listarUsuarioXUsuario(buscador.getText());

		// Si hay mas usuarios que iconos iterar sobre iconos
		if (usuariosList.size() > iconos.length) {
			bucle = iconos.length;

			// Si hay mas iconos que usuarios iterar sobre usuarios
		} else {
			bucle = usuariosList.size();
		}

		// Mostramos los usuarios y sus iconos
		for (int i = 0; i < bucle; i++) {
			usuarios[i].setText(usuariosList.get(i).getUsuario());
			iconos[i].setIcon(new ImageIcon(Buscar.class.getResource("/utilidades/" + usuariosList.get(i).getIcono())));
		}

		// Si hay menos usarios que labels, vaciomos los que sobren
		if (usuariosList.size() < iconos.length) {
			for (int i = iconos.length - 1; i >= bucle; i--) {
				usuarios[i].setText("");
				iconos[i].setIcon(null);
			}
		}
	}
}
