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
import by.htp.ellib.entity.Book;
import by.htp.ellib.entity.BooksLibrary;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class GoToIndexPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException, ConnectionPoolException {

		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		//	List<Book> book = bookService.find("");

		List<BooksLibrary> book = new ArrayList<>();

		book = bookService.all();

		List<BooksLibrary> bookSmall = new ArrayList<>();
		for (int i=book.size()-1; i>=book.size()-3; i--) {
			bookSmall.add(book.get(i));

		}

		request.setAttribute("books", bookSmall);

		/*HttpSession session = request.getSession(true);
		String loc = (String) session.getAttribute("local");
		//System.out.println("@@@@@"+ loc);
*/
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/default.jsp");
		dispatcher.forward(request, response);

	}

}
