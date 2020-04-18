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
	
	public String readHospitals() 
	{
		String output = "";
		
		try {
		Connection con = connect(); 
			 
			if (con == null) { 
				return "Error while connecting to the database for reading."; 
			} 
			output = "<table border=\"1\"><tr>"
					+ "<th>Hospital ID</th>"
					+ "<th>Hospital Name</th>"
					+ "<th>Hospital Province</th>"
					+ "<th>Hospital District</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "<th>Services</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>"; 
			 
			String query = "select * from hospital"; 
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{   
				String hospitalID = Integer.toString(rs.getInt("hospitalID")); 
				String hospitalName = rs.getString("hospitalName");  
				String hospitalProvince = rs.getString("hospitalProvince"); 
				String hospitalDistrict = rs.getString("hospitalDistrict");
				String hospitalEmail = rs.getString("hospitalEmail");
				String hospitalPhone = rs.getString("hospitalPhone");
				String hospitalServices = rs.getString("hospitalServices");
				
				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + hospitalName + "</td>";
				output += "<td>" + hospitalProvince + "</td>";   
				output += "<td>" + hospitalDistrict + "</td>"; 
				output += "<td>" + hospitalEmail + "</td>";
				output += "<td>" + hospitalPhone + "</td>";
				output += "<td>" + hospitalServices + "</td>";
				
				
				output += "<td><input name=\"btnUpdate\" "  
					   + " type=\"button\" value=\"Update\"></td>"   
					   + "<td><form method=\"post\" action=\"hospitals.jsp\">"
				       + "<input name=\"btnRemove\" "
					   + " type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
				       + "<input name=\"hospitalID\" type=\"hidden\" "   
					   + " value=\"" + hospitalID + "\">" 
				       + "</form></td></tr>";    
			 
			}
			con.close(); 
			output += "</table>";  
		
		}
		catch (Exception e) {  
			output = "Error while reading the hospitals.";  
			System.err.println(e.getMessage()); 
		}
		return output;
	}
}
	
	

	
		
