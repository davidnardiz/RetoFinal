package clases;

import java.time.LocalDate;

public class Usuario {

    private String usuario;
    private String contrasenia;
    private String codGmail;
    private String dni;
    private String correo;
    private int telefono;
    private String icono;
    private LocalDate fecha_nac;
    private int numSeguidores;
    private int numSeguidos;
    private boolean verificado;

    /**
     * ******** METODOS *********
     */
    @Override
    public String toString() {
        return "Usuario [usuario=" + usuario + ", contrasenia=" + contrasenia + ", dni=" + dni + ", codGmail="
                + codGmail + ", correo=" + correo + ", telefono=" + telefono + ", icono="
                + icono + ", fecha_nac=" + fecha_nac + ", numSeguidores=" + numSeguidores + ", numSeguidos="
                + numSeguidos + ", verificado=" + verificado + "]";
    }

    /**
     * ******** CONSTRUCTOR *********
     */
    /**
     * ******** GETTER && SETTER *********
     */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCodGmail() {
        return codGmail;
    }

    public void setCodGmail(String codGmail) {
        this.codGmail = codGmail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public int getNumSeguidores() {
        return numSeguidores;
    }

    public void setNumSeguidores(int numSeguidores) {
        this.numSeguidores = numSeguidores;
    }

    public int getNumSeguidos() {
        return numSeguidos;
    }

    public void setNumSeguidos(int numSeguidos) {
        this.numSeguidos = numSeguidos;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

}
