package service.impl;

import java.util.List;

import dao.PaymentDao;
import dao.impl.PaymentDaoImpl;
import model.Payment;
import service.PaymentService;

public class PaymentServiceImpl implements PaymentService {
	PaymentDao paymentDao = new PaymentDaoImpl();

	@Override
	public void insert(Payment category) {
		paymentDao.insert(category);

	}

	@Override
	public void edit(Payment newPayment) {
		Payment oldCate = paymentDao.get(newPayment.getId());
		oldCate.setName(newPayment.getName());
		paymentDao.edit(oldCate);

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