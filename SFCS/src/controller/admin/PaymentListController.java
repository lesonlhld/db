package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Payment;
import service.PaymentService;
import service.impl.PaymentServiceImpl;

@WebServlet(urlPatterns = { "/admin/payment/list" })
public class PaymentListController extends HttpServlet {
	PaymentService paymentService = new PaymentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		List<Payment> paymentList = paymentService.getAll();
		req.setAttribute("paymentList", paymentList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-payment.jsp");
		dispatcher.forward(req, resp);
	}

}
