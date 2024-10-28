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

import Classes.CardCreator;
import Classes.ConcreteCreatorMasterCard;
import Classes.ConcreteCreatorVisa;
import Classes.CreditCard;

@Path("/ReceiptService")
public class ReceiptService {

    public void generateReceipt(@Context ServletContext servletContext, String userName, String itemID, String tCost, String cardNum, String cardHold, String date, String secCode, HttpSession session) throws Exception {
    	try { 
			String fName = "";
			String lName = "";
			String street = "";
			String number = "";
			String prov = "";
			String coun = "";
			String postal = "";
			String itemShip = "";
			String itemExp = "";
			String itemName = "";
			String sTime = "";
			
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
			
			CardCreator creator = null;
			
			// Verify
			System.out.println("char:" + cardNum.charAt(0));
			if(cardNum.charAt(0) == '4') {
				creator = new ConcreteCreatorVisa();
			}
			else if (cardNum.charAt(0) == '5') {
				creator = new ConcreteCreatorMasterCard();
			}
			
			if (creator!= null) {
				CreditCard creditCard = creator.factoryMethod(cardNum, cardHold, date, secCode);
				session.setAttribute("cardType", creditCard.getType());
			}
			else {
				session.setAttribute("cardType", "Unknown Type");
			}
			
			try { 
				String projectLocation = servletContext.getRealPath("/database");
			    String url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
				
				Connection conn = null;
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
				String query = "SELECT * FROM Inventory WHERE ItemID = ?";
				
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				preparedStatement.setString(1, itemID);
				ResultSet resultSet = preparedStatement.executeQuery();
				itemShip = resultSet.getString("ShippingPrice");
				itemExp = resultSet.getString("ExpeditedCost");
				itemName = resultSet.getString("Name");
				sTime = resultSet.getString("ShippingTime");
				
				PreparedStatement stmt = null;
				String sql = "DELETE FROM Inventory WHERE ItemID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, itemID);
				stmt.executeUpdate();
				stmt.close();
				
				conn.close();	
			} catch(Exception exp) {
				System.out.println(exp);
			}
			
			session.setAttribute("fName", fName);
			session.setAttribute("lName", lName);
			session.setAttribute("street", street);
			session.setAttribute("number", number);
			session.setAttribute("prov", prov);
			session.setAttribute("coun", coun);
			session.setAttribute("postal", postal);
			session.setAttribute("tCost", tCost);
			session.setAttribute("itemID", itemID);
			session.setAttribute("sTime", sTime);
			session.setAttribute("itemName", itemName);
			
		}catch(Exception exp){
			System.out.println(exp);
		}
    }
}