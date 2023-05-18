package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import modelo.DAO;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author arceu
 */
public class VMain extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private Inicio inicio;
    private DAO dao;
    private boolean logeado = true;
    private final double addSize = 30;
    private final double tamanioCover = 40;
    private final double loginSize = 60;
    private TimingTarget target;

    public VMain(DAO dao) {
        this.dao = dao;
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
        initComponents();
        init();

    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        inicio = new Inicio(this, dao);
        target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraccion) {
                double fraccionCover;
                double fraccionLogin;
                double tamanio = tamanioCover;
                if (fraccion <= 0.5f) {
                    tamanio += fraccion * addSize;
                } else {
                    tamanio += addSize - fraccion * addSize;
                }
                if (logeado) {
                    fraccionCover = 1f - fraccion;
                    fraccionLogin = fraccion;
                    if (fraccion >= 0.5f) {
                        cover.registroCorrecto(fraccionCover * 100);
                    } else {
                        cover.loginCorrecto(fraccionLogin * 100);
                    }
                } else {
                    fraccionCover = fraccion;
                    fraccionLogin = 1f - fraccion;
                    if (fraccion <= 0.5f) {
                        cover.registroIncorrecto(fraccion * 100);
                    } else {
                        cover.loginIncorrecto((1f - fraccion) * 100);
                    }
                }
                if (fraccion >= 0.5f) {
                    inicio.showRegister(logeado);
                }
                fraccionCover = Double.valueOf(df.format(fraccionCover));
                fraccionLogin = Double.valueOf(df.format(fraccionLogin));
                layout.setComponentConstraints(cover, "width " + tamanio + "%, pos " + fraccionCover + "al 0 n 100%");
                layout.setComponentConstraints(inicio, "width " + loginSize + "%, pos " + fraccionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                logeado = !logeado;
            }
        };

        animacion();

    }

    protected void animacion() {
        Animator animador = new Animator(800, target);
        animador.setAcceleration(0.5f);
        animador.setDeceleration(0.5f);
        animador.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);
        bg.add(cover, "width " + tamanioCover + "%, pos " + (logeado ? "1al" : "0al") + " 0 n 100%");
        bg.add(inicio, "width " + loginSize + "%, pos " + (logeado ? "0al" : "1al") + " 0 n 100%"); //  1al as 100%
        inicio.showRegister(!logeado);
        cover.login(logeado);
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animador.isRunning()) {
                    animador.start();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
