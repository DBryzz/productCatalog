package webapp.compute;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import webapp.products.Category;
import webapp.products.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@WebServlet(urlPatterns = "/add-product.se")
public class AddProductServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ProductService newService = new ProductService();;
		
		request.setAttribute("catList", newService.makeCategoryList());
		
		request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);

	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		

		//String pxtOwner;
		//if ((String) request.getSession().getAttribute("userName") != null) {
			
		//} else {
		//	pxtOwner = (String) request.getSession().getAttribute("loginUserName");
		//}
		
		String pxtOwner = (String) request.getSession().getAttribute("userName");
		
		Category category = new Category();
		

		try {

			Connection conn = (Connection) request.getSession().getAttribute("connect");

			PreparedStatement pst;

			/*
			 * Inserting images into database 1 - The form should have a multipart/form-data
			 * encryption type (enctype) 2 - convert form entries to list of fileitem 3 -
			 * specify data type of each entry
			 */

			// Apache Commons-Fileupload library classes

			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);

			if (!ServletFileUpload.isMultipartContent(request)) { // checking if form is multipart/form-date enctype
				System.out.println("You must upload a file");
				return;
			}

			// putting form entries into a list.

			@SuppressWarnings("unchecked")
			List<FileItem> items = fileUpload.parseRequest(request);
			out.print(items);

			// Form entry 1 is a string (product name)
			FileItem name = (FileItem) items.get(0); // put it a list of file
			String pxtName = name.getString(); // convert it to string
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
			pst.setString(2, pxtOwner);
			pst.setString(3, pxtCat);
			pst.setBinaryStream(4, pxtImage.getInputStream(), (int) pxtImage.getSize());

			int result = pst.executeUpdate();

			if (result != 0) {

				System.out.println(pxtName + " was successfully added to your catalog list");
				out.println(pxtName + " was successfully added to your catalog list");

				//request.getSession().setAttribute("loginUserName", name);
				
				request.setAttribute("pxtList", category.makeProductList(pxtOwner));

				request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);

				//  response.sendRedirect("/add-product.se");
			} else {

				System.out.println("Could not upload image\n Verify your form entries");

				request.setAttribute("error_message", "Could not upload image\n Verify your form entries");
				request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
			}

			// pst.close();
			// conn.close();

		} catch (Exception e) {

			System.out.println("Could not upload image\n Verify your form entries");

			System.out.println("file error = " + e.getMessage());
			request.setAttribute("error_message", "Could not upload image\n Verify your form entries");
			request.getRequestDispatcher("/WEB-INF/views/new-product.jsp").forward(request, response);
		}
	}

}
