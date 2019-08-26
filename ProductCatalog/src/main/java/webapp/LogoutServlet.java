package webapp;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import webapp.in28minutes.UserValidationService;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/logout.se")
public class LogoutServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		request.getSession().invalidate();
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	   
}