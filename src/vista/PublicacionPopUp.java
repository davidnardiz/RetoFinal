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
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.DAO;

/**
 *
 * @author David
 */
public class PublicacionPopUp extends javax.swing.JDialog {

    private DAO dao;
    private Usuario usu;
    private Publicacion publi;
    private Perfil cerrarPerfil;
    private VMain vMain;
    private boolean guardado;

    /**
     * Abre una ventana donde se muestran todos los datos de una publicacion
     *
     * @param vMain Es la ventana padre
     * @param modal Es si es modal
     * @param dao Es la interfaz de la logica de negocio
     * @param publi Es la publicacion que se quiere mostrar
     * @param usu Es el usuario que controla la aplicacion
     * @param usuarioPerfil Es el usuaio que ha subido la publicacion
     * @param perfil Es la ventana desde la que se abre
     */
    public PublicacionPopUp(VMain vMain, boolean modal, DAO dao, Publicacion publi, Usuario usu, Usuario usuarioPerfil, Perfil perfil) {
        super(vMain, modal);
        try {
            this.vMain = vMain;
            this.dao = dao;
            this.usu = usu;
            this.publi = publi;
            this.cerrarPerfil = perfil;

            getContentPane().setBackground(new Color(49, 51, 53));
            initComponents();

            setLocationRelativeTo(null);

            lblUsuario.setText(usuarioPerfil.getUsuario());

            lblIcono.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/iconos/" + usuarioPerfil.getIcono())));
            imagen.setIcon(new ImageIcon(ParaTi.class.getResource("/imagenes/publicaciones/" + publi.getImagen())));

            lblMegusta.setText(publi.getNumLikes() + "");

            if (dao.comprobarLike(usu.getUsuario(), publi.getId_publicacion())) {
                btnLike.setSelected(true);
            }

            if (usu.getUsuario().equalsIgnoreCase(usuarioPerfil.getUsuario())) {
                btnEditar.setVisible(true);
                btnEliminar.setVisible(true);
                this.setSize(510, 760);
            }

            if (usuarioPerfil.isVerificado()) {
                lblVerificado.setVisible(true);
            }

            if (publi instanceof Foto) {
                lblDescripcion.setText(((Foto) publi).getDescripcion());
                lblDescripcion.setVisible(true);

                if (((Foto) publi).getEtiquetado() != null) {
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

            guardado = dao.comprobarGuardado(usu.getUsuario(), publi.getId_publicacion());
            if (guardado) {
                btnGuardar.setSelected(true);
            } else {
                btnGuardar.setSelected(false);
            }

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagen = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblIcono = new javax.swing.JLabel();
        lblVerificado = new javax.swing.JLabel();
        lblHistoria = new javax.swing.JLabel();
        lblMegusta = new javax.swing.JLabel();
        btnLike = new javax.swing.JToggleButton();
        btnEtiquetado = new javax.swing.JButton();
        lblDescripcion = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setPreferredSize(new java.awt.Dimension(485, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setPreferredSize(new java.awt.Dimension(475, 475));
        getContentPane().add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

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

        lblHistoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHistoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/eshistoria.png"))); // NOI18N
        getContentPane().add(lblHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, -3, 100, 100));
        lblHistoria.setVisible(false);

        lblMegusta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblMegusta.setForeground(new java.awt.Color(255, 255, 255));
        lblMegusta.setPreferredSize(new java.awt.Dimension(209, 40));
        getContentPane().add(lblMegusta, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 576, -1, -1));

        btnLike.setBackground(getBackground());
        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/btnLike(false).png"))); // NOI18N
        btnLike.setBorder(null);
        btnLike.setBorderPainted(false);
        btnLike.setContentAreaFilled(false);
        btnLike.setFocusPainted(false);
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
        btnEtiquetado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtiquetadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEtiquetado, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 580, -1, -1));
        btnEtiquetado.setVisible(false);

        lblDescripcion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcion.setPreferredSize(new java.awt.Dimension(410, 27));
        getContentPane().add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 623, -1, -1));
        lblDescripcion.setVisible(false);

        btnEliminar.setBackground(new java.awt.Color(227, 227, 227));
        btnEliminar.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminar.setText("Eliminar Publicacion");
        btnEliminar.setBorder(null);
        btnEliminar.setFocusable(false);
        btnEliminar.setRequestFocusEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 670, 120, 40));
        btnEliminar.setVisible(false);

        btnEditar.setBackground(new java.awt.Color(227, 227, 227));
        btnEditar.setForeground(new java.awt.Color(0, 0, 0));
        btnEditar.setText("Editar Publicacion");
        btnEditar.setBorder(null);
        btnEditar.setFocusable(false);
        btnEditar.setRequestFocusEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, 120, 40));
        btnEditar.setVisible(false);

        btnGuardar.setBackground(getBackground());
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/guardadd.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/guardd2.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 570, 46, 46));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Da o quita el like
     *
     * @param evt
     */
    private void darLike(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_darLike
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
        }
    }//GEN-LAST:event_darLike

    /**
     * Permite editar los datos de la publicacion, solo si la hemos subido
     * nosotros
     *
     * @param evt
     */
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Subir subir = new Subir(vMain, true, dao, usu, publi);
        this.dispose();
        cerrarPerfil.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * Permite eliminar la publicacion, solo si la hemos subido nosotros
     *
     * @param evt
     */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int seguro = JOptionPane.showConfirmDialog(this, "Seguro que quieres eliminar la publicacion");

        if (seguro == 0) {
            try {
                dao.eliminarPublicacion(publi.getId_publicacion());
                this.dispose();
            } catch (ErrVariados ex) {
                ex.mostrarError();
            } catch (ErrDelete ex) {
                ex.mostrarError();
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * Abre el perfil del usuario etiquetado en la publicacion
     *
     * @param evt
     */
    private void btnEtiquetadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtiquetadoActionPerformed
        try {
            this.dispose();
            cerrarPerfil.dispose();

            Usuario etiquetado = dao.buscarUsuario(((Foto) publi).getEtiquetado());
            Perfil perfil = new Perfil(vMain, true, dao, usu, etiquetado);
            this.setVisible(false);
            perfil.setVisible(true);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_btnEtiquetadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * Guarda la publicacion para poder verla mas tarde
     */
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
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
