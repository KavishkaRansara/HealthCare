package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class payment {

	public static Connection getconnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "Sliitstudent123");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

//insert payment, while they paid the deletion will happen with this(the payment will delete from remain payment table)  
	public String insertPayment() {
		String msg = "";

		return msg;
	}
	
//update added amount and producing updatedd html table using multiple pruduces MediaType
	public String updatePaymentdetails(String payID, String patientID, String doctorID, String date, String amount,
			String cardnumber, String postalnumber) {
		String output = "";

		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error occured while updating the appointment details.";
			}

			// create a prepared statement

			String query = "UPDATE successpayment SET patientID=?,doctorID=?,date=?, amount=?, cardnumber=?,postalnumber=? where payID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values to query

			preparedStmt.setString(1, patientID);
			preparedStmt.setString(2, doctorID);
			preparedStmt.setString(3, date);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setInt(5, Integer.parseInt(cardnumber));
			preparedStmt.setInt(6, Integer.parseInt(postalnumber));
			preparedStmt.setInt(7, Integer.parseInt(payID));

			// Executeing the statement
			preparedStmt.execute();
			con.close();

			output = "payment update successfully.";

		}

		catch (Exception e) {

			output = "An error occurred while updating details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// reading the payment details
	public String readpayment() {
		String output = "";
		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error while connecting to the database for reading the appointmentdetails.";

			}
			output = "<table border=\"1\"><tr>" 
					+ "<th>Payment ID</th>" 
					+ "<th>patient ID</th>" 
					+ "<th>doctor ID</th>"
					+ "<th>Date</th>" 
					+ "<th>Amount</th>" 
					+ "<th>card number</th>" 
					+ "<th>postal number</th></tr>";

			String query = "select * from successpayment";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String payID = Integer.toString(rs.getInt("payID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String date = rs.getString("date");
				String amount = Double.toString(rs.getDouble("amount"));
				String cardnumber = Integer.toString(rs.getInt("cardnumber"));
				String postlnumber = Integer.toString(rs.getInt("postalnumber"));

				output += "<td>" + payID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + cardnumber + "</td>";
				output += "<td>" + postlnumber + "</td></tr>";

			}

			con.close();

			output += "</table>";

		} catch (Exception e) {

			output = "An error occurred while reading the appointment details. ";
			System.err.println(e.getMessage());

		}

		return output;

	}

	// implementing the payment logic
	public String payAppointment(String payID, String patientID, String doctorID, String date, String amount,
			String cardnumber, String postalnumber) {
		String msg = "";

		try {
			Connection con = getconnection();

			if (con == null)

			{
				return "Error while connecting to the database for inserting appointment details.";
			}
			// create a prepared statement
			String query = " insert into successpayment (payID,patientID,doctorID,date,amount,cardnumber,postalnumber)"
					+ " values (?,?,?,?,?,?,?)";
			

			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			

			// binding values to appointment table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, patientID);
			preparedStmt.setString(3, doctorID);
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setInt(6, Integer.parseInt(cardnumber));
			preparedStmt.setInt(7, Integer.parseInt(postalnumber));
			
			
			preparedStmt.execute();
			
			
			con.close();
			msg = "Paid successfully";

		} catch (Exception e) {
			msg = "Error while pay ";
			System.err.println(e.getMessage());
		}

		return msg;
	}
	
	//implement the delete payment
public String paymentDelete(String payID) {
		
		String output = "";

		try {

			Connection con = getconnection();

			if (con == null) {

				return "Error occured while deleting the payment details.";
			}

			// create a prepared statement
			String query = "delete from successpayment where payID=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payID));

			// executing the statements
			preparedStmt.execute();

			con.close();

			output = " payment Deleted successfully.";
			
		} catch (Exception e) {

			output = " An error occurred while deleting the payment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
