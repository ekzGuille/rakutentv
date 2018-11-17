package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.InfoPuntuacion;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class InfoPuntuacionDAO implements DAO<InfoPuntuacion, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public InfoPuntuacionDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(InfoPuntuacion bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(InfoPuntuacion bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<InfoPuntuacion> findAll() {
		String sql = "SELECT * FROM `infopuntuacion`";
		List<InfoPuntuacion> lstInfoPuntuacion = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstInfoPuntuacion = new ArrayList<InfoPuntuacion>();

			while (rs.next()) {
				InfoPuntuacion infoPuntuacion = new InfoPuntuacion();

				infoPuntuacion.setIdInfoPuntuacion(rs.getInt(1));
				infoPuntuacion.setDescInfoPuntuacion(rs.getString(2));
				infoPuntuacion.setFotoInfoPuntuacion(rs.getString(3));

				lstInfoPuntuacion.add(infoPuntuacion);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstInfoPuntuacion;
	}

	@Override
	public InfoPuntuacion findById(Integer id) {
		String sql = "SELECT * FROM `infopuntuacion` WHERE `idInfoPuntuacion` = ?";
		List<InfoPuntuacion> lstInfoPuntuacion = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstInfoPuntuacion = new ArrayList<InfoPuntuacion>();

			while (rs.next()) {
				InfoPuntuacion infoPuntuacion = new InfoPuntuacion();

				infoPuntuacion.setIdInfoPuntuacion(rs.getInt(1));
				infoPuntuacion.setDescInfoPuntuacion(rs.getString(2));
				infoPuntuacion.setFotoInfoPuntuacion(rs.getString(3));

				lstInfoPuntuacion.add(infoPuntuacion);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstInfoPuntuacion.isEmpty()) ? lstInfoPuntuacion.get(0) : null;
	}

}
