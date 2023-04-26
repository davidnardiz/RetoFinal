package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;

public interface DAO {

    // Inserts
    public void publicar(Publicacion publi) throws ErrInsert;

    public void insertarLike(String usuario, String publicacion) throws ErrInsert;

    public void seguir(String nosotros, String usuarioPerfil) throws ErrInsert;

    public boolean registrar(Usuario us) throws ErrInsert;

    // Selects
    public Publicacion buscarPublicacionXId(String id) throws ErrSelect;

    public List<Publicacion> listarPublicaciones() throws ErrSelect;

    public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo) throws ErrSelect;

    public String calcularId(String tipo) throws ErrSelect;

    public Usuario buscarUsuario(String usuario) throws ErrSelect;

    public Usuario iniciarSesion(String usuario, String contrasenia) throws ErrSelect;

    public List<Usuario> listarUsuario() throws ErrSelect;

    public List<Usuario> listarUsuarioXUsuario(String usuario) throws ErrSelect;

    public List<Usuario> listarUsuariosVerificados(String usuario) throws ErrSelect;

    public List<Usuario> listarUsuariosXSeguidores(String usuario) throws ErrSelect;

    public int numPublicacionesUsuario(String usuario) throws ErrSelect;

    public List<Cancion> listarCanciones() throws ErrSelect;

    public List<TipoHistoria> listarTipoHistorias() throws ErrSelect;

    public Cancion buscarCancionXTitulo(String titulo) throws ErrSelect;

    public String tipoHistoria(String tipo) throws ErrSelect;

    public boolean comprobarLike(String usuario, String publicacion) throws ErrSelect;

    public boolean verSeguimiento(String nosotros, String usuarioPerfil) throws ErrSelect;

    // Alters
    // Deletes
    public void quirarLike(String usuario, String publicacion) throws ErrDelete;

    public void dejarSeguir(String nosotros, String usuarioPerfil) throws ErrDelete;
}
