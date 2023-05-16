package vista;

import clases.Publicacion;
import clases.Usuario;
<<<<<<< HEAD
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
=======
import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.DAO;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Perfil extends javax.swing.JDialog {

    private DAO dao;
<<<<<<< HEAD
    private Usuario usuarioPerfil;
    private Usuario usu;
    private Publicacion publi;
    private VMain vMain;
    private List<Publicacion> publicacionesList;

    public Perfil(VMain vMain, boolean modal, DAO dao, Usuario nosotros, Usuario usuarioPerfil) {
        super(vMain, modal);
        try {
            this.dao = dao;
            this.usu = nosotros;
            this.usuarioPerfil = usuarioPerfil;
            this.vMain = vMain;

            setTitle("Perfil");
            setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
            getContentPane().setBackground(new Color(49, 51, 53));
            initComponents();
            setLocationRelativeTo(null);

            if (dao.comprobarBloqueado(nosotros.getUsuario(), usuarioPerfil.getUsuario())) {
                bloqueado.setVisible(true);
                lblBloqueado.setVisible(true);

                btnEditarPerfil.setVisible(false);
                btnMenu.setVisible(false);
                btnSeguir.setVisible(false);
                btnMensaje.setVisible(false);
                rdbtnFoto.setVisible(false);
                rdbtnReel.setVisible(false);
                rdbtnHistoria.setVisible(false);

                franjaMenu.setVisible(false);

            } else {
                mostrarPublicacion();

                if (!nosotros.getUsuario().equalsIgnoreCase(usuarioPerfil.getUsuario())) {
                    btnMenu.setVisible(false);
                    btnEditarPerfil.setVisible(false);

                    if (dao.verSeguimiento(usu.getUsuario(), usuarioPerfil.getUsuario())) {
                        btnSeguir.setSelected(true);
                        btnSeguir.setText("Siguiendo");
                    }
                }
                if (!usuarioPerfil.isVerificado()) {
                    lblVerificado.setVisible(false);
                }
                lblNumPublicaciones.setText(dao.numPublicacionesUsuario(usuarioPerfil.getUsuario()) + "");
                lblSeguidores.setText(usuarioPerfil.getNumSeguidores() + "");
                lblSeguidos.setText(usuarioPerfil.getNumSeguidos() + "");
                lblUsuario.setText(usuarioPerfil.getUsuario());

                lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuarioPerfil.getIcono())));
                publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), usu.getUsuario(), "Foto");

                cargarTabla(publicacionesList);

                franjaMenu.setVisible(false);
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        } catch (NullPointerException ex) {
            ErrVariados er = new ErrVariados("Imagen");
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrar();
            }

        });
=======
    private ParaTi paraTi;
    private Usuario usuarioPerfil;
    private Usuario usu;
    private List<Publicacion> publicacionesList;

    public Perfil(ParaTi parent, boolean modal, DAO dao, Usuario nosotros, Usuario usu) {
        super(parent, modal);
        this.setModal(modal);
        this.dao = dao;
        this.usu = nosotros;
        this.usuarioPerfil = usu;
        this.paraTi = parent;

        setTitle("Perfil");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();

        if (!nosotros.getUsuario().equalsIgnoreCase(usuarioPerfil.getUsuario())) {
            btn.setVisible(false);
            btnEditarPerfil.setVisible(false);
        }
        if (!usuarioPerfil.isVerificado()) {
            lblVerificado.setVisible(false);
        }

        lblNumPublicaciones.setText(dao.numPublicacionesUsuario(usuarioPerfil.getUsuario()) + "");
        lblSeguidores.setText(usuarioPerfil.getNumSeguidores() + "");
        lblSeguidos.setText(usuarioPerfil.getNumSeguidos() + "");
        lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuarioPerfil.getIcono())));
        lblUsuario.setText(usuarioPerfil.getUsuario());

        publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), "Foto");
        cargarTabla(publicacionesList);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
    }

    private void cargarTabla(List<Publicacion> publicacionesList) {
        DefaultTableModel modelo = (DefaultTableModel) tablaPublicaciones.getModel();
        modelo.setRowCount(0);

        TableColumnModel columnModel = tablaPublicaciones.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(2).setCellRenderer(new ImageRenderer());

        String rutaProyecto = System.getProperty("user.dir");
        for (int i = 0; i < publicacionesList.size(); i = i + 3) {
            Object[] fila = new Object[3];
            fila[0] = new ImageIcon(
                    rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i).getImagen());

            if (publicacionesList.size() > i + 1) {
                fila[1] = new ImageIcon(
                        rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 1).getImagen());
            } else {
                fila[1] = null;
            }

            if (publicacionesList.size() > i + 2) {
                fila[2] = new ImageIcon(
                        rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 2).getImagen());
            } else {
                fila[2] = null;
            }

            modelo.addRow(fila);

        }

    }

    private void abrirFoto(String foto) {
        String rutaProyecto = System.getProperty("user.dir");
<<<<<<< HEAD
        publi = null;
=======
        Publicacion publi = null;
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0

        for (Publicacion i : publicacionesList) {
            if (foto.equalsIgnoreCase(rutaProyecto + "\\src\\imagenes\\publicaciones\\" + i.getImagen())) {
                publi = i;
                break;
            }
        }

<<<<<<< HEAD
        PublicacionPopUp publiPop = new PublicacionPopUp(vMain, true, dao, publi, usu, usuarioPerfil, this);
=======
        PublicacionPopUp publiPop = new PublicacionPopUp(paraTi, true, dao, publi, usu, usuarioPerfil);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        publiPop.setVisible(true);

    }

