package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.Usuario;
import modelo.DAO;

public class PublicacionPopUp extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JLabel lblIcono;
	private JLabel lblHistoria;
	private JLabel lblUsuario;
	private JLabel lblDescripcion;
	private JLabel lblMegusta;
	private JLabel imagen;
	private JLabel lblVerificado;
	private JToggleButton btnLike;
	private JButton btnEtiquetado;

	public PublicacionPopUp(ParaTi paraTi, boolean b, Publicacion publi, Usuario usu) {
		super(paraTi);
		this.setModal(b);

		int alto = 864;
		int ancho = (alto / 4) * 3;
		setBounds(100, 100, ancho, alto);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		lblIcono = new JLabel();
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(119, 89, 64, 64);
		contentPanel.add(lblIcono);
		lblIcono.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/iconos/" + usu.getIcono())));
		lblIcono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPerfil();
			}
		});

		if (publi instanceof Historia) {
			lblHistoria = new JLabel("");
			lblHistoria.setBounds(101, 71, 100, 100);
			contentPanel.add(lblHistoria);
		}

		lblUsuario = new JLabel(usu.getUsuario());
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(239, 110, 141, 22);
		contentPanel.add(lblUsuario);
		lblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abrirPerfil();
			}
		});

		btnLike = new JToggleButton("");
		btnLike.setSelectedIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(True).png")));
		btnLike.setBackground(new Color(49, 51, 53));
		btnLike.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(false).png")));
		btnLike.setBounds(137, 640, 46, 40);
		contentPanel.add(btnLike);
		btnLike.setBorder(null);
		btnLike.setContentAreaFilled(false);
		btnLike.addActionListener(this);

		lblDescripcion = new JLabel();
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(137, 687, 463, 27);
		contentPanel.add(lblDescripcion);

		if (publi instanceof Foto) {
			lblDescripcion.setText(((Foto) publi).getDescripcion());
			
		} else if (publi instanceof Reel) {
			lblDescripcion.setText(((Reel) publi).getDescripcion());
			
		} else {
			lblDescripcion.setVisible(false);
		}

		lblMegusta = new JLabel("");
		lblMegusta.setForeground(new Color(255, 255, 255));
		lblMegusta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMegusta.setBounds(199, 640, 209, 40);
		contentPanel.add(lblMegusta);

		if (publi instanceof Foto) {
			btnEtiquetado = new JButton("");
			btnEtiquetado.setBackground(new Color(49, 51, 53));
			btnEtiquetado.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/etiquedado.png")));
			btnEtiquetado.setBounds(504, 593, 30, 30);
			contentPanel.add(btnEtiquetado);
			btnEtiquetado.addActionListener(this);
			btnEtiquetado.setBorder(null);
		}

		imagen = new JLabel();
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setBounds(70, 161, 475, 475);
		contentPanel.add(imagen);
		imagen.setIcon(
				new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));
		contentPanel.add(imagen);

		if (usu.isVerificado()) {
			lblVerificado = new JLabel("");
			lblVerificado.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/verificado.png")));
			lblVerificado.setBounds(199, 106, 30, 30);
			lblVerificado.setVisible(false);
			contentPanel.add(lblVerificado);
		}
	}

	private void abrirPerfil() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
