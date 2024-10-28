package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/SignInService")
public class SignInService {
	
	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signin(@Context ServletContext servletContext, @FormParam("userName") String userName,
			@FormParam("passWord") String passWord) throws SQLException {

		Connection conn = null;

		try {
			String projectLocation = servletContext.getRealPath("/database");
		    String url ="jdbc:sqlite:"+projectLocation+"/SignUpInfo.db";
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			System.out.println("location : "+url);
			System.out.println("Connecting to database...");
			System.out.println("Creating statement...");
			String query = "SELECT * FROM user WHERE userName = ?"; //getting the username from database
			
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			String message = null;
			
			if(resultSet.next() && !(userName == null || userName.trim().isEmpty())){ //checking to see username exists
				
				 String passwordFromDB = resultSet.getString("passWord");
				    if(passwordFromDB.equals(passWord)){
			        // User is authenticated
				    	message = "Successful Login!";
				    	System.out.println("User Authenticated");
				    	return Response.status(200).entity(message).build();
			    } else {
			        // Authentication failed, redirect to page and display error message
			    	System.out.println("Authentication failed");
			    	 message = "Authentication Failed - Please Try Again! ";
			    	 return Response.status(401).entity(message).build();
			    }}
			else { //if username doesn't exist 
				System.out.println("Username doesn't match");
				message = "Username doesn't match - Please Try Again! ";
				return Response.status(401).entity(message).build();
	    	  }
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}