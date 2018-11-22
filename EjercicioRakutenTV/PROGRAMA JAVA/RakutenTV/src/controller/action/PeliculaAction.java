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

		case "listAllEstrenos":
			respuesta = findAllEstrenos(request, response);
			break;

		case "listEstrenos":
			respuesta = findEstrenos(request, response);
			break;

		case "listUltimasAdd":
			respuesta = findUltimasAdd(request, response);
			break;

		case "listMasVotadas":
			respuesta = findMasVotadas(request, response);
			break;

		case "listMejorVotadas":
			respuesta = findMejorVotadas(request, response);
			break;

		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE pelicula.idPelicula = ?</code>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula </code>
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
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`tituloPeli` ASC</code>
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
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`fechaEstreno` DESC</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findAllEstrenos(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllEstrenos();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

	/**
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`idPelicula` DESC LIMIT ?</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findEstrenos(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String cantidad = request.getParameter("CANTIDAD");

		if (cantidad != null) {
			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findEstrenos(Integer.parseInt(cantidad));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}
		return respuesta;
	}

	/**
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula ORDER BY `pelicula`.`idPelicula` DESC LIMIT ?"</code>
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
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones`  FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `valoracionesTotales` DESC LIMIT ?"</code>
	 * Las ultimas <b>1</b>
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

	/**
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula`,`valoracionglobalpelicula` WHERE `pelicula`.`idPelicula`= `valoracionglobalpelicula`.`idPelicula` ORDER BY `mediaValoraciones` DESC LIMIT ?
	</code> Las ultimas <b>1</b>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findMejorVotadas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String cantidad = request.getParameter("CANTIDAD");

		if (cantidad != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findMejorVotada(Integer.parseInt(cantidad));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}
}
