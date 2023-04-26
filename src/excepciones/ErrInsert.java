package excepciones;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ErrInsert extends Exception {

    public void mostrarError(JDialog ubicacion) {
        String mensaje;

        switch (ubicacion.getName()) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, error a la hora de insertar tu like en la base de datos";
                break;
            case "Subir":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de publicar la imagen";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error al insertar el articulo en la base de datos";
                break;
            case "BloquearDesbloquear":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de bloquear al usuario";
                break;
            default:
                throw new AssertionError();
        }
    }

}
