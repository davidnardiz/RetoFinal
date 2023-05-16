package modelo;

import clases.Articulo;
import java.util.List;

import clases.Cancion;
import clases.Mensaje;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;
import java.time.LocalDate;

public interface DAO {

    // Inserts
    public void publicar(Publicacion publi);

    public void insertarLike(String usuario, String publicacion);

    public void insertarMensaje(Mensaje men);

    public void insertarArticulo(Articulo art);

    // Selects
    public Publicacion buscarPublicacionXId(String id);

    public List<Publicacion> listarPublicaciones();

    public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo);

    public String calcularId(String tipo);

    public Usuario buscarUsuario(String usuario);

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

    public List<Mensaje> sacarMensajes(String usuario1, String usuario2);

    public String calcularIdMensaje(String string);

    public List<String> sacarConversaciones(String usuario);

    public List<Articulo> sacarTodosLosArticulos();

    public String calcularIdArticulo(String string);

    public int obtenerValoracion(Articulo ar);

    public List<Articulo> sacarArituclosPorPrecio(int min, int max, int opc);

    // Alters
    public void modificarArt(Articulo art);

    public void comprarArticulo(String lugarEntrega, LocalDate fecha, int valoracion, String id);

    // Deletes
    public void quirarLike(String usuario, String publicacion);

    public void borrarArticulo(String id);
}
