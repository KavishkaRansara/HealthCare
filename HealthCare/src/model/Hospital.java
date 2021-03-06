package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hospital {

	// get the connection with database
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// provide valid details for get the connection
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "Sliitstudent123");

			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	// Inserting data into hospital
	public String insertHospitals(String hName, String hProvince, String hDistrict, String hEmail, String hPhone,
			String hServices) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// Preapared statement creation
			String query = " insert into hospital (hospitalID,hospitalName,hospitalProvince,hospitalDistrict,hospitalEmail,hospitalPhone,hospitalServices)"
					+ " values (?,?,?,?,?,?,?)";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values into prepared statement
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

		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// read Hospitals which are in the database
	public String readHospitals() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare html html to display data
			output = "<table border=\"1\"><tr>" + "<th>Hospital ID</th>" + "<th>Hospital Name</th>"
					+ "<th>Hospital Province</th>" + "<th>Hospital District</th>" + "<th>Email</th>" + "<th>Phone</th>"
					+ "<th>Services</th>" + "<th>Update</th>" + "<th>Remove</th></tr>";

			String query = "select * from hospital";
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set.
			while (rs.next()) {
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String hospitalName = rs.getString("hospitalName");
				String hospitalProvince = rs.getString("hospitalProvince");
				String hospitalDistrict = rs.getString("hospitalDistrict");
				String hospitalEmail = rs.getString("hospitalEmail");
				String hospitalPhone = rs.getString("hospitalPhone");
				String hospitalServices = rs.getString("hospitalServices");

				// Add into html table
				output += "<tr><td>" + hospitalID + "</td>";
				output += "<td>" + hospitalName + "</td>";
				output += "<td>" + hospitalProvince + "</td>";
				output += "<td>" + hospitalDistrict + "</td>";
				output += "<td>" + hospitalEmail + "</td>";
				output += "<td>" + hospitalPhone + "</td>";
				output += "<td>" + hospitalServices + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"hospitals.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"hospitalID\" type=\"hidden\" " + " value=\"" + hospitalID + "\">"
						+ "</form></td></tr>";

			}
			con.close();

			// close the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the hospitals.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// Updating hospital details
	public String updateHospitals(String ID, String hName, String hProvince, String hDistrict, String hEmail,
			String hPhone, String hServices) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create prepared statement
			String query = "UPDATE hospital SET hospitalName=?,hospitalProvince=?,hospitalDistrict=?,hospitalEmail=?,hospitalPhone=?,hospitalServices=?  WHERE hospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, hName);
			preparedStmt.setString(2, hProvince);
			preparedStmt.setString(3, hDistrict);
			preparedStmt.setString(4, hEmail);
			preparedStmt.setString(5, hPhone);
			preparedStmt.setString(6, hServices);
			preparedStmt.setInt(7, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospitals(String hospitalID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create prepared statement
			String query = "delete from hospital where hospitalID=?";
			java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hospitalID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
