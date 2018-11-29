package model.beans;

import java.util.List;

import com.google.gson.Gson;

public class Usuario {

	private int idUsuario;
	private String email;
	private String username;
	private String contrasena;
	private String fechaCreacion;
	private String fotoUsuario;
	private int idMetodoPago;
	private String infoMetodoPago;
	private int activoUsuario;
	private List<Integer> compradasPelis;
	private List<Integer> favoritasPelis;

	private int cantidadPelisEnFavoritos;

	public Usuario() {
	}

	public Usuario(int idUsuario, String email, String username, String contrasena, String fechaCreacion,
			String fotoUsuario, int idMetodoPago, String infoMetodoPago, int activoUsuario,
			List<Integer> compradasPelis, List<Integer> favoritasPelis, int cantidadPelisEnFavoritos) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.username = username;
		this.contrasena = contrasena;
		this.fechaCreacion = fechaCreacion;
		this.fotoUsuario = fotoUsuario;
		this.idMetodoPago = idMetodoPago;
		this.infoMetodoPago = infoMetodoPago;
		this.activoUsuario = activoUsuario;
		this.compradasPelis = compradasPelis;
		this.favoritasPelis = favoritasPelis;
		this.cantidadPelisEnFavoritos = cantidadPelisEnFavoritos;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getInfoMetodoPago() {
		return infoMetodoPago;
	}

	public void setInfoMetodoPago(String infoMetodoPago) {
		this.infoMetodoPago = infoMetodoPago;
	}

	public int getActivoUsuario() {
		return activoUsuario;
	}

	public void setActivoUsuario(int activoUsuario) {
		this.activoUsuario = activoUsuario;
	}

	public List<Integer> getCompradasPelis() {
		return compradasPelis;
	}

	public void setCompradasPelis(List<Integer> compradasPelis) {
		this.compradasPelis = compradasPelis;
	}

	public List<Integer> getFavoritasPelis() {
		return favoritasPelis;
	}

	public void setFavoritasPelis(List<Integer> favoritasPelis) {
		this.favoritasPelis = favoritasPelis;
	}

	public int getCantidadPelisEnFavoritos() {
		return cantidadPelisEnFavoritos;
	}

	public void setCantidadPelisEnFavoritos(int cantidadPelisEnFavoritos) {
		this.cantidadPelisEnFavoritos = cantidadPelisEnFavoritos;
	}

	public static String toJSON(List<Usuario> lstUser) {
		String json = "";
		Gson gson = new Gson();

		if (lstUser != null && !lstUser.isEmpty()) {
			json = gson.toJson(lstUser);
			// json = gson.toJson(lstUser.get(0));
		}

		return json;
	}

}
