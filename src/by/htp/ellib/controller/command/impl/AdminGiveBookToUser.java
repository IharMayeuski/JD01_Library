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
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.LibraryService;
import by.htp.ellib.service.ServiceProvider;

public class AdminGiveBookToUser implements Command {

	private static final String USER_ID= "users_id";
	private static final String BOOK_ID = "book_id";


	private static final String PAGE = "/WEB-INF/jsp/adminGiveBookToUser.jsp";
	private static final String Admin_PAGE = "/WEB-INF/jsp/adminPage.jsp";



	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException, DaoException {

		System.out.println("create new info page");
		String page;

		try {

			String User_ID;
			String Book_ID;



			User_ID = request.getParameter(USER_ID);
			Book_ID = request.getParameter(BOOK_ID);

			int user = Integer.parseInt(User_ID);	
			int book = Integer.parseInt(Book_ID);	


			ServiceProvider provider = ServiceProvider.getInstance();
			LibraryService service = provider.getLibraryService();



			Info info = new Info();



			page = Admin_PAGE;

			boolean create;

			info.setBook_id(book);
			info.setUsers_id(user);

			System.out.println(info.toString()+"---------------");


			create=service.giveBookToUser(info);

			if (create == true)  {
				page = PAGE;

			}
			/*request.setAttribute("information", info);*/
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
