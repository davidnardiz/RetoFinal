package excepciones;

/**
 *
 * @author arceu
 */
public class ErrSelect extends Exception {

    private String mensaje = "";

    /**
     * Genera una excepcion para los errores de seleccion
     *
     * @param tabla Es la tabla donde esta el fallo para generar asi el mensaje
     */
    public ErrSelect(String tabla) {

        switch (tabla) {
            case "Usuario":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de recuperar datos sobre algun usuario";
            case "Publicacion":
                mensaje = "Ha ocurrido un error a la hora de recuperar una publicacion";
                break;
            case "Like":
                mensaje = "Ha ocurrido un error a la hora de visualizar los likes";
                break;
            case "Cancion":
                mensaje = "Ha ocurrido un error a la hora de visualizar las canciones";
                break;
            case "Historia":
                mensaje = "Ha ocurrido un error a la hora de visualizar los tipos de historias";
                break;
            case "Guardar":
                mensaje = "Ha ocurrido un error a la hora de recuperar informacion de las publicaciones guardadas";
                break;
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inesperado a la hora de recuperar datos de la base de datos";
        }
    }

    /**
     * Este metodo muestra la ventana del error
     */
    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }
}
