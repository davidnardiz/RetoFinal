package panelMensaje;

import clases.Articulo;
import clases.Mensaje;
import clases.Usuario;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
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
import javax.swing.JScrollBar;
import modelo.DAO;
import net.miginfocom.swing.MigLayout;
import tienda.FiltrarPanel;
import vista.AniadirProducto;
import vista.Buscar;
import vista.Comprar;
import vista.Tienda;
import vista.VMain;

/**
 * Es una clase auxiliar que genera mensajes personalizadas.
 *
 * @author Bayron
 */
public class panelOpcion extends javax.swing.JPanel {

    public Usuario usu;
    public DAO dao;
    private VMain vMain;
    private boolean tienda;
    private List<Articulo> ar;
    private Tienda tien;
    private boolean filtrar;

    private FiltrarPanel filtrarPanel;

    public panelOpcion(VMain vMain, Usuario usu, DAO dao, boolean tienda, List<Articulo> ar, Tienda tien) {
     
            this.usu = usu;
            this.dao = dao;
            this.tienda = tienda;
            this.filtrarPanel = filtrarPanel;
            this.ar = ar;
            this.tien = tien;
            this.filtrar = filtrar;
            this.vMain = vMain;
            initComponents();

            
            this.setBackground(new Color(49, 51, 53));
         
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

        lblModificar = new javax.swing.JLabel();
        lblBorrar = new javax.swing.JLabel();
        lblSubir = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/boton-editar-removebg-preview (1).jpg"))); // NOI18N
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/borrar (1)-removebg-preview (1).jpg"))); // NOI18N
        lblBorrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBorrarMouseClicked(evt);
            }
        });
        add(lblBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        lblSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/boton-mas-removebg-preview (1) (1).jpg"))); // NOI18N
        lblSubir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubirMouseClicked(evt);
            }
        });
        add(lblSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarMouseClicked
        
        AniadirProducto aniadir = new AniadirProducto(vMain, tien, true, dao, usu, false, false, true);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblModificarMouseClicked

    private void lblBorrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBorrarMouseClicked
        
        AniadirProducto aniadir = new AniadirProducto(vMain, tien, true, dao, usu, false, true, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblBorrarMouseClicked

    private void lblSubirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubirMouseClicked
      
        AniadirProducto aniadir = new AniadirProducto(vMain, tien, true, dao, usu, true, false, false);
        aniadir.setVisible(true);
    }//GEN-LAST:event_lblSubirMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBorrar;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblSubir;
    // End of variables declaration//GEN-END:variables
}
