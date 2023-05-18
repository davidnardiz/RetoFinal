package vista;

import utilidades.IniciarSesion.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener evento;
    private MigLayout layout;
    private JLabel titulo;
    private JLabel descripcion;
    private JLabel descripcion1;
    private ButtonOutLine boton;
    private boolean logeado;

    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();

    }

    private void init() {
        titulo = new JLabel("Welcome Back!");
        titulo.setFont(new Font("sansserif", 1, 30));
        titulo.setForeground(new Color(245, 245, 245));
        add(titulo);
        descripcion = new JLabel("Si ya tienes cuenta");
        descripcion.setForeground(new Color(245, 245, 245));
        add(descripcion);
        descripcion1 = new JLabel("puedes iniciar sesión!");
        descripcion1.setForeground(new Color(245, 245, 245));
        add(descripcion1);
        boton = new ButtonOutLine();
        boton.setBackground(new Color(255, 255, 255));
        boton.setForeground(new Color(255, 255, 255));
        boton.setText("INICIAR SESIÓN");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                evento.actionPerformed(ae);
            }
        });
        add(boton, "w 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(90, 92, 91), 0, getHeight(), new Color(49, 51, 53));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    public void addEvent(ActionListener event) {
        this.evento = event;
    }

    public void registroIncorrecto(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descripcion, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descripcion1, "pad 0 -" + v + "% 0 0");
    }

    public void registroCorrecto(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descripcion, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descripcion1, "pad 0 -" + v + "% 0 0");
    }

    public void loginIncorrecto(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descripcion, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descripcion1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginCorrecto(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descripcion, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descripcion1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void login(boolean login) {
        if (this.logeado != login) {
            if (login) {
                titulo.setText("Bienvenido!");
                descripcion.setText("Regístrate si todavía");
                descripcion1.setText("no tienes cuenta!");
                boton.setText("REGISTRARSE");
            } else {
                titulo.setText("Inicia sesión!");
                descripcion.setText("Si ya tienes cuenta");
                descripcion1.setText("puedes iniciar sesión!");
                boton.setText("INICIAR SESIÓN");
            }
            this.logeado = login;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
