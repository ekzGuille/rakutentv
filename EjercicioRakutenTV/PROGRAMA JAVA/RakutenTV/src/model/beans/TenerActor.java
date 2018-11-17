package model.beans;

public class TenerActor {
	private int idTenerActor;
	private int idActor;
	private int idPelicula;

	public TenerActor() {
	}

	public TenerActor(int idTenerActor, int idActor, int idPelicula) {
		this.idTenerActor = idTenerActor;
		this.idActor = idActor;
		this.idPelicula = idPelicula;
	}

	public int getIdTenerActor() {
		return idTenerActor;
	}

	public void setIdTenerActor(int idTenerActor) {
		this.idTenerActor = idTenerActor;
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

}
