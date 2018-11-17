package model.motor;

public class MotorMySQL extends Motor {
	
	private final String DRIVER = "jdbc:mysql://";
	private final String HOST = "localhost:3306";
	private final String BDNAME = "rakutentv";
	private final String TIMEZONE = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	public MotorMySQL() {
		urlDB = DRIVER + HOST + "/" + BDNAME + TIMEZONE;
		userDB = "root";
		passDB = "";
		driverDB = "com.mysql.cj.jdbc.Driver";
	}
}
