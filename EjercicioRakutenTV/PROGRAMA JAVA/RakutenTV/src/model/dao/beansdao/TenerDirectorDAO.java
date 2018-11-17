package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.TenerActor;
import model.beans.TenerDirector;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class TenerDirectorDAO implements DAO<TenerDirector, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public TenerDirectorDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(TenerDirector bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TenerDirector bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TenerDirector> findAll() {
		String sql = "SELECT * FROM `tenerdirector`";
		List<TenerDirector> lstTenerDirector = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerDirector = new ArrayList<TenerDirector>();

			while (rs.next()) {
				TenerDirector tenerDirector = new TenerDirector();

				tenerDirector.setIdTenerDirector(rs.getInt(1));
				tenerDirector.setIdDirector(rs.getInt(2));
				tenerDirector.setIdPelicula(rs.getInt(3));

				lstTenerDirector.add(tenerDirector);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstTenerDirector;
	}

	@Override
	public TenerDirector findById(Integer id) {
		String sql = "SELECT * FROM `tenerdirector` WHERE `idTenerDirector` = ?";
		List<TenerDirector> lstTenerDirector = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerDirector = new ArrayList<TenerDirector>();

			while (rs.next()) {
				TenerDirector tenerDirector = new TenerDirector();

				tenerDirector.setIdTenerDirector(rs.getInt(1));
				tenerDirector.setIdDirector(rs.getInt(2));
				tenerDirector.setIdPelicula(rs.getInt(3));

				lstTenerDirector.add(tenerDirector);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstTenerDirector.isEmpty()) ? lstTenerDirector.get(0) : null;
	}
}
