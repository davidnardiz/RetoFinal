package modelo;

import clases.Articulo;
import clases.Cancion;
import clases.Mensaje;
import clases.Publicacion;
import clases.TipoHistoria;
import clases.Usuario;
import excepciones.ErrAlter;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;
import java.time.LocalDate;
import java.util.List;

public interface DAO {

    /**
     * Éste método añade una publicación a la base de datos.
     *
     * @param publi le pasamos la publicación que se va a subir a la base de
     * datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void publicar(Publicacion publi) throws ErrVariados, ErrInsert;

    /**
     *
     * Añadimos a la base de datos el like de un usuario a una publicación.
     *
     * @param usuario el usuario que da el like.
     * @param publicacion la publicación a la que el usuario le da like.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void insertarLike(String usuario, String publicacion) throws ErrVariados, ErrInsert;

    /**
     * Éste método guarda en la base de datos cómo un usuario sigue a otro.
     *
     * @param nosotros el usuario que sigue.
     * @param usuarioPerfil el usuario al que le siguen.
     * @throws ErrVariados
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void seguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrInsert;

    /**
     * Éste método guarda en la base de datos un nuevo usuario.
     *
     * @param us el usuario con todos los datos.
     * @return
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public boolean registrar(Usuario us) throws ErrVariados, ErrInsert;

    /**
     * Éste método bloquea un usuario a otro.
     *
     * @param nosotros el usuario que bloquea.
     * @param usu el usuario al que bloquean.
     * @throws ErrVariados
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void bloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrInsert;

    /**
     * Éste método guarda en la base de datos la publicación que guarda un
     * usuario.
     *
     * @param usuario el usuario que guarda la publicación.
     * @param id_publicacion el id de la publicación que se guarda el usuario.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void guardarPublicación(String usuario, String id_publicacion) throws ErrVariados, ErrInsert;

    /**
     * Éste método guarda en la base de datos un usuario que selecciona otro
     * usuario como mejor amigo.
     *
     * @param nosotros el usuario que guarda al mejor amigo.
     * @param usu el usuario que es guardado como mejor amigo.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void aniadirAmigo(Usuario nosotros, String usu) throws ErrVariados, ErrInsert;

    /**
     * Éste método guarda el mensaje que envia un usuario en la base de datos.
     *
     * @param men el mensaje que envia el usuario.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void insertarMensaje(Mensaje men) throws ErrVariados, ErrInsert;

    /**
     * Éste método inserta un nuevo articulo en la base de datos.
     *
     * @param art es el articulo que hay que modificar.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrInsert gestiona un excepcion por si da un error al insertar
     * datos en la base de datos.
     */
    public void insertarArticulo(Articulo art) throws ErrVariados, ErrInsert;

