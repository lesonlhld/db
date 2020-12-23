package service.impl;

import java.util.List;

import dao.InvoiceDao;
import dao.impl.InvoiceDaoImpl;
import model.Invoice;
import service.InvoiceService;

public class InvoiceServiceImpl implements InvoiceService {
	InvoiceDao invoiceDao = new InvoiceDaoImpl();

	@Override
	public void insert(Invoice invoice) {
		invoiceDao.insert(invoice);
	}
	
	@Override
	public void delete(int id) {
		invoiceDao.delete(id);
	}
	
	@Override
	public void edit(Invoice newInvoice) {
		Invoice oldInvoice = invoiceDao.get(newInvoice.getId());
		oldInvoice.setInvoiceDate(newInvoice.getInvoiceDate());
		oldInvoice.setInvoiceTime(newInvoice.getInvoiceTime());
		oldInvoice.setCart(newInvoice.getCart());
		oldInvoice.setVoucher(newInvoice.getVoucher());
		oldInvoice.setTotalMoney(newInvoice.getTotalMoney());
		oldInvoice.setPaymentMethod(newInvoice.getPaymentMethod()); 	
		invoiceDao.edit(oldInvoice);

	}

	@Override
	public Invoice get(int id) {
		return invoiceDao.get(id);
	}
	
	@Override
	public List<Invoice> getAll() {
		return invoiceDao.getAll();
	}

	@Override
	public List<Invoice> search(String name) {
		return invoiceDao.search(name);
	}

}

