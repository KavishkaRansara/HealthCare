package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

	
		
		 public static Connection getconnection() {
		     	
		 		try {
		 				Class.forName("com.mysql.cj.jdbc.Driver");
		 				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Healthcare", "root", "Sliitstudent123");
		 				
		 				return con;
		 		} catch (ClassNotFoundException | SQLException e) {
		 				// TODO Auto-generated catch block
		 				e.printStackTrace();
		 				return null;
		 		}
		 			
		 	
		 }//ending get connection method



	}


