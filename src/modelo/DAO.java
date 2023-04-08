package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;

public interface DAO {

	// Inserts
	public void publicar(Publicacion publi);

	// Selects
	public Publicacion buscarPublicacionXId(String id);

	public int numPublicaciones();
	
	public int numPublicacionesHerencia(String tipo);

	public List<Cancion> listarCanciones();

	public List<TipoHistoria> listarTipoHistorias();

	public String calcularId(String string);

	public Cancion buscarCancionXTitulo(String titulo);

	public String tipoHistoria(String tipo);

	// Alters

	// Deletes

}
