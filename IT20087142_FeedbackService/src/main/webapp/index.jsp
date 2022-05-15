<%@ page import="org.electro_grid.model.Feedback"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Consumption Record Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/Feedback.js"></script>
</head>
<body>
<div class = "container">
<div class = "row">
<div class = "col">
	<h1>Feedback Management</h1>
	
	<form id="formFeedback" name="formFeedback" method="post" action="Index.jsp">
		 
		 Feedback Type :
		 <input id="feedbackType" name="feedbackType" type="date"
 						class="form-control form-control-sm">
		 
		 <br> Feedback Date :
		 <input id="feedbackDate" name="feedbackDate" type="text"
 						class="form-control form-control-sm">
 						
		<br> Feedback Desc:
		<input id="feedbackDesc" name="feedbackDesc" type="text"
 						class="form-control form-control-sm">
 						
 		<br> Feedback Status:
		<input id="feedbackStatus" name="feedbackStatus" type="text"
 						class="form-control form-control-sm">
 						
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidFeedbackIDSave" name="hidFeedbackIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success">
 	
 		
 	</div>
 	<div id = "alertError" class="alert-danger"></div>
	
	<br>
	<br>
	
	<div id ="divFeedbackGrid">
	<%
	 Feedback feedbackObj = new Feedback(); 
	 out.print(feedbackObj.readFeedback()); 
	%>
	</div>

</body>
</html> 