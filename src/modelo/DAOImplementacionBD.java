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
import excepciones.ErrAlter;
import excepciones.ErrDelete;
import excepciones.ErrInsert;
import excepciones.ErrSelect;
import excepciones.ErrVariados;

public class DAOImplementacionBD implements DAO {

    private Connection con = null;
    private PreparedStatement stmt;

// Sentencias SQL
    // Inserts
    final private String REGISTRAR = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, 'iconoPredeterminado.png', ?, ?, ?, ?)";
    final private String PUBLICAR = "INSERT INTO publicacion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final private String INSERTAR_FOTO = "INSERT INTO foto VALUES (?, ?, ?, ?)";
    final private String INSERTAR_REEL = "INSERT INTO reel VALUES (?, ?, ?, ?)";
    final private String INSERTAR_HISTORIA = "INSERT INTO historia VALUES (?, ?, ?)";
    final private String INSERTAR_LIKE = "INSERT INTO likes VALUES (?, ?)";
    final private String INSERTAR_BLOQUEADO = "INSERT INTO bloqueados VALUES(?, ?)";
    final private String INSERTAR_AMIGO = "INSERT INTO mejoramigo VALUES(?, ?)";
    final private String GUARDAR_PUBLICACION = "INSERT INTO guardados VALUES(?, ?)";

    final private String INSERTAR_MENSAJE = "INSERT INTO mensaje VALUES(?, ?, ?)";
    final private String INSERTAR_CONVERSA = "INSERT INTO conversa VALUES(?, ?, ?)";

    final private String INSERTAR_ARTICULO = "INSERT INTO articulo VALUES (?,?,?,?,?,?,?,?,?,?)";

