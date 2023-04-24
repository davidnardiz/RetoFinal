package modelo;

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
    final private String REGISTRAR = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, 'iconoPredeterminado.png', ?, ?, ?, ?)";
    final private String PUBLICAR = "INSERT INTO publicacion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    final private String INSERTAR_FOTO = "INSERT INTO foto VALUES (?, ?, ?, ?)";
    final private String INSERTAR_REEL = "INSERT INTO reel VALUES (?, ?, ?, ?)";
    final private String INSERTAR_HISTORIA = "INSERT INTO historia VALUES (?, ?, ?)";
    final private String SEGUIR = "INSERT INTO sigue VALUES (?, ?)";
    final private String INSERTAR_LIKE = "INSERT INTO likes VALUES (?, ?)";

    // Deletes
    final private String DEJAR_SEGUIR = "DELETE FROM sigue WHERE seguidor = ? and seguido = ?";

// Selects
    // PARA TI
    final private String BUSCAR_PUBLICACIO_X_ID = "SELECT * FROM publicacion WHERE id_publicacion = ?";
    final private String COMPROBAR_LIKE = "SELECT * FROM likes WHERE usuario = ? and id_publicacion = ?";
    final private String LISTAR_ID = "SELECT id_publicacion FROM publicacion";
    final private String BUSCAR_FOTO_ID = "SELECT * FROM foto WHERE id_publicacion = ?";
    final private String BUSCAR_REEL_ID = "SELECT * FROM reel WHERE id_publicacion = ?";
    final private String BUSCAR_HISTORIA_ID = "SELECT * FROM historia WHERE id_publicacion = ?";

    // BUSCAR
    final private String LISTAR_USUARIOS = "SELECT * FROM usuario";
    final private String LISTAR_USUARIOS_X_USUARIO = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ?";
    final private String LISTAR_USUARIOS_VERIFICADOS = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE usuario like ? and verificado = true";
    final private String LISTAR_USUARIOS_X_SEGUIDORES = "SELECT usuario, verificado, icono, numSeguidores FROM usuario WHERE numSeguidores >= ?";
    final private String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE usuario = ?";
    final private String INICIAR_SESION = "SELECT * FROM usuario WHERE usuario = ? and contrasenia=?";

    // SUBIR
    final private String LISTAR_MUSICA = "SELECT titulo FROM cancion";
    final private String BUSCAR_CANCION_X_TITULO = "SELECT * FROM cancion WHERE titulo = ?";
    final private String LISTAR_TIPO_HISTORIA = "SELECT tipo FROM tipoHistoria";
    final private String TIPO_HISTORIA = "SELECT cod_tipo FROM tipoHistoria WHERE tipo = ?";
    final private String ULTIMA_PUBLICACION = "SELECT id_publicacion FROM publicacion WHERE SUBSTRING(id_publicacion, 1, 1) = ? ORDER BY id_publicacion desc LIMIT 1;";

    // PERFIL
    final private String NUM_PUBLICACIONES_USUARIO = "SELECT count(*) FROM publicacion WHERE usuario = ?";
    final private String LISTAR_PUBLICACIONES_USUARIO = "SELECT * FROM publicacion WHERE usuario = ?";
    final private String VER_SEGUIMIENTO = "SELECT * FROM sigue WHERE seguidor = ? and seguido = ?";

// Alter
    final private String SUMAR_LIKE = "UPDATE publicacion set numLikes = numLikes + 1 WHERE id_publicacion = ?";
    final private String RESTAR_LIKE = "UPDATE publicacion set numLikes = numLikes - 1 WHERE id_publicacion = ?";
    final private String SUMAR_SEGUIDOR = "UPDATE usuario set numSeguidores = numSeguidores + 1 WHERE usuario = ?";
    final private String RESTAR_SEGUIDOR = "UPDATE usuario set numSeguidores = numSeguidores - 1 WHERE usuario = ?";
    final private String SUMAR_SEGUIDO = "UPDATE usuario set numSeguidos = numSeguidos - 1 WHERE usuario = ?";
    final private String RESTAR_SEGUIDO = "UPDATE usuario set numSeguidos = numSeguidos - 1 WHERE usuario = ?";

    // Deletes
    final private String QUITAR_LIKES = "DELETE FROM likes WHERE usuario = ? and id_publicacion = ?";

    public void abrirConexion() {

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
                usu = this.getUsuario(usu, rs);
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
    //Busca el codigo de la ultima publicacion
    @Override
    public String calcularId(String tipo) {
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
                System.out.println(id);
                System.out.println(cod);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        this.cerrarConexion();
        return cod;
    }

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
    public boolean registrar(Usuario us) {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.cerrarConexion();
        return registrar;
    }

    @Override
    public void seguir(String nosotros, String usuarioPerfil) {
        // TODO Auto-generated method stub
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(SEGUIR);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            stmt.execute();

            stmt = con.prepareStatement(SUMAR_SEGUIDOR);
            stmt.setString(1, usuarioPerfil);

            stmt.execute();

            stmt = con.prepareStatement(SUMAR_SEGUIDO);
            stmt.setString(1, nosotros);

            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.cerrarConexion();
    }

    @Override
    public void dejarSeguir(String nosotros, String usuarioPerfil) {
        // TODO Auto-generated method stub
        this.abrirConexion();

        try {
            stmt = con.prepareStatement(DEJAR_SEGUIR);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            stmt.execute();

            stmt = con.prepareStatement(RESTAR_SEGUIDOR);
            stmt.setString(1, usuarioPerfil);

            stmt.execute();

            stmt = con.prepareStatement(RESTAR_SEGUIDO);
            stmt.setString(1, nosotros);

            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.cerrarConexion();
    }

    @Override
    public boolean verSeguimiento(String nosotros, String usuarioPerfil) {
        // TODO Auto-generated method stub
        this.abrirConexion();

        boolean sigue = false;
        try {
            stmt = con.prepareStatement(VER_SEGUIMIENTO);
            stmt.setString(1, nosotros);
            stmt.setString(2, usuarioPerfil);

            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sigue = true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.cerrarConexion();

        return sigue;
    }

    @Override
    public Usuario iniciarSesion(String usuario, String contrasenia) {
        this.abrirConexion();
        ResultSet rs;
        Usuario us = new Usuario();
        try {
            stmt = con.prepareStatement(INICIAR_SESION);

            stmt.setString(1, usuario);
            stmt.setString(2, contrasenia);

            System.out.println(stmt);

            rs = stmt.executeQuery();

            if (rs.next()) {
                us = this.getUsuario(us, rs);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.cerrarConexion();
        return us;
    }

}
