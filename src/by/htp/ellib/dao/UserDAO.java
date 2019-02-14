package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;

public interface UserDAO {

	User authentification (String login, String password) throws DaoException, ConnectionPoolException;
	
	boolean registration (UserData userData) throws DaoException, ConnectionPoolException;

	List<User> all() throws DaoException, ConnectionPoolException;;
	
	
// все методы по юзеру: заблокирован, доступ к платным ресурсам
	// и прочие методы здесь 
	// попробовать метод файнд, чтобы он работал на критериях
	
	
}
