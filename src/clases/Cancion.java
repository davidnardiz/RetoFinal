package clases;

public class Cancion {

	private String id_cancion;
	private String titulo;
	private String artista;
	private float duracion;
	private String cod_genero;

	/********** METODOS **********/
	@Override
	public String toString() {
		return "Cancion [id_cancion=" + id_cancion + ", titulo=" + titulo + ", artista=" + artista + ", duracion="
				+ duracion + ", cod_genero=" + cod_genero + "]";
	}

	/********** CONSTRUCTOR **********/
	/********** GETTER && SETTER **********/

	public String getId_cancion() {
		return id_cancion;
	}

	public void setId_cancion(String id_cancion) {
		this.id_cancion = id_cancion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public String getCod_genero() {
		return cod_genero;
	}

	public void setCod_genero(String cod_genero) {
		this.cod_genero = cod_genero;
	}

}
