package service.impl;

import java.util.List;

import dao.PaymentDao;
import dao.impl.PaymentDaoImpl;
import model.Payment;
import service.PaymentService;

public class PaymentServiceImpl implements PaymentService {
	PaymentDao paymentDao = new PaymentDaoImpl();

	@Override
	public void insert(Payment payment) {
		paymentDao.insert(payment);

	}

	@Override
	public void edit(Payment newPayment) {
		Payment oldPayment = paymentDao.get(newPayment.getId());
		oldPayment.setName(newPayment.getName());
		paymentDao.edit(oldPayment);

	}

	@Override
	public void delete(int id) {
		paymentDao.delete(id);

	}

	@Override
	public Payment get(int id) {
		return paymentDao.get(id);
	}

	@Override
	public Payment get(String name) {
		return paymentDao.get(name);
	}

	@Override
	public List<Payment> getAll() {
		return paymentDao.getAll();
	}

	@Override
	public List<Payment> search(String username) {
		return paymentDao.search(username);
	}
}