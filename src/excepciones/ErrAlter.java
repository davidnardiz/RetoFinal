package excepciones;

public class ErrAlter extends Exception {

    private String mensaje;

    public ErrAlter(String tabla) {

        switch (tabla) {
            case "ParaTi":
            case "PublicacionPopUp":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modificar el numero de likes";
                break;
            case "Tienda":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar este articulo";
            case "Configuracion":
                mensaje = "Lo sentimos, ha ocurrido un error a la hora de modifcar tu perfil";
            default:
                mensaje = "Ha ocurrido un error inseperado al modificar algo en la base de datos";
        }
    }

    public void mostrarError() {
        VentanaError vent = new VentanaError(mensaje);
    }

}
