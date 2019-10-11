package webapp.compute.category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.products.ProductService;

/**
 * Servlet implementation class SignupServlet
 */

@WebServlet(urlPatterns="/delete-category.se")
public class DeleteCategoryServlet extends HttpServlet {
	
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String catOwner = (String) request.getSession().getAttribute("userName");
		
		int catID = Integer.parseInt(request.getParameter("id"));
		String catName = request.getParameter("name");
		String catDescription = request.getParameter("description");
		String owner = request.getParameter("owner");
		
		ProductService newService = new ProductService();
		
		

		try {

			Connection conn = (Connection) request.getSession().getAttribute("sessionConnect");

			PreparedStatement pst;

			String sql = "DELETE FROM category_tbl WHERE catID = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, catID);

			
			boolean result = pst.execute();

			if (!result) {
				
				request.setAttribute("catList", newService.makeCategoryList(catOwner));
				System.out.println( catName + " ---- " + catDescription +" created by "+ owner + "  has been removed from database");
				
				String sql1 = "DELETE FROM product_tbl WHERE pxtCategory = ? AND owner = ?"; 
				
			//	DELETE FROM product_tbl WHERE pxtID = (SELECT pxtID FROM product_tbl WHERE pxtCategory = ? AND owner =?)
				
				PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, catName);
				pst1.setString(2, owner);
				
				boolean result1 = pst1.execute();
				

				if (!result1) {

					System.out.println( catName + " --- " + catDescription + " as well as all your products under it have been deleted");
					request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);

					
				} else {
					System.out.println("Could not delete items under "+ catName);
					
					String deleteMsg = "Could not delete items under "+ catName;
					
					request.setAttribute("error_message", deleteMsg);
					
					request.setAttribute("catList", newService.makeCategoryList(catOwner));
					request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);
				
				}
				
				

			} else {
				System.out.println("Could not delete "+ catName);
				
				String deleteMsg = "Could not delete "+ catName;
				
				//request.setAttribute("deleteMsg", deleteMsg);

				request.setAttribute("error_message", deleteMsg);
				
				request.setAttribute("catList", newService.makeCategoryList(catOwner));
				request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);
			}

			/*
			 * result.close(); pst.close(); conn.close();
			 */
		} catch (SQLException e) {

			System.out.println("Querry error = " + e.getMessage());
			e.printStackTrace();
			
			request.setAttribute("catList", newService.makeCategoryList(catOwner));
			request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);
		}
	}
	
		
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//request.getSession().setAttribute(browser, arg1);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
