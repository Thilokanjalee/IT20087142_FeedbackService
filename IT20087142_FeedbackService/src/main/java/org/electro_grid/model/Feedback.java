package org.electro_grid.model;

import java.sql.*;

public class Feedback {

	//A common method to connect to the Database
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electro_grid", "root", "root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
	//Insert method
	public String insertFeedback(String feedbackType, String feedbackDate, String feedbackDesc, String feedbackStatus)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into feedback (`feedbackId`,`feedbackType`,`feedbackDate`,`feedbackDesc`,`feedbackStatus`) values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, feedbackType);
			preparedStmt.setString(3, feedbackDate);
			preparedStmt.setString(4, feedbackDesc);
			preparedStmt.setString(5, feedbackStatus);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Consumption record is inserted successfully";
			
			String newFeedaback = readFeedback();
			output = "{\"status\":\"success\", \"data\": \"" + newFeedaback + "\"}";
		}
		catch (Exception e)
		{
			//output = "Error while inserting the feedback record.";
			//System.err.println(e.getMessage());
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Feedback Service.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//Read method
	public String readFeedback()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Feedback Type</th><th>Feedback Date</th>" +
			"<th>Feedback Description</th>" +
			"<th>Feedback Status</th>"+ 
			"<th>Update</th><th>Remove</th></tr>";
			
			//Query for reading the data from the database
			String query = "select * from Feedback";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//Iterate through the rows in the result set
			while (rs.next())
			{
				String feedbackId = Integer.toString(rs.getInt("feedbackId"));
				String feedbackType = rs.getString("feedbackType");
				String feedbackDate = rs.getString("feedbackDate");
				String feedbackDesc = rs.getString("feedbackDesc");
				String feedbackStatus = rs.getString("feedbackStatus");
				
				// Add into the html table
				output += "<tr><td>" + feedbackType + "</td>";
				output += "<td>" + feedbackDate + "</td>";
				output += "<td>" + feedbackDesc + "</td>";
				output += "<td>" + feedbackStatus + "</td>";
				
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + feedbackId + "'>" + "</td></tr>"; 
				
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the consumption records.";
			System.err.println(e.getMessage());
		}
		
		return output;
	
	}
	
	//update method
		public String updateFeedback(int feedbackId, String feedbackType, String feedbackDate, String feedbackDesc, String feedbackStatus)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				//checking the database connection before inserting
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				
				// create a prepared statement
				String query = "UPDATE Feedback SET feedbackType=?,feedbackDate=?,feedbackDesc=?,feedbackStatus=? WHERE feedbackId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, feedbackType);
				preparedStmt.setString(2, feedbackDate);
				preparedStmt.setInt(3, Integer.parseInt(feedbackDesc));
				preparedStmt.setBoolean(4, Boolean.parseBoolean(feedbackStatus));
				preparedStmt.setInt(5, feedbackId);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//output = "Feedback data is updated successfully.";
				String newFeedaback = readFeedback();
				output = "{\"status\":\"success\", \"data\": \"" + newFeedaback + "\"}";
				
			}
			catch (Exception e)
			{
				//output = "Error while updating the feedback record.";
				//System.err.println(e.getMessage());
				
				output = "{\"status\":\"error\", \"data\":\"Error while updating the Feedback Service.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
	//Delete method
	public String deleteFeedback(String feedbackId)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			//checking the database connection before inserting
			if (con == null)
			{
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from Feedback where feedbackId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			//output = "Feedback record is deleted successfully";
			String newFeedaback = readFeedback();
			output = "{\"status\":\"success\", \"data\": \"" + newFeedaback + "\"}";
			
		}
		catch (Exception e)
		{
			//output = "Error while deleting the feedback record.";
			//System.err.println(e.getMessage());
			
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Feedback Service.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
		
		}
}