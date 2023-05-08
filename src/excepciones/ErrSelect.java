package excepciones;

public class ErrSelect extends Exception {

    private String mensaje = "";

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

    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }
}
