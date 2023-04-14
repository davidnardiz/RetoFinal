package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;

public interface DAO {

	// Inserts
	public void publicar(Publicacion publi);
	
	public void insertarLike(String usuario, String publicacion);

	// Selects
	public Publicacion buscarPublicacionXId(String id);
	
	public Usuario buscarUsuario(String usuario);
	
	public List<Usuario> listarUsuario();
	
	public List<Usuario> listarUsuarioXUsuario(String usuario);
	
	public List<Publicacion> listarId();

	public int numPublicaciones();
	
	public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo);
	
	public int numPublicacionesHerencia(String tipo);
	
	public String calcularId(String tipo);

	public List<Cancion> listarCanciones();

	public List<TipoHistoria> listarTipoHistorias();

	public Cancion buscarCancionXTitulo(String titulo);

	public String tipoHistoria(String tipo);

	public boolean comprobarLike(String usuario, String publicacion);
	// Alters

	// Deletes
	public void quirarLike(String usuario, String publicacion);
}
