package excepciones;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ErrInsert extends Exception {

    public ErrInsert(String ubicacion) {
        String mensaje;

        switch (ubicacion) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, error a la hora de insertar tu like en la base de datos";
                break;
            case "Subir":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de publicar la imagen";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error al guardar el articulo";
                break;
            case "BloquearDesbloquear":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de bloquear al usuario";
                break;
            default:
                mensaje = "Ha ocurrido un error inesperado a la hora de insertar algo la base de datos";
        }

        VentanaError vent = new VentanaError(mensaje);
    }

}
