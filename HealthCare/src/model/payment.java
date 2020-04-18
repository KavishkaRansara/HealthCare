package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

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
	  
//insert payment, while they paid the deletion will happen with this(the payment will delete from remain payment table)  
	  public String insertPayment() {
		  String msg = "";
		  
		  return msg;
	  }
	  
//Add payment to the appointment
	  public String appointmentAmount(String patientID,String doctorID,String amount) {
	      String msg = "";
		  
	      try {
				Connection con = getconnection();

				if (con == null)

				{
					return "Error while connecting to the database for inserting appointment details.";
				}
				// create a prepared statement
				String query = " insert into remainpayment (payID,patientID,doctorID,amount)" + " values (?,?,?,?)"; 
				java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values to appointment table
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, patientID);
				preparedStmt.setString(3, doctorID);
				preparedStmt.setDouble(4, Double.parseDouble(amount));
				
				preparedStmt.execute();
				con.close();
				msg = "Inserted successfully"; 

				
				} 
				catch (Exception e) {
				msg = "Error while inserting the appointment details.";
				System.err.println(e.getMessage());
				}
	      
		  return msg;
	  }
	  
//update added amount
	  public String updateAmount() {
	      String msg = "";
		  
		  return msg;
	  } 

	  //reading the payment details
	  public String readRemainpayment() {
		  String output="";
			try {
  
				
				Connection con = getconnection();

				if (con == null) {

					return "Error while connecting to the database for reading the appointmentdetails.";

				}
				output = "<table border=\"1\"><tr>"
						+ "<th>Payment ID</th>"
						+ "<th>patient ID</th>"
						+ "<th>doctor ID</th>"
						+ "<th>Amount</th></tr>"; 

				String query = "select * from remainpayment";

				java.sql.Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {

					String payID = Integer.toString(rs.getInt("payID"));
					String patientID = rs.getString("patientID");
					String doctorID = rs.getString("doctorID");
					String amount = Double.toString(rs.getDouble("amount"));
					

					output += "<td>" + payID + "</td>";
					output += "<td>" + patientID + "</td>";   
					output += "<td>" + doctorID + "</td>"; 
					output += "<td>" + amount + "</td>";
					    

				}

				con.close();

				output += "</table>";

				
			} catch (Exception e) {

				output = "An error occurred while reading the appointment details. ";
				System.err.println(e.getMessage());

			}

			return output;

	  }

}
