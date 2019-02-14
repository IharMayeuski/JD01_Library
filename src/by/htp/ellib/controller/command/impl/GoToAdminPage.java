package by.htp.ellib.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.controller.command.Command;
import by.htp.ellib.controller.command.util.CreatorFullURL;
import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.exceptions.ServiceException;

public class GoToAdminPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ConnectionPoolException, ServiceException {
		
		/*String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);
*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminPage.jsp");
		dispatcher.forward(request, response);
	}

}
