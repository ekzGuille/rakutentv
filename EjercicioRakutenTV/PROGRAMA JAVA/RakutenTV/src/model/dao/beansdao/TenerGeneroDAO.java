package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.TenerActor;
import model.beans.TenerGenero;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class TenerGeneroDAO implements DAO<TenerGenero, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public TenerGeneroDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(TenerGenero bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TenerGenero bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TenerGenero> findAll() {
		String sql = "SELECT * FROM `tenergenero`";
		List<TenerGenero> lstTenerGenero = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerGenero = new ArrayList<TenerGenero>();

			while (rs.next()) {
				TenerGenero tenerGenero = new TenerGenero();

				tenerGenero.setIdTenerGenero(rs.getInt(1));
				tenerGenero.setIdGenero(rs.getInt(2));
				tenerGenero.setiDPelicula(rs.getInt(3));

				lstTenerGenero.add(tenerGenero);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstTenerGenero;
	}

	@Override
	public TenerGenero findById(Integer id) {
		String sql = "SELECT * FROM `tenergenero` WHERE `idTenerGenero` = ?";
		List<TenerGenero> lstTenerGenero = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerGenero = new ArrayList<TenerGenero>();

			while (rs.next()) {
				TenerGenero tenerGenero = new TenerGenero();

				tenerGenero.setIdTenerGenero(rs.getInt(1));
				tenerGenero.setIdGenero(rs.getInt(2));
				tenerGenero.setiDPelicula(rs.getInt(3));

				lstTenerGenero.add(tenerGenero);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstTenerGenero.isEmpty()) ? lstTenerGenero.get(0) : null;
	}

}
