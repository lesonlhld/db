package service;

import java.util.List;

import model.Role;

public interface RoleService {
	void insert(Role role);

	void edit(Role role);

	void delete(int id);

	Role get(int id);
	
	Role get(String name);

	List<Role> getAll();

	List<Role> search(String username);
}
