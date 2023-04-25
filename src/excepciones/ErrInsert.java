package excepciones;

import javax.swing.JOptionPane;

public class ErrInsert extends Exception {

    public void mostrarError() {
        JOptionPane.showMessageDialog(null, "Error al introducir datos en la BDA", "Error", 2);
    }

}
