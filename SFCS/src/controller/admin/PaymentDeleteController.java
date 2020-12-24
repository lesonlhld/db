package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PaymentService;
import service.impl.PaymentServiceImpl;
@WebServlet(urlPatterns = { "/admin/payment/delete" })
public class PaymentDeleteController extends HttpServlet {
	PaymentService paymentService = new PaymentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		paymentService.delete(Integer.parseInt(id));
		
		resp.sendRedirect(req.getContextPath() + "/admin/payment/list");
	}

}
