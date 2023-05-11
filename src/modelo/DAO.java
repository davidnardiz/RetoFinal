package modelo;

import java.util.List;

import clases.Cancion;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;
import excepciones.ErrAlter;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;

public interface DAO {

    // Inserts
    public void publicar(Publicacion publi) throws ErrVariados, ErrInsert;

    public void insertarLike(String usuario, String publicacion) throws ErrVariados, ErrInsert;

    public void seguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrInsert;

    public boolean registrar(Usuario us) throws ErrVariados, ErrInsert;

    public void bloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrInsert;

    public void guardarPublicación(String usuario, String id_publicacion) throws ErrVariados, ErrInsert;

    // Selects
    public Publicacion buscarPublicacionXId(String id) throws ErrVariados, ErrSelect;

    public List<Publicacion> listarPublicaciones() throws ErrVariados, ErrSelect;

    public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo) throws ErrVariados, ErrSelect;

    public List<Publicacion> listarPublicacionesParaTi(String usuario) throws ErrVariados, ErrSelect;

    public String calcularId(String tipo) throws ErrVariados, ErrSelect;

    public Usuario buscarUsuario(String usuario) throws ErrVariados, ErrSelect;

    public Usuario iniciarSesion(String usuario, String contrasenia) throws ErrVariados, ErrSelect;

    public List<Usuario> listarUsuario() throws ErrVariados, ErrSelect;

    public List<Usuario> listarUsuarioXUsuario(String usuario) throws ErrVariados, ErrSelect;

    public List<Usuario> listarUsuariosVerificados(String usuario) throws ErrVariados, ErrSelect;

    public List<Usuario> listarUsuariosXSeguidores(String usuario) throws ErrVariados, ErrSelect;

    public List<Usuario> listarUsuarioXMejos(String usuario) throws ErrVariados, ErrSelect;

    public int numPublicacionesUsuario(String usuario) throws ErrVariados, ErrSelect;

    public List<Cancion> listarCanciones() throws ErrVariados, ErrSelect;

    public List<TipoHistoria> listarTipoHistorias() throws ErrVariados, ErrSelect;

    public Cancion buscarCancionXTitulo(String titulo) throws ErrVariados, ErrSelect;

    public Cancion buscarCancionXId(String id) throws ErrVariados, ErrSelect;

    public String buscarCodTipoHistoria(String tipo) throws ErrVariados, ErrSelect;

    public String buscarTipoHistoria(String id) throws ErrVariados, ErrSelect;

    public boolean comprobarLike(String usuario, String publicacion) throws ErrVariados, ErrSelect;

    public boolean verSeguimiento(String nosotros, String usuarioPerfil) throws ErrVariados, ErrSelect;

    public List<Usuario> listarBloqueados(Usuario usuario) throws ErrVariados, ErrSelect;

    public List<Usuario> listarDesbloqueados(Usuario usuario) throws ErrVariados, ErrSelect;

    public boolean comprobarMejos(Usuario nosotros, Usuario el) throws ErrVariados, ErrSelect;

    // Alters
    public void editarPerfil(Usuario us) throws ErrVariados, ErrAlter;

    public void editarPublicacion(Publicacion publi) throws ErrVariados, ErrAlter;

    // Deletes
    public void quirarLike(String usuario, String publicacion) throws ErrVariados, ErrDelete;

    public void dejarSeguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrDelete;

    public void eliminarPublicacion(String id_publicacion) throws ErrVariados, ErrDelete;

    public void desbloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrDelete;

    public void desguardarPublicacion(String usuario, String id_publicacion) throws ErrVariados, ErrDelete;

}
