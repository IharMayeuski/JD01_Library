package by.htp.ellib.service.validation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import by.htp.ellib.dao.impl.ConnectionPool;
import by.htp.ellib.dao.impl.SqlDao;

public class LoginValidator extends SqlDao {

	private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM users WHERE login=?";

	//private static final ConnectionPool pool = ConnectionPool.getInstance();

	public static boolean  isCorrect (String log) {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		boolean ret=false;

		try {
			con = DriverManager.getConnection(url, login, password);
			st =  con.prepareStatement(QUERY_CHECK_CREDENTIONALS);

			st.setString(1, log);
			rs = st.executeQuery();

			if (!rs.next()) {
				ret = true;
			}
			;
		}
		catch (SQLException e) {
			System.out.println("Ошибка");
			//throw new DaoException(e);
		}

		finally {try {
			if (rs !=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// log
		}

		try {
			if (st !=null) {
				st.close();
			}
		}
		catch (SQLException e) {
			// log
		}
		try {
			if (con !=null) {
				con.close();
			}
		} catch (SQLException e) {
			// log

		}

		}
		return ret;
	}

}