    /**
     * Éste método busca una publicación en la base de datos.
     *
     * @param id el id de la publicación que se quiere buscar.
     * @return devuelve la publicación con ese id con todos sus datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public Publicacion buscarPublicacionXId(String id) throws ErrVariados, ErrSelect;

    /**
     * Éste métodos lista todas las publicaciones de la base de datos.
     *
     * @return devuelve un listado de todas las publicaciones de la base de
     * datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Publicacion> listarPublicaciones() throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todas las publicaciones que un usuario tiene guardadas.
     *
     * @param usuario el usuario del cual queremos saber las publicaciones tiene
     * guardadas.
     * @return devuelve un listado con todas las publicaciones que ese usuario
     * tiene guardadas.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Publicacion> listarPublicacionesGuardadas(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todas las publicaciones en las que un usuario está
     * etiquetado.
     *
     * @param usuario el usuario del cual queremos saber las publicaciones en
     * las que el usuario está etiquetado.
     * @return devuelve un listado con todas las publicaciones en las que ese
     * usuario está etiquetado.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Publicacion> listarPublicacionesEtiquetadas(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista las publicaciones de personas que el usario sigue y no
     * le tienen bloqueado. En caso de que la publicación sea de mejores amigos
     * tendrá que estar en la lista de mejores amigos del usuario propietario de
     * la publicación para que aparezca.
     *
     * @param usuario el usuario al que le van a aparecer las publicaciones.
     * @return devuelve un listado de las publicaciones que cumplen con los
     * requisitos para que le aparezcan al usuario.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Publicacion> listarPublicacionesParaTi(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método calcula la id de una publicación según el tipo de publicación
     * que sea.
     *
     * @param tipo el tipo de publicación que es.
     * @return el id de la publicación.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public String calcularId(String tipo) throws ErrVariados, ErrSelect;

    /**
     * Éste métedo busca en la base de datos un usuario mediante su nombre de
     * usuario.
     *
     * @param usuario el nombre de usuario del que queremos obtener toda su
     * información.
     * @return devuelve el usuario con toda su información.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public Usuario buscarUsuario(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método busca en la base de datos si el usuario que se pasa por
     * parámetro existe y si la contraseña es correcta.
     *
     * @param usuario el nombre de usuario que se busca en la base de datos.
     * @param contrasenia la contraseña del usuario.
     * @return devuelve el usuario con todos sus datos con ese nombre de usuario
     * y contraseña.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public Usuario iniciarSesion(String usuario, String contrasenia) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los usuarios que existen en la base de datos.
     *
     * @return devuelve un listado de los usuarios de la base de datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarUsuario() throws ErrVariados, ErrSelect;

    /**
     * Éste método lista las publicaciones de un usuario de un tipo determinado.
     *
     * @param usuarioPerfil el usuario del que queremos obtener las
     * publicaciones.
     * @param nosotros el usuario que va a ver las publicaciones.
     * @param tipo
     * @return devuelve todos los usuarios de la base de datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Publicacion> listarPublicacionesUsuario(String usuarioPerfil, String nosotros, String tipo) throws ErrVariados, ErrSelect;

    /**
     * Éste método busca los usuarios dentro de la base de datos que contengan
     * el nombre de usuario que se le pasa por parámetro.
     *
     * @param usuario el nombre de usuario que queremos buscar.
     * @return devuelve una lista de los usuarios que contengan en su nombre de
     * usuario el parámetro que se le pasa.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarUsuarioXUsuario(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los usuarios que están verificados.
     *
     * @param usuario el nombre de usuario que queremos buscar.
     * @return devuelve todos los usuarios verificados
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarUsuariosVerificados(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista los usuarios por un numero minimos número de
     * seguidores.
     *
     * @param usuario el nombre de usuario que queremos buscar.
     * @return devuelve todos los ususarios que tengan ese minimos de seguidores
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarUsuariosXSeguidores(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista los mejores amigos del usuario que controla el
     * programa.
     *
     * @param usuario el nombre de usuario que queremos buscar.
     * @return devuelve todos los usuarios que esten en tu lista de mejores
     * amigos y que contengan esos caracteres en su nombre
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarUsuarioXMejos(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método calcula el número de publicaciones que ha subido un usuario.
     *
     * @param usuario el usuario del que queremos saber su número de
     * publicaciones.
     * @return devuelve el numero de publicaciones
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public int numPublicacionesUsuario(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista las canciones guardadas en la base de datos.
     *
     * @return devuelve un listado con todas las canciones de la base de datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Cancion> listarCanciones() throws ErrVariados, ErrSelect;

    /**
     * Éste método lista los tipos de historia disponibles.
     *
     * @return devuelve un listado de los tipos de historias disponibles.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<TipoHistoria> listarTipoHistorias() throws ErrVariados, ErrSelect;

    /**
     * Éste método devuelve la información de una canción.
     *
     * @param titulo el título de la canción de la cual queremos obtener su
     * información.
     * @return devuelve la canción con toda su información.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public Cancion buscarCancionXTitulo(String titulo) throws ErrVariados, ErrSelect;

    /**
     * Éste método devuelve la información de una canción.
     *
     * @param id el id de la canción de la cual queremos obtener su información.
     * @return devuelve la canción con toda su información.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public Cancion buscarCancionXId(String id) throws ErrVariados, ErrSelect;

    /**
     * Busca el codigo del tipo de historia en
     *
     * @param tipo
     * @return devuelve el codigo de del tipo de la historia
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public String buscarCodTipoHistoria(String tipo) throws ErrVariados, ErrSelect;

    /**
     * Éste método devuelve el tipo de una publicación mediante su id.
     *
     * @param id el id de la publicación de la que queremos saber su tipo.
     * @return devuelve el tipo de publicación.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public String buscarTipoHistoria(String id) throws ErrVariados, ErrSelect;

    /**
     * Éste método comprueba si el usuario ya ha dado like a una publicación o
     * no.
     *
     * @param usuario el usuario del cual queremos saber si ha dado like o no a
     * la publicación.
     * @param publicacion la publicación de la cual queremos saber si el usuario
     * ha dado like o no.
     * @return devuelve verdadero si el usuario ya le ha dado like a la
     * publicación o falso en caso de que no lo haya hecho.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public boolean comprobarLike(String usuario, String publicacion) throws ErrVariados, ErrSelect;

    /**
     * Éste método comprueba si un usuario sigue o no a otro.
     *
     * @param nosotros el usuario que controla el programa.
     * @param usuarioPerfil el usuario del cual queremos comprobar si sigue o
     * no.
     * @return devuelve verdadero si el usuaro que controla el programa sigue al
     * otro usuario o false si es al contrario.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public boolean verSeguimiento(String nosotros, String usuarioPerfil) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista los usuarios que el usuario que controla el programa
     * tiene bloqueados.
     *
     * @param usuario el usuario del que queremos saber cuales son los usuarios
     * que tiene bloqueados.
     * @return devuelve una lista de los usuarios que un usuario tiene
     * bloqueados.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarBloqueados(Usuario usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista los usuarios que el usuario que controla el programa no
     * tiene bloqueados.
     *
     * @param usuario el usuario del que queremos saber cuales son los usuarios
     * que no tiene bloqueados.
     * @return devuelve una lista de los usuarios que un usuario no tiene
     * bloqueados.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarDesbloqueados(Usuario usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método comprueba si el usuario 2 esta el la lista de mejores amigos
     * del usuario 1
     *
     * @param nosotros
     * @param el
     * @return
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public boolean comprobarMejos(String nosotros, String el) throws ErrVariados, ErrSelect;

    /**
     * Éste método comprueba en la base de datos si un usuario tiene bloqueado a
     * otro o no.
     *
     * @param nosotros el usuario que puede tener o no bloqueado al otro.
     * @param el el usuario que puede estas bloqueado o no.
     * @return devuelve verdadero si un usuario tiene bloqueado a otro y
     * devuelve falso si no es así.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public boolean comprobarBloqueado(String nosotros, String el) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los mejores amigos de un usuario.
     *
     * @param nosotros el usuario del que queremos saber sus mejores amigos.
     * @return devuelve una lista de los usuarios que un usuario tiene en
     * mejores amigos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarMejoresAmigos(Usuario nosotros) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los usuarios que no tiene en mejores amigos de un
     * usuario.
     *
     * @param nosotros el usuario del que queremos saber los usuarios que no
     * tiene ensus mejores amigos.
     * @return devuelve una lista de los usuarios que un usuario no tiene en
     * mejores amigos. mejores amigos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Usuario> listarNoMejoresAmigos(Usuario nosotros) throws ErrVariados, ErrSelect;

    /**
     * Éste método comprueba si un usuario tiene guardada una publicación o no.
     *
     * @param usuario el usuario del cual queremos saber si tiene la publicación
     * guardada o no.
     * @param publicacion el id de la publicación que queremos saber si el
     * usuario tiene guardada.
     * @return devuelve verdadero si el usuario tiene guardada la publicación y
     * devuelve falso si no la tiene guardada.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public boolean comprobarGuardado(String usuario, String publicacion) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los mensajes entre dos usuarios.
     *
     * @param usuario1 uno de los usuarios que participa en la conversación.
     * @param usuario2 el otro usuario que participa en la conversació.
     * @return devuelve un listado con todos los mensajes de una conversación
     * entre dos usuario.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Mensaje> sacarMensajes(String usuario1, String usuario2) throws ErrVariados, ErrSelect;

    /**
     * Éste método calcula el id de un nuevo mensaje.
     *
     * @param id el id
     * @return devuelve el id del nuevo mensaje.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public String calcularIdMensaje(String id) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista las conversaciones que un usuario tiene iniciadas.
     *
     * @param usuario el usuario del que queremos obtener sus conversaciones.
     * @return devuelve un listado de las conversaciónes que un usuario tiene
     * iniciadas.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<String> sacarConversaciones(String usuario) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los artículos de la base de datos.
     *
     * @return devuelve un listado con todos los articulos de la base de datos.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Articulo> sacarTodosLosArticulos() throws ErrVariados, ErrSelect;

    /**
     * Éste método calcula el id de un artículo.
     *
     * @return devuelve el id de un artículo.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public String calcularIdArticulo(String id) throws ErrVariados, ErrSelect;

    /**
     * Éste método obtiene la valoración de un artículo.
     *
     * @param ar el articulo del que queremos saber su valoración.
     * @return devuelve la valoración del artículo.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public int obtenerValoracion(Articulo ar) throws ErrVariados, ErrSelect;

    /**
     * Éste método lista todos los articulos en función de un precio mínimo,
     * máximo y un órden ascendente o descentene.
     *
     * @param min precio mínimo.
     * @param max precio máximo.
     * @param opc orden del precio.
     * @return
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrSelect gestiona un excepcion por si da un error al recuperar
     * datos de la base de datos.
     */
    public List<Articulo> sacarArituclosPorPrecio(int min, int max, int opc) throws ErrVariados, ErrSelect;

