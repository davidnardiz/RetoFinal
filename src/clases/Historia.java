package clases;

public class Historia extends Publicacion {

    private String id_publicacion;
    private boolean mejores_amigos;
    private String cod_tipo;

    /**
     *
     * @return Devuelve todos los atributos de la historia
     */
    @Override
    public String toString() {
        return super.toString() + "Historia [id_publicacion=" + id_publicacion + ", mejores_amigos=" + mejores_amigos + ", cod_tipo="
                + cod_tipo + "]";
    }

    //GETTER && SETTER
    public String getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(String id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public boolean isMejores_amigos() {
        return mejores_amigos;
    }

    public void setMejores_amigos(boolean mejores_amigos) {
        this.mejores_amigos = mejores_amigos;
    }

    public String getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(String cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    /**
     * ******** METODOS *********
     */
    /**
     * ******** CONSTRUCOR *********
     */
    /**
     * ******** GETTER && SETTER *********
     */
}
