package com;

import model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Appointments") 
public class AppointmentService {
	
	Appointment appointmentObj = new Appointment(); 
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointments()
	{
		return appointmentObj.readAppointments();
	}
	

	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointments(@FormParam("patientID") String patientID,
							@FormParam("doctorID") String doctorID,
							@FormParam("appointmentDate") String appointmentDate,
							@FormParam("appointmentTime") String appointmentTime)
							
	{
		String output = appointmentObj.insertAppointments(patientID, doctorID, appointmentDate, appointmentTime);  
		return output; 
	}
	
	@PUT 
	@Path("/") @Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointments(String appointmentData) {  
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject(); 
		 
		String appointmentID = appointmentObject.get("appointmentID").getAsString();  
		String patientID = appointmentObject.get("patientID").getAsString();  
		String doctorID = appointmentObject.get("doctorID").getAsString();
		String appointmentDate = appointmentObject.get("appointmentDate").getAsString();
		String appointmentTime = appointmentObject.get("appointmentTime").getAsString();  
		
		 
		String output = appointmentObj.updateAppointments(appointmentID, patientID, doctorID, appointmentDate, appointmentTime); 
		 
		 return output; 
		 
	}
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAppointments(String appointmentData) {  
		
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());  
		String appointmentID = doc.select("appointmentID").text(); 
		 
		 String output = appointmentObj.deleteAppointments(appointmentID);
		 
		 return output; }
}
