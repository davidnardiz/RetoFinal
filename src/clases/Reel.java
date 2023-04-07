package clases;

public class Reel extends Publicacion{

	private String id_publicacion;
	private int duracion;
	private int reproducciones;

	/********** METODOS **********/
	@Override
	public String toString() {
		return super.toString() + "Reel [id_publicacion=" + id_publicacion + ", duracion=" + duracion + ", reproducciones="
				+ reproducciones + "]";
	}

	/********** CONSTRUCTOR **********/
	/********** GETTER && SETTER **********/

	public String getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(String id_publicacion) {
		this.id_publicacion = id_publicacion;
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
