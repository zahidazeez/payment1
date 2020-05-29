package com;
import model.Payment;

import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit.Parser;
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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Payments")
public class PaymentService
{
Payment pay = new Payment();

@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)

public String readPayment() {
	
	return pay.readPayment();

}




@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("PaymentNo") String Payno,
@FormParam("AppointNo") String AppNo,
@FormParam("Amount") String amount,
@FormParam("PatientName") String PatientName)
{
String output = pay.insertPayment(Payno, AppNo, amount, PatientName);
return output;
}



@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatePayment(String PaymentData){

	JsonObject payments = new JsonParser().parse(PaymentData).getAsJsonObject();

	

String PaymentID = payments.get("PaymentID").getAsString();
String PayNo = payments.get("PaymentNo").getAsString();
String AppointmentNo = payments.get("AppointmentNo").getAsString();
String amount = payments.get("Amount").getAsString();
String PatientName = payments.get("PatientName").getAsString();

String output = pay.updatePayment(PaymentID, PayNo, AppointmentNo, amount, PatientName);
return output;


}






@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletePayment(String PaymentData)
{
//Convert the input string to an XML document

	Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>

	String PaymentID = doc.select("PaymentID").text();

	String output = pay.deletePayment(PaymentID);

}




}