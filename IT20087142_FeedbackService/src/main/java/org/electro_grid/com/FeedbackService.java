/*package org.electro_grid.com;
import org.electro_grid.model.Feedback;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Feedback")
public class FeedbackService {
	
	Feedback feedbackObj = new Feedback();

	//Insert Function 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String insertFeedback(@FormParam("feedbackType") String feedbackType,
					@FormParam("feedbackDate") String feedbackDate,
					@FormParam("feedbackDesc") String feedbackDesc,
					@FormParam("feedbackStatus") String feedbackStatus,
					@FormParam("customerId") String customerId)
	{
		String output = feedbackObj.insertFeedback(feedbackType, feedbackDate, feedbackDesc, feedbackStatus, customerId);
		return output;
	}

	//Read Function
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readFeedback()
		{
			return feedbackObj.readFeedback();
		}
		
	//Update Function
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateFeedback(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String feedbackId = itemObject.get("feedbackId").getAsString();
		String feedbackType = itemObject.get("feedbackType").getAsString();
		String feedbackDate = itemObject.get("feedbackDate").getAsString();
		String feedbackDesc = itemObject.get("feedbackDesc").getAsString();
		String feedbackStatus = itemObject.get("feedbackStatus").getAsString();
		String customerId = itemObject.get("customerId").getAsString();
		String output = feedbackObj.updateFeedback(feedbackId, feedbackType, feedbackDate, feedbackDesc, feedbackStatus, customerId);
		
		return output;
	}
	
	//Delete Function
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteFeedback(String feedbackData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());
		
		//Read the value from the element <feedbackId>
		String feedbackId = doc.select("feedbackId").text();
		String output = feedbackObj.deleteFeedback(feedbackId);
		
		return output;
	}
}*/