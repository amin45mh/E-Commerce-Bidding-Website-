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

import beans.User;

@Path("/SignUpService")
public class SignUpService {
	
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(@Context ServletContext servletContext, @FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("address") String address,
			@FormParam("streetNumber") String streetNumber,
			@FormParam("postalCode") String postalCode,
			@FormParam("city") String city,
			@FormParam("country") String country,
			@FormParam("userName") String userName,
			@FormParam("passWord") String passWord) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			String projectLocation = servletContext.getRealPath("/database");
			 //String url ="jdbc:sqlite: C:\\Users\\nobai\\Downloads\\EECS4413\\estella\\Auctooze\\Auctooze\\src\\main\\webapp\\database\\SignUpInfo.db";
		   String url ="jdbc:sqlite:"+projectLocation+"/SignUpInfo.db";
 //System.out.println(projectLocation + " and url "+ url);
			if((firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty() 
					|| address == null || address.trim().isEmpty() || streetNumber == null || streetNumber.trim().isEmpty()
					|| postalCode == null || postalCode.trim().isEmpty() || city == null || city.trim().isEmpty()
					|| country == null || country.trim().isEmpty() || userName == null || userName.trim().isEmpty())) {
				String message = "Sign Up Failed Due to Missing Fields - Please Try Again! ";
				return Response.status(401).entity(message).build();
			}
			else if (getUser( servletContext, userName) != null) {
				String message = "A user with that username already exists - Please Try Again! ";
				return Response.status(401).entity(message).build();
			}
			Class.forName("org.sqlite.JDBC");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(url);
			System.out.println("Creating statement...");
			String sql = "INSERT INTO user (firstName, lastName, address, streetNumber, postalCode, city, country, userName, passWord) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("first name " +firstName);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, address);
			stmt.setString(4, streetNumber);
			stmt.setString(5, postalCode);
			stmt.setString(6, city);
			stmt.setString(7, country);
			stmt.setString(8, userName);
			stmt.setString(9, passWord);
			stmt.executeUpdate();
            return Response.status(Response.Status.OK).build();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@Context ServletContext servletContext, @FormParam("userName") String userName) {
		User user = new User();
		Connection conn = null;
		try { 
			String projectLocation = servletContext.getRealPath("/database");
		    String url ="jdbc:sqlite:"+projectLocation+"/SignUpInfo.db";
		
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
			String query = "SELECT * FROM user WHERE userName = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				// Populate User object with data from database
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setAddress(resultSet.getString("address"));
				user.setUserName(userName);
				user.setPassWord(resultSet.getString("password"));
				user.setPostalCode(resultSet.getString("postalCode"));
				user.setCountry(resultSet.getString("country"));
				user.setCity(resultSet.getString("city"));
				user.setStreetNumber(resultSet.getInt("streetNumber"));

				conn.close();
				return user;
			}
		} catch (Exception exp) {
			System.out.println(exp);
		}
		return null;
	}
}