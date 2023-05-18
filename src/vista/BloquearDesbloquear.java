package vista;

import clases.Usuario;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.util.List;
import modelo.DAO;

/**
 *
 * @author arceu
 */
public class BloquearDesbloquear extends javax.swing.JDialog {

    private DAO dao;
    private Usuario nosotros;
    private int cant = 0;

    /**
     * Genera una ventana para bloquear y desbloquear usuarios
     *
     * @param vMain Es la ventana padre
     * @param aThis Es la ventana desde la que se llama al metodo
     * @param modal Es si la ventana es modal
     * @param dao Es la interfaz de la logica del negocio
     * @param usu Es el usuario que controla la aplicacion
     */
    public BloquearDesbloquear(VMain vMain, Perfil aThis, boolean modal, DAO dao, Usuario usu) {
        super(vMain, modal);
        this.dao = dao;
        this.nosotros = usu;
        getContentPane().setBackground(new Color(49, 51, 53));
        initComponents();
        this.setLocationRelativeTo(null);
        btnBloqDesbloq.setBackground(Color.white);
        btnBloqDesbloq.setBorder(null);

        bloqueados.setBackground(Color.white);
        bloqueados.setBorder(null);

        bloquear();

    }

    /**
     * Ese metodo carga un combobox con los usuarios que el usuario principal puede bloquear.
     */
    private void bloquear() {
        try {
            bloqueados.removeAllItems();
            btnBloqDesbloq.setText("Bloquear");
            List<Usuario> etiquetados = dao.listarDesbloqueados(nosotros);
            for (Usuario i : etiquetados) {
                if (!i.getUsuario().equalsIgnoreCase(nosotros.getUsuario())) {
                    bloqueados.addItem(i.getUsuario());
                }

            }
            bloqueados.setSelectedIndex(-1);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    /**
     * Ese metodo carga un combobox con los usuarios que el usuario principal tiene bloqueados.
     */
    private void desbloquear() {
        try {
            bloqueados.removeAllItems();
            btnBloqDesbloq.setText("Desbloquear");
            List<Usuario> etiquetados = dao.listarBloqueados(nosotros);
            for (Usuario i : etiquetados) {
                bloqueados.addItem(i.getUsuario());
            }
            bloqueados.setSelectedIndex(-1);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        bloquear = new javax.swing.JRadioButton();
        desbloquear = new javax.swing.JRadioButton();
        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        bloqueados = new javax.swing.JComboBox<>();
        btnBloqDesbloq = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 255, 0));

        bloquear.setBackground(new java.awt.Color(49, 51, 53));
        grupo.add(bloquear);
        bloquear.setForeground(new java.awt.Color(255, 255, 255));
        bloquear.setSelected(true);
        bloquear.setText("Bloquear");
        bloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloquearActionPerformed(evt);
            }
        });

        desbloquear.setBackground(new java.awt.Color(49, 51, 53));
        grupo.add(desbloquear);
        desbloquear.setForeground(new java.awt.Color(255, 255, 255));
        desbloquear.setText("Desbloquear");
        desbloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquearActionPerformed(evt);
            }
        });

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
                .addContainerGap(205, Short.MAX_VALUE))
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

        bloqueados.setToolTipText("");

        btnBloqDesbloq.setText("Bloquear");
        btnBloqDesbloq.setBorder(null);
        btnBloqDesbloq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloqDesbloqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bloqueados, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(desbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBloqDesbloq, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desbloquear)
                    .addComponent(bloquear))
                .addGap(36, 36, 36)
                .addComponent(bloqueados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnBloqDesbloq, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloquearActionPerformed
        // TODO add your handling code here:
        bloquear();
    }//GEN-LAST:event_bloquearActionPerformed

    private void desbloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desbloquearActionPerformed
        // TODO add your handling code here:
        desbloquear();
    }//GEN-LAST:event_desbloquearActionPerformed

    /**
     * Ese metodo bloquea o desbloquea al usuario que esta seleccionado en el
     * combobox en base a si el boton esta pulsado o no.
     *
     * @param evt
     */
    private void btnBloqDesbloqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloqDesbloqActionPerformed
        // TODO add your handling code here:
        try {
            if (bloquear.isSelected()) {
                dao.bloquearUsuario(nosotros, bloqueados.getSelectedItem().toString());
                bloquear();
            } else {
                dao.desbloquearUsuario(nosotros, bloqueados.getSelectedItem().toString());
                desbloquear();
            }
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrInsert ex) {
            ex.mostrarError();
        } catch (ErrDelete ex) {
            ex.mostrarError();
        }
    }//GEN-LAST:event_btnBloqDesbloqActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> bloqueados;
    private javax.swing.JRadioButton bloquear;
    private javax.swing.JButton btnBloqDesbloq;
    private javax.swing.JRadioButton desbloquear;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    // End of variables declaration//GEN-END:variables

}
