package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import dao.CartDao;
import jdbc.JDBCConnection;
import model.Cart;
import model.OrderStatus;
import model.User;
import service.OrderStatusService;
import service.UserService;
import service.impl.OrderStatusServiceImpl;
import service.impl.UserServiceImpl;

public class CartDaoImpl extends JDBCConnection implements CartDao {
	UserService userS = new UserServiceImpl();
	OrderStatusService statusS = new OrderStatusServiceImpl();

	@Override
	public void insert(Cart cart) {
		String sql = "INSERT INTO orders(user_id, order_time, order_date) VALUES (?,?,?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
			ps.setInt(1, cart.getBuyer().getId());
			ps.setTime(2, new Time(cart.getBuyTime().getTime()));
			ps.setDate(3, new Date(cart.getBuyDate().getTime()));
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					cart.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Cart cart) {
		String sql = "UPDATE orders SET user_id = ?, order_time = ?, order_date = ?, status = ? WHERE order_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cart.getBuyer().getId());
			ps.setTime(2, new Time(cart.getBuyTime().getTime()));
			ps.setDate(3, new Date(cart.getBuyDate().getTime()));
			ps.setInt(4, cart.getStatus().getId()); 
			ps.setInt(5, cart.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM orders WHERE order_id = ?";
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
	public Cart get(int id) {
		String sql = "SELECT o.order_id, o.order_date, o.order_time, u.user_id, u.username, u.email, os.order_status_id, os.order_status_name "
				+ "FROM orders o, users u, order_statuses os "
				+ "WHERE o.user_id = u.user_id AND o.order_status_id = os.order_status_id AND o.order_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));
				OrderStatus status = statusS.get(rs.getInt("order_status_id"));

				Cart cart = new Cart();
				cart.setId(rs.getInt("order_id"));
				cart.setBuyTime(rs.getTime("order_time"));
				cart.setBuyDate(rs.getDate("order_date"));
				cart.setStatus(status);
				cart.setBuyer(user);

				return cart;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Cart> getAll() {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT o.order_id, o.order_date, o.order_time, u.user_id, u.username, u.email, os.order_status_id, os.order_status_name "
				+ "FROM orders o, users u, order_statuses os "
				+ "WHERE o.user_id = u.user_id AND o.order_status_id = os.order_status_id";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));
				OrderStatus status = statusS.get(rs.getInt("order_status_id"));

				Cart cart = new Cart();
				cart.setId(rs.getInt("order_id"));
				cart.setBuyTime(rs.getTime("order_time"));
				System.out.println(rs.getTime("order_time"));
				System.out.println(new Time(rs.getTime("order_time").getTime()));
				cart.setBuyDate(rs.getDate("order_date"));
				cart.setStatus(status);
				cart.setBuyer(user);

				cartList.add(cart);

			}
			return cartList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Cart> search(String name) {
		List<Cart> cartList = new ArrayList<Cart>();
		String sql = "SELECT o.order_id, o.order_date, o.order_time, u.user_id, u.username, u.email, os.order_status_id, os.order_status_name "
				+ "FROM orders o, users u, order_statuses os "
				+ "WHERE o.user_id = u.user_id AND o.order_status_id = os.order_status_id AND u.email LIKE ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = userS.get(rs.getInt("user_id"));
				OrderStatus status = statusS.get(rs.getInt("order_status_id"));

				Cart cart = new Cart();
				cart.setId(rs.getInt("order_id"));
				cart.setBuyTime(rs.getTime("order_time"));
				cart.setBuyDate(rs.getDate("order_date"));
				cart.setStatus(status);
				cart.setBuyer(user);

				cartList.add(cart);

			}
			return cartList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Cart get(String name) {
		return null;
	}
}
