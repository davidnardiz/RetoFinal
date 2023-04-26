package excepciones;

import javax.swing.JDialog;
import utilidades.VentanasError.GlassPanePopup;

public class ErrDelete extends Exception {

    public void mostrarError(JDialog ubicacion) {
        String mensaje = "";

        switch (ubicacion.getName()) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar tu like de la base de datos";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar este articulo de la base de datos";
                break;
            case "Perfil":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar tu perfil";
                break;
            case "BloquearDesbloquear":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de desbloquear un usuario";
                break;
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inseperado a la hora de eliminar algo de la base de datos";

        }

        GlassPanePopup.showPopup(new VentanaError(mensaje));

    }
}
