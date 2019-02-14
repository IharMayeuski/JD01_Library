package by.htp.ellib.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.entity.Book;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class FindBookCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String criteria = " ";

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		List<Book> book = bookService.find(criteria);

		for (Book b : book) {
			System.out.println(b.getTitle());
		}

		request.setAttribute("books", book);

		// нужно ли...	
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/displayBook.jsp");
		dispatcher.forward(request, response);



	}


}
