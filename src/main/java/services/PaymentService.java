package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/PaymentService")
public class PaymentService {

    public String getPaymentDetails(@Context ServletContext servletContext, String userName, String itemID, String expShip,HttpSession session) throws Exception {
    	try { 
    		String fName = "";
			String lName = "";
			String street = "";
			String number = "";
			String prov = "";
			String coun = "";
			String postal = "";
			String itemPrice = "";
			String itemShip = "";
			String itemExp = "";
			String itemBidder = "";
			double tCost;
			String tCostS;
			
			try { 
				
				String projectLocation = servletContext.getRealPath("/database");
			    String url ="jdbc:sqlite:"+projectLocation+"/SignUpInfo.db";
			
				Connection conn = null;
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
				String query = "SELECT * FROM user WHERE userName = ?";
				
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, userName);
				ResultSet resultSet = preparedStatement.executeQuery();
				fName = resultSet.getString("firstName");
				lName = resultSet.getString("lastName");
				street = resultSet.getString("address");
				number = resultSet.getString("streetNumber");
				prov = resultSet.getString("city");
				coun = resultSet.getString("country");
				postal = resultSet.getString("postalCode");
				conn.close();
			} catch(Exception exp) {
				System.out.println(exp);
			}
			
			System.out.println(itemID);
			
			try { 
				String projectLocation = servletContext.getRealPath("/database");
			    String url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
				
				Connection conn = null;
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
				String query = "SELECT * FROM Inventory WHERE itemID = ?";
				
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, itemID);
				ResultSet resultSet = preparedStatement.executeQuery();
				itemPrice = resultSet.getString("HighestBid");
				itemShip = resultSet.getString("ShippingPrice");
				itemExp = resultSet.getString("ExpeditedCost");
				itemBidder = resultSet.getString("Bidder");
				conn.close();
			} catch(Exception exp) {
				System.out.println(exp);
			}
			
			if (expShip != null) {
				tCost = Double.parseDouble(itemPrice) + Double.parseDouble(itemShip) + Double.parseDouble(itemExp);
				tCostS = Double.toString(tCost);
			} else {
				tCost = Double.parseDouble(itemPrice) + Double.parseDouble(itemShip);
				tCostS = Double.toString(tCost);
			}
			
			session.setAttribute("fName", fName);
			session.setAttribute("lName", lName);
			session.setAttribute("street", street);
			session.setAttribute("number", number);
			session.setAttribute("prov", prov);
			session.setAttribute("coun", coun);
			session.setAttribute("postal", postal);
			session.setAttribute("tCost", tCostS);
			session.setAttribute("itemID", itemID);
			session.setAttribute("userName", userName);
			
			return itemBidder;
			
			
		}catch(Exception exp){
			System.out.println(exp);
		}
		return null;
    }
}