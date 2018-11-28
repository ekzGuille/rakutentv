package utils;

public class Respuesta {
	private String descRespuesta;
	private int respuesta;

	
	public Respuesta() {
	}

	public String getDescRespuesta() {
		return descRespuesta;
	}

	public void setDescRespuesta(String descRespuesta) {
		this.descRespuesta = descRespuesta;
	}

	public int getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}

	public Respuesta(String descRespuesta, int respuesta) {
		this.descRespuesta = descRespuesta;
		this.respuesta = respuesta;
	}
}
