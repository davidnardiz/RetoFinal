package tienda;

import panelMensaje.*;
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
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import modelo.DAO;
import net.miginfocom.swing.MigLayout;
import vista.AniadirProducto;
import vista.Buscar;
import vista.ParaTi;
import vista.Tienda;

/**
 *
 * @author RAVEN
 */
public class panelOpciones extends javax.swing.JPanel {

    /**
     * Creates new form Notifications
     */
    public Usuario usu;
    public DAO dao;
    private ParaTi paraTI;
    private Tienda tien;
    private boolean añadir;
    private boolean borrar;
    private boolean modificar;
    public panelOpciones(Tienda tien, DAO dao, Usuario usu) {
        this.tien = tien;
        this.usu = usu;
        this.dao = dao;
        initComponents();
        setOpaque(false);
        boolean añadir ;
        boolean borrar ;
        boolean modificar ;
        loadNoti();

    }

    private void loadNoti() {

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(49, 51, 53));
        int header = 0;
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/boton-editar-removebg-preview (1).jpg"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/boton-mas-removebg-preview (1) (1).jpg"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 50, 50));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/borrar (1)-removebg-preview (1).jpg"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 50, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        añadir = true;
        AniadirProducto aniadir = new AniadirProducto(tien, true, dao, usu, añadir, false, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
       borrar = true;
        AniadirProducto aniadir = new AniadirProducto(tien, true, dao, usu, false, borrar, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        modificar = true;
        AniadirProducto aniadir = new AniadirProducto(tien, true, dao, usu, false, false, modificar);
        aniadir.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
