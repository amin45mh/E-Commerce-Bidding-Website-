package Classes;

import javax.servlet.http.HttpSession;

public class ForwardBid extends BidType {

	@Override
	public void setUp(HttpSession session, String id, String name, String desc, String highBid, String ship, String time, String price, String bidder) {
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("desc", desc);
		session.setAttribute("highBid", highBid);
		session.setAttribute("ship", ship);
		session.setAttribute("fchecked", name);
		session.setAttribute("time", time);
		session.setAttribute("bidder", bidder);
	}
}
