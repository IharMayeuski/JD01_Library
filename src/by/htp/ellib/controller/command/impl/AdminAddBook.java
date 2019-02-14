package by.htp.ellib.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class AdminAddBook implements Command {

	private static final String PARAMETER_author = "author";
	private static final String PARAMETER_name = "name";
	private static final String PARAMETER_year = "year_book";
	private static final String PARAMETER_genre = "genre";
	private static final String PARAMETER_price = "price";

	private static final String AllOK = "/WEB-INF/jsp/adminInputBook.jsp";
	private static final String TryAgain = "/WEB-INF/jsp/adminPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException, DaoException {

		String author;
		String name;
		String year_;
		int year;
		String genre;
		String price_;
		int price;
		String page;

		try {
			author = request.getParameter(PARAMETER_author);
			name = request.getParameter(PARAMETER_name);
			year_ = request.getParameter(PARAMETER_year);
			year = Integer.parseInt(year_);

			genre = request.getParameter(PARAMETER_genre);
			price_ = request.getParameter(PARAMETER_price);
			price = Integer.parseInt(price_);


			ServiceProvider provider = ServiceProvider.getInstance();
			LibraryService service = provider.getLibraryService();

			BooksLibrary book = new BooksLibrary();

			page = "";
			int create;

			book.setAuthor(author);
			book.setName(name);
			book.setGenre(genre);
			book.setYear_book(year);
			book.setPrice(price);

			System.out.println(book.toString());


			create=service.registration(book);


			if (create==0) {
				request.setAttribute("book", book);
				page = AllOK;
			}
			if (create==1) {
				request.setAttribute("error", "somethind bad with your data! try again!");
				page = TryAgain;
			}
		} catch (Exception e) {
			request.setAttribute("error", "somethind bad with your data! try again!");
			System.out.println("!!");
			page = TryAgain;

		}

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);


		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}





