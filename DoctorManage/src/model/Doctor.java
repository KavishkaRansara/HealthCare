package model;

import java.sql.*;


public class Doctor {
	
	//connection the code to the mysql db
	
	public Connection connect(){
		Connection con = null;
	 try
	 {
	       Class.forName("com.mysql.jdbc.Driver");
	       //Provide the correct details: DBServer/DBName, username, password
	       con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "");
	       
	       //For testing
	       System.out.print("Successfully connected");
	 }
	 catch(Exception e){

	    e.printStackTrace();
	 }
	 return con;
	}
	
	
}	


