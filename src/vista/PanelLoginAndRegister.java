package vista;

import clases.Button;
import clases.MyPasswordField;
import clases.MyTextField;
import clases.Usuario;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import excepciones.VentanaError;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import modelo.DAO;
import net.miginfocom.swing.MigLayout;
import utilidades.Utilidades;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

//public class PanelLoginAndRegister extends javax.swing.JFrame {
    private MyTextField txtUsuario;
    private MyTextField txtEmail;
    private MyPasswordField txtContrasenia;
    private MyTextField txtTelefono;
    private MyTextField txtDni;
    private MyTextField txtFecha;
    MyTextField txtUsuarioReg;
    MyPasswordField txtContraseniaReg;

    private Usuario us;
    private DAO dao;
    private String error = "";
    private Conector conector;

    public PanelLoginAndRegister(Conector conector, DAO dao) {
        initComponents();
        initRegister();
        initLogin();
        this.conector = conector;
        login.setVisible(false);
        register.setVisible(true);
        this.dao = dao;
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]25[]push"));

        JLabel label = new JLabel("Crear Cuenta");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(49, 51, 53));
        register.add(label);

        txtUsuario = new MyTextField();
        txtUsuario.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/user.png")));
        txtUsuario.setHint("Usuario");
        register.add(txtUsuario, "w 60%");

        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");

        txtContrasenia = new MyPasswordField();
        txtContrasenia.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/pass.png")));
        txtContrasenia.setHint("Contaseña");
        register.add(txtContrasenia, "w 60%");

        txtTelefono = new MyTextField();
        txtTelefono.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/tf.png")));
        txtTelefono.setHint("Teléfono");
        register.add(txtTelefono, "w 60%");

        txtDni = new MyTextField();
        txtDni.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/dni2.png")));
        txtDni.setHint("Dni");
        register.add(txtDni, "w 60%");

        txtFecha = new MyTextField();
        txtFecha.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/tf.png")));
        txtFecha.setHint("Fecha de nacimiento");
        register.add(txtFecha, "w 60%");

        Button cmd = new Button();
        cmd.setBackground(new Color(49, 51, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTRARSE");
        cmd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    registrarse();
                } catch (ErrVariados ex) {
                    ErrVariados er = new ErrVariados("");
                } catch (ErrInsert ex) {
                    ErrInsert er = new ErrInsert("Usuario");
                } catch (ErrSelect ex) {
                    ErrSelect er = new ErrSelect("Usuario");
                }

            }
        });
        register.add(cmd, "w 40%, h 40");

    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));

        JLabel label = new JLabel("Iniciar sesión");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(49, 51, 53));
        login.add(label);

        txtUsuarioReg = new MyTextField();
        txtUsuarioReg.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/user.png")));
        txtUsuarioReg.setHint("Usuario");
        txtUsuarioReg.setText("nárdiz");
        login.add(txtUsuarioReg, "w 60%");

        txtContraseniaReg = new MyPasswordField();
        txtContraseniaReg.setPrefixIcon(new ImageIcon(getClass().getResource("/imagenes/pantalla/pass.png")));
        txtContraseniaReg.setHint("Contraseña");
        txtContraseniaReg.setText("1234");
        login.add(txtContraseniaReg, "w 60%");

        JButton cmdForget = new JButton("Recuperar contraseña");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setBorder(null);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);

        Button cmd = new Button();
        cmd.setBackground(new Color(49, 51, 53));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("INICIAR SESIÓN");
        cmd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    iniciarSesion();
                } catch (ErrVariados ex) {
                    ErrVariados er = new ErrVariados("");
                } catch (ErrSelect ex) {
                    ErrSelect er = new ErrSelect("Usuario");
                }
            }

        });
        login.add(cmd, "w 40%, h 40");
    }

    private void iniciarSesion() throws ErrVariados, ErrSelect {
        error = "";
        boolean bien = false;

        if (txtUsuarioReg.getText().isEmpty()) {
            error += "El usuario está vacio\n";
            txtUsuarioReg.setBackground(new Color(208, 56, 24));

        } else {
            txtUsuarioReg.setBackground(new Color(179, 231, 77));
            bien = true;
        }

        if (txtContraseniaReg.getText().isEmpty()) {
            error += "La contraseña está vacia\n";
            txtContraseniaReg.setBackground(new Color(208, 56, 24));

        } else {
            bien = true;
        }

        if (bien) {
            us = dao.iniciarSesion(txtUsuarioReg.getText(), txtContraseniaReg.getText());

            System.out.println(us.toString());

            if (us.getUsuario() != null) {
                txtUsuarioReg.setBackground(new Color(0, 0, 0, 0));
                txtContraseniaReg.setBackground(new Color(0, 0, 0, 0));
                conector.setOpacity(0);
                ParaTi parati = new ParaTi(conector, this, true, dao, us);
                parati.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "El usuario y la contraseña no coinciden", "ERROR", 0);
                txtContraseniaReg.setBackground(new Color(208, 56, 24));
            }

        } else {
            VentanaError ve = new VentanaError(error);
        }
    }

    private void registrarse() throws ErrVariados, ErrInsert, ErrSelect {
        error = comprobarDatosUsuario();
        if (error == "") {
            Usuario us = new Usuario();
            us.setUsuario(txtUsuario.getText());
            us.setContrasenia(txtContrasenia.getText());
            us.setDni(txtDni.getText());
            us.setCorreo(txtEmail.getText());
            us.setTelefono(Integer.parseInt(txtTelefono.getText()));
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate fecha = LocalDate.parse(txtFecha.getText(), formateador);
            us.setFecha_nac(fecha);
            us.setNumSeguidores(Utilidades.numeros_aleatorios(0, 5000000));
            us.setNumSeguidos(Utilidades.numeros_aleatorios(0, (int) (us.getNumSeguidores() * 0.3)));

            if (us.getNumSeguidores() > 4500000) {
                us.setVerificado(true);
            } else {
                us.setVerificado(false);
            }

            if (dao.registrar(us)) {
                VentanaError ve = new VentanaError("Registro correctamente hecha!!!");
                limpiar();

            } else {
                VentanaError ve = new VentanaError("Registro no completado!!!");
            }

        } else {
            VentanaError ve = new VentanaError(error);
        }
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables

    private String comprobarDatosUsuario() throws ErrVariados, ErrSelect {
        String error = "";

        if (txtUsuario.getText().length() > 20 || txtUsuario.getText().length() == 0) {
            error += "El usuario no es válido.\n";
            txtUsuario.setBackground(new Color(233, 0, 0));
        } else if (txtUsuario.getText().length() > 0) {
            txtUsuario.setBackground(new Color(142, 246, 86));
        }

        if (txtContrasenia.getText().length() > 20 || txtContrasenia.getText().length() == 0) {
            error += "La contraseña es demasiado larga.\n";
            txtContrasenia.setBackground(new Color(233, 0, 0));
        } else if (txtContrasenia.getText().length() > 0) {
            txtContrasenia.setBackground(new Color(142, 246, 86));
        }

        Pattern patron = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
        Matcher mat = patron.matcher(txtDni.getText());
        if (!mat.matches()) {
            error += "El dni no es válido.\n";
            txtDni.setBackground(new Color(233, 0, 0));
        } else {
            txtDni.setBackground(new Color(142, 246, 86));
        }

        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(txtEmail.getText());

        if (!matcher.matches()) {
            error += "El correo no es válido. \n";
            txtEmail.setBackground(new Color(233, 0, 0));
        } else {
            txtEmail.setBackground(new Color(142, 246, 86));
        }

        //Pattern patternTelefono = Pattern.compile("^(\+34|0034|34)?[6|7|9][0-9]{8}$");
        //Matcher matcher2 = pattern.matcher(txtTelefono.getText());
        if (txtTelefono.getText().matches("[0-9]{0,9}")) {
            error += "El teléfono no es válido.\n";
            txtTelefono.setBackground(new Color(233, 0, 0));
        } else {
            txtTelefono.setBackground(new Color(142, 246, 86));
        }

        error = comprobarUsuario(error, txtUsuario.getText(), txtEmail.getText(), txtTelefono.getText(), txtDni.getText());

        return error;
    }

    private void limpiar() {
        txtUsuario.setText("");
        txtEmail.setText("");
        txtContrasenia.setText("");
        txtTelefono.setText("");
        txtDni.setText("");
    }

    private String comprobarUsuario(String error, String usuario, String email, String tlf, String dni) throws ErrVariados, ErrSelect {
        List<Usuario> usuarios = dao.listarUsuario();

        boolean salir = false;

        for (int i = 0; i < usuarios.size(); i++) {

            if (usuarios.get(i).getUsuario().equalsIgnoreCase(usuario)) {
                error += "Ya existe una cuenta con ese nombre \n";
                txtTelefono.setBackground(new Color(233, 0, 0));
                salir = true;
            }

            if (usuarios.get(i).getCorreo().equalsIgnoreCase(email)) {
                error += "Ya existe una cuenta con ese correo \n";
                txtTelefono.setBackground(new Color(233, 0, 0));
                salir = true;
            }

            String tf = usuarios.get(i).getTelefono() + "";

            if (tf.equalsIgnoreCase(tlf)) {
                error += "Ya existe una cuenta con ese teléfono \n";
                txtTelefono.setBackground(new Color(233, 0, 0));
                salir = true;
            }

            if (usuarios.get(i).getDni().equalsIgnoreCase(dni)) {
                error += "Ya existe una cuenta con ese dni \n";
                txtDni.setBackground(new Color(233, 0, 0));
                salir = true;
            }

            if (salir) {
                i = usuarios.size();
            }
        }

        return error;

    }

}
