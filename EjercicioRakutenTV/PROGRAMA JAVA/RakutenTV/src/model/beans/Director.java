package model.beans;

public class Director {
	private int idDirector;
	private String nombreDirector;
	private String apellidosDirector;
	private String fotoDirector;

	public Director() {
	}

	public Director(int idDirector, String nombreDirector, String apellidosDirector, String fotoDirector) {
		this.idDirector = idDirector;
		this.nombreDirector = nombreDirector;
		this.apellidosDirector = apellidosDirector;
		this.fotoDirector = fotoDirector;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public String getNombreDirector() {
		return nombreDirector;
	}

	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}

	public String getApellidosDirector() {
		return apellidosDirector;
	}

	public void setApellidosDirector(String apellidosDirector) {
		this.apellidosDirector = apellidosDirector;
	}

	public String getFotoDirector() {
		return fotoDirector;
	}

	public void setFotoDirector(String fotoDirector) {
		this.fotoDirector = fotoDirector;
	}

}
