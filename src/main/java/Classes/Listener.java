package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import beans.Item;

// Updates Auctions
@WebListener
public class Listener implements ServletContextListener {
	private String url;
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		String projectLocation = ctx.getRealPath("/database");
		System.out.println(projectLocation);
		url ="jdbc:sqlite:"+projectLocation+"/InventoryDB.db";
		
		System.out.println(url);
		System.out.println("Test");
		updateDutch();
	}
	public void updateDutch() {
		System.out.println("UpdateDutch called");
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("UpdateDutch Attempted Execution");
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					//List<Item> results = new ArrayList<Item>();
					Class.forName("org.sqlite.JDBC");
					conn = DriverManager.getConnection(url);
					List<Item> results = new ArrayList<Item>();

					// execute search query
					String sql = "SELECT * FROM Inventory WHERE AuctionType = 'Dutch'";
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);

					System.out.println("UpdateDutch Attempted Search");

					// build list of search results
					while (rs.next()) {
						System.out.println("UpdateDutch Tried Something Else");
						Item currentItem = new Item();

						currentItem.setItemId(rs.getInt("ItemID"));
						currentItem.setName(rs.getString("Name"));
						currentItem.setDescription(rs.getString("Description"));
						currentItem.setAuctionType(rs.getString("AuctionType"));
						currentItem.setPrice(rs.getDouble("Price"));
						currentItem.setHighestBid(rs.getDouble("HighestBid"));
						currentItem.setBidder(rs.getString("Bidder"));

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						if(rs.getString("AuctionEndTime") != null) {
							LocalDateTime dateTime = LocalDateTime.parse(rs.getString("AuctionEndTime"), formatter);
							currentItem.setAuctionEndTime(dateTime);
						}

						currentItem.setShippingTime(rs.getDouble("ShippingTime"));
						currentItem.setShippingPrice(rs.getDouble("ShippingPrice"));
						currentItem.setExpeditedCost(rs.getDouble("ExpeditedCost"));
						results.add(currentItem);

						System.out.println("UpdateDutch: ItemName = " + currentItem.getName());
					}
					stmt.close();

					for(Item item : results) {
						double initialPrice = item.getHighestBid();
						double currentPrice = initialPrice;
						Class.forName("org.sqlite.JDBC");
						currentPrice *= 0.95;
						try {
							sql = "UPDATE Inventory SET HighestBid = ? WHERE ItemID = ?";
							PreparedStatement pStmt = null;
							pStmt = conn.prepareStatement(sql);
							pStmt.setDouble(1, currentPrice);
							pStmt.setInt(2, item.getItemId());
							pStmt.executeUpdate();
							pStmt.close();

							System.out.println("UpdateDutch Attempted Update");
						} catch(Exception exp) {
							System.out.println(exp);
						}
						if (currentPrice <= item.getPrice()) {
							try {
								sql = "DELETE FROM Inventory WHERE ItemID = ?";
								PreparedStatement pStmt = null;
								pStmt = conn.prepareStatement(sql);
								pStmt.setInt(1, item.getItemId());
								pStmt.executeUpdate();
								pStmt.close();

								System.out.println("UpdateDutch Attempted Delete");
							} catch(Exception exp) {
								System.out.println(exp);
							}
						}
						System.out.println("UpdateDutch Successfully Executed");
					}
					System.out.println("UpdateDutch Done");
					stmt.close();
					rs.close();
					conn.close();
				}catch(Exception exp) {
					System.out.println(exp);
				}
			}
		}, 1, 5, TimeUnit.MINUTES);
		System.out.println("Death");
		//executorService.shutdown();
	}
}