package webapp.compute.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.products.Category;

/**
 * Servlet implementation class SignupServlet
 */

@WebServlet(urlPatterns="/delete-product.se")
public class DeleteProductServlet extends HttpServlet {
	
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pxtOwner = (String) request.getSession().getAttribute("userName");
		
		int pxtID = Integer.parseInt(request.getParameter("id"));
		String pxtCat = request.getParameter("category");
		String pxtName = request.getParameter("name");
		String owner = request.getParameter("owner");
		
		Category category = new Category();
		
		

		try {

			Connection conn = (Connection) request.getSession().getAttribute("connect");

			PreparedStatement pst;

			String sql = "DELETE FROM product_tbl WHERE pxtID = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, pxtID);

			boolean result = pst.execute();

			if (!result) {

				System.out.println( pxtName + " ---- " + pxtCat +" owned by "+ owner + "  has been removed from database");

				request.setAttribute("pxtList", category.makeProductList(pxtOwner));

				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);


			} else {
				System.out.println("Could not delete "+ pxtName);
				
				String deleteMsg = "Could not delete "+ pxtName;
				
				//request.setAttribute("deleteMsg", deleteMsg);

				request.setAttribute("error_message", deleteMsg);
				
				request.setAttribute("pxtList", category.makeProductList(pxtOwner));
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
			}

			/*
			 * result.close(); pst.close(); conn.close();
			 */
		} catch (SQLException e) {

			System.out.println("Querry error = " + e.getMessage());
			e.printStackTrace();
			
			request.setAttribute("pxtList", category.makeProductList(pxtOwner));
			request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
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
