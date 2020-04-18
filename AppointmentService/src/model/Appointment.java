package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Appointment {


	private Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
					
			System.out.println("Successfully Connected");

		} catch (Exception e) {

			e.printStackTrace();
			
		}
		return con;
	}

	public String insertAppointments(String pID, String dID, String appDate, String appTime) {

		String output = ""; 
		
		try {
			Connection con = connect();

			if (con == null)

			{
				return "Error while connecting to the database for inserting appointment details.";
			}
			// create a prepared statement
			String query = " insert into appointment (appointmentID,patientID,doctorID,appointmentDate,appointmentTime)" + " values (?,?,?,?,?)"; 
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values to appointment table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, pID);
			preparedStmt.setString(3, dID);
			preparedStmt.setString(4, appDate);
			preparedStmt.setString(5, appTime);
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully"; 

			
			} 
			catch (Exception e) {
			output = "Error while inserting the appointment details.";
			System.err.println(e.getMessage());
			}
			return output;

	}

	public String readAppointments()
	{
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error while connecting to the database for reading the appointmentdetails.";

			}
			output = "<table border=\"1\"><tr>"
					+ "<th>Patient ID</th>"
					+ "<th>Doctor ID</th>"
					+ "<th>Appointment Date</th>"
					+ "<th>Appointment Time</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>"; 

			String query = "select * from appointment";

			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String appointmentID = Integer.toString(rs.getInt("appointmentID"));
				String patientID = rs.getString("patientID");
				String doctorID = rs.getString("doctorID");
				String appointmentDate = rs.getString("appointmentDate");
				String appointmentTime = rs.getString("appointmentTime");

				output += "<td>" + patientID + "</td>";
				output += "<td>" + doctorID + "</td>";   
				output += "<td>" + appointmentDate + "</td>"; 
				output += "<td>" + appointmentTime + "</td>";
				
			
				output += "<td><input name=\"btnUpdate\" "  
						   + " type=\"button\" value=\"Update\"></td>"   
						   + "<td><form method=\"post\" action=\"hospitals.jsp\">"
					       + "<input name=\"btnRemove\" "
						   + " type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
					       + "<input name=\"appointmentID\" type=\"hidden\" "   
						   + " value=\"" + appointmentID + "\">" 
					       + "</form></td></tr>";    

			}

			con.close();

			output += "</table>";

			
		} catch (Exception e) {

			output = "An error occurred while reading the appointment details. ";
			System.err.println(e.getMessage());

		}

		return output;
	}

	public String updateAppointments(String ID, String pID, String dID, String appDate,String appTime)
	{
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occured while updating the appointment details.";
			}

			// create a prepared statement
			
			String query = "UPDATE appointment SET patientID=?,doctorID=?,appointmentDate=?,appointmentTime=?  WHERE appointmentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			// binding values to query
			preparedStmt.setString(1, pID);
			preparedStmt.setString(2, dID);
			preparedStmt.setString(3, appDate);
			preparedStmt.setString(4, appTime);
			preparedStmt.setInt(5, Integer.parseInt(ID));

			// Executeing the statement
			 preparedStmt.execute(); 
			 con.close();

			output = "Appointment details update successfully.";
		
		}

		catch (Exception e) {

			output = "An error occurred while updating the appointment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointments(String appointmentID) {
		
		String output = "";

		try {

			Connection con = connect();

			if (con == null) {

				return "Error occured while deleting the appointment details.";
			}

			// create a prepared statement
			String query = "delete from appointment where appointmentID=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(appointmentID));

			// executing the statements
			preparedStmt.execute();

			con.close();

			output = "Appointment details deleted successfully.";
			
		} catch (Exception e) {

			output = " An error occurred while deleting the Appointment details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
