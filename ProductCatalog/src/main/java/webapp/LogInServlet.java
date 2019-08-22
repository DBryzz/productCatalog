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

@WebServlet(urlPatterns = "/login.act")
public class LogInServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

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

		} catch (SQLException e) {

			System.out.println("Incompatible User Name and Password Combination");

			request.setAttribute("error_message", "Incompatible User Name and Password Combination");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}
