package excepciones;

public class ErrDelete extends Exception {

    public ErrDelete(String ubicacion) {
        String mensaje = "";

        switch (ubicacion) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar tu like";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar este articulo";
                break;
            case "Perfil":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar tu perfil";
                break;
            case "BloquearDesbloquear":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de desbloquear un usuario";
                break;
            default:
                mensaje = "Ha ocurrido un error inseperado a la hora de eliminar algo de la base de datos";

        }

        VentanaError vent = new VentanaError(mensaje);
    }
}
