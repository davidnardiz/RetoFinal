package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private DAO dao;
	private Publicacion publi;
	private Usuario nosotros;
	private Usuario usu;
	
	private JLabel lblIcono;
	private JLabel lblHistoria;
	private JLabel lblUsuario;
	private JLabel lblDescripcion;
	private JLabel lblMegusta;
	private JLabel imagen;
	private JLabel lblVerificado;
	private JToggleButton btnLike;
	private JButton btnEtiquetados;

	private ParaTi paraTi;
	
	public PublicacionPopUp(ParaTi paraTi, boolean b, DAO dao, Publicacion publi, Usuario nosotros,
			Usuario usuarioPerfil) {
		super(paraTi);
		this.setModal(b);

		this.paraTi=paraTi;
		
		this.dao = dao;
		this.nosotros = nosotros;
		this.publi = publi;
		this.usu=usuarioPerfil;
		
		setBounds(100, 100, 510, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(49, 51, 53));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);

		lblIcono = new JLabel();
		lblIcono.setHorizontalAlignment(SwingConstants.LEFT);
		lblIcono.setBounds(59, 15, 64, 64);
		contentPanel.add(lblIcono);
		lblIcono.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/iconos/" + usuarioPerfil.getIcono())));

		if (publi instanceof Historia) {
			lblHistoria = new JLabel("");
			lblHistoria.setBounds(101, 71, 100, 100);
			contentPanel.add(lblHistoria);

			if (!((Historia) publi).isMejores_amigos()) {
				lblHistoria.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/pantalla/esHistoria")));
			} else {
				lblHistoria.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/pantalla/esMejos")));
			}
		}

		lblUsuario = new JLabel(usuarioPerfil.getUsuario());
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(179, 36, 141, 22);
		contentPanel.add(lblUsuario);

		btnLike = new JToggleButton("");
		btnLike.setSelectedIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(True).png")));
		btnLike.setBackground(new Color(49, 51, 53));
		btnLike.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/btnLike(false).png")));
		btnLike.setBounds(74, 576, 46, 40);
		contentPanel.add(btnLike);
		btnLike.setBorder(null);
		btnLike.setContentAreaFilled(false);
		btnLike.addActionListener(this);
		btnLike.setSelected(dao.comprobarLike(nosotros.getUsuario(), publi.getId_publicacion()));

		lblDescripcion = new JLabel();
		lblDescripcion.setForeground(new Color(255, 255, 255));
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDescripcion.setBounds(74, 623, 410, 27);
		contentPanel.add(lblDescripcion);

		if (publi instanceof Foto) {
			lblDescripcion.setText(((Foto) publi).getDescripcion());

		} else if (publi instanceof Reel) {
			lblDescripcion.setText(((Reel) publi).getDescripcion());

		} else {
			lblDescripcion.setVisible(false);
		}

		lblMegusta = new JLabel(publi.getNumLikes() + "");
		lblMegusta.setForeground(new Color(255, 255, 255));
		lblMegusta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMegusta.setBounds(136, 576, 209, 40);
		contentPanel.add(lblMegusta);

		

		imagen = new JLabel();
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setBounds(10, 90, 475, 475);
		contentPanel.add(imagen);
		imagen.setIcon(
				new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));
		contentPanel.add(imagen);
		
		btnEtiquetados = new JButton("");
		btnEtiquetados.setBackground(new Color(49, 51, 53));
		btnEtiquetados.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/pantalla/etiquedado.png")));
		btnEtiquetados.setBounds(406, 570, 46, 40);
		btnEtiquetados.addActionListener(this);
		btnEtiquetados.setBorderPainted(false);
		contentPanel.add(btnEtiquetados);

		if (usuarioPerfil.isVerificado()) {
			lblVerificado = new JLabel("");
			lblVerificado.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/verificado.png")));
			lblVerificado.setBounds(199, 106, 30, 30);
			lblVerificado.setVisible(false);
			contentPanel.add(lblVerificado);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLike)) {
			darLike();
		}
		if(e.getSource().equals(btnEtiquetados)) {
			abrirPerfil();
		}
	}

	private void darLike() {
		if (btnLike.isSelected()) {
			lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) + 1 + "");
			dao.insertarLike(nosotros.getUsuario(), publi.getId_publicacion());

		} else {
			lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) - 1 + "");
			dao.quitarLike(nosotros.getUsuario(), publi.getId_publicacion());
		}

	}
	
	private void abrirPerfil() {
		Usuario per = dao.buscarUsuario(publi.getEtiquetado());
		Perfil perfil = new Perfil(paraTi, true, dao, usu, per);
		this.setVisible(false);
		perfil.setVisible(true);

	}
}
