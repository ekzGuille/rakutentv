package model.beans;

public class Comentario {

	private int idComentario;
	private String descComentario;
	private int idUsuario;
	private int idPelicula;
	private String fechaComentario;

	public Comentario() {
	}

	public Comentario(int idComentario, String descComentario, int idUsuario, int idPelicula, String fechaComentario) {
		this.idComentario = idComentario;
		this.descComentario = descComentario;
		this.idUsuario = idUsuario;
		this.idPelicula = idPelicula;
		this.fechaComentario = fechaComentario;
	}

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public String getDescComentario() {
		return descComentario;
	}

	public void setDescComentario(String descComentario) {
		this.descComentario = descComentario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(String fechaComentario) {
		this.fechaComentario = fechaComentario;
	}


}