<<<<<<< HEAD
    private void mostrarPublicacion() {
        try {

            if (dao.verSeguimiento(usu.getUsuario(), usuarioPerfil.getUsuario()) || usu.getUsuario().equalsIgnoreCase(usuarioPerfil.getUsuario())) {
                noSigue.setVisible(false);
                lblSigue.setVisible(false);

                rdbtnFoto.setVisible(true);
                rdbtnReel.setVisible(true);
                rdbtnHistoria.setVisible(true);
            } else {
                noSigue.setVisible(true);
                lblSigue.setVisible(true);

                rdbtnFoto.setVisible(false);
                rdbtnReel.setVisible(false);
                rdbtnHistoria.setVisible(false);
            }

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")

=======
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPublicacion = new javax.swing.ButtonGroup();
<<<<<<< HEAD
        bloqueado = new javax.swing.JPanel();
        lblBloqueado = new javax.swing.JLabel();
        noSigue = new javax.swing.JPanel();
        lblSigue = new javax.swing.JLabel();
        franjaMenu = new javax.swing.JPanel();
        equis = new javax.swing.JButton();
        btnBloquear = new javax.swing.JButton();
        btnPublisGuardadas = new javax.swing.JButton();
        btnEtiquetas = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnMejoresAmigos = new javax.swing.JButton();
=======
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        lblNumPublicaciones = new javax.swing.JLabel();
        lblSeguidores = new javax.swing.JLabel();
        lblSeguidos = new javax.swing.JLabel();
        lblPublicacionesText = new javax.swing.JLabel();
        lblSeguidoresText = new javax.swing.JLabel();
        lblSeguidosText = new javax.swing.JLabel();
        lblIcono = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblVerificado = new javax.swing.JLabel();
        btnEditarPerfil = new javax.swing.JButton();
<<<<<<< HEAD
        btnMenu = new javax.swing.JButton();
=======
        btn = new javax.swing.JButton();
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        btnMensaje = new javax.swing.JButton();
        btnSeguir = new javax.swing.JToggleButton();
        rdbtnFoto = new javax.swing.JRadioButton();
        rdbtnReel = new javax.swing.JRadioButton();
        rdbtnHistoria = new javax.swing.JRadioButton();
        scroll = new javax.swing.JScrollPane();
        tablaPublicaciones = new javax.swing.JTable();

        tipoPublicacion.add(rdbtnFoto);
        tipoPublicacion.add(rdbtnReel);
        tipoPublicacion.add(rdbtnHistoria);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setModal(true);
        setName("paraTi"); // NOI18N
        setPreferredSize(new java.awt.Dimension(648, 864));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

<<<<<<< HEAD
        bloqueado.setBackground(getBackground());

        lblBloqueado.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblBloqueado.setForeground(new java.awt.Color(255, 255, 255));
        lblBloqueado.setText("Este usuario te ha bloqueado");

        javax.swing.GroupLayout bloqueadoLayout = new javax.swing.GroupLayout(bloqueado);
        bloqueado.setLayout(bloqueadoLayout);
        bloqueadoLayout.setHorizontalGroup(
            bloqueadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bloqueadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBloqueado)
                .addGap(15, 15, 15))
        );
        bloqueadoLayout.setVerticalGroup(
            bloqueadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bloqueadoLayout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(lblBloqueado)
                .addContainerGap(293, Short.MAX_VALUE))
        );

        lblBloqueado.setVisible(false);

        getContentPane().add(bloqueado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 630, 640));
        bloqueado.setVisible(false);

        noSigue.setBackground(getBackground());

        lblSigue.setFont(new java.awt.Font("SansSerif", 0, 48)); // NOI18N
        lblSigue.setForeground(new java.awt.Color(255, 255, 255));
        lblSigue.setText("No sigues a este usuario");

        javax.swing.GroupLayout noSigueLayout = new javax.swing.GroupLayout(noSigue);
        noSigue.setLayout(noSigueLayout);
        noSigueLayout.setHorizontalGroup(
            noSigueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noSigueLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblSigue)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        noSigueLayout.setVerticalGroup(
            noSigueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noSigueLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(lblSigue)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        getContentPane().add(noSigue, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 610, 410));

        franjaMenu.setBackground(new java.awt.Color(35, 36, 37));
        franjaMenu.setPreferredSize(new java.awt.Dimension(648, 80));

        equis.setBackground(new java.awt.Color(35, 36, 37));
        equis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/equis.png"))); // NOI18N
        equis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equisMouseClicked(evt);
            }
        });

        btnBloquear.setBackground(new java.awt.Color(49, 51, 53));
        btnBloquear.setForeground(new java.awt.Color(255, 255, 255));
        btnBloquear.setText("Bloquear usuario");
        btnBloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloquearActionPerformed(evt);
            }
        });

        btnPublisGuardadas.setBackground(new java.awt.Color(49, 51, 53));
        btnPublisGuardadas.setForeground(new java.awt.Color(255, 255, 255));
        btnPublisGuardadas.setText("Publicaciones guardadas");
        btnPublisGuardadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublisGuardadasActionPerformed(evt);
            }
        });

        btnEtiquetas.setBackground(new java.awt.Color(49, 51, 53));
        btnEtiquetas.setForeground(new java.awt.Color(255, 255, 255));
        btnEtiquetas.setText("Etiquetas");
        btnEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetasActionPerformed(evt);
            }
        });

        btnCerrarSesion.setBackground(new java.awt.Color(133, 0, 0));
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnMejoresAmigos.setBackground(new java.awt.Color(14, 105, 0));
        btnMejoresAmigos.setForeground(new java.awt.Color(255, 255, 255));
        btnMejoresAmigos.setText("Mejores Amigos");
        btnMejoresAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMejoresAmigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout franjaMenuLayout = new javax.swing.GroupLayout(franjaMenu);
        franjaMenu.setLayout(franjaMenuLayout);
        franjaMenuLayout.setHorizontalGroup(
            franjaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaMenuLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(franjaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMejoresAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPublisGuardadas, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equis, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        franjaMenuLayout.setVerticalGroup(
            franjaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(equis, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152)
                .addComponent(btnBloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPublisGuardadas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEtiquetas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMejoresAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        getContentPane().add(franjaMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 330, 830));

=======
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

        lblLogoLetras.setForeground(getBackground());
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
                .addContainerGap(392, Short.MAX_VALUE))
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

<<<<<<< HEAD
=======
        lblLogoLetras.getAccessibleContext().setAccessibleName("");

>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 682, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));
        franajAbajo.setPreferredSize(new java.awt.Dimension(632, 100));

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
        btnParaTi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParaTiActionPerformed(evt);
            }
        });

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

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 725, -1, -1));

        lblNumPublicaciones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblNumPublicaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblNumPublicaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumPublicaciones.setText("0");
        lblNumPublicaciones.setPreferredSize(new java.awt.Dimension(94, 28));
        getContentPane().add(lblNumPublicaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 120, -1, -1));
        lblNumPublicaciones.setBounds(212, 120, 94, 28);

        lblSeguidores.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSeguidores.setForeground(new java.awt.Color(255, 255, 255));
        lblSeguidores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeguidores.setText("0");
        lblSeguidores.setPreferredSize(new java.awt.Dimension(78, 28));
        getContentPane().add(lblSeguidores, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 120, -1, -1));
        lblSeguidores.setBounds(352, 120, 78, 28);

        lblSeguidos.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        lblSeguidos.setForeground(new java.awt.Color(255, 255, 255));
        lblSeguidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeguidos.setText("0");
        lblSeguidos.setPreferredSize(new java.awt.Dimension(78, 28));
        getContentPane().add(lblSeguidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 120, -1, -1));

        lblPublicacionesText.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblPublicacionesText.setForeground(new java.awt.Color(255, 255, 255));
        lblPublicacionesText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPublicacionesText.setText("Publicaciones");
        lblPublicacionesText.setPreferredSize(new java.awt.Dimension(94, 20));
        getContentPane().add(lblPublicacionesText, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 156, -1, -1));

        lblSeguidoresText.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblSeguidoresText.setForeground(new java.awt.Color(255, 255, 255));
        lblSeguidoresText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeguidoresText.setText("Seguidores");
        lblSeguidoresText.setPreferredSize(new java.awt.Dimension(78, 20));
        getContentPane().add(lblSeguidoresText, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 156, -1, -1));

        lblSeguidosText.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblSeguidosText.setForeground(new java.awt.Color(255, 255, 255));
        lblSeguidosText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeguidosText.setText("Siguiendo");
        lblSeguidosText.setPreferredSize(new java.awt.Dimension(70, 20));
        getContentPane().add(lblSeguidosText, new org.netbeans.lib.awtextra.AbsoluteConstraints(488, 156, -1, -1));

        lblIcono.setPreferredSize(new java.awt.Dimension(64, 64));
        getContentPane().add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 120, -1, -1));

        lblUsuario.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setPreferredSize(new java.awt.Dimension(120, 20));
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 196, -1, -1));

        lblVerificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/verificado.png"))); // NOI18N
        getContentPane().add(lblVerificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        btnEditarPerfil.setBackground(new java.awt.Color(227, 227, 227));
        btnEditarPerfil.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
<<<<<<< HEAD
=======
        btnEditarPerfil.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        btnEditarPerfil.setText("Editar Cuenta");
        btnEditarPerfil.setBorder(null);
        btnEditarPerfil.setBorderPainted(false);
        btnEditarPerfil.setFocusPainted(false);
<<<<<<< HEAD
        btnEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 242, 120, 42));

        btnMenu.setBackground(new java.awt.Color(227, 227, 227));
        btnMenu.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnMenu.setText("Menú");
        btnMenu.setBorder(null);
        btnMenu.setBorderPainted(false);
        btnMenu.setFocusPainted(false);
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 242, 120, 42));

        btnMensaje.setBackground(new java.awt.Color(227, 227, 227));
        btnMensaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
