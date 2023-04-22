package vista;

import clases.Cancion;
import clases.TipoHistoria;
import clases.Usuario;
import java.util.List;
import modelo.DAO;

public class Subir_Historia extends javax.swing.JPanel {

    private DAO dao;
    private Usuario usu;
    private Subir subir;

    public Subir_Historia(Subir subir, boolean par, DAO dao, Usuario usu) {
        initComponents();

        this.dao = dao;
        this.usu = usu;
        this.subir = subir;

        List<Cancion> canciones = dao.listarCanciones();
        for (Cancion i : canciones) {
            cbCancion.addItem(i.getTitulo());
        }
        cbCancion.setSelectedIndex(-1);

        List<TipoHistoria> tipoHistoria = dao.listarTipoHistorias();
        for (TipoHistoria i : tipoHistoria) {
            cbTipoHistoria.addItem(i.getCod_tipo());
        }
        cbTipoHistoria.setSelectedIndex(-1);

        /*
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Subir_Foto.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mejosGroup = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        lblCancion = new javax.swing.JLabel();
        cbCancion = new javax.swing.JComboBox<>();
        lblUbicacion = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnFoto = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblMejos = new javax.swing.JLabel();
        rdbtnNo = new javax.swing.JRadioButton();
        rdbtnSi = new javax.swing.JRadioButton();
        lblHistoria = new javax.swing.JLabel();
        cbTipoHistoria = new javax.swing.JComboBox<>();
        btnSubir = new javax.swing.JButton();

        mejosGroup.add(rdbtnSi);
        mejosGroup.add(rdbtnNo);

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
        btnFoto.setFocusPainted(false);
        btnFoto.setFocusable(false);
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });
        add(btnFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 86, 40));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 640, 20));

        lblMejos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblMejos.setForeground(new java.awt.Color(255, 255, 255));
        lblMejos.setText("Subir a mejores amigos?");
        add(lblMejos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        rdbtnNo.setBackground(new java.awt.Color(49, 51, 53));
        mejosGroup.add(rdbtnNo);
        rdbtnNo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnNo.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnNo.setText("No");
        rdbtnNo.setBorder(null);
        rdbtnNo.setFocusPainted(false);
        rdbtnNo.setFocusable(false);
        rdbtnNo.setRequestFocusEnabled(false);
        rdbtnNo.setRolloverEnabled(false);
        add(rdbtnNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        rdbtnSi.setBackground(new java.awt.Color(49, 51, 53));
        mejosGroup.add(rdbtnSi);
        rdbtnSi.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        rdbtnSi.setForeground(new java.awt.Color(255, 255, 255));
        rdbtnSi.setText("Si");
        rdbtnSi.setBorder(null);
        rdbtnSi.setFocusPainted(false);
        rdbtnSi.setFocusable(false);
        rdbtnSi.setRequestFocusEnabled(false);
        rdbtnSi.setRolloverEnabled(false);
        rdbtnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnSiActionPerformed(evt);
            }
        });
        add(rdbtnSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        lblHistoria.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblHistoria.setForeground(new java.awt.Color(255, 255, 255));
        lblHistoria.setText("Tipo de Historia:");
        add(lblHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, -1));

        cbTipoHistoria.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoHistoria.setFocusable(false);
        add(cbTipoHistoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 260, 30));

        btnSubir.setBackground(new java.awt.Color(227, 227, 227));
        btnSubir.setForeground(new java.awt.Color(0, 0, 0));
        btnSubir.setText("Subir");
        btnSubir.setBorder(null);
        btnSubir.setFocusPainted(false);
        btnSubir.setFocusable(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
        add(btnSubir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, 86, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void rdbtnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnSiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbtnSiActionPerformed

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
    protected javax.swing.JComboBox<String> cbTipoHistoria;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblCancion;
    private javax.swing.JLabel lblHistoria;
    private javax.swing.JLabel lblMejos;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.ButtonGroup mejosGroup;
    protected javax.swing.JRadioButton rdbtnNo;
    protected javax.swing.JRadioButton rdbtnSi;
    protected javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
