package Servlets;

import java.io.IOException;
import javax.ws.rs.core.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.BidService;


@WebServlet("/BidPrepServlet")
public class BidPrepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String checked;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkedName = request.getParameter("checked");
		String userName = request.getParameter("userName");
		checked = request.getParameter("checked");
		BidService bidService = new BidService();
		Response bidResponse = bidService.bid(getServletContext(), request, checkedName, userName);

	    String target = bidResponse.getEntity().toString();
        request.getRequestDispatcher(target).forward(request, response);
	   
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
