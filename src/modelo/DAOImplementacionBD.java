package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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
import clases.Publicacion;
import clases.TipoHistoria;

public class DAOImplementacionBD implements DAO {

	private Connection con = null;
	private PreparedStatement stmt;

	// Sentencias SQL

	// Selects
	final private String BUSCAR_PUBLICACIO_X_ID = "SELECT * FROM publicacion WHERE id_publicacion = ?";
	final private String NUM_PUBLICACIONES = "SELECT count(*) FROM publicacion";

	final private String LISTAR_MUSICA = "SELECT titulo FROM cancion";
	final private String LISTAR_TIPO_HISTORIA = "SELECT tipo FROM tipoHistoria";

	public void abrirConexion() {

		try {
			Properties configBDA = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\arceu\\Desktop\\RetoFinal\\src\\configBDA.properties");
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
			
			while(rs.next()) {
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

}
