package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Compra;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class CompraDAO implements DAO<Compra, Integer[]> {

	private Motor motor;
	private PreparedStatement pst;

	public CompraDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Compra bean) {
		String sql = "INSERT INTO `compra`(`idUsuario`, `idPelicula`, `precioCompra`, `fechaCompra`) VALUES (?,?,?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, bean.getIdUsuario());
			pst.setInt(2, bean.getIdPelicula());
			pst.setDouble(3, bean.getPrecioCompra());
			pst.setString(4,
					String.valueOf(new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date())));

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
	public int update(Compra bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Compra> findAll() {
		String sql = "SELECT * FROM `compra`";

		List<Compra> lstCompra = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstCompra = new ArrayList<Compra>();

			while (rs.next()) {
				Compra compra = new Compra();

				compra.setIdCompra(rs.getInt(1));
				compra.setIdUsuario(rs.getInt(2));
				compra.setIdPelicula(rs.getInt(3));
				compra.setPrecioCompra(rs.getDouble(4));
				compra.setFechaCompra(rs.getString(5));

				lstCompra.add(compra);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return lstCompra;

	}

	@Override
	public Compra findById(Integer[] id) {
		String sql = "SELECT * FROM `compra` WHERE `idCompra` = ?";

		List<Compra> lstCompra = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);

			ResultSet rs = this.motor.executeQuery(pst);
			lstCompra = new ArrayList<Compra>();

			while (rs.next()) {
				Compra compra = new Compra();

				compra.setIdCompra(rs.getInt(1));
				compra.setIdUsuario(rs.getInt(2));
				compra.setIdPelicula(rs.getInt(3));
				compra.setPrecioCompra(rs.getDouble(4));
				compra.setFechaCompra(rs.getString(5));

				lstCompra.add(compra);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return (!lstCompra.isEmpty()) ? lstCompra.get(0) : null;
	}

	public Compra findByPeliUser(Integer[] id) {
		String sql = "SELECT * FROM `compra` WHERE `idPelicula` = ? AND `idUsuario` = ?";

		List<Compra> lstCompra = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id[0]);
			pst.setInt(1, id[1]);

			ResultSet rs = this.motor.executeQuery(pst);
			lstCompra = new ArrayList<Compra>();

			while (rs.next()) {
				Compra compra = new Compra();

				compra.setIdCompra(rs.getInt(1));
				compra.setIdUsuario(rs.getInt(2));
				compra.setIdPelicula(rs.getInt(3));
				compra.setPrecioCompra(rs.getDouble(4));
				compra.setFechaCompra(rs.getString(5));

				lstCompra.add(compra);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return (!lstCompra.isEmpty()) ? lstCompra.get(0) : null;
	}

}
