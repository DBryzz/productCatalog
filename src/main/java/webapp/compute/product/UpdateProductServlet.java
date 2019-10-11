package webapp.compute.product;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
@MultipartConfig(maxFileSize=8388608)			//16177215
@WebServlet(urlPatterns="/update-product.se") 
public class UpdateProductServlet extends HttpServlet {
	
       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private static int pxtID;
	private static String cat;
	private static String name;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	//	String pxtOwner = (String) request.getSession().getAttribute("userName");
		
		pxtID = Integer.parseInt(request.getParameter("id"));
		//setPxtID(id);
		cat = request.getParameter("category");
		name = request.getParameter("name");
	//	String owner = request.getParameter("owner");
	//	String image = request.getParameter("image");
		
		
		request.setAttribute("formerCat", cat);
		request.setAttribute("formerName", name);
		
		ProductService newService = new ProductService();;
		
		request.setAttribute("catList", newService.makeCategoryList());
		

		
		request.getRequestDispatcher("/WEB-INF/views/update-product.jsp").forward(request, response);

	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String pxtOwner = (String) request.getSession().getAttribute("userName");
		
		//int pxtID = Integer.parseInt(request.getParameter("id"));
	//	cat = request.getParameter("category");
	//	name = request.getParameter("name");
	//	String owner = request.getParameter("owner");
	//	String image = request.getParameter("image");
		
		
		
		Category category = new Category();
		
		

		try {

			Connection conn = (Connection) request.getSession().getAttribute("sessionConnect");

			PreparedStatement pst;
			
			String pxtName = request.getParameter("pxtName");
			String pxtCat = request.getParameter("pxtCat");
			
			Part imagePart = request.getPart("pxtImage");
			InputStream inputStream = imagePart.getInputStream();

			String sql = "UPDATE product_tbl SET pxtName = ?, pxtCategory = ?, pxtImage = ? WHERE pxtID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, pxtName);
			pst.setString(2, pxtCat);
			pst.setBlob(3, inputStream);
			pst.setInt(4, pxtID);

			boolean result = pst.execute();

			if (!result) {

				System.out.println( name + " ---- "+ cat +" has been modified to \n" + pxtName + " ---- " + pxtCat +" \n and is owned by "+ pxtOwner );

				request.setAttribute("pxtList", category.makeProductList(pxtOwner));

				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);


			} else {
				System.out.println("Could not update "+ pxtName);
				
				String updateMsg = "Could not update "+ pxtName;
				
				//request.setAttribute("deleteMsg", deleteMsg);

				request.setAttribute("error_message", updateMsg);
				
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



	public int getPxtID() {
		return pxtID;
	}



	public void setPxtID(int pxtID) {
		this.pxtID = pxtID;
	}
	
		
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
