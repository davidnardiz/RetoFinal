/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import clases.Publicacion;
import clases.Usuario;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.DAO;

public class PublicacionesEtiquetadas extends javax.swing.JDialog {

    private List<Publicacion> publicacionesList;
    private Usuario usuarioPerfil;
    private Usuario nosotros;
    private VMain vMain;
    private ParaTi paraTi;
    private Perfil perfil;
    private DAO dao;

    public PublicacionesEtiquetadas(VMain vMain, boolean modal, DAO dao, Publicacion publi, Usuario usu, Usuario usuarioPerfil1, JDialog parent) {
        super(vMain, modal);
        this.nosotros = usu;
        this.vMain = vMain;
        this.perfil = perfil;
        this.dao = dao;
        this.usuarioPerfil = usuarioPerfil1;

        initComponents();
        getContentPane().setBackground(new Color(49, 51, 53));
        getContentPane().setBounds(80000, 2000, 668, 491);
        this.setLocationRelativeTo(null);

        btnVolver.setBackground(Color.white);
        btnVolver.setBorder(null);

        try {
            publicacionesList = dao.listarPublicacionesEtiquetadas(usuarioPerfil.getUsuario());
            cargarTabla(publicacionesList);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        franjaArriba = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblLogoLetras = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        tablaPublicaciones = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        franjaArriba.setBackground(new java.awt.Color(43, 45, 47));
        franjaArriba.setPreferredSize(new java.awt.Dimension(648, 80));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pantalla/logoPequeño.png"))); // NOI18N

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll.setFocusable(false);
        scroll.setPreferredSize(new java.awt.Dimension(594, 351));
        scroll.setRequestFocusEnabled(false);

        tablaPublicaciones.setBackground(getBackground());
        tablaPublicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPublicaciones.setFillsViewportHeight(true);
        tablaPublicaciones.setFocusable(false);
        tablaPublicaciones.setRequestFocusEnabled(false);
        tablaPublicaciones.setRowHeight(349);
        tablaPublicaciones.setRowSelectionAllowed(false);
        tablaPublicaciones.setShowGrid(false);
        tablaPublicaciones.setTableHeader(null);
        tablaPublicaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPublicacionesMouseClicked(evt);
            }
        });
        scroll.setViewportView(tablaPublicaciones);

        btnVolver.setText("Volver");
        btnVolver.setFocusPainted(false);
        btnVolver.setFocusable(false);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(franjaArriba, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(franjaArriba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPublicacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPublicacionesMouseClicked
        int fila = tablaPublicaciones.rowAtPoint(evt.getPoint());
        int columna = tablaPublicaciones.columnAtPoint(evt.getPoint());

        try {
            abrirFoto(tablaPublicaciones.getValueAt(fila, columna).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            //Clickas en la tercera celda de la tabla y al haber menos imagenes da fallo;
            //El fallo no repercute al codigo por lo que no quiero gestionarlo ni avisar
        } catch (NullPointerException e) {
        }
    }//GEN-LAST:event_tablaPublicacionesMouseClicked

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
        perfil.setVisible(true);

    }//GEN-LAST:event_btnVolverActionPerformed

    private void cargarTabla(List<Publicacion> publicacionesList) {
        DefaultTableModel modelo = (DefaultTableModel) tablaPublicaciones.getModel();
        modelo.setRowCount(0);

        TableColumnModel columnModel = tablaPublicaciones.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(1).setCellRenderer(new ImageRenderer());
        columnModel.getColumn(2).setCellRenderer(new ImageRenderer());

        String rutaProyecto = System.getProperty("user.dir");
        for (int i = 0; i < publicacionesList.size(); i = i + 3) {
            Object[] fila = new Object[3];
            fila[0] = new ImageIcon(
                    rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i).getImagen());

            if (publicacionesList.size() > i + 1) {
                fila[1] = new ImageIcon(
                        rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 1).getImagen());
            } else {
                fila[1] = null;
            }

            if (publicacionesList.size() > i + 2) {
                fila[2] = new ImageIcon(
                        rutaProyecto + "\\src\\imagenes\\publicaciones\\" + publicacionesList.get(i + 2).getImagen());
            } else {
                fila[2] = null;
            }

            modelo.addRow(fila);

        }

    }

    private void abrirFoto(String foto) {
        String rutaProyecto = System.getProperty("user.dir");
        Publicacion publi = null;
        try {
            for (Publicacion i : publicacionesList) {
                if (foto.equalsIgnoreCase(rutaProyecto + "\\src\\imagenes\\publicaciones\\" + i.getImagen())) {
                    publi = dao.buscarPublicacionXId(i.getId_publicacion());
                    usuarioPerfil = dao.buscarUsuario(i.getUsuario());
                    break;

                }
            }

            PublicacionPopUp publiPop = new PublicacionPopUp(vMain, true, dao, publi, nosotros, usuarioPerfil, perfil);
            publiPop.setVisible(true);

        } catch (ErrVariados ex) {
            ex.mostrarError();
        } catch (ErrSelect ex) {
            ex.mostrarError();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JPanel franjaArriba;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoLetras;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaPublicaciones;
    // End of variables declaration//GEN-END:variables
}
