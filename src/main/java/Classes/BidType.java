package Classes;

import javax.servlet.http.HttpSession;

public abstract class BidType { //Template Method Design Pattern
	
	public final void setType(HttpSession session, String id, String name, String desc, String highBid, String ship, String time, String price, String bidder){
		setUp(session, id, name, desc, highBid, ship, time, price, bidder);
		System.out.println("Bid Type excuted");
	}
	
	public abstract void setUp(HttpSession session, String id, String name, String desc, String highBid, String ship, String time, String price, String bidder);
}
