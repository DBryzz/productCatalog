package webapp.compute.category;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.products.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet(urlPatterns = "/list.html")
public class ListCategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
			

			ProductService newService = new ProductService();
			
			request.setAttribute("catList", newService.makeCategoryList());
			
			request.getRequestDispatcher("/WEB-INF/views/list-category.jsp").forward(request, response);
	}

}
