package excepciones;

/**
 *
 * @author arceu
 */
public class VentanaMensaje extends javax.swing.JDialog {

    /**
     * Genera una ventana personalizada para poder ver los errores
     *
     * @param titulo Es el titulo de la ventana
     * @param mensaje Es el mensaje que se va a mostrar
     */
    public VentanaMensaje(String titulo, String mensaje) {

        // super(parent, modal);
        this.setModal(true);

        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        txt.setText(mensaje);
        txt.setCaretPosition(0);
        lblTitulo.setText(titulo);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new excepciones.Button();
        btnCancel = new excepciones.Button();
        lblTitulo = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();

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

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(80, 80, 80));

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll.setAutoscrolls(true);

        txt.setEditable(false);
        txt.setBackground(getBackground());
        txt.setColumns(20);
        txt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txt.setForeground(new java.awt.Color(133, 133, 133));
        txt.setRows(3);
        scroll.setViewportView(txt);
        txt.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
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
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