=======
        getContentPane().add(btnEditarPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 242, 120, 42));

        btn.setBackground(new java.awt.Color(227, 227, 227));
        btn.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btn.setForeground(new java.awt.Color(0, 0, 0));
        btn.setText("???");
        btn.setBorder(null);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        getContentPane().add(btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 242, 120, 42));

        btnMensaje.setBackground(new java.awt.Color(227, 227, 227));
        btnMensaje.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnMensaje.setForeground(new java.awt.Color(0, 0, 0));
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        btnMensaje.setText("Enviar Mensaje");
        btnMensaje.setBorder(null);
        btnMensaje.setBorderPainted(false);
        btnMensaje.setFocusPainted(false);
        getContentPane().add(btnMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 242, 120, 42));

        btnSeguir.setBackground(new java.awt.Color(0, 149, 246));
        btnSeguir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSeguir.setForeground(new java.awt.Color(255, 255, 255));
        btnSeguir.setText("Seguir");
        btnSeguir.setBorder(null);
        btnSeguir.setBorderPainted(false);
        btnSeguir.setFocusPainted(false);
        btnSeguir.setRolloverEnabled(false);
<<<<<<< HEAD
        btnSeguir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seguir(evt);
            }
        });
=======
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        getContentPane().add(btnSeguir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 242, 120, 42));

        rdbtnFoto.setBackground(getBackground());
        tipoPublicacion.add(rdbtnFoto);
        rdbtnFoto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnFoto.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnFoto.setSelected(true);
        rdbtnFoto.setText("Foto");
        rdbtnFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdbtnFoto.setFocusPainted(false);
        rdbtnFoto.setPreferredSize(new java.awt.Dimension(109, 23));
        rdbtnFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdbtnFotoMouseClicked(evt);
            }
        });
        getContentPane().add(rdbtnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 314, -1, -1));

        rdbtnReel.setBackground(getBackground());
        tipoPublicacion.add(rdbtnReel);
        rdbtnReel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnReel.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnReel.setText("Reels");
        rdbtnReel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdbtnReel.setFocusPainted(false);
        rdbtnReel.setPreferredSize(new java.awt.Dimension(109, 23));
        rdbtnReel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdbtnReelMouseClicked(evt);
            }
        });
