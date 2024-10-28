package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import services.SignInService;
import services.SignUpService;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String streetNumber = request.getParameter("streetNumber");
		String postalCode = request.getParameter("postalCode");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");

		try { 
			SignUpService signUpService = new SignUpService();
			Response signUp = signUpService.signUp(getServletContext(),firstName, lastName, address, streetNumber, postalCode, city, country, userName, passWord);
			if(signUp.getStatus() != 200) {
				request.setAttribute("message", signUp.getEntity());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SignUpPage.jsp");
				dispatcher.forward(request, response);
			}
			response.setContentType("text/html");
			getServletConfig().getServletContext().getRequestDispatcher("/SignInServlet").forward(request, response);


		}catch(Exception exp){
			System.out.println(exp);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
