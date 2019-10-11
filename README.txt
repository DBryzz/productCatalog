**** Product Catalog By Domou Brice ****


**** Product Catalog is JAVAWEB Application ****



**** Requirements ****

	- Xampp(phpMyAdmin)
	
	- Apache Tomcat Server
	
	- Eclipse 

	- Maven




**** JAVA Files 

	- ConnectClass.java : Has a ConnectClass class used to connect to the database
	
	- Products.java : Products class used to manages different items
	
	- Category.java : Category class used to manage item categories and List of items
	
	- ProductService : ProductService class used to manage List of Categories




**** Functionality ****
	
	
	*** Common Users
	
	- Users can view all existing items found in the database (landing page) ---- index.jsp/IndexServlet
	
	- View list of existing item categories ---- list-category.jsp/LogInPageServlet.java/LogInServlet.java
	
	- Create and account (Signup) --- signup.jsp/SignUpPageServlet.java/SignUpServlet.java
	
	
	*** Users with accounts
	
	
	- Login --- login.jsp/LogInPageServlet.java/LogInServlet.java
	
	** Product
	
	- view all His existing items --- users.jsp/ListProductServelt.java
	
	- add items to existing categories in database --- new-product.jsp/AddProductServlet.java
	
	- delete His items --- DeleteProductServlet.java
	
	- update His already existing items --- update-product.jsp/UpdateProductServlet.java
	
	
	** Category
	
	- view all His existing created items --- list-category.jsp/ListCategoryServelt.java
	
	- create new categories which can be used by all other users as well --- new-category.jsp/AddCategoryServlet.java
	
	- delete His Category thereby deleting all of His items under that category but not deleting other users' items. 
	  
	  any other user's item under the deleted category will be attributed a Null category --- DeleteCategoryServlet.java
	
	- update His already existing categories --- update-category.jsp/UpdateCategoryServlet.java
	
	

	