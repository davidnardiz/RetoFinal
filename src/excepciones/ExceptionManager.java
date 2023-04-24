package excepciones;

import javax.swing.JOptionPane;

public class ExceptionManager {

    public void SQLException(Exception e) {

    }

    public void NullPoineterException(Exception e) {
        JOptionPane.showMessageDialog(null, "Error al cargar la imagen", "Ruta no encontrada", 2);
        e.printStackTrace();

    }
}
