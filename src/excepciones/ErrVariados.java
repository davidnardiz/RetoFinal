package excepciones;

/**
 *
 * @author Jason
 */
public class ErrVariados extends Exception {

    private String mensaje;

    /**
     * Genera una excepcion para los errores de variados
     *
     * @param tabla Es la tabla donde esta el fallo para generar asi el mensaje
     */
    public ErrVariados(String razon) {

        switch (razon) {
            case "Fichero":
                mensaje = "Lo sentimos, no se ha encontrado el fichero de configuracion";
                break;
            case "LeerFichero":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de leer lo que hay dentro del fichero";
                break;
            case "ConexionBDA":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de conectarse con la base de datos";
                break;
            case "Imagen":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de cargar una imagen";
                break;
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inesperado";
        }
    }

    /**
     * Este metodo muestra la ventana del error
     */
    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }
}
