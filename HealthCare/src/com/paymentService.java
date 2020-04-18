package com;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.payment;

@Path("/payment")
public class paymentService {
    payment pay = new payment();
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
	public String readRemainpayments()
	{
    	return pay.readRemainpayment();
		  
	}
    

	//POST METHOD
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("patientID") String patientID,
							@FormParam("doctorID") String doctorID,
							@FormParam("amount") String amount)
							
	{
		String output = pay.appointmentAmount(patientID, doctorID, amount);  
		return output; 
	}
}
