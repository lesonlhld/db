package dao;

import java.util.List;

import model.Invoice;

public interface InvoiceDao {
	void insert(Invoice invoice);

	void edit(Invoice invoice);

	void delete(int id);

	Invoice get(String name);

	Invoice get(int id);

	List<Invoice> getAll();

	List<Invoice> search(String name);
}
