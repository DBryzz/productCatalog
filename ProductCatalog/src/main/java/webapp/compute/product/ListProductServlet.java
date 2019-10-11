package webapp.compute.product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.products.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet(urlPatterns = "/list.se")
public class ListProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = (String) request.getSession().getAttribute("userName");

		Category category = new Category();

		request.setAttribute("pxtList", category.makeProductList(name));

		request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
	}

}