    // Updates
    /**
     * Éste método sobreescribe los datos de un usuario.
     *
     * @param us el usuario con la nueva información.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrAlter gestiona un excepcion por si da un error al modificar
     * algo de la base de datos.
     */
    public void editarPerfil(Usuario us) throws ErrVariados, ErrAlter;

    /**
     * Éste método sobreescribe los datos de una publicación.
     *
     * @param publi la publicación con la nueva información.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrAlter gestiona un excepcion por si da un error al modificar
     * algo de la base de datos.
     */
    public void editarPublicacion(Publicacion publi) throws ErrVariados, ErrAlter;

    /**
     * Este método sobreescribe los datos de un articulo
     *
     * @param art es el articulo que se va a modificar
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrAlter gestiona un excepcion por si da un error al modificar
     * algo de la base de datos.
     */
    public void modificarArt(Articulo art) throws ErrVariados, ErrAlter;

    /**
     * Éste método sobreescribe los datos de una publicación.
     *
     * @param lugarEntrega donde se va a entregar el artículo.
     * @param fecha la fecha cuando se compró e l artículo.
     * @param valoracion la valoración que le da el usuario comprador al
     * usuario. vendedor.
     * @param id el id del articulo que el usuario compra.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrAlter gestiona un excepcion por si da un error al modificar
     * algo de la base de datos.
     */
    public void comprarArticulo(String lugarEntrega, LocalDate fecha, int valoracion, String id) throws ErrVariados, ErrAlter;

