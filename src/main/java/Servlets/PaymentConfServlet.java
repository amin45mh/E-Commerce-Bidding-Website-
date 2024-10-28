package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.PaymentConfService;

@WebServlet("/PaymentConfServlet")
public class PaymentConfServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public PaymentConfServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		PaymentConfService PaymentConf = new PaymentConfService();
		PaymentConf.paymentConf(getServletContext(),userName, request, response);
		//	request.getRequestDispatcher("/PaymentConfPage.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
