package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Puntuacion;
import model.dao.beansdao.PuntuacionDAO;
import utils.Respuesta;

public class PuntuarAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "addPuntuacion":
			respuesta = addPuntuacion(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>INSERT INTO `puntuacion` (`idPelicula`, `idUsuario`, `idInfoPuntuacion`, `fechaPuntuacion`) VALUES (?,?,?,?)</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String addPuntuacion(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		Puntuacion puntuacion = new Puntuacion();
		String usuario = request.getParameter("ID_USUARIO");
		String pelicula = request.getParameter("ID_PELICULA");
		String puntos = request.getParameter("PUNTUACION");
		
		if (usuario != null && pelicula != null && puntos != null) {
			Respuesta resp = new Respuesta();
			Gson gson = new Gson();
			puntuacion.setIdUsuario(Integer.parseInt(usuario));
			puntuacion.setIdPelicula(Integer.parseInt(pelicula));
			puntuacion.setIdInfoPuntuacion(Integer.parseInt(puntos));

			Integer[] id = new Integer[] { Integer.parseInt(pelicula), Integer.parseInt(usuario) };

			PuntuacionDAO puntuacionDAO = new PuntuacionDAO();

			if (puntuacionDAO.findByPeliUser(id) == null) {
				resp.setRespuesta(puntuacionDAO.add(puntuacion));
				resp.setDescRespuesta("addPuntuacion");
				respuesta = gson.toJson(resp);
			} else {
				resp.setRespuesta(puntuacionDAO.update(puntuacion));
				resp.setDescRespuesta("modPuntuacion");
				respuesta = gson.toJson(resp);
			}
		}

		return "[" + respuesta + "]";

	}
}