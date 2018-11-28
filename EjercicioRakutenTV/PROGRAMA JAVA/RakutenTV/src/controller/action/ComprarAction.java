package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.beans.Compra;
import model.dao.beansdao.CompraDAO;

public class ComprarAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "comprar":
			respuesta = addCompra(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>INSERT INTO `compra`(`idUsuario`, `idPelicula`, `precioCompra`, `fechaCompra`) VALUES (?,?,?,?)</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String addCompra(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		Compra compra = new Compra();
		String usuario = request.getParameter("ID_USUARIO");
		String pelicula = request.getParameter("ID_PELICULA");

		if (usuario != null && pelicula != null) {

			compra.setIdUsuario(Integer.parseInt(usuario));
			compra.setIdPelicula(Integer.parseInt(pelicula));

			CompraDAO compraDAO = new CompraDAO();

			respuesta = String.valueOf(compraDAO.add(compra));
		}
		return respuesta;
	}
}
