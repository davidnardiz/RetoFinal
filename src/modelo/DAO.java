package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;

public interface DAO {

	public Publicacion buscarPublicacionXId(String id);
	
	public void publicar(Publicacion publi);
	
	public int numPublicaciones();
	
	public List<Cancion> listarCanciones();
	
	public List<TipoHistoria> listarTipoHistorias();

	public String calcularId(String string);
	
	public Cancion buscarCancionXTitulo(String titulo);
	
	public String tipoHistoria(String tipo);
}
