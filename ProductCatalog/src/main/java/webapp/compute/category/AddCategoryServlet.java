package webapp.compute.category;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import webapp.products.Category;
import webapp.products.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet(urlPatterns = "/add-category.se")
public class AddCategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String catOwner = (String) request.getSession().getAttribute("userName");
		
		
		ProductService newService = new ProductService();
		
		request.setAttribute("catList", newService.makeCategoryList(catOwner));
		
		request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String catName = request.getParameter("catName");
		String catDescription = request.getParameter("catDescription");
		

		
		
		String catOwner = (String) request.getSession().getAttribute("userName");
		
		
		ProductService newService = new ProductService();

		try {

			Connection conn = (Connection) request.getSession().getAttribute("sessionConnect");

			PreparedStatement pst;

			

			
			String sql = "INSERT INTO category_tbl (catName, catDescription, owner) VALUES(?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, catName);
			pst.setString(2, catDescription);
			pst.setString(3, catOwner);

			int result = pst.executeUpdate();

			if (result != 0) {

				System.out.println(catName + " was successfully added to your catalog list");
				//out.println(catName + " was successfully added to your catalog list");

				//request.getSession().setAttribute("loginUserName", name);
				
				request.setAttribute("catList", newService.makeCategoryList(catOwner));

				request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);

				//  response.sendRedirect("/add-product.se");
			} else {

				System.out.println("Could not add category\n Verify your form entries");

				request.setAttribute("error_message", "Could not add category\n Verify your form entries");
				request.setAttribute("catList", newService.makeCategoryList(catOwner));
				request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
			}

			// pst.close();
			// conn.close();

		} catch (Exception e) {

			System.out.println("Could not add category\n Verify your form entries");

			System.out.println("file error = " + e.getMessage());
			request.setAttribute("error_message", "Could not add category\n Verify your form entries");
			request.setAttribute("catList", newService.makeCategoryList(catOwner));
			request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
		}
	}

}
