package com;

import javax.ws.rs.GET;
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
    public String readPayment() {
    	return "pay";
    } 
}
