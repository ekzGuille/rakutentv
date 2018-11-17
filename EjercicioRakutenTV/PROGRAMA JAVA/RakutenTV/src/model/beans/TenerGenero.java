package model.beans;

public class TenerGenero {

	private int idTenerGenero;
	private int idGenero;
	private int iDPelicula;

	public TenerGenero() {
	}

	public TenerGenero(int idTenerGenero, int idGenero, int iDPelicula) {
		this.idTenerGenero = idTenerGenero;
		this.idGenero = idGenero;
		this.iDPelicula = iDPelicula;
	}

	public int getIdTenerGenero() {
		return idTenerGenero;
	}

	public void setIdTenerGenero(int idTenerGenero) {
		this.idTenerGenero = idTenerGenero;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public int getiDPelicula() {
		return iDPelicula;
	}

	public void setiDPelicula(int iDPelicula) {
		this.iDPelicula = iDPelicula;
	}

}
