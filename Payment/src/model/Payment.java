package model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.*;

import com.PaymentService;

public class Payment {
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items", "root", "sajeesan123");
//For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	
	
	
//insert sucess

public String insertPayment(String Payno, String AppNo, String amount, String PatientName) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
	// create a prepared statement
				String query = " insert into payment (`PaymentID`,`PaymentNo`,`AppointmentID`,`Amount`,`PatientName`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, Payno);
				preparedStmt.setString(3, AppNo);
				preparedStmt.setDouble(4, Double.parseDouble(amount));
				preparedStmt.setString(5, PatientName);

	// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the Payment.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	
	
	
	
//Update
			
		
public String updatePayment(String ID, String Payno, String AppNo, String amount, String PatientName)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for updating."; }
		
		
		
		// create a prepared statement
		String query = "UPDATE payment SET PaymentNo=?,AppointmentID=?,Amount=?,PatientName=? WHERE PaymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		
		
		// binding values
		preparedStmt.setString(1, Payno);
		preparedStmt.setString(2, AppNo);
		preparedStmt.setDouble(3, Double.parseDouble(amount));
		preparedStmt.setString(4, PatientName);
		preparedStmt.setInt(5, Integer.parseInt(ID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
		}
		catch (Exception e)
		{
		output = "Error while updating the payment.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		
		
		
			
//readdd
	
public String readPayment()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for reading."; }



// Prepare the html table to be displayed


output = "<table border=\"1\"><tr><th>Payment NO</th><th>Appointment ID</th><th>Amount</th><th>Patient Name</th><th>Update</th><th>Remove</th></tr>";

String query = "select * from payment";

java.sql.Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);



// iterate through the rows in the result set
while (rs.next())
{
String PaymentID = Integer.toString(rs.getInt("PaymentID"));
String Payno = rs.getString("PaymentNo");
String AppNo = rs.getString("AppointmentID");
String amount = Double.toString(rs.getDouble("Amount"));
String PatientName = rs.getString("PatientName");



// Add into the html table
output += "<tr><td>" + Payno + "</td>";
output += "<td>" + AppNo + "</td>";
output += "<td>" + amount + "</td>";
output += "<td>" + PatientName + "</td>";



// buttons
output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">" + "<input name=\"itemID\" type=\"hidden\" value=\"" + PaymentID + "\">" + "</form></td></tr>";
}
con.close();



// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the Payments.";
System.err.println(e.getMessage());
}
return output;
}



	
//delelte
	
public String deletePayment(String PaymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from payment where PaymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}