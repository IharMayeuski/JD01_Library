package by.htp.ellib.service;

import java.util.List;

import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;

public interface ClientService {

		User authorization (String login, String password) throws ServiceException, ConnectionPoolException;

		int registration (UserData user) throws ServiceException, ConnectionPoolException;
		
		List<User> all() throws ConnectionPoolException, ServiceException, DaoException;

		
}
