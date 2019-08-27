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

import webapp.products.Category;

@WebServlet(urlPatterns = "/login.act")
public class LogInServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("/index.html");

	}
	
/*	
	Category category = new Category();
	
	String pxtOwner;
	if((String) request.getSession().getAttribute("userName") != null) {
		pxtOwner = (String) request.getSession().getAttribute("userName");
	} else{
		pxtOwner = (String) request.getSession().getAttribute("loginUserName");
	}
	
	request.setAttribute("pxtList", category.makeProductList(pxtOwner));
	 

	request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);

	
	*/
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String name = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Category category = new Category();
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

				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

			//	response.sendRedirect("/add-product.se");

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

}
