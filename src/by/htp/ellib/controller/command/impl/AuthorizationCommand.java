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
import by.htp.ellib.entity.User;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class AuthorizationCommand implements Command{

	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";

	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	private static final String INDEX_PAGE = "/index.jsp"; // here was index.jsp
	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
	private static final String ADMIN_PAGE = "/WEB-INF/jsp/adminPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionPoolException {


		ServiceProvider provider = ServiceProvider.getInstance();
		LibraryService bookService = provider.getLibraryService();

		//	List<Book> book = bookService.find("");

		List<BooksLibrary> book = new ArrayList<>();

		try {
			book = bookService.all();
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<BooksLibrary> bookSmall = new ArrayList<>();
		for (int i=book.size()-1; i>=book.size()-3; i--) {
			bookSmall.add(book.get(i));

		}

		request.setAttribute("books", bookSmall);




		String login;
		String password;

		System.out.println(CreatorFullURL.create(request));

		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);

		provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		User user = null;
		String page = null;

		HttpSession session = request.getSession(true);
		String loc = (String) session.getAttribute("local");
		//System.out.println("@@@@@"+ loc);


		try {

			user = service.authorization(login, password);

			if (user.getLogin().equals("admin")){
				request.setAttribute("user", user.getLogin());
				
				
				
				page = ADMIN_PAGE;
			}else {

				if (user==null) {
					request.setAttribute("error", "login or password error");
					page = DEFAULT_PAGE;

				} else if (user!=null && user.getLogin().equals("admin")==false) {
					request.setAttribute("user", user);
					page = MAIN_PAGE;						
				} else {
					request.setAttribute("error", "login or password error");
					page = DEFAULT_PAGE;
				}
			}




		} catch (Exception e) {
			request.setAttribute("error", "login or password error");
			// log logger.error и сообщение
			page = DEFAULT_PAGE;

		}

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
	

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);


	}

}
