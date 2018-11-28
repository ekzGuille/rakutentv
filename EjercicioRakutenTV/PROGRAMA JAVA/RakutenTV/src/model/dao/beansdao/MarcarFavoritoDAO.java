package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.MarcarFavorito;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class MarcarFavoritoDAO implements DAO<MarcarFavorito, Integer[]> {

	private Motor motor;
	private PreparedStatement pst;

	public MarcarFavoritoDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(MarcarFavorito bean) {
		String sql = "INSERT INTO `marcarfavorito`(`idPelicula`, `idUsuario`) VALUES (?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, bean.getIdPelicula());
			pst.setInt(2, bean.getIdUsuario());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer[] id) {
		String sql = "DELETE FROM `marcarfavorito` WHERE `idPelicula` = ? AND `idUsuario` = ?";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);
			pst.setInt(2, id[1]);

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(MarcarFavorito bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MarcarFavorito> findAll() {
		String sql = "SELECT * FROM `marcarfavorito`";
		List<MarcarFavorito> lstMarcarFavorito = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstMarcarFavorito = new ArrayList<MarcarFavorito>();

			while (rs.next()) {
				MarcarFavorito marcarFavorito = new MarcarFavorito();

				marcarFavorito.setIdMarcarFavorito(rs.getInt(1));
				marcarFavorito.setIdPelicula(rs.getInt(2));
				marcarFavorito.setIdUsuario(rs.getInt(3));

				lstMarcarFavorito.add(marcarFavorito);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstMarcarFavorito;
	}

	@Override
	public MarcarFavorito findById(Integer[] id) {
		String sql = "SELECT * FROM `marcarfavorito` WHERE `idMarcarFavorito` = ?";
		List<MarcarFavorito> lstMarcarFavorito = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);

			ResultSet rs = this.motor.executeQuery(pst);
			lstMarcarFavorito = new ArrayList<MarcarFavorito>();

			while (rs.next()) {
				MarcarFavorito marcarFavorito = new MarcarFavorito();

				marcarFavorito.setIdMarcarFavorito(rs.getInt(1));
				marcarFavorito.setIdPelicula(rs.getInt(2));
				marcarFavorito.setIdUsuario(rs.getInt(3));

				lstMarcarFavorito.add(marcarFavorito);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return (!lstMarcarFavorito.isEmpty()) ? lstMarcarFavorito.get(0) : null;
	}

	public MarcarFavorito findByPeliUser(Integer[] id) {
		String sql = "SELECT * FROM `marcarfavorito` WHERE `idPelicula` = ? AND `idUsuario` = ?";
		List<MarcarFavorito> lstMarcarFavorito = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);
			pst.setInt(2, id[1]);

			ResultSet rs = this.motor.executeQuery(pst);
			lstMarcarFavorito = new ArrayList<MarcarFavorito>();

			while (rs.next()) {
				MarcarFavorito marcarFavorito = new MarcarFavorito();

				marcarFavorito.setIdMarcarFavorito(rs.getInt(1));
				marcarFavorito.setIdPelicula(rs.getInt(2));
				marcarFavorito.setIdUsuario(rs.getInt(3));

				lstMarcarFavorito.add(marcarFavorito);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return (!lstMarcarFavorito.isEmpty()) ? lstMarcarFavorito.get(0) : null;
	}

}
