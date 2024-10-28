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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Classes.BidType;
import Classes.DutchBid;
import Classes.ForwardBid;
import Classes.ConfigManager;

@Path("/BidService")
public class BidService {
	 
	    @GET
	    @Produces(MediaType.TEXT_HTML)
	    public Response bid(@Context ServletContext servletContext, @Context HttpServletRequest request ,@QueryParam("checked") String checked, @QueryParam("userName") String userName) {
	        String name = "";
	        String desc = "";
	        String ship = "";
	        String price = "";
	        String highBid = "";
	        String aucType = "";
	        String time = "";
	        String bidder = "";
	        String id = "";

	        HttpSession session = request.getSession();
	        String target = "";
	        String projectLocation = servletContext.getRealPath("/database");
	        String url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
	     
	        ConfigManager.setUrl(url);

	        try { 
	            Connection conn = null;
	            Class.forName("org.sqlite.JDBC");
	            conn = DriverManager.getConnection(url);
	            String query = "SELECT * FROM Inventory WHERE Name = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(query);
	            preparedStatement.setString(1, checked);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            id = resultSet.getString("itemId");
	            aucType = resultSet.getString("AuctionType");
	            name = resultSet.getString("Name");
	            desc = resultSet.getString("Description");
	            price = resultSet.getString("Price");
	            highBid = resultSet.getString("HighestBid");
	            ship = resultSet.getString("ShippingPrice");
	            bidder = resultSet.getString("Bidder");  
	            System.out.println("high bid & bidder "+highBid +" "+bidder);
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            if(resultSet.getString("AuctionEndTime") != null) {
	                LocalDateTime dateTime = LocalDateTime.parse(resultSet.getString("AuctionEndTime"), formatter);
	                time = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime);
	            }
	            conn.close();
	        } catch(Exception exp) {
	            System.out.println(exp);
	          
	        }
	        if (aucType != null) {
	            if (aucType.equals("Forward")) {
	                target = "/WSC.jsp";
	                BidType bidType = new ForwardBid();
	                System.out.println("in forward");
	                bidType.setType(session, id, name, desc, highBid, ship, time, price, bidder);
	            } else if (aucType.equals("Dutch")) {
	                target = "/DutchPage.jsp";
	                BidType bidType = new DutchBid();
	                bidType.setType(session, id, name, desc, highBid, ship, time, price, bidder);
	            } else {
	                return Response.status(Status.NOT_FOUND).build();
	            }
	        } else {
	            session.setAttribute("error", "You didn't select an item. Please go back to last page.");
	            target = "/ErrorPage.jsp";
	        }

	        return Response.ok(target, MediaType.TEXT_HTML).build();
	    }
	}