// Selects
    // PARA TI
    final private String BUSCAR_PUBLICACIO_X_ID = "SELECT * FROM publicacion WHERE id_publicacion = ?";
    final private String COMPROBAR_LIKE = "SELECT * FROM likes WHERE usuario = ? and id_publicacion = ?";
    final private String COMPROBAR_GUARDADO = "SELECT * FROM guardados WHERE usuario = ? and id_publicacion = ?";
    final private String LISTAR_ID = "SELECT id_publicacion FROM publicacion";
    final private String BUSCAR_FOTO_ID = "SELECT * FROM foto WHERE id_publicacion = ?";
    final private String BUSCAR_REEL_ID = "SELECT * FROM reel WHERE id_publicacion = ?";
    final private String BUSCAR_HISTORIA_ID = "SELECT * FROM historia WHERE id_publicacion = ?";
    final private String COMPROBAR_MEJOS = "SELECT * FROM  mejorAmigo WHERE mejor_amigo = ? and usuario = ? ";
    final private String COMPROBAR_BLOQUEADO = "SELECT * FROM bloqueados WHERE usuario_bloqueado = ? and usuario = ? ";

    // BUSCAR
    final private String LISTAR_USUARIOS = "SELECT * FROM usuario";
    final private String LISTAR_USUARIOS_X_USUARIO = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ?";
    final private String LISTAR_USUARIOS_VERIFICADOS = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ? and verificado = true";
    final private String LISTAR_USUARIOS_X_SEGUIDORES = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE numSeguidores >= ?";
    final private String LISTAR_USUARIOS_X_MEJOS = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario in (SELECT mejor_amigo FROM mejorAmigo WHERE usuario like ?)";
    final private String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE usuario = ?";
    final private String INICIAR_SESION = "SELECT * FROM usuario WHERE usuario = ? and contrasenia=?";
    final private String LISTAR_BLOQUEADOS = "SELECT usuario_bloqueado FROM bloqueados WHERE usuario = ?";
    final private String LISTAR_MEJORES_AMIGOS = "SELECT mejor_amigo FROM mejoramigo WHERE usuario = ?";
    final private String LISTAR_DESBLOQUEADOS = "SELECT usuario FROM usuario WHERE usuario NOT IN(SELECT usuario_bloqueado FROM bloqueados WHERE usuario = ?)";
    final private String LISTAR_NO_MEJORES_AMIGOS = "SELECT usuario FROM usuario WHERE usuario NOT IN(SELECT mejor_amigo FROM mejoramigo WHERE usuario = ?)";
    final private String LISTAR_PUBLICACIONES_GUARDADAS = "SELECT * FROM publicacion WHERE id_publicacion IN (SELECT id_publicacion FROM guardados WHERE usuario = ?)";
    final private String LISTAR_PUBLICACIONES_ETIQUETADAS = "SELECT * FROM publicacion WHERE id_publicacion IN (SELECT id_publicacion FROM foto WHERE etiquetado = ?)";

    // SUBIR
    final private String LISTAR_MUSICA = "SELECT titulo, artistas FROM cancion";
    final private String BUSCAR_CANCION_X_TITULO = "SELECT * FROM cancion WHERE titulo = ?";
    final private String BUSCAR_CANCION_X_ID = "SELECT * FROM cancion WHERE id_cancion = ?";
    final private String LISTAR_TIPO_HISTORIA = "SELECT tipo FROM tipoHistoria";
    final private String BUSCAR_COD_TIPOHISTORIA = "SELECT cod_tipo FROM tipoHistoria WHERE tipo = ?";
    final private String BUSCAR_TIPO_TIPOHISTORIA = "SELECT tipo FROM tipoHistoria WHERE cod_tipo= ?";
    final private String ULTIMA_PUBLICACION = "SELECT id_publicacion FROM publicacion WHERE SUBSTRING(id_publicacion, 1, 1) = ? ORDER BY id_publicacion desc LIMIT 1;";

    // PERFIL
    final private String NUM_PUBLICACIONES_USUARIO = "SELECT count(*) FROM publicacion WHERE usuario = ?";
    final private String LISTAR_PUBLICACIONES_USUARIO = "SELECT * FROM publicacion WHERE usuario = ?";
    final private String VER_SEGUIMIENTO = "SELECT * FROM sigue WHERE seguidor = ? and seguido = ?";
    final private String BORRAR_PUBS_GUARDADAS = "DELETE FROM guardados WHERE usuario = ?";

    //Tienda
    final private String SACAR_PRODUCTOS = "SELECT * FROM articulo where lugar_entrega is null";
    final private String ULTIMO_ARTICULO = "SELECT id_articulo FROM articulo WHERE SUBSTRING(id_articulo, 1, 1) = ? ORDER BY id_articulo desc LIMIT 1;";
    final private String SACAR_MEDIA_VALORACION = "SELECT AVG(valoracion) AS media_valoracion from articulo where vendedor = ? and valoracion is not null ";
    final private String SACAR_ARTICULO_ORDENADO = "SELECT * from articulo where lugar_entrega is null and precio >= ? and precio <= ? order by precio";
    final private String SACAR_ARTICULO_ORDENADO_DESCENDETE = "SELECT * from articulo where lugar_entrega is null and precio >= ? and precio <= ? order by precio DESC ";

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

// Update
    final private String EDITAR_PERFIL = "UPDATE usuario set contrasenia = ?, icono = ?, correo = ?, telefono = ? WHERE usuario = ?";
    final private String MODIFICAR_ARTICULO = "UPDATE articulo set descripcion = ?, precio = ?, peso = ? where id_articulo = ?";
    final private String COMPRAR_ARTICULO = "UPDATE articulo set fecha_compra = ?, lugar_entrega = ?, valoracion = ? where id_articulo = ?";
    final private String MODIFICAR_PUBLICACION = "UPDATE publicacion SET imagen = ?, ubicacion = ?, id_cancion = ? WHERE id_publicacion = ?";
    final private String MODIFICAR_FOTO = "UPDATE foto SET descripcion = ?, resolucion = ?, etiquetado = ? WHERE id_publicacion = ?";
    final private String MODIFICAR_REEL = "UPDATE reel SET duracion = ?, descripcion = ? WHERE id_publicacion = ?";
    final private String MODIFICAR_HISTORIA = "UPDATE historia SET mejores_amigos = ?, cod_tipo = ? WHERE id_publicacion = ?";

