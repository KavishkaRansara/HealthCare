package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/user")
public class UserService {
	User registerObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return registerObj.readPatient();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("pName") String pName,
	 @FormParam("pAddress") String pAddress,
	 @FormParam("pEmail") String pEmail,
	 @FormParam("pDate") String pDate,
	 @FormParam("nic") String nic,
	 @FormParam("pno") String pno)
	{
	 String output = registerObj.insertPatient(pName, pAddress, pEmail, pDate,  nic, pno);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData)
	{
	//Convert the input string to a JSON object
	 JsonObject regObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	 String pID = regObject.get("pID").getAsString();
	 String pName = regObject.get("pName").getAsString();
	 String pAddress = regObject.get("pAddress").getAsString();
	 String pEmail = regObject.get("pEmail").getAsString();
	 String pDate = regObject.get("pDate").getAsString();
	 String nic = regObject.get("nic").getAsString();
	 String pno = regObject.get("pno").getAsString();
	 String output = registerObj.updatePatient(pID, pName, pAddress, pEmail, pDate, nic, pno);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String pID = doc.select("pID").text();
	 String output = registerObj.deletePatient(pID);
	return output;
	}
}
