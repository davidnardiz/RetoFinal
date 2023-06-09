package vista;

import clases.Usuario;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.DAO;
import utilidades.Filtros_Buscador.SearchOptinEvent;
import utilidades.Filtros_Buscador.SearchOption;

/**
 *
 * @author Jason
 */
public class Buscar extends javax.swing.JDialog {

    private DAO dao;
    private Usuario usu;
    private ParaTi paraTi;
    private List<Usuario> usuariosList;
    private VMain vMain;

    private boolean conver;

    /**
     * Genera la pantalla para mostrar usuarios
     *
     * @param vMain Es la pantalla padre
     * @param modal Es si la pantalla es modal o si np
     * @param dao Es la interfaz de la logica de negocio
     * @param usu Es el usuario que controla la aplicacion
     * @param par1 Es si vienes quieres abrir una conversacion
     */
    public Buscar(VMain vMain, boolean modal, DAO dao, Usuario usu, boolean par1) {
        super(vMain, modal);
        this.setModal(modal);
        this.dao = dao;
        this.usu = usu;
        this.vMain = vMain;
        this.conver = par1;

        try {
            this.usuariosList = dao.listarUsuario();
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }

        setTitle("Buscar");
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/pantalla/logo.png")).getImage());
        getContentPane().setBackground(new Color(49, 51, 53));
        buscador = new utilidades.Filtros_Buscador.TextFieldSearchOption();
        initComponents();

        setLocationRelativeTo(null);

        buscador.addEventOptionSelected(new SearchOptinEvent() {
            @Override
            public void optionSelected(SearchOption option, int index) {
                buscador.setHint("Buscar por " + option.getName() + "...");
            }

        });

        String rutaProyecto = System.getProperty("user.dir");

        buscador.addOption(new SearchOption("usuario", new ImageIcon(rutaProyecto + "\\src\\utilidades\\Filtros_Buscador\\user.png")));
        buscador.addOption(new SearchOption("verificado", new ImageIcon(rutaProyecto + "\\src\\imagenes\\pantalla\\verificado.png")));
        buscador.addOption(new SearchOption("mejos", new ImageIcon(rutaProyecto + "\\src\\imagenes\\pantalla\\mejoresAmigosBuscar.png")));
        buscador.addOption(new SearchOption("seguidores", new ImageIcon(rutaProyecto + "\\src\\utilidades\\Filtros_Buscador\\address.png")));
        buscador.setSelectedIndex(0);
        cargarTabla(usuariosList);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrar();
            }

        });
    }

    /**
     * Carga la tabla
     *
     * @param usuariosList Son los usuarios que quieres que aparezcan en la
     * tabla
     * @throws NullPointerException Pueden haber errores a la hora de cargar
     * imagenes
     */
    private void cargarTabla(List<Usuario> usuariosList) throws NullPointerException {
        DefaultTableModel modelo = (DefaultTableModel) tablaUsuarios.getModel();
        modelo.setRowCount(0);

        TableColumnModel columnModel = tablaUsuarios.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setResizable(true);
        columnModel.getColumn(1).setPreferredWidth(-1);

        String rutaProyecto = System.getProperty("user.dir");
        for (int i = 0; i < usuariosList.size(); i++) {
            Object[] fila = new Object[4];

            fila[0] = new ImageIcon(rutaProyecto + "\\src\\imagenes\\iconos\\" + usuariosList.get(i).getIcono());
            if (usuariosList.get(i).isVerificado()) {
                fila[1] = new ImageIcon(rutaProyecto + "\\src\\imagenes\\pantalla\\verificado.png");
            }
            fila[2] = usuariosList.get(i).getUsuario();
            fila[3] = usuariosList.get(i).getNumSeguidores() + " ";

            modelo.addRow(fila);

        }

    }

    /**
     * Al clickar en un usuario se abrira su perfil
     *
     * @param usuario Es el usuario cuyo perfil quieres abrir
     */
    private void buscarUsuario(String usuario) {
        try {
            Usuario perf = dao.buscarUsuario(usuario);
            Perfil perfil = new Perfil(vMain, true, dao, usu, perf);
            this.setVisible(false);
            perfil.setVisible(true);
        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    /**
     * Ese metodo permite cambiar el criterio de busqueda
     */
    private void cambiarFiltro() {
        if (buscador.isSelected()) {
            try {
                int opcion = buscador.getSelectedIndex();

                switch (opcion) {
                    case 0:
                        usuariosList = dao.listarUsuarioXUsuario(buscador.getText());
                        break;
                    case 1:
                        usuariosList = dao.listarUsuariosVerificados(buscador.getText());
                        break;
                    case 2:
                        usuariosList = dao.listarUsuarioXMejos(buscador.getText());
                        break;
                    case 3:
                        usuariosList = dao.listarUsuariosXSeguidores(buscador.getText());
                    default:

                }
                cargarTabla(usuariosList);
            } catch (ErrVariados ex) {
                ex.mostrarError();
            } catch (ErrSelect ex) {
                ex.mostrarError();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        franajAbajo = new javax.swing.JPanel();
        btnParaTi = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        btnTienda = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        lblBuscadorText = new javax.swing.JLabel();
        buscador = new utilidades.Filtros_Buscador.TextFieldSearchOption();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(49, 51, 53));
        setModal(true);
        setName("paraTi"); // NOI18N
        setPreferredSize(new java.awt.Dimension(648, 864));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

        lblLogoLetras.setForeground(getBackground());
        lblLogoLetras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/letrasInstagram.png"))); // NOI18N
        lblLogoLetras.setPreferredSize(new java.awt.Dimension(50, 16));

        javax.swing.GroupLayout franjaArribaLayout = new javax.swing.GroupLayout(franjaArriba);
        franjaArriba.setLayout(franjaArribaLayout);
        franjaArribaLayout.setHorizontalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblLogo)
                .addGap(18, 18, 18)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        franjaArribaLayout.setVerticalGroup(
            franjaArribaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franjaArribaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblLogo)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, franjaArribaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogoLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(franjaArriba, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        franajAbajo.setBackground(new java.awt.Color(43, 45, 47));

        btnParaTi.setBackground(franjaArriba.getBackground());
        btnParaTi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/para ti.png"))); // NOI18N
        btnParaTi.setToolTipText("");
        btnParaTi.setAlignmentY(0.0F);
        btnParaTi.setAutoscrolls(true);
        btnParaTi.setBorder(null);
        btnParaTi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnParaTi.setFocusable(false);
        btnParaTi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnParaTi.setRolloverEnabled(false);
        btnParaTi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParaTiActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(franjaArriba.getBackground());
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/buscar.png"))); // NOI18N
        btnBuscar.setToolTipText("");
        btnBuscar.setAlignmentY(0.0F);
        btnBuscar.setAutoscrolls(true);
        btnBuscar.setBorder(null);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusable(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBuscar.setRolloverEnabled(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSubir.setBackground(franjaArriba.getBackground());
        btnSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/subir.png"))); // NOI18N
        btnSubir.setToolTipText("");
        btnSubir.setAlignmentY(0.0F);
        btnSubir.setAutoscrolls(true);
        btnSubir.setBorder(null);
        btnSubir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSubir.setFocusable(false);
        btnSubir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSubir.setRolloverEnabled(false);
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        btnTienda.setBackground(franjaArriba.getBackground());
        btnTienda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/tienda.png"))); // NOI18N
        btnTienda.setToolTipText("");
        btnTienda.setAlignmentY(0.0F);
        btnTienda.setAutoscrolls(true);
        btnTienda.setBorder(null);
        btnTienda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnTienda.setFocusable(false);
        btnTienda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTienda.setRolloverEnabled(false);
        btnTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiendaActionPerformed(evt);
            }
        });

        btnCuenta.setBackground(franjaArriba.getBackground());
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/cuenta.png"))); // NOI18N
        btnCuenta.setToolTipText("");
        btnCuenta.setAlignmentY(0.0F);
        btnCuenta.setAutoscrolls(true);
        btnCuenta.setBorder(null);
        btnCuenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCuenta.setFocusable(false);
        btnCuenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCuenta.setRolloverEnabled(false);
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout franajAbajoLayout = new javax.swing.GroupLayout(franajAbajo);
        franajAbajo.setLayout(franajAbajoLayout);
        franajAbajoLayout.setHorizontalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnParaTi)
                .addGap(66, 66, 66)
                .addComponent(btnBuscar)
                .addGap(64, 64, 64)
                .addComponent(btnSubir)
                .addGap(64, 64, 64)
                .addComponent(btnTienda)
                .addGap(66, 66, 66)
                .addComponent(btnCuenta)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        franajAbajoLayout.setVerticalGroup(
            franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(franajAbajoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(franajAbajoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCuenta)
                    .addComponent(btnBuscar)
                    .addComponent(btnParaTi)
                    .addComponent(btnSubir)
                    .addComponent(btnTienda))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(franajAbajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 725, -1, -1));

        scroll.setBackground(getBackground());
        scroll.setForeground(new java.awt.Color(255, 255, 255));
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setAutoscrolls(true);

        tablaUsuarios.setBackground(getBackground());
        tablaUsuarios.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
        tablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Icono", "Verificado", "Usuario", "Seguidores"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setFocusable(false);
        tablaUsuarios.setRowHeight(109);
        tablaUsuarios.setRowSelectionAllowed(false);
        tablaUsuarios.setShowGrid(false);
        tablaUsuarios.setShowGrid(false);
        tablaUsuarios.setTableHeader(null);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        scroll.setViewportView(tablaUsuarios);

        getContentPane().add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 156, 560, 522));
        scroll.setBorder(BorderFactory.createEmptyBorder());

        lblBuscadorText.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBuscadorText.setText("Buscador:");
        getContentPane().add(lblBuscadorText, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 109, -1, -1));

        buscador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscadorMouseClicked(evt);
            }
        });
        buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscadorKeyReleased(evt);
            }
        });
        getContentPane().add(buscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 100, 300, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Abre la ventana para ti
     *
     * @param evt
     */
    private void btnParaTiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParaTiActionPerformed
        ParaTi paraTi = new ParaTi(vMain, true, dao, usu);
        this.dispose();
        paraTi.setVisible(true);
    }//GEN-LAST:event_btnParaTiActionPerformed

    /**
     * Abre la ventana de buscar
     *
     * @param evt
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Buscar buscar = new Buscar(vMain, true, dao, usu, false);
        this.dispose();
        buscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Abre la ventana de subir
     *
     * @param evt
     */
    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        // TODO add your handling code here:
        Subir subir = new Subir(vMain, true, dao, usu, null);
        this.dispose();
        subir.setVisible(true);
    }//GEN-LAST:event_btnSubirActionPerformed

    /**
     * Abre la ventana de tienda
     *
     * @param evt
     */
    private void btnTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiendaActionPerformed
        Tienda tienda = new Tienda(vMain, true, dao, usu);
        this.dispose();
        tienda.setVisible(true);
    }//GEN-LAST:event_btnTiendaActionPerformed

    /**
     * Abre la ventana de perfil
     *
     * @param evt
     */
    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        Perfil perfil = new Perfil(vMain, true, dao, usu, usu);
        this.dispose();
        perfil.setVisible(true);
    }//GEN-LAST:event_btnCuentaActionPerformed

    /**
     * Cambia el filtro de busqueda
     *
     * @param evt
     */
    private void buscadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscadorMouseClicked
        cambiarFiltro();
    }//GEN-LAST:event_buscadorMouseClicked

    /**
     * Si se clicka en un usuario se abrira su perfil
     *
     * @param evt
     */
    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        int fila = tablaUsuarios.rowAtPoint(evt.getPoint());

        if (conver) {
            String otroUsu = tablaUsuarios.getValueAt(fila, 2).toString();
            PruebaChat chat = new PruebaChat(vMain, true, dao, usu, otroUsu);
            this.dispose();
            chat.setVisible(true);

        } else {
            try {
                buscarUsuario(tablaUsuarios.getValueAt(fila, 2).toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                //Clickas en la tercera celda de la tabla y al haber menos imagenes da fallo;
                //El fallo no repercute al codigo por lo que no quiero gestionarlo ni avisar
            } catch (NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    /**
     * Cambia el filtro de busqueda
     *
     * @param evt
     */
    private void buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscadorKeyReleased
        cambiarFiltro();
    }//GEN-LAST:event_buscadorKeyReleased

    /**
     * Este metodo cierra la ventana
     */
    private void cerrar() {
        this.dispose();
        vMain.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCuenta;
    private javax.swing.JButton btnParaTi;
    private javax.swing.JButton btnSubir;
    private javax.swing.JButton btnTienda;
    private utilidades.Filtros_Buscador.TextFieldSearchOption buscador;
    private javax.swing.JPanel franajAbajo;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblBuscadorText;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
