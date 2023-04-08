package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
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

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 158, 612, 13);
		contentPanel.add(separator);

		btnElegir = new JButton("Elegir foto");
		btnElegir.setFont(new Font("Serif", Font.PLAIN, 20));
		btnElegir.setBackground(SystemColor.controlHighlight);
		btnElegir.setBounds(215, 193, 210, 55);
		contentPanel.add(btnElegir);

		lblCancion = new JLabel("Cancion:");
		lblCancion.setForeground(Color.WHITE);
		lblCancion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblCancion.setBounds(103, 289, 71, 27);
		contentPanel.add(lblCancion);

		List<Cancion> canciones = dao.listarCanciones();
		cancion = new JComboBox<String>();
		cancion.setBounds(301, 289, 228, 34);
		contentPanel.add(cancion);

		for (int i = 0; i < canciones.size(); i++) {
			cancion.addItem(canciones.get(i).getTitulo());
		}

		lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setForeground(Color.WHITE);
		lblUbicacion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblUbicacion.setBounds(103, 358, 87, 27);
		contentPanel.add(lblUbicacion);

		ubicacion = new JTextField();
		ubicacion.setBounds(301, 358, 228, 34);
		contentPanel.add(ubicacion);
		ubicacion.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 428, 612, 13);
		contentPanel.add(separator_1);

		lblResolucion = new JLabel("Resolucion:");
		lblResolucion.setForeground(Color.WHITE);
		lblResolucion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblResolucion.setBounds(103, 456, 95, 27);
		contentPanel.add(lblResolucion);

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

		lblEtiquetado = new JLabel("Etiquetado:");
		lblEtiquetado.setForeground(Color.WHITE);
		lblEtiquetado.setFont(new Font("Serif", Font.PLAIN, 20));
		lblEtiquetado.setBounds(103, 525, 95, 27);
		contentPanel.add(lblEtiquetado);

		etiquetado = new JTextField();
		etiquetado.setBounds(301, 519, 228, 34);
		contentPanel.add(etiquetado);
		etiquetado.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblDescripcion.setBounds(103, 595, 97, 27);
		contentPanel.add(lblDescripcion);

		descripcion = new JTextArea();
		descripcion.setBounds(301, 583, 228, 106);
		contentPanel.add(descripcion);

		segundos = new JLabel("");
		segundos.setForeground(new Color(255, 255, 255));
		segundos.setFont(new Font("Serif", Font.PLAIN, 20));
		segundos.setBounds(508, 497, 61, 23);
		contentPanel.add(segundos);
		segundos.setVisible(false);

		lblDuracion = new JLabel("Duracion:");
		lblDuracion.setForeground(Color.WHITE);
		lblDuracion.setFont(new Font("Serif", Font.PLAIN, 20));
		lblDuracion.setBounds(275, 461, 95, 27);
		contentPanel.add(lblDuracion);
		lblDuracion.setVisible(false);

		duracion = new JSlider();
		duracion.setValue(0);
		duracion.setBackground(new Color(49, 51, 53));
		duracion.setForeground(new Color(49, 51, 53));
		duracion.setMaximum(120);
		duracion.setBounds(76, 524, 494, 41);
		contentPanel.add(duracion);
		duracion.setVisible(false);
		duracion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				segundos.setText(duracion.getValue() + "s");
			}
		});

		lblTipoHistoria = new JLabel("Tipo de Historia:");
		lblTipoHistoria.setForeground(Color.WHITE);
		lblTipoHistoria.setFont(new Font("Serif", Font.PLAIN, 20));
		lblTipoHistoria.setBounds(88, 599, 138, 27);
		contentPanel.add(lblTipoHistoria);
		lblTipoHistoria.setVisible(false);

		List<TipoHistoria> tipoHistorias = dao.listarTipoHistorias();

		tipoHistoria = new JComboBox<String>();
		tipoHistoria.setBounds(301, 595, 228, 34);
		contentPanel.add(tipoHistoria);
		tipoHistoria.setVisible(false);

		for (int i = 0; i < tipoHistorias.size(); i++) {
			tipoHistoria.addItem(tipoHistorias.get(i).getTipo());
		}

		lblMejoresAmigos = new JLabel("Subir a mejores amigos?");
		lblMejoresAmigos.setForeground(Color.WHITE);
		lblMejoresAmigos.setFont(new Font("Serif", Font.PLAIN, 20));
		lblMejoresAmigos.setBounds(69, 504, 195, 27);
		contentPanel.add(lblMejoresAmigos);
		lblMejoresAmigos.setVisible(false);

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

		btnSubir = new JButton("Subir");
		btnSubir.setBackground(SystemColor.controlHighlight);
		btnSubir.setForeground(new Color(0, 0, 0));
		btnSubir.setFont(new Font("Serif", Font.PLAIN, 20));
		btnSubir.setBounds(211, 719, 210, 53);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

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

		int minLikes = (int) (usu.getNumSeguidores() * 0.12);
		int maxLikes = (int) (usu.getNumSeguidores() * 0.9);
		int minCom = (int) (usu.getNumSeguidores() * 0.012);
		int maxCom = (int) (usu.getNumSeguidores() * 0.04);

		// Compruebo si los campos son validos
		if (comprobarCampos()) {

			if (rdbtnImagen.isSelected()) {

				// Genero el codigo de las fotos
				ultimoCodigo = dao.calcularId("f");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "F-" + String.format("%03d", numCod);

				// Creo una nueva foto y le doy los valores
				publi = new Foto();

				// Si los valores opcionales estan vacion no escribir "" porque da fallo en la
				// BDA, mejor que ponga null
				if (!descripcion.getText().equalsIgnoreCase("")) {
					((Foto) publi).setDescripcion(descripcion.getText());
				}

				if (!etiquetado.getText().equalsIgnoreCase("")) {
					((Foto) publi).setEtiquetado(etiquetado.getText());
				}

				((Foto) publi).setResolucion(resolucion.getSelectedItem().toString());

			} else if (rdbtnReel.isSelected()) {

				// Genero el codigo de los reels
				ultimoCodigo = dao.calcularId("r");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "R-" + String.format("%03d", numCod);

				// Creo un nuevo reel y le doy los datos
				publi = new Reel();

				// Si el valor opcional esta vacio mejor null que ""
				if (!descripcion.getText().equalsIgnoreCase("")) {
					((Foto) publi).setDescripcion(descripcion.getText());
				}
				((Reel) publi).setDuracion(duracion.getValue());

				int minRrep = (int) (usu.getNumSeguidores() * 0.5);
				int maxRrep = usu.getNumSeguidores() * 15;

				((Reel) publi).setReproducciones(Utilidades.numeros_aleatorios(minRrep, maxRrep));

			} else {

				// Genero el codigo de las historias
				ultimoCodigo = dao.calcularId("h");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				numCod++;
				codigo = "H-" + String.format("%03d", numCod);

				// Creo una nueva historia y le doy los datos
				publi = new Historia();

				if (rdbtnSi.isSelected()) {
					((Historia) publi).setMejores_amigos(true);
				} else {
					((Historia) publi).setMejores_amigos(false);
				}
				((Historia) publi).setCod_tipo(dao.tipoHistoria(tipoHistoria.getSelectedItem().toString()));

				// Digo la hora en la que supuestamente se deberia de borrar la historia
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
				JOptionPane.showMessageDialog(this, "Esta historia se eliminara el dia " + LocalDate.now().plusDays(1)
						+ " a las " + LocalDateTime.now().format(formato));
			}

			// Le doy los datos de la publicacion
			publi.setId_publicacion(codigo);
			publi.setImagen(fotoSeleccionada);
			publi.setNumLikes(Utilidades.numeros_aleatorios(minLikes, maxLikes));
			publi.setNumComentarios(Utilidades.numeros_aleatorios(minCom, maxCom));
			publi.setFecha_subida(LocalDate.now());
			publi.setUsuario(usu.getUsuario());
			if (!ubicacion.getText().equalsIgnoreCase("")) {
				publi.setUbicacion(ubicacion.getText());
			}
			if (cancion.getSelectedIndex() != -1) {
				publi.setId_cancion(dao.buscarCancionXTitulo(cancion.getSelectedItem().toString()).getId_cancion());
			}

			// Inserto todos los datos en la BDA
			dao.publicar(publi);
			JOptionPane.showMessageDialog(this, "Publicacion subida con exito", "", 3);
			this.dispose();
			paraTi.setVisible(true);
		}

	}

	private boolean comprobarCampos() {
		String mensaje = "";
		boolean correcto = true;

		// Miro si se ha elegido una foto
		if (fotoSeleccionada == null) {
			mensaje += "No has elegido ninguna imagen\n";
			btnElegir.setBackground(new Color(204, 51, 51));
			correcto = false;

		} else {
			btnElegir.setBackground(new Color(0, 153, 51));
		}

		// Comporbar que la ubicacion no se mas larga de lo que permite la BDA
		if (ubicacion.getText().length() > 50) {
			mensaje += "La ubicacion no puede tener mas de 50 caracteres\n";
			ubicacion.setBackground(new Color(204, 51, 51));
			correcto = false;

		} else {
			ubicacion.setBackground(new Color(0, 153, 51));
		}

		// Compruebo que la descripcion no es mas larga de lo que permite la BDA
		if (descripcion.getText().length() > 500) {
			mensaje += "La descripcion no puede tener mas de 500 caracteres\n";
			descripcion.setBackground(new Color(204, 51, 51));
			descripcion.setForeground(new Color(0, 0, 0));

		} else {
			descripcion.setBackground(new Color(0, 153, 51));
		}

		// Doy por validos los campos opcionales
		cancion.setBackground(new Color(0, 153, 51));
		etiquetado.setBackground(new Color(0, 153, 51));

		if (rdbtnImagen.isSelected()) {

			// Compruebo que se ha elegido una resolucion
			if (resolucion.getSelectedIndex() == -1) {
				mensaje += "No has elegido ninguna resolucion\n";
				resolucion.setBackground(new Color(204, 51, 51));
				correcto = false;

			} else {
				resolucion.setBackground(new Color(0, 153, 51));
			}

		} else if (rdbtnReel.isSelected()) {

			// Compruebo que la duracion no sea 0
			if (duracion.getValue() == 0) {
				mensaje += "No has elegido ninguna duracion\n";
				duracion.setBackground(new Color(204, 51, 51));
				correcto = false;

			} else {
				duracion.setBackground(new Color(0, 153, 51));
			}

		} else {

			// Compruebo que se ha elegido si es de mejores amigos o no
			if (!rdbtnSi.isSelected() && !rdbtnNo.isSelected()) {
				mensaje += "Por favor elige si quieres subirla a mejores amigos o si no\n";
				correcto = false;

			}

			// Compruebo que se ha elegido un tipo de historia
			if (tipoHistoria.getSelectedIndex() == -1) {
				mensaje += "Por favor elige el tipo de la historia\n";
				tipoHistoria.setBackground(new Color(204, 51, 51));
				correcto = false;

			} else {
				tipoHistoria.setBackground(new Color(0, 153, 51));
			}

		}

		if (correcto) {
			return true;
		} else {
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", 0);
			return false;
		}
	}

	private void elegirFoto() {
		fotoSeleccionada = Utilidades.exploradorArchivos(this);

		if (fotoSeleccionada != "") {
			btnElegir.setBackground(new Color(0, 153, 51));
			btnElegir.setForeground(new Color(0, 0, 0));

		}
	}

	// Vacio los campos y cambio los colores a los predeterminados
	@SuppressWarnings("rawtypes")
	private void limpiar() {
		JComboBox[] comboBox = { cancion, resolucion, tipoHistoria };
		JTextField[] texto = { ubicacion, etiquetado };

		for (int i = 0; i < comboBox.length; i++) {
			comboBox[i].setSelectedIndex(-1);
			comboBox[i].setBackground(new Color(240, 240, 240));
		}

		for (int i = 0; i < texto.length; i++) {
			texto[i].setText("");
			texto[i].setBackground(new Color(255, 255, 255));
		}

		fotoSeleccionada = null;
		btnElegir.setBackground(SystemColor.controlHighlight);
		descripcion.setText("");
		descripcion.setBackground(new Color(255, 255, 255));
		duracion.setValue(0);
		rdbtnSi.setSelected(false);
		rdbtnNo.setSelected(false);

	}

	// Vacio los campos y muestro u oculto los campos necesarios para las historias
	private void historia() {
		this.limpiar();
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

	// Vacio los campos y muestro u oculto los campos necesarios para los reels
	private void reel() {
		this.limpiar();
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

	// Vacio los campos y muestro u oculto los campos necesarios para las imagenes
	private void imagen() {
		this.limpiar();
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
