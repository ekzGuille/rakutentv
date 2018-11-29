package controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.beans.Genero;
import model.dao.beansdao.GeneroDAO;

public class GeneroAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";

		String actionReived = request.getParameter("ACTION");

		String[] actions = actionReived.split("\\.");

		switch (actions[1]) {

		case "listAll":
			respuesta = findAll(request, response);
			break;
		default:
			respuesta = "[]";
		}

		return respuesta;
	}

	private String findAll(HttpServletRequest request, HttpServletResponse response) {

		List<Genero> lstGenero = null;
		Gson gson = new Gson();
		GeneroDAO generoDAO = new GeneroDAO();
		lstGenero = generoDAO.findAll();

		return gson.toJson(lstGenero);
	}
}
