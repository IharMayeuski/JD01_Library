package by.htp.ellib.dao.impl;

import java.util.ResourceBundle;

import by.htp.ellib.exceptions.NotDBDriverException;

public abstract class SqlDao {

	protected static final String driver;
	protected static final String url;
	protected  static final String login;
	protected  static final String password;

	private static final String DB_DRIVER = "db.driver";
	private static final String DB_URL = "db.url";
	private static final String DB_LOGIN = "db.login";
	private static final String DB_PASSWORD = "db.password";


	private static final String DB_PROPERTIES_FILE_PATH = "resources.db";

	static {

		ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);

		driver = jdbcProperties.getString(DB_DRIVER);
		url = jdbcProperties.getString(DB_URL);
		login = jdbcProperties.getString(DB_LOGIN);
		password = jdbcProperties.getString(DB_PASSWORD);

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {

			throw new NotDBDriverException("Can't find driver.", e);
		}
	}

	public SqlDao() {}

}

