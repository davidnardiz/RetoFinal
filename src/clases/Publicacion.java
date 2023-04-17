package clases;

import java.time.LocalDate;

public class Publicacion {

	private String id_publicacion;
	private String imagen;
	private int numLikes;
	private int numComentarios;
	private LocalDate fecha_subida;
	private String ubicacion;
	private String usuario;
	private String etiquetado;
	private String id_cancion;

	@Override
	public String toString() {
		return "Publicacion [id_publicacion=" + id_publicacion + ", imagen=" + imagen + ", numLikes=" + numLikes
				+ ", numComentarios=" + numComentarios + ", fecha_subida=" + fecha_subida + ", ubicacion=" + ubicacion
				+ ", usuario=" + usuario + ", etiquetado=" + etiquetado + ", id_cancion=" + id_cancion + "]";
	}

	/********** CONSTRUCTORES **********/
	
	/********** GETTERS && SETTERS **********/
	public String getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(String id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public int getNumComentarios() {
		return numComentarios;
	}

	public void setNumComentarios(int numComentarios) {
		this.numComentarios = numComentarios;
	}

	public LocalDate getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(LocalDate fecha_subida) {
		this.fecha_subida = fecha_subida;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getId_cancion() {
		return id_cancion;
	}

	public void setId_cancion(String id_cancion) {
		this.id_cancion = id_cancion;
	}

	public String getEtiquetado() {
		return etiquetado;
	}

	public void setEtiquetado(String etiquetado) {
		this.etiquetado = etiquetado;
	}
}
