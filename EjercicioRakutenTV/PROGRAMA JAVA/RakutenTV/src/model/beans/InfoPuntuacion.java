package model.beans;

public class InfoPuntuacion {

	private int idInfoPuntuacion;
	private String descInfoPuntuacion;
	private String fotoInfoPuntuacion;

	public InfoPuntuacion() {
	}

	public InfoPuntuacion(int idInfoPuntuacion, String descInfoPuntuacion, String fotoInfoPuntuacion) {
		this.idInfoPuntuacion = idInfoPuntuacion;
		this.descInfoPuntuacion = descInfoPuntuacion;
		this.fotoInfoPuntuacion = fotoInfoPuntuacion;
	}

	public int getIdInfoPuntuacion() {
		return idInfoPuntuacion;
	}

	public void setIdInfoPuntuacion(int idInfoPuntuacion) {
		this.idInfoPuntuacion = idInfoPuntuacion;
	}

	public String getDescInfoPuntuacion() {
		return descInfoPuntuacion;
	}

	public void setDescInfoPuntuacion(String descInfoPuntuacion) {
		this.descInfoPuntuacion = descInfoPuntuacion;
	}

	public String getFotoInfoPuntuacion() {
		return fotoInfoPuntuacion;
	}

	public void setFotoInfoPuntuacion(String fotoInfoPuntuacion) {
		this.fotoInfoPuntuacion = fotoInfoPuntuacion;
	}

}
