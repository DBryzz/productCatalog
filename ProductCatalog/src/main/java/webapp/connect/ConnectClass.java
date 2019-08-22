package webapp.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.Statement;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class ConnectClass {
	
	private Connection conn;
	private Statement st;
	
	public ConnectClass() {
		
	}
	
	public boolean connect() {
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/productcatalog_db", "root", "root");
			System.out.println("Connection Successful");
			return true;
			
		} catch(SQLException e) {
			
			System.out.println("Error Connecting to Database");
			e.printStackTrace();
			return false;
			
		}
	}

}
