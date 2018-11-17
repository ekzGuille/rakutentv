package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Puntuacion;
import model.beans.ValoracionGlobalPelicula;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class ValoracionGlobalPeliculaDAO implements DAO<ValoracionGlobalPelicula, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public ValoracionGlobalPeliculaDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(ValoracionGlobalPelicula bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ValoracionGlobalPelicula bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ValoracionGlobalPelicula> findAll() {
		String sql = "SELECT * FROM `valoracionglobalpelicula`";
		List<ValoracionGlobalPelicula> lstValoracionGlobalPelicula = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);
			lstValoracionGlobalPelicula = new ArrayList<ValoracionGlobalPelicula>();

			while (rs.next()) {
				ValoracionGlobalPelicula valoracionGlobalPelicula = new ValoracionGlobalPelicula();

				valoracionGlobalPelicula.setIdPelicula(rs.getInt(1));
				valoracionGlobalPelicula.setValoracionesTotales(rs.getInt(2));
				valoracionGlobalPelicula.setMediaValoraciones(rs.getDouble(3));

				lstValoracionGlobalPelicula.add(valoracionGlobalPelicula);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstValoracionGlobalPelicula;
	}

	@Override
	public ValoracionGlobalPelicula findById(Integer id) {
		String sql = "SELECT * FROM `valoracionglobalpelicula` WHERE `idPelicula` = ?";
		List<ValoracionGlobalPelicula> lstValoracionGlobalPelicula = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstValoracionGlobalPelicula = new ArrayList<ValoracionGlobalPelicula>();

			while (rs.next()) {
				ValoracionGlobalPelicula valoracionGlobalPelicula = new ValoracionGlobalPelicula();

				valoracionGlobalPelicula.setIdPelicula(rs.getInt(1));
				valoracionGlobalPelicula.setValoracionesTotales(rs.getInt(2));
				valoracionGlobalPelicula.setMediaValoraciones(rs.getDouble(3));

				lstValoracionGlobalPelicula.add(valoracionGlobalPelicula);
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstValoracionGlobalPelicula.isEmpty()) ? lstValoracionGlobalPelicula.get(0) : null;
	}

}
