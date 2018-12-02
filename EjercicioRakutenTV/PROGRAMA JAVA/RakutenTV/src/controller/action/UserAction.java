package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.beans.Usuario;
import model.dao.beansdao.UsuarioDAO;
import utils.Respuesta;

public class UserAction {

	private HttpSession sesion;

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		sesion = request.getSession(true);

		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "getSessionUser":
			respuesta = getSessionUser(request, response);
			break;

		case "logout":
			respuesta = logout(request, response);
			break;

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

		case "listAll":
			respuesta = findAll(request, response);
			break;

		case "listMasPeliculasAddFav":
			respuesta = findMasPeliculasAddFav(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	private String getSessionUser(HttpServletRequest request, HttpServletResponse response) {
		Gson gson = new Gson();
		String respuesta = "";

		HttpSession sesion = (HttpSession) request.getSession();

		if (sesion != null && sesion.getAttribute("usuario") != null) {
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");
			respuesta = gson.toJson(usuario);

		} else {
			Respuesta resp = new Respuesta();
			resp.setRespuesta(0);
			resp.setDescRespuesta("getSesion");
			respuesta = gson.toJson(resp);
		}

		return respuesta;
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		Gson gson = new Gson();

		HttpSession sesion = (HttpSession) request.getSession();
		sesion.invalidate();

		Respuesta resp = new Respuesta();
		resp.setRespuesta(1);
		resp.setDescRespuesta("userLogout");

		return gson.toJson(resp);
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
		String saveSession = request.getParameter("SAVE_SESSION");

		if (userMail != null && contrasena != null) {
			boolean guardarSesion = true;
			if (saveSession == null) {
				guardarSesion = true;
			} else {
				guardarSesion = Boolean.parseBoolean(saveSession);
			}
			Gson gson = new Gson();
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			usuario = usuarioDAO.findByCredentials(userMail, contrasena);

			if (usuario != null) {
				if (guardarSesion) {
					HttpSession sesion = (HttpSession) request.getSession();
					sesion.setAttribute("usuario", usuario);
				}
				respuesta = gson.toJson(usuario);
			} else {
				Respuesta resp = new Respuesta();
				resp.setRespuesta(0);
				resp.setDescRespuesta("userLogIn");
				respuesta = gson.toJson(resp);
			}
		}

		return "[" + respuesta + "]";

	}

	/**
	 * <code>INSERT INTO `usuario` (`email`, `username`, `contrasena`, `fechaCreacion`) VALUES (?,?,?,?)</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String register(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		Usuario usuario = new Usuario();

		String email = request.getParameter("EMAIL");
		String username = request.getParameter("USERNAME");
		String contrasena = request.getParameter("CONTRASENA");

		Respuesta resp = new Respuesta();
		Gson gson = new Gson();

		if (email != null && username != null && contrasena != null) {

			UsuarioDAO usuarioDAO = new UsuarioDAO();

			if (usuarioDAO.findByCredentials(email, contrasena) == null) {

				usuario.setEmail(email);
				usuario.setUsername(username);
				usuario.setContrasena(contrasena);

				int respuestaRegistro = usuarioDAO.add(usuario);
				resp.setRespuesta(respuestaRegistro);
				resp.setDescRespuesta("userRegister");

				if (respuestaRegistro == 1) {
					HttpSession sesion = (HttpSession) request.getSession();
					Usuario usrRegistered = usuarioDAO.findByCredentials(email, contrasena);
					sesion.setAttribute("usuario", usrRegistered);
					respuesta = gson.toJson(usrRegistered);
				}
			} else {
				resp.setRespuesta(0);
				resp.setDescRespuesta("userRegister");
				respuesta = gson.toJson(resp);
			}

		} else {
			resp.setRespuesta(0);
			resp.setDescRespuesta("userRegister");
			respuesta = gson.toJson(resp);
		}

		return "[" + respuesta + "]";
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
		Usuario usuario = new Usuario();

		String idUsuario = request.getParameter("ID_USUARIO");
		String email = request.getParameter("EMAIL");
		String username = request.getParameter("USERNAME");
		String contrasena = request.getParameter("CONTRASENA");
		String fotoUsuario = request.getParameter("FOTO");
		String idMetodoPago = request.getParameter("ID_METODO_PAGO");
		String infoMetodoPago = request.getParameter("INFO_METODO_PAGO");
		String activoUsuario = request.getParameter("ACTIVO");

		Respuesta resp = new Respuesta();
		Gson gson = new Gson();

		if (idUsuario != null) {

			if (idUsuario != null) {
				usuario.setIdUsuario(Integer.parseInt(idUsuario));
			}

			if (email != null) {
				usuario.setEmail(email);
			}

			if (username != null) {
				usuario.setUsername(username);
			}

			if (contrasena != null) {
				usuario.setContrasena(contrasena);
			}

			if (fotoUsuario != null) {
				usuario.setFotoUsuario(fotoUsuario);
			}

			if (idMetodoPago != null) {
				usuario.setIdMetodoPago(Integer.parseInt(idMetodoPago));
			}

			if (infoMetodoPago != null) {
				usuario.setInfoMetodoPago(infoMetodoPago);
			}

			if (activoUsuario != null) {
				usuario.setActivoUsuario(Integer.parseInt(activoUsuario));
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();

			resp.setRespuesta(usuarioDAO.update(usuario));
			resp.setDescRespuesta("userUpdate");

		} else {
			resp.setRespuesta(0);
			resp.setDescRespuesta("userUpdate");

		}
		respuesta = gson.toJson(resp);

		return "[" + respuesta + "]";
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
		String idUsuario = request.getParameter("ID_USUARIO");

		Respuesta resp = new Respuesta();
		Gson gson = new Gson();

		if (idUsuario != null) {

			UsuarioDAO usuarioDAO = new UsuarioDAO();

			resp.setRespuesta(usuarioDAO.delete(Integer.parseInt(idUsuario)));
			resp.setDescRespuesta("userPermaDelete");

		} else {
			resp.setRespuesta(0);
			resp.setDescRespuesta("userPermaDelete");

		}
		respuesta = gson.toJson(resp);
		return "[" + respuesta + "]";
	}

	private String findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Usuario> lstUsuario = null;
		Gson gson = new Gson();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		lstUsuario = usuarioDAO.findAll();

		return gson.toJson(lstUsuario);

	}

	private String findMasPeliculasAddFav(HttpServletRequest request, HttpServletResponse response) {

		List<Usuario> lstUsuario = null;
		Gson gson = new Gson();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String cantidad = request.getParameter("CANTIDAD");

		if (cantidad != null) {

			lstUsuario = usuarioDAO.findMasPeliculasAddFav(Integer.parseInt(cantidad));
		}
		return gson.toJson(lstUsuario);

	}

}
