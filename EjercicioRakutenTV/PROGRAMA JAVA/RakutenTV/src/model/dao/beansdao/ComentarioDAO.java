package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Comentario;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class ComentarioDAO implements DAO<Comentario, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public ComentarioDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Comentario bean) {
		String sql = "INSERT INTO `comentario` (`descComentario`, `fechaComentario`, `idPelicula`, `idUsuario`) VALUES (?,?,?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getDescComentario());
			pst.setString(2,
					String.valueOf(new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date())));
			pst.setInt(3, bean.getIdPelicula());
			pst.setInt(4, bean.getIdUsuario());

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM `comentario` WHERE `idComentario`= ?";
		int resp = 0;

		try {

			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			resp = this.motor.execute(pst);
		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(Comentario bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comentario> findAll() {
		String sql = "SELECT * FROM `comentario` ";

		List<Comentario> lstComentario = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstComentario = new ArrayList<Comentario>();

			while (rs.next()) {
				Comentario comentario = new Comentario();

				comentario.setIdComentario(rs.getInt(1));
				comentario.setDescComentario(rs.getString(2));
				comentario.setFechaComentario(rs.getString(3));
				comentario.setIdPelicula(rs.getInt(4));
				comentario.setIdUsuario((rs.getInt(5)));

				lstComentario.add(comentario);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstComentario;
	}

	@Override
	public Comentario findById(Integer id) {
		String sql = "SELECT * FROM `comentario` ";

		List<Comentario> lstComentario = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstComentario = new ArrayList<Comentario>();

			while (rs.next()) {
				Comentario comentario = new Comentario();

				comentario.setIdComentario(rs.getInt(1));
				comentario.setDescComentario(rs.getString(2));
				comentario.setFechaComentario(rs.getString(3));
				comentario.setIdPelicula(rs.getInt(4));
				comentario.setIdUsuario((rs.getInt(5)));

				lstComentario.add(comentario);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstComentario.isEmpty()) ? lstComentario.get(0) : null;

	}

}
