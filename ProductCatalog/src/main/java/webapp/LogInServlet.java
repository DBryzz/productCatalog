package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import webapp.connect.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(urlPatterns = "/login.act")
public class LogInServlet extends HttpServlet {
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		// String pxtName = request.getParameter("pxtName");
		// String pxtCat = request.getParameter("pxtCat");
	    // String pxtImage = request.getParameter("pxtImage");
		String pxtOwner;
	    if((String) request.getSession().getAttribute("userName") != null) {
	    	pxtOwner = (String) request.getSession().getAttribute("userName");
	    } else {
	    	pxtOwner = (String) request.getSession().getAttribute("loginUserName");
	    }
	    
	    
	    Connection conn = (Connection) request.getSession().getAttribute("connect");

		PreparedStatement pst;

		try {
			
			
			
			// Inserting images into database
			
			// 1 - The form should have a multipart/form-data encryption type (enctype)
			
			// 2 - convert form entries to list of fileitem
			
			// 3 - specify data type of each entry 
			
			
			// Apache Commons-Fileupload library classes
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			if (! ServletFileUpload.isMultipartContent(request)) {		// checking if form is multipart/form-date enctype
				System.out.println("You must upload a file");
				return;
			}
			
			// putting form entries into a list.
			
			List<FileItem> items = {fileUpload.parseRequest(request);
			out.print(items);
			
			// Form entry 1 is a string (product name)
			FileItem name = (FileItem) items.get(0); // put it a list of file
			String pxtName = name.getString();	// convert it to string
			out.println(pxtName);
			
			// entry 2 String too
			FileItem cat = (FileItem) items.get(1);
			String pxtCat = cat.getString();
			out.println(pxtCat);
			
			// entry 3 is a file(image)
			FileItem pxtImage = (FileItem) items.get(2); // no need for conversion
			
			
			String sql = "INSERT INTO product_tbl (pxtName, owner, pxtCategory, pxtImage) VALUES(?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, pxtName);
			pst.setString(2, pxtName);
			pst.setBinaryStream(3, pxtImage.getInputStream(), (int) pxtImage.getSize());
			pst.setString(4, pxtOwner);
			
			int result = pst.executeUpdate();
			

			if (result > 0) {

				System.out.println(pxtName + " was successfully added to your catalog list");
				out.println(pxtName + " was successfully added to your catalog list");

				request.getSession().setAttribute("loginUserName", name);
				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

				//response.sendRedirect("/welcome.se");
			}
			
			pst.close();
			conn.close();

		} catch (SQLException e) {

			System.out.println("Could not upload image\n Verify your form entries");

			request.setAttribute("error_message", "Could not upload image\n Verify your form entries");
			request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
		}
	}


	    

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		

		Connection conn = (Connection) request.getSession().getAttribute("connect");

		PreparedStatement pst;

		try {
			
			String sql = "SELECT * FROM user_tbl WHERE userName = ?  AND password = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			
			ResultSet result = pst.executeQuery();

			if (result.next()) {

				System.out.println("found " + name + " in database");

				request.getSession().setAttribute("loginUserName", name);
				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

				//response.sendRedirect("/welcome.se");
				
				
			}
			
			result.close();
			pst.close();
			conn.close();

		} catch (SQLException e) {

			System.out.println("Incompatible User Name and Password Combination");

			request.setAttribute("error_message", "Incompatible User Name and Password Combination");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}
