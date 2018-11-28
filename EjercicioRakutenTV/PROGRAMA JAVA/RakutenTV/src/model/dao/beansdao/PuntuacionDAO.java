package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.beans.Puntuacion;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class PuntuacionDAO implements DAO<Puntuacion, Integer[]> {

	private Motor motor;
	private PreparedStatement pst;

	public PuntuacionDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Puntuacion bean) {
		String sql = "INSERT INTO `puntuacion` (`idPelicula`, `idUsuario`, `idInfoPuntuacion`, `fechaPuntuacion`) VALUES (?,?,?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, bean.getIdPelicula());
			pst.setInt(2, bean.getIdUsuario());
			pst.setInt(3, bean.getIdInfoPuntuacion());
			pst.setString(4, String.valueOf(java.time.LocalDate.now()));

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer[] id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Puntuacion bean) {
		String sql = "UPDATE `puntuacion` SET ";
		HashMap<Integer, Object> lstCondiciones = new HashMap<>();
		int contarCasos = 0;
		int resp = 0;

		if (bean.getIdPelicula() > 0) {
			sql += "`idPelicula` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getIdPelicula());
		}
		if (bean.getIdUsuario() > 0) {
			sql += "`idUsuario` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getIdUsuario());
		}
		if (bean.getIdInfoPuntuacion() > 0) {
			sql += "`idInfoPuntuacion` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getIdInfoPuntuacion());
		}
		if (bean.getFechaPuntuacion() != null) {
			sql += "`fechaPuntuacion` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, String.valueOf(java.time.LocalDate.now()));
		}

		if (sql.endsWith(",")) {
			sql = sql.substring(0, sql.length() - 1);
		}

		sql += " WHERE `idPelicula` = ? AND `idUsuario` = ?";
		contarCasos++;
		lstCondiciones.put(contarCasos, bean.getIdPelicula());
		contarCasos++;
		lstCondiciones.put(contarCasos, bean.getIdUsuario());

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
			}

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public List<Puntuacion> findAll() {
		String sql = "SELECT * FROM `puntuacion`";
		List<Puntuacion> lstPuntuacion = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstPuntuacion = new ArrayList<Puntuacion>();

			while (rs.next()) {
				Puntuacion puntuacion = new Puntuacion();

				puntuacion.setIdInfoPuntuacion(rs.getInt(1));
				puntuacion.setIdPelicula(rs.getInt(2));
				puntuacion.setIdUsuario(rs.getInt(3));
				puntuacion.setIdInfoPuntuacion(rs.getInt(4));
				puntuacion.setFechaPuntuacion(rs.getString(5));

				lstPuntuacion.add(puntuacion);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstPuntuacion;
	}

	@Override
	public Puntuacion findById(Integer[] id) {
		String sql = "SELECT * FROM `puntuacion` WHERE `idPuntuacion` = ?";
		List<Puntuacion> lstPuntuacion = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);

			ResultSet rs = this.motor.executeQuery(pst);
			lstPuntuacion = new ArrayList<Puntuacion>();

			while (rs.next()) {
				Puntuacion puntuacion = new Puntuacion();

				puntuacion.setIdInfoPuntuacion(rs.getInt(1));
				puntuacion.setIdPelicula(rs.getInt(2));
				puntuacion.setIdUsuario(rs.getInt(3));
				puntuacion.setIdInfoPuntuacion(rs.getInt(4));
				puntuacion.setFechaPuntuacion(rs.getString(5));

				lstPuntuacion.add(puntuacion);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstPuntuacion.isEmpty()) ? lstPuntuacion.get(0) : null;
	}

	public Puntuacion findByPeliUser(Integer[] id) {
		String sql = "SELECT * FROM `puntuacion`  WHERE `idPelicula` = ? AND `idUsuario` = ?";
		List<Puntuacion> lstPuntuacion = null;
		
		try {
			pst = this.motor.connect().prepareStatement(sql);
			
			pst.setInt(1, id[0]);
			pst.setInt(1, id[1]);

			
			ResultSet rs = this.motor.executeQuery(pst);
			lstPuntuacion = new ArrayList<Puntuacion>();
			
			while (rs.next()) {
				Puntuacion puntuacion = new Puntuacion();
				
				puntuacion.setIdInfoPuntuacion(rs.getInt(1));
				puntuacion.setIdPelicula(rs.getInt(2));
				puntuacion.setIdUsuario(rs.getInt(3));
				puntuacion.setIdInfoPuntuacion(rs.getInt(4));
				puntuacion.setFechaPuntuacion(rs.getString(5));
				
				lstPuntuacion.add(puntuacion);
			}
			
		} catch (SQLException e) {
			
		} finally {
			this.motor.disconnect();
		}
		
		return (!lstPuntuacion.isEmpty()) ? lstPuntuacion.get(0) : null;
	}

}
