package excepciones;

import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

public class VentanaMensaje14 extends javax.swing.JDialog {

    public VentanaMensaje14(String titulo, String mensaje) {

        // super(parent, modal);
        this.setModal(true);

        System.out.println("excepciones.VentanaMensaje.<init>()");
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        txt.setText(mensaje);
        lblTitulo.setText(titulo);
        this.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new excepciones.Button();
        btnCancel = new excepciones.Button();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(187, 187, 187));
        setModal(true);

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

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(getBackground());
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        txt.setColumns(20);
        txt.setLineWrap(true);
        txt.setRows(3);
        txt.setTabSize(3);
        txt.setWrapStyleWord(true);
        txt.setAutoscrolls(false);
        txt.setBorder(null);
        txt.setCaretColor(getBackground());
        txt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(txt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar
        this.dispose();

    }//GEN-LAST:event_cerrar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private excepciones.Button btnCancel;
    private excepciones.Button btnOk;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
