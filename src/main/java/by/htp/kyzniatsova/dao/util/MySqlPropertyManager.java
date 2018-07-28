package by.htp.kyzniatsova.dao.util;

import java.util.ResourceBundle;

public class MySqlPropertyManager {
	
	private static final ResourceBundle rb;
	
	static {
		rb = ResourceBundle.getBundle("db_config"); //ResourceBundle по умолчанию ищет properties files
	}
	public static String getUrl() {
		return rb.getString("db.url");
	}
	
	public static String getLogin() {
		return rb.getString("db.login");
	}
	
	public static String getPass() {
		return rb.getString("db.pass");
	}

}
