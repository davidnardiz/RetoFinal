package excepciones;

import javax.swing.JDialog;
import utilidades.VentanasError.GlassPanePopup;
import vista.ParaTi;

public class ErrImagenes extends Exception {

    public void mostrarError(JDialog ubicacion) {
        String mensaje = "";

        switch (ubicacion.getName()) {
            case "ParaTi":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta de la imagen de la publicacion";
                ((ParaTi) ubicacion).siguienteFoto();
                break;
            case "Buscar":
            case "Perfil":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta del icono";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta de la imagen de el articulo";
                break;
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta del icono";
                break;
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inesperado al cargar una imagen";
        }

        GlassPanePopup.showPopup(new VentanaError((mensaje)));
    }
}
