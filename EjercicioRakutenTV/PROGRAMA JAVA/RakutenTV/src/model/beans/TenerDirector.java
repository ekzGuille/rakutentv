package model.beans;

public class TenerDirector {
	private int idTenerDirector;
	private int idDirector;
	private int idPelicula;

	public TenerDirector() {
	}

	public TenerDirector(int idTenerDirector, int idDirector, int idPelicula) {
		this.idTenerDirector = idTenerDirector;
		this.idDirector = idDirector;
		this.idPelicula = idPelicula;
	}

	public int getIdTenerDirector() {
		return idTenerDirector;
	}

	public void setIdTenerDirector(int idTenerDirector) {
		this.idTenerDirector = idTenerDirector;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

}
