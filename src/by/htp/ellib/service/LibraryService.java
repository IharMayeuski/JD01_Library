package by.htp.ellib.service;

import java.util.List;

import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.Info;
import by.htp.ellib.exceptions.ServiceException;

public interface LibraryService {
	
	List<Book> find (String criteria);
	
	List<BooksLibrary> searchGenre(String genre) throws  ConnectionPoolException, ServiceException;
	
	List<BooksLibrary> all() throws ConnectionPoolException, ServiceException;
	
	int registration(BooksLibrary book) throws  ConnectionPoolException, ServiceException;
	
	List<BooksLibrary> searchFreeBookGenre (String genre) throws  ConnectionPoolException, ServiceException;

	List<BooksLibrary> allFreeBook() throws ConnectionPoolException, ServiceException;
	
	List<BooksLibrary> allBookedBook() throws ConnectionPoolException, ServiceException;
	
	boolean giveBookToUser(Info info) throws ConnectionPoolException, ServiceException;

	boolean backBookFromUser(Info info) throws ConnectionPoolException, ServiceException;


	
	
}
