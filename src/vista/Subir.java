package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.Cancion;
import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.TipoHistoria;
import clases.Usuario;
import modelo.DAO;
import utilidades.Utilidades;

public class Subir extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DAO dao;
	private Usuario usu;
	private String fotoSeleccionada;
	private ParaTi paraTi;

	private JRadioButton rdbtnImagen;
	private JRadioButton rdbtnReel;
	private JRadioButton rdbtnHistoria;
	private JTextField etiquetado;
	private JComboBox<String> cancion;
	private JComboBox<String> resolucion;
	private JComboBox<String> tipoHistoria;
	private JSlider duracion;
	private JButton btnSubir;

	private ButtonGroup tipoPublicacion = new ButtonGroup();
	private JLabel lblTipoHistoria;
	private JLabel lblCancion;
	private JLabel lblEtiquetado;
	private JSeparator separator_1;
	private JLabel lblDuracion;
	private JLabel segundos;
	private JLabel lblResolucion;
	private JLabel lblMejoresAmigos;
	private JLabel lblUbicacion;
	private JLabel lblDescripcion;
	private JTextArea descripcion;
	private JButton btnElegir;
	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	private ButtonGroup mejos = new ButtonGroup();
	private JTextField ubicacion;

	public Subir(ParaTi paraTi, boolean b, DAO dao, Usuario usu) {
		super(paraTi);
		setTitle("Subir Publicacion");
		this.setModal(b);
		this.paraTi = paraTi;
		this.dao = dao;
		this.usu = usu;

		this.usu = new Usuario();
		// int alto = Toolkit.getDefaultToolkit().getScreenSize().height;
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

		List<Cancion> canciones = dao.listarCanciones();
		cancion = new JComboBox<String>();
		cancion.setBounds(301, 289, 228, 34);
		contentPanel.add(cancion);

		for (int i = 0; i < canciones.size(); i++) {
			cancion.addItem(canciones.get(i).getTitulo());
		}

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

		resolucion = new JComboBox<String>();
		resolucion.setBounds(301, 452, 228, 34);
		contentPanel.add(resolucion);
		resolucion.addItem("144p");
		resolucion.addItem("240p");
		resolucion.addItem("360p");
		resolucion.addItem("480p");
		resolucion.addItem("720p");
		resolucion.addItem("1080p");
		resolucion.setSelectedIndex(-1);

		etiquetado = new JTextField();
		etiquetado.setBounds(301, 519, 228, 34);
		contentPanel.add(etiquetado);
		etiquetado.setColumns(10);

		segundos = new JLabel("");
		segundos.setForeground(new Color(255, 255, 255));
		segundos.setFont(new Font("Serif", Font.PLAIN, 20));
		segundos.setBounds(508, 508, 61, 23);
		contentPanel.add(segundos);
		segundos.setVisible(false);

		duracion = new JSlider();
		duracion.setValue(0);
		duracion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				segundos.setText(duracion.getValue() + "s");
			}
		});

		duracion.setBackground(new Color(49, 51, 53));
		duracion.setForeground(new Color(49, 51, 53));
		duracion.setMaximum(120);
		duracion.setBounds(76, 524, 494, 41);
		contentPanel.add(duracion);
		duracion.setVisible(false);

		List<TipoHistoria> tipoHistorias = dao.listarTipoHistorias();

		tipoHistoria = new JComboBox<String>();
		tipoHistoria.setBounds(301, 595, 228, 34);
		contentPanel.add(tipoHistoria);
		tipoHistoria.setVisible(false);

		for (int i = 0; i < tipoHistorias.size(); i++) {
			tipoHistoria.addItem(tipoHistorias.get(i).getTipo());
		}

		btnSubir = new JButton("Subir");
		btnSubir.setBackground(SystemColor.controlHighlight);
		btnSubir.setForeground(new Color(0, 0, 0));
		btnSubir.setFont(new Font("Serif", Font.PLAIN, 20));
		btnSubir.setBounds(211, 719, 210, 53);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		lblTipoHistoria = new JLabel("Tipo de Historia:");
		lblTipoHistoria.setForeground(Color.WHITE);
		lblTipoHistoria.setFont(new Font("Serif", Font.PLAIN, 20));
		lblTipoHistoria.setBounds(88, 599, 138, 27);
		contentPanel.add(lblTipoHistoria);
		lblTipoHistoria.setVisible(false);

		lblCancion = new JLabel("Cancion:");
		lblCancion.setForeground(Color.WHITE);
		lblCancion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblCancion.setBounds(103, 289, 71, 27);
		contentPanel.add(lblCancion);

		lblEtiquetado = new JLabel("Etiquetado:");
		lblEtiquetado.setForeground(Color.WHITE);
		lblEtiquetado.setFont(new Font("Serif", Font.PLAIN, 20));
		lblEtiquetado.setBounds(103, 525, 95, 27);
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
		lblDuracion.setBounds(275, 461, 95, 27);
		contentPanel.add(lblDuracion);
		lblDuracion.setVisible(false);

		lblResolucion = new JLabel("Resolucion:");
		lblResolucion.setForeground(Color.WHITE);
		lblResolucion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblResolucion.setBounds(103, 456, 95, 27);
		contentPanel.add(lblResolucion);

		lblMejoresAmigos = new JLabel("Subir a mejores amigos?");
		lblMejoresAmigos.setForeground(Color.WHITE);
		lblMejoresAmigos.setFont(new Font("Serif", Font.PLAIN, 20));
		lblMejoresAmigos.setBounds(69, 504, 195, 27);
		contentPanel.add(lblMejoresAmigos);
		lblMejoresAmigos.setVisible(false);

		btnElegir = new JButton("Elegir foto");
		btnElegir.setFont(new Font("Serif", Font.PLAIN, 20));
		btnElegir.setBackground(SystemColor.controlHighlight);
		btnElegir.setBounds(215, 193, 210, 55);
		contentPanel.add(btnElegir);

		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setForeground(new Color(255, 255, 255));
		rdbtnSi.setFont(new Font("Serif", Font.PLAIN, 20));
		rdbtnSi.setBackground(new Color(49, 51, 53));
		rdbtnSi.setBounds(301, 508, 109, 23);
		contentPanel.add(rdbtnSi);
		rdbtnSi.setVisible(false);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setForeground(new Color(255, 255, 255));
		rdbtnNo.setFont(new Font("Serif", Font.PLAIN, 20));
		rdbtnNo.setBackground(new Color(49, 51, 53));
		rdbtnNo.setBounds(412, 508, 109, 23);
		contentPanel.add(rdbtnNo);
		btnElegir.addActionListener(this);
		rdbtnNo.setVisible(false);

		mejos.add(rdbtnSi);
		mejos.add(rdbtnNo);

		lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setForeground(Color.WHITE);
		lblUbicacion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblUbicacion.setBounds(103, 358, 87, 27);
		contentPanel.add(lblUbicacion);

		ubicacion = new JTextField();
		ubicacion.setBounds(301, 358, 228, 34);
		contentPanel.add(ubicacion);
		ubicacion.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblDescripcion.setBounds(103, 595, 97, 27);
		contentPanel.add(lblDescripcion);

		descripcion = new JTextArea();
		descripcion.setBounds(301, 583, 228, 106);
		contentPanel.add(descripcion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(rdbtnImagen)) {
			imagen();
		} else if (e.getSource().equals(rdbtnReel)) {
			reel();
		} else if (e.getSource().equals(rdbtnHistoria)) {
			historia();
		} else if (e.getSource().equals(btnElegir)) {
			elegirFoto();
		} else if (e.getSource().equals(btnSubir)) {
			subirFoto();
		}

	}

	private void subirFoto() {
		Publicacion publi;
		String codigo;
		String ultimoCodigo;
		int numCod;

		int minLikes = usu.getNumSeguidores();
		int maxLikes = usu.getNumSeguidores();
		int minCom = usu.getNumSeguidores();
		int maxCom = usu.getNumSeguidores();

		if (comprobarCampos()) {
			if (rdbtnImagen.isSelected()) {
				ultimoCodigo = dao.calcularId("f");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "F-" + String.format("%03d", numCod);

				publi = new Foto();
				((Foto) publi).setDescripcion(descripcion.getText());
				((Foto) publi).setResolucion(resolucion.getSelectedItem().toString());
				((Foto) publi).setEtiquetado(etiquetado.getText());

			} else if (rdbtnReel.isSelected()) {
				ultimoCodigo = dao.calcularId("r");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "R-" + String.format("%03d", numCod);

				publi = new Reel();
				((Reel) publi).setDuracion(duracion.getValue());

				int minRrep = usu.getNumSeguidores();
				int maxRrep = usu.getNumSeguidores();

				((Reel) publi).setReproducciones(Utilidades.numeros_aleatorios(minRrep, maxRrep));

			} else {
				ultimoCodigo = dao.calcularId("h");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "R-" + String.format("%03d", numCod);

				publi = new Historia();

				if (rdbtnSi.isSelected()) {
					((Historia) publi).setMejores_amigos(true);
				} else {
					((Historia) publi).setMejores_amigos(false);
				}
				((Historia) publi).setCod_tipo(dao.tipoHistoria(tipoHistoria.getSelectedItem().toString()));
			}

			publi.setId_publicacion(codigo);
			publi.setImagen(fotoSeleccionada);
			publi.setNumLikes(Utilidades.numeros_aleatorios(minLikes, maxLikes));
			publi.setNumComentarios(Utilidades.numeros_aleatorios(minCom, maxCom));
			publi.setFecha_subida(LocalDate.now());
			publi.setUbicacion(ubicacion.getText());
			publi.setUsuario(usu.getUsuario());

			if (cancion.getSelectedIndex() != -1) {
				publi.setId_cancion(dao.buscarCancionXTitulo(cancion.getSelectedItem().toString()).getId_cancion());
			}

			dao.publicar(publi);

			JOptionPane.showMessageDialog(this, "Publicacion subida con exito", "", 3);
			paraTi.setVisible(true);
			this.dispose();
		}

	}

	private boolean comprobarCampos() {
		boolean correcto = true;

		if (fotoSeleccionada == null) {
			JOptionPane.showMessageDialog(this, "No has elegido ninguna imagen", "ERROR", 0);
			btnElegir.setBackground(new Color(204, 51, 51));
			btnElegir.setForeground(new Color(0, 0, 0));
			correcto = false;

		} else {
			btnElegir.setBackground(new Color(0, 153, 51));
			btnElegir.setForeground(new Color(0, 0, 0));
		}

		if (rdbtnImagen.isSelected()) {
			if (resolucion.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "No has elegido ninguna resolucion", "ERROR", 0);
				resolucion.setBackground(new Color(204, 51, 51));
				resolucion.setForeground(new Color(0, 0, 0));
				correcto = false;

			} else {
				resolucion.setBackground(new Color(0, 153, 51));
				resolucion.setForeground(new Color(0, 0, 0));
			}

			if (descripcion.getText().length() > 500) {
				JOptionPane.showMessageDialog(this, "La descripcion no puede tener mas de 500 caracteres", "ERROR", 0);
				descripcion.setBackground(new Color(204, 51, 51));
				descripcion.setForeground(new Color(0, 0, 0));
				correcto = false;

			} else {
				descripcion.setBackground(new Color(0, 153, 51));
				descripcion.setForeground(new Color(0, 0, 0));
			}

			if (ubicacion.getText().length() > 50) {
				JOptionPane.showMessageDialog(this, "La ubicacion no puede tener mas de 50 caracteres", "ERROR", 0);
				ubicacion.setBackground(new Color(204, 51, 51));
				ubicacion.setForeground(new Color(0, 0, 0));
				correcto = false;

			} else {
				ubicacion.setBackground(new Color(0, 153, 51));
				ubicacion.setForeground(new Color(0, 0, 0));
			}

			cancion.setBackground(new Color(0, 153, 51));
			cancion.setForeground(new Color(0, 0, 0));
			etiquetado.setBackground(new Color(0, 153, 51));
			etiquetado.setForeground(new Color(0, 0, 0));

		} else if (rdbtnReel.isSelected()) {
			if (duracion.getValue() == 0) {
				JOptionPane.showMessageDialog(this, "No has elegido ninguna duracion", "ERROR", 0);
				duracion.setBackground(new Color(204, 51, 51));
				duracion.setForeground(new Color(0, 0, 0));
				correcto = false;

			} else {
				duracion.setBackground(new Color(0, 153, 51));
				duracion.setForeground(new Color(0, 0, 0));
			}

		} else {
			if (!rdbtnSi.isSelected() && !rdbtnNo.isSelected()) {
				JOptionPane.showMessageDialog(this, "Por favor elige si quieres subirla a mejores amigos o si no");
				correcto = false;

			}

			if (tipoHistoria.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Por favor elige el tipo de la historia");
				tipoHistoria.setBackground(new Color(204, 51, 51));
				tipoHistoria.setForeground(new Color(0, 0, 0));
				correcto = false;

			} else {
				tipoHistoria.setBackground(new Color(0, 153, 51));
				tipoHistoria.setForeground(new Color(0, 0, 0));
			}

		}
		if (correcto) {
			return true;
		} else {
			return false;
		}
	}

	private void elegirFoto() {
		// Crear un objeto JFileChooser
		JFileChooser fileChooser = new JFileChooser();

		// Establecer una carpeta predeterminada
		String rutaProyecto = System.getProperty("user.dir");
		File defaultDir = new File(rutaProyecto + "/src/img");
		fileChooser.setCurrentDirectory(defaultDir);

		// Establecer un filtro para mostrar solo archivos de imagen
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png", "gif");
		fileChooser.setFileFilter(filter);

		// Mostrar el cuadro de diálogo del selector de archivos
		int result = fileChooser.showOpenDialog(this);

		// Si el usuario selecciona un archivo, mostrar su ruta
		if (result == JFileChooser.APPROVE_OPTION) {
			fotoSeleccionada = fileChooser.getSelectedFile().getAbsolutePath();
		}

	}

	private void historia() {
		JComponent[] mostrar = { rdbtnSi, rdbtnNo, lblMejoresAmigos, tipoHistoria, lblTipoHistoria };
		JComponent[] ocultar = { resolucion, lblResolucion, etiquetado, lblEtiquetado, duracion, lblDuracion, segundos,
				lblDescripcion, descripcion };

		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}

		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}

	}

	private void reel() {
		JComponent[] mostrar = { duracion, lblDuracion, segundos, lblDescripcion, descripcion };
		JComponent[] ocultar = { resolucion, lblResolucion, etiquetado, lblEtiquetado, rdbtnSi, rdbtnNo,
				lblMejoresAmigos, tipoHistoria, lblTipoHistoria };

		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}

		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}

	}

	private void imagen() {
		JComponent[] mostrar = { resolucion, lblResolucion, etiquetado, lblEtiquetado, lblDescripcion, descripcion };
		JComponent[] ocultar = { duracion, lblDuracion, rdbtnSi, rdbtnNo, lblMejoresAmigos, tipoHistoria,
				lblTipoHistoria, segundos };

		for (int i = 0; i < mostrar.length; i++) {
			mostrar[i].setVisible(true);
		}

		for (int i = 0; i < ocultar.length; i++) {
			ocultar[i].setVisible(false);
		}

	}
}
