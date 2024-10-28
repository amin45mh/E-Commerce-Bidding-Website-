package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SearchServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			String userName = request.getParameter("userName");
			response.setContentType("text/html");
			String searchTerm = request.getParameter("searchTerm");
			HttpSession session = request.getSession();
			session.setAttribute("sTerm",searchTerm);
			session.setAttribute("userName", userName);
			
			getServletConfig().getServletContext().getRequestDispatcher("/SearchPage.jsp").forward(request, response);
		}catch(Exception exp){
			System.out.println(exp);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
