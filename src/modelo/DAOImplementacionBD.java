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
import clases.Mensaje;
import clases.Publicacion;
import clases.Reel;
import clases.TipoHistoria;
import clases.Usuario;

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

	final private String INSERTAR_MENSAJE = "INSERT INTO conversa VALUES(?, ?, ?, ?)";
	

	// Selects
	final private String BUSCAR_PUBLICACIO_X_ID = "SELECT * FROM publicacion WHERE id_publicacion = ?";
	final private String NUM_PUBLICACIONES = "SELECT count(*) FROM publicacion";
	final private String NUM_PUBLICACIONES_POR_TIPO = "SELECT count(*) FROM publicacion WHERE SUBSTRING(id_publicacion, 1, 1) = ?";
	final private String LISTAR_MUSICA = "SELECT titulo FROM cancion";
	final private String BUSCAR_CANCION_X_TITULO = "SELECT * FROM cancion WHERE titulo = ?";
	final private String LISTAR_TIPO_HISTORIA = "SELECT tipo FROM tipoHistoria";
	final private String ULTIMA_PUBLICACION = "SELECT id_publicacion FROM publicacion WHERE SUBSTRING(id_publicacion, 1, 1) = ? ORDER BY id_publicacion desc LIMIT 1;";
	final private String ULTIMO_MENSAJE = "SELECT id_mensaje FROM conversa WHERE SUBSTRING(id_mensaje, 1, 1) = ? ORDER BY id_mensaje desc LIMIT 1;";
	final private String TIPO_HISTORIA = "SELECT cod_tipo FROM tipoHistoria WHERE tipo = ?";

	final private String LISTAR_USUARIOS = "SELECT usuario, icono FROM usuario";
	final private String LISTAR_USUARIOS_X_USUARIO = "SELECT usuario, icono FROM usuario WHERE usuario like ?";
	final private String BUSCAR_USUARIO = "SELECT * FROM usuario WHERE usuario = ?";

	final private String COMPROBAR_LIKE = "SELECT * FROM likes WHERE usuario = ? and id_publicacion = ?";
	
	final private String comprobarConver = "SELECT * FROM conversa where usuario = ? and usuario2 = ?";
	final private String SACAR_CONVERSACION = "SELECT * FROM conversa where usuario = ? or usuario2 = ? or usuario = ? ";

	// Alter
	final private String SUMAR_LIKE = "UPDATE publicacion set numLikes = numLikes + 1 WHERE id_publicacion = ?";
	final private String RESTAR_LIKE = "UPDATE publicacion set numLikes = numLikes - 1 WHERE id_publicacion = ?";

	// Deletes
	final private String QUITAR_LIKES = "DELETE FROM likes WHERE usuario = ? and id_publicacion = ?";

	public void abrirConexion() {

		try {
			Properties configBDA = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\bayro\\Desktop\\RetoFinal\\src\\configBDA.properties");
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return publi;
	}

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

	@Override
	public Publicacion buscarPublicacionXId(String id) {
		Publicacion publi = new Publicacion();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(BUSCAR_PUBLICACIO_X_ID);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				publi = this.getPublicacion(publi, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();

		return publi;
	}

	@Override
	public void publicar(Publicacion publi) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(PUBLICAR);
			stmt = setPublicacion(publi, stmt);

			System.out.println(stmt);

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

				System.out.println(stmt);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
	}

	@Override
	public int numPublicaciones() {
		int numPubli = 0;
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(NUM_PUBLICACIONES);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				numPubli = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return numPubli;
	}

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

	@Override
	public String calcularId(String string) {
		String cod = "";
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(ULTIMA_PUBLICACION);
			stmt.setString(1, string);

			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cod = rs.getString("id_publicacion");
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

	@Override
	public int numPublicacionesHerencia(String tipo) {
		int numPubli = 0;
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(NUM_PUBLICACIONES_POR_TIPO);
			stmt.setString(1, tipo);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				numPubli = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cerrarConexion();
		return numPubli;
	}

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
				usu.setIcono(rs.getString("icono"));
				usuarios.add(usu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cerrarConexion();
		return usuarios;
	}

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
				usuarios.add(usu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cerrarConexion();
		return usuarios;
	}

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
			System.out.println(stmt);
			stmt.execute();

			System.out.println(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cerrarConexion();

	}

	@Override
	public void quirarLike(String usuario, String publicacion) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(QUITAR_LIKES);
			stmt.setString(1, usuario);
			stmt.setString(2, publicacion);

			System.out.println(stmt);
			stmt.execute();

			stmt = con.prepareStatement(RESTAR_LIKE);
			stmt.setString(1, publicacion);
			stmt.execute();

			System.out.println(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.cerrarConexion();

	}

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

	@Override
	public boolean buscarConver(String usuario) {
		boolean existeConver = false;
		
		this.abrirConexion();
		
		try {
			stmt = con.prepareStatement(comprobarConver);
			stmt.setString(1, usuario);
			stmt.setString(2, usuario);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				existeConver = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cerrarConexion();
		return existeConver;
	}

	@Override
	public String calcularIdMensaje(String string) {
		String cod = "";
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(ULTIMO_MENSAJE);
			stmt.setString(1, string);

			System.out.println(stmt);
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
	public void insertarMensaje(Mensaje men, String usuario, String usuario2) {
		this.abrirConexion();
		
		try {
			stmt = con.prepareStatement(INSERTAR_MENSAJE);
			stmt.setString(1, men.getUsuario1());
			stmt.setString(2, men.getUsuario2());
			stmt.setString(3, men.getIdMensaje());
			stmt.setString(4, men.getMensaje());
			
			stmt.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		this.cerrarConexion();
	}

	@Override
	public List<Mensaje> sacarIdMensajes(String usuario1, String usuario2) {
		List<Mensaje> mensajes = new ArrayList<>();
		
		this.abrirConexion();
		
		try {
			stmt = con.prepareStatement(SACAR_CONVERSACION);
			stmt.setString(1, usuario1);
			stmt.setString(2, usuario2);
			stmt.setString(3, usuario2);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Mensaje men = new Mensaje();
				men.setUsuario1(rs.getString("usuario"));
				men.setUsuario2(rs.getString("usuario2"));
				men.setIdMensaje(rs.getString("id_mensaje"));
				men.setMensaje(rs.getString("mensaje"));
				mensajes.add(men);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cerrarConexion();
		return mensajes;
		
	}

}
