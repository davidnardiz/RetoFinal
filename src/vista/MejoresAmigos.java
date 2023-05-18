package vista;

import clases.Usuario;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import modelo.DAO;

/**
 * 
 * @author David
 */
public class MejoresAmigos extends javax.swing.JDialog {

    private List<Usuario> usuariosList;
    private DAO dao;
    private Usuario nosotros;

    /**
     * Genera una pantalla para poder añadir y eliminar mejores amigos.
     *
     * @param vMain Es la ventana padre
     * @param aThis Es la pantalla desde la que se le llama
     * @param modal Es si es modal
     * @param dao Es la interfaz de a logica del negocio
     * @param usu Es el usuario que controla la aplicacion
     */
    public MejoresAmigos(VMain vMain, Perfil aThis, boolean modal, DAO dao, Usuario usu) {
        super(vMain, modal);
        initComponents();
        getContentPane().setBackground(new Color(49, 51, 53));
        this.setLocationRelativeTo(null);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(aniadir);
        grupo.add(quitar);

        btnAniQuit.setBackground(Color.white);
        btnAniQuit.setBorder(null);

        this.nosotros = usu;
        this.dao = dao;

        aniadir();
    }

    /**
     * Ese metodo carga en un arraylist todos los usuarios que el usuario principal puede añadir a us lista de mejores amigos.
     */
    private void aniadir() {
        try {
            amigos.removeAllItems();
            btnAniQuit.setText("Añadir");
            List<Usuario> mAmigos = dao.listarNoMejoresAmigos(nosotros);
            List<Usuario> usuariosBloqueados = dao.listarBloqueados(nosotros);
                
            for (Usuario i : mAmigos) {
                for (int j = 0; j < usuariosBloqueados.size(); j++) {
                    if(i.getUsuario().equalsIgnoreCase(usuariosBloqueados.get(j).getUsuario())){
                        
                    }else{
                        amigos.addItem(i.getUsuario());
                    }
                }    
            }
            
            amigos.setSelectedIndex(-1);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    /**
     * Este metodo carga en el combobox los mejores amigos del usuario principal.
     */
    private void quitar() {
        try {
            amigos.removeAllItems();
            btnAniQuit.setText("Quitar");
            List<Usuario> mAmigos = dao.listarMejoresAmigos(nosotros);
            List<Usuario> usuariosBloqueados = dao.listarBloqueados(nosotros);
                
            for (Usuario i : mAmigos) {
                for (int j = 0; j < usuariosBloqueados.size(); j++) {
                    if(i.getUsuario().equalsIgnoreCase(usuariosBloqueados.get(j).getUsuario())){
                        
                    }else{
                        amigos.addItem(i.getUsuario());
                    }
                }
                
            }
            amigos.setSelectedIndex(-1);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        amigos = new javax.swing.JComboBox<>();
        aniadir = new javax.swing.JRadioButton();
        quitar = new javax.swing.JRadioButton();
        btnAniQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(495, 381));

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
                .addContainerGap(191, Short.MAX_VALUE))
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

        amigos.setToolTipText("");

        aniadir.setBackground(new java.awt.Color(49, 51, 53));
        aniadir.setForeground(new java.awt.Color(255, 255, 255));
        aniadir.setSelected(true);
        aniadir.setText("Añiadir");
        aniadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aniadirActionPerformed(evt);
            }
        });

        quitar.setBackground(new java.awt.Color(49, 51, 53));
        quitar.setForeground(new java.awt.Color(255, 255, 255));
        quitar.setText("Quitar");
        quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarActionPerformed(evt);
            }
        });

        btnAniQuit.setText("Añadir");
        btnAniQuit.setBorder(null);
        btnAniQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAniQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(franjaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amigos, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aniadir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnAniQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aniadir)
                    .addComponent(quitar))
                .addGap(36, 36, 36)
                .addComponent(amigos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnAniQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Llama al metodo de añadir.
     *
     * @param evt
     */
    private void aniadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aniadirActionPerformed
        // TODO add your handling code here:
        aniadir();
    }//GEN-LAST:event_aniadirActionPerformed

    /**
     * Llama al metodo de quitar.
     *
     * @param evt
     */
    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        // TODO add your handling code here:
        quitar();
    }//GEN-LAST:event_quitarActionPerformed

    /**
     * Éste método añade o elimina de la lista de mejores amigos el usuario que está seleccionado en el combobox.
     *
     * @param evt
     */
    private void btnAniQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAniQuitActionPerformed
        // TODO add your handling code here:
        try {
            if (aniadir.isSelected()) {
                dao.aniadirAmigo(nosotros, amigos.getSelectedItem().toString());
                aniadir();

            } else {
                dao.quitarAmigo(nosotros, amigos.getSelectedItem().toString());
                quitar();
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_btnAniQuitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> amigos;
    private javax.swing.JRadioButton aniadir;
    private javax.swing.JButton btnAniQuit;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JRadioButton quitar;
    // End of variables declaration//GEN-END:variables

}
