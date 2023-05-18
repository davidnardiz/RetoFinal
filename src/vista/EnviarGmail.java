package vista;

import clases.Usuario;
import excepciones.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.DAO;

/**
 *
 * @author Jason
 */
public class EnviarGmail extends javax.swing.JDialog {

    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    final private String contraseniaApp = "gybhburhnxqtbzzj";
    final private String emisor = "instagram.retofinal@gmail.com";
    private String asunto;
    private String receptor;
    private String contenido;

    private Usuario usu;
    private Inicio main;
    private VMain conector;
    private DAO dao;

    private String razon;
    private int intentos = 3;

    /**
     * Genera una pantalla donde se envia un correo al gmail del usuario
     *
     * @param vMain Es la ventana padre
     * @param aThis Es la ventana desde la que se le llama
     * @param modal Es si es modal
     * @param dao Es la interfaz de la logica de negocio
     * @param us Es el usuario que controla la aplicacion
     * @param razon Este valor determina si el correo es de registro o de
     * recuperacion
     */
    public EnviarGmail(VMain vMain, Inicio aThis, boolean modal, DAO dao, Usuario us, String razon) {
        super(vMain, modal);

        this.conector = conector;
        this.main = aThis;
        this.dao = dao;
        this.usu = us;
        this.razon = razon;

        switch (razon) {
            case "Registrar":
                asunto = "Bienvenido a instagram";
                contenido = "Gracias  " + usu.getUsuario() + " por registrarte en Instagram.<br>Para comprobar que realmente eres tu introduce el siguiente codigo en la aplicacion: " + usu.getCodGmail() + "<br><br>Esperemos que disfrutes de nuetro reto final :D";
                break;

            case "Cambio":
                asunto = "Cambio de correo";
                contenido = usu.getUsuario() + "Has solicitado cambiar tu direccion de correo<br>Para asegurarnos de que realmente este es tu correo introduce el siguente codigo de verificacion: " + usu.getCodGmail() + "<br>Recuerde que si se equivoca tres veces su cuenta sera eliminada, a si que tenga cuidado";
                break;

            case "Contrasenia": {
                try {
                    usu = dao.buscarUsuario(usu.getUsuario());
                    System.out.println(usu.toString());
                    asunto = "Recuperar contraseña";
                    contenido = "Estimado " + usu.getUsuario() + "<br>Su contraseña es: " + usu.getContrasenia() + "<br><br>Esperemos que tengas mejor memoria para la proxima vez ;)";

                } catch (ErrVariados ex) {
                    ex.mostrarError();
                } catch (ErrSelect ex) {
                    ex.mostrarError();
                }
            }

            break;

            default:
                throw new AssertionError();
        }

        mProperties = new Properties();
        crearEmail();

        setUndecorated(true);
        initComponents();

        if (razon.equalsIgnoreCase("Contrasenia")) {
            lblTitulo.setText("Recuperar contraseña");
            lblMensaje.setText("Te hemos enviado un correo con tu contraseña");
            txtCodigo.setVisible(false);

        }
        setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * Crea el gmail
     */
    private void crearEmail() {
        receptor = usu.getCorreo();

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emisor);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emisor));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            mCorreo.setSubject(asunto);
            mCorreo.setText(contenido, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(EnviarGmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarGmail.class.getName()).log(Level.SEVERE, null, ex);
        }

        enviarEmail();
    }

    /**
     * Envia el gmail al usuario
     */
    private void enviarEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emisor, contraseniaApp);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnviarGmail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnviarGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new excepciones.Button();
        btnCancel = new excepciones.Button();
        lblTitulo = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(187, 187, 187));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(456, 180));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOk.setBackground(new java.awt.Color(48, 170, 63));
        btnOk.setForeground(new java.awt.Color(255, 255, 255));
        btnOk.setText("OK");
        btnOk.setFocusable(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar(evt);
            }
        });
        getContentPane().add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 101, -1));

        btnCancel.setBackground(new java.awt.Color(232, 232, 232));
        btnCancel.setText("Cancelar");
        btnCancel.setFocusable(false);
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 101, -1));

        lblTitulo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(80, 80, 80));
        lblTitulo.setText("Verificacion en dos pasos");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 27, -1, -1));

        lblMensaje.setBackground(getBackground());
        lblMensaje.setForeground(new java.awt.Color(133, 133, 133));
        lblMensaje.setText("Le hemos enviado un codigo de verificacion al correo");
        lblMensaje.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblMensaje.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 64, 419, 28));

        txtCodigo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCodigo.setBorder(null);
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 286, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Si la razon es de registro pedira el codigo que se le ha enviado al gmail
     * si no simplemente cierra la ventana
     *
     * @param evt
     */
    private void cerrar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar
        if (!razon.equalsIgnoreCase("Contrasenia")) {

            if (txtCodigo.getText().equalsIgnoreCase(usu.getCodGmail())) {
                this.dispose();
                conector.setOpacity(0);
                ParaTi parati = new ParaTi(conector, true, dao, usu);
                parati.setVisible(true);

            } else {
                if (intentos > 0) {
                    VentanaMensaje ve = new VentanaMensaje("Codigo incorrecoto", "El codigo que has introducido es erroneo\nSi no introducel el codigo correcto en " + intentos + " tu cuenta sera eliminada");
                    intentos--;

                } else {
                    try {
                        VentanaMensaje ve = new VentanaMensaje("Borrando cuenta", "La cuenta sera eliminada por motivos de seguridad");
                        dao.eliminarUsuario(usu.getUsuario());
                        this.dispose();
                        conector.dispose();

                    } catch (ErrVariados ex) {
                        ex.mostrarError();
                    } catch (ErrDelete ex) {
                        ex.mostrarError();
                    }

                }
            }
        } else {
            this.dispose();
        }

    }//GEN-LAST:event_cerrar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private excepciones.Button btnCancel;
    private excepciones.Button btnOk;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
