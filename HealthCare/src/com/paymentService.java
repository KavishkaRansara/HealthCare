package com;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.text.Document;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.payment;

@Path("/payment")
public class paymentService {
	payment pay = new payment();

	// implementing the reading service for read the success payment
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readRemainpayments() {
		return pay.readpayment();
	}

	//implement the update micro service 
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })

	public String updateAppointments(String paymentData) {
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();

		String payID = paymentObject.get("payID").getAsString();
		String patientID = paymentObject.get("patientID").getAsString();
		String doctorID = paymentObject.get("doctorID").getAsString();
		String date = paymentObject.get("date").getAsString();
		String amount = paymentObject.get("amount").getAsString();
		String cardnumber = paymentObject.get("cardnumber").getAsString();
		String postalnumber = paymentObject.get("postalnumber").getAsString();

		String output = pay.updatePaymentdetails(payID, patientID, doctorID, date, amount, cardnumber, postalnumber);

		return output + "" + pay.readpayment() + "";

	}

	/*
	 * imlemeting the paying method for appointment paymengt
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
	public String insertapointmentPayment(@FormParam("payID") String payID, @FormParam("patientID") String patientID,
			@FormParam("doctorID") String doctorID, @FormParam("date") String date, @FormParam("amount") String amount,
			@FormParam("cardnumber") String cardnumber, @FormParam("postalnumber") String postalnumber)

	{
		String output = pay.payAppointment(payID, patientID, doctorID, date, amount, cardnumber, postalnumber);
		return output + "" + pay.readpayment();
	}

	//implement the delete micro service
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({ MediaType.TEXT_PLAIN, MediaType.TEXT_HTML })
	public String deleteItem(String paymentData) {
		// Convert the input string to an XML document
		org.jsoup.nodes.Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element <payID>
		String payID = doc.select("payID").text();

		String output = pay.paymentDelete(payID);
		return output + "" + pay.readpayment();
	}

}
