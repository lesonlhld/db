package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import dao.InvoiceDao;
import jdbc.JDBCConnection;
import model.Invoice;
import model.Cart;
import service.CartService;
import service.impl.CartServiceImpl;
import model.Payment;
import service.PaymentService;
import service.impl.PaymentServiceImpl;

public class InvoiceDaoImpl extends JDBCConnection implements InvoiceDao {
	CartService cartS = new CartServiceImpl();
	PaymentService paymentS = new PaymentServiceImpl();

	@Override
	public void insert(Invoice invoice) {
		String sql = "INSERT INTO invoices(order_id, invoice_total, invoice_date, invoice_time, code, payment_id)"
				+ "VALUES (?,?,?,?,?,?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
			ps.setInt(1, invoice.getCart().getId());
			ps.setLong(2, invoice.getTotalMoney());
			ps.setDate(3, new Date(invoice.getInvoiceDate().getTime()));
			ps.setTime(4, new Time(invoice.getInvoiceTime().getTime()));
			ps.setString(5, invoice.getVoucher());
			ps.setInt(6, invoice.getPaymentMethod().getId());
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					invoice.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Invoice invoice) {
		String sql = "UPDATE invoices SET order_id = ?, invoice_total = ?, invoice_date = ?, invoice_time = ?, code = ?, paymentMethod = ? WHERE invoice_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, invoice.getCart().getId());
			ps.setLong(2, invoice.getTotalMoney());
			ps.setDate(3, new Date(invoice.getInvoiceDate().getTime()));
			ps.setTime(4, new Time(invoice.getInvoiceTime().getTime()));
			ps.setString(5, invoice.getVoucher());
			ps.setInt(6, invoice.getPaymentMethod().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM invoices WHERE invoice_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Invoice get(int id) {
		String sql = "SELECT o.order_id, o.order_date, o.order_time, u.order_id, u.cartname, u.email, os.payment_id, os.name "
				+ "FROM orders o, carts u, order_paymentMethodes os "
				+ "WHERE o.order_id = u.order_id AND o.paymentMethod = os.payment_id AND o.order_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = cartS.get(rs.getInt("order_id"));
				Payment payment = paymentS.get(rs.getInt("payment_id"));

				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt("order_id"));
				invoice.setInvoiceTime(rs.getTime("order_time"));
				invoice.setInvoiceDate(rs.getDate("order_date"));
				invoice.setPaymentMethod(payment);
				invoice.setCart(cart);

				return invoice;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Invoice> getAll() {
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		String sql = "SELECT* "
				+ "FROM invoices";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = cartS.get(rs.getInt("order_id"));
				Payment payment = paymentS.get(rs.getInt("payment_id"));

				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt("invoice_id"));
				invoice.setTotalMoney(rs.getLong("invoice_total"));
				invoice.setInvoiceTime(rs.getTime("invoice_time"));
				invoice.setInvoiceDate(rs.getDate("invoice_date"));
				invoice.setPaymentMethod(payment);;
				invoice.setCart(cart);

				invoiceList.add(invoice);

			}
			return invoiceList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Invoice> search(String name) {
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		String sql = "SELECT o.order_id, o.order_date, o.order_time, u.order_id, u.cartname, u.email, os.payment_id, os.name "
				+ "FROM orders o, carts u, order_paymentMethodes os "
				+ "WHERE o.order_id = u.order_id AND o.paymentMethod = os.payment_id AND u.email LIKE ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cart cart = cartS.get(rs.getInt("order_id"));
				Payment payment = paymentS.get(rs.getInt("payment_id"));

				Invoice invoice = new Invoice();
				invoice.setId(rs.getInt("order_id"));
				invoice.setInvoiceTime(rs.getTime("order_time"));
				invoice.setInvoiceDate(rs.getDate("order_date"));
				invoice.setPaymentMethod(payment);
				invoice.setCart(cart);

				invoiceList.add(invoice);

			}
			return invoiceList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Invoice get(String name) {
		return null;
	}
}
