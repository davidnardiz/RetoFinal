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
import javax.swing.ImageIcon;
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

	private JButton btnParaTi;
	private JButton btnBuscar;
	private JButton btnSubir;
	private JButton btnTienda;
	private JButton btnCuenta;

	private JRadioButton rdbtnImagen;
	private JRadioButton rdbtnReel;
	private JRadioButton rdbtnHistoria;
	private JTextField etiquetado;
	private JComboBox<String> cancion;
	private JComboBox<String> resolucion;
	private JComboBox<String> tipoHistoria;
	private JSlider duracion;
	private JButton btnSubirFoto;
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

		btnBuscar = new JButton("");
		btnBuscar.setBackground(new Color(43, 45, 47));
		btnBuscar.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/buscar.png")));
		btnBuscar.setBounds(176, 750, 50, 50);
		btnBuscar.setBorder(null);
		contentPanel.add(btnBuscar);
		btnBuscar.addActionListener(this);

		btnParaTi = new JButton("");
		btnParaTi.setBackground(new Color(43, 45, 47));
		btnParaTi.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/para ti.png")));
		btnParaTi.setBounds(63, 750, 50, 50);
		btnParaTi.setBorder(null);
		contentPanel.add(btnParaTi);
		btnParaTi.addActionListener(this);

		btnCuenta = new JButton("");
		btnCuenta.setBackground(new Color(43, 45, 47));
		btnCuenta.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/cuenta.png")));
		btnCuenta.setBounds(515, 750, 50, 50);
		btnCuenta.setBorder(null);
		contentPanel.add(btnCuenta);
		btnCuenta.addActionListener(this);

		btnTienda = new JButton("");
		btnTienda.setBackground(new Color(43, 45, 47));
		btnTienda.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/tienda.png")));
		btnTienda.setBounds(402, 750, 50, 50);
		btnTienda.setBorder(null);
		contentPanel.add(btnTienda);
		btnTienda.addActionListener(this);

		btnSubir = new JButton("");
		btnSubir.setBackground(new Color(43, 45, 47));
		btnSubir.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/subir.png")));
		btnSubir.setBounds(289, 750, 50, 50);
		btnSubir.setBorder(null);
		contentPanel.add(btnSubir);
		btnSubir.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Que vas a subir?");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 26));
		lblNewLabel.setBounds(215, 84, 202, 41);
		contentPanel.add(lblNewLabel);

		JLabel lblLetrasInstagram = new JLabel("");
		lblLetrasInstagram.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/letrasInstagram.png")));
		lblLetrasInstagram.setBounds(98, 12, 282, 70);
		contentPanel.add(lblLetrasInstagram);

		JLabel lblLogoInstagram = new JLabel("");
		lblLogoInstagram.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/logoPequeño.png")));
		lblLogoInstagram.setBounds(27, 21, 50, 50);
		contentPanel.add(lblLogoInstagram);

		JPanel franjaArriba = new JPanel();
		franjaArriba.setBackground(new Color(43, 45, 47));
		franjaArriba.setBounds(0, 0, 632, 83);
		contentPanel.add(franjaArriba);

		JPanel franjaAbajo = new JPanel();
		franjaAbajo.setBackground(new Color(43, 45, 47));
		franjaAbajo.setBounds(0, 725, 632, 100);
		contentPanel.add(franjaAbajo);
		

		rdbtnImagen = new JRadioButton("Imagen");
		rdbtnImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnImagen.setForeground(Color.WHITE);
		rdbtnImagen.setBackground(new Color(49, 51, 53));
		rdbtnImagen.setBounds(110, 135, 71, 25);
		contentPanel.add(rdbtnImagen);
		rdbtnImagen.addActionListener(this);
		rdbtnImagen.setSelected(true);

		rdbtnReel = new JRadioButton("Reel");
		rdbtnReel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnReel.setForeground(Color.WHITE);
		rdbtnReel.setBackground(new Color(49, 51, 53));
		rdbtnReel.setBounds(291, 135, 51, 25);
		contentPanel.add(rdbtnReel);
		rdbtnReel.addActionListener(this);

		rdbtnHistoria = new JRadioButton("Historia");
		rdbtnHistoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnHistoria.setForeground(Color.WHITE);
		rdbtnHistoria.setBackground(new Color(49, 51, 53));
		rdbtnHistoria.setBounds(452, 135, 69, 25);
		contentPanel.add(rdbtnHistoria);
		rdbtnHistoria.addActionListener(this);

		tipoPublicacion.add(rdbtnImagen);
		tipoPublicacion.add(rdbtnReel);
		tipoPublicacion.add(rdbtnHistoria);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 167, 612, 13);
		contentPanel.add(separator);

		btnElegir = new JButton("Elegir foto");
		btnElegir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnElegir.setBackground(SystemColor.controlHighlight);
		btnElegir.setBounds(263, 318, 106, 41);
		contentPanel.add(btnElegir);

		lblCancion = new JLabel("Cancion:");
		lblCancion.setForeground(Color.WHITE);
		lblCancion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCancion.setBounds(92, 200, 54, 17);
		contentPanel.add(lblCancion);

		List<Cancion> canciones = dao.listarCanciones();
		cancion = new JComboBox<String>();
		cancion.setBounds(238, 191, 300, 34);
		contentPanel.add(cancion);

		for (int i = 0; i < canciones.size(); i++) {
			cancion.addItem(canciones.get(i).getTitulo());
		}

		lblUbicacion = new JLabel("Ubicacion:");
		lblUbicacion.setForeground(Color.WHITE);
		lblUbicacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUbicacion.setBounds(92, 269, 63, 17);
		contentPanel.add(lblUbicacion);

		ubicacion = new JTextField();
		ubicacion.setBounds(238, 260, 300, 34);
		contentPanel.add(ubicacion);
		ubicacion.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 378, 612, 13);
		contentPanel.add(separator_1);

		lblResolucion = new JLabel("Resolucion:");
		lblResolucion.setForeground(Color.WHITE);
		lblResolucion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResolucion.setBounds(92, 402, 70, 17);
		contentPanel.add(lblResolucion);

		resolucion = new JComboBox<String>();
		resolucion.setBounds(238, 393, 300, 34);
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
		lblEtiquetado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEtiquetado.setBounds(92, 473, 71, 17);
		contentPanel.add(lblEtiquetado);

		etiquetado = new JTextField();
		etiquetado.setBounds(238, 464, 300, 34);
		contentPanel.add(etiquetado);
		etiquetado.setColumns(10);

		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(92, 547, 70, 17);
		contentPanel.add(lblDescripcion);

		descripcion = new JTextArea();
		descripcion.setBounds(238, 532, 300, 106);
		contentPanel.add(descripcion);

		segundos = new JLabel("");
		segundos.setForeground(new Color(255, 255, 255));
		segundos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		segundos.setBounds(511, 438, 0, 0);
		contentPanel.add(segundos);
		segundos.setVisible(false);

		lblDuracion = new JLabel("Duracion:");
		lblDuracion.setForeground(Color.WHITE);
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuracion.setBounds(286, 402, 60, 17);
		contentPanel.add(lblDuracion);
		lblDuracion.setVisible(false);

		duracion = new JSlider();
		duracion.setValue(0);
		duracion.setBackground(new Color(49, 51, 53));
		duracion.setForeground(new Color(49, 51, 53));
		duracion.setMaximum(120);
		duracion.setBounds(79, 465, 494, 41);
		contentPanel.add(duracion);
		duracion.setVisible(false);
		duracion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				segundos.setText(duracion.getValue() + "s");
			}
		});

		lblTipoHistoria = new JLabel("Tipo de Historia:");
		lblTipoHistoria.setForeground(Color.WHITE);
		lblTipoHistoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoHistoria.setBounds(92, 547, 98, 17);
		contentPanel.add(lblTipoHistoria);
		lblTipoHistoria.setVisible(false);

		List<TipoHistoria> tipoHistorias = dao.listarTipoHistorias();

		tipoHistoria = new JComboBox<String>();
		tipoHistoria.setBounds(286, 538, 228, 34);
		contentPanel.add(tipoHistoria);
		tipoHistoria.setVisible(false);

		for (int i = 0; i < tipoHistorias.size(); i++) {
			tipoHistoria.addItem(tipoHistorias.get(i).getTipo());
		}

		lblMejoresAmigos = new JLabel("Subir a mejores amigos?");
		lblMejoresAmigos.setForeground(Color.WHITE);
		lblMejoresAmigos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMejoresAmigos.setBounds(92, 473, 149, 17);
		contentPanel.add(lblMejoresAmigos);
		lblMejoresAmigos.setVisible(false);

		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setForeground(new Color(255, 255, 255));
		rdbtnSi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSi.setBackground(new Color(49, 51, 53));
		rdbtnSi.setBounds(301, 469, 35, 25);
		contentPanel.add(rdbtnSi);
		rdbtnSi.setVisible(false);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setForeground(new Color(255, 255, 255));
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNo.setBackground(new Color(49, 51, 53));
		rdbtnNo.setBounds(412, 469, 43, 25);
		contentPanel.add(rdbtnNo);
		btnElegir.addActionListener(this);
		rdbtnNo.setVisible(false);

		mejos.add(rdbtnSi);
		mejos.add(rdbtnNo);

		btnSubirFoto = new JButton("Subir");
		btnSubirFoto.setBackground(SystemColor.controlHighlight);
		btnSubirFoto.setForeground(new Color(0, 0, 0));
		btnSubirFoto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubirFoto.setBounds(263, 673, 106, 41);
		contentPanel.add(btnSubirFoto);
		btnSubirFoto.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnParaTi)) {
			abrirParaTi();
		} else if (e.getSource().equals(btnBuscar)) {
			abrirBuscar();
		} else if (e.getSource().equals(btnTienda)) {
			abrirTienda();
		} else if (e.getSource().equals(btnCuenta)) {
			abrirCuenta();
		} else if (e.getSource().equals(rdbtnImagen)) {
			imagen();
		} else if (e.getSource().equals(rdbtnReel)) {
			reel();
		} else if (e.getSource().equals(rdbtnHistoria)) {
			historia();
		} else if (e.getSource().equals(btnElegir)) {
			elegirFoto();
		} else if (e.getSource().equals(btnSubirFoto)) {
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
				codigo = "F-" + String.format("%03d", numCod + 1);

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
				codigo = "R-" + String.format("%03d", numCod + 1);

				// Creo un nuevo reel y le doy los datos
				publi = new Reel();

				// Si el valor opcional esta vacio mejor null que ""
				if (!descripcion.getText().equalsIgnoreCase("")) {
					((Reel) publi).setDescripcion(descripcion.getText());
				}
				((Reel) publi).setDuracion(duracion.getValue());

				int minRrep = (int) (usu.getNumSeguidores() * 0.5);
				int maxRrep = usu.getNumSeguidores() * 15;

				((Reel) publi).setReproducciones(Utilidades.numeros_aleatorios(minRrep, maxRrep));

			} else {

				// Genero el codigo de las historias
				ultimoCodigo = dao.calcularId("h");
				numCod = Integer.parseInt(ultimoCodigo.substring(2));
				codigo = "H-" + String.format("%03d", numCod + 1);

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

	private void abrirCuenta() {
		Perfil perf = new Perfil(paraTi, true, dao, usu, usu);
		this.dispose();
		perf.setVisible(true);
		
	}

	private void abrirTienda() {
		// TODO Auto-generated method stub

	}

	private void abrirBuscar() {
		Buscar buscar = new Buscar(paraTi, true, dao, usu, false);
		this.dispose();
		buscar.setVisible(true);

	}

	private void abrirParaTi() {
		this.dispose();
		paraTi.setVisible(true);

	}
}
