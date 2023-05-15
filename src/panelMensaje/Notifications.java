package panelMensaje;

import clases.Articulo;
import clases.Mensaje;
import clases.Usuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import modelo.DAO;
import net.miginfocom.swing.MigLayout;
import vista.Buscar;
import vista.Comprar;
import vista.ParaTi;
import vista.Tienda;

/**
 *
 * @author RAVEN
 */
public class Notifications extends javax.swing.JPanel {

    /**
     * Creates new form Notifications
     */
    public Usuario usu;
    public DAO dao;
    private ParaTi paraTI;
    private boolean tienda;
    private List<Articulo> ar;
    private Tienda tien;

    public Notifications(Usuario usu, DAO dao, boolean tienda, List<Articulo> ar, Tienda tien) {
        this.usu = usu;
        this.dao = dao;
        this.tienda = tienda;
        this.ar = ar;
        this.tien = tien;

        initComponents();

        scroll.setBackground(new Color(49, 51, 53));
        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setForeground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        panel.setLayout(new MigLayout("inset 0, fillx, wrap", "[fill]"));
        btnComprar.setBackground(Color.WHITE);
        btnComprar.setContentAreaFilled(false);
        panel.setBackground(new Color(49, 51, 53));
        List<String> conversaciones = dao.sacarConversaciones(usu.getUsuario());

        loadNoti(conversaciones);
        if (conversaciones.size() >= 1) {
            lblConver.setVisible(false);
            btnConversacion.setVisible(false);
        }
        if (tienda) {
            btnConversacion.setVisible(false);
            lblConver.setVisible(false);
            lblTitulo.setVisible(false);
        } else {
            btnComprar.setVisible(false);
        }
    }

    private void loadNoti(List<String> conversaciones) {

        if (tienda) {
            String rutaProyecto = System.getProperty("user.dir");
            for (Articulo art : ar) {
                Icon icon2 = new ImageIcon(rutaProyecto + "\\src\\imagenes\\tienda\\" + art.getImagen());
                panel.add(new ItemTienda(icon2, art, dao, usu, tienda, tien, null));
            }
        } else {

            String rutaProyecto = System.getProperty("user.dir");

            LocalDate fechaHoy = LocalDate.now();

            for (String con : conversaciones) {
                Usuario otroUsu = dao.buscarUsuario(con);
                List<Mensaje> mensajes = dao.sacarMensajes(usu.getUsuario(), con);
                String ultimoMensaje = mensajes.get(mensajes.size() - 1).getMensaje();
                int n = (int) ChronoUnit.DAYS.between(mensajes.get(mensajes.size() - 1).getFechaEnvio(), fechaHoy);
                System.out.println(n);
                if (n == 0) {
                    panel.add(new Item(new ImageIcon(rutaProyecto + "\\src\\imagenes\\iconos\\" + otroUsu.getIcono()), con, ultimoMensaje, "Hoy", dao, usu, tienda));
                } else {
                    panel.add(new Item(new ImageIcon(rutaProyecto + "\\src\\imagenes\\iconos\\" + otroUsu.getIcono()), con, ultimoMensaje, "Hace " + n + " dias", dao, usu, tienda));
                }

            }
        }

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int header = 10;
        AffineTransform tran = new AffineTransform();
        tran.translate(getWidth() - 25, 5);
        tran.rotate(Math.toRadians(45));
        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), tran);
        Area area = new Area(p);
        area.add(new Area(new RoundRectangle2D.Double(0, header, getWidth(), getHeight() - header, 10, 10)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        lblConver = new javax.swing.JLabel();
        btnConversacion = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(117, 117, 117));
        lblTitulo.setText("Mensajes");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new java.awt.Color(255, 62, 220));
        panel.setOpaque(false);
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConver.setText("No tienes conversaciones");
        panel.add(lblConver, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        btnConversacion.setText("Iniciar Conversacion");
        btnConversacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConversacionActionPerformed(evt);
            }
        });
        panel.add(btnConversacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        scroll.setViewportView(panel);

        btnComprar.setText("Finalizar Compra");
        btnComprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComprar))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConversacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConversacionActionPerformed
        Buscar buscar = new Buscar(paraTI, true, dao, usu, true);
        this.setVisible(false);
        buscar.setVisible(true);
    }//GEN-LAST:event_btnConversacionActionPerformed

    private void btnComprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprarMouseClicked

        Comprar comprar = new Comprar(tien, true, dao, ar, usu);
        GlassPanePopup.closePopupAll();
        comprar.setVisible(true);

    }//GEN-LAST:event_btnComprarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnConversacion;
    private javax.swing.JLabel lblConver;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
