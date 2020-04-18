package com;

import model.Doctor;

//for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Doctors")

public class DoctorService {
	
	Doctor docObj = new Doctor();
	
	//for viewing doctors
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor() {
		
		return docObj.readDoctor();
		
	}
	
	
	//for inserting doctors	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(   @FormParam("docName") String doctorName,
								  @FormParam("docSpec") String specialization,
								  @FormParam("docHosp") String hospital,
								  @FormParam("docContact") String contact,
								  @FormParam("docEmail") String email,
								  @FormParam("docStatus") String status)
	{
			String output = docObj.insertDoctor(doctorName, specialization, hospital, contact, email, status);
			return output;
	}
	
	//insert and view is tested using POSTMAN
	
	
	//for updating doctors
	   @PUT
	   @Path("/")
	   @Produces(MediaType.TEXT_PLAIN)
	   public String updateDoctor(String docData) {
		
		 //Convert the input string to a JSON object
		  JsonObject docjObj = new JsonParser().parse(docData).getAsJsonObject();
		  
		//Read the values from the JSON object
		  String doctorID= docjObj.get("docID").getAsString();
		  String doctorName= docjObj.get("docName").getAsString();
		  String specialization= docjObj.get("docSpec").getAsString();
		  String hospital= docjObj.get("docHosp").getAsString();
		  String contact = docjObj.get("docContact").getAsString();
		  String email= docjObj.get("docEmail").getAsString();
		  String status= docjObj.get("docStatus").getAsString();
		  
		  String output= docObj.updateDoctor(doctorID, doctorName,specialization,hospital,contact,email,status);
		  return output;
		  
		  
		 
	  }

}
