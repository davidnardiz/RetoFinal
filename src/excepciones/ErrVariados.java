package excepciones;

public class ErrVariados extends Exception {

    public ErrVariados(String razon) {

        String mensaje = "";

        switch (razon) {
            case "Fichero":
                mensaje = "Lo sentimos, no se ha encontrado el fichero de configuracion";
                break;
            case "Logeo":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de conectarse con la base de datos";
            default:
                mensaje = "Lo sentimos, no se ha encontrado el fichero";
        }

        VentanaError vent = new VentanaError(mensaje);
    }

}
