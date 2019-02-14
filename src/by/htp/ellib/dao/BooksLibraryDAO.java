package by.htp.ellib.dao;

import java.util.List;

import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.Info;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;

public interface BooksLibraryDAO {
	
	List <BooksLibrary> find (String genre) throws ConnectionPoolException, DaoException;
	
	List <BooksLibrary> all () throws DaoException, ConnectionPoolException;
	
	//boolean registration (Book book) throws DaoException, ConnectionPoolException;

	boolean registration(BooksLibrary book) throws DaoException, ConnectionPoolException;
	
	List <BooksLibrary> findFree (String genre) throws ConnectionPoolException, DaoException;
	
	List <BooksLibrary> allFreeBook () throws DaoException, ConnectionPoolException;

	List<BooksLibrary> allBookedBook() throws DaoException, ConnectionPoolException;
	
/*	List<Info> receiveBook () throws DaoException, ConnectionPoolException;
	
	List<Info> giveBook () throws DaoException, ConnectionPoolException;*/
	
	boolean registration(Info book) throws DaoException, ConnectionPoolException;

	boolean delete(Info info) throws DaoException, ConnectionPoolException;;

}
