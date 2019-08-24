package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet(urlPatterns = "/login-page.html")
public class LogInPageServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int choice = Integer.parseInt(request.getParameter("choice"));
		
		if(choice == 1)
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
		if(choice == 2)
			request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
	}

}
