package modelo;

import clases.Publicacion;

public interface DAO {

	public Publicacion buscarPublicacionXId(String id);
	
	public int numPublicaciones();
}
