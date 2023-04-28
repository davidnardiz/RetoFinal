package excepciones;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class ErrInsert extends Exception {

    public ErrInsert(String tabla) {
        String mensaje;

        switch (tabla) {
            case "Publicacion":
                mensaje = "Lo sentimos, error a la hora de subir esta publicacion";
                break;
            case "Usuario":
                mensaje = "Lo sentimos, error a la hora de registrarse";
                break;
            case "Like":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de darle like";
                break;
            case "Sigue":
                mensaje = "Lo sentimos, ha ocurrido un error al seguir a este usuario";
                break;
            case "Bloquear":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de bloquear al usuario";
                break;
            case "Guardar":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de guardar esta publicacion";
                break;
            default:
                mensaje = "Ha ocurrido un error inesperado a la hora de insertar algo la base de datos";
        }

        VentanaError vent = new VentanaError(mensaje);
    }

}
