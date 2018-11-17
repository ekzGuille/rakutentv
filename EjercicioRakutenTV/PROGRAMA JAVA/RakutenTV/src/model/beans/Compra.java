package model.beans;

public class Compra {

	private int idCompra;
	private int idPelicula;
	private int idUsuario;
	private double precioCompra;
	private String fechaCompra;

	public Compra() {
	}

	public Compra(int idCompra, int idPelicula, int idUsuario, double precioCompra, String fechaCompra) {
		this.idCompra = idCompra;
		this.idPelicula = idPelicula;
		this.idUsuario = idUsuario;
		this.precioCompra = precioCompra;
		this.fechaCompra = fechaCompra;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public String getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(String fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

}
