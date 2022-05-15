$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidFeedbackIDSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "FeedbackAPI",
 		type : type,
 		data : $("#formFeedback").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onItemSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divFeedbackGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidFeedbackIDSave").val("");
	 $("#formFeedback")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;
		$("#hidFeedbackIDSave").val(id.substring(0, id.length-1));
 		$("#feedbackType").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#feedbackDate").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#feedbackDesc").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#feedbackStatus").val($(this).closest("tr").find('td:eq(3)').text());
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "FeedbackAPI",
 		type : "DELETE",
	    data : "feedbackId=" + $(this).data("feedbackId"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onItemDeleteComplete(response.responseText, status);
 		}
	 });
});







// after completing delete request
function onItemDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divItemsGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}


function validateItemForm()
{
	// CODE
	if ($("#feedbackType").val().trim() == "")
 	{
		 return "Insert Feedback Type.";
    }
    
    
	// NAME
	if ($("#feedbackDate").val().trim() == "")
    {
		 return "Insert Feedback Date.";
 	} 
 	

	// PRICE-------------------------------
	if ($("#feedbackDesc").val().trim() == "")
    {
 		return "Insert Feedback Description.";
 	}
 	
 	
	
	// DESCRIPTION------------------------
	if ($("#feedbackStatus").val().trim() == "")
   {
		 return "Insert Feedback Status.";
   }
   return true;
}
