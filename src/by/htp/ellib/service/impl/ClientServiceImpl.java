package by.htp.ellib.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.BooksLibraryDAO;
import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.User;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.validation.GredentionalValidator;
import by.htp.ellib.service.validation.LoginValidator;

public class ClientServiceImpl implements ClientService{

	@Override
	public User authorization(String login, String password) throws ServiceException, ConnectionPoolException{

		if(!GredentionalValidator.isCorrect(login, password)) {
			throw new ServiceException();

		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		User user = null;

		try {

			user = userDAO.authentification(login, password);
		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return user;
	}


	@Override
	public int registration(UserData user) throws ServiceException, ConnectionPoolException {
		int error;
		try {

			if(!GredentionalValidator.isCorrect(user.getLogin(), user.getPassword())) {		
				error = 1;
				throw new ServiceException();

			}

			if(!LoginValidator.isCorrect(user.getLogin())) {
				error = 2;
				System.out.println("There is login - "+user.getLogin()+" in our sqlbase");

				throw new ServiceException();

			}

			DAOProvider daoProvider = DAOProvider.getInstance();
			UserDAO userDAO = daoProvider.getUserDAO();



			userDAO.registration(user);
			error=0;
			return error;
		}catch (DaoException e){
			throw new ServiceException();// e

		}

		//return error;
	}



	@Override
	public List<User> all() throws ConnectionPoolException, ServiceException, DaoException {

		DAOProvider daoProvider = DAOProvider.getInstance();
		UserDAO userDAO = daoProvider.getUserDAO();

		List<User> users = new ArrayList<>();

		users = userDAO.all();

		return users;
	}

}