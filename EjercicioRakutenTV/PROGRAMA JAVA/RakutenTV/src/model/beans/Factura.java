package model.beans;

public class Factura {

	private int idFactura;
	private String descFactura;
	private int idCompra;

	public Factura() {
	}

	public Factura(int idFactura, String descFactura, int idCompra) {
		this.idFactura = idFactura;
		this.descFactura = descFactura;
		this.idCompra = idCompra;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getDescFactura() {
		return descFactura;
	}

	public void setDescFactura(String descFactura) {
		this.descFactura = descFactura;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

}
