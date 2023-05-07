package excepciones;

public class ErrVariados extends Exception {

    public ErrVariados(String razon) {

        String mensaje = "";

        switch (razon) {
            case "Fichero":
                mensaje = "Lo sentimos, no se ha encontrado el fichero de configuracion";
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

        VentanaError vent = new VentanaError(mensaje);
    }

}
