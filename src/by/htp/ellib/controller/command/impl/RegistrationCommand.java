package by.htp.ellib.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.entity.User;
import by.htp.ellib.entity.UserData;
import by.htp.ellib.exceptions.ServiceException;
import by.htp.ellib.service.ClientService;
import by.htp.ellib.service.ServiceProvider;

public class RegistrationCommand implements Command{

	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_NAME = "name";
	private static final String PARAMETER_SURNAME = "surname";
	private static final String PARAMETER_EMAIL = "email";



	private static final String MAIN_PAGE = "/WEB-INF/jsp/main.jsp";
	private static final String INDEX_PAGE = "/index.jsp";
	private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";
	private static final String USER_PAGE = "/WEB-INF/jsp/userPage.jsp";



	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionPoolException {

		System.out.println("create new user");

		//	int id;
		String login;
		String password;
		String email;
		String name;
		String surname;

		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		email = request.getParameter(PARAMETER_EMAIL);
		name = request.getParameter(PARAMETER_NAME);
		surname = request.getParameter(PARAMETER_SURNAME);

		ServiceProvider provider = ServiceProvider.getInstance();
		ClientService service = provider.getClientService();

		UserData userData = new UserData();

		String page;
		int create;

		userData.setName(name);
		userData.setSurname(surname);
		userData.setEmail(email);
		userData.setLogin(login);
		userData.setPassword(password);



		System.out.println(userData.toString());
		try {
			create=service.registration(userData);

			if (create==1) {
				request.setAttribute("error", "somethind bad with your data! try again!");
				page = REGISTRATION_PAGE;
			}
			if (create==2) {
				request.setAttribute("error", "we have this login. Try again!");
				page = REGISTRATION_PAGE;


			}else {

				request.setAttribute("userData", userData);
				page = USER_PAGE;
			}

		} catch (ServiceException e) {
			request.setAttribute("error", "Error. Try again!");
			// log logger.error и сообщение
			page = REGISTRATION_PAGE;

		}

		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);


		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);


	}

}
