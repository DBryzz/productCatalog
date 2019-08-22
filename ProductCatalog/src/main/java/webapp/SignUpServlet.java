package webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import webapp.connect.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/signup.act")
public class SignUpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("name");
		String name = request.getParameter("userName");
		String password = request.getParameter("password");

		Connection conn = (Connection) request.getSession().getAttribute("connect");

		PreparedStatement pst;

		try {
			
			String sql = "INSERT INTO user_tbl (fullName, userName, password) VALUES(?, ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, fullName);
			pst.setString(2, name);
			pst.setString(3, password);
			
			int result = pst.executeUpdate(); // returns a boolean
			

			if (result != 0) {

				System.out.println(name +" was inserted into database");

				request.getSession().setAttribute("UserName", name);
				
				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

				//response.sendRedirect("/welcome.se");
			}

		} catch (SQLException e) {

			System.out.println("Incompatible User Name and Password Combination");

			request.setAttribute("error_message", "Incompatible User Name and Password Combination");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}
