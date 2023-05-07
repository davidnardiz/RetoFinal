package vista;

import clases.Cancion;
import clases.Usuario;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DAO;

public class Subir_Foto extends javax.swing.JPanel {

    private DAO dao;
    private Usuario usu;
    private Subir subir;

    public Subir_Foto(Subir subir, boolean par, DAO dao, Usuario usu) {
        try {
            initComponents();

            this.subir = subir;
            this.dao = dao;
            this.usu = usu;

            List<Cancion> canciones = dao.listarCanciones();
            for (Cancion i : canciones) {
                cbCancion.addItem(i.getTitulo());
            }
            cbCancion.setSelectedIndex(-1);

            List<Usuario> etiquetados = dao.listarUsuario();
            for (Usuario i : etiquetados) {
                cbEtiquetado.addItem(i.getUsuario());
            }
            cbEtiquetado.setSelectedIndex(-1);

        } catch (ErrVariados ex) {
            ErrVariados er = new ErrVariados("");
        } catch (ErrSelect ex) {
            ErrSelect er = new ErrSelect("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblCancion = new javax.swing.JLabel();
        cbCancion = new javax.swing.JComboBox<>();
        lblUbicacion = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnFoto = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblResolucion = new javax.swing.JLabel();
        cbResolucion = new javax.swing.JComboBox<>();
        lbllEtiquetado = new javax.swing.JLabel();
        cbEtiquetado = new javax.swing.JComboBox<>();
        lblDescripcion = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnSubir = new javax.swing.JButton();

        setBackground(new java.awt.Color(49, 51, 53));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 640, 20));

        lblCancion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblCancion.setForeground(new java.awt.Color(255, 255, 255));
        lblCancion.setText("Cancion:");
        add(lblCancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, 30));

        cbCancion.setForeground(new java.awt.Color(0, 0, 0));
        cbCancion.setFocusable(false);
        add(cbCancion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 260, 30));

        lblUbicacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacion.setText("Ubicacion:");
        add(lblUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, 30));

        txtUbicacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtUbicacion.setForeground(new java.awt.Color(0, 0, 0));
        add(txtUbicacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 260, 30));

        btnFoto.setBackground(new java.awt.Color(227, 227, 227));
        btnFoto.setForeground(new java.awt.Color(0, 0, 0));
        btnFoto.setText("Elegir Foto");
        btnFoto.setBorder(null);
        btnFoto.setBorderPainted(false);
        btnFoto.setFocusable(false);
        btnFoto.setRequestFocusEnabled(false);
        btnFoto.setRolloverEnabled(false);
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        add(btnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 86, 40));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 640, 20));

        lblResolucion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblResolucion.setForeground(new java.awt.Color(255, 255, 255));
        lblResolucion.setText("Resolucion:");
        add(lblResolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, -1, 30));

        cbResolucion.setForeground(new java.awt.Color(0, 0, 0));
        cbResolucion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "144p", "240p", "360p", "480p", "720p", "1080p" }));
        cbResolucion.setSelectedIndex(-1);
        cbResolucion.setFocusable(false);
        add(cbResolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 260, 30));

        lbllEtiquetado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lbllEtiquetado.setForeground(new java.awt.Color(255, 255, 255));
        lbllEtiquetado.setText("Etiquetado:");
        add(lbllEtiquetado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, 30));

        cbEtiquetado.setForeground(new java.awt.Color(0, 0, 0));
        cbEtiquetado.setFocusable(false);
        add(cbEtiquetado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 260, 30));

        lblDescripcion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setText("Descripcion:");
        add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 0));
        txtDescripcion.setRows(5);
        scroll.setViewportView(txtDescripcion);

        add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 270, -1));

        btnSubir.setBackground(new java.awt.Color(227, 227, 227));
        btnSubir.setForeground(new java.awt.Color(0, 0, 0));
        btnSubir.setText("Subir");
        btnSubir.setBorder(null);
        btnSubir.setBorderPainted(false);
        btnSubir.setFocusable(false);
        btnSubir.setRequestFocusEnabled(false);
        btnSubir.setRolloverEnabled(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
        add(btnSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, 86, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        subir.elegirFoto();
    }//GEN-LAST:event_btnFotoActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        subir.comprobarDatos();
    }//GEN-LAST:event_btnSubirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btnFoto;
    protected javax.swing.JButton btnSubir;
    protected javax.swing.JComboBox<String> cbCancion;
    protected javax.swing.JComboBox<String> cbEtiquetado;
    protected javax.swing.JComboBox<String> cbResolucion;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCancion;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblResolucion;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JLabel lbllEtiquetado;
    private javax.swing.JScrollPane scroll;
    protected javax.swing.JTextArea txtDescripcion;
    protected javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
