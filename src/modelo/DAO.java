package modelo;

import java.time.LocalDate;
import java.util.List;

import clases.Cancion;
import clases.Mensaje;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;

public interface DAO {

	// Inserts
	public void publicar(Publicacion publi);
	
	public void insertarLike(String usuario, String publicacion);
	
	
	public void insertarMensaje(Mensaje men, String usuario, String usuario2);

	// Selects
	public Publicacion buscarPublicacionXId(String id);
	
	public Usuario buscarUsuario(String usuario);
	
	public List<Usuario> listarUsuario();
	
	public List<Usuario> listarUsuarioXUsuario(String usuario);

	public int numPublicaciones();
	
	public int numPublicacionesHerencia(String tipo);

	public List<Cancion> listarCanciones();

	public List<TipoHistoria> listarTipoHistorias();

	public String calcularId(String string);
	
	public String calcularIdMensaje(String string);

	public Cancion buscarCancionXTitulo(String titulo);

	public String tipoHistoria(String tipo);
	
	public boolean buscarConver(String usuario);

	public boolean comprobarLike(String usuario, String publicacion);
	
	public List<Mensaje> sacarIdMensajes(String usuario1, String usuario2);
	

	// Alters

	// Deletes
	public void quirarLike(String usuario, String publicacion);
}
