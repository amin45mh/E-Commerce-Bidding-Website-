package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Item;
import services.SearchService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/ItemDisplayServlet")
public class ItemDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	public ItemDisplayServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  	String userName = request.getParameter("userName");
		    String searchTerm = request.getParameter("searchTerm");
		    
		    // Call SearchService to retrieve search results
		    SearchService searchService = new SearchService();
		    List<Item> results = searchService.search(getServletContext(),searchTerm);
		    
		    if(results.isEmpty()) {
		    	String message = "No Results - Please Try Another Search Term! ";
		    	request.setAttribute("message", message);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("SearchPage.jsp");
		    	dispatcher.forward(request, response);
			}
		    
		    // Forward results to JSP page for display
		    request.setAttribute("results", results);
		    HttpSession session=request.getSession();
		    session.setAttribute("userName", userName);
		    RequestDispatcher rd = request.getRequestDispatcher("/ItemDisplayPage.jsp");
		    rd.forward(request, response);
		    System.out.println("in item display");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
