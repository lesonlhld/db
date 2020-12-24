package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDao;
import jdbc.JDBCConnection;
import model.Role;

public class RoleDaoImpl extends JDBCConnection implements RoleDao {

	@Override
	public void insert(Role role) {
		String sql = "INSERT INTO roles(role_name) VALUES (?)";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, role.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Role role) {
		String sql = "UPDATE roles SET role_name = ? WHERE role_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, role.getName());
			ps.setInt(2, role.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM roles WHERE role_id = ?";
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
	public Role get(int id) {
		String sql = "SELECT * FROM roles WHERE role_id = ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role role = new Role();

				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));

				return role;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Role> getAll() {
		List<Role> roles = new ArrayList<Role>();
		String sql = "SELECT * FROM roles";
		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role role = new Role();

				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));

				roles.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public List<Role> search(String keyword) {
		List<Role> roles = new ArrayList<Role>();
		String sql = "SELECT * FROM roles WHERE role_name LIKE ?";
		Connection conn = super.getJDBCConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role role = new Role();

				role.setId(rs.getInt("id"));
				role.setName(rs.getString("role_name"));

				roles.add(role);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return roles;
	}

	@Override
	public Role get(String name) {
		String sql = "SELECT * FROM roles WHERE role_name LIKE ?";
		Connection con = super.getJDBCConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Role role = new Role();

				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));

				return role;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}