package by.htp.ellib.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ellib.dao.ConnectPool.ConnectionPoolException;
import by.htp.ellib.exceptions.DaoException;
import by.htp.ellib.exceptions.ServiceException;

public interface Command {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ConnectionPoolException, ServiceException, DaoException  ;


}
