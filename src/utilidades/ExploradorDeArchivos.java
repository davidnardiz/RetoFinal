package utilidades;

import java.io.File;

public class ExploradorDeArchivos extends javax.swing.JDialog {

    public ExploradorDeArchivos() {
        String rutaProyecto = System.getProperty("user.dir");
        File rutaExplorador = new File(rutaProyecto + "/src/imagenes/publicaciones/");

        setLocationRelativeTo(null);

        System.out.println(getClass());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        explorador = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        explorador.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        explorador.setDialogTitle("Selecciona una imagen");
        explorador.setFileHidingEnabled(false);
        explorador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exploradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(explorador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(explorador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exploradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exploradorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exploradorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser explorador;
    // End of variables declaration//GEN-END:variables
}
