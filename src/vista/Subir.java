package vista;

import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.Usuario;
import excepciones.ErrAlter;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import excepciones.VentanaError;
import excepciones.VentanaMensaje;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modelo.DAO;
import utilidades.Utilidades;

public class Subir extends javax.swing.JDialog {

    private DAO dao;
    private Usuario usu;
    private Conector conector;

    private String imagen = null;

    private Subir_Foto ventanaFoto;
    private Subir_Reel ventanaReel;
    private Subir_Historia ventanaHistoria;
    private ParaTi paraTi;
    private Publicacion publiEditar;

    private boolean editar = false;

    public Subir(Conector conector1, ParaTi paraTi, boolean modal, DAO dao, Usuario usu, Publicacion publiEditar) {
        super(paraTi, modal);

        this.paraTi = paraTi;
        this.dao = dao;
        this.usu = usu;
        this.conector = conector1;
        this.publiEditar = publiEditar;

        initComponents();

        getContentPane().setBackground(new Color(49, 51, 53));
        setLocationRelativeTo(null);

        ventanaFoto = new Subir_Foto(this, true, dao, usu);
        ventanaReel = new Subir_Reel(this, true, dao, usu);
        ventanaHistoria = new Subir_Historia(this, true, dao, usu);

        panelSlide.init(ventanaFoto, ventanaReel, ventanaHistoria);
        panelSlide.setAnimate(4);

        if (publiEditar != null) {
            editar = true;
            mostrarDatos();
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrar();
            }

        });
    }

    public void elegirFoto() {
        imagen = Utilidades.seleccionarImagen(this);
        if (imagen != null) {
            ventanaFoto.btnFoto.setBackground(new Color(0, 153, 51));
        }
    }

    public void subirPublicacion() {
        Publicacion publi;
        String id;
        try {
            if (rdbtnFoto.isSelected()) {

                publi = new Foto();

                id = dao.calcularId("F");

                if (ventanaFoto.cbCancion.getSelectedIndex() != -1) {
                    publi.setId_cancion(dao.buscarCancionXTitulo(ventanaFoto.cbCancion.getSelectedItem().toString()).getId_cancion());
                }

                if (ventanaFoto.cbEtiquetado.getSelectedIndex() != -1) {
                    ((Foto) publi).setEtiquetado(ventanaFoto.cbEtiquetado.getSelectedItem().toString());
                }

                if (!ventanaFoto.txtUbicacion.getText().isBlank()) {
                    publi.setUbicacion(ventanaFoto.txtUbicacion.getText());
                }

                ((Foto) publi).setResolucion(ventanaFoto.cbResolucion.getSelectedItem().toString());
                ((Foto) publi).setDescripcion(ventanaFoto.txtDescripcion.getText());

            } else if (rdbtnReel.isSelected()) {
                publi = new Reel();

                id = dao.calcularId("R");

                if (ventanaReel.cbCancion.getSelectedIndex() != -1) {
                    publi.setId_cancion(dao.buscarCancionXTitulo(ventanaReel.cbCancion.getSelectedItem().toString()).getId_cancion());
                }

                if (!ventanaReel.txtUbicacion.getText().isBlank()) {
                    publi.setUbicacion(ventanaReel.txtUbicacion.getText());
                }

                ((Reel) publi).setDuracion(ventanaReel.sliderDuracion.getValue());
                ((Reel) publi).setDescripcion(ventanaReel.txtDescripcion.getText());

            } else {
                publi = new Historia();

                id = dao.calcularId("H");

                if (ventanaHistoria.cbCancion.getSelectedIndex() != -1) {
                    publi.setId_cancion(dao.buscarCancionXTitulo(ventanaHistoria.cbCancion.getSelectedItem().toString()).getId_cancion());
                }

                if (ventanaHistoria.cbTipoHistoria.getSelectedIndex() != -1) {
                    ((Historia) publi).setCod_tipo(dao.buscarCodTipoHistoria(ventanaHistoria.cbTipoHistoria.getSelectedItem().toString()));
                }

                if (ventanaHistoria.rdbtnSi.isSelected()) {
                    ((Historia) publi).setMejores_amigos(true);
                } else {
                    ((Historia) publi).setMejores_amigos(false);
                }

                if (!ventanaHistoria.txtUbicacion.getText().isBlank()) {
                    publi.setUbicacion(ventanaHistoria.txtUbicacion.getText());
                }

            }

            publi.setId_publicacion(id);
            publi.setImagen(imagen);
            publi.setUsuario(usu.getUsuario());
            publi.setFecha_subida(LocalDate.now());
            publi.setNumLikes(Utilidades.numeros_aleatorios((int) (usu.getNumSeguidores() * 0.12), (int) (usu.getNumSeguidores() * 0.9)));
            publi.setNumComentarios(Utilidades.numeros_aleatorios((int) (usu.getNumSeguidores() * 0.012), (int) (usu.getNumSeguidores() * 0.04)));

            if (!editar) {
                dao.publicar(publi);
                VentanaMensaje ve = new VentanaMensaje("Publicado correctamente", "La imagen se ha guardado exitosamente en la base de datos");
            } else {
                publi.setId_publicacion(publiEditar.getId_publicacion());
                dao.editarPublicacion(publi);
                VentanaMensaje ve = new VentanaMensaje("Modificada correctamente", "Los datos de la imagen se han guardado correctamente en la base de datos");
            }
            limpiar();
            //this.dispose();
            // paraTi.setVisible(true);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrAlter ex) {
            ex.mostrarError();
        }
    }

    public void comprobarDatos() {
        String mensaje = "";
        boolean correcto = true;

        if (rdbtnFoto.isSelected()) {
            ventanaFoto.cbCancion.setBackground(new Color(0, 153, 51));
            ventanaFoto.txtUbicacion.setBackground(new Color(0, 153, 51));
            ventanaFoto.cbEtiquetado.setBackground(new Color(0, 153, 51));
            ventanaFoto.txtDescripcion.setBackground(new Color(0, 153, 51));

            if (imagen == null) {
                ventanaFoto.btnFoto.setBackground(new Color(204, 51, 51));
                mensaje += "Selecciona una imagen\n";

            } else {
                ventanaFoto.btnFoto.setBackground(new Color(0, 153, 51));

            }

            if (ventanaFoto.cbResolucion.getSelectedIndex() == -1) {
                ventanaFoto.cbResolucion.setBackground(new Color(204, 51, 51));
                mensaje += "Escoje una resolucion\n";

            } else {
                ventanaFoto.cbResolucion.setBackground(new Color(0, 153, 51));
            }

            if (ventanaFoto.txtUbicacion.getText().isBlank()) {
                ventanaFoto.txtUbicacion.setText(null);
            }

            if (ventanaFoto.txtDescripcion.getText().isBlank()) {
                ventanaFoto.txtDescripcion.setText(null);
            }

        }

        if (rdbtnReel.isSelected()) {
            ventanaReel.cbCancion.setBackground(new Color(0, 153, 51));
            ventanaReel.txtUbicacion.setBackground(new Color(0, 153, 51));
            ventanaReel.txtDescripcion.setBackground(new Color(0, 153, 51));

            if (imagen == null) {
                ventanaReel.btnFoto.setBackground(new Color(204, 51, 51));
                mensaje += "Selecciona una imagen\n";

            } else {
                ventanaReel.btnFoto.setBackground(new Color(0, 153, 51));
            }

            if (ventanaReel.cbCancion.getSelectedIndex() == -1) {
                ventanaReel.cbCancion.setSelectedItem("");
            }

            if (ventanaReel.sliderDuracion.getValue() == 1) {
                ventanaReel.sliderDuracion.setBackground(new Color(204, 51, 51));
                mensaje += "Escoje una duracion\n";
            }

            if (ventanaReel.txtUbicacion.getText().isBlank()) {
                ventanaReel.txtUbicacion.setText(null);
            }

            if (ventanaReel.txtDescripcion.getText().isBlank()) {
                ventanaReel.txtDescripcion.setText(null);
            }
        }

        if (rdbtnHistoria.isSelected()) {
            ventanaHistoria.cbCancion.setBackground(new Color(0, 153, 51));
            ventanaHistoria.txtUbicacion.setBackground(new Color(0, 153, 51));

            if (imagen == null) {
                ventanaHistoria.btnFoto.setBackground(new Color(204, 51, 51));
                mensaje += "Selecciona una imagen\n";

            } else {
                ventanaHistoria.btnFoto.setBackground(new Color(0, 153, 51));
            }

            if (ventanaHistoria.cbCancion.getSelectedIndex() == -1) {
                ventanaHistoria.cbCancion.setSelectedItem("");
            }

            if (ventanaHistoria.txtUbicacion.getText().isBlank()) {
                ventanaHistoria.txtUbicacion.setText(null);
            }

            if (!ventanaHistoria.rdbtnSi.isSelected() && !ventanaHistoria.rdbtnNo.isSelected()) {
                ventanaHistoria.rdbtnSi.setBackground(new Color(204, 51, 51));
                ventanaHistoria.rdbtnNo.setBackground(new Color(204, 51, 51));
                mensaje += "Escoje si quieres subir la foto a mejores amigos\n";
            } else {
                if (ventanaHistoria.rdbtnSi.isSelected()) {
                    ventanaHistoria.rdbtnSi.setBackground(new Color(204, 51, 51));
                } else {
                    ventanaHistoria.rdbtnNo.setBackground(new Color(204, 51, 51));

                }
            }

            if (ventanaHistoria.cbTipoHistoria.getSelectedIndex() == -1) {
                ventanaHistoria.cbTipoHistoria.setBackground(new Color(204, 51, 51));
                mensaje += "Escoje un tipo de historia\n";
            } else {
                ventanaHistoria.cbTipoHistoria.setBackground(new Color(204, 51, 51));
            }
        }
        if (!mensaje.equalsIgnoreCase("")) {
            VentanaError ve = new VentanaError(mensaje);

        } else {
            subirPublicacion();
        }
    }

    public void limpiar() {
        imagen = null;

        JTextField[] texto = {ventanaFoto.txtUbicacion, ventanaReel.txtUbicacion, ventanaHistoria.txtUbicacion};

        for (int i = 0; i < texto.length; i++) {
            texto[i].setText("");
            texto[i].setBackground(new Color(255, 255, 255));
        }

        JButton[] botones = {ventanaFoto.btnFoto, ventanaFoto.btnSubir, ventanaReel.btnFoto, ventanaReel.btnSubir, ventanaHistoria.btnFoto, ventanaHistoria.btnSubir};

        for (int i = 0; i < botones.length; i++) {
            botones[i].setBackground(new Color(227, 227, 227));

        }

        JComboBox[] comboBox = {ventanaFoto.cbCancion, ventanaFoto.cbResolucion, ventanaFoto.cbEtiquetado, ventanaReel.cbCancion, ventanaHistoria.cbCancion, ventanaHistoria.cbTipoHistoria};

        for (int i = 0; i < comboBox.length; i++) {
            comboBox[i].setSelectedIndex(-1);
            comboBox[i].setBackground(new Color(255, 255, 255));
        }

        JTextArea[] textArea = {ventanaFoto.txtDescripcion, ventanaReel.txtDescripcion,};

        for (int i = 0; i < textArea.length; i++) {
            textArea[i].setText("");
            textArea[i].setBackground(new Color(255, 255, 255));

        }

        JRadioButton[] rdbtn = {ventanaHistoria.rdbtnSi, ventanaHistoria.rdbtnNo};

        for (int i = 0; i < textArea.length; i++) {
            rdbtn[i].setSelected(false);
            rdbtn[i].setBackground(new Color(255, 255, 255));

        }
    }

    private void mostrarDatos() {
        imagen = publiEditar.getImagen();

        try {
            if (publiEditar instanceof Foto) {
                panelSlide.show(0);

                if (publiEditar.getId_cancion() != null) {
                    ventanaFoto.cbCancion.setSelectedItem(dao.buscarCancionXId(publiEditar.getId_cancion()).getTitulo());
                }

                ventanaFoto.txtUbicacion.setText(publiEditar.getUbicacion());
                ventanaFoto.cbResolucion.setSelectedItem(((Foto) publiEditar).getResolucion());
                ventanaFoto.cbEtiquetado.setSelectedItem(((Foto) publiEditar).getEtiquetado());
                ventanaFoto.txtDescripcion.setText(((Foto) publiEditar).getDescripcion());

            } else if (publiEditar instanceof Reel) {
                panelSlide.show(1);
                rdbtnReel.setSelected(true);

                if (publiEditar.getId_cancion() != null) {
                    ventanaReel.cbCancion.setSelectedItem(dao.buscarCancionXId(publiEditar.getId_cancion()).getTitulo());
                }

                ventanaReel.txtUbicacion.setText(publiEditar.getUbicacion());
                ventanaReel.sliderDuracion.setValue(((Reel) publiEditar).getDuracion());
                ventanaReel.txtDescripcion.setText(((Reel) publiEditar).getDescripcion());

            } else {
                panelSlide.show(2);
                rdbtnHistoria.setSelected(true);

                if (publiEditar.getId_cancion() != null) {
                    ventanaHistoria.cbCancion.setSelectedItem(dao.buscarCancionXId(publiEditar.getId_cancion()).getTitulo());
                }

                ventanaHistoria.txtUbicacion.setText(publiEditar.getUbicacion());
                ventanaHistoria.cbTipoHistoria.setSelectedItem(dao.buscarTipoHistoria(((Historia) publiEditar).getCod_tipo()));

                if (((Historia) publiEditar).isMejores_amigos()) {
                    ventanaHistoria.rdbtnSi.setSelected(true);
                } else {
                    ventanaHistoria.rdbtnNo.setSelected(true);
                }
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPublicacion = new javax.swing.ButtonGroup();
        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        panelSlide = new vista.PanelSlide();
        lblText = new javax.swing.JLabel();
        rdbtnFoto = new javax.swing.JRadioButton();
        rdbtnReel = new javax.swing.JRadioButton();
        rdbtnHistoria = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setPreferredSize(new java.awt.Dimension(648, 864));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

        lblLogoLetras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/letrasInstagram.png"))); // NOI18N
        lblLogoLetras.setPreferredSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout franjaArribaLayout = new javax.swing.GroupLayout(franjaArriba);
        franjaArriba.setLayout(franjaArribaLayout);
        franjaArribaLayout.setHorizontalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
        );
        franjaArribaLayout.setVerticalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblLogo)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 636, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));

        btnParaTi.setBackground(franajAbajo.getBackground());
        btnParaTi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/para ti.png"))); // NOI18N
        btnParaTi.setToolTipText("");
        btnParaTi.setAlignmentY(0.0F);
        btnParaTi.setBorder(null);
        btnParaTi.setBorderPainted(false);
        btnParaTi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnParaTi.setFocusable(false);
        btnParaTi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParaTi.setRequestFocusEnabled(false);
        btnParaTi.setRolloverEnabled(false);
        btnParaTi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParaTiActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(franajAbajo.getBackground());
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/buscar.png"))); // NOI18N
        btnBuscar.setToolTipText("");
        btnBuscar.setAlignmentY(0.0F);
        btnBuscar.setBorder(null);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setRequestFocusEnabled(false);
        btnBuscar.setRolloverEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSubir.setBackground(franajAbajo.getBackground());
        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/subir.png"))); // NOI18N
        btnSubir.setToolTipText("");
        btnSubir.setAlignmentY(0.0F);
        btnSubir.setBorder(null);
        btnSubir.setBorderPainted(false);
        btnSubir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubir.setFocusable(false);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubir.setRequestFocusEnabled(false);
        btnSubir.setRolloverEnabled(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnTienda.setBackground(franajAbajo.getBackground());
        btnTienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/tienda.png"))); // NOI18N
        btnTienda.setToolTipText("");
        btnTienda.setAlignmentY(0.0F);
        btnTienda.setBorder(null);
        btnTienda.setBorderPainted(false);
        btnTienda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTienda.setFocusable(false);
        btnTienda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienda.setRequestFocusEnabled(false);
        btnTienda.setRolloverEnabled(false);
        btnTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiendaActionPerformed(evt);
            }
        });

        btnCuenta.setBackground(franajAbajo.getBackground());
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/cuenta.png"))); // NOI18N
        btnCuenta.setToolTipText("");
        btnCuenta.setAlignmentY(0.0F);
        btnCuenta.setBorder(null);
        btnCuenta.setBorderPainted(false);
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCuenta.setFocusable(false);
        btnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCuenta.setRequestFocusEnabled(false);
        btnCuenta.setRolloverEnabled(false);
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout franajAbajoLayout = new javax.swing.GroupLayout(franajAbajo);
        franajAbajo.setLayout(franajAbajoLayout);
        franajAbajoLayout.setHorizontalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnParaTi)
                .addGap(66, 66, 66)
                .addComponent(btnBuscar)
                .addGap(64, 64, 64)
                .addComponent(btnSubir)
                .addGap(64, 64, 64)
                .addComponent(btnTienda)
                .addGap(66, 66, 66)
                .addComponent(btnCuenta)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        franajAbajoLayout.setVerticalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCuenta)
                    .addComponent(btnBuscar)
                    .addComponent(btnParaTi)
                    .addComponent(btnSubir)
                    .addComponent(btnTienda))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 725, -1, -1));

        panelSlide.setPreferredSize(new java.awt.Dimension(660, 527));

        javax.swing.GroupLayout panelSlideLayout = new javax.swing.GroupLayout(panelSlide);
        panelSlide.setLayout(panelSlideLayout);
        panelSlideLayout.setHorizontalGroup(
            panelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelSlideLayout.setVerticalGroup(
            panelSlideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        getContentPane().add(panelSlide, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 640, -1));

        lblText.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblText.setForeground(new java.awt.Color(255, 255, 255));
        lblText.setText("Que vas a subir?");
        lblText.setToolTipText("");
        getContentPane().add(lblText, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        rdbtnFoto.setBackground(getBackground());
        tipoPublicacion.add(rdbtnFoto);
        rdbtnFoto.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdbtnFoto.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnFoto.setSelected(true);
        rdbtnFoto.setText("Foto");
        rdbtnFoto.setBorder(null);
        rdbtnFoto.setFocusPainted(false);
        rdbtnFoto.setFocusable(false);
        rdbtnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarFoto(evt);
            }
        });
        getContentPane().add(rdbtnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        rdbtnReel.setBackground(getBackground());
        tipoPublicacion.add(rdbtnReel);
        rdbtnReel.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdbtnReel.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnReel.setText("Reel");
        rdbtnReel.setBorder(null);
        rdbtnReel.setFocusPainted(false);
        rdbtnReel.setFocusable(false);
        rdbtnReel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarReel(evt);
            }
        });
        getContentPane().add(rdbtnReel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        rdbtnHistoria.setBackground(getBackground());
        tipoPublicacion.add(rdbtnHistoria);
        rdbtnHistoria.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        rdbtnHistoria.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnHistoria.setText("Historia");
        rdbtnHistoria.setBorder(null);
        rdbtnHistoria.setFocusPainted(false);
        rdbtnHistoria.setFocusable(false);
        rdbtnHistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarHistoria(evt);
            }
        });
        getContentPane().add(rdbtnHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnParaTiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParaTiActionPerformed
        this.dispose();
        paraTi.setVisible(true);
    }//GEN-LAST:event_btnParaTiActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar buscar = new Buscar(conector, paraTi, true, dao, usu, false);
        this.dispose();
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
        Subir subir = new Subir(conector, paraTi, true, dao, usu, null);
        this.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Tienda tienda = new Tienda(conector, paraTi, true, dao, usu);
        this.dispose();
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        Perfil perfil = new Perfil(conector, paraTi, true, dao, usu, usu);
        this.dispose();
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void cambiarFoto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarFoto
        panelSlide.show(0);
        limpiar();
    }//GEN-LAST:event_cambiarFoto

    private void cambiarReel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarReel
        panelSlide.show(1);
        limpiar();
    }//GEN-LAST:event_cambiarReel

    private void cambiarHistoria(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarHistoria
        panelSlide.show(2);
        limpiar();
    }//GEN-LAST:event_cambiarHistoria

    private void cerrar() {
        this.dispose();
        conector.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JLabel lblText;
    private vista.PanelSlide panelSlide;
    private javax.swing.JRadioButton rdbtnFoto;
    private javax.swing.JRadioButton rdbtnHistoria;
    private javax.swing.JRadioButton rdbtnReel;
    private javax.swing.ButtonGroup tipoPublicacion;
    // End of variables declaration//GEN-END:variables

}
