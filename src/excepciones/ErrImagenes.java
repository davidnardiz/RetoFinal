package excepciones;

public class ErrImagenes extends Exception {

    public ErrImagenes(String tabla) {
        String mensaje = "";

        switch (tabla) {
            case "ParaTi":
                mensaje = "Lo sentimos, no se ha podido encontrar la imagen de esta publicacion";
                break;
            case "Buscar":
            case "Perfil":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta del icono";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, no se ha podido encontrar la imagen de este articulo";
                break;
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de encontrar la ruta del icono";
                break;
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inesperado al cargar una imagen";
        }

        VentanaError vent = new VentanaError(mensaje);
    }
}
