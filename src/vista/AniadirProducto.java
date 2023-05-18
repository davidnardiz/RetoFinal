package vista;

import clases.Articulo;
import clases.Usuario;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import modelo.DAO;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import net.miginfocom.swing.MigLayout;
import panelMensaje.DefaultLayoutCallBack;
import panelMensaje.DefaultOption;
import panelMensaje.GlassPanePopup;
import panelMensaje.Message;
import panelMensaje.ModernScrollBarUI;
import tienda.panelContenido;
import tienda.panelContenido2;
import utilidades.Utilidades;

/**
 *
 * @author arceu
 */
public class AniadirProducto extends javax.swing.JDialog {

    private Tienda tien;
    private DAO dao;
    private Usuario usu;
    private String imagen;
    private boolean añadir;

    /**
     * Genera una ventana para poder añadir, modificar o elimar articulos a la
     * base de datos
     *
     * @param tien Es la ventana de la que viene
     * @param modal Dice si la pantalla es modal o si no
     * @param dao Es la interface de la logica del negocio
     * @param usu Es el usuario que usa la aplicacion
     * @param añadir Es para saber si va a añadir algo
     * @param borrar Es para saber si va a borrar algo
     * @param modificar Es para saber si va a modificar algo
     */
    public AniadirProducto(Tienda tien, boolean modal, DAO dao, Usuario usu, boolean añadir, boolean borrar, boolean modificar) {
        super(tien, modal);
        this.dao = dao;
        this.usu = usu;
        this.tien = tien;
        initComponents();
        getContentPane().setBackground(new Color(49, 51, 53));
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        sb.setBackground(new Color(49, 51, 53));

        JScrollBar sb2 = scroll2.getVerticalScrollBar();
        sb2.setOpaque(false);
        sb2.setForeground(new Color(33, 140, 206));
        sb2.setPreferredSize(new Dimension(8, 8));
        sb2.setUI(new ModernScrollBarUI());
        sb2.setBackground(new Color(49, 51, 53));

        correcto.setVisible(false);
        pestañaAñadir.setBackground(new Color(49, 51, 53));
        pestañaBorrar.setLayout(new MigLayout("inset 0, fillx, wrap 2", "fill", "[][]"));
        pestañaBorrar.setBackground(new Color(49, 51, 53));

        pestañaModificar.setLayout(new MigLayout("inset 0, fillx, wrap 2", "fill", "[] []"));
        pestañaModificar.setBackground(new Color(49, 51, 53));

        GlassPanePopup.install(tien, tien);
        cargarElementos();
        if (añadir) {
            pestañas.setSelectedIndex(0);
            pestañas.setEnabledAt(1, false);
            pestañas.setEnabledAt(2, false);
        } else if (borrar) {
            pestañas.setSelectedIndex(1);
            pestañas.setEnabledAt(0, false);
            pestañas.setEnabledAt(2, false);
        } else if (modificar) {
            pestañas.setSelectedIndex(2);
            pestañas.setEnabledAt(0, false);
            pestañas.setEnabledAt(1, false);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

            }
        });

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        pestañas = new javax.swing.JTabbedPane();
        pestañaAñadir = new javax.swing.JPanel();
        subir = new javax.swing.JButton();
        Descripcion = new tienda.AgregarTexto();
        peso = new tienda.AgregarTexto();
        Precio = new tienda.AgregarTexto();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        aniadirFoto = new javax.swing.JButton();
        correcto = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        pestañaBorrar = new javax.swing.JPanel();
        scroll2 = new javax.swing.JScrollPane();
        pestañaModificar = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

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
                .addContainerGap(390, Short.MAX_VALUE))
        );
        franjaArribaLayout.setVerticalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo)
                    .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pestañas.setToolTipText("");

        pestañaAñadir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subir.setText("Subir");
        subir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subirMouseClicked(evt);
            }
        });
        pestañaAñadir.add(subir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 82, 32));

        Descripcion.setLabelText("Descripcion");
        Descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescripcionActionPerformed(evt);
            }
        });
        pestañaAñadir.add(Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 314, -1));

        peso.setLabelText("Peso");
        pestañaAñadir.add(peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 314, -1));

        Precio.setLabelText("Precio");
        pestañaAñadir.add(Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 314, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Datos del nuevo Producto");
        pestañaAñadir.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 314, 41));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Imagen");
        pestañaAñadir.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 73, 45));

        aniadirFoto.setText("Seleccionar imagen");
        aniadirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aniadirFotoActionPerformed(evt);
            }
        });
        pestañaAñadir.add(aniadirFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        correcto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/marca-de-verificacion-removebg-preview (1).jpg"))); // NOI18N
        pestañaAñadir.add(correcto, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 52, 51));

        pestañas.addTab("Añadir", pestañaAñadir);

        javax.swing.GroupLayout pestañaBorrarLayout = new javax.swing.GroupLayout(pestañaBorrar);
        pestañaBorrar.setLayout(pestañaBorrarLayout);
        pestañaBorrarLayout.setHorizontalGroup(
            pestañaBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        pestañaBorrarLayout.setVerticalGroup(
            pestañaBorrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        scroll.setViewportView(pestañaBorrar);

        pestañas.addTab("Borrar", scroll);

        javax.swing.GroupLayout pestañaModificarLayout = new javax.swing.GroupLayout(pestañaModificar);
        pestañaModificar.setLayout(pestañaModificarLayout);
        pestañaModificarLayout.setHorizontalGroup(
            pestañaModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );
        pestañaModificarLayout.setVerticalGroup(
            pestañaModificarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 465, Short.MAX_VALUE)
        );

        scroll2.setViewportView(pestañaModificar);

        pestañas.addTab("Modificar", scroll2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(pestañas, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pestañas.getAccessibleContext().setAccessibleName("Añadir");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aniadirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aniadirFotoActionPerformed
        imagen = Utilidades.seleccionarImagenTienda(this, WIDTH, HEIGHT);
        if (imagen != null) {
            aniadirFoto.setVisible(false);
            correcto.setVisible(true);
        }
    }//GEN-LAST:event_aniadirFotoActionPerformed

    private void DescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescripcionActionPerformed

    private void subirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subirMouseClicked

        try {
            Articulo art = new Articulo();
            String codigo;
            String ultimoCodigo = "";
            int numCod = 0;
            ultimoCodigo = dao.calcularIdArticulo("A");
            numCod = Integer.parseInt(ultimoCodigo.substring(2));
            numCod++;
            codigo = "A-" + String.format("%03d", numCod);

            art.setId_articulo(codigo);
            art.setDescripcion(Descripcion.getText());
            art.setPrecio(Float.parseFloat(Precio.getText()));
            art.setPeso(Integer.parseInt(peso.getText()));
            art.setImagen(imagen);
            art.setVendedor(usu.getUsuario());
            art.setFechaSubida(LocalDate.now());
            art.setFechaCompra(null);
            art.setValoracion(0);
            dao.insertarArticulo(art);

            this.dispose();
            tien.cargarElementos(false);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_subirMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private tienda.AgregarTexto Descripcion;
    private tienda.AgregarTexto Precio;
    private javax.swing.JButton aniadirFoto;
    private javax.swing.JLabel correcto;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private tienda.AgregarTexto peso;
    private javax.swing.JPanel pestañaAñadir;
    private javax.swing.JPanel pestañaBorrar;
    private javax.swing.JPanel pestañaModificar;
    private javax.swing.JTabbedPane pestañas;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JScrollPane scroll2;
    private javax.swing.JButton subir;
    // End of variables declaration//GEN-END:variables

    
    private void cargarElementos() {
        try {
            List<Articulo> articulos = dao.sacarTodosLosArticulos();

            for (Articulo art : articulos) {
                if (art.getVendedor().equalsIgnoreCase(usu.getUsuario())) {
                    String precio = String.valueOf(art.getPrecio());
                    String usuV = art.getVendedor();
                    String peso = String.valueOf(art.getPeso());
                    Usuario otroUsu = dao.buscarUsuario(usuV);

                    Icon icon = new ImageIcon(getClass().getResource("/imagenes/iconos/" + otroUsu.getIcono()));
                    Icon icon2 = new ImageIcon(getClass().getResource("/imagenes/tienda/" + art.getImagen()));

                    pestañaBorrar.add(new panelContenido(icon, icon2, dao, tien, true, false, usu, art, true, this));

                    pestañaModificar.add(new panelContenido2(icon, icon2, art.getVendedor(), precio, art.getDescripcion(), dao, tien, false, art.getId_articulo(), true, peso, this));

                }
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    public void popUpMensaje(Message mensaje) {
        GlassPanePopup.showPopup(mensaje, new DefaultOption() {

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
    }
}
