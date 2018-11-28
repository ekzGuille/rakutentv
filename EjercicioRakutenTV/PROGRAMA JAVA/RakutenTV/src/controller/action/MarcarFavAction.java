package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.MarcarFavorito;
import model.dao.beansdao.MarcarFavoritoDAO;
import utils.Respuesta;

public class MarcarFavAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "marcarFavorito":
			respuesta = addFavorito(request, response);
			break;

		case "quitarFavorito":
			respuesta = removeFavorito(request, response);
			break;
		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>INSERT INTO `marcarfavorito`(`idPelicula`, `idUsuario`) VALUES (?,?)</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String addFavorito(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		MarcarFavorito marcarFav = new MarcarFavorito();
		String usuario = request.getParameter("ID_USUARIO");
		String pelicula = request.getParameter("ID_PELICULA");

		if (usuario != null && pelicula != null) {

			marcarFav.setIdUsuario(Integer.parseInt(usuario));
			marcarFav.setIdPelicula(Integer.parseInt(pelicula));

			MarcarFavoritoDAO marcarFavDAO = new MarcarFavoritoDAO();

			Respuesta resp = new Respuesta();
			Gson gson = new Gson();

			resp.setRespuesta(marcarFavDAO.add(marcarFav));
			resp.setDescRespuesta("addFavorito");

			respuesta = "[" + gson.toJson(resp) + "]";
		}

		return respuesta;

	}

	/**
	 * <code>SELECT * FROM `marcarfavorito` WHERE `idPelicula` = ? AND `idUsuario` = ?</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String removeFavorito(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		String usuario = request.getParameter("ID_USUARIO");
		String pelicula = request.getParameter("ID_PELICULA");

		if (usuario != null && pelicula != null) {

			Integer[] id = new Integer[] { Integer.parseInt(pelicula), Integer.parseInt(usuario) };

			MarcarFavoritoDAO marcarFavDAO = new MarcarFavoritoDAO();
			
			Respuesta resp = new Respuesta();
			Gson gson = new Gson();

			resp.setRespuesta(marcarFavDAO.delete(id));
			resp.setDescRespuesta("remFavorito");

			respuesta = "[" + gson.toJson(resp) + "]";
		}

		return respuesta;

	}
}
