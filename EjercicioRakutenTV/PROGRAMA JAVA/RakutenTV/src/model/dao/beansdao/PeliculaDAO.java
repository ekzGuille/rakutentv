package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.util.List;

import model.beans.Pelicula;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class PeliculaDAO implements DAO<Pelicula, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public PeliculaDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Pelicula bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Pelicula bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Pelicula> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pelicula findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
