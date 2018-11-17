package model.dao;

import java.util.List;

public interface DAO<E, I> {

	public int add(E bean);

	public int delete(I id);

	public int update(E bean);

	public List<E> findAll();

	public E findById(I id);
}
