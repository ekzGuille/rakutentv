package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Puntuacion;
import model.beans.TenerActor;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class TenerActorDAO implements DAO<TenerActor, Integer> {

	private Motor motor;
	private PreparedStatement pst;
	
	public TenerActorDAO() {
		motor = new MotorMySQL();
	}
	
	@Override
	public int add(TenerActor bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TenerActor bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TenerActor> findAll() {
		String sql = "SELECT * FROM `teneractor`";
		List<TenerActor> lstTenerActor = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerActor = new ArrayList<TenerActor>();

			while (rs.next()) {
				TenerActor tenerActor = new TenerActor();
				
				tenerActor.setIdTenerActor(rs.getInt(1));
				tenerActor.setIdActor(rs.getInt(2));
				tenerActor.setIdPelicula(rs.getInt(3));
				
				lstTenerActor.add(tenerActor);
				
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstTenerActor;
	}

	@Override
	public TenerActor findById(Integer id) {
		String sql = "SELECT * FROM `teneractor` WHERE `idTenerActor` = ?";
		List<TenerActor> lstTenerActor = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstTenerActor = new ArrayList<TenerActor>();

			while (rs.next()) {
				TenerActor tenerActor = new TenerActor();
				
				tenerActor.setIdTenerActor(rs.getInt(1));
				tenerActor.setIdActor(rs.getInt(2));
				tenerActor.setIdPelicula(rs.getInt(3));
				
				lstTenerActor.add(tenerActor);
				
			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstTenerActor.isEmpty()) ? lstTenerActor.get(0) : null;
	}

}
