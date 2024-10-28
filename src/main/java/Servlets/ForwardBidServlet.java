package Servlets;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import services.ForwardBidService;
import services.ReceiptService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@ServerEndpoint("/ForwardBidServlet")
public class ForwardBidServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static ConcurrentHashMap<String, HashSet<Session>> repo = new ConcurrentHashMap<>();
	private static HashSet<Session> toBeNotified = new HashSet<Session>();
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		
		//sendMessageToAll("reload");
		ForwardBidService forwardBidService = new ForwardBidService();
		forwardBidService.handleBid(message, session);
		
		
	}
	public static void sendMessageToAll(String message) throws IOException {
	    for (Session session : clients) {
	        session.getBasicRemote().sendText(message);
	    }
	}



	@OnOpen
	public void onOpen(Session session) {
		// Add session to the connected sessions set
		clients.add(session);

	}

	@OnClose
	public void onClose(Session session) {
		// Remove session from the connected sessions set
		clients.remove(session);
	}

	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}

}
