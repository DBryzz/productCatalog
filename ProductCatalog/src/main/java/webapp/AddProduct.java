package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet(urlPatterns = "/add-product.se")
public class AddProduct extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
		
	}

}
