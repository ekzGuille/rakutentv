package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Pelicula;
import model.dao.beansdao.PeliculaDAO;

public class PeliculaAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {
		case "list":
			respuesta = findById(request, response);
			break;

		case "listAll":
			respuesta = findAll(request, response);
			break;

		case "listTitulosAsc":
			respuesta = findTitulosAsc(request, response);
			break;

		case "listAllNuevas":
			respuesta = findAllNuevas(request, response);
			break;

		case "listUltimasAdd":
			respuesta = findUltimasAdd(request, response);
			break;

		case "listMasVotadas":
			respuesta = findMasVotadas(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	private String findById(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		Pelicula pelicula = null;
		String id = request.getParameter("ID_PELICULA");

		if (id != null) {
			Gson gson = new Gson();
			PeliculaDAO peliculaDAO = new PeliculaDAO();
			pelicula = peliculaDAO.findById(Integer.parseInt(id));
			if (pelicula != null) {
				respuesta = "[" + gson.toJson(pelicula) + "]";
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `pelicula`</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findAll(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAll();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `pelicula` ORDER BY `pelicula`.`tituloPeli` ASC</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findTitulosAsc(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllTituloASC();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `pelicula` ORDER BY `pelicula`.`fechaEstreno` DESC</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findAllNuevas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllMasNuevas();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `pelicula` WHERE 1 ORDER BY `pelicula`.`idPelicula` DESC LIMIT 5</code>
	 * Las ultimas <b>5</b>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findUltimasAdd(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String cantidad = request.getParameter("CANTIDAD");

		if (cantidad != null) {
			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findUltimasAdd(Integer.parseInt(cantidad));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT * FROM `pelicula` WHERE `pelicula`.`idPelicula`= (SELECT `valoracionglobalpelicula`.`idPelicula` FROM `valoracionglobalpelicula` ORDER by `count(idPelicula)` DESC LIMIT 1)
	</code> Las ultimas <b>1</b>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findMasVotadas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String cantidad = request.getParameter("CANTIDAD");

		if (cantidad != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findMasVotada(Integer.parseInt(cantidad));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}
}