<<<<<<< HEAD
=======
        rdbtnReel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnReelActionPerformed(evt);
            }
        });
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        getContentPane().add(rdbtnReel, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 314, -1, -1));

        rdbtnHistoria.setBackground(getBackground());
        tipoPublicacion.add(rdbtnHistoria);
        rdbtnHistoria.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnHistoria.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnHistoria.setText("Historias");
        rdbtnHistoria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        rdbtnHistoria.setFocusPainted(false);
        rdbtnHistoria.setPreferredSize(new java.awt.Dimension(109, 23));
        rdbtnHistoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdbtnHistoriaMouseClicked(evt);
            }
        });
        getContentPane().add(rdbtnHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(433, 314, -1, -1));

        scroll.setBackground(getBackground());
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
<<<<<<< HEAD
        scroll.setFocusable(false);
        scroll.setPreferredSize(new java.awt.Dimension(594, 351));
        scroll.setRequestFocusEnabled(false);
=======
        scroll.setAutoscrolls(true);
        scroll.setPreferredSize(new java.awt.Dimension(594, 351));
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0

        tablaPublicaciones.setBackground(getBackground());
        tablaPublicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
<<<<<<< HEAD

=======
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
<<<<<<< HEAD
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPublicaciones.setFillsViewportHeight(true);
        tablaPublicaciones.setFocusable(false);
        tablaPublicaciones.setRequestFocusEnabled(false);
        tablaPublicaciones.setRowHeight(349);
        tablaPublicaciones.setRowSelectionAllowed(false);
