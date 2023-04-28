package excepciones;

public class ErrDelete extends Exception {

    public ErrDelete(String tabla) {
        String mensaje = "";

        switch (tabla) {
            case "Publicacion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar la publicacion";
                break;
            case "Usuario":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de eliminar tu cuenta";
                break;
            case "Like":
                mensaje = "Lo sentimos, ha ocurrido un error a la quitar el like";
                break;
            case "Sigue":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de dejar de seguir a este usuario";
                break;
            case "Bloqueado":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de desbloquear a este usuario";
                break;
            case "Guardar":
                mensaje = "Lo sentimos, ha ocurrido un error al dejar de guardar esta publicacion";
                break;
            default:
                mensaje = "Ha ocurrido un error inseperado a la hora de eliminar algo de la base de datos";

        }

        VentanaError vent = new VentanaError(mensaje);
    }
}
