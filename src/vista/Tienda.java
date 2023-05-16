package vista;

import clases.Articulo;
import clases.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import modelo.DAO;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import panelMensaje.DefaultLayoutCallBack;
import panelMensaje.DefaultOption;
import panelMensaje.GlassPanePopup;
import panelMensaje.ModernScrollBarUI;
import panelMensaje.Notifications;
import tienda.FiltrarPanel;

import tienda.panelContenido;

public class Tienda extends javax.swing.JDialog {

    private DAO dao;
    private Usuario usu;
    private ParaTi paraTi;
    private boolean añadir;
    private boolean borrar;
    private boolean modificar;
    private List<Articulo> ar;
    private FiltrarPanel filtrarPanel = new FiltrarPanel(this);

    public Tienda(ParaTi parent, boolean modal, DAO dao, Usuario usu) {
        super(parent, modal);
        this.setModal(modal);
        this.dao = dao;
        this.usu = usu;
        this.ar = ar;
        this.paraTi = parent;
        boolean añadir;
        boolean borrar;
        boolean modificar;

        ar = new ArrayList<>();

        GlassPanePopup.install(this, this);

        setTitle("Tienda");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();

        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.setBackground(new Color(49, 51, 53));
        cargarElementos(false);
        panel.setLayout(new MigLayout("inset 0, fillx, wrap 2", "fill", "[][]"));
        panel.setBackground(new Color(49, 51, 53));
        panelOpciones.setBackground(new Color(49, 51, 53));
        panelDestino.setBackground(new Color(49, 51, 53));
        panelOpciones.setVisible(false);

        opciones2.setVisible(false);

        carritoCompraLleno.setVisible(false);
        //efectoCarrito();
    }

    public void setAr(List<Articulo> ar) {
        this.ar = ar;
    }

    public List<Articulo> getAr() {
        return ar;
    }

    public void setCarritoCompraLleno(JLabel carritoCompraLleno) {
        this.carritoCompraLleno = carritoCompraLleno;
    }

