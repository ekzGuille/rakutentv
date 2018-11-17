package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Director;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class DirectorDAO implements DAO<Director, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public DirectorDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Director bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Director bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Director> findAll() {
		String sql = "SELECT * FROM `director`";
		List<Director> lstDirector = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);

			lstDirector = new ArrayList<Director>();

			while (rs.next()) {
				Director director = new Director();

				director.setIdDirector(rs.getInt(1));
				director.setNombreDirector(rs.getString(2));
				director.setApellidosDirector(rs.getString(3));
				director.setFotoDirector(rs.getString(4));

				lstDirector.add(director);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstDirector;
	}

	@Override
	public Director findById(Integer id) {
		String sql = "SELECT * FROM `director` WHERE `idDirector` = ?";
		List<Director> lstDirector = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);

			lstDirector = new ArrayList<Director>();

			while (rs.next()) {
				Director director = new Director();

				director.setIdDirector(rs.getInt(1));
				director.setNombreDirector(rs.getString(2));
				director.setApellidosDirector(rs.getString(3));
				director.setFotoDirector(rs.getString(4));

				lstDirector.add(director);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstDirector.isEmpty()) ? lstDirector.get(0) : null;
	}
}
