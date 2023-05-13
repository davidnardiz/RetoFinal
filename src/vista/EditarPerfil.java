/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import clases.Usuario;
import excepciones.ErrAlter;
import excepciones.ErrVariados;
import java.awt.Color;
import java.awt.Frame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import modelo.DAO;
import utilidades.Utilidades;

/**
 *
 * @author 1dam
 */
public class EditarPerfil extends javax.swing.JDialog {

    private Usuario nosotros;
    private String ruta;
    private DAO dao;
    private VMain vMain;
    private Usuario us;
    private Usuario usuarioPerfil;

    public EditarPerfil(VMain vMain, boolean modal, DAO dao, Usuario usu, Usuario usuarioPerfil) {
        super(vMain, modal);
        this.nosotros = usu;
        this.dao = dao;
        this.vMain = vMain;
        this.us = usu;
        this.usuarioPerfil = usuarioPerfil;
        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();
        setLocationRelativeTo(null);

        cargarDatos(usu);
    }

    private void cargarDatos(Usuario us) {
        try {
            fotoPerfil.setIcon(new ImageIcon(EditarPerfil.class.getResource("/imagenes/iconos/" + nosotros.getIcono())));
        } catch (NullPointerException e) {
            ErrVariados ex = new ErrVariados("Imagen");
        }

        usuario.setText(us.getUsuario());
        dni.setText(us.getDni());
        contrasenia.setText(us.getContrasenia());
        correo.setText(us.getCorreo());
        telefono.setText(us.getTelefono() + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franjaArriba2 = new javax.swing.JPanel();
        fotoPerfil = new javax.swing.JLabel();
        dni = new javax.swing.JTextField();
        contrasenia = new javax.swing.JTextField();
        telefono = new javax.swing.JTextField();
        correo = new javax.swing.JTextField();
        usuario = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        btnEnviarDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(682, 830));
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
                .addContainerGap(358, Short.MAX_VALUE))
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

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        franjaArriba2.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba2.setPreferredSize(new java.awt.Dimension(648, 80));

        javax.swing.GroupLayout franjaArriba2Layout = new javax.swing.GroupLayout(franjaArriba2);
        franjaArriba2.setLayout(franjaArriba2Layout);
        franjaArriba2Layout.setHorizontalGroup(
            franjaArriba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        franjaArriba2Layout.setVerticalGroup(
            franjaArriba2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        getContentPane().add(franjaArriba2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 784, -1, -1));

        fotoPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotoPerfilMouseClicked(evt);
            }
        });
        getContentPane().add(fotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 186, 64, 64));

        dni.setEditable(false);
        getContentPane().add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 358, 327, 40));
        getContentPane().add(contrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 300, 327, 40));
        getContentPane().add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 474, 327, 40));
        getContentPane().add(correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 416, 327, 40));

        usuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 208, 165, 42));

        btnVolver.setBackground(new java.awt.Color(227, 227, 227));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 619, 120, 44));

        btnEnviarDatos.setBackground(new java.awt.Color(227, 227, 227));
        btnEnviarDatos.setText("Enviar datos");
        btnEnviarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarDatosActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 619, 130, 44));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrar();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEnviarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarDatosActionPerformed
        editarDatos();
    }//GEN-LAST:event_btnEnviarDatosActionPerformed

    private void fotoPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotoPerfilMouseClicked
        ruta = Utilidades.seleccionarIcono(this);
        fotoPerfil.setIcon(new ImageIcon(EditarPerfil.class.getResource("/imagenes/iconos/" + ruta)));
    }//GEN-LAST:event_fotoPerfilMouseClicked

    private void cerrar() {
        Perfil per = new Perfil(vMain, true, dao, us, usuarioPerfil);
        this.dispose();
        per.setVisible(true);
    }

    private void editarDatos() {
        try {
            Usuario us = new Usuario();
            us.setUsuario(usuario.getText());
            us.setIcono(ruta);
            us.setContrasenia(contrasenia.getText());
            us.setCorreo(correo.getText());
            us.setTelefono(Integer.parseInt(telefono.getText()));
            dao.editarPerfil(us);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrAlter ex) {
            ex.mostrarError();
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarDatos;
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextField contrasenia;
    private javax.swing.JTextField correo;
    private javax.swing.JTextField dni;
    private javax.swing.JLabel fotoPerfil;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JPanel franjaArriba2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JTextField telefono;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables

}
