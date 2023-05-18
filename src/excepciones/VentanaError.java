package excepciones;

/**
 *
 * @author Jason
 */
public class VentanaError extends javax.swing.JDialog {

    /**
     * Genera una ventana personalizada para poder ver mensajes de error
     *
     * @param mensaje Es el mensaje que se va a mostrar
     */
    public VentanaError(String mensaje) {

        // super(parent, modal);
        this.setModal(true);

        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        txt.setText(mensaje);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new excepciones.Button();
        btnCancel = new excepciones.Button();
        lbl = new javax.swing.JLabel();
        txt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(187, 187, 187));
        setModal(true);
        setResizable(false);

        btnOk.setBackground(new java.awt.Color(48, 170, 63));
        btnOk.setForeground(new java.awt.Color(255, 255, 255));
        btnOk.setText("OK");
        btnOk.setFocusable(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(232, 232, 232));
        btnCancel.setText("Cancelar");
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar(evt);
            }
        });

        lbl.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbl.setForeground(new java.awt.Color(80, 80, 80));
        lbl.setText("Error en la base de datos");

        txt.setBackground(getBackground());
        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        txt.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 240, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbl)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbl)
                .addGap(18, 18, 18)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Es el metodo para cerrar la ventana
     *
     * @param evt
     */
    private void cerrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar
        this.dispose();
    }//GEN-LAST:event_cerrar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private excepciones.Button btnCancel;
    private excepciones.Button btnOk;
    private javax.swing.JLabel lbl;
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables
}
