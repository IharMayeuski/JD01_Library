package by.htp.ellib.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class AdminLookAllBook implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException {

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		List<BooksLibrary> book = new ArrayList<>();

		book = bookService.all();

		request.setAttribute("books", book);
		System.out.println(book.size());

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminLookBook.jsp");
		dispatcher.forward(request, response);

	}

}
