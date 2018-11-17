package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Factura;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class FacturaDAO implements DAO<Factura, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public FacturaDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Factura bean) {
		String sql = "INSERT INTO `factura`(`descFactura`, `idCompra`) VALUES (?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getDescFactura());
			pst.setInt(2, bean.getIdCompra());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Factura bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Factura> findAll() {
		String sql = "SELECT * FROM `factura`";

		List<Factura> lstFactura = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstFactura = new ArrayList<Factura>();

			while (rs.next()) {
				Factura factura = new Factura();
				factura.setIdFactura(rs.getInt(1));
				factura.setDescFactura(rs.getString(2));
				factura.setIdCompra(rs.getInt(3));

				lstFactura.add(factura);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstFactura;
	}

	@Override
	public Factura findById(Integer id) {
		String sql = "SELECT * FROM `factura` WHERE `idFactura` = ?";
		List<Factura> lstFactura = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstFactura = new ArrayList<Factura>();

			while (rs.next()) {
				Factura factura = new Factura();
				factura.setIdFactura(rs.getInt(1));
				factura.setDescFactura(rs.getString(2));
				factura.setIdCompra(rs.getInt(3));

				lstFactura.add(factura);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstFactura.isEmpty()) ? lstFactura.get(0) : null;
	}

}
