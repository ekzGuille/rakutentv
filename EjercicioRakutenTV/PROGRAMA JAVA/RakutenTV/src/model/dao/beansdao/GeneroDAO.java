package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Genero;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class GeneroDAO implements DAO<Genero, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public GeneroDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Genero bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Genero bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Genero> findAll() {
		String sql = "SELECT * FROM `genero` ";
		List<Genero> lstGenero = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);

			lstGenero = new ArrayList<Genero>();

			while (rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt(1));
				genero.setDescGenero(rs.getString(2));

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstGenero;
	}

	@Override
	public Genero findById(Integer id) {
		String sql = "SELECT * FROM `genero` WHERE `idGenero` = ?";
		List<Genero> lstGenero = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);

			lstGenero = new ArrayList<Genero>();

			while (rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt(1));
				genero.setDescGenero(rs.getString(2));

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstGenero.isEmpty()) ? lstGenero.get(0) : null;
	}

}
