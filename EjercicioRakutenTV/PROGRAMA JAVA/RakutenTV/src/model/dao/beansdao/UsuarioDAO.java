package model.dao.beansdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.beans.Usuario;
import model.dao.DAO;
import model.motor.Motor;
import model.motor.MotorMySQL;

public class UsuarioDAO implements DAO<Usuario, Integer> {

	private Motor motor;
	private PreparedStatement pst;

	public UsuarioDAO() {
		motor = new MotorMySQL();
	}

	@Override
	public int add(Usuario bean) {
		String sql = "INSERT INTO `usuario` (`email`, `username`, `contrasena`, `fechaCreacion`) VALUES (?,?,?,?)";
		int resp = 0;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setString(1, bean.getEmail());
			pst.setString(2, bean.getUsername());
			pst.setString(3, bean.getContrasena());
			pst.setString(4, String.valueOf(java.time.LocalDate.now()));

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM `usuario` WHERE `idUsuario`= ?";
		int resp = 0;

		try {

			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			resp = this.motor.execute(pst);
		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return resp;
	}

	@Override
	public int update(Usuario bean) {

		String sql = "UPDATE `usuario` SET ";
		HashMap<Integer, Object> lstCondiciones = new HashMap<>();
		int contarCasos = 0;
		int resp = 0;

		if (bean.getEmail() != null) {
			sql += "`email` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getEmail());
		}
		if (bean.getUsername() != null) {
			sql += "`username` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getUsername());
		}
		if (bean.getContrasena() != null) {
			sql += "`contrasena` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getContrasena());
		}
		if (bean.getFechaCreacion() != null) {
			sql += "`fechaCreacion` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getFechaCreacion());
		}
		if (bean.getFotoUsuario() != null) {
			sql += "`fotoUsuario` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getFotoUsuario());
		}
		if (bean.getIdMetodoPago() > 0) {
			sql += "`idMetodoPago` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getIdMetodoPago());
		}
		if (bean.getInfoMetodoPago() != null) {
			sql += "`infoMetodoPago` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getInfoMetodoPago());
		}
		if (bean.getActivoUsuario() >= 0) {
			sql += "`activoUsuario` = ?,";
			contarCasos++;
			lstCondiciones.put(contarCasos, bean.getActivoUsuario());
		}

		if (sql.endsWith(",")) {
			sql = sql.substring(0, sql.length() - 1);
		}

		sql += " WHERE `idUsuario` = ?";
		contarCasos++;
		lstCondiciones.put(contarCasos, bean.getIdUsuario());

		try {
			pst = this.motor.connect().prepareStatement(sql);

			for (Map.Entry<Integer, Object> item : lstCondiciones.entrySet()) {
				Integer contar = item.getKey();
				Object valor = item.getValue();

				if (valor instanceof String) {
					pst.setString(contar, (String) valor);
				}
				if (valor instanceof Integer) {
					pst.setInt(contar, (Integer) valor);
				}
			}

			resp = this.motor.execute(pst);

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}
		return resp;
	}

	@Override
	public List<Usuario> findAll() {
		String sql = "SELECT * FROM `usuario`";

		List<Usuario> lstUsuario = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);
			ResultSet rs = this.motor.executeQuery(pst);
			lstUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt(1));
				usuario.setEmail(rs.getString(2));
				usuario.setUsername(rs.getString(3));
				usuario.setContrasena(rs.getString(4));
				usuario.setFechaCreacion(rs.getString(5));
				usuario.setFotoUsuario(rs.getString(6));
				usuario.setIdMetodoPago(rs.getInt(7));
				usuario.setInfoMetodoPago(rs.getString(8));
				usuario.setActivoUsuario(rs.getInt(9));

				lstUsuario.add(usuario);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return lstUsuario;
	}

	@Override
	public Usuario findById(Integer id) {
		String sql = "SELECT * FROM `usuario` WHERE `idUsuario` = ?";

		List<Usuario> lstUsuario = null;

		try {
			pst = this.motor.connect().prepareStatement(sql);

			pst.setInt(1, id);

			ResultSet rs = this.motor.executeQuery(pst);
			lstUsuario = new ArrayList<Usuario>();

			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt(1));
				usuario.setEmail(rs.getString(2));
				usuario.setUsername(rs.getString(3));
				usuario.setContrasena(rs.getString(4));
				usuario.setFechaCreacion(rs.getString(5));
				usuario.setFotoUsuario(rs.getString(6));
				usuario.setIdMetodoPago(rs.getInt(7));
				usuario.setInfoMetodoPago(rs.getString(8));
				usuario.setActivoUsuario(rs.getInt(9));

				lstUsuario.add(usuario);

			}

		} catch (SQLException e) {

		} finally {
			this.motor.disconnect();
		}

		return (!lstUsuario.isEmpty()) ? lstUsuario.get(0) : null;
	}

}
