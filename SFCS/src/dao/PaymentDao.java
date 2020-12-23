package dao;

import java.util.List;

import model.Payment;

public interface PaymentDao {
	void insert(Payment payment);

	void edit(Payment payment);

	void delete(int id);

	Payment get(int id);
	
	Payment get(String name);

	List<Payment> getAll();

	List<Payment> search(String username);
}
