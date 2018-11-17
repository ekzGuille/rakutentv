package model.beans;

public class ValoracionGlobalPelicula {

	private int idPelicula;
	private int valoracionesTotales;
	private double mediaValoraciones;

	public ValoracionGlobalPelicula() {
	}

	public ValoracionGlobalPelicula(int idPelicula, int valoracionesTotales, double mediaValoraciones) {
		this.idPelicula = idPelicula;
		this.valoracionesTotales = valoracionesTotales;
		this.mediaValoraciones = mediaValoraciones;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getValoracionesTotales() {
		return valoracionesTotales;
	}

	public void setValoracionesTotales(int valoracionesTotales) {
		this.valoracionesTotales = valoracionesTotales;
	}

	public double getMediaValoraciones() {
		return mediaValoraciones;
	}

	public void setMediaValoraciones(double mediaValoraciones) {
		this.mediaValoraciones = mediaValoraciones;
	}

}
