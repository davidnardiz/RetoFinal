package excepciones;

public class ErrAlter extends Exception {

    public ErrAlter(String ubicacion) {
        String mensaje = "";
        switch (ubicacion) {
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

        VentanaError vent = new VentanaError(mensaje);
    }

}
