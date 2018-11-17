package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.Actor;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class ActorDAO implements DAO<Actor, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public ActorDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Actor bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Actor bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Actor> findAll() {
		String sql = "SELECT * FROM `actor`";
		List<Actor> lstActor = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);

			lstActor = new ArrayList<Actor>();

			while (rs.next()) {
				Actor actor = new Actor();

				actor.setIdActor(rs.getInt(1));
				actor.setNombreActor(rs.getString(2));
				actor.setApellidosActor(rs.getString(3));
				actor.setFotoActor(rs.getString(4));

				lstActor.add(actor);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstActor;
	}

	@Override
	public Actor findById(Integer id) {
		String sql = "SELECT * FROM `actor` WHERE `idActor` = ?";
		List<Actor> lstActor = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);

			lstActor = new ArrayList<Actor>();

			while (rs.next()) {
				Actor actor = new Actor();

				actor.setIdActor(rs.getInt(1));
				actor.setNombreActor(rs.getString(2));
				actor.setApellidosActor(rs.getString(3));
				actor.setFotoActor(rs.getString(4));

				lstActor.add(actor);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstActor.isEmpty()) ? lstActor.get(0) : null;
	}

}
