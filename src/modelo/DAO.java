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
    
    public void seguir(String nosotros, String usuarioPerfil);
    
    public boolean registrar(Usuario us);

    // Selects
    public Publicacion buscarPublicacionXId(String id);

    public List<Publicacion> listarPublicaciones();

    public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo);

    public String calcularId(String tipo);

    public Usuario buscarUsuario(String usuario);
    
    public Usuario iniciarSesion(String usuario, String contrasenia);

    public List<Usuario> listarUsuario();

    public List<Usuario> listarUsuarioXUsuario(String usuario);

    public List<Usuario> listarUsuariosVerificados(String usuario);

    public List<Usuario> listarUsuariosXSeguidores(String usuario);

    public int numPublicacionesUsuario(String usuario);

    public List<Cancion> listarCanciones();

    public List<TipoHistoria> listarTipoHistorias();

    public Cancion buscarCancionXTitulo(String titulo);

    public String tipoHistoria(String tipo);

    public boolean comprobarLike(String usuario, String publicacion);
    
    public boolean verSeguimiento(String nosotros, String usuarioPerfil);

    // Alters
    // Deletes
    public void quirarLike(String usuario, String publicacion);
    
    public void dejarSeguir(String nosotros, String usuarioPerfil);
}
