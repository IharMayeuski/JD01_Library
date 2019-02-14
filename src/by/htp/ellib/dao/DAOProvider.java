package by.htp.ellib.dao;

import by.htp.ellib.dao.impl.SQLBookDAO;
import by.htp.ellib.dao.impl.SQLBooksLibraryDAO;
import by.htp.ellib.dao.impl.SQLUserDAO;
import by.htp.ellib.entity.BooksLibrary;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final BookDAO bookDAO = new SQLBookDAO();
	private final BooksLibraryDAO book = new SQLBooksLibraryDAO();
	
	
	
	public BooksLibraryDAO getBook() {
		return book;
	}

	private DAOProvider () {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public BookDAO getBookDAO() {
		return bookDAO;
	}
	
	public BooksLibraryDAO getBooksLibraryDAO() {
		return book;
		
	}
	
	public static DAOProvider getInstance() {
		return instance;
	}

	
	

}
