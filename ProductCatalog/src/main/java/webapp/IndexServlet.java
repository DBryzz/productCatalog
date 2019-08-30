package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.connect.ConnectClass;
import webapp.products.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

@WebServlet(urlPatterns = "/index.html")
public class IndexServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Category category = new Category();

		request.getSession().setAttribute("connect", new ConnectClass().connect());
		Connection conn = (Connection) request.getSession().getAttribute("connect");

		try {

			PreparedStatement pst;

			String sql = "SELECT * FROM product_tbl GROUP BY pxtID";
			pst = conn.prepareStatement(sql);
			ResultSet result = pst.executeQuery();

			while (result.next()) {

				System.out.println(result.getString("pxtName"));

				Blob blobImage = result.getBlob("pxtImage");

				InputStream inputStream = blobImage.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				byte[] imageBytes = outputStream.toByteArray();
				String pxtImage = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();

				category.addProduct(result.getString("pxtName"), result.getString("pxtCategory"),
						result.getString("owner"), pxtImage);

			}

			request.setAttribute("firstList", category.getProductList());

			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

		} catch (SQLException e) {

			System.out.println("SQL error = " + e);

		}

		/*
		 * request.setAttribute("firstList", category.getProductList());
		 * 
		 * request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request,
		 * response);
		 * 
		 */

	}

}
