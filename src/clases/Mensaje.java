package clases;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author bayro
 */
public class Mensaje {

    private String idMensaje;
    private LocalDate fechaEnvio;
    private String mensaje;
    private String usuario1;
    private String usuario2;

    public String getIdMensaje() {
        return idMensaje;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    /**
     *
     * @return Devuelve todos los atributos del mensaje
     */
    public void getDatos() {
        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println(mensaje);
    }
}
