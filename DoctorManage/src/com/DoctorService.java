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
	

}
