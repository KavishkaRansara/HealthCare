package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hospital {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitaldetailsdb","root","root");
	 
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace(); 
	 }

	 return con;
	
	}
	
	public String insertHospitals(String hName, String hProvince, String hDistrict, String hEmail,String hPhone,String hServices) 
	{
			
		String output = ""; 
		
		try {
		Connection con = connect(); 
		 
		if (con == null) { 
			return "Error while connecting to the database"; 
		} 
	
	
		String query = " insert into hospital (hospitalID,hospitalName,hospitalProvince,hospitalDistrict,hospitalEmail,hospitalPhone,hospitalServices)" + " values (?,?,?,?,?,?,?)"; 
		java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, hName);
		preparedStmt.setString(3, hProvince);
		preparedStmt.setString(4, hDistrict);
		preparedStmt.setString(5, hEmail);
		preparedStmt.setString(6, hPhone);
		preparedStmt.setString(7, hServices);
		preparedStmt.execute();
		con.close(); 
		output = "Inserted successfully"; 
		
		}
		catch(Exception e) {
		output = "Error while inserting";  
		System.err.println(e.getMessage()); 
		}
		return output; 
	}	
}
	
	

	
		