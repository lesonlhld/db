package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Invoice;
import service.InvoiceService;
import service.impl.InvoiceServiceImpl;
@WebServlet(urlPatterns= {"/admin/invoice/list"})
public class InvoiceListController extends HttpServlet {
	InvoiceService invoiceService = new InvoiceServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<Invoice> listInvoice = invoiceService.getAll();
	req.setAttribute("listInvoice", listInvoice);
	RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/view/list-invoice.jsp");
	dispatcher.forward(req, resp);
	}
	
}
