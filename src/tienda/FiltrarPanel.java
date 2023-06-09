/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package tienda;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vista.Tienda;

/**
 *
 * @author Bayron
 */
public class FiltrarPanel extends javax.swing.JPanel {

    /**
     * Creates new form FiltrarPanel
     */
    private Tienda tien;

    public FiltrarPanel(Tienda tien) {
        this.tien = tien;
        this.setBackground(new Color(49, 51, 53));
        initComponents();

        Slider.setBackground(new Color(49, 51, 53));
        Slider.setForeground(Color.WHITE);
        SliderMax.setBackground(new Color(49, 51, 53));
        SliderMax.setForeground(Color.WHITE);
        sinLimite.setVisible(false);
    }

    public ButtonGroup getButtonGroup1() {
        return buttonGroup1;
    }

    public JLabel getPrecioMaximo() {
        return precioMaximo;
    }

    public JLabel getPrecioMinimo() {
        return precioMinimo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Slider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        precioMinimo = new javax.swing.JLabel();
        SliderMax = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        precioMaximo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mayor = new javax.swing.JCheckBox();
        menor = new javax.swing.JCheckBox();
        sinLimite = new javax.swing.JLabel();

        Slider.setMajorTickSpacing(100);
        Slider.setMaximum(999);
        Slider.setPaintLabels(true);
        Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Precio Minimo:");

        precioMinimo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        precioMinimo.setForeground(new java.awt.Color(255, 255, 255));

        SliderMax.setMajorTickSpacing(100);
        SliderMax.setMaximum(999);
        SliderMax.setPaintLabels(true);
        SliderMax.setToolTipText("999 ");
        SliderMax.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderMaxStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Precio Maximo:");

        precioMaximo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        precioMaximo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ordenar por:");

        mayor.setBackground(new java.awt.Color(49, 51, 53));
        buttonGroup1.add(mayor);
        mayor.setForeground(new java.awt.Color(255, 255, 255));
        mayor.setText("Precio: De  mayor a menor");

        menor.setBackground(new java.awt.Color(49, 51, 53));
        buttonGroup1.add(menor);
        menor.setForeground(new java.awt.Color(255, 255, 255));
        menor.setText("Precio: De menor a mayor");

        sinLimite.setForeground(new java.awt.Color(255, 255, 255));
        sinLimite.setText("Sin limite");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(precioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(SliderMax, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(precioMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(sinLimite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menor)
                    .addComponent(mayor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(precioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(precioMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(SliderMax, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sinLimite))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(menor)
                .addGap(10, 10, 10)
                .addComponent(mayor)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderStateChanged

        precioMinimo.setText(String.valueOf(Slider.getValue()));

        if (Slider.getValue() > SliderMax.getValue()) {
            Slider.setValue(SliderMax.getValue());
        }
        String dato = String.valueOf(Slider.getValue());
    }//GEN-LAST:event_SliderStateChanged

    private void SliderMaxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderMaxStateChanged
        precioMaximo.setText(String.valueOf(SliderMax.getValue()));

        if (SliderMax.getValue() < Slider.getValue()) {
            SliderMax.setValue(Slider.getValue());
        }
        if (SliderMax.getValue() == 999) {
            sinLimite.setVisible(true);
        } else {
            sinLimite.setVisible(false);
        }

    }//GEN-LAST:event_SliderMaxStateChanged

    public int obtenerMax() {
        return SliderMax.getValue();
    }

    public int obtenerMin() {
        return Slider.getValue();
    }

    public int obtenerOpc() {
        int opc = 0;
        if (menor.isSelected()) {
            opc = 1;
        } else {
            opc = 0;
        }
        return opc;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider Slider;
    private javax.swing.JSlider SliderMax;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox mayor;
    private javax.swing.JCheckBox menor;
    public javax.swing.JLabel precioMaximo;
    public javax.swing.JLabel precioMinimo;
    private javax.swing.JLabel sinLimite;
    // End of variables declaration//GEN-END:variables
}