// Deletes
    final private String ELIMINAR_PUBLICACION = "DELETE FROM publicacion WHERE id_publicacion = ?";
    final private String ELIMINAR_USUARIO = "DELETE FROM usuario WHERE usuario = ?";
    final private String QUITAR_AMIGO = "DELETE FROM mejoramigo WHERE usuario = ? and mejor_amigo = ?";
    final private String QUITAR_PUBLICACION = "DELETE FROM guardados WHERE usuario = ? and id_publicacion = ?";
    final private String DESBLOQUEAR_USUARIO = "DELETE FROM bloqueados WHERE usuario = ? and usuario_bloqueado = ?";
    final private String QUITAR_LIKES = "DELETE FROM likes WHERE usuario = ? and id_publicacion = ?";
    final private String BORRAR_ARTCIULO = "DELETE FROM articulo WHERE id_articulo = ?";

//Procedimientos
    final private String SEGUIR = "call Seguir (?, ?)";
    final private String DEJAR_SEGUIR = "call dejarSeguir(?, ?)";
    final private String DAR_LIKE = "call DarLike(?, ?)";
    final private String QUITAR_LIKE = "call QuitarLike(?, ?)";
    final private String BLOQUEAR_USUARIO = "call bloquear(?, ?)";

    final private String COMPROBAR_PUBLICACION = "SELECT MostrarPublicacion(?, ?)";

    public void abrirConexion() throws ErrVariados {

        try {
            Properties configBDA = new Properties();
            String rutaProyecto = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(rutaProyecto + "\\src\\configBDA.properties");
            configBDA.load(fis);

            final String URL = configBDA.getProperty("url");
            final String USER = configBDA.getProperty("user");
            final String PASSWORD = configBDA.getProperty("password");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (FileNotFoundException e) {
            throw new ErrVariados("Fichero");
        } catch (IOException e) {
            throw new ErrVariados("LeerFichero");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ErrVariados("ConexionBDA");
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
            usu.setCodGmail(rs.getString("codGmail"));
            usu.setDni(rs.getString("dni"));
            usu.setCorreo(rs.getString("correo"));
            usu.setTelefono(rs.getInt("telefono"));
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
    @Override
    public Publicacion buscarPublicacionXId(String id) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Publicacion");
        }

        this.cerrarConexion();

        return publi;
    }

    @Override
    public List<Publicacion> listarPublicacionesParaTi(String usuario) throws ErrVariados, ErrSelect {
        List<Publicacion> publicaciones = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_ID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(COMPROBAR_PUBLICACION);
                stmt.setString(1, rs.getString("id_publicacion"));
                stmt.setString(2, usuario);

                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    if (rs2.getBoolean(1)) {
                        Publicacion publi = new Publicacion();
                        publi.setId_publicacion(rs.getString("id_publicacion"));
                        publicaciones.add(publi);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ErrSelect("Publicacion");
        }

        this.cerrarConexion();
        return publicaciones;
    }

    @Override
    public void insertarLike(String usuario, String publicacion) throws ErrVariados, ErrInsert {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(DAR_LIKE);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            stmt.execute();

        } catch (SQLException e) {
            throw new ErrInsert("Likes");
        }
        this.cerrarConexion();

    }

    @Override
    public void quirarLike(String usuario, String publicacion) throws ErrVariados, ErrDelete {

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(QUITAR_LIKE);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            stmt.execute();

        } catch (SQLException e) {
            throw new ErrDelete("Likes");
        }
        this.cerrarConexion();

    }

    /**
     * ****************** BUSCAR *******************
     */
    @Override
    public Usuario buscarUsuario(String usuario) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return usu;
    }

    @Override
    public List<Publicacion> listarPublicaciones() throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Publicacion");
        }
        this.cerrarConexion();
        return id;
    }

    @Override
    public List<Usuario> listarUsuario() throws ErrVariados, ErrSelect {
        List<Usuario> usuarios = new ArrayList<>();
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu = this.getUsuario(usu, rs);
                usuarios.add(usu);
            }

        } catch (SQLException e) {
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuarioXUsuario(String usuario) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuariosVerificados(String usuario) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuariosXSeguidores(String seguidores) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return usuarios;
    }

    @Override
    public List<Usuario> listarUsuarioXMejos(String usuario) throws ErrVariados, ErrSelect {
        List<Usuario> usuarios = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_USUARIOS_X_MEJOS);
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
            throw new ErrSelect("Usuario");
        }

        this.cerrarConexion();
        return usuarios;
    }

    /**
     * ****************** SUBIR *******************
     */
    @Override
    public String calcularId(String tipo) throws ErrVariados, ErrSelect {
        String id;
        String cod = "";
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ULTIMA_PUBLICACION);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getString("id_publicacion");
                cod = tipo + "-" + String.format("%03d", Integer.parseInt(id.substring(2, 5)) + 1);

            }
        } catch (SQLException e) {
            throw new ErrSelect("Publicacion");

        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public void publicar(Publicacion publi) throws ErrVariados, ErrInsert {
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
            throw new ErrInsert("Publicacion");
        }

        this.cerrarConexion();
    }

    @Override
    public List<Cancion> listarCanciones() throws ErrVariados, ErrSelect {
        List<Cancion> canciones = new ArrayList<>();

        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_MUSICA);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cancion can = new Cancion();
                can.setTitulo(rs.getString("titulo"));
                can.setArtista(rs.getString("artistas"));
                canciones.add(can);
            }
        } catch (SQLException e) {
            throw new ErrSelect("Cancion");
        }
        this.cerrarConexion();

        return canciones;
    }

    @Override
    public Cancion buscarCancionXTitulo(String titulo) throws ErrVariados, ErrSelect {
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
                can.setArtista(rs.getString("artistas"));
                can.setDuracion(rs.getFloat("duracion"));
                can.setCod_genero(rs.getString("cod_genero"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ErrSelect("Cancion");
        }
        this.cerrarConexion();
        return can;
    }

    @Override
    public Cancion buscarCancionXId(String id) throws ErrVariados, ErrSelect {
        Cancion can = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_CANCION_X_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                can = new Cancion();

                can.setId_cancion(rs.getString("id_cancion"));
                can.setTitulo(rs.getString("titulo"));
                can.setArtista(rs.getString("artistas"));
                can.setDuracion(rs.getFloat("duracion"));
                can.setCod_genero(rs.getString("cod_genero"));
            }

        } catch (SQLException e) {
            throw new ErrSelect("Cancion");
        }
        this.cerrarConexion();
        return can;
    }

    @Override
    public List<TipoHistoria> listarTipoHistorias() throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Historia");
        }
        this.cerrarConexion();
        return tipoHistoria;
    }

    @Override
    public String buscarCodTipoHistoria(String tipo) throws ErrVariados, ErrSelect {
        String cod = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_COD_TIPOHISTORIA);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cod = rs.getString("cod_tipo");

            }
        } catch (SQLException e) {
            throw new ErrSelect("Historia");
        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public String buscarTipoHistoria(String cod) throws ErrVariados, ErrSelect {
        String tipo = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BUSCAR_TIPO_TIPOHISTORIA);
            stmt.setString(1, cod);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = rs.getString("tipo");
            }
        } catch (SQLException e) {
            throw new ErrSelect("Historia");
        }
        this.cerrarConexion();
        return tipo;
    }

    @Override
    public void editarPublicacion(Publicacion publi) throws ErrVariados, ErrAlter {
        this.abrirConexion();

        try {

            stmt = con.prepareStatement(MODIFICAR_PUBLICACION);
            stmt.setString(1, publi.getImagen());
            stmt.setString(2, publi.getUbicacion());
            stmt.setString(3, publi.getId_cancion());
            stmt.setString(4, publi.getId_publicacion());

            stmt.execute();

            if (publi instanceof Foto) {
                stmt = con.prepareStatement(MODIFICAR_FOTO);
                stmt.setString(1, ((Foto) publi).getDescripcion());
                stmt.setString(2, ((Foto) publi).getResolucion());
                stmt.setString(3, ((Foto) publi).getEtiquetado());
                stmt.setString(4, publi.getId_publicacion());

                stmt.execute();

            } else if (publi instanceof Reel) {
                stmt = con.prepareStatement(MODIFICAR_REEL);
                stmt.setInt(1, ((Reel) publi).getDuracion());
                stmt.setString(2, ((Reel) publi).getDescripcion());
                stmt.setString(3, publi.getId_publicacion());

                stmt.execute();

            } else {
                stmt = con.prepareStatement(MODIFICAR_HISTORIA);
                stmt.setBoolean(1, ((Historia) publi).isMejores_amigos());
                stmt.setString(2, ((Historia) publi).getCod_tipo());
                stmt.setString(3, publi.getId_publicacion());

                stmt.execute();
            }

        } catch (SQLException e) {
            throw new ErrAlter("Publicacion");

        }

        this.cerrarConexion();
    }

    /**
     * ****************** PERFIL *******************
     */
    @Override
    public boolean comprobarLike(String usuario, String publicacion) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Likes");
        }

        this.cerrarConexion();
        return like;
    }

    @Override
    public List<Publicacion> listarPublicacionesUsuario(String usuarioPerifl, String nosotros, String tipo) throws ErrVariados, ErrSelect {
        List<Publicacion> publicaciones = new ArrayList<>();
        Publicacion publi = null;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(LISTAR_PUBLICACIONES_USUARIO);
            stmt.setString(1, usuarioPerifl);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(COMPROBAR_PUBLICACION);
                stmt.setString(1, rs.getString("id_publicacion"));
                stmt.setString(2, nosotros);
                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    if (rs2.getBoolean(1)) {

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
                }
            }
        } catch (SQLException e) {
            throw new ErrSelect("Publicacion");
        }

        this.cerrarConexion();
        return publicaciones;
    }

    @Override
    public int numPublicacionesUsuario(String usuario) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Publicacion");
        }
        this.cerrarConexion();
        return numPubli;
    }

    @Override
    public boolean registrar(Usuario us) throws ErrVariados, ErrInsert {
        // TODO Auto-generated method stub
        this.abrirConexion();
        ResultSet rs;
        boolean registrar = false;
        String f = us.getFecha_nac() + "";
        try {
            stmt = con.prepareStatement(REGISTRAR);

            stmt.setString(1, us.getUsuario());
            stmt.setString(2, us.getContrasenia());
            stmt.setString(3, us.getCodGmail());
            stmt.setString(4, us.getDni());
            stmt.setString(5, us.getCorreo());
            stmt.setInt(6, us.getTelefono());
            stmt.setString(7, f);
            stmt.setInt(8, us.getNumSeguidores());
            stmt.setInt(9, us.getNumSeguidos());
            stmt.setBoolean(10, us.isVerificado());

            if (stmt.executeUpdate() == 1) {
                registrar = true;
            }
        } catch (SQLException e) {
            throw new ErrInsert("Usuario");
        }
        this.cerrarConexion();
        return registrar;
    }

    @Override
    public void seguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrInsert {
        // TODO Auto-generated method stub
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SEGUIR);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ErrInsert("Sigue");
        }

        this.cerrarConexion();
    }

    @Override
    public void dejarSeguir(String nosotros, String usuarioPerfil) throws ErrVariados, ErrDelete {
        // TODO Auto-generated method stub
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(DEJAR_SEGUIR);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            stmt.execute();

        } catch (SQLException e) {
            throw new ErrDelete("Sigue");
        }

        this.cerrarConexion();
    }

    @Override
    public boolean verSeguimiento(String nosotros, String usuarioPerfil) throws ErrVariados, ErrSelect {
        // TODO Auto-generated method stub
        this.abrirConexion();

        boolean sigue = false;
        try {
            stmt = con.prepareStatement(VER_SEGUIMIENTO);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sigue = true;
            }

        } catch (SQLException e) {
            throw new ErrSelect("Sigue");
        }

        this.cerrarConexion();

        return sigue;
    }

    @Override
    public Usuario iniciarSesion(String usuario, String contrasenia) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        ResultSet rs;
        Usuario us = null;
        try {
            stmt = con.prepareStatement(INICIAR_SESION);

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);
            System.out.println(stmt);
            rs = stmt.executeQuery();

            if (rs.next()) {
                us = new Usuario();
                us = this.getUsuario(us, rs);
            }

        } catch (SQLException e) {
            throw new ErrSelect("Usuario");
        }
        this.cerrarConexion();
        return us;
    }

    @Override
    public List<Usuario> listarBloqueados(Usuario usuario) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Usuario> usuarios = new ArrayList();

        try {
            stmt = con.prepareStatement(LISTAR_BLOQUEADOS);

            stmt.setString(1, usuario.getUsuario());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(BUSCAR_USUARIO);

                stmt.setString(1, rs.getString("usuario_bloqueado"));

                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    Usuario usu = new Usuario();
                    usu = this.getUsuario(usu, rs2);
                    usuarios.add(usu);
                }
            }
        } catch (SQLException ex) {
            throw new ErrSelect("Bloqueado");
        }

        return usuarios;
    }

    @Override
    public List<Usuario> listarDesbloqueados(Usuario usuario) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Usuario> usuarios = new ArrayList();

        try {
            stmt = con.prepareStatement(LISTAR_DESBLOQUEADOS);

            stmt.setString(1, usuario.getUsuario());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(BUSCAR_USUARIO);

                stmt.setString(1, rs.getString("usuario"));

                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    Usuario usu = new Usuario();
                    usu = this.getUsuario(usu, rs2);
                    usuarios.add(usu);
                }
            }
        } catch (SQLException ex) {
            throw new ErrSelect("Bloqueado");
        }

        return usuarios;
    }

    @Override
    public void eliminarPublicacion(String id_publicacion) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ELIMINAR_PUBLICACION);
            stmt.setString(1, id_publicacion);
            stmt.execute();

        } catch (SQLException e) {
            throw new ErrDelete("Publicacion");
        }

        this.cerrarConexion();
    }

    @Override
    public void guardarPublicación(String usuario, String id_publicacion) throws ErrVariados, ErrInsert {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(GUARDAR_PUBLICACION);

            stmt.setString(1, usuario);
            stmt.setString(2, id_publicacion);

            stmt.execute();
        } catch (SQLException ex) {
            throw new ErrInsert("Guardado");
        }

        this.cerrarConexion();
    }

    @Override
    public void bloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrInsert {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BLOQUEAR_USUARIO);

            stmt.setString(1, nosotros.getUsuario());
            stmt.setString(2, usu);

            stmt.execute();

        } catch (SQLException ex) {
            throw new ErrInsert("Bloquear");
        }

        this.cerrarConexion();
    }

    @Override
    public void desbloquearUsuario(Usuario nosotros, String usu) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(DESBLOQUEAR_USUARIO);

            stmt.setString(1, nosotros.getUsuario());
            stmt.setString(2, usu);

            stmt.execute();

        } catch (SQLException ex) {
            throw new ErrDelete("Bloquear");
        }

        this.cerrarConexion();
    }

    @Override
    public void editarPerfil(Usuario us) throws ErrVariados, ErrAlter {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(EDITAR_PERFIL);

            stmt.setString(1, us.getContrasenia());
            stmt.setString(2, us.getIcono());
            stmt.setString(3, us.getCorreo());
            stmt.setString(4, us.getTelefono() + "");
            stmt.setString(5, us.getUsuario());

            stmt.execute();
        } catch (SQLException ex) {
            throw new ErrAlter("Usuario");
        }

        this.cerrarConexion();

    }

    @Override
    public void eliminarUsuario(String usuario) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(ELIMINAR_USUARIO);
            stmt.setString(1, usuario);
            stmt.execute();

        } catch (SQLException e) {
            throw new ErrDelete("Usuario");
        }
    }

    @Override
    public List<Publicacion> listarPublicacionesGuardadas(String usuario) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Publicacion> publicaciones = new ArrayList();
        try {
            stmt = con.prepareStatement(LISTAR_PUBLICACIONES_GUARDADAS);

            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion pub = new Publicacion();
                pub = this.getPublicacion(pub, rs);
                publicaciones.add(pub);
            }
        } catch (SQLException ex) {
            throw new ErrSelect("Guardado");
        }

        this.cerrarConexion();

        return publicaciones;
    }

    @Override
    public boolean comprobarGuardado(String usuario, String publicacion) throws ErrVariados, ErrSelect {
        boolean guardado = false;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPROBAR_GUARDADO);
            stmt.setString(1, usuario);
            stmt.setString(2, publicacion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                guardado = true;
            }
        } catch (SQLException e) {
            throw new ErrSelect("Guardado");
        }

        this.cerrarConexion();
        return guardado;

    }

    @Override
    public void vaciarPublicacionesGuardadas(String usuario) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BORRAR_PUBS_GUARDADAS);

            stmt.setString(1, usuario);

            stmt.execute();
        } catch (SQLException ex) {
            throw new ErrDelete("Guardado");
        }

        this.cerrarConexion();
    }

    @Override
    public List<Publicacion> listarPublicacionesEtiquetadas(String usuario) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Publicacion> publicaciones = new ArrayList();
        try {
            stmt = con.prepareStatement(LISTAR_PUBLICACIONES_ETIQUETADAS);

            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Publicacion pub = new Publicacion();
                pub = this.getPublicacion(pub, rs);
                publicaciones.add(pub);
            }
        } catch (SQLException ex) {
            throw new ErrSelect("Guardado");
        }

        this.cerrarConexion();

        return publicaciones;

    }

    @Override
    public List<Usuario> listarMejoresAmigos(Usuario nosotros) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Usuario> usuarios = new ArrayList();

        try {
            stmt = con.prepareStatement(LISTAR_MEJORES_AMIGOS);

            stmt.setString(1, nosotros.getUsuario());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(BUSCAR_USUARIO);
                stmt.setString(1, rs.getString("mejor_amigo"));

                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    Usuario usu = new Usuario();
                    usu = this.getUsuario(usu, rs2);
                    usuarios.add(usu);
                }
            }
        } catch (SQLException ex) {
            throw new ErrSelect("Mejos");
        }

        return usuarios;
    }

    @Override
    public List<Usuario> listarNoMejoresAmigos(Usuario nosotros) throws ErrVariados, ErrSelect {
        this.abrirConexion();
        List<Usuario> usuarios = new ArrayList();

        try {
            stmt = con.prepareStatement(LISTAR_NO_MEJORES_AMIGOS);
            stmt.setString(1, nosotros.getUsuario());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stmt = con.prepareStatement(BUSCAR_USUARIO);
                stmt.setString(1, rs.getString("usuario"));

                ResultSet rs2 = stmt.executeQuery();

                if (rs2.next()) {
                    Usuario usu = new Usuario();
                    usu = this.getUsuario(usu, rs2);
                    usuarios.add(usu);
                }
            }

        } catch (SQLException ex) {
            throw new ErrSelect("Mejos");
        }

        return usuarios;
    }

    @Override
    public void aniadirAmigo(Usuario nosotros, String usu) throws ErrVariados, ErrInsert {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(INSERTAR_AMIGO);

            stmt.setString(1, nosotros.getUsuario());
            stmt.setString(2, usu);

            stmt.execute();

        } catch (SQLException ex) {
            throw new ErrInsert("Mejos");
        }

        this.cerrarConexion();
    }

    @Override
    public boolean comprobarMejos(String nosotros, String el) throws ErrVariados, ErrSelect {
        boolean esMejo = false;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPROBAR_MEJOS);
            stmt.setString(1, nosotros);
            stmt.setString(2, el);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                esMejo = true;
            }

        } catch (SQLException ex) {
            throw new ErrSelect("Mejor Amigo");
        }

        this.cerrarConexion();
        return esMejo;
    }

    @Override
    public boolean comprobarBloqueado(String nosotros, String el) throws ErrVariados, ErrSelect {
        boolean bloqueado = false;
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPROBAR_BLOQUEADO);
            stmt.setString(1, nosotros);
            stmt.setString(2, el);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                bloqueado = true;
            }
        } catch (SQLException e) {
            throw new ErrSelect("Bloqueados");
        }
        this.cerrarConexion();
        return bloqueado;
    }

    @Override
    public void desguardarPublicacion(String usuario, String id_publicacion) throws ErrVariados, ErrDelete {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void quitarAmigo(Usuario nosotros, String usu) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {

            stmt = con.prepareStatement(QUITAR_AMIGO);

            stmt.setString(1, nosotros.getUsuario());
            stmt.setString(2, usu);

            stmt.execute();

        } catch (SQLException ex) {
            throw new ErrDelete("Mejos");
        }

        this.cerrarConexion();
    }

    @Override
    public void insertarMensaje(Mensaje men) throws ErrVariados, ErrInsert {
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
            throw new ErrInsert("Mensaje");
        }
        this.cerrarConexion();
    }

    @Override
    public List<Mensaje> sacarMensajes(String usuario1, String usuario2) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Mensaje");
        }
        this.cerrarConexion();
        return mensajes;
    }

    @Override
    public String calcularIdMensaje(String string) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Mensaje");
        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public List<String> sacarConversaciones(String usuario) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Conversacion");
        }
        this.cerrarConexion();
        return mensajes;

    }

    @Override
    public List<Articulo> sacarTodosLosArticulos() throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Articulos");
        }

        return articulos;
    }

    @Override
    public void insertarArticulo(Articulo art) throws ErrVariados, ErrInsert {
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
            throw new ErrInsert("Articulos");
        }
    }

    @Override
    public String calcularIdArticulo(String string) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Articulos");
        }
        this.cerrarConexion();
        return cod;
    }

    @Override
    public void borrarArticulo(String id) throws ErrVariados, ErrDelete {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(BORRAR_ARTCIULO);
            stmt.setString(1, id);

            stmt.execute();
        } catch (SQLException e) {
            throw new ErrDelete("Articulos");
        }

        this.cerrarConexion();
    }

    @Override
    public void modificarArt(Articulo art) throws ErrVariados, ErrAlter {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(MODIFICAR_ARTICULO);
            stmt.setString(1, art.getDescripcion());
            stmt.setFloat(2, art.getPrecio());
            stmt.setFloat(3, art.getPeso());
            stmt.setString(4, art.getId_articulo());

            stmt.execute();
        } catch (SQLException e) {
            throw new ErrAlter("Articulos");
        }

        this.cerrarConexion();

    }

    @Override
    public void comprarArticulo(String lugarEntrega, LocalDate fecha, int valoracion, String id) throws ErrVariados, ErrAlter {
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(COMPRAR_ARTICULO);
            stmt.setDate(1, Date.valueOf(fecha));
            stmt.setString(2, lugarEntrega);
            stmt.setInt(3, valoracion);
            stmt.setString(4, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new ErrAlter("Articulos");
        }

        this.cerrarConexion();
    }

    @Override
    public int obtenerValoracion(Articulo ar) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Articulos");
        }
        this.cerrarConexion();
        return valoracion;

    }

    @Override
    public List<Articulo> sacarArituclosPorPrecio(int min, int max, int opc) throws ErrVariados, ErrSelect {
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
            throw new ErrSelect("Articulos");
        }

        return articulos;
    }
}
