package excepciones;

/**
 *
 * @author arceu
 */
public class ErrAlter extends Exception {

    private String mensaje;

    /**
     * Genera una excepcion para los errores de modificacion
     *
     * @param tabla Es la tabla donde esta el fallo para generar asi el mensaje
     */
    public ErrAlter(String tabla) {

        switch (tabla) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modificar el numero de likes";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar este articulo";
            case "Configuracion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar tu perfil";
            default:
                mensaje = "Ha ocurrido un error inseperado al modificar algo en la base de datos";
        }
    }

    /**
     * Este metodo muestra la ventana del error
     */
    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }

}
