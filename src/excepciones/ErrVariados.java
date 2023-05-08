package excepciones;

public class ErrVariados extends Exception {

    private String mensaje;

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

    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }
}
