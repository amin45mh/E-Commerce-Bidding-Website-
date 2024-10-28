package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import beans.Item;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Path("/search")
public class SearchService {
	
		@GET
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public List<Item> search(@Context ServletContext servletContext, @QueryParam("searchTerm") String searchTerm) {
			 System.out.println("in search service");
			Connection conn = null;
			Statement stmt = null;
		        ResultSet rs = null;
			
		        List<Item> results = new ArrayList<Item>();
		       String projectLocation = servletContext.getRealPath("/database");
			   String url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
		    
			try { 
				
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection(url);
				
				// execute search query
				String sql;
				if (searchTerm.isEmpty()) {
					sql = "SELECT * FROM Inventory";
				} else {
					sql = "SELECT * FROM Inventory WHERE Name LIKE '%" + searchTerm + "%'";
				}
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				// build list of search results
				while (rs.next()) {
					Item currentItem = new Item();
					
					currentItem.setItemId(rs.getInt("ItemID"));
					currentItem.setName(rs.getString("Name"));
					currentItem.setDescription(rs.getString("Description"));
					currentItem.setAuctionType(rs.getString("AuctionType"));
					currentItem.setPrice(rs.getDouble("Price"));
					currentItem.setHighestBid(rs.getDouble("HighestBid"));
					currentItem.setBidder(rs.getString("Bidder"));
					
					Duration dur = null;
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					if(rs.getString("AuctionEndTime") != null) {
						LocalDateTime dateTime = LocalDateTime.parse(rs.getString("AuctionEndTime"), formatter);
						currentItem.setAuctionEndTime(dateTime);
						dur = Duration.between(LocalDateTime.now(), currentItem.getAuctionEndTime());
					}
					
					currentItem.setShippingTime(rs.getDouble("ShippingTime"));
					currentItem.setShippingPrice(rs.getDouble("ShippingPrice"));
					currentItem.setExpeditedCost(rs.getDouble("ExpeditedCost"));
					
					if(!(dur != null && dur.isNegative())) {
						results.add(currentItem);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try { rs.close(); } catch (Exception e) { /* ignored */ }
				try { stmt.close(); } catch (Exception e) { /* ignored */ }
				try { conn.close(); } catch (Exception e) { /* ignored */ }
			}
			
			return results;
		}
	}


