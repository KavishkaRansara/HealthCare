package com;

import model.Hospital;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Hospitals")
public class HospitalService {
	
	Hospital hospitalObj = new Hospital(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospitals() {
		return hospitalObj.readHospitals();
	}
	
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitals(@FormParam("hospitalName") String hospitalName,
							@FormParam("hospitalProvince") String hospitalProvince,
							@FormParam("hospitalDistrict") String hospitalDistrict,
							@FormParam("hospitalEmail") String hospitalEmail,
							@FormParam("hospitalPhone") String hospitalPhone,
							@FormParam("hospitalServices") String hospitalServices)
	{
		String output = hospitalObj.insertHospitals(hospitalName, hospitalProvince, hospitalDistrict, hospitalEmail,hospitalPhone,hospitalServices);  
		return output; 
	}
	

}
