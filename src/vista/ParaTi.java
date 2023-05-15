package vista;

import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.Usuario;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import excepciones.VentanaMensaje;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import modelo.DAO;
import utilidades.Utilidades;

public class ParaTi extends javax.swing.JDialog {

    private VMain vMain;
    private DAO dao;
    private Usuario usu;
    private Publicacion publi;
    private Usuario usuPubli;
    private List<String> hanSalido = new ArrayList<>();
    private List<Publicacion> publicacionesDisponibles = new ArrayList<>();

    public ParaTi(VMain conector, boolean modal, DAO dao, Usuario usu) {
        super(conector, modal);
        this.dao = dao;
        this.usu = usu;
        this.vMain = conector;

        setTitle("Para Ti");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();

        setLocationRelativeTo(null);

        if (usu.isVerificado()) {
            rdbtnSiguiendo.setVisible(true);
            rdbtnTodas.setVisible(true);
        }

        try {
            publicacionesDisponibles = dao.listarPublicacionesParaTi(usu.getUsuario());
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }

        siguienteFoto();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrar();
            }

        });
    }

    private void siguienteFoto() {
        try {

            String id;
            if (rdbtnTodas.isSelected()) {
                id = generarPublicacionAleatoria();
            } else {
                id = generarPublicacionAleatoriaReducida();
            }

            if (!id.equalsIgnoreCase("")) {
                publi = dao.buscarPublicacionXId(id);
                usuPubli = dao.buscarUsuario(publi.getUsuario());

                if (publi instanceof Historia && ((Historia) publi).isMejores_amigos()) {
                    if (!dao.comprobarMejos(usu.getUsuario(), publi.getUsuario())) {
                        this.siguienteFoto();
                    }
                }

                lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuPubli.getIcono())));
                imagen.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));

                if (dao.comprobarLike(usu.getUsuario(), publi.getId_publicacion())) {
                    btnLike.setSelected(true);
                } else {
                    btnLike.setSelected(false);
                }

                if (dao.comprobarGuardado(usu.getUsuario(), publi.getId_publicacion())) {
                    btnGuardar.setSelected(true);
                } else {
                    btnGuardar.setSelected(false);
                }

                lblDescripcion.setVisible(true);
                lblVerificado.setVisible(false);
                getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 110, 170, 22));
                lblUsuario.setVisible(true);
                lblHistoria.setVisible(false);
                btnEtiquetado.setVisible(false);
                lblUsuario.setText(publi.getUsuario());
                lblMegusta.setText(publi.getNumLikes() + "");

                if (usuPubli.isVerificado()) {
                    lblVerificado.setVisible(true);
                    getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, 110, 170, 22));

                }

                if (publi instanceof Foto) {
                    lblDescripcion.setText(((Foto) publi).getDescripcion());

                    if (((Foto) publi).getEtiquetado() != null) {
                        btnEtiquetado.setVisible(true);

                    }

                } else if (publi instanceof Reel) {
                    lblDescripcion.setText(((Reel) publi).getDescripcion());

                } else {
                    lblHistoria.setVisible(true);
                    lblDescripcion.setVisible(false);

                    if (((Historia) publi).isMejores_amigos()) {
                        lblHistoria.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/esMejos.png")));

                    } else {
                        lblHistoria.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/pantalla/esHistoria.png")));
                    }
                }
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
            ex.printStackTrace();
        } catch (ErrSelect ex) {
            ex.mostrarError();
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ErrVariados er = new ErrVariados("Imagen");
            ex.printStackTrace();
            er.mostrarError();
        }

    }

    private String generarPublicacionAleatoria() {
        String publiActual = "";
        int numRandom;
        boolean salir;

        if (hanSalido.size() == publicacionesDisponibles.size()) {
            hanSalido.clear();
        }

        do {
            salir = true;
            numRandom = Utilidades.numeros_aleatorios(0, publicacionesDisponibles.size() - 1);

            for (String i : hanSalido) {
                if (i.equalsIgnoreCase(publicacionesDisponibles.get(numRandom).getId_publicacion())) {
                    salir = false;
                }
            }

        } while (!salir);

        publiActual = publicacionesDisponibles.get(numRandom).getId_publicacion();
        hanSalido.add(publiActual);

        return publiActual;

    }

    private String generarPublicacionAleatoriaReducida() {
        String publiActual = "";
        int numRandom;
        boolean salir;

        if (publicacionesDisponibles.size() == 0) {
            VentanaMensaje ve = new VentanaMensaje("Disculpe", "Las personas a las que sigues no han publicado nada");
            lblHistoria.setVisible(false);
            btnLike.setVisible(false);
            btnGuardar.setVisible(false);
            btnEtiquetado.setVisible(false);
            lblVerificado.setVisible(false);
            return "";
        }

        if (hanSalido.size() == publicacionesDisponibles.size()) {
            hanSalido.clear();
        }

        do {
            salir = true;
            numRandom = Utilidades.numeros_aleatorios(0, publicacionesDisponibles.size() - 1);

            for (String i : hanSalido) {
                if (i.equalsIgnoreCase(publicacionesDisponibles.get(numRandom).getId_publicacion())) {
                    salir = false;
                }
            }

        } while (!salir);

        publiActual = publicacionesDisponibles.get(numRandom).getId_publicacion();
        hanSalido.add(publiActual);

        return publiActual;
    }

    private void darLike() {
        try {
            if (btnLike.isSelected()) {
                lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) + 1 + "");
                dao.insertarLike(usu.getUsuario(), publi.getId_publicacion());

            } else {
                lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) - 1 + "");
                dao.quirarLike(usu.getUsuario(), publi.getId_publicacion());
            }

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        } catch (NullPointerException ex) {
            ErrVariados er = new ErrVariados("Imagen");
            er.mostrarError();
        }
    }

    private void guardar() {
        try {
            if (btnGuardar.isSelected()) {
                dao.guardarPublicación(usu.getUsuario(), publi.getId_publicacion());
            } else {
                dao.desguardarPublicacion(usu.getUsuario(), publi.getId_publicacion());
            }

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoParaTi = new javax.swing.ButtonGroup();
        btnGuardar = new javax.swing.JToggleButton();
        franjaArriba = new javax.swing.JPanel();
        btnMensaje = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();
        lblIcono = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblVerificado = new javax.swing.JLabel();
        btnLike = new javax.swing.JToggleButton();
        btnEtiquetado = new javax.swing.JButton();
        lblMegusta = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblHistoria = new javax.swing.JLabel();
        rdbtnTodas = new javax.swing.JRadioButton();
        rdbtnSiguiendo = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setModal(true);
        setName("paraTi"); // NOI18N
        setPreferredSize(new java.awt.Dimension(648, 864));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setBackground(getBackground());
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/guardadd.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFocusable(false);
        btnGuardar.setHideActionText(true);
        btnGuardar.setRequestFocusEnabled(false);
        btnGuardar.setRolloverEnabled(false);
        btnGuardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/guardd2.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 640, 40, 40));

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        btnMensaje.setBackground(franjaArriba.getBackground());
        btnMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/conversaciones.png"))); // NOI18N
        btnMensaje.setToolTipText("");
        btnMensaje.setAlignmentY(0.0F);
        btnMensaje.setAutoscrolls(true);
        btnMensaje.setBorder(null);
        btnMensaje.setContentAreaFilled(false);
        btnMensaje.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnMensaje.setFocusPainted(false);
        btnMensaje.setFocusable(false);
        btnMensaje.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMensaje.setRolloverEnabled(false);
        btnMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajeActionPerformed(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

        lblLogoLetras.setForeground(getBackground());
        lblLogoLetras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/letrasInstagram.png"))); // NOI18N
        lblLogoLetras.setPreferredSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout franjaArribaLayout = new javax.swing.GroupLayout(franjaArriba);
        franjaArriba.setLayout(franjaArribaLayout);
        franjaArribaLayout.setHorizontalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(btnMensaje)
                .addGap(84, 84, 84))
        );
        franjaArribaLayout.setVerticalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo)
                    .addComponent(btnMensaje)
                    .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));

        btnParaTi.setBackground(franjaArriba.getBackground());
        btnParaTi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/para ti.png"))); // NOI18N
        btnParaTi.setToolTipText("");
        btnParaTi.setAlignmentY(0.0F);
        btnParaTi.setAutoscrolls(true);
        btnParaTi.setBorder(null);
        btnParaTi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnParaTi.setFocusable(false);
        btnParaTi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParaTi.setRolloverEnabled(false);

        btnBuscar.setBackground(franjaArriba.getBackground());
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/buscar.png"))); // NOI18N
        btnBuscar.setToolTipText("");
        btnBuscar.setAlignmentY(0.0F);
        btnBuscar.setAutoscrolls(true);
        btnBuscar.setBorder(null);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setRolloverEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSubir.setBackground(franjaArriba.getBackground());
        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/subir.png"))); // NOI18N
        btnSubir.setToolTipText("");
        btnSubir.setAlignmentY(0.0F);
        btnSubir.setAutoscrolls(true);
        btnSubir.setBorder(null);
        btnSubir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubir.setFocusable(false);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubir.setRolloverEnabled(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnTienda.setBackground(franjaArriba.getBackground());
        btnTienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/tienda.png"))); // NOI18N
        btnTienda.setToolTipText("");
        btnTienda.setAlignmentY(0.0F);
        btnTienda.setAutoscrolls(true);
        btnTienda.setBorder(null);
        btnTienda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTienda.setFocusable(false);
        btnTienda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienda.setRolloverEnabled(false);
        btnTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiendaActionPerformed(evt);
            }
        });

        btnCuenta.setBackground(franjaArriba.getBackground());
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/cuenta.png"))); // NOI18N
        btnCuenta.setToolTipText("");
        btnCuenta.setAlignmentY(0.0F);
        btnCuenta.setAutoscrolls(true);
        btnCuenta.setBorder(null);
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCuenta.setFocusable(false);
        btnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addContainerGap(56, Short.MAX_VALUE))
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

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 725, 632, 100));

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imagen.setPreferredSize(new java.awt.Dimension(475, 475));
        imagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imagenClickada(evt);
            }
        });
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 161, -1, -1));

        lblIcono.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIcono.setPreferredSize(new java.awt.Dimension(64, 64));
        lblIcono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarPerfil(evt);
            }
        });
        getContentPane().add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 89, -1, -1));

        lblUsuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarPerfil(evt);
            }
        });
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 110, 170, 22));

        lblVerificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/verificado.png"))); // NOI18N
        getContentPane().add(lblVerificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 106, -1, -1));

        btnLike.setBackground(getBackground());
        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/btnLike(false).png"))); // NOI18N
        btnLike.setBorder(null);
        btnLike.setBorderPainted(false);
        btnLike.setContentAreaFilled(false);
        btnLike.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLike.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/btnLike(True).png"))); // NOI18N
        btnLike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLikeMouseClicked(evt);
            }
        });
        getContentPane().add(btnLike, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 640, -1, -1));
        btnLike.setBounds(137, 640, 46, 40);

        btnEtiquetado.setBackground(getBackground());
        btnEtiquetado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/etiquedado.png"))); // NOI18N
        btnEtiquetado.setBorder(null);
        btnEtiquetado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEtiquetado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarEtiquetado(evt);
            }
        });
        getContentPane().add(btnEtiquetado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 640, -1, -1));

        lblMegusta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblMegusta.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblMegusta, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 640, 209, 40));

        lblDescripcion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 687, 463, 27));

        lblHistoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/eshistoria.png"))); // NOI18N
        lblHistoria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(lblHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 71, 100, 100));
        lblHistoria.setBounds(101, 71, 100, 100);

        rdbtnTodas.setBackground(getBackground());
        tipoParaTi.add(rdbtnTodas);
        rdbtnTodas.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnTodas.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnTodas.setText("Todas");
        rdbtnTodas.setBorder(null);
        rdbtnTodas.setContentAreaFilled(false);
        rdbtnTodas.setFocusPainted(false);
        rdbtnTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnTodasActionPerformed(evt);
            }
        });
        getContentPane().add(rdbtnTodas, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, -1, -1));
        rdbtnTodas.setVisible(false);

        rdbtnSiguiendo.setBackground(getBackground());
        tipoParaTi.add(rdbtnSiguiendo);
        rdbtnSiguiendo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnSiguiendo.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnSiguiendo.setSelected(true);
        rdbtnSiguiendo.setText("Siguiendo");
        rdbtnSiguiendo.setBorder(null);
        rdbtnSiguiendo.setContentAreaFilled(false);
        rdbtnSiguiendo.setFocusPainted(false);
        rdbtnSiguiendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnTodasActionPerformed(evt);
                rdbtnSiguiendoActionPerformed(evt);
            }
        });
        getContentPane().add(rdbtnSiguiendo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));
        rdbtnSiguiendo.setVisible(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMensajeActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) throws NullPointerException{//GEN-FIRST:event_btnBuscarActionPerformed
        Buscar buscar = new Buscar(vMain, true, dao, usu, false);
        this.setVisible(false);
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        Subir subir = new Subir(vMain, true, dao, usu, null);
        this.setVisible(false);
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Tienda tienda = new Tienda(vMain, true, dao, usu);
        this.setVisible(false);
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) throws NullPointerException{//GEN-FIRST:event_btnCuentaActionPerformed
        Perfil perfil = new Perfil(vMain, true, dao, usu, usu);
        this.setVisible(false);
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void imagenClickada(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imagenClickada
        siguienteFoto();
    }//GEN-LAST:event_imagenClickada

    private void btnLikeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLikeMouseClicked
        darLike();
    }//GEN-LAST:event_btnLikeMouseClicked

    private void buscarPerfil(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarPerfil
        try {
            Usuario etiquetado = dao.buscarUsuario(publi.getUsuario());
            Perfil perfil = new Perfil(vMain, true, dao, usu, etiquetado);
            this.setVisible(false);
            perfil.setVisible(true);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_buscarPerfil

    private void buscarEtiquetado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarEtiquetado
        try {
            Usuario etiquetado = dao.buscarUsuario(((Foto) publi).getEtiquetado());
            Perfil perfil = new Perfil(vMain, true, dao, usu, etiquetado);
            this.setVisible(false);
            perfil.setVisible(true);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_buscarEtiquetado

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void rdbtnTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnTodasActionPerformed
        try {
            publicacionesDisponibles = dao.listarPublicaciones();
            hanSalido.clear();

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_rdbtnTodasActionPerformed

    private void rdbtnSiguiendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnSiguiendoActionPerformed
        try {
            publicacionesDisponibles = dao.listarPublicacionesParaTi(usu.getUsuario());
            hanSalido.clear();

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_rdbtnSiguiendoActionPerformed

    private void cerrar() {
        this.dispose();
        vMain.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEtiquetado;
    private javax.swing.JToggleButton btnGuardar;
    private javax.swing.JToggleButton btnLike;
    private javax.swing.JButton btnMensaje;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblHistoria;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JLabel lblMegusta;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVerificado;
    private javax.swing.JRadioButton rdbtnSiguiendo;
    private javax.swing.JRadioButton rdbtnTodas;
    private javax.swing.ButtonGroup tipoParaTi;
    // End of variables declaration//GEN-END:variables

}
