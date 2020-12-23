package service;

import java.util.List;

import model.Invoice;

public interface InvoiceService {
	void insert(Invoice invoice);

	void edit(Invoice newInvoice);

	void delete(int id);

	Invoice get(int id);
	
	List<Invoice> getAll();

	List<Invoice> search(String keyword);
}
