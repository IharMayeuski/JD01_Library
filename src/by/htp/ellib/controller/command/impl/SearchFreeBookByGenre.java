package by.htp.ellib.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.User;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class SearchFreeBookByGenre implements Command {

	private static final String Search = "genre";

	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	private static final String INDEX_PAGE = "/index.jsp"; 
	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
	private static final String SEARCH_BOOK = "/WEB-INF/jsp/SearchFreeBook.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException {
		String userLogin;
		userLogin = request.getParameter("USER");
		String userPassword;
		userPassword= request.getParameter("USER_pass");
		
		
				
		System.out.println(userLogin+"!!!!!"+userPassword);
		
		ServiceProvider prov = ServiceProvider.getInstance();
		ClientService serv = prov.getClientService();
		
		User user = serv.authorization(userLogin, userPassword);
		request.setAttribute("USER", user);
		
		ServiceProvider provider1 = ServiceProvider.getInstance();
		LibraryService bookService1 = provider1.getLibraryService();
		
		List<BooksLibrary> book1 = new ArrayList<>();
		
		book1 = bookService1.all();
		
		List<BooksLibrary> bookSmall = new ArrayList<>();
		for (int i=book1.size()-1; i>=book1.size()-3; i--) {
			bookSmall.add(book1.get(i));
			 
		}
		
		request.setAttribute("novelty", bookSmall);
		
		String genre;
		String page = SEARCH_BOOK;


		genre = request.getParameter(Search);
		System.out.println(genre);

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		List<BooksLibrary> book = new ArrayList<>();

		try {book = bookService.searchFreeBookGenre(genre);

				if (book==null) {
					request.setAttribute("null", "null");
					
				}else {
		
					request.setAttribute("books", book);
					
				}	

		} catch (ServiceException e) {
			request.setAttribute("error", "book error");
			// log logger.error и сообщение
			//page = DEFAULT_PAGE;

		}


		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}



}
