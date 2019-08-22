package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.connect.ConnectClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;


@WebServlet(urlPatterns = "/index.html")
public class IndexServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		//Connection conn = new ConnectClass().connect();
		request.getSession().setAttribute("connect", new ConnectClass().connect()); 
		Connection conn = (Connection) request.getSession().getAttribute("connect");
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		
	}

}
