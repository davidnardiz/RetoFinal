package excepciones;

import javax.swing.JDialog;
import utilidades.VentanasError.GlassPanePopup;

public class ErrAlter extends Exception {

    public void mostrarError(JDialog ubicacion) {
        String mensaje = "";
        switch (ubicacion.getName()) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modificar el numero de likes en esta publicacion";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar los atributos de este articulo";
            case "Configuracion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar tu perfil";
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inseperado a la hora de modificar algo en la base de datos";
        }

        GlassPanePopup.showPopup(new VentanaError(mensaje));
    }

}
