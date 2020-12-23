package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PaymentDao;
import jdbc.JDBCConnection;
import model.Payment;

public class PaymentDaoImpl extends JDBCConnection implements PaymentDao {

	@Override
	public void insert(Payment payment) {
		String sql = "INSERT INTO payments(payment_method) VALUES (?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, payment.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Payment payment) {
		String sql = "UPDATE payments SET payment_method = ? WHERE payment_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, payment.getName());
			ps.setInt(2, payment.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM payments WHERE payment_id = ?";
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
	public Payment get(int id) {
		String sql = "SELECT * FROM payments WHERE payment_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment payment = new Payment();

				payment.setId(rs.getInt("payment_id"));
				payment.setName(rs.getString("payment_method"));

				return payment;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Payment> getAll() {
		List<Payment> payments = new ArrayList<Payment>();
		String sql = "SELECT * FROM payments";
		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment payment = new Payment();

				payment.setId(rs.getInt("payment_id"));
				payment.setName(rs.getString("payment_method"));

				payments.add(payment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payments;
	}

	@Override
	public List<Payment> search(String keyword) {
		List<Payment> payments = new ArrayList<Payment>();
		String sql = "SELECT * FROM payments WHERE payment_method LIKE ?";
		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment payment = new Payment();

				payment.setId(rs.getInt("id"));
				payment.setName(rs.getString("payment_method"));

				payments.add(payment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return payments;
	}

	@Override
	public Payment get(String name) {
		String sql = "SELECT * FROM payments WHERE payment_method LIKE ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment payment = new Payment();

				payment.setId(rs.getInt("payment_id"));
				payment.setName(rs.getString("payment_method"));

				return payment;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