=======
        ));
        tablaPublicaciones.setFillsViewportHeight(true);
        tablaPublicaciones.setPreferredSize(new java.awt.Dimension(594, 351));
        tablaPublicaciones.setRowHeight(351);
        tablaPublicaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        tablaPublicaciones.setShowGrid(false);
        tablaPublicaciones.setTableHeader(null);
        tablaPublicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPublicacionesMouseClicked(evt);
            }
        });
        scroll.setViewportView(tablaPublicaciones);

<<<<<<< HEAD
=======
        scroll.setBorder(BorderFactory.createEmptyBorder());

>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        getContentPane().add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 355, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnParaTiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParaTiActionPerformed
<<<<<<< HEAD
        ParaTi paraTi = new ParaTi(vMain, true, dao, usu);
=======
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        this.dispose();
        paraTi.setVisible(true);
    }//GEN-LAST:event_btnParaTiActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        Buscar buscar = new Buscar(vMain, true, dao, usu, false);
=======
        Buscar buscar = new Buscar(paraTi, true, dao, usu, false);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        this.dispose();
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
<<<<<<< HEAD
        Subir subir = new Subir(vMain, true, dao, usu, null);
=======
        Subir subir = new Subir(paraTi, true, dao, usu);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        this.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
<<<<<<< HEAD
        Tienda tienda = new Tienda(vMain, true, dao, usu);
=======
        Tienda tienda = new Tienda(paraTi, true, dao, usu);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        this.dispose();
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
<<<<<<< HEAD
        Perfil perfil = new Perfil(vMain, true, dao, usu, usu);
=======
        Perfil perfil = new Perfil(paraTi, true, dao, usu, usu);
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
        this.dispose();
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

