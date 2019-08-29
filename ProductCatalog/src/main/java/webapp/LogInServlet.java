package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import webapp.connect.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import webapp.products.Category;
import webapp.products.Products;

@WebServlet(urlPatterns = "/login.act")
public class LogInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("/index.html");

	}

	/*
	 * Category category = new Category();
	 * 
	 * String pxtOwner; if((String) request.getSession().getAttribute("userName") !=
	 * null) { pxtOwner = (String) request.getSession().getAttribute("userName"); }
	 * else{ pxtOwner = (String) request.getSession().getAttribute("loginUserName");
	 * }
	 * 
	 * request.setAttribute("pxtList", category.makeProductList(pxtOwner));
	 * 
	 * 
	 * request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(
	 * request, response);
	 * 
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("userName");
		String password = request.getParameter("password");

		
	//	byte[] imgData = null;
		Category category = new Category();
	//	List<String> imageList = new ArrayList<String>();
   //		List<Products> pxtList = category.makeProductList(name);
	
/*
		for(int i=0; i<pxtList.size(); i++) {
			
			
			OutputStream out = response.getOutputStream();
			Blob image = pxtList.get(i).getPxtImage();
			
			InputStream inputStream = image
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			byte[] imageBytes = outputStream.toByteArray();
			String baseimg = Base64.getEncoder().encodeToString(imageBytes);
			inputStream.close();
			outputStream.close();
		//	prd.setBaseimg(baseimg);
			imageList.add(baseimg);
		}
		
		request.setAttribute("imageList", imageList); 
	*/
	
		request.setAttribute("pxtList", category.makeProductList(name));

		try {

			Connection conn = (Connection) request.getSession().getAttribute("connect");

			PreparedStatement pst;

			String sql = "SELECT * FROM user_tbl WHERE userName = ?  AND password = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);

			ResultSet result = pst.executeQuery();

			if (result.next()) {

				System.out.println("found " + name + " in database");

				request.getSession().setAttribute("loginUserName", name);

			/*	
				try {
					byte[] imgData = null;

					sql = "SELECT pxtImage FROM product_tbl WHERE owner=? GROUP BY pxtID";
					pst = conn.prepareStatement(sql);
					pst.setString(1, name);
					result = pst.executeQuery();
					OutputStream out = response.getOutputStream();
					while (result.next()) {
						Blob image = result.getBlob("pxtImage");
						imgData = image.getBytes(1, (int) image.length());
						
						// display the image
						response.setContentType("image/jpg");
						
						out.write(imgData);
						out.flush();
						out.close();
					} 
					
					
					
					
					
				} catch (Exception e) {
					System.out.println("Unable To Display image");
					System.out.println("Image Display Error=" + e.getMessage());
					return;
				}
*/
				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

				// response.sendRedirect("/add-product.se");

			} else {
				System.out.println("Incompatible User Name and Password Combination");

				request.setAttribute("error_message", "Incompatible User Name and Password Combination");
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			}

			/*
			 * result.close(); pst.close(); conn.close();
			 */
		} catch (SQLException e) {

			System.out.println("Querry error = " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

	/*
	 * byte[ ] imgData = null ; PreparedStatement prs=null; try { String
	 * sql="select productimage from products where productid='"+request.
	 * getParameter("prdtdel")+"'"; Connection con=ConnectDB.DbConnector(); prs =
	 * con.prepareStatement(sql); ResultSet rs=prs.executeQuery(); if (rs.next()) {
	 * Blob image = rs.getBlob(1); imgData = image.getBytes(1,(int)image.length());
	 * } else { System.out.println("Display Blob Example");
	 * System.out.println("image not found for given id>"); return; } // display the
	 * image response.setContentType("image/jpg"); OutputStream o =
	 * response.getOutputStream(); o.write(imgData); o.flush(); o.close(); } catch
	 * (Exception e) { System.out.println("Unable To Display image");
	 * System.out.println("Image Display Error=" + e.getMessage()); return; }
	 * 
	 * OutputStream out = response.getOutputStream();
			Blob image = pxtList.get(i).getPxtImage();
			imgData = image.getBytes(1, (int) image.length());
			response.setContentType("image/jpg");
			out.write(imgData);
			out.flush();
			out.close();
	 */

}
