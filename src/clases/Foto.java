package clases;

/**
 *
 * @author arceu
 */
public class Foto extends Publicacion {

    private String id_publicacion;
    private String descripcion;
    private String resolucion;
    private String etiquetado;

    /**
     *
     * @return Devuelve todos los atributos de la foto
     */
    @Override
    public String toString() {
        return super.toString() + "Foto [id_publicacion=" + id_publicacion + ", descripcion=" + descripcion + ", resolucion=" + resolucion
                + ", etiquetado=" + etiquetado + "]";
    }

//Getters setters
    public String getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(String id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getEtiquetado() {
        return etiquetado;
    }

    public void setEtiquetado(String etiquetado) {
        this.etiquetado = etiquetado;
    }

}
