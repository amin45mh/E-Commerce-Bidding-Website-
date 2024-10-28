package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.ReceiptService;

@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ReceiptServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html");

            HttpSession session = request.getSession(false);
            String userName = request.getParameter("userName");
            String itemID = request.getParameter("itemID");
            String tCost = (String) session.getAttribute("tCost");
            String cardNum = request.getParameter("cardNum");
            String cardHold = request.getParameter("cardHold");
            String date = request.getParameter("date");
            String secCode = request.getParameter("secCode");
            
            ReceiptService receiptService = new ReceiptService();
            receiptService.generateReceipt(getServletContext(),userName, itemID, tCost, cardNum, cardHold, date, secCode, session);

            getServletConfig().getServletContext().getRequestDispatcher("/ReceiptPage.jsp").forward(request,
                    response);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

