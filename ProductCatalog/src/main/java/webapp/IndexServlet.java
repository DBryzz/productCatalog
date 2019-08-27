package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.connect.ConnectClass;
import webapp.products.Category;
import webapp.products.Products;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/index.html")
public class IndexServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		
		
		Category category = new Category();
		
		request.getSession().setAttribute("connect", new ConnectClass().connect()); 
		Connection conn = (Connection) request.getSession().getAttribute("connect");

		try {

			PreparedStatement pst;

			String sql = "SELECT * FROM product_tbl";
			pst = conn.prepareStatement(sql);
			ResultSet result = pst.executeQuery();

			while (result.next()) {
				
				System.out.println(result.getString("pxtName"));

				category.addProduct(result.getString("pxtName"), result.getString("pxtCategory"), result.getString("owner"));

			}
			
			request.setAttribute("firstList", category.getProductList());

			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
			

		} catch (SQLException e) {

			System.out.println("SQL error = " + e);

			
		}
		
/*
		request.setAttribute("firstList", category.getProductList());

		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		
		*/
		
	}

}
