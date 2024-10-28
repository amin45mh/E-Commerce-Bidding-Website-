package services;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.Session;
import javax.ws.rs.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Classes.ConfigManager;
import Servlets.ForwardBidServlet;

@Path("/ForwardBidService")
public class ForwardBidService {
	
	private static final long serialVersionUID = 1L;
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static ConcurrentHashMap<String, HashSet<Session>> repo = new ConcurrentHashMap<>();
	
	public void handleBid(String message, Session session) throws IOException {
        String[] parts = message.split(":");
        String messageNum = parts[0].replaceAll("[^0-9]", "");
        String messageItem = parts[0].replaceAll("[^a-zA-Z]+", "");
        String userName = parts[1];
        String itemChosen = "";
        int bid = Integer.parseInt(messageNum);
        String bidS = messageNum;
        String highestBidS = "";
        String ID = "";
        double highestBid = 0;

     
        synchronized (clients) {
            try {
            	String url = ConfigManager.getUrl();
              
                Connection conn = null;
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(url);
                String query = "SELECT * FROM Inventory WHERE Name = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, messageItem);
                ResultSet resultSet = preparedStatement.executeQuery();
                ID = resultSet.getString("ItemID");
                conn.close();
            } catch(Exception exp) {
                System.out.println(exp);
            }

            itemChosen = ID;
            HashSet<Session> theList = repo.get(ID);
            if (theList == null) {
                theList = new HashSet<Session>();
            }
            theList.add(session);
            repo.put(ID, theList);
            Set<Session> toBeNotified = theList;

            for (Session client : toBeNotified) {
                forwardAuc(itemChosen, highestBidS, highestBid, userName, bid, bidS, client, message, session);
            }
        }
    }
	
	public void forwardAuc(String itemChosen, String highestBidS, double highestBid, String userName, int bid, String bidS, Session client, String message, Session session) {
		try { 
			String url = ConfigManager.getUrl();
			
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            String query = "SELECT * FROM Inventory WHERE ItemID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, itemChosen);
            ResultSet resultSet = preparedStatement.executeQuery();
            
			if (resultSet.getString("HighestBid") != null) {
				highestBidS = resultSet.getString("HighestBid");
			} else {
				highestBidS = resultSet.getString("Price");
			}
			highestBid = Integer.parseInt(highestBidS);
			conn.close();
			
		} catch(Exception exp) {
			System.out.println(exp);
		}
		
		if (bid > highestBid) {
			if (client.equals(session)) {
				try {
					String url = ConfigManager.getUrl();
					ForwardBidServlet.sendMessageToAll("reload");
					PreparedStatement stmt = null;
					Connection conn = null;
					Class.forName("org.sqlite.JDBC");
					conn = DriverManager.getConnection(url);
					String sql = "UPDATE Inventory SET HighestBid = ?, Bidder = ? WHERE ItemID = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, bidS);
					stmt.setString(2, userName);
					stmt.setString(3, itemChosen);
					stmt.executeUpdate();
					stmt.close();
					conn.close();
				} catch(Exception exp) {
					System.out.println(exp);
				}
				
			}
			

			/*if (!client.equals(session)) {
				System.out.println("THE MESSAGE IS " + bidS);
				try {
					client.getBasicRemote().sendText("New Bid: "+bidS);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
			
		} else {
			if (client.equals(session)) {
				System.out.println("YOUR BID ISN'T HIGH ENOUGH! " + bid);
				try {
					client.getBasicRemote().sendText("Bid must be greater than "+ highestBid);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}