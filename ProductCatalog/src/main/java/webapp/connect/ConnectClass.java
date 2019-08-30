package webapp.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.Statement;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

public class ConnectClass {
	
	private Connection conn;
	
	public ConnectClass() {
		
	}
	
	public Connection connect() {
		try {
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/productcatalog_db", "root", "root");
			System.out.println("Connection Successful");
			return conn;
			
		} catch(SQLException e) {
			
			System.out.println("Error Connecting to Database");
			e.printStackTrace();
			return null;
			
		}
	}

	
/*	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}
	
*/

}
