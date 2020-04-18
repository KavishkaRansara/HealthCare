package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class payment {

	  public static Connection getconnection() {
		  Connection con = null;
		  try
		  {
		  Class.forName("com.mysql.jdbc.Driver");

		  //Provide the correct details: DBServer/DBName, username, password
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "Sliitstudent123");
		  }
		  catch (Exception e)
		  {e.printStackTrace();} 

		  return con;
	  }
	  
	  
}
