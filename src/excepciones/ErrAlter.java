package excepciones;

import utilidades.VentanasError.GlassPanePopup;

public class ErrAlter extends Exception {

    public void mostrarError(String ubicacion) {
        String mensaje = "";
        switch (ubicacion) {
            case "Publicacion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modificar la publicacion";
                break;
            case "Perfil":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar tu perfil";
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inseperado a la hora de modificar datos";
        }

        GlassPanePopup.showPopup(new VentanaError(mensaje));
    }

}
