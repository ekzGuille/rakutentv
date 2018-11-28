package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.beans.Pelicula;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class PeliculaDAO implements DAO<Pelicula, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public PeliculaDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Pelicula bean) {
		String sql = "INSERT INTO `pelicula`(`tituloPeli`, `resumenPeli`, `trailerPeli`, `caratulaPeli`, `imagenPeli`, `fechaEstreno`, `audiosDisponibles`, `subtitulosDisponibles`, `duracionPeli`, `precioPeli`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getTituloPeli());
			pst.setString(2, bean.getResumenPeli());
			pst.setString(3, bean.getTrailerPeli());
			pst.setString(4, bean.getCaratulaPeli());
			pst.setString(5, bean.getImagenPeli());
			pst.setString(6, bean.getFechaEstreno());
			pst.setString(7, bean.getAudiosDisponibles());
			pst.setString(8, bean.getSubtitulosDisponibles());
			pst.setInt(9, bean.getDuracionPeli());
			pst.setDouble(10, bean.getPrecioPeli());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM `pelicula` WHERE `idPelicula` = ?";

		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(Pelicula bean) {

		String sql = "UPDATE `pelicula` SET ";
		HashMap<Integer, Object> lstCondiciones = new HashMap<>();
		int contarCasos = 0;
		int resp = 0;

		if (bean.getTituloPeli() != null) {
			sql += "`tituloPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getTituloPeli());
		}
		if (bean.getResumenPeli() != null) {
			sql += "`resumenPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getResumenPeli());
		}
		if (bean.getTrailerPeli() != null) {
			sql += "`trailerPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getTrailerPeli());
		}
		if (bean.getCaratulaPeli() != null) {
			sql += "`caratulaPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getCaratulaPeli());
		}
		if (bean.getImagenPeli() != null) {
			sql += "`imagenPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getImagenPeli());
		}
		if (bean.getCaratulaPeli() != null) {
			sql += "`caratulaPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getCaratulaPeli());
		}
		if (bean.getFechaEstreno() != null) {
			sql += "`fechaEstreno`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getFechaEstreno());
		}
		if (bean.getAudiosDisponibles() != null) {
			sql += "`audiosDisponibles`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getAudiosDisponibles());
		}
		if (bean.getSubtitulosDisponibles() != null) {
			sql += "`subtitulosDisponibles`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getSubtitulosDisponibles());
		}
		if (bean.getDuracionPeli() > 0) {
			sql += "`duracionPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getDuracionPeli());
		}
		if (bean.getPrecioPeli() > 0) {
			sql += "`precioPeli`= ?, ";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getPrecioPeli());
		}

		if (sql.endsWith(",")) {
			sql = sql.substring(0, sql.length() - 1);
		}

		sql += " WHERE `idPelicula` = ?";
		contarCasos++;
		lstCondiciones.put(contarCasos, bean.getIdPelicula());

		try {
			pst = this.motor.connect().prepareStatement(sql);

			for (Map.Entry<Integer, Object> item : lstCondiciones.entrySet()) {
				Integer contar = item.getKey();
				Object valor = item.getValue();

				if (valor instanceof String) {
					pst.setString(contar, (String) valor);
				}
				if (valor instanceof Integer) {
					pst.setInt(contar, (Integer) valor);
				}
				if (valor instanceof Double) {
					pst.setDouble(contar, (Double) valor);
				}
			}

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public List<Pelicula> findAll() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	@Override
	public Pelicula findById(Integer id) {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE pelicula.idPelicula = ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return (!lstPeliculas.isEmpty()) ? lstPeliculas.get(0) : null;
	}

	public List<Pelicula> findAllTituloAZ() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`tituloPeli` ASC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	public List<Pelicula> findAllTituloZA() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`tituloPeli` DESC";
		List<Pelicula> lstPeliculas = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();
			
			while (rs.next()) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));
				
				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));
				
				lstPeliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findAllEstrenosNuevas() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`fechaEstreno` DESC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	public List<Pelicula> findAllEstrenosViejas() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`fechaEstreno`";
		List<Pelicula> lstPeliculas = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();
			
			while (rs.next()) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));
				
				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));
				
				lstPeliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findEstrenos(Integer cantidad) {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`fechaEstreno` DESC LIMIT ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, cantidad);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findUltimasAdd(Integer cantidad) {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`idPelicula` DESC LIMIT ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, cantidad);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findMasVotada(Integer cantidad) {
		String sql = "";
		if (cantidad == 0) {
			sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones`  FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `valoracionesTotales` DESC";
		} else {
			sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones`  FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `valoracionesTotales` DESC LIMIT ?";
		}
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			if (cantidad > 0) {
				pst.setInt(1, cantidad);
			}

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findMejorVotada(Integer cantidad) {
		String sql = "";
		if (cantidad == 0) {
			sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `mediaValoraciones` DESC";
		} else {
			sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `mediaValoraciones` DESC LIMIT ?";
		}
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			if (cantidad > 0) {
				pst.setInt(1, cantidad);
			}

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findPeliculaByGenero(String descGenero) {
		String sql = "SELECT `pelicula`.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`,`valoracionesTotales`, `mediaValoraciones` FROM `genero`,`tenergenero`,`pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE `pelicula`.`idPelicula` = `tenergenero`.`idPelicula` AND `genero`.`idGenero` = `tenergenero`.`idGenero` AND `genero`.`descGenero` LIKE ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, descGenero);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findPeliculaByGenero(Integer idGenero) {
		String sql = "SELECT `pelicula`.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`,`valoracionesTotales`, `mediaValoraciones` FROM `genero`,`tenergenero`,`pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE `pelicula`.`idPelicula` = `tenergenero`.`idPelicula` AND `genero`.`idGenero` = `tenergenero`.`idGenero` AND `genero`.`idGenero` = ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, idGenero);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findByNombre(String nombre) {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE `tituloPeli` LIKE ?";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			pst.setString(1, "%" + nombre + "%");

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findCompradas(Integer idUsuario) {
		String sql = "SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones`, compra.fechaCompra FROM `compra`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `compra`.`idPelicula` = `pelicula`.`idPelicula` AND `compra`.`idUsuario` = ? ORDER BY `compra`.`fechaCompra` DESC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, idUsuario);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findPuntuadas(Integer idUsuario) {
		String sql = "SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones`, puntuacion.idInfoPuntuacion FROM `puntuacion`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `puntuacion`.`idPelicula` = `pelicula`.`idPelicula` AND `puntuacion`.`idUsuario` = ? ORDER BY `puntuacion`.`idInfoPuntuacion` DESC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, idUsuario);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findFavoritas(Integer idUsuario) {
		String sql = "SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones`, marcarfavorito.idMarcarFavorito FROM `marcarfavorito`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `marcarfavorito`.`idPelicula` = `pelicula`.`idPelicula` AND `marcarfavorito`.`idUsuario` = ? ORDER BY `marcarfavorito`.`idMarcarFavorito` DESC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			pst.setInt(1, idUsuario);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	
	public List<Pelicula> findAllMejorVotadas() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `valoracionglobalpelicula`.`mediaValoraciones` DESC";
		List<Pelicula> lstPeliculas = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();

			while (rs.next()) {
				Pelicula pelicula = new Pelicula();

				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));

				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));

				lstPeliculas.add(pelicula);
			}

		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	public List<Pelicula> findAllMasVotadas() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `valoracionglobalpelicula`.`valoracionesTotales` DESC";
		List<Pelicula> lstPeliculas = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();
			
			while (rs.next()) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));
				
				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));
				
				lstPeliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}

	public List<Pelicula> findAllCaras() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`precioPeli` DESC";
		List<Pelicula> lstPeliculas = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();
			
			while (rs.next()) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));
				
				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));
				
				lstPeliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	public List<Pelicula> findAllBaratas() {
		String sql = "SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`precioPeli` ASC";
		List<Pelicula> lstPeliculas = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPeliculas = new ArrayList<Pelicula>();
			
			while (rs.next()) {
				Pelicula pelicula = new Pelicula();
				
				pelicula.setIdPelicula(rs.getInt(1));
				pelicula.setTituloPeli(rs.getString(2));
				pelicula.setResumenPeli(rs.getString(3));
				pelicula.setTrailerPeli(rs.getString(4));
				pelicula.setCaratulaPeli(rs.getString(5));
				pelicula.setImagenPeli(rs.getString(6));
				pelicula.setFechaEstreno(new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate(7)));
				pelicula.setAudiosDisponibles(rs.getString(8));
				pelicula.setSubtitulosDisponibles(rs.getString(9));
				pelicula.setDuracionPeli(rs.getInt(10));
				pelicula.setPrecioPeli(rs.getDouble(11));
				
				pelicula.setValoracionesTotales(rs.getInt(12));
				pelicula.setMediaValoraciones(rs.getDouble(13));
				
				lstPeliculas.add(pelicula);
			}
			
		} catch (SQLException e) {
		} finally {
			this.motor.disconnect();
		}
		return lstPeliculas;
	}
	
}