    public JLabel getCarritoCompraLleno() {
        return carritoCompraLleno;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCarritoCompra = new javax.swing.JPanel();
        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        carritoCompraLleno = new javax.swing.JLabel();
        carritoCompra1 = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        opciones1 = new javax.swing.JLabel();
        panelOpciones = new javax.swing.JPanel();
        lblModificar = new javax.swing.JLabel();
        lblBorrar = new javax.swing.JLabel();
        lblSubir = new javax.swing.JLabel();
        panelDestino = new javax.swing.JPanel();
        opciones2 = new javax.swing.JLabel();
        filtrar = new javax.swing.JLabel();

        javax.swing.GroupLayout panelCarritoCompraLayout = new javax.swing.GroupLayout(panelCarritoCompra);
        panelCarritoCompra.setLayout(panelCarritoCompraLayout);
        panelCarritoCompraLayout.setHorizontalGroup(
            panelCarritoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );
        panelCarritoCompraLayout.setVerticalGroup(
            panelCarritoCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setModal(true);
        setName("paraTi"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));
        franjaArriba.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N
        franjaArriba.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 6, -1, -1));

        lblLogoLetras.setForeground(getBackground());
        lblLogoLetras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/letrasInstagram.png"))); // NOI18N
        lblLogoLetras.setPreferredSize(new java.awt.Dimension(50, 16));
        franjaArriba.add(lblLogoLetras, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 6, 185, 65));

        carritoCompraLleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/carrito-de-supermercado-removebg-preview (1).jpg"))); // NOI18N
        carritoCompraLleno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carritoCompraLlenoMouseClicked(evt);
            }
        });
        franjaArriba.add(carritoCompraLleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 57, 51));

        carritoCompra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/carrito-de-compras (1)-removebg-preview (1).jpg"))); // NOI18N
        franjaArriba.add(carritoCompra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 57, 51));

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 645, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));

        btnParaTi.setBackground(franjaArriba.getBackground());
        btnParaTi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/para ti.png"))); // NOI18N
        btnParaTi.setToolTipText("");
        btnParaTi.setAlignmentY(0.0F);
        btnParaTi.setAutoscrolls(true);
        btnParaTi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnParaTi.setFocusable(false);
        btnParaTi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParaTi.setMaximumSize(new java.awt.Dimension(50, 50));
        btnParaTi.setMinimumSize(new java.awt.Dimension(50, 50));
        btnParaTi.setPreferredSize(new java.awt.Dimension(50, 50));
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
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setMaximumSize(new java.awt.Dimension(50, 50));
        btnBuscar.setMinimumSize(new java.awt.Dimension(50, 50));
        btnBuscar.setPreferredSize(new java.awt.Dimension(50, 50));
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
        btnSubir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubir.setFocusable(false);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubir.setMaximumSize(new java.awt.Dimension(50, 50));
        btnSubir.setMinimumSize(new java.awt.Dimension(50, 50));
        btnSubir.setPreferredSize(new java.awt.Dimension(50, 50));
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
        btnTienda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTienda.setFocusable(false);
        btnTienda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienda.setMaximumSize(new java.awt.Dimension(50, 50));
        btnTienda.setMinimumSize(new java.awt.Dimension(50, 50));
        btnTienda.setPreferredSize(new java.awt.Dimension(50, 50));
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
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCuenta.setFocusable(false);
        btnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCuenta.setMaximumSize(new java.awt.Dimension(50, 50));
        btnCuenta.setMinimumSize(new java.awt.Dimension(50, 50));
        btnCuenta.setPreferredSize(new java.awt.Dimension(50, 50));
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
                .addComponent(btnParaTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btnCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        franajAbajoLayout.setVerticalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParaTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 717, -1, -1));

        scroll.setBorder(null);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        getContentPane().add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 86, 563, 625));

        opciones1.setBackground(new java.awt.Color(41, 51, 54));
        opciones1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones-removebg-preview (1).jpg"))); // NOI18N
        opciones1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opciones1MouseClicked(evt);
            }
        });
        getContentPane().add(opciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 630, 53, 50));

        lblModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/boton-editar-removebg-preview (1).jpg"))); // NOI18N
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });

        lblBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/borrar (1)-removebg-preview (1).jpg"))); // NOI18N
        lblBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBorrarMouseClicked(evt);
            }
        });

        lblSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/boton-mas-removebg-preview (1) (1).jpg"))); // NOI18N
        lblSubir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSubir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(lblSubir, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
        );

        getContentPane().add(panelOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 620, 53, 170));

        javax.swing.GroupLayout panelDestinoLayout = new javax.swing.GroupLayout(panelDestino);
        panelDestino.setLayout(panelDestinoLayout);
        panelDestinoLayout.setHorizontalGroup(
            panelDestinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        panelDestinoLayout.setVerticalGroup(
            panelDestinoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        getContentPane().add(panelDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, -1, -1));

        opciones2.setBackground(new java.awt.Color(41, 51, 54));
        opciones2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/opciones-removebg-preview (1).jpg"))); // NOI18N
        opciones2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opciones2MouseClicked(evt);
            }
        });
        getContentPane().add(opciones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 630, 53, 50));

        filtrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/filtrar.jpg"))); // NOI18N
        filtrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtrarMouseClicked(evt);
            }
        });
        getContentPane().add(filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnParaTiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParaTiActionPerformed
        this.dispose();
        paraTi.setVisible(true);
    }//GEN-LAST:event_btnParaTiActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar buscar = new Buscar(paraTi, true, dao, usu, false);
        this.dispose();
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
        Subir subir = new Subir(paraTi, true, dao, usu);
        this.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Tienda tienda = new Tienda(paraTi, true, dao, usu);
        this.dispose();
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        Perfil perfil = new Perfil(paraTi, true, dao, usu, usu);
        this.dispose();
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void opciones1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opciones1MouseClicked

        panelOpciones.setVisible(true);
        Point img1L = panelDestino.getLocationOnScreen();

        int targetX = img1L.x;
        int targetY = img1L.y;

        int startX = panelOpciones.getX();
        int startY = panelOpciones.getY();

        int endX = targetX - 10;
        int endY = targetY - 30;

        Animator animator = new Animator(400, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                int x = (int) (startX + (endX - startX) * fraction);
                int y = (int) (startY + (endY - startY) * fraction);

                panelOpciones.setLocation(x, y);

            }

            @Override
            public void end() {
                // Devolver la imagen del producto a su posición inicial

                // imgArticulo1.setImage(nuevoIcon);
            }
        });

        // Iniciar el Animator
        animator.start();

        opciones1.setVisible(false);
        opciones2.setVisible(true);
    }//GEN-LAST:event_opciones1MouseClicked

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarMouseClicked
        modificar = true;
        AniadirProducto aniadir = new AniadirProducto(this, true, dao, usu, false, false, modificar);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblModificarMouseClicked

    private void lblBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseClicked
        borrar = true;
        AniadirProducto aniadir = new AniadirProducto(this, true, dao, usu, false, borrar, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblBorrarMouseClicked

    private void lblSubirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubirMouseClicked
        añadir = true;
        AniadirProducto aniadir = new AniadirProducto(this, true, dao, usu, añadir, false, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblSubirMouseClicked

    private void opciones2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opciones2MouseClicked

        int startX = panelOpciones.getX();
        int startY = panelOpciones.getY();

        int endX = 570;
        int endY = 620;

        Animator animator = new Animator(400, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                int x = (int) (startX + (endX - startX) * fraction);
                int y = (int) (startY + (endY - startY) * fraction);

                panelOpciones.setLocation(x, y);
            }

            @Override
            public void end() {

            }
        });

        // Iniciar el Animator
        animator.start();
        panelOpciones.setVisible(false);
        opciones2.setVisible(false);
        opciones1.setVisible(true);
    }//GEN-LAST:event_opciones2MouseClicked

    private void carritoCompraLlenoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carritoCompraLlenoMouseClicked

        boolean tien = true;
        GlassPanePopup.showPopup(new Notifications(usu, dao, tien, ar, this, false, filtrarPanel), new DefaultOption() {

            @Override
            public float opacity() {
                return 0;
            }

            @Override
            public LayoutCallback getLayoutCallBack(Component parent) {
                return new DefaultLayoutCallBack(parent) {
                    @Override
                    public void correctBounds(ComponentWrapper cw) {
                        if (parent.isVisible()) {
                            cw.setBounds(215, 70, 380, 500);
                        } else {
                            super.correctBounds(cw);
                        }
                    }

                };
            }

        });
    }//GEN-LAST:event_carritoCompraLlenoMouseClicked

    private void filtrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtrarMouseClicked

        GlassPanePopup.showPopup(new Notifications(usu, dao, false, ar, this, true, filtrarPanel), new DefaultOption() {

            @Override
            public float opacity() {
                return 0;
            }

            @Override
            public LayoutCallback getLayoutCallBack(Component parent) {
                return new DefaultLayoutCallBack(parent) {
                    @Override
                    public void correctBounds(ComponentWrapper cw) {
                        if (parent.isVisible()) {
                            cw.setBounds(265, 122, 380, 500);
                        } else {
                            super.correctBounds(cw);
                        }
                    }

                };
            }

        });
    }//GEN-LAST:event_filtrarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private javax.swing.JLabel carritoCompra1;
    private javax.swing.JLabel carritoCompraLleno;
    private javax.swing.JLabel filtrar;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblBorrar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblSubir;
    private javax.swing.JLabel opciones1;
    private javax.swing.JLabel opciones2;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelCarritoCompra;
    private javax.swing.JPanel panelDestino;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables

    public void cargarElementos(boolean filtrar) {
        panel.removeAll();
        if (filtrar) {
            int max = filtrarPanel.obtenerMax();
            int min = filtrarPanel.obtenerMin();
            int opc = filtrarPanel.obtenerOpc();

            List<Articulo> articulos = dao.sacarArituclosPorPrecio(min, max, opc);

            for (Articulo art : articulos) {
                int cont = 0;
                String usuV = art.getVendedor();
                Usuario otroUsu = dao.buscarUsuario(usuV);
                Icon icon = new ImageIcon(getClass().getResource("/imagenes/iconos/" + otroUsu.getIcono()));
                Icon icon2 = new ImageIcon(getClass().getResource("/imagenes/tienda/" + art.getImagen()));

                for (Articulo a : ar) {
                    if (art.getId_articulo().equalsIgnoreCase(a.getId_articulo())) {
                        cont++;
                        panel.add(new panelContenido(icon, icon2, dao, this, false, false, usu, art, true, null));
                    }
                }
                if (cont == 0) {
                    panel.add(new panelContenido(icon, icon2, dao, this, false, false, usu, art, false, null));
                }

            }
        } else {
            List<Articulo> articulos = dao.sacarTodosLosArticulos();

            for (Articulo art : articulos) {
                int cont = 0;
                String usuV = art.getVendedor();
                Usuario otroUsu = dao.buscarUsuario(usuV);
                Icon icon = new ImageIcon(getClass().getResource("/imagenes/iconos/" + otroUsu.getIcono()));
                Icon icon2 = new ImageIcon(getClass().getResource("/imagenes/tienda/" + art.getImagen()));

                for (Articulo a : ar) {
                    if (art.getId_articulo().equalsIgnoreCase(a.getId_articulo())) {
                        cont++;
                        panel.add(new panelContenido(icon, icon2, dao, this, false, false, usu, art, true, null));
                    }
                }
                if (cont == 0) {
                    panel.add(new panelContenido(icon, icon2, dao, this, false, false, usu, art, false, null));
                }

            }
        }

        ///
    }

}
