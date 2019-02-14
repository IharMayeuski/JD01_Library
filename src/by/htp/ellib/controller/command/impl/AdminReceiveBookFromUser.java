package by.htp.ellib.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.Info;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class AdminReceiveBookFromUser implements Command {

	private static final String USER_ID= "users_id";
	private static final String BOOK_ID = "book_id";


	private static final String PAGE = "/WEB-INF/jsp/adminReceivedBookFromUser.jsp";
	private static final String Admin_PAGE = "/WEB-INF/jsp/adminPage.jsp";



	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException, DaoException {

		System.out.println("take back book");
		
		String page;
		
		try {

			String Book_ID;

			Book_ID = request.getParameter(BOOK_ID);

			int book = Integer.parseInt(Book_ID);	


			ServiceProvider provider = ServiceProvider.getInstance();
			LibraryService service = provider.getLibraryService();

			Info info = new Info();

			

			page = Admin_PAGE;

			boolean delete;

			info.setBook_id(book);

			System.out.println(info.toString());


			delete=service.backBookFromUser(info);

			if (delete == true) {
				page = PAGE;

			}
			request.setAttribute("information", info);
		} catch (Exception e) {
			request.setAttribute("error", "somethind bad with your data! try again!");
			page = Admin_PAGE;
		}

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);


		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);


	}

}
