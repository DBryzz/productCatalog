package webapp.signup;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet(urlPatterns = "/signup-page.html")
public class SignUpPageServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
		
	}

}