<<<<<<< HEAD
    private void rdbtnFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnFotoMouseClicked
        try {
            publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), usu.getUsuario(), "Foto");
            cargarTabla(publicacionesList);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_rdbtnFotoMouseClicked

    private void rdbtnReelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnReelMouseClicked
        try {
            publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), usu.getUsuario(), "Reel");
            cargarTabla(publicacionesList);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_rdbtnReelMouseClicked

    private void rdbtnHistoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnHistoriaMouseClicked
        try {
            publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), usu.getUsuario(), "Historia");
            cargarTabla(publicacionesList);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_rdbtnHistoriaMouseClicked

    private void seguir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seguir
        try {
            if (btnSeguir.isSelected()) {
                dao.seguir(usu.getUsuario(), usuarioPerfil.getUsuario());
                btnSeguir.setText("Siguiendo");
                lblSeguidores.setText(Integer.parseInt(lblSeguidores.getText()) + 1 + "");

                publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), usu.getUsuario(), "Foto");
                cargarTabla(publicacionesList);

            } else {
                dao.dejarSeguir(usu.getUsuario(), usuarioPerfil.getUsuario());
                btnSeguir.setText("Seguir");
                lblSeguidores.setText(Integer.parseInt(lblSeguidores.getText()) - 1 + "");
            }

            mostrarPublicacion();

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_seguir

=======
    private void rdbtnReelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnReelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtnReelActionPerformed

    private void rdbtnFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnFotoMouseClicked
        publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), "Foto");
        cargarTabla(publicacionesList);
    }//GEN-LAST:event_rdbtnFotoMouseClicked

    private void rdbtnReelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnReelMouseClicked
        publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), "Reel");
        cargarTabla(publicacionesList);
    }//GEN-LAST:event_rdbtnReelMouseClicked

    private void rdbtnHistoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdbtnHistoriaMouseClicked
        publicacionesList = dao.listarPublicacionesUsuario(usuarioPerfil.getUsuario(), "Historia");
        cargarTabla(publicacionesList);
    }//GEN-LAST:event_rdbtnHistoriaMouseClicked

>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
    private void tablaPublicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPublicacionesMouseClicked
        int fila = tablaPublicaciones.rowAtPoint(evt.getPoint());
        int columna = tablaPublicaciones.columnAtPoint(evt.getPoint());

