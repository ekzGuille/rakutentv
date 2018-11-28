package model.beans;

public class Puntuacion {

	private int idPuntuacion;
	private int idPelicula;
	private int idUsuario;
	private int idInfoPuntuacion;
	private String fechaPuntuacion;

	public Puntuacion() {
	}

	public Puntuacion(int idPuntuacion, int idPelicula, int idUsuario, int idInfoPuntuacion, String fechaPuntuacion) {
		this.idPuntuacion = idPuntuacion;
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
		this.idInfoPuntuacion = idInfoPuntuacion;
		this.fechaPuntuacion = fechaPuntuacion;
	}

	public int getIdPuntuacion() {
		return idPuntuacion;
	}

	public void setIdPuntuacion(int idPuntuacion) {
		this.idPuntuacion = idPuntuacion;
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

	public int getIdInfoPuntuacion() {
		return idInfoPuntuacion;
	}

	public void setIdInfoPuntuacion(int idInfoPuntuacion) {
		this.idInfoPuntuacion = idInfoPuntuacion;
	}

	public String getFechaPuntuacion() {
		return fechaPuntuacion;
	}

	public void setFechaPuntuacion(String fechaPuntuacion) {
		this.fechaPuntuacion = fechaPuntuacion;
	}

	@Override
	public String toString() {
		return "Puntuacion [idPuntuacion=" + idPuntuacion + ", idPelicula=" + idPelicula + ", idUsuario=" + idUsuario
				+ ", idInfoPuntuacion=" + idInfoPuntuacion + ", fechaPuntuacion=" + fechaPuntuacion + "]";
	}

	  
}
