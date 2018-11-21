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

	private String findUltimasAdd(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findUltimasAdd();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}
}
