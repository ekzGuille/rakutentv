package model.beans;

public class MarcarFavorito {
	private int idMarcarFavorito;
	private int idPelicula;
	private int idUsuario;

	public MarcarFavorito() {
	}

	public MarcarFavorito(int idMarcarFavorito, int idPelicula, int idUsuario) {
		this.idMarcarFavorito = idMarcarFavorito;
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
	}

	public int getIdMarcarFavorito() {
		return idMarcarFavorito;
	}

	public void setIdMarcarFavorito(int idMarcarFavorito) {
		this.idMarcarFavorito = idMarcarFavorito;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
