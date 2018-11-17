package model.beans;

public class MetodoPago {

	private int idMetodoPago;
	private String descMetodoPago;

	public MetodoPago() {
	}

	public MetodoPago(int idMetodoPago, String descMetodoPago) {
		this.idMetodoPago = idMetodoPago;
		this.descMetodoPago = descMetodoPago;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getDescMetodoPago() {
		return descMetodoPago;
	}

	public void setDescMetodoPago(String descMetodoPago) {
		this.descMetodoPago = descMetodoPago;
	}

}
