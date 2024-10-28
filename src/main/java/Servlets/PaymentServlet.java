package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.PaymentService;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			response.setContentType("text/html");
			
			HttpSession session = request.getSession(false);
			String userName = request.getParameter("userName");
			String itemID = request.getParameter("itemID");
			String expShip = request.getParameter("exp");
			String itemBidder;
			PaymentService paymentService = new PaymentService();
			itemBidder = paymentService.getPaymentDetails(getServletContext(),userName, itemID, expShip, session);
			
			if (userName.equals(itemBidder)) {
				getServletConfig().getServletContext().getRequestDispatcher("/PaymentPage.jsp").forward(request, response);
			} else {
				session.setAttribute("error", "You didn't win the item. Better luck next time.");
				getServletConfig().getServletContext().getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
			}
			
		}catch(Exception exp){
			System.out.println(exp);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
