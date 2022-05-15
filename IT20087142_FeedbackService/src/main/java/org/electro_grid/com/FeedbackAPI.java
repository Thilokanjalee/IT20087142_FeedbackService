package org.electro_grid.com;

import org.electro_grid.com.*;
import org.electro_grid.model.Feedback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/FeedbackAPI")
public class FeedbackAPI extends HttpServlet{
	
	private static final long serialVersionUID =1L;
	Feedback feedbackObj = new Feedback();
	
	public FeedbackAPI()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		System.out.println("requets recieved");
		String output = feedbackObj.insertFeedback(request.getParameter("feedbackType"),
			request.getParameter("feedbackDate"),
			request.getParameter("feedbackDesc"),
			request.getParameter("feedbackStatus"));
			 
			response.getWriter().write(output);
	}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = feedbackObj.updateFeedback(Integer.parseInt(paras.get("hidFeedbackIDSave").toString()),
			paras.get("feedbackType").toString(),paras.get("feedbackDate").toString(),paras.get("feedbackDesc").toString(),paras.get("feedbackStatus").toString());
			 
			response.getWriter().write(output);
	} 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = feedbackObj.deleteFeedback(paras.get("feedbackId").toString().trim());
			 System.out.println(paras.get("feedbackId").toString());
			 response.getWriter().write(output);
	}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
	
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}