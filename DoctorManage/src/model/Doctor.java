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
	
	
public String insertDoctor(String doctorName, String specialization, String hospital, String contact, String email, String status){
	    
	    String output = "";
		
		try{
	         Connection con = connect();
	         if (con == null)
	         {
	             return "Error while connecting to the database";
	         }
	        
	        // create a prepared statement
	        String query = " insert into doctors(`docID`,`docName`,`docSpec`,`docHosp`,`docContact`,`docEmail`,`docStat`)" + " values (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement preparedStmt = con.prepareStatement(query);

	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, doctorName);
	        preparedStmt.setString(3, specialization);
	        preparedStmt.setString(4, hospital);
	        preparedStmt.setInt(5, Integer.parseInt(contact));
	        preparedStmt.setString(6, email);
	        preparedStmt.setString(7, status);

	        //execute the statement
	        preparedStmt.execute();
	        con.close();

	        output = "Inserted successfully";

		}catch(Exception e){

	        output = "Error while inserting";
	        System.err.println(e.getMessage());
		}

		return output;

	}
	
	
	
	public String readDoctor(){

		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}


				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Doctor Name</th><th>Specialization</th><th>Hospital</th><th>Contact</th><th>Email</th><th>Status</th><th>Update</th><th>Remove</th></tr>";
				String query = "select * from doctors";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String docID = Integer.toString(rs.getInt("docID"));
					String docName = rs.getString("docName");
					String docSpec = rs.getString("docSpec");
					String docHosp = rs.getString("docHosp");
					String docContact = Integer.toString(rs.getInt("docContact"));
					String docEmail = rs.getString("docEmail");
					String docStatus = rs.getString("docStat");

					// Add into the html table
					output += "<tr><td>" + docName + "</td>";
					output += "<td>" + docSpec + "</td>";
					output += "<td>" + docHosp + "</td>";
					output += "<td>" + docContact + "</td>";
					output += "<td>" + docEmail + "</td>";
					output += "<td>" + docStatus + "</td>";
					
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"index.jsp\">"
					+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
					+ "<input name=\"itemID\" type=\"hidden\" value=\"" + docID
					+ "\">" + "</form></td></tr>";
				}

				con.close();
				// Complete the html table
				output += "</table>";
		}
		catch (Exception e)
		{
				output = "Error while reading the values.";
				System.err.println(e.getMessage());
		}
	return output;
}
	
}	


