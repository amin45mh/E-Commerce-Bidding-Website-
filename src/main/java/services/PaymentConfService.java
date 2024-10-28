package services;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 

@Path("/PaymentConfService")
public class PaymentConfService {
	
		@GET
		@Produces(MediaType.TEXT_HTML)
		public void paymentConf(@Context ServletContext servletContext, @QueryParam("userName") String userName,
				@Context HttpServletRequest request,
				@Context HttpServletResponse response) throws ServletException, IOException {
			try { 
				response.setContentType("text/html");
				HttpSession session = request.getSession(false);
				String firstName = "";
				String lastName = "";

				String itemID = (String)session.getAttribute("id");
				String itemName = (String)session.getAttribute("name");
				String itemDesc = (String)session.getAttribute("desc");
				String itemShip = (String)session.getAttribute("ship");
				String itemAuc = "";
				String itemPrice = "";
				String itemWinner = "";
				String itemExp = "";
				
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
					itemPrice = resultSet.getString("HighestBid");
					itemWinner = resultSet.getString("Bidder");
					itemExp = resultSet.getString("ExpeditedCost");
					itemAuc = resultSet.getString("AuctionType");
					conn.close();
					if(itemAuc.equals("Forward")){
					if(!itemWinner.equals(userName)) {
						System.out.println("in here itemWinner");
						request.getRequestDispatcher("/PaymentServlet").forward(request, response);
					}}

				} catch(Exception exp) {
					System.out.println(exp);
				}
				
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
					firstName = resultSet.getString("firstName");
					lastName = resultSet.getString("lastName");
					conn.close();
				} catch(Exception exp) {
					System.out.println(exp);
				}

				if (itemWinner == null) {
					try {
						String projectLocation = servletContext.getRealPath("/database");
					    String url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
					
						PreparedStatement stmt = null;
						Connection conn = null;
						Class.forName("org.sqlite.JDBC");
						conn = DriverManager.getConnection(url);
						String sql = "UPDATE Inventory SET Bidder = ? WHERE Name = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, userName);
						stmt.setString(2, itemName);
						stmt.executeUpdate();
						stmt.close();
						conn.close();
						itemWinner = userName;
					} catch(Exception exp) {
						System.out.println(exp);
					}
				}
				
				session.setAttribute("name", itemName);
				session.setAttribute("desc", itemDesc);
				session.setAttribute("ship", itemShip);
				session.setAttribute("price", itemPrice);
				session.setAttribute("exp", itemExp);
				session.setAttribute("bidder", itemWinner);
				session.setAttribute("itemID", itemID);
				session.setAttribute("userName", userName);
				request.getRequestDispatcher("/PaymentConfPage.jsp").forward(request, response);
			}catch(Exception exp){
				System.out.println(exp);
			}
		}
	}