    // Deletes
    /**
     * Éste método elimina un like de un usuario a una publicación.
     *
     * @param usuario el usuario del que queremos.
     * @param publicacion la publicacion de la que se quiere quitar el like.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void quitarLike(String usuario, String publicacion) throws ErrVariados, ErrDelete;

    /**
     * Éste método deja de seguir un usuario otro.
     *
     * @param nosotros el usuario que deja de seguir.
     * @param usuarioPerfil el usuario que es dejado de seguir.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void dejarSeguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrDelete;

    /**
     * Éste método elimina una publicación de la base de datos según su id.
     *
     * @param id_publicacion el id de la publicación que el usuario quiere
     * borrar.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void eliminarPublicacion(String id_publicacion) throws ErrVariados, ErrDelete;

    /**
     * Éste método elimina un usuario de la base de datos según su nombre de
     * usuario.
     *
     * @param usuario el usuario que se quiere borrar.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void eliminarUsuario(String usuario) throws ErrVariados, ErrDelete;

    /**
     * Éste método desbloquea un usuario por otro usuario.
     *
     * @param nosotros el usuario que va a desbloquear a otro usuario.
     * @param usu el usuario que es desbloqueado.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void desbloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrDelete;

    /**
     * Éste método elimina una publicación de la tabla de guardadas.
     *
     * @param usuario el usuario que va a quitar la publicación.
     * @param id_publicacion el id de la publicación que se quiere quitar de
     * guardados.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void desguardarPublicacion(String usuario, String id_publicacion) throws ErrVariados, ErrDelete;

    /**
     * Éste método eliminar todas las publicaciones guardadas de un usuario de
     * la tabla de guardadas.
     *
     * @param usuario
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void vaciarPublicacionesGuardadas(String usuario) throws ErrVariados, ErrDelete;

    /**
     * Éste método elimina un usuario de la lista de mejores amigos de otro de
     * la tabla mejores amigos.
     *
     * @param nosotros el usuario del que vamos a quitar el mejor amigo.
     * @param usu el usuario que vamos a quitar de mejores amigos del usuario
     * que controla el programa.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void quitarAmigo(Usuario nosotros, String usu) throws ErrVariados, ErrDelete;

    /**
     * Éste método elimina un artículo de la base de datos.
     *
     * @param id el id de la publicación que se va a eliminar.
     * @throws ErrVariados gestiona un excepcion por si no se puede conectar con
     * la base de datos.
     * @throws ErrDelete gestiona un excepcion por si da un error al elimianar
     * algo de la base de datos.
     */
    public void borrarArticulo(String id) throws ErrVariados, ErrDelete;

}
