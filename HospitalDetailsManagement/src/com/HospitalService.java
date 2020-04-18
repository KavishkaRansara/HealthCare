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
	
	@PUT 
	@Path("/") @Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitals(String hospitalData) {  
		JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject(); 
		 
		String hospitalID = hospitalObject.get("hospitalID").getAsString();  
		String hospitalName = hospitalObject.get("hospitalName").getAsString();  
		String hospitalProvince = hospitalObject.get("hospitalProvince").getAsString();
		String hospitalDistrict = hospitalObject.get("hospitalDistrict").getAsString();
		String hospitalEmail = hospitalObject.get("hospitalEmail").getAsString();  
		String hospitalPhone = hospitalObject.get("hospitalPhone").getAsString();
		String hospitalServices = hospitalObject.get("hospitalServices").getAsString();
		 
		String output = hospitalObj.updateHospitals(hospitalID, hospitalName, hospitalProvince, hospitalDistrict, hospitalEmail, hospitalPhone, hospitalServices); 
		 
		 return output; 
		 
	}
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteHospitals(String hospitalData) {  
		
		Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());  
		String hospitalID = doc.select("hospitalID").text(); 
		 
		 String output = hospitalObj.deleteHospitals(hospitalID);
		 
		 return output; } 
	
	

}
