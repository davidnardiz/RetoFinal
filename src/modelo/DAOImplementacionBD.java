package modelo;

import clases.Articulo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import clases.Cancion;
import clases.Foto;
import clases.Historia;
import clases.Mensaje;
import clases.Publicacion;
import clases.Reel;
import clases.TipoHistoria;
import clases.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOImplementacionBD implements DAO {

    private Connection con = null;
    private PreparedStatement stmt;

    // Sentencias SQL
    // Inserts
    final private String PUBLICAR = "INSERT INTO publicacion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final private String INSERTAR_FOTO = "INSERT INTO foto VALUES (?, ?, ?, ?)";
    final private String INSERTAR_REEL = "INSERT INTO reel VALUES (?, ?, ?, ?)";
    final private String INSERTAR_HISTORIA = "INSERT INTO historia VALUES (?, ?, ?)";

    final private String INSERTAR_LIKE = "INSERT INTO likes VALUES (?, ?)";

    final private String INSERTAR_MENSAJE = "INSERT INTO mensaje VALUES(?, ?, ?)";
    final private String INSERTAR_CONVERSA = "INSERT INTO conversa VALUES(?, ?, ?)";

    final private String INSERTAR_ARTICULO = "INSERT INTO articulo VALUES (?,?,?,?,?,?,?,?,?,?)";

// Selects
    //Tienda
    final private String SACAR_PRODUCTOS = "SELECT * FROM articulo where lugar_entrega is null";
    final private String ULTIMO_ARTICULO = "SELECT id_articulo FROM articulo WHERE SUBSTRING(id_articulo, 1, 1) = ? ORDER BY id_articulo desc LIMIT 1;";
    final private String SACAR_MEDIA_VALORACION = "SELECT AVG(valoracion) AS media_valoracion from articulo where vendedor = ? and valoracion is not null ";
    final private String SACAR_ARTICULO_ORDENADO = "SELECT * from articulo where lugar_entrega is null and precio >= ? and precio <= ? order by precio";
    final private String SACAR_ARTICULO_ORDENADO_DESCENDETE = "SELECT * from articulo where lugar_entrega is null and precio >= ? and precio <= ? order by precio DESC ";
    // PARA TI
    final private String BUSCAR_PUBLICACIO_X_ID = "SELECT * FROM publicacion WHERE id_publicacion = ?";
    final private String COMPROBAR_LIKE = "SELECT * FROM likes WHERE usuario = ? and id_publicacion = ?";
    final private String LISTAR_ID = "SELECT id_publicacion FROM publicacion";
    final private String BUSCAR_FOTO_ID = "SELECT * FROM foto WHERE id_publicacion = ?";
    final private String BUSCAR_REEL_ID = "SELECT * FROM reel WHERE id_publicacion = ?";
    final private String BUSCAR_HISTORIA_ID = "SELECT * FROM historia WHERE id_publicacion = ?";

    // BUSCAR
    final private String LISTAR_USUARIOS = "SELECT usuario, verificado, icono, numSeguidores FROM usuario";
    final private String LISTAR_USUARIOS_X_USUARIO = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ?";
    final private String LISTAR_USUARIOS_VERIFICADOS = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ? and verificado = true";
    final private String LISTAR_USUARIOS_X_SEGUIDORES = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE numSeguidores >= ?";
    final private String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE usuario = ?";

    // SUBIR
    final private String LISTAR_MUSICA = "SELECT titulo FROM cancion";
    final private String BUSCAR_CANCION_X_TITULO = "SELECT * FROM cancion WHERE titulo = ?";
    final private String LISTAR_TIPO_HISTORIA = "SELECT tipo FROM tipoHistoria";
    final private String TIPO_HISTORIA = "SELECT cod_tipo FROM tipoHistoria WHERE tipo = ?";
    final private String ULTIMA_PUBLICACION = "SELECT id_publicacion FROM publicacion WHERE SUBSTRING(id_publicacion, 1, 1) = ? ORDER BY id_publicacion desc LIMIT 1;";

    // PERFIL
    final private String NUM_PUBLICACIONES_USUARIO = "SELECT count(*) FROM publicacion WHERE usuario = ?";
    final private String LISTAR_PUBLICACIONES_USUARIO = "SELECT * FROM publicacion WHERE usuario = ?";

    //CONVERSASCIONES
    final private String ULTIMO_MENSAJE = "SELECT id_mensaje FROM conversa WHERE SUBSTRING(id_mensaje, 1, 1) = ? ORDER BY id_mensaje desc LIMIT 1;";
    final private String comprobarConver = "SELECT * FROM conversa where usuario = ? and usuario2 = ?";
    final private String SACAR_CONVERSACION = "SELECT men.mensaje, men.fecha_envio, conver.usuario, conver.usuario2 from conversa conver inner join mensaje men "
            + "on conver.id_mensaje = men.id_mensaje "
            + "where(conver.usuario = ? and conver.usuario2 = ?) or (conver.usuario = ? and conver.usuario2 = ?) "
            + "order by men.fecha_envio ASC ";
    final private String SACAR_TODAS_LAS_CONVERSACIONES = "SELECT DISTINCT CASE WHEN USUARIO = ? THEN usuario2 "
            + "ELSE usuario "
            + "END AS OTRO_USUARIO "
            + "FROM conversa WHERE ? IN(usuario, usuario2)";

    // Alter
    final private String SUMAR_LIKE = "UPDATE publicacion set numLikes = numLikes + 1 WHERE id_publicacion = ?";
    final private String RESTAR_LIKE = "UPDATE publicacion set numLikes = numLikes - 1 WHERE id_publicacion = ?";
    final private String MODIFICAR_ARTICULO = "UPDATE articulo set descripcion = ?, precio = ?, peso = ? where id_articulo = ?";
    final private String COMPRAR_ARTICULO = "UPDATE articulo set fecha_compra = ?, lugar_entrega = ?, valoracion = ? where id_articulo = ?";
    // Deletes
    final private String QUITAR_LIKES = "DELETE FROM likes WHERE usuario = ? and id_publicacion = ?";
    final private String BORRAR_ARTCIULO = "DELETE FROM articulo WHERE id_articulo = ?";

    public void abrirConexion() {

        try {
            Properties configBDA = new Properties();
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\bayro\\Desktop\\RetoFinal-pruebasJason\\src\\configBDA.properties");
            configBDA.load(fis);

            final String URL = configBDA.getProperty("url");
            final String USER = configBDA.getProperty("user");
            final String PASSWORD = configBDA.getProperty("password");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void cerrarConexion() {
        try {
            if (con != null) {
                con.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Devuelve una publicacion con todos sus valores
    public Publicacion getPublicacion(Publicacion publi, ResultSet rs) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            publi.setId_publicacion(rs.getString("id_publicacion"));
            publi.setImagen(rs.getString("imagen"));
            publi.setNumLikes(Integer.parseInt(rs.getString("numLikes")));
            publi.setNumComentarios(Integer.parseInt(rs.getString("numComentarios")));
            publi.setFecha_subida(LocalDate.parse(rs.getString("fecha_subida"), formateador));
            publi.setUbicacion(rs.getString("ubicacion"));
            publi.setUsuario(rs.getString("usuario"));
            publi.setId_cancion(rs.getString("id_cancion"));

            if (publi instanceof Foto) {
                stmt = con.prepareStatement(BUSCAR_FOTO_ID);
                stmt.setString(1, publi.getId_publicacion());
                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    ((Foto) publi).setDescripcion(rs2.getString("descripcion"));
                    ((Foto) publi).setResolucion(rs2.getString("resolucion"));
                    ((Foto) publi).setEtiquetado(rs2.getString("etiquetado"));
                }

            } else if (publi instanceof Reel) {
                stmt = con.prepareStatement(BUSCAR_REEL_ID);
                stmt.setString(1, publi.getId_publicacion());
                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    ((Reel) publi).setDuracion(rs2.getInt("duracion"));
                    ((Reel) publi).setReproducciones(rs2.getInt("reproducciones"));
                    ((Reel) publi).setDescripcion(rs2.getString("descripcion"));
                }
            } else if (publi instanceof Historia) {
                stmt = con.prepareStatement(BUSCAR_HISTORIA_ID);
                stmt.setString(1, publi.getId_publicacion());
                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    ((Historia) publi).setMejores_amigos(rs2.getBoolean("mejores_amigos"));
                    ((Historia) publi).setCod_tipo(rs2.getString("cod_tipo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publi;
    }

    // Prepara una publicacion para insertarla en la BDA
    public PreparedStatement setPublicacion(Publicacion publi, PreparedStatement stmt) {
        try {
            stmt.setString(1, publi.getId_publicacion());
            stmt.setString(2, publi.getImagen());
            stmt.setInt(3, publi.getNumLikes());
            stmt.setInt(4, publi.getNumComentarios());
            stmt.setDate(5, Date.valueOf(publi.getFecha_subida()));
            stmt.setString(6, publi.getUbicacion());
            stmt.setString(7, publi.getUsuario());
            stmt.setString(8, publi.getId_cancion());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }

    // Devuelve un usuario con todos sus valores
    public Usuario getUsuario(Usuario usu, ResultSet rs) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            usu.setUsuario(rs.getString("usuario"));
            usu.setContrasenia(rs.getString("contrasenia"));
            usu.setDni(rs.getString("dni"));
            usu.setNombre_completo(rs.getString("nombre_completo"));
            usu.setCorreo(rs.getString("correo"));
            usu.setTelefono(rs.getInt("telefono"));
            usu.setGenero(rs.getString("genero"));
            usu.setIcono(rs.getString("icono"));
            usu.setFecha_nac(LocalDate.parse(rs.getString("fecha_nac"), formateador));
            usu.setNumSeguidores(rs.getInt("numSeguidores"));
            usu.setNumSeguidos(rs.getInt("numSeguidos"));
            usu.setVerificado(rs.getBoolean("verificado"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usu;
    }

    /**
     * ****************** PARA TI *******************
     */
    //Devuelve una publicacion en base a su id
    @Override
    public Publicacion buscarPublicacionXId(String id) {
        Publicacion publi = null;

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_PUBLICACIO_X_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                char tipoPubli = rs.getString("id_publicacion").charAt(0);

                switch (tipoPubli) {
                    case 'F':
                        publi = new Foto();
                        break;
                    case 'R':
                        publi = new Reel();
                        break;
                    case 'H':
                        publi = new Historia();
                        break;
                }

                publi = this.getPublicacion(publi, rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();

        return publi;
    }

    //Inserta un usuario y publicacion en la tabla Likes y suma 1 like a la publicacion
    @Override
    public void insertarLike(String usuario, String publicacion) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(INSERTAR_LIKE);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            stmt.execute();

            stmt = con.prepareStatement(SUMAR_LIKE);

            stmt.setString(1, publicacion);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();

    }

    //Quita un usuario y publicacion de la tabla Likes y resta 1 like a la publicacion
    @Override
    public void quirarLike(String usuario, String publicacion) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(QUITAR_LIKES);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            stmt.execute();

            stmt = con.prepareStatement(RESTAR_LIKE);
            stmt.setString(1, publicacion);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();

    }

    /**
     * ****************** BUSCAR *******************
     */
    //Devuelve un usario en base a su usuario
    @Override
    public Usuario buscarUsuario(String usuario) {
        Usuario usu = new Usuario();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_USUARIO);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usu = this.getUsuario(usu, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return usu;
    }

    //Devuelve todas las id_publicacion que existen
    @Override
    public List<Publicacion> listarPublicaciones() {
        List<Publicacion> id = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion publi = new Publicacion();
                publi.setId_publicacion(rs.getString("id_publicacion"));
                id.add(publi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return id;
    }

    //Devuelve todos los usarios que existen
    @Override
    public List<Usuario> listarUsuario() {
        List<Usuario> usuarios = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("usuario"));
                usu.setVerificado(rs.getBoolean("verificado"));
                usu.setIcono(rs.getString("icono"));
                usu.setNumSeguidores(rs.getInt("numSeguidores"));
                usuarios.add(usu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return usuarios;
    }

    //Devuelven todos los usuario que contengan un string en su usuario
    @Override
    public List<Usuario> listarUsuarioXUsuario(String usuario) {
        List<Usuario> usuarios = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS_X_USUARIO);
            stmt.setString(1, "%" + usuario + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("usuario"));
                usu.setIcono(rs.getString("icono"));
                usu.setNumSeguidores(rs.getInt("numSeguidores"));
                usu.setVerificado(rs.getBoolean("verificado"));
                usuarios.add(usu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuariosVerificados(String usuario) {
        List<Usuario> usuarios = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS_VERIFICADOS);
            stmt.setString(1, "%" + usuario + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("usuario"));
                usu.setIcono(rs.getString("icono"));
                usu.setNumSeguidores(rs.getInt("numSeguidores"));
                usu.setVerificado(rs.getBoolean("verificado"));
                usuarios.add(usu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuariosXSeguidores(String seguidores) {
        List<Usuario> usuarios = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS_X_SEGUIDORES);
            stmt.setString(1, seguidores);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setUsuario(rs.getString("usuario"));
                usu.setIcono(rs.getString("icono"));
                usu.setNumSeguidores(rs.getInt("numSeguidores"));
                usu.setVerificado(rs.getBoolean("verificado"));
                usuarios.add(usu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return usuarios;
    }

    /**
     * ****************** SUBIR *******************
     */
    //Inserta una publicacion en la BDA
    @Override
    public void publicar(Publicacion publi) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(PUBLICAR);
            stmt = setPublicacion(publi, stmt);

            if (stmt.executeUpdate() == 1) {
                if (publi instanceof Foto) {
                    stmt = con.prepareStatement(INSERTAR_FOTO);
                    stmt.setString(1, publi.getId_publicacion());
                    stmt.setString(2, ((Foto) publi).getDescripcion());
                    stmt.setString(3, ((Foto) publi).getResolucion());
                    stmt.setString(4, ((Foto) publi).getEtiquetado());

                } else if (publi instanceof Reel) {
                    stmt = con.prepareStatement(INSERTAR_REEL);
                    stmt.setString(1, publi.getId_publicacion());
                    stmt.setInt(2, ((Reel) publi).getDuracion());
                    stmt.setInt(3, ((Reel) publi).getReproducciones());
                    stmt.setString(4, ((Reel) publi).getDescripcion());

                } else {
                    stmt = con.prepareStatement(INSERTAR_HISTORIA);
                    stmt.setString(1, publi.getId_publicacion());
                    stmt.setBoolean(2, ((Historia) publi).isMejores_amigos());
                    stmt.setString(3, ((Historia) publi).getCod_tipo());

                }

                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();
    }

    //Muestra todos los titulos de las canciones que existen
    @Override
    public List<Cancion> listarCanciones() {
        List<Cancion> canciones = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_MUSICA);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cancion can = new Cancion();
                can.setTitulo(rs.getString("titulo"));
                canciones.add(can);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();

        return canciones;
    }

    //Muestran la cancion a la que corresponde el titulo
    @Override
    public Cancion buscarCancionXTitulo(String titulo) {
        Cancion can = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_CANCION_X_TITULO);
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                can = new Cancion();

                can.setId_cancion(rs.getString("id_cancion"));
                can.setTitulo(rs.getString("titulo"));
                can.setArtista(rs.getString("artista"));
                can.setDuracion(rs.getFloat("duracion"));
                can.setCod_genero(rs.getString("cod_genero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return can;
    }

    //Lista todos los tipos de historia que existen
    @Override
    public List<TipoHistoria> listarTipoHistorias() {
        List<TipoHistoria> tipoHistoria = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_TIPO_HISTORIA);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TipoHistoria tp = new TipoHistoria();
                tp.setTipo(rs.getString("tipo"));
                tipoHistoria.add(tp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return tipoHistoria;
    }

    //Devuvle el codigo de un tipoHistoria dependiendo de su tipo
    @Override
    public String tipoHistoria(String tipo) {
        String cod = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(TIPO_HISTORIA);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cod = rs.getString("cod_tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return cod;
    }

    /**
     * ****************** PERFIL *******************
     */
    //Mira a ver si le has dado like a una publicacion
    @Override
    public boolean comprobarLike(String usuario, String publicacion) {
        boolean like = false;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPROBAR_LIKE);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                like = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();
        return like;
    }

    //Busca el codigo de la ultima publicacion
    @Override
    public String calcularId(String tipo) {
        String cod = "";
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ULTIMA_PUBLICACION);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cod = rs.getString("id_publicacion");
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        this.cerrarConexion();
        return cod;
    }

    //Muestra todas las publicaciones que ha subido un usuario
    @Override
    public List<Publicacion> listarPublicacionesUsuario(String usuario, String tipo) {
        List<Publicacion> publicaciones = new ArrayList<>();
        Publicacion publi = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_PUBLICACIONES_USUARIO);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tipoPubli = tipo.substring(0, 1);

                if (tipoPubli.equals(rs.getString("id_publicacion").substring(0, 1))) {

                    switch (tipoPubli) {
                        case "F":
                            publi = new Foto();
                            break;
                        case "R":
                            publi = new Reel();
                            break;
                        case "H":
                            publi = new Historia();
                            break;
                    }
                    publi = this.getPublicacion(publi, rs);
                    publicaciones.add(publi);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();
        return publicaciones;
    }

    //Mustra el numero de publicaciones de un usuario
    @Override
    public int numPublicacionesUsuario(String usuario) {
        int numPubli = 0;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(NUM_PUBLICACIONES_USUARIO);
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                numPubli = Integer.parseInt(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return numPubli;
    }

    @Override
    public void insertarMensaje(Mensaje men) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(INSERTAR_MENSAJE);
            stmt.setString(1, men.getIdMensaje());
            stmt.setDate(2, Date.valueOf(men.getFechaEnvio()));
            stmt.setString(3, men.getMensaje());

            if (stmt.executeUpdate() == 1) {
                stmt = con.prepareStatement(INSERTAR_CONVERSA);
                stmt.setString(1, men.getUsuario1());
                stmt.setString(2, men.getUsuario2());
                stmt.setString(3, men.getIdMensaje());
                stmt.execute();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        this.cerrarConexion();
    }

    @Override
    public List<Mensaje> sacarMensajes(String usuario1, String usuario2) {
        List<Mensaje> mensajes = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SACAR_CONVERSACION);
            stmt.setString(1, usuario1);
            stmt.setString(2, usuario2);
            stmt.setString(3, usuario2);
            stmt.setString(4, usuario1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Mensaje men = new Mensaje();

                LocalDate fecha = rs.getDate("fecha_envio").toLocalDate();
                men.setUsuario1(rs.getString("usuario"));
                men.setUsuario2(rs.getString("usuario2"));
                men.setMensaje(rs.getString("mensaje"));
                men.setFechaEnvio(fecha);
                mensajes.add(men);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.cerrarConexion();
        return mensajes;
    }

    @Override
    public String calcularIdMensaje(String string) {
        String cod = "";
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ULTIMO_MENSAJE);
            stmt.setString(1, string);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cod = rs.getString("id_mensaje");
            } else {
                cod = "00000";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public List<String> sacarConversaciones(String usuario) {
        List<String> mensajes = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SACAR_TODAS_LAS_CONVERSACIONES);
            stmt.setString(1, usuario);
            stmt.setString(2, usuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mensajes.add(rs.getString("OTRO_USUARIO"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return mensajes;

    }

    @Override
    public List<Articulo> sacarTodosLosArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SACAR_PRODUCTOS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Articulo art = new Articulo();
                LocalDate fecha = rs.getDate("fecha_subida").toLocalDate();

                art.setId_articulo(rs.getString("id_articulo"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setPrecio(rs.getFloat("precio"));
                art.setPeso(rs.getFloat("peso"));
                art.setFechaSubida(fecha);
                art.setImagen(rs.getString("imagen"));
                art.setLugarEntrega(rs.getString("lugar_entrega"));
                art.setVendedor(rs.getString("vendedor"));
                articulos.add(art);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }

    @Override
    public void insertarArticulo(Articulo art) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(INSERTAR_ARTICULO);
            stmt.setString(1, art.getId_articulo());
            stmt.setString(2, art.getImagen());
            stmt.setString(3, art.getDescripcion());
            stmt.setFloat(4, art.getPrecio());
            stmt.setFloat(5, art.getPeso());
            stmt.setDate(6, Date.valueOf(art.getFechaSubida()));
            stmt.setDate(7, null);
            stmt.setString(8, art.getLugarEntrega());
            stmt.setString(9, art.getVendedor());
            stmt.setNull(10, java.sql.Types.INTEGER);
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String calcularIdArticulo(String string) {
        String cod = "";
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ULTIMO_ARTICULO);
            stmt.setString(1, string);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cod = rs.getString("id_articulo");
            } else {
                cod = "00000";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public void borrarArticulo(String id) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BORRAR_ARTCIULO);
            stmt.setString(1, id);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();
    }

    @Override
    public void modificarArt(Articulo art) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(MODIFICAR_ARTICULO);
            stmt.setString(1, art.getDescripcion());
            stmt.setFloat(2, art.getPrecio());
            stmt.setFloat(3, art.getPeso());
            stmt.setString(4, art.getId_articulo());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();

    }

    @Override
    public void comprarArticulo(String lugarEntrega, LocalDate fecha, int valoracion, String id) {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPRAR_ARTICULO);
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, lugarEntrega);
            stmt.setInt(3, valoracion);
            stmt.setString(4, id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.cerrarConexion();
    }

    @Override
    public int obtenerValoracion(Articulo ar) {
        int valoracion = 0;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SACAR_MEDIA_VALORACION);
            stmt.setString(1, ar.getVendedor());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                valoracion = rs.getInt("media_valoracion");
            } else {
                valoracion = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.cerrarConexion();
        return valoracion;

    }

    @Override
    public List<Articulo> sacarArituclosPorPrecio(int min, int max, int opc) {
        List<Articulo> articulos = new ArrayList<>();
        this.abrirConexion();

        try {
            if (opc == 0) {
                int maxValor = Integer.MAX_VALUE;
                stmt = con.prepareStatement(SACAR_ARTICULO_ORDENADO_DESCENDETE);
                stmt.setInt(1, min);
                if (max == 999) {
                    stmt.setInt(2, maxValor);
                } else {
                    stmt.setInt(2, max);
                }

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Articulo art = new Articulo();
                    LocalDate fecha = rs.getDate("fecha_subida").toLocalDate();

                    art.setId_articulo(rs.getString("id_articulo"));
                    art.setDescripcion(rs.getString("descripcion"));
                    art.setPrecio(rs.getFloat("precio"));
                    art.setPeso(rs.getFloat("peso"));
                    art.setFechaSubida(fecha);
                    art.setImagen(rs.getString("imagen"));
                    art.setLugarEntrega(rs.getString("lugar_entrega"));
                    art.setVendedor(rs.getString("vendedor"));
                    articulos.add(art);
                }
            } else {
                stmt = con.prepareStatement(SACAR_ARTICULO_ORDENADO);
                stmt.setInt(1, min);
                stmt.setInt(2, max);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Articulo art = new Articulo();
                    LocalDate fecha = rs.getDate("fecha_subida").toLocalDate();

                    art.setId_articulo(rs.getString("id_articulo"));
                    art.setDescripcion(rs.getString("descripcion"));
                    art.setPrecio(rs.getFloat("precio"));
                    art.setPeso(rs.getFloat("peso"));
                    art.setFechaSubida(fecha);
                    art.setImagen(rs.getString("imagen"));
                    art.setLugarEntrega(rs.getString("lugar_entrega"));
                    art.setVendedor(rs.getString("vendedor"));
                    articulos.add(art);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }

}
