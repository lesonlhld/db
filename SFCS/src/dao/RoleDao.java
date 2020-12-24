package dao;

import java.util.List;

import model.Role;

public interface RoleDao {
	void insert(Role role);

	void edit(Role role);

	void delete(int id);

	Role get(int id);
	
	Role get(String name);

	List<Role> getAll();

	List<Role> search(String username);
}
