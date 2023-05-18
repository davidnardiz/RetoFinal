package vista;

import clases.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import modelo.DAO;
import chat.ChatEvent;

public class Chat extends javax.swing.JDialog {

    private DAO dao;
    private VMain vMain;
    private Usuario usu;

    public Chat(VMain parent, boolean modal, DAO dao, Usuario nosotros, boolean usu) {
        super(parent, modal);
        this.setModal(modal);
        this.dao = dao;
        this.usu = nosotros;
        this.vMain = parent;

        chatArea.addChatEvent(new ChatEvent() {
            @Override
            public void mousePressedSendButton(ActionEvent evt) {

            }

            @Override
            public void mousePressedFileButton(ActionEvent evt) {
            }

            @Override
            public void keyTyped(KeyEvent evt) {
            }
        });
        initComponents();
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        chatArea = new chat.ChatArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

        lblLogoLetras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/letrasInstagram.png"))); // NOI18N
        lblLogoLetras.setPreferredSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout franjaArribaLayout = new javax.swing.GroupLayout(franjaArriba);
        franjaArriba.setLayout(franjaArribaLayout);
        franjaArribaLayout.setHorizontalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(392, Short.MAX_VALUE))
        );
        franjaArribaLayout.setVerticalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblLogo)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 682, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));
        franajAbajo.setPreferredSize(new java.awt.Dimension(632, 100));

        btnParaTi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/para ti.png"))); // NOI18N
        btnParaTi.setToolTipText("");
        btnParaTi.setAlignmentY(0.0F);
        btnParaTi.setAutoscrolls(true);
        btnParaTi.setBorder(null);
        btnParaTi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnParaTi.setFocusable(false);
        btnParaTi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParaTi.setRolloverEnabled(false);
        btnParaTi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParaTiActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/buscar.png"))); // NOI18N
        btnBuscar.setToolTipText("");
        btnBuscar.setAlignmentY(0.0F);
        btnBuscar.setAutoscrolls(true);
        btnBuscar.setBorder(null);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setRolloverEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/subir.png"))); // NOI18N
        btnSubir.setToolTipText("");
        btnSubir.setAlignmentY(0.0F);
        btnSubir.setAutoscrolls(true);
        btnSubir.setBorder(null);
        btnSubir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubir.setFocusable(false);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubir.setRolloverEnabled(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnTienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/tienda.png"))); // NOI18N
        btnTienda.setToolTipText("");
        btnTienda.setAlignmentY(0.0F);
        btnTienda.setAutoscrolls(true);
        btnTienda.setBorder(null);
        btnTienda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTienda.setFocusable(false);
        btnTienda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienda.setRolloverEnabled(false);
        btnTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiendaActionPerformed(evt);
            }
        });

        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/cuenta.png"))); // NOI18N
        btnCuenta.setToolTipText("");
        btnCuenta.setAlignmentY(0.0F);
        btnCuenta.setAutoscrolls(true);
        btnCuenta.setBorder(null);
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCuenta.setFocusable(false);
        btnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
                .addComponent(btnParaTi)
                .addGap(66, 66, 66)
                .addComponent(btnBuscar)
                .addGap(64, 64, 64)
                .addComponent(btnSubir)
                .addGap(64, 64, 64)
                .addComponent(btnTienda)
                .addGap(66, 66, 66)
                .addComponent(btnCuenta)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        franajAbajoLayout.setVerticalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCuenta)
                    .addComponent(btnBuscar)
                    .addComponent(btnParaTi)
                    .addComponent(btnSubir)
                    .addComponent(btnTienda))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, -1, -1));
        getContentPane().add(chatArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 630, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Éste método abre la ventana para ti.
     * 
     * @param evt 
     */
    private void btnParaTiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParaTiActionPerformed
        ParaTi paraTi = new ParaTi(vMain, true, dao, usu);
        this.dispose();
        paraTi.setVisible(true);
    }//GEN-LAST:event_btnParaTiActionPerformed

    
    /**
     * Éste método abre la ventana buscar.
     * 
     * @param evt 
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar buscar = new Buscar(vMain, true, dao, usu, false);
        this.dispose();
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Éste método abre la ventana subir.
     * 
     * @param evt 
     */
    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
        Subir subir = new Subir(vMain, true, dao, usu, null);
        this.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    /**
     * Éste método abre la ventana tienda.
     * 
     * @param evt 
     */
    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Tienda tienda = new Tienda(vMain, true, dao, usu);
        this.dispose();
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    /**
     * Éste método abre la ventana cuenta.
     * 
     * @param evt 
     */
    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        Perfil perfil = new Perfil(vMain, true, dao, usu, usu);
        this.dispose();
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private chat.ChatArea chatArea;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    // End of variables declaration//GEN-END:variables
}
