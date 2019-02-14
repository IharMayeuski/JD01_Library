package by.htp.ellib.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ellib.controller.command.Command;

public class ChangeLocale implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newLocale;
		HttpSession session;

		newLocale = request.getParameter("locale");
		System.out.println("NewLocale создана - "+newLocale);

		session = request.getSession(true);
		session.setAttribute("local", newLocale);

		String url = (String) request.getSession(false).getAttribute("prev_request");

		System.out.println("Searching url = "+url);

		if (url==null) {
			url = "http://localhost:8082/ElLibrary/controller?command=go_to_index&";
		}

		response.sendRedirect(url);
	}

}
