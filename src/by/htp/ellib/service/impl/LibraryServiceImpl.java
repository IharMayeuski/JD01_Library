package by.htp.ellib.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.ellib.dao.BookDAO;
import by.htp.ellib.dao.BooksLibraryDAO;
import by.htp.ellib.dao.DAOProvider;
import by.htp.ellib.dao.UserDAO;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.Info;
import by.htp.ellib.entity.User;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.validation.ValidatorCreateBook;

public class LibraryServiceImpl implements LibraryService{

	@Override
	public List<Book> find(String criteria) {
		// validation


		DAOProvider provider = DAOProvider.getInstance();
		BookDAO bookDAO = provider.getBookDAO();

		List <Book> books;

		books = bookDAO.find(criteria);

		return books;
	}

	@Override
	public List <BooksLibrary> searchGenre(String genre) throws ServiceException, ConnectionPoolException {

		DAOProvider daoProvider = DAOProvider.getInstance();
		BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();

		List <BooksLibrary> book = new ArrayList<>();

		try {

			book = books.find(genre);


		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return book;
	}

	@Override
	public List<BooksLibrary> all() throws ConnectionPoolException, ServiceException {

		DAOProvider daoProvider = DAOProvider.getInstance();
		BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();

		List <BooksLibrary> book = new ArrayList<>();

		try {

			book = books.all();


		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return book;
	}

	@Override
	public int registration(BooksLibrary book) throws ConnectionPoolException, ServiceException {
		try {	

			if (!ValidatorCreateBook.isCorrect(book.getAuthor(), book.getName(), book.getYear_book(), book.getGenre(), book.getPrice())) {

				throw new ServiceException("ERROR");	
			}	
			DAOProvider daoProvider = DAOProvider.getInstance();
			BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();


			books.registration(book);
			return 0;

		}catch (DaoException e){
			throw new ServiceException("ERROR");// e
		}
	}

	@Override
	public List<BooksLibrary> searchFreeBookGenre(String genre) throws ConnectionPoolException, ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();

		List <BooksLibrary> book = new ArrayList<>();

		try {

			book = books.findFree(genre);


		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return book;
	}

	@Override
	public List<BooksLibrary> allFreeBook() throws ConnectionPoolException, ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();

		List <BooksLibrary> book = new ArrayList<>();

		try {

			book = books.allFreeBook();


		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return book;
	}

	@Override
	public List<BooksLibrary> allBookedBook() throws ConnectionPoolException, ServiceException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();

		List <BooksLibrary> book = new ArrayList<>();

		try {

			book = books.allBookedBook();


		}catch (DaoException e){
			throw new ServiceException();// e
		}

		return book;
	}

	@Override
	public boolean giveBookToUser(Info info) throws ConnectionPoolException, ServiceException {
		try {
			/*	if (!ValidatorCreateBook.isCorrect(book.getAuthor(), book.getName(), book.getYear_book(), book.getGenre(), book.getPrice())) {

				throw new ServiceException("ERROR");	
			}	*/
			DAOProvider daoProvider = DAOProvider.getInstance();
			BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();


			books.registration(info);
			return true;

		}catch (DaoException e){
			throw new ServiceException();// e

		}
	}

	@Override
	public boolean backBookFromUser(Info info) throws ConnectionPoolException, ServiceException {
		try {	

			/*	if (!ValidatorCreateBook.isCorrect(book.getAuthor(), book.getName(), book.getYear_book(), book.getGenre(), book.getPrice())) {

					throw new ServiceException("ERROR");	
				}	*/
			DAOProvider daoProvider = DAOProvider.getInstance();
			BooksLibraryDAO books = daoProvider.getBooksLibraryDAO();


			books.delete(info);
			return true;

		}catch (DaoException e){
			throw new ServiceException("ERROR");// e
		}
	}

}



