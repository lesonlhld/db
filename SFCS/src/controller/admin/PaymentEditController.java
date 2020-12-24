package controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Payment;
import service.PaymentService;
import service.impl.PaymentServiceImpl;

@WebServlet(urlPatterns = { "/admin/payment/edit" })
public class PaymentEditController extends HttpServlet {
	PaymentService paymentService = new PaymentServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		Payment payment = paymentService.get(Integer.parseInt(id));
		
		req.setAttribute("payment", payment);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/edit-payment.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Payment payment = new Payment();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		
		try {
			List<FileItem> items = servletFileUpload.parseRequest(req);

			for (FileItem item : items) {
				if (item.getFieldName().equals("id")) {
					payment.setId(Integer.parseInt(item.getString()));
				} else if (item.getFieldName().equals("name")) {
					payment.setName(item.getString("UTF-8"));
				}		
			}

			paymentService.edit(payment);

			resp.sendRedirect(req.getContextPath() + "/admin/payment/list");
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
