package excepciones;

/**
 *
 * @author arceu
 */
public class ErrInsert extends Exception {

    private String mensaje;

    /**
     * Genera una excepcion para los errores de insercion
     *
     * @param tabla Es la tabla donde esta el fallo para generar asi el mensaje
     */
    public ErrInsert(String tabla) {

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
    }

    /**
     * Este metodo muestra la ventana del error
     */
    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }

}
