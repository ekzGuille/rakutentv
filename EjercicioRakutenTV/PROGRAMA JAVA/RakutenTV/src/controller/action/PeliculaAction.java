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

		case "listTitulosAZ":
			respuesta = findTitulosAZ(request, response);
			break;

		case "listTitulosZA":
			respuesta = findTitulosZA(request, response);
			break;

		case "listAllEstrenosNuevas":
			respuesta = findAllEstrenosNuevas(request, response);
			break;

		case "listAllEstrenosViejas":
			respuesta = findAllEstrenosViejas(request, response);
			break;

		case "listEstrenos":
			respuesta = findEstrenos(request, response);
			break;

		case "listUltimasAdd":
			respuesta = findUltimasAdd(request, response);
			break;

		case "listMejorVotadas":
			respuesta = findMejorVotadas(request, response);
			break;

		case "listMasVotadas":
			respuesta = findMasVotadas(request, response);
			break;

		case "listAllMejorVotadas":
			respuesta = findAllMejorVotadas(request, response);
			break;

		case "listAllMasVotadas":
			respuesta = findAllMasVotadas(request, response);
			break;

		case "listGenero":
			respuesta = findByGenero(request, response);
			break;

		case "filtrarNombre":
			respuesta = findByNombre(request, response);
			break;

		case "listCompradas":
			respuesta = findCompradas(request, response);
			break;

		case "listPuntuadas":
			respuesta = findPuntuadas(request, response);
			break;

		case "listFavoritas":
			respuesta = findFavoritas(request, response);
			break;

		case "listAllCaras":
			respuesta = findAllCaras(request, response);
			break;

		case "listAllBaratas":
			respuesta = findAllBaratas(request, response);
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
	private String findTitulosAZ(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllTituloAZ();
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
	private String findTitulosZA(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllTituloZA();
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
	private String findAllEstrenosNuevas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllEstrenosNuevas();
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
	private String findAllEstrenosViejas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllEstrenosViejas();
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

	/**
	 * <code>SELECT `genero`.`descGenero`, `pelicula`.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`,`valoracionesTotales`, `mediaValoraciones` FROM `genero`,`tenergenero`,`pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE `pelicula`.`idPelicula` = `tenergenero`.`idPelicula` AND `genero`.`idGenero` = `tenergenero`.`idGenero` AND `genero`.`idGenero` = ?
	</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findByGenero(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String genero = request.getParameter("GENERO");

		if (genero != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findPeliculaByGenero(Integer.parseInt(genero));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT pelicula.`idPelicula`,`tituloPeli`,`resumenPeli`,`trailerPeli`,`caratulaPeli`,`imagenPeli`,`fechaEstreno`,`audiosDisponibles`,`subtitulosDisponibles`,`duracionPeli`,`precioPeli`, `valoracionesTotales`, `mediaValoraciones` FROM `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON pelicula.idPelicula = valoracionglobalpelicula.idPelicula WHERE `tituloPeli` LIKE '%?%'</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findByNombre(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String nombre = request.getParameter("NOMBRE");

		if (nombre != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findByNombre(nombre);
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones` FROM `compra`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `compra`.`idPelicula` = `pelicula`.`idPelicula` AND `compra`.`idUsuario` = 2</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findCompradas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String idUsuario = request.getParameter("USUARIO");

		if (idUsuario != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findCompradas(Integer.parseInt(idUsuario));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones`, puntuacion.idInfoPuntuacion FROM `puntuacion`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `puntuacion`.`idPelicula` = `pelicula`.`idPelicula` AND `puntuacion`.`idUsuario` = ? ORDER BY `puntuacion`.`idInfoPuntuacion` DESC</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findPuntuadas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String idUsuario = request.getParameter("USUARIO");

		if (idUsuario != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findPuntuadas(Integer.parseInt(idUsuario));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	/**
	 * <code>SELECT `pelicula`.*, `valoracionesTotales`, `mediaValoraciones` FROM `marcarfavorito`, `pelicula` LEFT OUTER JOIN `valoracionglobalpelicula` ON `pelicula`.`idPelicula` = `valoracionglobalpelicula`.`idPelicula` WHERE `marcarfavorito`.`idPelicula` = `pelicula`.`idPelicula` AND `marcarfavorito`.`idUsuario` = 3
	</code>
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	private String findFavoritas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		String idUsuario = request.getParameter("USUARIO");

		if (idUsuario != null) {

			PeliculaDAO peliculaDAO = new PeliculaDAO();
			Gson gson = new Gson();
			lstPelicula = peliculaDAO.findFavoritas(Integer.parseInt(idUsuario));
			if (lstPelicula != null) {
				respuesta = gson.toJson(lstPelicula);
			} else {
				respuesta = "[]";
			}
		}

		return respuesta;
	}

	private String findAllMasVotadas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllMasVotadas();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

	private String findAllMejorVotadas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllMejorVotadas();
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
	private String findAllCaras(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllCaras();
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
	private String findAllBaratas(HttpServletRequest request, HttpServletResponse response) {
		String respuesta = "";
		List<Pelicula> lstPelicula = null;

		PeliculaDAO peliculaDAO = new PeliculaDAO();
		Gson gson = new Gson();
		lstPelicula = peliculaDAO.findAllBaratas();
		if (lstPelicula != null) {
			respuesta = gson.toJson(lstPelicula);
		} else {
			respuesta = "[]";
		}

		return respuesta;
	}

}
