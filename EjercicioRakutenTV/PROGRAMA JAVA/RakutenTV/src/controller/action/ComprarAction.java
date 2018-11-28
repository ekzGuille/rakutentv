package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Compra;
import model.dao.beansdao.CompraDAO;
import utils.Respuesta;

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
		String precio = request.getParameter("PRECIO");

		if (usuario != null && pelicula != null) {

			compra.setIdUsuario(Integer.parseInt(usuario));
			compra.setIdPelicula(Integer.parseInt(pelicula));
			compra.setPrecioCompra(Double.parseDouble(precio));

			CompraDAO compraDAO = new CompraDAO();
			
			Respuesta resp = new Respuesta();
			Gson gson = new Gson();
			
			Integer[] id = new Integer[] { Integer.parseInt(pelicula), Integer.parseInt(usuario) };
			
			if(compraDAO.findByPeliUser(id) == null) {
				resp.setRespuesta(compraDAO.add(compra));
				resp.setDescRespuesta("addCompra");
			}else {
				resp.setRespuesta(0);
				resp.setDescRespuesta("yaCompra");
			}

			respuesta = "[" + gson.toJson(resp) + "]";
		}
		return respuesta;
	}
}
