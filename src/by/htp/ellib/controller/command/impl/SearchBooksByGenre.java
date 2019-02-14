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
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class SearchBooksByGenre implements Command {

	private static final String Search = "genre";

	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	private static final String INDEX_PAGE = "/index.jsp"; 
	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
	private static final String SEARCH_BOOK = "/WEB-INF/jsp/SearchBook.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException {

		String genre;
		String page;


		genre = request.getParameter(Search);
		System.out.println(genre);

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		List<BooksLibrary> book = new ArrayList<>();

		try {book = bookService.searchGenre(genre);

				if (book==null) {
					request.setAttribute("error", "book error");
					page = DEFAULT_PAGE;
				}else {
		
					request.setAttribute("books", book);
					page = SEARCH_BOOK;
				}	

		} catch (ServiceException e) {
			request.setAttribute("error", "book error");
			// log logger.error и сообщение
			page = DEFAULT_PAGE;

		}

		/*for (BooksLibrary b : book) {
			System.out.println(b.getName());
		}

		request.setAttribute("books", book);*/

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}



}
