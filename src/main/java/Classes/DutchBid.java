package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

public class DutchBid extends BidType {
	
	@Override
	public void setUp(HttpSession session, String id, String name, String desc, String highBid, String ship, String time, String price, String bidder) {
		
			session.setAttribute("id", id);
		    session.setAttribute("name", name);
		    session.setAttribute("desc", desc);
		    session.setAttribute("price",highBid);
		    session.setAttribute("ship", ship);
		  
	}

}
