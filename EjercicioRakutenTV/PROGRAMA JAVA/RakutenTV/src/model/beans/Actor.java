package model.beans;

public class Actor {

	private int idActor;
	private String nombreActor;
	private String apellidosActor;
	private String fotoActor;

	public Actor() {
	}

	public Actor(int idActor, String nombreActor, String apellidosActor, String fotoActor) {
		this.idActor = idActor;
		this.nombreActor = nombreActor;
		this.apellidosActor = apellidosActor;
		this.fotoActor = fotoActor;
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public String getNombreActor() {
		return nombreActor;
	}

	public void setNombreActor(String nombreActor) {
		this.nombreActor = nombreActor;
	}

	public String getApellidosActor() {
		return apellidosActor;
	}

	public void setApellidosActor(String apellidosActor) {
		this.apellidosActor = apellidosActor;
	}

	public String getFotoActor() {
		return fotoActor;
	}

	public void setFotoActor(String fotoActor) {
		this.fotoActor = fotoActor;
	}

}
