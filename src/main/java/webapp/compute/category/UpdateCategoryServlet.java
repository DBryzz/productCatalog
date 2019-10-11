package webapp.compute.category;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import webapp.products.Category;
import webapp.products.ProductService;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet(urlPatterns="/update-category.se") 
public class UpdateCategoryServlet extends HttpServlet {
	
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static int catID;
	private static String title;
	private static String description;
	//private formerCat

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		String owner = (String) request.getSession().getAttribute("userName");
		
		catID = Integer.parseInt(request.getParameter("id"));
		
		title = request.getParameter("name");
		description = request.getParameter("description");
		
		request.setAttribute("formerName", title);
		request.setAttribute("formerDescription", description);

		
		request.getRequestDispatcher("/WEB-INF/views/update-category.jsp").forward(request, response);

	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProductService newService = new ProductService();

		String pxtOwner = (String) request.getSession().getAttribute("userName");
		
		//int pxtID = Integer.parseInt(request.getParameter("id"));
	//	String catName = request.getParameter("catName");
	//	String  = request.getParameter("catDescription");
	//	String owner = request.getParameter("owner");
	//	String image = request.getParameter("image");
		
		
		
		
		

		try {

			Connection conn = (Connection) request.getSession().getAttribute("sessionConnect");

			PreparedStatement pst;
			
			String catName = request.getParameter("catName");
			String catDescription = request.getParameter("catDescription");
			

			String sql = "UPDATE category_tbl SET catName = ?, catDescription = ? WHERE catID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, catName);
			pst.setString(2, catDescription);
			pst.setInt(3, catID);

			boolean result = pst.execute();

			if (!result) {

				System.out.println( title + " ---- "+ description +" owned by "+ pxtOwner +" has been modified to "+ title + " ---- " + description);

				request.setAttribute("catList", newService.makeCategoryList(pxtOwner));

				
				request.getRequestDispatcher("/WEB-INF/views/new-category.jsp").forward(request, response);


			} else {
				System.out.println("Could not update "+ title);
				
				String updateMsg = "Could not update "+ title;
				
				//request.setAttribute("deleteMsg", deleteMsg);

				request.setAttribute("error_message", updateMsg);
				
		//		request.setAttribute("catList", newService.makeCategoryList(pxtOwner));
				
				request.setAttribute("formerName", title);
				request.setAttribute("formerDescription", description);
				request.getRequestDispatcher("/WEB-INF/views/update-category.jsp").forward(request, response);
			}

			
		} catch (SQLException e) {

			System.out.println("Querry error = " + e.getMessage());
			e.printStackTrace();
			
		//	request.setAttribute("catList", newService.makeCategoryList(pxtOwner));
			
			request.setAttribute("formerName", title);
			request.setAttribute("formerDescription", description);
			
			request.getRequestDispatcher("/WEB-INF/views/update-category.jsp").forward(request, response);
		}
	}



	public static int getPxtID() {
		return catID;
	}



	public static void setPxtID(int pxtID) {
		UpdateCategoryServlet.catID = pxtID;
	}



	

}
