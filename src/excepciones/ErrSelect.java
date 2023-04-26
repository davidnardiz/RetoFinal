package excepciones;

public class ErrSelect extends Exception {

    public ErrSelect(String ubicacion) {
        String mensaje = "";
        switch (ubicacion) {
            case "ParaTi":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de recuperar datos de la base de datos";
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de recuperar datos de la base de datos";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de recuperar datos de la base de datos";
            case "Configuracion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de recuperar datos de la base de datos";
            default:
                mensaje = "Lo sentimos, ha ocurrido un error inesperado a la hora de recuperar datos de la base de datos";
        }

        VentanaError vent = new VentanaError(mensaje);
    }
}
