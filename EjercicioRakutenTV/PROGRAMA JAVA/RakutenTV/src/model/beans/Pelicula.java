package model.beans;

import java.util.List;

public class Pelicula {

	private int idPelicula;
	private String tituloPeli;
	private String resumenPeli;
	private String trailerPeli;
	private String caratulaPeli;
	private String imagenPeli;
	private String fechaEstreno;
	private String audiosDisponibles;
	private String subtitulosDisponibles;
	private int duracionPeli;
	private double precioPeli;
	private List<Integer> idDirectorPeli;
	private List<Integer> idGeneroPeli;
	private List<Integer> idActorPeli;
	private List<Integer> idComentarioPeli;

	private int valoracionesTotales;
	private double mediaValoraciones;

	public Pelicula() {
	}

	public Pelicula(int idPelicula, String tituloPeli, String resumenPeli, String trailerPeli, String caratulaPeli,
			String imagenPeli, String fechaEstreno, String audiosDisponibles, String subtitulosDisponibles,
			int duracionPeli, double precioPeli, List<Integer> idDirectorPeli, List<Integer> idGeneroPeli,
			List<Integer> idActorPeli, List<Integer> idComentarioPeli, int valoracionesTotales,
			double mediaValoraciones) {
		this.idPelicula = idPelicula;
		this.tituloPeli = tituloPeli;
		this.resumenPeli = resumenPeli;
		this.trailerPeli = trailerPeli;
		this.caratulaPeli = caratulaPeli;
		this.imagenPeli = imagenPeli;
		this.fechaEstreno = fechaEstreno;
		this.audiosDisponibles = audiosDisponibles;
		this.subtitulosDisponibles = subtitulosDisponibles;
		this.duracionPeli = duracionPeli;
		this.precioPeli = precioPeli;
		this.idDirectorPeli = idDirectorPeli;
		this.idGeneroPeli = idGeneroPeli;
		this.idActorPeli = idActorPeli;
		this.idComentarioPeli = idComentarioPeli;
		this.valoracionesTotales = valoracionesTotales;
		this.mediaValoraciones = mediaValoraciones;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTituloPeli() {
		return tituloPeli;
	}

	public void setTituloPeli(String tituloPeli) {
		this.tituloPeli = tituloPeli;
	}

	public String getResumenPeli() {
		return resumenPeli;
	}

	public void setResumenPeli(String resumenPeli) {
		this.resumenPeli = resumenPeli;
	}

	public String getTrailerPeli() {
		return trailerPeli;
	}

	public void setTrailerPeli(String trailerPeli) {
		this.trailerPeli = trailerPeli;
	}

	public String getCaratulaPeli() {
		return caratulaPeli;
	}

	public void setCaratulaPeli(String caratulaPeli) {
		this.caratulaPeli = caratulaPeli;
	}

	public String getImagenPeli() {
		return imagenPeli;
	}

	public void setImagenPeli(String imagenPeli) {
		this.imagenPeli = imagenPeli;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public String getAudiosDisponibles() {
		return audiosDisponibles;
	}

	public void setAudiosDisponibles(String audiosDisponibles) {
		this.audiosDisponibles = audiosDisponibles;
	}

	public String getSubtitulosDisponibles() {
		return subtitulosDisponibles;
	}

	public void setSubtitulosDisponibles(String subtitulosDisponibles) {
		this.subtitulosDisponibles = subtitulosDisponibles;
	}

	public int getDuracionPeli() {
		return duracionPeli;
	}

	public void setDuracionPeli(int duracionPeli) {
		this.duracionPeli = duracionPeli;
	}

	public double getPrecioPeli() {
		return precioPeli;
	}

	public void setPrecioPeli(double precioPeli) {
		this.precioPeli = precioPeli;
	}

	public List<Integer> getIdDirectorPeli() {
		return idDirectorPeli;
	}

	public void setIdDirectorPeli(List<Integer> idDirectorPeli) {
		this.idDirectorPeli = idDirectorPeli;
	}

	public List<Integer> getIdGeneroPeli() {
		return idGeneroPeli;
	}

	public void setIdGeneroPeli(List<Integer> idGeneroPeli) {
		this.idGeneroPeli = idGeneroPeli;
	}

	public List<Integer> getIdActorPeli() {
		return idActorPeli;
	}

	public void setIdActorPeli(List<Integer> idActorPeli) {
		this.idActorPeli = idActorPeli;
	}

	public List<Integer> getIdComentarioPeli() {
		return idComentarioPeli;
	}

	public void setIdComentarioPeli(List<Integer> idComentarioPeli) {
		this.idComentarioPeli = idComentarioPeli;
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
