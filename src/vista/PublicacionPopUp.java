package vista;

import clases.Foto;
import clases.Historia;
import clases.Publicacion;
import clases.Reel;
import clases.Usuario;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.DAO;

public class PublicacionPopUp extends javax.swing.JDialog {

    private ParaTi paraTi;
    private DAO dao;
    private Usuario usu;
    private Publicacion publi;
    private Perfil cerrarPerfil;
    private Conector conector;
    private List<Publicacion> publicaciones;
    private boolean guardado;

    public PublicacionPopUp(Conector conector1, ParaTi parent, boolean modal, DAO dao, Publicacion publi, Usuario usu, Usuario usuarioPerfil, Perfil perfil) {
        super(parent, modal);
        this.setModal(modal);
        this.paraTi = parent;
        this.dao = dao;
        this.usu = usu;
        this.publi = publi;
        this.cerrarPerfil = perfil;

        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();

        setLocationRelativeTo(null);

        lblUsuario.setText(usuarioPerfil.getUsuario());

        try {
            lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuarioPerfil.getIcono())));
            imagen.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "La ruta de la imagen no se ha encontrado", "Fallo", 2);
        }

        lblMegusta.setText(publi.getNumLikes() + "");

        if (usu.isVerificado()) {
            lblVerificado.setVisible(true);
        }

        if (publi instanceof Foto) {
            lblDescripcion.setText(((Foto) publi).getDescripcion());
            lblDescripcion.setVisible(true);

            if (publi.getEtiquetado() != null) {
                btnEtiquetado.setVisible(true);
            }
        } else if (publi instanceof Reel) {
            lblDescripcion.setText(((Reel) publi).getDescripcion());
            lblDescripcion.setVisible(true);

        } else if (publi instanceof Historia) {
            lblHistoria.setVisible(true);

            if (((Historia) publi).isMejores_amigos()) {
                lblHistoria.setIcon(new ImageIcon(PublicacionPopUp.class.getResource("/imagenes/pantalla/esMejos.png")));
            }
        }

        if (dao.comprobarLike(usu.getUsuario(), publi.getId_publicacion())) {
            btnLike.setSelected(true);
        }
        guardado = dao.comprobarGuardado(usu.getUsuario(), publi.getId_publicacion());
        if(guardado){
            btnGuardar.setSelected(true);
        }else{
            btnGuardar.setSelected(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        lblIcono = new javax.swing.JLabel();
        lblVerificado = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        lblHistoria = new javax.swing.JLabel();
        lblMegusta = new javax.swing.JLabel();
        btnLike = new javax.swing.JToggleButton();
        btnEtiquetado = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setPreferredSize(new java.awt.Dimension(141, 22));
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, -1, -1));

        lblIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcono.setPreferredSize(new java.awt.Dimension(64, 64));
        getContentPane().add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 15, -1, -1));

        lblVerificado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVerificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/verificado.png"))); // NOI18N
        getContentPane().add(lblVerificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 36, -1, -1));
        lblVerificado.setVisible(false);

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setPreferredSize(new java.awt.Dimension(475, 475));
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        lblHistoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/eshistoria.png"))); // NOI18N
        getContentPane().add(lblHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, -3, 100, 100));
        lblHistoria.setVisible(false);

        lblMegusta.setPreferredSize(new java.awt.Dimension(209, 40));
        getContentPane().add(lblMegusta, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 576, -1, -1));

        btnLike.setBackground(getBackground());
        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/btnLike(false).png"))); // NOI18N
        btnLike.setBorder(null);
        btnLike.setBorderPainted(false);
        btnLike.setFocusable(false);
        btnLike.setPreferredSize(new java.awt.Dimension(46, 46));
        btnLike.setRolloverEnabled(false);
        btnLike.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/btnLike(True).png"))); // NOI18N
        btnLike.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                darLike(evt);
            }
        });
        getContentPane().add(btnLike, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 576, -1, -1));

        btnEtiquetado.setBackground(getBackground());
        btnEtiquetado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/etiquedado.png"))); // NOI18N
        btnEtiquetado.setBorder(null);
        btnEtiquetado.setBorderPainted(false);
        btnEtiquetado.setFocusPainted(false);
        btnEtiquetado.setFocusable(false);
        btnEtiquetado.setRolloverEnabled(false);
        getContentPane().add(btnEtiquetado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 590, -1, -1));
        btnEtiquetado.setVisible(false);

        lblDescripcion.setPreferredSize(new java.awt.Dimension(410, 27));
        getContentPane().add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 623, -1, -1));
        lblDescripcion.setVisible(false);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/guardadd.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
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
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 590, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void darLike(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_darLike
        if (btnLike.isSelected()) {
            lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) + 1 + "");
            dao.insertarLike(usu.getUsuario(), publi.getId_publicacion());

        } else {
            lblMegusta.setText(Integer.parseInt(lblMegusta.getText()) - 1 + "");
            dao.quirarLike(usu.getUsuario(), publi.getId_publicacion());
        }
    }//GEN-LAST:event_darLike

    private void btnEtiquetadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEtiquetadoMouseClicked
        this.dispose();
        cerrarPerfil.dispose();

        Usuario etiquetado = dao.buscarUsuario(publi.getEtiquetado());
        Perfil perfil = new Perfil(conector, paraTi, true, dao, usu, etiquetado);
        this.setVisible(false);
        perfil.setVisible(true);

    }//GEN-LAST:event_btnEtiquetadoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void guardar() {
        if (btnGuardar.isSelected()) {
            dao.guardarPublicación(usu.getUsuario(), publi.getId_publicacion());
        } else {
            dao.eliminarPublicacion(usu.getUsuario(), publi.getId_publicacion());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEtiquetado;
    private javax.swing.JToggleButton btnGuardar;
    private javax.swing.JToggleButton btnLike;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblHistoria;
    private javax.swing.JLabel lblIcono;
    private javax.swing.JLabel lblMegusta;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVerificado;
    // End of variables declaration//GEN-END:variables
}
