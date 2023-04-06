package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;

public interface DAO {

	public Publicacion buscarPublicacionXId(String id);
	
	public int numPublicaciones();
	
	public List<Cancion> listarCanciones();
	
	public List<TipoHistoria> listarTipoHistorias();
}
