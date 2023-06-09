package tienda;

import clases.Articulo;
import clases.Usuario;
import excepciones.ErrDelete;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.Timer;
import modelo.DAO;
import vista.AniadirProducto;
import vista.Tienda;

/**
 * Es una clase auxiliar que genera campos personalizados.
 *
 * @author Bayron
 */
public class panelContenido extends javax.swing.JPanel {

    private Timer timer;

    private JLabel carritoCompra;
    private DAO dao;
    private Tienda tien;
    private boolean borrarB;
    private boolean modificar;
    private Usuario usu;
    private Articulo art;
    public List<Articulo> artCarrito;
    public boolean mostrar;
    private AniadirProducto produc;

    public panelContenido(Icon icon, Icon image, DAO dao, Tienda tien, boolean borrarB, boolean modificar, Usuario usu, Articulo art, boolean mostrar, AniadirProducto produc) {
        try {
            initComponents();
            setBackground(new Color(49, 51, 53));
            pic.setIcon(icon);
            this.artCarrito = artCarrito;
            this.produc = produc;
            this.art = art;
            this.dao = dao;
            this.usu = usu;
            this.mostrar = mostrar;
            this.borrarB = borrarB;
            this.modificar = modificar;
            imgArticulo1.setImage((Icon) image);
            this.tien = tien;
            vendedor.setText(art.getVendedor());
            descripcion.setText(art.getDescripcion());
            descripcion.setBackground(new Color(49, 51, 53));
            precio.setText(Float.toString(art.getPrecio()));

            artCarrito = new ArrayList<>();

            int valoracion = dao.obtenerValoracion(art);
            
            starRatingNoEditable1.setStar(valoracion);
            if (borrarB) {
                System.out.println("asddfff");
                comprar.setVisible(false);
                agregado.setVisible(false);
                borrar.setVisible(true);
            } else if (modificar) {
                System.out.println("pruebitaa");
                comprar.setVisible(false);
                borrar.setVisible(false);
                agregado.setVisible(false);

            } else if (usu.getUsuario().equalsIgnoreCase(art.getVendedor())) {
                borrar.setVisible(false);
                agregado.setVisible(false);
                comprar.setVisible(false);
            } else {
                borrar.setVisible(false);
                agregado.setVisible(false);
            }

            if (mostrar && !borrarB) {
                comprar.setVisible(false);
                agregado.setVisible(true);
            } else if (!mostrar && !borrarB) {
                comprar.setVisible(true);
                agregado.setVisible(false);
            }
            
            if(usu.getUsuario().equalsIgnoreCase(art.getVendedor())){
                comprar.setVisible(false);
            }
            vendedor.setForeground(Color.white);
            descripcion.setForeground(Color.white);
            precio.setForeground(Color.white);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    public void setAgregado(JLabel agregado) {
        this.agregado = agregado;
    }

    public void setComprar(JLabel comprar) {
        this.comprar = comprar;
    }

    public JLabel getAgregado() {
        return agregado;
    }

    public JLabel getComprar() {
        return comprar;
    }

    //Getters y setters
    public void setArtCarrito(List<Articulo> artCarrito) {
        this.artCarrito = artCarrito;
    }

    public List<Articulo> getArtCarrito() {
        return artCarrito;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow = new tienda.PanelShadow();
        precio = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        comprar = new javax.swing.JLabel();
        imgArticulo1 = new tienda.PictureBox();
        pic1 = new panelMensaje.ImageAvatar();
        agregado = new javax.swing.JLabel();
        pic = new panelMensaje.ImageAvatar();
        vendedor = new javax.swing.JLabel();
        borrar = new javax.swing.JLabel();
        starRatingNoEditable1 = new tienda.StarRatingNoEditable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelShadow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        precio.setText(" ");
        panelShadow.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 72, 16));

        descripcion.setEditable(false);
        descripcion.setBackground(new java.awt.Color(41, 51, 53));
        descripcion.setBorder(null);
        panelShadow.add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 180, 60));

        comprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/anadir-a-la-cesta-removebg-preview (1).jpg"))); // NOI18N
        comprar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comprarMouseClicked(evt);
            }
        });
        panelShadow.add(comprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 60, 50));

        imgArticulo1.add(pic1);
        pic1.setBounds(0, 0, 53, 44);

        panelShadow.add(imgArticulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 250, 140));

        agregado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/marca-de-verificacion-removebg-preview (1).jpg"))); // NOI18N
        panelShadow.add(agregado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, -1, -1));
        panelShadow.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 30));

        vendedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        panelShadow.add(vendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 134, 30));

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/borrar (1)-removebg-preview (1).jpg"))); // NOI18N
        borrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarMouseClicked(evt);
            }
        });
        panelShadow.add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 50, 50));
        panelShadow.add(starRatingNoEditable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 190, 40));

        add(panelShadow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void comprarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprarMouseClicked
        try {
            List<Articulo> articulos = dao.sacarTodosLosArticulos();
            String rutaImg = " ";

            for (Articulo art : articulos) {
                if (art.getDescripcion().equalsIgnoreCase(descripcion.getText())) {
                    rutaImg = art.getImagen();
                }
            }

            comprar.setVisible(false);
            agregado.setVisible(true);
            //tien.efectoCarrito();
            JLabel prueba = tien.getCarritoCompraLleno();
            prueba.setVisible(true);

            Articulo ar = new Articulo();
            ar.setImagen(art.getImagen());
            ar.setId_articulo(art.getId_articulo());
            ar.setVendedor(art.getVendedor());
            ar.setPrecio(art.getPrecio());
            ar.setDescripcion(art.getDescripcion());
            artCarrito.add(ar);

            List<Articulo> a = tien.getAr();
            a.add(ar);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }

    }//GEN-LAST:event_comprarMouseClicked

    private void borrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarMouseClicked
        try {
            dao.borrarArticulo(art.getId_articulo());
            tien.cargarElementos(false);
            produc.dispose();
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_borrarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel agregado;
    private javax.swing.JLabel borrar;
    public javax.swing.JLabel comprar;
    private javax.swing.JTextField descripcion;
    private tienda.PictureBox imgArticulo1;
    private tienda.PanelShadow panelShadow;
    private panelMensaje.ImageAvatar pic;
    private panelMensaje.ImageAvatar pic1;
    private javax.swing.JLabel precio;
    private tienda.StarRatingNoEditable starRatingNoEditable1;
    private javax.swing.JLabel vendedor;
    // End of variables declaration//GEN-END:variables
}