<<<<<<< HEAD
        try {
            abrirFoto(tablaPublicaciones.getValueAt(fila, columna).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            //Clickas en la tercera celda de la tabla y al haber menos imagenes da fallo;
            //El fallo no repercute al codigo por lo que no quiero gestionarlo ni avisar
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_tablaPublicacionesMouseClicked

    private void equisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equisMouseClicked
        // TODO add your handling code here:
        btnMenu.setBorder(null);
        franjaMenu.setVisible(false);
        btnMenu.setVisible(true);
        btnEditarPerfil.setEnabled(true);
        btnMensaje.setVisible(true);
        equis.setVisible(false);
        equis.setBorder(null);
        //btnBloquear.setVisible(true);
        btnSubir.setEnabled(true);
        btnSeguir.setVisible(false);
        rdbtnReel.setEnabled(true);
        rdbtnFoto.setEnabled(true);
        rdbtnHistoria.setVisible(true);
        btnBuscar.setEnabled(true);
        btnParaTi.setEnabled(true);
        btnTienda.setEnabled(true);
        btnCuenta.setEnabled(true);
        tablaPublicaciones.setEnabled(true);
    }//GEN-LAST:event_equisMouseClicked

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        // TODO add your handling code here:
        btnMenu.setBorder(null);
        franjaMenu.setVisible(true);
        btnMenu.setVisible(false);
        btnEditarPerfil.setEnabled(false);
        btnMensaje.setVisible(false);
        equis.setVisible(true);
        equis.setBorder(null);
        //btnBloquear.setVisible(true);
        btnSubir.setEnabled(false);
        rdbtnReel.setEnabled(false);
        rdbtnFoto.setEnabled(false);
        rdbtnHistoria.setVisible(false);
        btnBuscar.setEnabled(false);
        btnParaTi.setEnabled(false);
        btnTienda.setEnabled(false);
        btnCuenta.setEnabled(false);
        tablaPublicaciones.setEnabled(false);
        btnBloquear.setBorder(null);
        btnPublisGuardadas.setBorder(null);
        btnEtiquetas.setBorder(null);
        btnCerrarSesion.setBorder(null);
        btnMejoresAmigos.setBorder(null);

        /*
        Point img1L = franjaMenu.getLocationOnScreen();
        int targetX = img1L.x;
        int targetY = img1L.y;

        int startX = franjaMenu.getX();
        int startY = franjaMenu.getY();
        int endX = targetX;
        int endY = targetY;
        Animator animator = new Animator(750, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                int x = (int) (startX + (endX - startX) * fraction);
                int y = (int) (startY + (endY - startY) * fraction);
                franjaMenu.setLocation(x, y);

            }
        });
         */
    }//GEN-LAST:event_btnMenuMouseClicked

    private void btnPublisGuardadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublisGuardadasActionPerformed
        // TODO add your handling code here:
        PublicacionesGuardadas vent = new PublicacionesGuardadas(vMain, true, dao, publi, usu, usuarioPerfil, this);
        vent.setVisible(true);
    }//GEN-LAST:event_btnPublisGuardadasActionPerformed

    private void btnEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetasActionPerformed
        // TODO add your handling code here:
        PublicacionesEtiquetadas vent = new PublicacionesEtiquetadas(vMain, true, dao, publi, usu, usuarioPerfil, this);
        vent.setVisible(true);
    }//GEN-LAST:event_btnEtiquetasActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "¿Éstas seguro de que quieres cerrar sesión?", "ATENCIÓN!!", dialogButton);
        if (dialogButton == JOptionPane.YES_OPTION) {
            this.dispose();
            vMain.setOpacity(1);
            if (dialogButton == JOptionPane.NO_OPTION) {
                remove(dialogButton);
            }
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnMejoresAmigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMejoresAmigosActionPerformed
        // TODO add your handling code here:
        MejoresAmigos vent = new MejoresAmigos(vMain, this, true, dao, usu);
        vent.setVisible(true);
    }//GEN-LAST:event_btnMejoresAmigosActionPerformed

    private void btnBloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloquearActionPerformed
        // TODO add your handling code here:
        BloquearDesbloquear bd = new BloquearDesbloquear(vMain, this, true, dao, usu);
        bd.setVisible(true);
    }//GEN-LAST:event_btnBloquearActionPerformed

    private void btnEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPerfilActionPerformed
        // TODO add your handling code here:
        this.dispose();
        EditarPerfil vent = new EditarPerfil(vMain, true, dao, usu, usuarioPerfil);
        vent.setVisible(true);

    }//GEN-LAST:event_btnEditarPerfilActionPerformed

    private void cerrar() {
        this.dispose();
        vMain.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bloqueado;
    private javax.swing.JButton btnBloquear;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JButton btnEtiquetas;
    private javax.swing.JButton btnMejoresAmigos;
    private javax.swing.JButton btnMensaje;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnPublisGuardadas;
    private javax.swing.JToggleButton btnSeguir;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private javax.swing.JButton equis;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JPanel franjaMenu;
    private javax.swing.JLabel lblBloqueado;
=======
        abrirFoto(tablaPublicaciones.getValueAt(fila, columna).toString());
    }//GEN-LAST:event_tablaPublicacionesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnEditarPerfil;
    private javax.swing.JButton btnMensaje;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JToggleButton btnSeguir;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JLabel lblNumPublicaciones;
    private javax.swing.JLabel lblPublicacionesText;
    private javax.swing.JLabel lblSeguidores;
    private javax.swing.JLabel lblSeguidoresText;
    private javax.swing.JLabel lblSeguidos;
    private javax.swing.JLabel lblSeguidosText;
<<<<<<< HEAD
    private javax.swing.JLabel lblSigue;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVerificado;
    private javax.swing.JPanel noSigue;
=======
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVerificado;
>>>>>>> 58571a11d8a13ecba610123df311daa514dab3e0
    private javax.swing.JRadioButton rdbtnFoto;
    private javax.swing.JRadioButton rdbtnHistoria;
    private javax.swing.JRadioButton rdbtnReel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaPublicaciones;
    private javax.swing.ButtonGroup tipoPublicacion;
    // End of variables declaration//GEN-END:variables
}
