package clases;

<<<<<<< HEAD
public class Foto extends Publicacion{

	private String id_publicacion;
	private String descripcion;
	private String resolucion;
	private String etiquetado;

	/********** METODOS **********/
	@Override
	public String toString() {
		return super.toString() + "Foto [id_publicacion=" + id_publicacion + ", descripcion=" + descripcion + ", resolucion=" + resolucion
				+ ", etiquetado=" + etiquetado + "]";
	}

	/********** CONSTRUCTOR **********/
	/********** GETTER && SETTER **********/

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
=======
public class Foto extends Publicacion {

    public Foto() {
    }

    private String id_publicacion;
    private String descripcion;
    private String resolucion;
    private String etiquetado;

    /**
     * ******** METODOS *********
     */
    @Override
    public String toString() {
        return super.toString() + "Foto [id_publicacion=" + id_publicacion + ", descripcion=" + descripcion + ", resolucion=" + resolucion
                + ", etiquetado=" + etiquetado + "]";
    }

    /**
     * ******** CONSTRUCTOR *********
     */
    /**
     * ******** GETTER && SETTER *********
     */
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
>>>>>>> 97faa5ca3ce0112520bfe7b3a88b1547e52b5ab3

}
