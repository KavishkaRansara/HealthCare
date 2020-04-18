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
	
	

}
