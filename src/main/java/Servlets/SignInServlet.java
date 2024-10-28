package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import services.SignInService;

@WebServlet("/SignInServlet")
public class SignInServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public SignInServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

		try { 
			SignInService signinService = new SignInService();
			Response signin = signinService.signin(getServletContext(),userName, passWord);
			if(signin.getStatus() != 200) {
				request.setAttribute("message", signin.getEntity());
				RequestDispatcher dispatcher = request.getRequestDispatcher("SignInPage.jsp");
				dispatcher.forward(request, response);
			}
			response.setContentType("text/html");
			getServletConfig().getServletContext().getRequestDispatcher("/SearchServlet").forward(request, response);

		}catch(Exception exp){
			System.out.println(exp);
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
