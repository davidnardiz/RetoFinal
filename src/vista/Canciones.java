package vista;

import clases.Cancion;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class Canciones extends javax.swing.JDialog {

    private String audio;
    private int duracionSegundos;
    private Thread t;

    public Canciones(java.awt.Frame parent, boolean modal, Cancion can) {
        super(parent, modal);
        initComponents();

        getContentPane().setBackground(new Color(49, 51, 53));
        setLocationRelativeTo(null);

        double duracionMinutos = can.getDuracion();
        duracionSegundos = (int) (duracionMinutos * 60);

        sDuracion.setMaximum(duracionSegundos);

        lblFoto.setIcon(new ImageIcon(getClass().getResource("/imagenes/canciones/" + can.getTitulo() + ".png")));
        lblTitulo.setText(can.getTitulo());
        lblArtista.setText(can.getArtista());

        contador();
    }

    public void contador() {
        t = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = sDuracion.getValue(); i <= duracionSegundos; i++) {
                    try {
                        lblSegundos.setText(i + "");
                        sDuracion.setValue(i);

                        Thread.sleep(1000);

                        i = sDuracion.getValue();

                    } catch (InterruptedException ex) {

                    }
                }
            }
        });
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFoto = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblArtista = new javax.swing.JLabel();
        sDuracion = new javax.swing.JSlider();
        btnReproducir = new javax.swing.JToggleButton();
        lblSegundos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setResizable(false);

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/canciones/en la mia.png"))); // NOI18N

        lblTitulo.setFont(new java.awt.Font("SansSerif", 0, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("En la mia");

        lblArtista.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblArtista.setForeground(new java.awt.Color(255, 255, 255));
        lblArtista.setText("Jyco");

        sDuracion.setBackground(getBackground());
        sDuracion.setMaximum(200);
        sDuracion.setValue(0);
        sDuracion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sDuracionStateChanged(evt);
            }
        });

        btnReproducir.setBackground(getBackground());
        btnReproducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/pausar.png"))); // NOI18N
        btnReproducir.setBorder(null);
        btnReproducir.setBorderPainted(false);
        btnReproducir.setContentAreaFilled(false);
        btnReproducir.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/seguir.png"))); // NOI18N
        btnReproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReproducirActionPerformed(evt);
            }
        });

        lblSegundos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSegundos.setForeground(new java.awt.Color(255, 255, 255));
        lblSegundos.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReproducir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sDuracion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblArtista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblSegundos)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArtista)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSegundos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReproducir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReproducirActionPerformed
        if (btnReproducir.isSelected()) {
            t.stop();
        } else {
            contador();
        }

    }//GEN-LAST:event_btnReproducirActionPerformed

    private void sDuracionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sDuracionStateChanged
        lblSegundos.setText(sDuracion.getValue() + "s");
    }//GEN-LAST:event_sDuracionStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnReproducir;
    private javax.swing.JLabel lblArtista;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblSegundos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSlider sDuracion;
    // End of variables declaration//GEN-END:variables
}
