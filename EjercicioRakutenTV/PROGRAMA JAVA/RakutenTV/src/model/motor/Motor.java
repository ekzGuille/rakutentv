package model.motor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Motor implements MotorI {
	protected String urlDB;
	protected String userDB;
	protected String passDB;
	protected String driverDB;

	protected Connection con;
	protected ResultSet rs;
	protected PreparedStatement pst;

	protected Motor() {
	}

	protected Motor(String urlDB, String userDB, String passDB, String driverDB) {
		this.urlDB = urlDB;
		this.userDB = userDB;
		this.passDB = passDB;
		this.driverDB = driverDB;
	}

	@Override
	public Connection connect() {
		try {
			Class.forName(driverDB);
			con = DriverManager.getConnection(urlDB, userDB, passDB);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public int execute(PreparedStatement pst) {
		int resp = 0;

		try {
			resp = pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resp;
	}

	@Override
	public ResultSet executeQuery(PreparedStatement pst) {
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {

		}
		return rs;
	}

	@Override
	public void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {

		}
	}
}
