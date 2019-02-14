package by.htp.ellib.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.dao.ConnectPool.ConnectionPool;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;

public class SQLUserDAO extends SqlDao implements UserDAO{

	private static final String QUERY_CHECK_CREDENTIONALS = "SELECT * FROM users WHERE login=? and password=?";
	private static final String All_users = "SELECT * FROM users";

	private static final ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public User authentification(String log, String pass)  throws DaoException, ConnectionPoolException{ 

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User user = null;

		try {
			//con = DriverManager.getConnection(url, login, password);
			con = pool.takeConection();
			st =  con.prepareStatement(QUERY_CHECK_CREDENTIONALS);

			st.setString(1, log);
			st.setString(2, pass);
			rs = st.executeQuery();

			if (rs.next()) {
				user = createUser(rs);
			}
		}

		catch (SQLException e) {
			System.out.println("Ошибка");
			throw new DaoException(e);
		}

		//finally {close(rs, st, con);}
		finally {pool.closeConnection(rs, st, con);}

		return user;
	}

	private User createUser (ResultSet rs) throws SQLException{
		User user = new User();
		user.setId(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setSurname(rs.getString(3));
		user.setEmail(rs.getString(4));
		user.setLogin(rs.getString(5));
		user.setPassword(rs.getString(6));


		return user;
	}

	private void close (ResultSet rs, PreparedStatement st, Connection con) {
		try {
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

	private void close_2 (PreparedStatement st1, PreparedStatement st2, Connection con) {
		try {
			if (st1 !=null) {
				st1.close();
			}
		} catch (SQLException e) {
			// log
		}

		try {
			if (st2 !=null) {
				st2.close();
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

	@Override
	public boolean registration(UserData userData) throws DaoException, ConnectionPoolException {

		Connection con = null;
		PreparedStatement stUsers = null;
		PreparedStatement stUsersDetail = null;
		ResultSet rs = null;
		User user = null;

		try {
			//	con = DriverManager.getConnection(url, login, password);

			con=pool.takeConection();

			String sql = "INSERT INTO users (name, surname, email, login, password) VALUES (?,?,?,?,?)";

			con.setAutoCommit(false);

			stUsers =  con.prepareStatement(sql);

			stUsers.setString(1, userData.getName());
			stUsers.setString(2, userData.getSurname());
			stUsers.setString(3, userData.getEmail());	
			stUsers.setString(4, userData.getLogin());
			stUsers.setString(5, userData.getPassword());

			stUsers.executeUpdate();

			//	stUsers = con.prepareStatement("insert into addresses (city, address, users_id values (?,?,?");

			//stUsers.executeUpdate();

			con.commit();		

		}catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new DaoException(e);
			}

			throw new DaoException(e);
		}

		//finally {close_2(stUsers, stUsersDetail, con);}
		finally {pool.closeConnection(rs, stUsers,con);}

		return true;
	}

	public List<User> all () throws ConnectionPoolException, DaoException {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();

		try {
			con = pool.takeConection();
			st =  con.prepareStatement(All_users);
			rs = st.executeQuery();

			while (rs.next()) {
				User user = createUser(rs);
				users.add(user);
			}

		}
		catch (SQLException e) {
			System.out.println("Ошибка");
			throw new DaoException(e);
		}

		return users;

	}
}
