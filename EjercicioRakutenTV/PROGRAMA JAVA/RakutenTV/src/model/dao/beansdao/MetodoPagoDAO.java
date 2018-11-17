package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.MetodoPago;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class MetodoPagoDAO implements DAO<MetodoPago, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public MetodoPagoDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(MetodoPago bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MetodoPago bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MetodoPago> findAll() {
		String sql = "SELECT * FROM `metodopago`";
		List<MetodoPago> lstMetodoPago = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);

			lstMetodoPago = new ArrayList<MetodoPago>();

			while (rs.next()) {
				MetodoPago metodoPago = new MetodoPago();

				metodoPago.setIdMetodoPago(rs.getInt(1));
				metodoPago.setDescMetodoPago(rs.getString(2));

				lstMetodoPago.add(metodoPago);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstMetodoPago;
	}

	@Override
	public MetodoPago findById(Integer id) {
		String sql = "SELECT * FROM `metodopago` WHERE `idMetodoPago` = ?";
		List<MetodoPago> lstMetodoPago = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);

			lstMetodoPago = new ArrayList<MetodoPago>();

			while (rs.next()) {
				MetodoPago metodoPago = new MetodoPago();

				metodoPago.setIdMetodoPago(rs.getInt(1));
				metodoPago.setDescMetodoPago(rs.getString(2));

				lstMetodoPago.add(metodoPago);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstMetodoPago.isEmpty()) ? lstMetodoPago.get(0) : null;
	}

}
