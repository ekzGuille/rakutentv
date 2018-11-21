package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Usuario;
import model.dao.beansdao.UsuarioDAO;

public class UserAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "login":
			respuesta = login(request, response);
			break;

		case "register":
			respuesta = register(request, response);
			break;

		case "update":
			respuesta = update(request, response);
			break;

		case "darBaja":
			respuesta = darBaja(request, response);
			break;

		case "permaDelete":
			respuesta = permaDelete(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `usuario` WHERE (`email` = ? AND `contrasena` = ?) OR (`username` = ? AND `contrasena` = ?)</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String login(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		Usuario usuario = null;
		String userMail = request.getParameter("userMail");
		String contrasena = request.getParameter("contrasena");

		if (userMail != null && contrasena != null) {
			Gson gson = new Gson();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			usuario = usuarioDAO.findByCredentials(userMail, contrasena);

			if (usuario != null) {
				respuesta = "[" + gson.toJson(usuario) + "]";
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;

	}

	/**
	 * <code> </code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String register(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		return respuesta;
	}

	/**
	 * <code> </code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String update(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		return respuesta;
	}

	/**
	 * <code> </code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String darBaja(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		return respuesta;
	}

	/**
	 * <code> </code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String permaDelete(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		return respuesta;
	}

}
