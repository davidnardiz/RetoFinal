package clases;

public class Reel extends Publicacion{

	private String id_publicacion;
	private String descripcion;
	private int duracion;
	private int reproducciones;

	/********** METODOS **********/
	@Override
	public String toString() {
		return "Reel [id_publicacion=" + id_publicacion + ", descripcion=" + descripcion + ", duracion=" + duracion
				+ ", reproducciones=" + reproducciones + "]";
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}

}
